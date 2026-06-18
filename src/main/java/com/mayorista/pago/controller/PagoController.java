package com.mayorista.pago.controller;

import com.mayorista.pago.model.Pago;
import com.mayorista.pago.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @PostMapping("/procesar")
    public ResponseEntity<Pago> registrarPago(@RequestBody Pago pago) {
        Pago nuevoPago = pagoService.procesarPago(pago);
        return new ResponseEntity<>(nuevoPago, HttpStatus.CREATED);
    }
}