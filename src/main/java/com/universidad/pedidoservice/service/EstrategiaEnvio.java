package com.universidad.pedidoservice.service;

import com.universidad.pedidoservice.domain.Pedido;

public interface EstrategiaEnvio {
    double calcularCosto(Pedido pedido);
}
