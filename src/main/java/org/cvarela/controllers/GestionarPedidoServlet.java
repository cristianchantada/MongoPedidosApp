package org.cvarela.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.cvarela.models.Camarero;
import org.cvarela.models.EstadoCobroConsumicion;
import org.cvarela.models.Pedido;
import org.cvarela.service.CamareroService;
import org.cvarela.service.PedidoService;
import org.cvarela.utils.InsertPedidoEntities;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/gestionar-pedido")
public class GestionarPedidoServlet extends HttpServlet {

    @Inject
    private PedidoService service;
    @Inject
    private CamareroService camareroService;
    @Inject
    private InsertPedidoEntities insertPedidoEntities;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        if(id == null || id.isBlank()){
            id = (String) req.getAttribute("id");
        }

        Pedido pedido = new Pedido();
        if (id != null) {
            Optional<Pedido> o = service.get(id);
            if (o.isPresent()) {
                pedido = o.get();
            }
        }

        insertPedidoEntities.insert(pedido);

        if(pedido.getCamareroResponsable() == null) {
            List<Camarero> camareros = camareroService.getAll();
            req.setAttribute("camareros", camareros);
        }

        /*pedido.setImporteTotal((float) pedido.getConsumiciones().stream()
                .mapToDouble(consumicion -> consumicion.getProducto().getPrecio())
                .sum());

       pedido.setImporteSatisfecho((float) pedido.getConsumiciones().stream()
               .filter(consumicion -> consumicion.getEstadoCobroConsumicion() == EstadoCobroConsumicion.PAGADO)
               .mapToDouble(consumicion -> consumicion.getProducto().getPrecio())
               .sum());

       pedido.setImporteRestante(pedido.getImporteTotal() - pedido.getImporteSatisfecho());*/

        req.setAttribute("pedido", pedido);
        getServletContext().getRequestDispatcher("/gestion.jsp").forward(req, resp);

    }
}
