#include <Arduino.h>
// #include <Keyboard.h>
#include <Keyboard.h>
#include <Mouse.h>
#include <string.h>
#include <SoftwareSerial.h>


//forward Declartions
///////////////////////
void performPressKeyboard(uint8_t t_bit);
void performReleaseKeyboard(uint8_t t_bit);


#define SHIFT 0x80
const uint8_t asciimap[128] =
{
	0x00,             // NUL	0
	0x00,             // SOH	1
	0x00,             // STX	2
	0x00,             // ETX	3
	0x00,             // EOT	4
	0x00,             // ENQ	5
	0x00,             // ACK  6
	0x00,             // BEL	7
	0x2a,							// BS		8
	0x2b,							// TAB	9
	0x28,							// LF		10
	0x00,             // VT 	11
	0x00,             // FF 	12
	0x00,             // CR 	13
	0x00,             // SO 	14
	0x00,             // SI 	15
	0x00,             // DEL	16
	0x00,             // DC1	17
	0x00,             // DC2	18
	0x00,             // DC3	19
	0x00,             // DC4	20
	0x00,             // NAK	21
	0x00,             // SYN	22
	0x00,             // ETB	23
	0x00,             // CAN	24
	0x00,             // EM 	25
	0x00,             // SUB	26
	0x00,             // ESC	27
	0x00,             // FS 	28
	0x00,             // GS 	29
	0x00,             // RS 	30
	0x00,             // US 	31
	0x2c,		   				// ' '	32	
	0x1e|SHIFT,	  		// !		33
	0x34|SHIFT,	  		// "		34
	0x20|SHIFT,   		// #		35
	0x21|SHIFT,   		// $		36
	0x22|SHIFT,   		// %		37
	0x24|SHIFT,   		// &		38
	0x34,         		// '		39
	0x26|SHIFT,   		// (		40
	0x27|SHIFT,   		// )		41
	0x25|SHIFT,   		// *		42
	0x2e|SHIFT,   		// +		43
	0x36,         		// ,		44
	0x2d,         		// -		45
	0x37,         		// .		46
	0x38,         		// /		47
	0x27,         		// 0		48
	0x1e,         		// 1		49
	0x1f,         		// 2		50
	0x20,         		// 3		51
	0x21,         		// 4		52
	0x22,         		// 5		53
	0x23,         		// 6		54
	0x24,         		// 7		55
	0x25,         		// 8		56
	0x26,         		// 9    57
	0x33|SHIFT,      	// :
	0x33,          		// ;
	0x36|SHIFT,      	// <
	0x2e,          		// =
	0x37|SHIFT,      	// >
	0x38|SHIFT,      	// ?
	0x1f|SHIFT,      	// @
	0x04|SHIFT,      	// A
	0x05|SHIFT,      	// B
	0x06|SHIFT,      	// C
	0x07|SHIFT,      	// D
	0x08|SHIFT,      	// E
	0x09|SHIFT,      	// F
	0x0a|SHIFT,      	// G
	0x0b|SHIFT,      	// H
	0x0c|SHIFT,      	// I
	0x0d|SHIFT,      	// J
	0x0e|SHIFT,      	// K
	0x0f|SHIFT,      	// L
	0x10|SHIFT,      	// M
	0x11|SHIFT,      	// N
	0x12|SHIFT,      	// O
	0x13|SHIFT,      	// P
	0x14|SHIFT,      	// Q
	0x15|SHIFT,      	// R
	0x16|SHIFT,      	// S
	0x17|SHIFT,      	// T
	0x18|SHIFT,      	// U
	0x19|SHIFT,      	// V
	0x1a|SHIFT,      	// W
	0x1b|SHIFT,      	// X
	0x1c|SHIFT,      	// Y
	0x1d|SHIFT,      	// Z
	0x2f,          		// [
	0x31,          		// bslash
	0x30,          		// ]
	0x23|SHIFT,    		// ^
	0x2d|SHIFT,    		// _
	0x35,          		// `
	0x04,          		// a
	0x05,          		// b
	0x06,          		// c
	0x07,          		// d
	0x08,          		// e
	0x09,          		// f
	0x0a,          		// g
	0x0b,          		// h
	0x0c,          		// i
	0x0d,          		// j
	0x0e,          		// k
	0x0f,          		// l
	0x10,          		// m
	0x11,          		// n
	0x12,          		// o
	0x13,          		// p
	0x14,          		// q
	0x15,          		// r
	0x16,          		// s
	0x17,          		// t
	0x18,          		// u
	0x19,          		// v
	0x1a,          		// w
	0x1b,          		// x
	0x1c,          		// y
	0x1d,          		// z
	0x2f|SHIFT,    		// {
	0x31|SHIFT,    		// |
	0x30|SHIFT,    		// }
	0x35|SHIFT,    		// ~
	0									// DEL
};

struct MODIFIERKEYS {
        uint8_t bmLeftCtrl : 1;
        uint8_t bmLeftShift : 1;
        uint8_t bmLeftAlt : 1;
        uint8_t bmLeftGUI : 1;
        uint8_t bmRightCtrl : 1;
        uint8_t bmRightShift : 1;
        uint8_t bmRightAlt : 1;
        uint8_t bmRightGUI : 1;
};


int buttonPin = 9;  // Set a button to any pin

//Screen Resolution
int resX = 1024;
int resY = 768; 

///////////////// Serial Communications Section
String inputString = "";         // a String to hold incoming data
bool stringComplete = false;  // whether the string is complete

uint8_t f_bit;
uint8_t s_bit;
uint8_t t_bit;



/*
  Not all pins on the Leonardo support change interrupts, 
  so only the following can be used for RX: 
  8, 9, 10, 11, 14 (MISO), 15 (SCK), 16 (MOSI).
*/
SoftwareSerial mySerial(10,9);// RX, TX

void setup() {

  Serial.begin( 14400 );
  mySerial.begin( 9600 );

 
  // pinMode(buttonPin, INPUT);  // Set the button as an input
  // digitalWrite(buttonPin, HIGH);  // Pull the button high

  // reserve 200 bytes for the inputString:
  inputString.reserve(200);

}

void loop() {   

  // Keyboard.write('z');  // send a 'z' to the computer via Keyboard HID
  // delay(1000);  // delay so there aren't a kajillion z's
  // Mouse.move(resX/2,resY/2,0);
  // Mouse.press(MOUSE_LEFT);
  // delay(1000);  // delay so there aren't a kajillion z's
  // Mouse.move(resX/3,resY/3,0);
  // Mouse.release(MOUSE_LEFT);
  // delay(25000);  // delay so there aren't a kajillion z's

  if (stringComplete) {
      
    char* str;
    char buff[20];
    strcpy(buff,inputString.c_str());
    const char delim[2] = " ";

    //Serial.println(inputString);
    
    str = strtok(buff,delim);  
    f_bit = strtoul((const char*)str, NULL,16);

    str = strtok(NULL,delim);
    s_bit = strtoul((const char*)str, NULL,16);

    str = strtok(NULL,delim);
    t_bit = strtoul((const char*)str, NULL,16);

    /*
      f_bit - keyup/keydown
      s_bit - modifier
      t_bit - keycode
    */


	 	/*
					struct MODIFIERKEYS {
									uint8_t bmLeftCtrl : 1;
									uint8_t bmLeftShift : 1;
									uint8_t bmLeftAlt : 1;
									uint8_t bmLeftGUI : 1;
									uint8_t bmRightCtrl : 1;
									uint8_t bmRightShift : 1;
									uint8_t bmRightAlt : 1;
									uint8_t bmRightGUI : 1;
					};
		 */
		MODIFIERKEYS mod;
		*((uint8_t*)&mod) = s_bit;

		mod.bmLeftCtrl == 1 ? Keyboard.press(KEY_LEFT_CTRL) : Keyboard.release(KEY_LEFT_CTRL);
		mod.bmLeftShift == 1 ? Keyboard.press(KEY_LEFT_SHIFT) : Keyboard.release(KEY_LEFT_SHIFT);
		mod.bmLeftAlt == 1 ? Keyboard.press(KEY_LEFT_ALT) : Keyboard.release(KEY_LEFT_ALT);
		mod.bmLeftGUI == 1 ? Keyboard.press(KEY_LEFT_GUI) : Keyboard.release(KEY_LEFT_GUI);
		mod.bmRightCtrl == 1 ? Keyboard.press(KEY_RIGHT_CTRL) : Keyboard.release(KEY_RIGHT_CTRL);
		mod.bmRightShift == 1 ? Keyboard.press(KEY_RIGHT_SHIFT) : Keyboard.release(KEY_RIGHT_SHIFT);
		mod.bmRightAlt == 1 ? Keyboard.press(KEY_RIGHT_ALT) : Keyboard.release(KEY_RIGHT_ALT);
		mod.bmRightGUI == 1 ? Keyboard.press(KEY_RIGHT_GUI) : Keyboard.release(KEY_RIGHT_GUI);

		// 
		f_bit==0x00 ? performPressKeyboard(t_bit) : performReleaseKeyboard(t_bit);

    // clear the string:
    inputString = "";
    f_bit = 0x00;
    s_bit = 0x00;
    t_bit = 0x00;
  
    stringComplete = false;
  }
  
  if(mySerial.available()){

    while (mySerial.available()) {
      uint8_t inChar = mySerial.read();
      if (inChar == 0x7e) {
        stringComplete = true;
        break;
      }
      inputString += String(inChar,HEX);
      inputString += " ";
    }
  }else{
    // Serial.println("No Data...");
    // delay(1000);
  }

}


void performPressKeyboard(uint8_t t_bit){
	switch(t_bit){
		case 82:
			Keyboard.press(KEY_UP_ARROW);
			break;
		case 81:
			Keyboard.press(KEY_DOWN_ARROW);
			break;
		case 80:
			Keyboard.press(KEY_LEFT_ARROW);
			break;
		case 79:
			Keyboard.press(KEY_RIGHT_ARROW);
			break;
		case 42:
			Keyboard.press(KEY_BACKSPACE);
			break;
		case 43:
			Keyboard.press(KEY_TAB);
			break;
		case 40:
			Keyboard.press(KEY_RETURN);
			break;
		case 41:
			Keyboard.press(KEY_ESC);
			break;
		case 73:
			Keyboard.press(KEY_INSERT);
			break;
		case 76:
			Keyboard.press(KEY_DELETE);
			break;
		case 75:
			Keyboard.press(KEY_PAGE_UP);
			break;
		case 78:
			Keyboard.press(KEY_PAGE_DOWN);
			break;
		case 74:
			Keyboard.press(KEY_HOME);
			break;
		case 77:
			Keyboard.press(KEY_END);
			break;
		case 57:
			Keyboard.press(KEY_CAPS_LOCK);
			break;
		case 58:
			Keyboard.press(KEY_F1);
			break;
		case 59:
			Keyboard.press(KEY_F2);
			break;
		case 60:
			Keyboard.press(KEY_F3);
			break;
		case 61:
			Keyboard.press(KEY_F4);
			break;
		case 62:
			Keyboard.press(KEY_F5);
			break;
		case 63:
			Keyboard.press(KEY_F6);
			break;
		case 64:
			Keyboard.press(KEY_F7);
			break;
		case 65:
			Keyboard.press(KEY_F8);
			break;
		case 66:
			Keyboard.press(KEY_F9);
			break;
		case 67:
			Keyboard.press(KEY_F10);
			break;
		case 68:
			Keyboard.press(KEY_F11);
			break;
		case 69:
			Keyboard.press(KEY_F12);
			break;
		default: {
			//for assci
      for(uint8_t i = 0;i< 128;i++){
        if(asciimap[i]==t_bit){
          Keyboard.press(i);
        }
      }
		}

	}
}

void performReleaseKeyboard(uint8_t t_bit){
	switch(t_bit){
		case 82:
			Keyboard.release(KEY_UP_ARROW);
			break;
		case 81:
			Keyboard.release(KEY_DOWN_ARROW);
			break;
		case 80:
			Keyboard.release(KEY_LEFT_ARROW);
			break;
		case 79:
			Keyboard.release(KEY_RIGHT_ARROW);
			break;
		case 42:
			Keyboard.release(KEY_BACKSPACE);
			break;
		case 43:
			Keyboard.release(KEY_TAB);
			break;
		case 40:
			Keyboard.release(KEY_RETURN);
			break;
		case 41:
			Keyboard.release(KEY_ESC);
			break;
		case 73:
			Keyboard.release(KEY_INSERT);
			break;
		case 76:
			Keyboard.release(KEY_DELETE);
			break;
		case 75:
			Keyboard.release(KEY_PAGE_UP);
			break;
		case 78:
			Keyboard.release(KEY_PAGE_DOWN);
			break;
		case 74:
			Keyboard.release(KEY_HOME);
			break;
		case 77:
			Keyboard.release(KEY_END);
			break;
		case 57:
			Keyboard.release(KEY_CAPS_LOCK);
			break;
		case 58:
			Keyboard.release(KEY_F1);
			break;
		case 59:
			Keyboard.release(KEY_F2);
			break;
		case 60:
			Keyboard.release(KEY_F3);
			break;
		case 61:
			Keyboard.release(KEY_F4);
			break;
		case 62:
			Keyboard.release(KEY_F5);
			break;
		case 63:
			Keyboard.release(KEY_F6);
			break;
		case 64:
			Keyboard.release(KEY_F7);
			break;
		case 65:
			Keyboard.release(KEY_F8);
			break;
		case 66:
			Keyboard.release(KEY_F9);
			break;
		case 67:
			Keyboard.release(KEY_F10);
			break;
		case 68:
			Keyboard.release(KEY_F11);
			break;
		case 69:
			Keyboard.release(KEY_F12);
			break;
		default: {
			//for assci
      for(uint8_t i = 0;i< 128;i++){
        if(asciimap[i]==t_bit){
          Keyboard.release(i);
        }
      }
		}

	}
}
