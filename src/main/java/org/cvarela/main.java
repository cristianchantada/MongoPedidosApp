package org.cvarela;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.cvarela.models.EstadoCobro;
import org.cvarela.models.Pedido;
import org.cvarela.repositories.Repository;
import org.cvarela.service.PedidoService;
import org.cvarela.service.ServiceMongoException;
import org.cvarela.utils.ConexionBaseDatos;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class main {
    public static void main(String[] args) throws Exception {

       String COLLECTION_NAME = "pedidos";
        String DATABASE_NAME = "pedidos_app";
        ConexionBaseDatos<Pedido> conn = new ConexionBaseDatos<>(DATABASE_NAME, COLLECTION_NAME, Pedido.class);
        MongoCollection<Pedido> collection = conn.getCollection();

        Repository<Pedido> repository = new Repository<Pedido>() {
            @Override
            public Pedido get(ObjectId id) throws Exception {
                ObjectId objectId = new ObjectId(String.valueOf(id));
                Bson equalComp = eq("_id", objectId);
                return collection.find(equalComp).first();
            }

            @Override
            public List<Pedido> getAll() throws Exception {
                List<Pedido> pedidos;

                try {
                    pedidos = collection.find().into(new ArrayList<>());
                } catch (MongoException e) {
                    System.out.println("=====================>MongoException al obtener todos los pedidos======================");
                    throw new ServiceMongoException("Error de MongoDB al obtener todos los pedidos", e);
                } catch (Exception e) {
                    System.out.println("======================>Error inesperado al obtener todos los pedidos=========================");
                    throw new ServiceMongoException("Error inesperado al obtener todos los pedidos", e);
                }
                return pedidos;
            }

            @Override
            public void save(Pedido pedido) throws Exception {
                collection.insertOne(pedido);
            }

            @Override
            public void delete(ObjectId id) throws Exception {

            }
        };

        ObjectId id = new ObjectId("6642fa7569fcea01676abebf");

        //Pedido pedido = new Pedido();
        //pedido.setEstadoCobro(EstadoCobro.COMPLETO);
        List<Pedido> pedidos = repository.getAll();
        System.out.println("pedidos = " + pedidos);

    }
}
