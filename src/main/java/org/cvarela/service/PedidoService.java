package org.cvarela.service;

import org.cvarela.models.Consumicion;
import org.cvarela.models.EstadoCobroConsumicion;
import org.cvarela.models.Pedido;
import java.util.List;
import java.util.Optional;

public interface PedidoService {

    List<Pedido> getAll();

    Optional<Pedido> get(String id);

    void save(Pedido pedido);

    void delete(String id);


}
