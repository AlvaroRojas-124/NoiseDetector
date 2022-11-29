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


## Esquema del Circuito
![Esquema del Circuito](images/Esquema_NoiseDetector.png)
