package org.cvarela.service;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.bson.types.ObjectId;
import org.cvarela.configs.Service;
import org.cvarela.models.EstadoCobroConsumicion;
import org.cvarela.models.Pedido;
import org.cvarela.repositories.PedidoRepository;
import org.cvarela.repositories.Repository;
import org.cvarela.repositories.RepositoryMongo;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceMongoImpl implements PedidoService {

    @Inject
    @RepositoryMongo
    private Repository<Pedido> repository;

    @Override
    public List<Pedido> getAll() {
        try {
            return repository.getAll();
        } catch (Exception throwables) {
            throw new ServiceMongoException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Pedido> get(String id) {
        try {
            return Optional.ofNullable(repository.get(new ObjectId(id)));
        } catch (Exception throwables) {
            throw new ServiceMongoException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void save(Pedido pedido) {
        try {
            repository.save(pedido);
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

    public void setEstadoCobroConsumicionPagado(String pedidoId, String consumicionId){
        try {
            PedidoRepository pedidoRepository = new PedidoRepository();
            pedidoRepository.setEstadoCobroConsumicionPagado(
                    new ObjectId(String.valueOf(pedidoId)),
                    new ObjectId(String.valueOf(consumicionId)));
        } catch (Exception throwables) {
            throw new ServiceMongoException(throwables.getMessage(), throwables.getCause());
        }
    }

}
