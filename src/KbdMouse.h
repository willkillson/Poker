#ifndef KBDMOUSE_H
#define KBDMOUSE_H

#include <string.h>
#include <Arduino.h>
#include <SoftwareSerial.h>
#include <Keyboard.h>
#include <Mouse.h>

#include "SerialDriver.h"

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



class KbdMouse {
  public:
	  const static uint8_t DELIMITER = 0x7e;
    const static uint8_t EVENT_TYPE_KB_DOWN = 0x00;
    const static uint8_t EVENT_TYPE_KB_UP = 0x01;
    const static uint8_t EVENT_TYPE_MOUSE_DOWN = 0x02;
    const static uint8_t EVENT_TYPE_MOUSE_UP = 0x03;
    const static uint8_t EVENT_TYPE_MOUSE_MOVE = 0x04;

    const static uint8_t MOUSE_LEFT_BUTTON = 0xF1;
    const static uint8_t MOUSE_RIGHT_BUTTON = 0xF2;
    const static uint8_t MOUSE_MIDDLE_BUTTON = 0xF3;

    KbdMouse(SerialDriver* sd);
		void update();

	private:
		//Screen Resolution
		int resX = 1024;
		int resY = 768; 

		uint8_t f_bit;
		uint8_t s_bit;
		uint8_t t_bit;

		SerialDriver* _sd;

		void processOutput();
		
		void performKeyboardModifiers(uint8_t s_bit);

		void performPressKeyboard(uint8_t t_bit);
		void performReleaseKeyboard(uint8_t t_bit);

		void performMouseMove(uint8_t dx, uint8_t dy);
		void performPressMouse(uint8_t t_bit);
		void performReleaseMouse(uint8_t t_bit);
};

#endif