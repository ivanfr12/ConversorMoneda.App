package com.aluracursos.conversorDeMonedas;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Clase para representar un registro de conversión
public class ConversionRecord {
    private String monedaOrigen;  // Código de moneda de origen
    private String monedaDestino; // Código de moneda de destino
    private double monto;         // Monto original a convertir
    private double resultado;     // Resultado de la conversión
    private double tasa;          // Tasa utilizada en la conversión
    private LocalDateTime timestamp; // Marca de tiempo de la conversión

    // Constructor para inicializar un registro de conversión
    public ConversionRecord(String monedaOrigen, String monedaDestino, double monto, double resultado, double tasa, LocalDateTime timestamp) {
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
        this.monto = monto;
        this.resultado = resultado;
        this.tasa = tasa;
        this.timestamp = timestamp;
    }

    // Representación en String del registro con marca de tiempo formateada
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("Fecha: %s | %s -> %s | Monto: %.2f, Tasa: %.4f, Resultado: %.2f",
                timestamp.format(formatter), monedaOrigen, monedaDestino, monto, tasa, resultado);
    }
}
