package org.cvarela.repositories;

import com.mongodb.client.MongoCollection;
import jakarta.inject.Inject;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.cvarela.configs.Repositorio;
import org.cvarela.models.Alumno;
import org.cvarela.utils.ConexionBaseDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.eq;

@Repositorio
@RepositoryMongo
public class AlumnoRepository implements Repository<Alumno> {

    private final String COLLECTION_NAME = "alumnos";
    private final String DATABASE_NAME = "pedidos_app";
    ConexionBaseDatos<Alumno> conn = new ConexionBaseDatos<>(DATABASE_NAME, COLLECTION_NAME, Alumno.class);
    MongoCollection<Alumno> collection = conn.getCollection();

    @Override
    public Alumno get(ObjectId id) {
        Bson equalComp = eq("_id", id);
        return collection.find(equalComp).first();
    }

    @Override
    public List<Alumno> getAll() {
        List<Alumno> alumnos = new ArrayList<>();
        collection.find().forEach(alumnos::add);
        return alumnos;
    }

    @Override
    public void save(Alumno alumno) {

    }

    @Override
    public void delete(ObjectId id) {

    }
}
