#include <Arduino.h>
#include <Keyboard.h>
#include <Mouse.h>
#include <string.h>
#include <SoftwareSerial.h>


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

void loop() {   

  // if (digitalRead(buttonPin) == 0)  // if the button goes low
  // {
  //   // //Keyboard.write('z');  // send a 'z' to the computer via Keyboard HID
  //   // delay(1000);  // delay so there aren't a kajillion z's
  //   // Mouse.move(resX/2,resY/2,0);
  //   // Mouse.press(MOUSE_LEFT);
  //   // delay(1000);  // delay so there aren't a kajillion z's
  //   // Mouse.move(resX/3,resY/3,0);
  //   // Mouse.release(MOUSE_LEFT);
  //   // delay(25000);  // delay so there aren't a kajillion z's
  // }
  if (stringComplete) {
    Serial.print(inputString);
    // clear the string:
    inputString = "";
    stringComplete = false;
  }
  
  if(mySerial.available()){
    while (mySerial.available()) {
      // get the new byte:
      char inChar = (char)mySerial.read();
      // add it to the inputString:
      inputString += inChar;
      // if the incoming character is a newline, set a flag so the main loop can
      // do something about it:
      if (inChar == '\n') {
        stringComplete = true;
      }
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