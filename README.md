# Conversor de Monedas

Conversor de Monedas es una aplicación en Java que permite convertir diversas monedas utilizando la API [ExchangeRate-API](https://www.exchangerate-api.com/).
La aplicación cuenta con un menú interactivo, historial de conversiones (con registros de fecha y hora) y la posibilidad de realizar conversiones personalizadas entre múltiples divisas.

## Características

- **Conversión de Monedas Fijas y Personalizadas:**  
  Se pueden convertir monedas predefinidas (como Dólar a Peso Argentino, Dólar a Real Brasileño, etc.) y también se permite la conversión ingresando los códigos de moneda deseados.

- **Historial de Conversiones:**  
  Se guarda un registro de cada conversión exitosa, incluyendo la moneda de origen, la moneda de destino, el monto, la tasa utilizada y un sello de tiempo.

- **Registros con Marca de Tiempo:**  
  Cada conversión se registra utilizando `java.time`, mostrando la fecha y hora exacta en que se realizó.

## Pre-requisitos

- **Java 11** o superior (la aplicación utiliza `HttpClient` y otras características modernas de Java).
- **Gson:** Biblioteca de Google para parsear JSON. Puedes agregarla como dependencia en tu proyecto (por ejemplo, mediante Maven o importando el JAR).

## Instalación

1. **Clona el repositorio:**
   ```bash
   git clone https://github.com/ivanfr12/ConversorMoneda.App
   
Importa el proyecto en tu IDE favorito (IntelliJ IDEA, Eclipse, etc.).

Configura las dependencias:

Si usas Maven, asegúrate de agregar la dependencia de Gson en tu pom.xml:
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.8.9</version>
</dependency>
Alternativamente, puedes descargar el JAR de Gson y agregarlo manualmente al classpath del proyecto.

Uso
Ejecuta la clase ConversorApp.java desde tu IDE o compílala y ejecútala desde la terminal:
javac -d bin src/com/aluracursos/conversorDeMonedas/*.java
java -cp bin com.aluracursos.conversorDeMonedas.ConversorApp

Sigue las instrucciones mostradas en el menú interactivo.

Selecciona la opción deseada para realizar conversiones o consulta el historial.

Ingresa el monto a convertir y, en el caso de conversión personalizada, ingresa el código de las monedas.
conversorDeMonedas/
├── src/
│   └── com/
│       └── aluracursos/
│           └── conversorDeMonedas/
│               ├── Clientehttp.java         # Maneja las solicitudes HTTP a la API.
│               ├── ConversorApp.java          # Clase principal con el menú interactivo.
│               ├── TasaCambioResponse.java    # Modelo para mapear la respuesta JSON de la API.
│               └── ConversionRecord.java      # Clase para registrar cada conversión con su marca de tiempo.
├── README.md                               # Documentación del proyecto.
└── LICENSE                                 # Archivo de licencia (opcional).

API
Se utiliza la API ExchangeRate-API para obtener las tasas de cambio. En la clase Clientehttp.java verás la siguiente línea, donde debes colocar tu API Key:
String apiKey = "12312312ssadxxx"; // Reemplaza con tu propia API key si es necesario.

Contribuciones
¡Las contribuciones son bienvenidas!
Si encuentras un error o tienes una idea para mejorar el proyecto, abre un issue o envía un pull request.
Por favor, sigue las buenas prácticas de GitHub en cuanto a comentarios y revisiones de código.

Licencia
Este proyecto está licenciado bajo la MIT License.

Autor
Ivan Rodriguez

