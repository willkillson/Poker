#ifndef SERIALDRIVER_H
#define SERIALDRIVER_H

#include <string.h>
#include <Arduino.h>
#include <SoftwareSerial.h>

class SerialDriver{

  public:
    SerialDriver();
    SoftwareSerial mySerial;
    ///////////////// Serial Communications Section
    String inputString = "";         // a String to hold incoming data
    bool stringComplete = false;  // whether the string is complete
    void getSerialInput();
    void resetState();
    

};

#endif