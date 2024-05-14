package org.cvarela.utils;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import org.cvarela.models.*;
import org.cvarela.service.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dependent
public class InsertPedidoEntities {

    @Inject
    private PedidoService pedidoService;
    @Inject
    private CamareroService camareroService;
    @Inject
    private AlumnoService alumnoService;
    @Inject
    private BarService barService;
    @Inject
    private FechaHorasPedidosService fechaHorasPedidosService;
    @Inject
    private GrupoService grupoService;
    @Inject
    private ProductoService productoService;

    public Pedido insert(Pedido pedido) {
        if (pedido.getCamareroId() != null) {
            Optional<Camarero> camareroOptional = camareroService.get(String.valueOf(pedido.getCamareroId()));
            if (camareroOptional.isPresent()) {
                Camarero camarero = camareroOptional.get();
                pedido.setCamareroResponsable(camarero);
            }
        }

        if (pedido.getBarId() != null) {
            Optional<Bar> barOptional = barService.get(String.valueOf(pedido.getBarId()));
            if (barOptional.isPresent()) {
                Bar bar = barOptional.get();
                pedido.setBar(bar);
            }
        }

        if (pedido.getGrupoId() != null) {
            Optional<Grupo> grupoOptional = grupoService.get(String.valueOf(pedido.getGrupoId()));
            if (grupoOptional.isPresent()) {
                Grupo grupo = grupoOptional.get();
                pedido.setGrupo(grupo);
            }
        }

        if (pedido.getConsumiciones() != null && !pedido.getConsumiciones().isEmpty()) {
            List<Consumicion> nuevasConsumiciones = new ArrayList<>();

            for (Consumicion c : pedido.getConsumiciones()) {
                Consumicion consumicion = new Consumicion();
                consumicion.setId(c.getId());
                consumicion.setEstadoCobroConsumicion(c.getEstadoCobroConsumicion());

                if (c.getProductoId() != null) {
                    Optional<Producto> productoOptional = productoService.get(String.valueOf(c.getProductoId()));
                    if (productoOptional.isPresent()) {
                        Producto producto = productoOptional.get();
                        consumicion.setProducto(producto);
                    }
                }

                if (c.getAlumnoId() != null) {
                    Optional<Alumno> alumnoOptional = alumnoService.get(String.valueOf(c.getAlumnoId()));
                    if (alumnoOptional.isPresent()) {
                        Alumno alumno = alumnoOptional.get();
                        consumicion.setAlumno(alumno);
                    }
                }

                nuevasConsumiciones.add(consumicion);
            }

            pedido.setConsumiciones(nuevasConsumiciones);
        }

        pedido.setImporteTotal((float) pedido.getConsumiciones().stream()
                .mapToDouble(consumicion -> consumicion.getProducto().getPrecio())
                .sum());

        pedido.setImporteSatisfecho((float) pedido.getConsumiciones().stream()
                .filter(consumicion -> consumicion.getEstadoCobroConsumicion() == EstadoCobroConsumicion.PAGADO)
                .mapToDouble(consumicion -> consumicion.getProducto().getPrecio())
                .sum());

        pedido.setImporteRestante(pedido.getImporteTotal() - pedido.getImporteSatisfecho());

        return pedido;
    }
}
