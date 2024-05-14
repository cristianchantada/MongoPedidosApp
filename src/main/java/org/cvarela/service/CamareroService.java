package org.cvarela.service;

import org.cvarela.models.Camarero;
import java.util.List;
import java.util.Optional;

public interface CamareroService {

    List<Camarero> getAll();

    Optional<Camarero> get(String id);

    void save(Camarero t);

    void delete(String id);
}
