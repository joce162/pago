package com.mayorista.pago.service;

import com.mayorista.pago.model.Pago;
import com.mayorista.pago.repository.PagoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PagoService {

    private static final Logger logger = LoggerFactory.getLogger(PagoService.class);

    @Autowired
    private PagoRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public Pago registrarPago(Pago pago) {
        logger.info("Registrando un nuevo pago para la factura: {}", pago.getId_factura());
        pago.setFecha_pago(LocalDateTime.now());

        Pago pagoGuardado = repository.save(pago);


        try {
            String urlFactura = "http://localhost:8080/api/facturas/" + pagoGuardado.getId_factura() + "/pagar";
            logger.info("Enviando aviso de pago al microservicio de Factura en la URL: {}", urlFactura);
            restTemplate.postForObject(urlFactura, null, String.class);
            logger.info("¡Estado de factura actualizado exitosamente en el microservicio destino!");
        } catch (Exception e) {
            logger.error("No se pudo conectar con el microservicio de Factura: {}", e.getMessage());
        }

        return pagoGuardado;
    }

    public List<Pago> listarTodos() {
        logger.info("Obteniendo lista de todos los pagos");
        return repository.findAll();
    }
}