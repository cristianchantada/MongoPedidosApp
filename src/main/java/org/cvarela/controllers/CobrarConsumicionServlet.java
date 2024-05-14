package org.cvarela.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.cvarela.models.*;
import org.cvarela.service.PedidoServiceMongoImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@WebServlet("/cobrar-consumicion")
public class CobrarConsumicionServlet extends HttpServlet {

    @Inject
    private PedidoServiceMongoImpl pedidoService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pedidoId = req.getParameter("id");
        String consumicionId = req.getParameter("consumicion-id");

        if (consumicionId != null && !consumicionId.isBlank()) {
            pedidoService.setEstadoCobroConsumicionPagado(pedidoId, consumicionId);
        }

        boolean estaTodoPagado = true;
        Pedido pedido;
        if (pedidoId != null && !pedidoId.isBlank()) {
            Optional<Pedido> o = pedidoService.get(pedidoId);
            if (o.isPresent()) {
                pedido = o.get();

                if(!pedido.getEstadoCobro().equals(EstadoCobro.PARCIAL)){
                    pedido.setEstadoCobro(EstadoCobro.PARCIAL);
                }

                List<Consumicion> consumicionesDelPedido = pedido.getConsumiciones();
                for(Consumicion c : consumicionesDelPedido){
                    if (!c.getEstadoCobroConsumicion().equals(EstadoCobroConsumicion.PAGADO)) {
                        estaTodoPagado = false;
                        break;
                    }
                }

                if(estaTodoPagado){
                    pedido.setEstadoCobro(EstadoCobro.COMPLETO);
                    pedido.setEstado(Estado.CERRADO);
                    pedido.getFechaHorasPedidos().setFechaHoraCerrado(LocalDateTime.now());
                }

            pedidoService.save(pedido);

            }
        }

        if (estaTodoPagado){
            resp.sendRedirect(req.getContextPath() + "/pedidos");
        } else {
            resp.sendRedirect(req.getContextPath() + "/gestionar-pedido?id=" + pedidoId);
        }
    }
}
