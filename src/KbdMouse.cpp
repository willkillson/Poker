#include "KbdMouse.h"

/*
  Not all pins on the Leonardo support change interrupts, 
  so only the following can be used for RX: 
  8, 9, 10, 11, 14 (MISO), 15 (SCK), 16 (MOSI).
*/
KbdMouse::KbdMouse(SerialDriver* sd)
{
	_sd = sd;
}

void KbdMouse::performKeyboardModifiers(uint8_t s_bit){
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
}

void KbdMouse::performPressKeyboard(uint8_t t_bit){
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

		case 98:
			Keyboard.press(KEY_KEYPAD_0);
			break;
		case 89:
			Keyboard.press(KEY_KEYPAD_1);
			break;
		case 90:
			Keyboard.press(KEY_KEYPAD_2);
			break;
		case 91:
			Keyboard.press(KEY_KEYPAD_3);
			break;
		case 92:
			Keyboard.press(KEY_KEYPAD_4);
			break;
		case 93:
			Keyboard.press(KEY_KEYPAD_5);
			break;
		case 94:
			Keyboard.press(KEY_KEYPAD_6);
			break;
		case 95:
			Keyboard.press(KEY_KEYPAD_7);
			break;
		case 96:
			Keyboard.press(KEY_KEYPAD_8);
			break;
		case 97:
			Keyboard.press(KEY_KEYPAD_9);
			break;
		case 99:
			Keyboard.press(KEY_KEYPAD_DECIMAL);
			break;
		case 88:
			Keyboard.press(KEY_KEYPAD_ENTER);
			break;
		case 87:
			Keyboard.press(KEY_KEYPAD_PLUS);
			break;
		case 86:
			Keyboard.press(KEY_KEYPAD_MINUS);
			break;
		case 85:
			Keyboard.press(KEY_KEYPAD_MULTIPLY);
			break;
		case 84:
			Keyboard.press(KEY_KEYPAD_DIVIDE);
			break;
		case 83:
			Keyboard.press(KEY_KEYPAD_NUMLOCK);
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

void KbdMouse::performReleaseKeyboard(uint8_t t_bit){
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
		case 98:
			Keyboard.release(KEY_KEYPAD_0);
			break;
		case 89:
			Keyboard.release(KEY_KEYPAD_1);
			break;
		case 90:
			Keyboard.release(KEY_KEYPAD_2);
			break;
		case 91:
			Keyboard.release(KEY_KEYPAD_3);
			break;
		case 92:
			Keyboard.release(KEY_KEYPAD_4);
			break;
		case 93:
			Keyboard.release(KEY_KEYPAD_5);
			break;
		case 94:
			Keyboard.release(KEY_KEYPAD_6);
			break;
		case 95:
			Keyboard.release(KEY_KEYPAD_7);
			break;
		case 96:
			Keyboard.release(KEY_KEYPAD_8);
			break;
		case 97:
			Keyboard.release(KEY_KEYPAD_9);
			break;
		case 99:
			Keyboard.release(KEY_KEYPAD_DECIMAL);
			break;
		case 88:
			Keyboard.release(KEY_KEYPAD_ENTER);
			break;
		case 87:
			Keyboard.release(KEY_KEYPAD_PLUS);
			break;
		case 86:
			Keyboard.release(KEY_KEYPAD_MINUS);
			break;
		case 85:
			Keyboard.release(KEY_KEYPAD_MULTIPLY);
			break;
		case 84:
			Keyboard.release(KEY_KEYPAD_DIVIDE);
			break;
		case 83:
			Keyboard.release(KEY_KEYPAD_NUMLOCK);
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

void KbdMouse::performMouseMove(uint8_t dx, uint8_t dy){
	Mouse.move(dx,dy,0);
}

void KbdMouse::performPressMouse(uint8_t t_bit){
	
	/*

		t_bit mapping
    const static uint8_t MOUSE_LEFT_BUTTON = 0xF1;
    const static uint8_t MOUSE_RIGHT_BUTTON = 0xF2;
    const static uint8_t MOUSE_MIDDLE_BUTTON = 0xF3;

	*/
	switch(t_bit){
		case MOUSE_LEFT_BUTTON:
			Mouse.press(MOUSE_LEFT);
			break;
		case MOUSE_RIGHT_BUTTON:
			Mouse.press(MOUSE_RIGHT);
			break;
		case MOUSE_MIDDLE_BUTTON:
			Mouse.press(MOUSE_MIDDLE);
			break;
	}
}

void KbdMouse::performReleaseMouse(uint8_t t_bit){
	switch(t_bit){
		case MOUSE_LEFT_BUTTON:
			Mouse.release(MOUSE_LEFT);
			break;
		case MOUSE_RIGHT_BUTTON:
			Mouse.release(MOUSE_RIGHT);
			break;
		case MOUSE_MIDDLE_BUTTON:
			Mouse.release(MOUSE_MIDDLE);
			break;
	}
}

void KbdMouse::processOutput(){
      
    char* str;
    char buff[20];
    strcpy(buff,_sd->inputString.c_str());
    const char delim[2] = " ";

    //Serial.println(inputString);
    
    str = strtok(buff,delim);  
    f_bit = strtoul((const char*)str, NULL,16);

    str = strtok(NULL,delim);
    s_bit = strtoul((const char*)str, NULL,16);

    str = strtok(NULL,delim);
    t_bit = strtoul((const char*)str, NULL,16);

    /*
      f_bit - keyup/keydown or mouseup/mousedown/mousemove
						0x00 -> performPressKeyboard
						0x01 -> performReleaseKeyboard
						0x02 -> performPressMouse
						0x03 -> performReleaseMouse
						0x04 -> performMouseMove
      s_bit - modifier
      t_bit - keycode
    */

		switch(f_bit){
			case EVENT_TYPE_KB_DOWN:
				performKeyboardModifiers(s_bit);
				performPressKeyboard(t_bit);
				break;
			case EVENT_TYPE_KB_UP:
				performKeyboardModifiers(s_bit);
				performReleaseKeyboard(t_bit);
				break;
			case EVENT_TYPE_MOUSE_DOWN:
				performPressMouse(t_bit);
				break;
			case EVENT_TYPE_MOUSE_UP:
				performReleaseMouse(t_bit);
				break;
			case EVENT_TYPE_MOUSE_MOVE:
				performMouseMove(s_bit,t_bit);
				break;
		}
    f_bit = 0x00;
    s_bit = 0x00;
    t_bit = 0x00;
}

void KbdMouse::update(){

	if(_sd->mySerial.available()){
		_sd->getSerialInput();
		processOutput();
		_sd->resetState();
	}

}