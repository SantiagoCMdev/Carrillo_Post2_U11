package com.universidad.pedidoservice.service;

import com.universidad.pedidoservice.domain.Pedido;
import org.springframework.stereotype.Component;

@Component("GRATIS")
public class EnvioGratis implements EstrategiaEnvio {
    public double calcularCosto(Pedido pedido) { return 0.0; }
}
