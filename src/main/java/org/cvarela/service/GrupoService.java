package org.cvarela.service;

import org.cvarela.models.Grupo;
import java.util.List;
import java.util.Optional;

public interface GrupoService {

    List<Grupo> getAll();

    Optional<Grupo> get(String id);

    void save(Grupo t);

    void delete(String id);
}
