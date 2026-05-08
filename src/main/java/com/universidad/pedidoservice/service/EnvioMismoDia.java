package com.universidad.pedidoservice.service;

import com.universidad.pedidoservice.domain.Pedido;
import org.springframework.stereotype.Component;

@Component("MISMO_DIA")
public class EnvioMismoDia implements EstrategiaEnvio {
    public double calcularCosto(Pedido pedido) { return 24.99; }
}
