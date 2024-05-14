package org.cvarela.repositories;

import com.mongodb.client.MongoCollection;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.cvarela.configs.Repositorio;
import org.cvarela.models.Producto;
import org.cvarela.utils.ConexionBaseDatos;
import java.util.ArrayList;
import java.util.List;
import static com.mongodb.client.model.Filters.eq;

@Repositorio
@RepositoryMongo
public class ProductoRepository implements Repository<Producto> {

    private final String COLLECTION_NAME = "productos";
    private final String DATABASE_NAME = "pedidos_app";
    ConexionBaseDatos<Producto> conn = new ConexionBaseDatos<>(DATABASE_NAME, COLLECTION_NAME, Producto.class);
    MongoCollection<Producto> collection = conn.getCollection();

    @Override
    public Producto get(ObjectId id) {
        Bson equalComp = eq("_id", id);
        return collection.find(equalComp).first();
    }

    @Override
    public List<Producto> getAll() {
        List<Producto> productos = new ArrayList<>();
        collection.find().forEach(productos::add);
        return productos;
    }

    @Override
    public void save(Producto producto) {
    }

    @Override
    public void delete(ObjectId id) {
    }
}
