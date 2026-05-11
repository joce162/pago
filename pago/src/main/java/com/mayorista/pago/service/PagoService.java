package com.mayorista.pago.service;

import com.mayorista.pago.model.Pago;
import com.mayorista.pago.repository.PagoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PagoService {

    private static final Logger logger = LoggerFactory.getLogger(PagoService.class);

    @Autowired
    private PagoRepository repository;

    public Pago registrarPago(Pago pago) {
        logger.info("Registrando un nuevo pago para la factura: {}", pago.getId_factura());
        pago.setFecha_pago(LocalDateTime.now());
        return repository.save(pago);
    }

    public List<Pago> listarTodos() {
        logger.info("Obteniendo lista de todos los pagos");
        return repository.findAll();
    }
}