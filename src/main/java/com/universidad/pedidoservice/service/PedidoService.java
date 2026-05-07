package com.universidad.pedidoservice.service;

import com.universidad.pedidoservice.domain.*;
import com.universidad.pedidoservice.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class PedidoService {

    private final PedidoRepository repo;
    private final NotificacionService notificacion;

    public PedidoService(PedidoRepository repo,
                         NotificacionService notificacion) {
        this.repo = repo;
        this.notificacion = notificacion;
    }

    public String procesarPedido(Long clienteId, DatosCliente cliente,
                                 LineaPedido[] lineas, String metodoPago,
                                 boolean esUrgente, CodigoDescuento descuento) {
        double total = calcularTotal(lineas);
        double totalConDescuento = aplicarDescuento(total, descuento);
        notificacion.notificarPedido(cliente, esUrgente);
        return persistirPedido(clienteId, cliente, totalConDescuento);
    }

    private double calcularTotal(LineaPedido[] lineas) {
        return Arrays.stream(lineas)
                .mapToDouble(l -> l.getPrecioUnitario() * l.getCantidad())
                .sum();
    }

    private double aplicarDescuento(double total, CodigoDescuento descuento) {
        return descuento != null ? total * (1 - descuento.getPorcentaje()) : total;
    }

    private String persistirPedido(Long clienteId, DatosCliente cliente,
                                   double total) {
        Pedido pedido = new Pedido(clienteId, cliente.getNombre(), total);
        return "OK_" + repo.save(pedido).getId();
    }
}
