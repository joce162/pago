package com.mayorista.pago.service;

import com.mayorista.pago.model.Pago;
import com.mayorista.pago.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    public Pago procesarPago(Pago pago) {
        pago.setFechaPago(LocalDateTime.now());
        Pago pagoGuardado = pagoRepository.save(pago);

        try {
            WebClient.create("http://localhost:8083")
                    .post()
                    .uri("/api/v1/facturas/" + pagoGuardado.getIdFactura() + "/pagar")
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
            System.out.println("Factura actualizada a pagada de forma remota.");
        } catch (Exception ex) {
            System.out.println("Pago registrado localmente pero fallo la conexión con Facturas: " + ex.getMessage());
        }

        return pagoGuardado;
    }
}