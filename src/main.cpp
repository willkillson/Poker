#include <Arduino.h>
#include <Keyboard.h>
#include <Mouse.h>
#include <string.h>
#include <SoftwareSerial.h>



//Decimal Indexed ASSCII map
const uint8_t asciiMap[128] =
{
  //Hex       CHAR       DEC
	0x00,   //  NUL         0
	0x01,   //  SOH         1
	0x02,   //  STX         2
	0x03,   //  ETX         3
	0x04,   //  EOT         4
	0x05,   //  ENQ         5
	0x06,   //  ACK         6
	0x07,   //  BEL         7
	0x08,		//  BS	        8
	0x09,		//  TAB         9
	0x0A,		//  LF          10
	0x0B,   //  VT          11 
	0x0C,   //  FF          12
	0x0D,   //  CR          13
	0x0E,   //  SO          14
	0x0F,   //  SI          15

	0x10,   //  DEL         16
	0x11,   //  DC1         17
	0x12,   //  DC2         18
	0x13,   //  DC3         19
	0x14,   //  DC4         20
	0x15,   //  NAK         21
	0x16,   //  SYN         22
	0x17,   //  ETB         23
	0x18,   //  CAN         24
	0x19,   //  EM          25
	0x1A,   //  SUB         26
	0x1B,   //  ESC         27
	0x1C,   //  FS          28
	0x1D,   //  GS          29
	0x1E,   //  RS          30
	0x1F,   //  US          31

	0x20,		//  ' '         32
	0x21,	  //  !           33
	0x22,	  //  "           34
	0x23,   //  #           35
	0x24,   //  $           36
	0x25,   //  %           37
	0x26,   //  &           38
	0x27,   //  '           39
	0x28,   //  (           40
	0x29,   //  )           41
	0x2A,   //  *           42
	0x2B,   //  +           43
	0x2C,   //  ,           44
	0x2D,   //  -           45
	0x2E,   //  .           46
	0x2F,   //  /           47

  0x30,		//  0           48
	0x31,	  //  1           49
	0x32,	  //  2           50
	0x33,   //  3           51
	0x34,   //  4           52
	0x35,   //  5           53
	0x36,   //  6           54
	0x37,   //  7           55
	0x38,   //  8           56
	0x39,   //  9           57
	0x3A,   //  :           58
	0x3B,   //  ;           59
	0x3C,   //  <           60
	0x3D,   //  =           61
	0x3E,   //  >           62
	0x3F,   //  ?           63
  
};

int buttonPin = 9;  // Set a button to any pin

//Screen Resolution
int resX = 1024;
int resY = 768; 

///////////////// Serial Communications Section
String inputString = "";         // a String to hold incoming data
bool stringComplete = false;  // whether the string is complete


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

uint8_t f_bit;
uint8_t s_bit;
uint8_t t_bit;

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

    Serial.println(inputString);
    
    str = strtok(buff,delim);  
    f_bit = strtoul((const char*)str, NULL,16);

    str = strtok(NULL,delim);
    s_bit = strtoul((const char*)str, NULL,16);

    str = strtok(NULL,delim);
    t_bit = strtoul((const char*)str, NULL,16);

    Serial.print("f_bit: ");
    Serial.print(f_bit);
    // Serial.print(" ");
    // Serial.print("s_bit: ");
    // Serial.print(s_bit);
    Serial.print(" ");
    Serial.print("t_bit: ");
    Serial.println(t_bit);
    

    /*
      f_bit - keyup/keydown
      s_bit - modifier
      t_bit - keycode
    */

    if(f_bit==0x00){
      //key down
      //s_bit

      switch(t_bit){
        case 0x1e:
          Keyboard.write('1');
          break;
        case 0x1f:
          Keyboard.write('2');
          break;
      }

      //Keyboard.press(Keyboard.convertMegaToLeonardKeyMap(s_bit));
    }else{
      // //key up
      //   switch(t_bit){
      //   case 0x1e:
      //     Keyboard.release('1');
      //     break;
      //   case 0x1f:
      //     Keyboard.release('2');
      //     break;
      // }
      //Keyboard.release(Keyboard.convertMegaToLeonardKeyMap(s_bit));
    }



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





///////////////////////////////////// Test input


// void setup()
// {
//   Serial.begin(9600);
//   while (!Serial);

//   for (int cnt = 0; cnt <= 21; cnt++)
//   {
//     pinMode(cnt, INPUT_PULLUP);
//   }z
// }

// void loop()
// {
//   for (int cnt = 0; cnt <= 21; cnt++)
//   {
//     Serial.print("Pin "); 
//     Serial.print(String(cnt)); 
//     Serial.print(" : ");
    
//     Serial.println(digitalRead(cnt));
//     delay(100);
//   }

//   delay(5000);
// }