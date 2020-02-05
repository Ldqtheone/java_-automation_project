//#include <Adafruit_Sensor.h>

#include "DHT.h"
//#include <DHT_U.h>


int button1 = 0;
int button2 = 0;
int button3 = 0;
int button4 = 0;
int bulb1 , bulb2, bulb3 = 0;
int heater1, heater2 = 0;
int bool1, bool2, bool3, bool4 = 0;
int AnalR, AnalG, AnalB = 0;
bool tempCheck = false;

char letter = ' ';
char temperature = 'a';

#define DHTPIN 9    // Changer le pin sur lequel est branché le DHT
#define DHTTYPE DHT22       // DHT 22  (AM2302)
DHT dht(DHTPIN, DHTTYPE);

void setup() {
  Serial.begin(100000);
  pinMode(3, OUTPUT);
  pinMode(5, OUTPUT);
  pinMode(6, OUTPUT);
  pinMode(12, OUTPUT);
  pinMode(13, OUTPUT);
  pinMode(A0, OUTPUT);
  pinMode(A1, OUTPUT);
  pinMode(A2, OUTPUT);
  pinMode(2, INPUT);
  pinMode(4, INPUT);
  pinMode(7, INPUT);
  pinMode(8, INPUT);

  dht.begin(); 
}

boolean delay_without_delaying(unsigned long time) {
  // return false if we're still "delaying", true if time ms has passed.
  // this should look a lot like "blink without delay"
  static unsigned long previousmillis = 0;
  unsigned long currentmillis = millis();
  if (currentmillis - previousmillis >= time) {
    previousmillis = currentmillis;
    return true;
  }
  return false;
}

boolean delay_without_delaying(unsigned long &since, unsigned long time) {
  // return false if we're still "delaying", true if time ms has passed.
  // this should look a lot like "blink without delay"
  unsigned long currentmillis = millis();
  if (currentmillis - since >= time) {
    since = currentmillis;
    return true;
  }
  return false;
}

// Lecture de la température en Celcius
void getTemp(int input) {
  float t = dht.readTemperature();
  switch(input){
    case 97:
      Serial.println(t);
      break;
    case 98 :
      digitalWrite(12, HIGH);
      digitalWrite(13, LOW);
      break;
    case 99 :
      digitalWrite(13, HIGH);
      digitalWrite(12, LOW);
      break;
    case 100 :
      digitalWrite(12, LOW);
      digitalWrite(13, LOW);
      break;
  }
}
  
void bulbs(int input){
 if (Serial.available() >= 0){

  switch(input){
    case 105 :
      digitalWrite(3, HIGH);
      break;
    case 106 :
      digitalWrite(3, LOW);
      break;
    case 107 :
      digitalWrite(5, HIGH);
      break;
    case 108 :
      digitalWrite(5, LOW);
      break;
    case 109 :
      digitalWrite(6, HIGH);
      break;
    case 110 :
      digitalWrite(6, LOW);
      break;
    case 113 :
      digitalWrite(A0, 255);
      digitalWrite(A1, 0);
      digitalWrite(A2, 0);
      break;
    case 114 :
      digitalWrite(A0, 0);
      digitalWrite(A1, 0);
      digitalWrite(A2, 255);
      break;
    case 115 :
      digitalWrite(A0, 0);
      digitalWrite(A1, 255);
      digitalWrite(A2, 0);
      break;
    case 117 :
      digitalWrite(A0, 170);
      digitalWrite(A1, 170);
      digitalWrite(A2, 170);
      break;
    case 118 :
      digitalWrite(A0, 255);
      digitalWrite(A1, 99);
      digitalWrite(A2, 71);
      break;
    case 112 :
      digitalWrite(A0, 0);
      digitalWrite(A1, 0);
      digitalWrite(A2, 0);
      break;
    default:
      break;
  }
 }
}

void loop() {
  
 int input = Serial.read();
 

 button1 = digitalRead(2);
 button2 = digitalRead(4);
 button3 = digitalRead(7);
 button4 = digitalRead(8);

 bulb1 = digitalRead(3);
 bulb2 = digitalRead(5);
 bulb3 = digitalRead(6);
 heater1 = digitalRead(12);
 heater2 = digitalRead(13);
 AnalR = digitalRead(A0);
 AnalG = digitalRead(A1);
 AnalB = digitalRead(A2);

 if(105<=input && input<=118){
   bulbs(input);
 }else if (97<=input && input<=100){
  getTemp(input);
 }

  // static unsigned long atime, btime, dtime = 0;

   // Lecture des 4 boutons des lumières
  /* if(button1 == HIGH){
    if(bool1==0){
     if(bulb1 == LOW){
      digitalWrite(3, HIGH);
      letter = 'a';
     }else{
      digitalWrite(3, LOW);
      letter = 'b';
      }
    bool1=1;
   }
 }
 if(button1 == LOW){
  if(bool1 == 1){
    bool1 = 0;
  }
 }
 

 if(button2 == HIGH){
    if(bool2==0){
     if(bulb2 == LOW){
      digitalWrite(5, HIGH);
      letter = 'c';
     }else{
      digitalWrite(5, LOW);
      letter = 'd';
      }
    bool2=1;
   }
 }
 if(button2 == LOW){
  if(bool2 == 1){
    bool2 = 0;
  }
 }

 if(button3 == HIGH){
    if(bool3==0){
     if(bulb3 == LOW){
      digitalWrite(6, HIGH);
      letter = 'e';
     }else{
      digitalWrite(6, LOW);
      letter = 'f';
      }
    bool3=1;
   }
 }
 if(button3 == LOW){
  if(bool3 == 1){
    bool3 = 0;
  }
 }

 if(button4 == HIGH){
    if(bool4==0){
     if(AnalR == LOW && AnalG == LOW && AnalB == LOW){
      digitalWrite(A0, 212);
      digitalWrite(A1, 115);
      digitalWrite(A2, 212);
      letter = 'g';
     }else{
      digitalWrite(A0, 0);
      digitalWrite(A1, 0);
      digitalWrite(A2, 0);
      letter = 'h';
      }
    bool4 = 1;
   }
 }
 if(button4 == LOW){
  if(bool4 == 1){
    bool4 = 0;
  }
 } */
}
