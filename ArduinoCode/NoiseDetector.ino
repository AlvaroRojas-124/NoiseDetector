const char *ssid = "IoTest"; 
const char *password = "momoring123"; 
const char* mqtt_server = "68.183.119.177"; // Este es el address en mqtt dash 
const char* keyDevice = ""; 
int LedV = 4;
int LedR = 5;
const int sensorPIN = A0;
unsigned int peakToPeak = 0;         // peak-to-peak level    
// size of the window
const int inputWindow = 100;
// placeholder for a single measurement
unsigned int inputSample;
  
#include <ESP8266WiFi.h> 
#include <PubSubClient.h> 

WiFiClient espClient; 
PubSubClient client(espClient); 
 
void setup_wifi() { 
  delay(10); 
  WiFi.begin(ssid, password); 
  while (WiFi.status() != WL_CONNECTED)  
  { 
    Serial.print("."); 
    delay(500); 
  } 
  Serial.println("OK"); 
  } 
//void callback(char* topic, byte* payload, unsigned int length) { 
//} 
 void reconnect() { 
   if (!client.connected()) { 
    if (client.connect(keyDevice,"","")){//Servidor broker es el usuario y prueba12 la contraseña en mqtt dash 
      Serial.println("connected"); 
      client.subscribe("nd/#"); // Topico al momento de crear el boton 
    } else { 
      delay(5000); 
    } 
  } 
} 
void setup() { 
  Serial.begin(9600); 
  Serial.println("iniciando"); 
  setup_wifi(); 
  client.setServer(mqtt_server, 1883); 
  pinMode(sensorPIN, INPUT); 
  pinMode(LedV, OUTPUT);
  pinMode(LedR, OUTPUT); 
  //client.setCallback(callback);   
 } 
void loop() 
{ 
  if (!client.connected()) 
  { 
   reconnect(); 
  } 
  client.loop(); 

  // two variables for minimum and maximum values in window
  unsigned int inputMax = 0;
  unsigned int inputMin = 1024;

  // loop for the window
  for (unsigned int i = 0; i < inputWindow; i++) {
    // read in a single value
    inputSample = analogRead(sensorPIN);
    // get the minimum and maximum value
    inputMin = min(inputMin, inputSample);
    inputMax = max(inputMax, inputSample);
  }

  // send the values on serial
  Serial.print("Min: ");
  Serial.print(inputMin);
  Serial.print("  Max: ");
  Serial.print(inputMax);
  Serial.print("  Diff: ");
  Serial.print(inputMax - inputMin);
  Serial.println();
  peakToPeak = inputMax - inputMin;
  double volts = ((peakToPeak * 3.3) / 1024)*10;
  double volts_db = 20*log10(volts/0.001259);
  Serial.println(volts_db);
   if (volts_db <= 0){
      volts_db = 0;
      Serial.println(volts_db);
   }else{
      Serial.println(volts_db);
   }

   if (volts_db >= 40.0 and volts_db < 70.0){
    
      digitalWrite(LedV, HIGH);
      digitalWrite(LedR, LOW);   
   }else{
      digitalWrite(LedV, LOW);
      digitalWrite(LedR, HIGH);   
   }
  
  
  client.publish("nd/sensor",String(volts_db).c_str());
}
