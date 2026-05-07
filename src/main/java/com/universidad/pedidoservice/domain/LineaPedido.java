package com.universidad.pedidoservice.domain;

public class LineaPedido {

    private final double precioUnitario;
    private final int cantidad;

    public LineaPedido(double precioUnitario, int cantidad) {
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() { return precioUnitario; }
    public int getCantidad() { return cantidad; }
}
