package org.cvarela.service;

import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.cvarela.configs.Service;
import org.cvarela.models.Camarero;
import org.cvarela.repositories.Repository;
import org.cvarela.repositories.RepositoryMongo;
import java.util.List;
import java.util.Optional;

@Service
public class CamareroServiceMongoImpl implements CamareroService{

    @Inject
    @RepositoryMongo
    private Repository<Camarero> repository;

    @Override
    public List<Camarero> getAll() {
        try {
            return repository.getAll();
        } catch (Exception throwables) {
            throw new ServiceMongoException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Camarero> get(String id) {
        try {
            return Optional.ofNullable(repository.get(new ObjectId(id)));
        } catch (Exception throwables) {
            throw new ServiceMongoException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void save(Camarero camarero) {
        try {
            repository.save(camarero);
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
