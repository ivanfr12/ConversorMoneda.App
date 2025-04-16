package com.aluracursos.conversorDeMonedas;
// Importar biblioteca Gson para manejar Json
import com.google.gson.Gson;


import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConversorApp {
    public static void main(String[] args) {
        // Crea un objeto Scanner para leer la entrada del usuario
        Scanner lectura = new Scanner(System.in);

        Clientehttp clienteHttp = new Clientehttp(); // Crea la instancia de Clientehttp
        // Crea una instancia de Gson para convertir JSON a objetos Java
        Gson gson = new Gson();

        // Lista para almacenar el historial de conversiones
        List<ConversionRecord> historial = new ArrayList<>();

        // variable para controlar el ciclo de menu
        boolean salir = false;

        // bucle principal para ejecutar las opciones del usuario
        while (!salir) {
            System.out.println("******************************************");

            System.out.println("\nSea Bienvenido/a al Comversor de Monedas");
            System.out.println("1) Dólar =>> Peso Argentino");
            System.out.println("2) Peso Argentino =>> Dólar");
            System.out.println("3) Dólar =>> Real Brasileño");
            System.out.println("4) Real Brasileño =>> Dólar");
            System.out.println("5) Dólar =>> Peso Colombiano");
            System.out.println("6) Peso Colombiano =>> Dólar");
            System.out.println("7) Conversión personalizada");
            System.out.println("8) Ver Historial de Conversiones");
            System.out.println("9) Salir");
            System.out.println("Elija una opción válida: ");

            System.out.println("******************************************");
            // Lee la opcion del usuario
            String opcion = lectura.nextLine();

            // almacenar la moneda de origen
            String monedaOrigen = "";
            // Almacena la moneda de destino
            String monedaDestino = "";

            // Asignar las monedas de origen y destino según la opcion seleccionada
            switch (opcion) {
                case "1":
                    monedaOrigen = "USD";
                    monedaDestino = "ARS";
                    break;
                case "2":
                    monedaOrigen = "ARS";
                    monedaDestino = "USD";
                    break;
                case "3":
                    monedaOrigen = "USD";
                    monedaDestino = "BRL";
                    break;
                case "4":
                    monedaOrigen = "BRL";
                    monedaDestino = "USD";
                    break;
                case "5":
                    monedaOrigen = "USD";
                    monedaDestino = "COP";
                    break;
                case "6":
                    monedaOrigen = "COP";
                    monedaDestino = "USD";
                    break;
                case "7":
                    // Conversion personalizada: se solicitan los codigos de moneda al usuario
                    System.out.println("Ingrese la moneda de origen (ej. USD): ");
                    monedaOrigen = lectura.nextLine().toUpperCase();
                    System.out.println("Ingrese la moneda de destino (ej. EUR)");
                    monedaDestino = lectura.nextLine().toUpperCase();
                    break;
                case "8":
                    // Mostrar historial de conversiones
                    if (historial.isEmpty()){
                        System.out.println("El historial está vacío.");
                    }else {
                        System.out.println("\n--- Historial de Conversiones ---");
                        for (ConversionRecord registro : historial){
                            System.out.println(registro);
                        }
                    }
                    continue; // Regresa al menú sin hacer conversion
                case "9":
                    // Salir de la aplicacion
                    System.out.println("Gracias por usar el conversor de monedas.");
                    salir=true;
                    continue;
                default:
                    // Mensaje de error para opción inválida
                    System.out.println("Opcion invalida. Por favor, intente nuevamente.");
                    continue; // Vuelve al inicio del bucle
            }
            // Solicita al usuario el monto a converitr
            System.out.println("Ingrese el monto a convertir: ");
            double monto;

            try {
                // Intenta convertir la entrada a un numero decimal
                monto = Double.parseDouble(lectura.nextLine());
            } catch (NumberFormatException e) {
                // Mostrar mensaje de error en caso de la entrada no sea un numero
                System.out.println("Monto inválido. Intente nuevamente.");
                continue; // Vuelve al inicio
            }

            // Realizar la solicitud Http para obtener la tasa de cambio
            HttpResponse<String> respuesta = clienteHttp.obtenerTasaCambio(monedaOrigen);

            if (respuesta != null && respuesta.statusCode() == 200) { // Verifica que la respuesta no sea nula y que el código de estado sea 200 (OK)
                TasaCambioResponse tasaCambioResponse = gson.fromJson(respuesta.body(), TasaCambioResponse.class); // Convierte la respuesta JSON a un objeto Java

                if ("success".equalsIgnoreCase(tasaCambioResponse.getResult())) { // Verifica que la respuesta indique éxito
                    Double tasa = tasaCambioResponse.getConversionRates().get(monedaDestino); // Obtiene la tasa de cambio para la moneda de destino
                    if (tasa != null) { // Verifica que la tasa no sea nula
                        double resultado = monto * tasa; // Calcula el monto convertido
                        // Muestra la tasa de cambio y el resultado de la conversión
                        System.out.printf("Tasa de cambio: 1 %s = %.4f %s%n", monedaOrigen, tasa, monedaDestino);
                        System.out.printf("Resultado: %.2f %s = %.2f %s%n", monto, monedaOrigen, resultado, monedaDestino);

                        // Crear y almacenar el registro de la conversion con marca de tiempo actual
                        ConversionRecord registro = new ConversionRecord(monedaOrigen, monedaDestino,
                                monto, resultado,
                                tasa, LocalDateTime.now());
                        historial.add(registro);

                    } else {
                        System.out.println("Moneda de destino no encontrada."); // Mensaje de error si la moneda de destino no se encuentra
                    }
                } else {
                    System.out.println("Error en la respuesta de la API."); // Mensaje de error si la API indica un fallo
                }
            } else {
                System.out.println("Error al obtener la tasa de cambio."); // Mensaje de error si la respuesta HTTP es nula o no es exitosa
            }
        }

        lectura.close(); // Cierra el objeto Scanner
    }
}

