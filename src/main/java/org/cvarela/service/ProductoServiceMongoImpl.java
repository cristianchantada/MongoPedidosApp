package org.cvarela.service;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.bson.types.ObjectId;
import org.cvarela.configs.Service;
import org.cvarela.models.Producto;
import org.cvarela.repositories.Repository;
import org.cvarela.repositories.RepositoryMongo;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceMongoImpl implements ProductoService {

    @Inject
    @RepositoryMongo
    private Repository<Producto> repository;

    @Override
    public List<Producto> getAll() {
        try {
            return repository.getAll();
        } catch (Exception throwables) {
            throw new ServiceMongoException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Producto> get(String id) {
        try {
            return Optional.ofNullable(repository.get(new ObjectId(id)));
        } catch (Exception throwables) {
            throw new ServiceMongoException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void save(Producto producto) {
        try {
            repository.save(producto);
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
