package org.cvarela.service;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.bson.types.ObjectId;
import org.cvarela.configs.Service;
import org.cvarela.models.Alumno;
import org.cvarela.models.Bar;
import org.cvarela.repositories.Repository;
import org.cvarela.repositories.RepositoryMongo;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoServiceMongoImpl implements AlumnoService {

    @Inject
    @RepositoryMongo
    private Repository<Alumno> repository;

    @Override
    public List<Alumno> getAll() {
        try {
            return repository.getAll();
        } catch (Exception throwables) {
            throw new ServiceMongoException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Alumno> get(String id) {
        try {
            return Optional.ofNullable(repository.get(new ObjectId(id)));
        } catch (Exception throwables) {
            throw new ServiceMongoException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void save(Alumno alumno) {
        try {
            repository.save(alumno);
        } catch (Exception throwables) {
            throw new ServiceMongoException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void delete(String id) {
        try {
            repository.delete(new ObjectId(id));
        } catch (Exception throwables) {
            throw new ServiceMongoException(throwables.getMessage(), throwables.getCause());
        }
    }

}
