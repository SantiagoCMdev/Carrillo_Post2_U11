package com.universidad.pedidoservice.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long clienteId;
    private String clienteNombre;
    private Double total;

    public Pedido(Long clienteId, String clienteNombre, Double total) {
        this.clienteId = clienteId;
        this.clienteNombre = clienteNombre;
        this.total = total;
    }
}
