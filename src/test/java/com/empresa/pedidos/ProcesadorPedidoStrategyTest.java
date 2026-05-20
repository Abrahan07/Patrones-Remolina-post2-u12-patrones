package com.empresa.pedidos;

import com.empresa.pedidos.adaptadores.procesadores.ProcesadorPedidoEstandar;
import com.empresa.pedidos.adaptadores.procesadores.ProcesadorPedidoExpress;
import com.empresa.pedidos.adaptadores.procesadores.ProcesadorPedidoInternacional;
import com.empresa.pedidos.dominio.EstadoPedido;
import com.empresa.pedidos.dominio.Pedido;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProcesadorPedidoStrategyTest {

    @Test
    void procesadorEstandar_calculaCostoCorrectamente() {
        Pedido pedido = new Pedido();
        pedido.setSubtotal(100.0);
        new ProcesadorPedidoEstandar().procesar(pedido);
        assertEquals(110.0, pedido.getCosto(), 0.001);
        assertEquals(EstadoPedido.PROCESADO, pedido.getEstado());
    }

    @Test
    void procesadorExpress_calculaCostoCorrectamente() {
        Pedido pedido = new Pedido();
        pedido.setSubtotal(100.0);
        new ProcesadorPedidoExpress().procesar(pedido);
        assertEquals(130.0, pedido.getCosto(), 0.001);
        assertEquals(EstadoPedido.PROCESADO, pedido.getEstado());
    }

    @Test
    void procesadorInternacional_calculaCostoCorrectamente() {
        Pedido pedido = new Pedido();
        pedido.setSubtotal(100.0);
        new ProcesadorPedidoInternacional().procesar(pedido);
        assertEquals(175.0, pedido.getCosto(), 0.001);
        assertEquals(EstadoPedido.PROCESADO, pedido.getEstado());
    }
}