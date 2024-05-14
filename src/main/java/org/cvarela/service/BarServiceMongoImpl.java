package org.cvarela.service;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.bson.types.ObjectId;
import org.cvarela.configs.Service;
import org.cvarela.models.Bar;
import org.cvarela.repositories.Repository;
import org.cvarela.repositories.RepositoryMongo;
import java.util.List;
import java.util.Optional;

@Service
public class BarServiceMongoImpl implements BarService {

    @Inject
    @RepositoryMongo
    private Repository<Bar> repository;

    @Override
    public List<Bar> getAll() {
        try {
            return repository.getAll();
        } catch (Exception throwables) {
            throw new ServiceMongoException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Bar> get(String id) {
        try {
            return Optional.ofNullable(repository.get(new ObjectId(String.valueOf(id))));
        } catch (Exception throwables) {
            throw new ServiceMongoException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void save(Bar bar) {
        try {
            repository.save(bar);
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
