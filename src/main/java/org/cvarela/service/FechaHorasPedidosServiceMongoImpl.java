package org.cvarela.service;

import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.cvarela.configs.Service;
import org.cvarela.models.FechaHorasPedidos;
import org.cvarela.repositories.Repository;
import org.cvarela.repositories.RepositoryMongo;
import java.util.List;
import java.util.Optional;

@Service
public class FechaHorasPedidosServiceMongoImpl implements FechaHorasPedidosService {

    @Inject
    @RepositoryMongo
    private Repository<FechaHorasPedidos> repository;

    @Override
    public List<FechaHorasPedidos> getAll() {
        try {
            return repository.getAll();
        } catch (Exception throwables) {
            throw new ServiceMongoException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<FechaHorasPedidos> get(String id) {
        try {
            return Optional.ofNullable(repository.get(new ObjectId(String.valueOf(id))));
        } catch (Exception throwables) {
            throw new ServiceMongoException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void save(FechaHorasPedidos fechaHorasPedidos) {
        try {
            repository.save(fechaHorasPedidos);
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
