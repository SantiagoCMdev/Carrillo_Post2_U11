package com.universidad.pedidoservice;

import com.universidad.pedidoservice.domain.Cliente;
import com.universidad.pedidoservice.domain.Pedido;
import com.universidad.pedidoservice.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EnvioServiceTest {

    private EnvioService service;

    @BeforeEach
    void setUp() {
        service = new EnvioService(Map.of(
                "ESTANDAR", new EnvioEstandar(),
                "EXPRESS", new EnvioExpress(),
                "MISMO_DIA", new EnvioMismoDia(),
                "GRATIS", new EnvioGratis()
        ));
    }

    @Test
    void calcularEnvio_estandar_conTotalAlto_debeSerGratis() {
        Pedido p = new Pedido();
        p.setTotal(60.0);
        assertEquals(0.0, service.calcularEnvio(p, "ESTANDAR"), 0.001);
    }

    @Test
    void calcularEnvio_estandar_conTotalBajo_debeCobrar() {
        Pedido p = new Pedido();
        p.setTotal(20.0);
        assertEquals(5.99, service.calcularEnvio(p, "ESTANDAR"), 0.001);
    }

    @Test
    void calcularEnvio_express_debeCobrarFijo() {
        Pedido p = new Pedido();
        p.setTotal(10.0);
        assertEquals(12.99, service.calcularEnvio(p, "EXPRESS"), 0.001);
    }

    @Test
    void calcularEnvio_mismoDia_debeCobrarFijo() {
        Pedido p = new Pedido();
        p.setTotal(10.0);
        assertEquals(24.99, service.calcularEnvio(p, "MISMO_DIA"), 0.001);
    }

    @Test
    void calcularEnvio_gratis_debeSerCero() {
        Pedido p = new Pedido();
        p.setTotal(10.0);
        assertEquals(0.0, service.calcularEnvio(p, "GRATIS"), 0.001);
    }

    @Test
    void calcularEnvio_tipoDesconocido_debeLanzarExcepcion() {
        Pedido p = new Pedido();
        p.setTotal(10.0);
        assertThrows(IllegalArgumentException.class,
                () -> service.calcularEnvio(p, "INVALIDO"));
    }

    @Test
    void aprobarCredito_clienteNulo_debeRechazar() {
        assertEquals("RECHAZADO", service.aprobarCredito(null, 1000));
    }

    @Test
    void aprobarCredito_clienteInactivo_debeRechazar() {
        Cliente c = new Cliente(1L, "Juan", false, 700, 5000);
        assertEquals("RECHAZADO", service.aprobarCredito(c, 1000));
    }

    @Test
    void aprobarCredito_scoreInsuficiente_debeRechazar() {
        Cliente c = new Cliente(1L, "Juan", true, 500, 5000);
        assertEquals("RECHAZADO", service.aprobarCredito(c, 1000));
    }

    @Test
    void aprobarCredito_montoNegativo_debeRechazar() {
        Cliente c = new Cliente(1L, "Juan", true, 700, 5000);
        assertEquals("RECHAZADO", service.aprobarCredito(c, -100));
    }

    @Test
    void aprobarCredito_montoSuperaLimite_debeRechazar() {
        Cliente c = new Cliente(1L, "Juan", true, 700, 5000);
        assertEquals("RECHAZADO", service.aprobarCredito(c, 6000));
    }

    @Test
    void aprobarCredito_todoCorrecto_debeAprobar() {
        Cliente c = new Cliente(1L, "Juan", true, 700, 5000);
        assertEquals("APROBADO", service.aprobarCredito(c, 1000));
    }
}
