# S3.02_Patterns_2_N2
# Aplicación Observador Bursátil

Esta aplicación demuestra el patrón de diseño Observer utilizando un sistema de corredor de bolsa que notifica a las agencias bursátiles los cambios en los precios de las acciones.

## Características

- Implementación del patrón Observador**: El agente de bolsa (observable) notifica a las agencias bursátiles (observadores) los cambios en el mercado de valores.
- Datos bursátiles en tiempo real**: Conéctese a la API de Alpha Vantage para obtener los precios de las acciones en tiempo real.
- Modo Demo**: Prueba la aplicación con datos simulados sin clave API.

## Cómo Ejecutar

1. Asegúrese de que tiene instalado Java 8 o superior
2. Clona este repositorio
3. Construye el proyecto usando Gradle:
   ```
   ./gradlew build
   ```
4. Ejecuta la aplicación:
   ```
   ./gradlew run
   ```

## Modos

### Modo Demo
Utiliza datos simulados para demostrar el patrón Observador. No requiere clave API.

### Modo Tiempo Real
Se conecta a la API de Alpha Vantage para obtener precios de acciones en tiempo real. Requiere:
- Una clave API de Alpha Vantage (obtenga la clave gratis en https://www.alphavantage.co/support/#api-key)
- Conexión a Internet
- Símbolos bursátiles a monitorizar (por ejemplo, AAPL, MSFT, GOOGL)

## Diseño

Esta aplicación implementa el patrón Observador:
- **StockBroker (Observable)**: Mantiene una lista de observadores y les notifica los cambios en las acciones
- **StockMarketAgency (Observador)**: Recibe actualizaciones del broker y proporciona recomendaciones
- Servicio de datos bursátiles**: Obtiene datos en tiempo real de la API de Alpha Vantage



## Límites de la API

El nivel gratuito de Alpha Vantage tiene los siguientes límites:
- 5 solicitudes de API por minuto
- 500 peticiones al día

La aplicación está diseñada para respetar estos límites añadiendo retardos entre las llamadas a la API. 
