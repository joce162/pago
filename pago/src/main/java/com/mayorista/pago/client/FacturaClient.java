package com.mayorista.pago.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class FacturaClient {

    private final WebClient webClient;

    public FacturaClient() {

        this.webClient = WebClient.create("http://localhost:8083");
    }

    public void notificarPagoAFactura(Long idFactura) {
        try {
            this.webClient.put()
                    .uri("/api/v1/facturas/" + idFactura + "/pagar")
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
            System.out.println("Capa Client: Factura " + idFactura + " notificada con éxito.");
        } catch (Exception ex) {
            System.out.println("Capa Client: Error al conectar con Facturas -> " + ex.getMessage());
        }
    }
}