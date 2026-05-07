package com.universidad.pedidoservice.service;

import com.universidad.pedidoservice.domain.Pedido;
import com.universidad.pedidoservice.domain.Producto;
import com.universidad.pedidoservice.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    public String procesarPedido(Long clienteId, String clienteNombre,
                                 String clienteEmail, String clienteTelefono,
                                 String clienteDireccion, String clienteCiudad,
                                 String clienteCodigoPostal, List<Long> productosIds,
                                 List<Integer> cantidades, String metodoPago,
                                 boolean esUrgente, String codigoDescuento) {

        if (clienteId == null || clienteNombre == null
                || clienteNombre.isBlank() || clienteEmail == null
                || !clienteEmail.contains("@")) {
            return "ERROR_CLIENTE";
        }

        double total = 0;
        for (int i = 0; i < productosIds.size(); i++) {
            Producto p = repo.findProductoById(productosIds.get(i));
            if (p == null) return "ERROR_PRODUCTO";
            total += p.getPrecio() * cantidades.get(i);
        }

        if (codigoDescuento != null && codigoDescuento.equals("VIP10")) {
            total = total * 0.90;
        } else if (codigoDescuento != null && codigoDescuento.equals("NEW20")) {
            total = total * 0.80;
        }

        System.out.println("Enviando email a: " + clienteEmail);
        System.out.println("Pedido urgente: " + esUrgente);

        Pedido pedido = new Pedido(clienteId, clienteNombre, total);
        return "OK_" + repo.save(pedido).getId();
    }
}
