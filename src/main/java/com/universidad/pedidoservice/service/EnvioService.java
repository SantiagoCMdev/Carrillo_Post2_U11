package com.universidad.pedidoservice.service;
import com.universidad.pedidoservice.domain.Cliente;
import com.universidad.pedidoservice.domain.Pedido;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class EnvioService {

    private final Map<String, EstrategiaEnvio> estrategias;

    public EnvioService(Map<String, EstrategiaEnvio> estrategias) {
        this.estrategias = estrategias;
    }

    public double calcularEnvio(Pedido pedido, String tipo) {
        return Optional.ofNullable(estrategias.get(tipo))
                .orElseThrow(() -> new IllegalArgumentException(
                        "Tipo de envio desconocido: " + tipo))
                .calcularCosto(pedido);
    }

    public String aprobarCredito(Cliente c, double monto) {
        if (c == null) return "RECHAZADO";
        if (!c.isActivo()) return "RECHAZADO";
        if (c.getScore() < 600) return "RECHAZADO";
        if (monto <= 0) return "RECHAZADO";
        if (monto > c.getLimiteCredito()) return "RECHAZADO";
        return "APROBADO";
    }
}
