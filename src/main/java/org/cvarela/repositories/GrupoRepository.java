package org.cvarela.repositories;

import com.mongodb.client.MongoCollection;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.cvarela.configs.Repositorio;
import org.cvarela.models.Grupo;
import org.cvarela.utils.ConexionBaseDatos;
import java.util.ArrayList;
import java.util.List;
import static com.mongodb.client.model.Filters.eq;

@Repositorio
@RepositoryMongo
public class GrupoRepository implements Repository<Grupo> {

    private final String COLLECTION_NAME = "grupos";
    private final String DATABASE_NAME = "pedidos_app";
    ConexionBaseDatos<Grupo> conn = new ConexionBaseDatos<>(DATABASE_NAME, COLLECTION_NAME, Grupo.class);
    MongoCollection<Grupo> collection = conn.getCollection();

    @Override
    public Grupo get(ObjectId id) {
        Bson equalComp = eq("_id", id);
        return collection.find(equalComp).first();
    }

    @Override
    public List<Grupo> getAll() {
        List<Grupo> grupos = new ArrayList<>();
        collection.find().forEach(grupos::add);
        return grupos;
    }

    @Override
    public void save(Grupo grupo) {

    }

    @Override
    public void delete(ObjectId id) {

    }
}