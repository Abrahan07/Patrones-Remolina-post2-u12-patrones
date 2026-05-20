package com.empresa.pedidos;

import com.empresa.pedidos.adaptadores.facade.FachadaPedidos;
import com.empresa.pedidos.dominio.EstadoPedido;
import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.TipoPedido;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FachadaPedidosIntegrationTest {

    @Autowired
    private FachadaPedidos fachada;

    @Test
    void crearPedido_flujoCompletoEstandar() {
        Pedido pedido = new Pedido();
        pedido.setTipo(TipoPedido.ESTANDAR);
        pedido.setSubtotal(100.0);

        Pedido resultado = fachada.crearPedido(pedido);

        assertNotNull(resultado.getId());
        assertEquals(110.0, resultado.getCosto(), 0.001);
        assertEquals(EstadoPedido.PROCESADO, resultado.getEstado());
    }

    @Test
    void crearPedido_flujoCompletoExpress() {
        Pedido pedido = new Pedido();
        pedido.setTipo(TipoPedido.EXPRESS);
        pedido.setSubtotal(100.0);

        Pedido resultado = fachada.crearPedido(pedido);

        assertNotNull(resultado.getId());
        assertEquals(130.0, resultado.getCosto(), 0.001);
        assertEquals(EstadoPedido.PROCESADO, resultado.getEstado());
    }

    @Test
    void crearPedido_flujoCompletoInternacional() {
        Pedido pedido = new Pedido();
        pedido.setTipo(TipoPedido.INTERNACIONAL);
        pedido.setSubtotal(100.0);

        Pedido resultado = fachada.crearPedido(pedido);

        assertNotNull(resultado.getId());
        assertEquals(175.0, resultado.getCosto(), 0.001);
        assertEquals(EstadoPedido.PROCESADO, resultado.getEstado());
    }
}