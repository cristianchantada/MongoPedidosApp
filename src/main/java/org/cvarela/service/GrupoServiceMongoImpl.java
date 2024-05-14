package org.cvarela.service;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.bson.types.ObjectId;
import org.cvarela.configs.Service;
import org.cvarela.models.Grupo;
import org.cvarela.repositories.Repository;
import org.cvarela.repositories.RepositoryMongo;

import java.util.List;
import java.util.Optional;

@Service
public class GrupoServiceMongoImpl implements GrupoService {

    @Inject
    @RepositoryMongo
    private Repository<Grupo> repository;
    
    @Override
    public List<Grupo> getAll() {
        try {
            return repository.getAll();
        } catch (Exception throwables) {
            throw new ServiceMongoException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Grupo> get(String id) {
        try {
            return Optional.ofNullable(repository.get(new ObjectId(id)));
        } catch (Exception throwables) {
            throw new ServiceMongoException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void save(Grupo grupo) {
        try {
            repository.save(grupo);
        } catch (Exception throwables) {
            throw new ServiceMongoException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void delete(String id) {
        try {
            repository.delete(new ObjectId(String.valueOf(id)));
        } catch (Exception throwables) {
            throw new ServiceMongoException(throwables.getMessage(), throwables.getCause());
        }
    }
    
}
