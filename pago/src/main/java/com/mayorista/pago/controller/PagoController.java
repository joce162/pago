package com.mayorista.pago.controller;

import com.mayorista.pago.model.Pago;
import com.mayorista.pago.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private PagoService service;

    @GetMapping
    public List<Pago> obtenerTodos() {
        return service.listarTodos();
    }
// profe la quiero
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Pago pago) {
        try {
            Pago nuevoPago = service.registrarPago(pago);
            return new ResponseEntity<>(nuevoPago, HttpStatus.CREATED);
        } catch (Exception e) {

            return new ResponseEntity<>("Error al procesar el pago: " + e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }
}