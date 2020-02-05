#include <Adafruit_Sensor.h>

#include <DHT.h>
#include <DHT_U.h>

#define DHTPIN 6    // Changer le pin sur lequel est branché le DHT

int switchState = 0;
char letter = "a";

#define DHTTYPE DHT22       // DHT 22  (AM2302)
DHT dht(DHTPIN, DHTTYPE); 

void setup() {
  Serial.begin(1000000);
  pinMode(3, OUTPUT);
  pinMode(4, OUTPUT);
  pinMode(5, OUTPUT);
  pinMode(7, OUTPUT);
  pinMode(2, INPUT);

  dht.begin();  
  }

void loop() {

 int input = 0;
  
 switchState = digitalRead(2);
  
 // Lecture de la température en Celcius
 float t = dht.readTemperature();
  
 if(switchState == LOW){
    digitalWrite(3, LOW);
    digitalWrite(4, LOW);
    digitalWrite(5, LOW);
    digitalWrite(7, LOW);
    delay(1000);
    digitalWrite(3, HIGH);
    Serial.println("Lumiere Verte");
    delay(1000);
    
  }
  else {
    digitalWrite(3, LOW);
    digitalWrite(5, HIGH);
    Serial.println("Lumiere Rouge");
    delay(1000);
  }
  while (Serial.available() > 0)
   {
    input = Serial.read();

    if (input == 121) {
      digitalWrite(4, HIGH);
      digitalWrite(5, LOW);
      Serial.println("Lumiere Blanche");
      delay(15000);
    }
    else if(input == 110){
      digitalWrite(7, HIGH);
      digitalWrite(4, LOW);
      Serial.println("Lumiere Jaune");
      delay(2000);
    }  
  }
  
  switch(letter) {
    case 'a' :
      Serial.println(t);
      letter = "b";
      break;
    default:
      letter = "b";
      break;
 }
}
