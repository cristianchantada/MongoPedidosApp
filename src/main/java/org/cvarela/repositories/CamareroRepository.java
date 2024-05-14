package org.cvarela.repositories;

import com.mongodb.client.MongoCollection;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.cvarela.configs.Repositorio;
import org.cvarela.models.Camarero;
import org.cvarela.utils.ConexionBaseDatos;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Repositorio
@RepositoryMongo
public class CamareroRepository implements Repository<Camarero> {

    private final String COLLECTION_NAME = "camareros";
    private final String DATABASE_NAME = "pedidos_app";
    ConexionBaseDatos<Camarero> conn = new ConexionBaseDatos<>(DATABASE_NAME, COLLECTION_NAME, Camarero.class);
    MongoCollection<Camarero> collection = conn.getCollection();

    @Override
    public Camarero get(ObjectId id) {
        Bson equalComp = eq("_id", id);
        return collection.find(equalComp).first();
    }

    @Override
    public List<Camarero> getAll() {
        List<Camarero> camareros = new ArrayList<>();
        collection.find().forEach(camareros::add);
        return camareros;
    }

    @Override
    public void save(Camarero camarero) {

    }

    @Override
    public void delete(ObjectId id) {

    }
}