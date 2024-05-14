package org.cvarela.service;

import org.cvarela.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    List<Producto> getAll();

    Optional<Producto> get(String id);

    void save(Producto t);

    void delete(String id);
}
