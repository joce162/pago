package com.mayorista.pago.service;

import com.mayorista.pago.client.FacturaClient;
import com.mayorista.pago.model.Pago;
import com.mayorista.pago.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private FacturaClient facturaClient;

    public Pago procesarPago(Pago pago) {
        pago.setFechaPago(LocalDateTime.now());
        Pago pagoGuardado = pagoRepository.save(pago);

        facturaClient.notificarPagoAFactura(pagoGuardado.getIdFactura());

        return pagoGuardado;
    }
}