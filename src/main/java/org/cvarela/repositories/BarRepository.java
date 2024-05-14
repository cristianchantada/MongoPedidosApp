package org.cvarela.repositories;

import com.mongodb.client.MongoCollection;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.cvarela.configs.Repositorio;
import org.cvarela.models.Alumno;
import org.cvarela.models.Bar;
import org.cvarela.utils.ConexionBaseDatos;

import java.util.ArrayList;
import java.util.List;
import static com.mongodb.client.model.Filters.eq;

@Repositorio
@RepositoryMongo
public class BarRepository implements Repository<Bar> {

    private final String COLLECTION_NAME = "bares";
    private final String DATABASE_NAME = "pedidos_app";
    ConexionBaseDatos<Bar> conn = new ConexionBaseDatos<>(DATABASE_NAME, COLLECTION_NAME, Bar.class);
    MongoCollection<Bar> collection = conn.getCollection();


    @Override
    public Bar get(ObjectId id) {
        Bson equalComp = eq("_id", id);
        return collection.find(equalComp).first();
    }

    @Override
    public List<Bar> getAll() {
        List<Bar> bares = new ArrayList<>();
        collection.find().forEach(bares::add);
        return bares;
    }

    @Override
    public void save(Bar bar) {

    }

    @Override
    public void delete(ObjectId id) {

    }
}