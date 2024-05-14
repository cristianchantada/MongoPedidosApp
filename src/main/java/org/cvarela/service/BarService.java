package org.cvarela.service;

import org.cvarela.models.Bar;
import java.util.List;
import java.util.Optional;

public interface BarService {

    List<Bar> getAll();

    Optional<Bar> get(String id);

    void save(Bar t);

    void delete(String id);
}
