package org.cvarela.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.cvarela.models.Estado;
import org.cvarela.models.Pedido;
import org.cvarela.service.PedidoService;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@WebServlet("/despachar")
public class CambiarEstadoPedidoServlet extends HttpServlet {

    @Inject
    private PedidoService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pedidoId = req.getParameter("id");
        Pedido pedido;
        if (pedidoId != null && !pedidoId.isBlank()) {
            Optional<Pedido> o = service.get(pedidoId);
            if (o.isPresent()) {
                pedido = o.get();
                pedido.setEstado(Estado.DESPACHADO);
                pedido.getFechaHorasPedidos().setFechaHoraDespachado(LocalDateTime.now());
                service.save(pedido);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/gestionar-pedido?id=" + pedidoId);
    }
}
