package com.empresa.pedidos;

import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.PedidoProcesadoEvent;
import com.empresa.pedidos.infraestructura.notificaciones.NotificacionEmail;
import com.empresa.pedidos.infraestructura.notificaciones.NotificacionLog;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NotificacionObserverTest {

    @Test
    void notificacionEmail_procesaEventoSinExcepcion() {
        Pedido pedido = new Pedido();
        PedidoProcesadoEvent evento = new PedidoProcesadoEvent(pedido);
        NotificacionEmail notificacion = new NotificacionEmail();
        assertDoesNotThrow(() -> notificacion.notificar(evento));
    }

    @Test
    void notificacionLog_procesaEventoSinExcepcion() {
        Pedido pedido = new Pedido();
        PedidoProcesadoEvent evento = new PedidoProcesadoEvent(pedido);
        NotificacionLog notificacion = new NotificacionLog();
        assertDoesNotThrow(() -> notificacion.notificar(evento));
    }
}