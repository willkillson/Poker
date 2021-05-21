#include "SerialDriver.h"

SerialDriver::SerialDriver()
  :
    mySerial(10,9)
{

  // reserve 200 bytes for the inputString:
	inputString.reserve(200);
  mySerial.begin( 9600 );

}

void SerialDriver::resetState(){
  inputString = "";
  stringComplete = false;
}

void SerialDriver::getSerialInput(){
  while (mySerial.available()) {
    uint8_t inChar = mySerial.read();
    if (inChar == 0x7e) {
      stringComplete = true;
      break;
    }
    inputString += String(inChar,HEX);
    inputString += " ";
  }
}