package com.universidad.pedidoservice.domain;

public class Cliente {

    private final Long id;
    private final String nombre;
    private final boolean activo;
    private final int score;
    private final double limiteCredito;

    public Cliente(Long id, String nombre, boolean activo,
                   int score, double limiteCredito) {
        this.id = id;
        this.nombre = nombre;
        this.activo = activo;
        this.score = score;
        this.limiteCredito = limiteCredito;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public boolean isActivo() { return activo; }
    public int getScore() { return score; }
    public double getLimiteCredito() { return limiteCredito; }
}
