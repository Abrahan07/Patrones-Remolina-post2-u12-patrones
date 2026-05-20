package com.empresa.pedidos;

import com.empresa.pedidos.adaptadores.procesadores.ProcesadorPedidoEstandar;
import com.empresa.pedidos.adaptadores.procesadores.ProcesadorPedidoExpress;
import com.empresa.pedidos.adaptadores.procesadores.ProcesadorPedidoInternacional;
import com.empresa.pedidos.aplicacion.ProcesadorPedidoFactory;
import com.empresa.pedidos.dominio.TipoPedido;
import com.empresa.pedidos.dominio.puertos.ProcesadorPedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProcesadorPedidoFactoryTest {

    private ProcesadorPedidoFactory factory;

    @BeforeEach
    void setUp() {
        List<ProcesadorPedido> procesadores = List.of(
                new ProcesadorPedidoEstandar(),
                new ProcesadorPedidoExpress(),
                new ProcesadorPedidoInternacional()
        );
        factory = new ProcesadorPedidoFactory(procesadores);
    }

    @Test
    void factory_retornaProcesadorEstandar() {
        ProcesadorPedido p = factory.obtener(TipoPedido.ESTANDAR);
        assertInstanceOf(ProcesadorPedidoEstandar.class, p);
    }

    @Test
    void factory_retornaProcesadorExpress() {
        ProcesadorPedido p = factory.obtener(TipoPedido.EXPRESS);
        assertInstanceOf(ProcesadorPedidoExpress.class, p);
    }

    @Test
    void factory_retornaProcesadorInternacional() {
        ProcesadorPedido p = factory.obtener(TipoPedido.INTERNACIONAL);
        assertInstanceOf(ProcesadorPedidoInternacional.class, p);
    }

    @Test
    void factory_lanzaExcepcionParaTipoNoSoportado() {
        assertThrows(IllegalArgumentException.class,
                () -> factory.obtener(null));
    }
}