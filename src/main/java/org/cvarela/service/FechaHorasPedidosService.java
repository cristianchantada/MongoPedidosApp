package org.cvarela.service;

import org.cvarela.models.FechaHorasPedidos;
import org.cvarela.models.Producto;
import java.util.List;
import java.util.Optional;

public interface FechaHorasPedidosService {

    List<FechaHorasPedidos> getAll();

    Optional<FechaHorasPedidos> get(String id);

    void save(FechaHorasPedidos t);

    void delete(String id);
}
