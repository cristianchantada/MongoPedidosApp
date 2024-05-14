package org.cvarela.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.cvarela.models.*;
import org.cvarela.service.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;

@WebServlet("/form")
public class PedidoFormServlet extends HttpServlet {

    @Inject
    private PedidoService pedidoService;
    @Inject
    private AlumnoService alumnoService;
    @Inject
    private BarService barService;
    @Inject
    private GrupoService grupoService;
    @Inject
    private ProductoService productoService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        Pedido pedido = new Pedido();
        if (id != null && !id.isBlank()) {
            Optional<Pedido> o = pedidoService.get(id);
            if (o.isPresent()) {
                pedido = o.get();
                pedido.setEstado(Estado.EN_MODIFICACION);
                pedido.getFechaHorasPedidos().setFechaHoraModificado(LocalDateTime.now());
                pedidoService.save(pedido);
            }
        }

        List<Alumno> alumnos = alumnoService.getAll();
        List<Bar> bares = barService.getAll();
        List<Grupo> grupos = grupoService.getAll();
        List<Producto> productos = productoService.getAll();

        List<Consumicion> consumiciones = new ArrayList<>();
        consumiciones.add(new Consumicion());

        req.setAttribute("pedido", pedido);
        req.setAttribute("grupos", grupos);
        req.setAttribute("alumnos", alumnos);
        req.setAttribute("bares", bares);
        req.setAttribute("productos", productos);
        req.setAttribute("consumiciones", consumiciones);

        getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String grupoId = req.getParameter("grupo");
        String barId = req.getParameter("bar");

        String[] productoIdArray = req.getParameterValues("producto");
        String[] alumnoIdArray = req.getParameterValues("alumno");
        Map<String, String> errores = new HashMap<>();


        if (grupoId == null || grupoId.isEmpty()){
            errores.put("grupo", "el grupo es requerido!");
        }

        if (barId == null || barId.isEmpty()){
            errores.put("bar", "el bar es requerido!");
        }

        String pedidoId = req.getParameter("id");
        Pedido pedido = new Pedido();

        if (pedidoId != null && !pedidoId.isBlank()) {
            Optional<Pedido> o = pedidoService.get(pedidoId);
            if (o.isPresent()) {
                pedido = o.get();
            }
        }

        Grupo grupo;
        if (grupoId != null && !grupoId.isBlank()) {
            Optional<Grupo> o = grupoService.get(grupoId);
            if (o.isPresent()) {
                grupo = o.get();
                pedido.setGrupoId(grupo.getId());
            }
        }

        Bar bar;
        if (barId != null && !barId.isEmpty()) {
            Optional<Bar> o = barService.get(barId);
            if (o.isPresent()) {
                bar = o.get();
                pedido.setBarId(bar.getId());
            }
        }

        pedido.getConsumiciones().clear();
        for(int i = 0; i < productoIdArray.length; i++) {

            Consumicion consumicion = new Consumicion();
            consumicion.setEstadoCobroConsumicion(EstadoCobroConsumicion.PENDIENTE);

            String alumnoId = alumnoIdArray[i];
            String productoId = productoIdArray[i];

            Alumno alumno;
            if (alumnoId !=null && !alumnoId.isBlank()) {
                Optional<Alumno> o = alumnoService.get(alumnoId);
                if(o.isPresent()){
                    alumno = o.get();
                    consumicion.setAlumnoId(alumno.getId());
                }
            }

            Producto producto;
            if (productoId != null && !productoId.isBlank()) {
                Optional<Producto> o = productoService.get(productoId);
                if (o.isPresent()) {
                    producto = o.get();
                    consumicion.setProductoId(producto.getId());
                }
            }

            pedido.getConsumiciones().add(consumicion);
            pedido.getFechaHorasPedidos().setFechaHoraPedido(LocalDateTime.now());
        }

        if (errores.isEmpty()) {
            pedido.setEstado(Estado.EN_COLA);
            pedidoService.save(pedido);
            resp.sendRedirect(req.getContextPath() + "/pedidos");
        } else {
            req.setAttribute("errores", errores);
            req.setAttribute("grupos", grupoService.getAll());
            req.setAttribute("alumnos", alumnoService.getAll());
            req.setAttribute("bares", barService.getAll());
            req.setAttribute("productos", productoService.getAll());
            req.setAttribute("pedido", pedido);
            getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
        }
    }
}
