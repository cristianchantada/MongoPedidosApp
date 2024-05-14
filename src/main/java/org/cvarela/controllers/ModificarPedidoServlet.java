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
import org.cvarela.utils.InsertPedidoEntities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/modificar")
public class ModificarPedidoServlet extends HttpServlet {

    @Inject
    private PedidoService service;

    @Inject
    private InsertPedidoEntities insertPedidoEntities;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Pedido> pedidos = service.getAll();

        List<Pedido> pedidosNew = new ArrayList<>();
        for (Pedido p : pedidos) {
            Pedido pe = insertPedidoEntities.insert(p);
            pedidosNew.add(pe);
        }

        pedidosNew = pedidosNew.stream().filter(p -> p.getEstado() != null && p.getEstado().equals(Estado.EN_COLA)).toList();

        req.setAttribute("pedidos", pedidosNew);
        getServletContext().getRequestDispatcher("/modificacion.jsp").forward(req, resp);

    }


}
