package org.cvarela.utils;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class ConexionBaseDatos<T> {

    private static final String URL = "mongodb+srv://cristianchantada:cristian@cristian-instance.rrvjjrp.mongodb.net/?retryWrites=true&w=majority";
    private MongoClient conn = MongoClients.create(URL);
    private MongoDatabase database;
    private MongoCollection<T> collection;
    private CodecRegistry pojoCodecRegistry;

    public ConexionBaseDatos(String dataBaseName, String collectionName, Class<T> clazz) {
        pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        database = conn.getDatabase(dataBaseName).withCodecRegistry(pojoCodecRegistry);
        collection = database.getCollection(collectionName, clazz);
    }

    public MongoCollection<T> getCollection() {
        return collection;
    }

    public void closeConnection() {
        if (conn != null) {
            conn.close();
        }
    }

}



