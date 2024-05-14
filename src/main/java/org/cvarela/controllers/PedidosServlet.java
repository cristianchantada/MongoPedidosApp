package org.cvarela.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.cvarela.models.*;
import org.cvarela.service.*;
import org.cvarela.utils.InsertPedidoEntities;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet("/pedidos")
public class PedidosServlet extends HttpServlet {

    @Inject
    private PedidoService pedidoService;
    @Inject
    private CamareroService camareroService;
    @Inject
    private  InsertPedidoEntities insertPedidoEntities;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Pedido> pedidos = pedidoService.getAll();
        List<Camarero> camareros = camareroService.getAll();

        List<Pedido> pedidosNew = new ArrayList<>();
        for (Pedido p : pedidos) {
            Pedido pe = insertPedidoEntities.insert(p);
            pedidosNew.add(pe);
        }

        req.setAttribute("camareros", camareros);
        req.setAttribute("pedidos", pedidosNew);
        getServletContext().getRequestDispatcher("/pedidos.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pedidoId = req.getParameter("pedidoId");
        String camareroId = req.getParameter("camarero");

        Pedido pedido = new Pedido();
        if (pedidoId != null && !pedidoId.isBlank()) {
            Optional<Pedido> o = pedidoService.get(pedidoId);
            if (o.isPresent()) {
                pedido = o.get();
            }
        }

        Camarero camarero;
        if (camareroId != null && !camareroId.isBlank()) {
            Optional<Camarero> o = camareroService.get(camareroId);
            if (o.isPresent()) {
                camarero = o.get();
                pedido.setCamareroResponsable(camarero);
                pedido.setCamareroId(camarero.getId());
                pedido.setEstado(Estado.EN_PROCESO);
                pedido.getFechaHorasPedidos().setFechaHoraEnProceso(LocalDateTime.now());
                pedidoService.save(pedido);
            }
        }

        resp.sendRedirect(req.getContextPath() + "/gestionar-pedido?id=" + pedido.getId());
    }

}
