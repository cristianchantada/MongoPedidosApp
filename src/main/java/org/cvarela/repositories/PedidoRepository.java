package org.cvarela.repositories;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.cvarela.configs.Repositorio;
import org.cvarela.models.*;
import org.cvarela.utils.ConexionBaseDatos;
import java.util.ArrayList;
import java.util.List;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Updates.set;


@Repositorio
@RepositoryMongo
public class PedidoRepository implements Repository<Pedido> {

    private final String COLLECTION_NAME = "pedidos";
    private final String DATABASE_NAME = "pedidos_app";
    ConexionBaseDatos<Pedido> conn = new ConexionBaseDatos<>(DATABASE_NAME, COLLECTION_NAME, Pedido.class);
    MongoCollection<Pedido> collection = conn.getCollection();

    @Override
    public Pedido get(ObjectId id) {
        Bson equalComp = eq("_id", id);
        return collection.find(equalComp).first();
    }

    @Override
    public List<Pedido> getAll() throws Exception {
        List<Pedido> pedidos = new ArrayList<>();
        collection.find().forEach(pedidos::add);
        return pedidos;
    }

    @Override
    public void save(Pedido pedido) {
        if (pedido.getId() == null) {
            collection.insertOne(pedido);
        } else {
            collection.replaceOne(eq("_id", pedido.getId()), pedido);
        }
    }

    @Override
    public void delete(ObjectId id) {

    }

    public void setEstadoCobroConsumicionPagado(ObjectId pedidoId, ObjectId consumicionId) {

        Bson filter = and(eq("_id", pedidoId), eq("consumiciones._id", consumicionId));
        Bson update = set("consumiciones.$.estado_cobro", "PAGADO");
        UpdateResult result = collection.updateOne(filter, update);
        if (result.getMatchedCount() == 0) {
            throw new RuntimeException("No se encontró el pedido o la consumición especificados");
        }
    }
}
