package com.aluracursos.conversorDeMonedas;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Clientehttp {
    private  HttpClient client;  // Cliente para realizar solicitudes HTTP

    public Clientehttp() {
        this.client = HttpClient.newHttpClient();  // Inicializa el cliente HTTP
    }
// Metodo de instancia para realizar la solicitud HTTP
    public HttpResponse<String> hacerSolicitud(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
 // Metodo de instancia para obtener la tasa de cambio
    public HttpResponse<String> obtenerTasaCambio(String monedaOrigen) {
        String apiKey = "141b1de33a1232291e5f6d71"; // API KEY
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + monedaOrigen;
        //Llama al metodo intancia, usando "this" para mayor claridad (aunque es opcional)
        return this.hacerSolicitud(url);
    }
}
