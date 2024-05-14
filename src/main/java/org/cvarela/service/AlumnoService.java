package org.cvarela.service;

import org.cvarela.models.Alumno;
import java.util.List;
import java.util.Optional;

public interface AlumnoService {

    List<Alumno> getAll();

    Optional<Alumno> get(String id);

    void save(Alumno t);

    void delete(String id);

}
