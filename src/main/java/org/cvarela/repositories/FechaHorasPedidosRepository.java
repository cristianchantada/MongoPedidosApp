package org.cvarela.repositories;

import com.mongodb.client.MongoCollection;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.cvarela.configs.Repositorio;
import org.cvarela.models.Bar;
import org.cvarela.models.FechaHorasPedidos;
import org.cvarela.utils.ConexionBaseDatos;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Repositorio
@RepositoryMongo
public class FechaHorasPedidosRepository implements Repository<FechaHorasPedidos> {

    private final String COLLECTION_NAME = "fechas_horas_pedidos";
    private final String DATABASE_NAME = "pedidos_app";
    ConexionBaseDatos<FechaHorasPedidos> conn = new ConexionBaseDatos<>(DATABASE_NAME, COLLECTION_NAME, FechaHorasPedidos.class);
    MongoCollection<FechaHorasPedidos> collection = conn.getCollection();

    @Override
    public FechaHorasPedidos get(ObjectId id) {
        Bson equalComp = eq("_id", id);
        return collection.find(equalComp).first();
    }

    @Override
    public List<FechaHorasPedidos> getAll() {
        List<FechaHorasPedidos> fechasHorasPedidos = new ArrayList<>();
        collection.find().forEach(fechasHorasPedidos::add);
        return fechasHorasPedidos;
    }

    @Override
    public void save(FechaHorasPedidos fechaHorasPedidos) {

    }

    @Override
    public void delete(ObjectId id) {

    }
}
