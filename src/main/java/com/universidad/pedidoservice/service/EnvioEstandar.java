package com.universidad.pedidoservice.service;

import com.universidad.pedidoservice.domain.Pedido;
import org.springframework.stereotype.Component;

@Component("ESTANDAR")
public class EnvioEstandar implements EstrategiaEnvio {
    public double calcularCosto(Pedido pedido) {
        return pedido.getTotal() > 50 ? 0.0 : 5.99;
    }
}
