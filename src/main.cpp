#include <Arduino.h>
#include <string.h>
#include "KbdMouse.h"
#include "SerialDriver.h"

SerialDriver sd;
KbdMouse kbdMouse(&sd);

void setup() {
  //For Debugging
  Serial.begin( 14400 );
}

void loop() {   
  kbdMouse.update();
}
