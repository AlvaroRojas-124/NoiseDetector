# **Noise Detector**
*Noise detector es un instrumento que nace, bajo la necesidad de medir los niveles de contaminación acústica en algún lugar en específico.*

El proyecto está dirigido para los lugares que necesitan poca contaminación acústica ó, en su defecto mucho silencio, tales como:
+ Bibliotecas
+ Centros de estudio 
+ Edificios 
+ Hospitales

Socialmente hablando, el vivir de la gente hoy en dia se orienta en edificios de gran envergadura donde viven mucha gente en ellos, y por reglas de convivencia se debe tener en consideración el volumen después de determinadas horas.

## Implementación del Proyecto

Para esta implementación se programará un dispositivo **NodeMCU** el cuál nos permitirá conectarnos inalámbricamente (WiFi) a nuestro dispositivo móvil, de manera adicional a través de un sensor de sonidos **FC-109**, que posee una capacitancia pequeña, detectará intervalos de DB desde 0 a 80 Decibeles, lo cual nos permite hacer pequeñas experimentaciones en lugares pequeños, creemos que con un dispositivo más avanzado como un micrófono podemos experimentar con parámetros más altos para evolucionar el proyecto.

Por otro lado, la aplicación **Android** presentara distintos parámetros, como los niveles de decibeles que recepciona del sensor, los cuales podremos ver a través de una interfaz gráfica. Esto hará que en nuestro teléfono podamos ver 
los niveles de contaminación acústica que hay en cada zona en la que se esté presente. 

De manera física, el dispositivo tendrá la capacidad de representar esto a través de **2 diodos LED**, que nos dirá de manera intuitiva, si hay contaminación acústica en el lugar. Para esto el dispositivo está configurado para que cuando este entre los rangos de 40 a 60 decibeles, se encienda el LED verde, el cuál indica que se encuentra en un ambiente grato sin contaminación excesiva. Y si se supera este rango se encenderá el LED rojo, el cuál indica que hay contaminación acústica en dicho lugar. *Todo esto se ve reflejado en la aplicación Android en paralelo.*

## Lista de Materiales
### **NodeMCU V2 ESP8266 WiFi**
#### Especificaciones Tecnicas
* Voltaje de Alimentación: 5V   <img src="images/nodeMCU_1.png" align="right">
* Voltaje de Entradas/Salidas: 3.3V DC (No usar 5V)
* Placa: NodeMCU v2 (Amica)
* Chip conversor USB-serial: CP2102
*	SoM: ESP-12E (Ai-Thinker)
*	SoC: ESP8266 (Espressif)
*	CPU: Tensilica Xtensa LX3 (32 bit)
*	Frecuencia de Reloj: 80MHz/160MHz
*	Instruction RAM: 32KB
*	Data RAM: 96KB
*	Memoria Flash Externa: 4MB
*	Pines Digitales GPIO: 17 (4 pueden configurarse como PWM a 3.3V)
*	Pin Analógico ADC: 1 (0-1V)
*	Puerto Serial UART: 2
*	Certificación FCC
*	Antena en PCB
*	802.11 b/g/n
*	Wi-Fi Direct (P2P), soft-AP
*	Stack de Protocolo TCP/IP integrado
*	PLLs, reguladores, DCXO y manejo de poder integrados
*	Potencia de salida de +19.5dBm en modo 802.11b
*	Corriente de fuga menor a 10uA
*	STBC, 1×1 MIMO, 2×1 MIMO
*	A-MPDU & A-MSDU aggregation & 0.4ms guard interval
*	Wake up and transmit packets in < 2ms
*	Consumo de potencia Standby < 1.0mW (DTIM3)
*	Pulsador RESET y FLASH
*	Leds indicadores: 2
*	Dimensiones: 49*26*12 mm
*	Peso: 9 gramos



## Esquema del Circuito
![Esquema del Circuito](images/Esquema_NoiseDetector.png)
