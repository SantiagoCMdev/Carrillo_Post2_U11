package com.universidad.pedidoservice.service;

import com.universidad.pedidoservice.domain.Pedido;
import org.springframework.stereotype.Component;

@Component("EXPRESS")
public class EnvioExpress implements EstrategiaEnvio {
    public double calcularCosto(Pedido pedido) { return 12.99; }
}
