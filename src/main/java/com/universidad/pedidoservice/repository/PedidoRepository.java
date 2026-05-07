package com.universidad.pedidoservice.repository;

import com.universidad.pedidoservice.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    com.universidad.pedidoservice.domain.Producto findProductoById(Long id);
}
