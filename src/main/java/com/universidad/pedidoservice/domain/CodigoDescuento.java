package com.universidad.pedidoservice.domain;

public class CodigoDescuento {

    private final String codigo;
    private final double porcentaje;

    private CodigoDescuento(String codigo, double porcentaje) {
        this.codigo = codigo;
        this.porcentaje = porcentaje;
    }

    public static CodigoDescuento from(String codigo) {
        if (codigo == null) return null;
        return switch (codigo) {
            case "VIP10" -> new CodigoDescuento(codigo, 0.10);
            case "NEW20" -> new CodigoDescuento(codigo, 0.20);
            default -> null;
        };
    }

    public double getPorcentaje() { return porcentaje; }
    public String getCodigo() { return codigo; }
}
