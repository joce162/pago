package com.mayorista.pago.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagoss")
@Data
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pago;

    private Long id_factura;
    private Double montoo_pagado;
    private String metodo_pago;
    private LocalDateTime fecha_pago;
}