package org.cvarela.configs;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import jakarta.enterprise.context.RequestScoped;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Inject;


import java.util.logging.Logger;

@ApplicationScoped
public class ProducerResources {

    @Inject
    private Logger log;

    @Produces
    private Logger beanLogger(InjectionPoint injectionPoint){
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }




    // intentar crear en el contexto la conexion a la base de datos de Mongo. Hay que anotar
    // el close con @disposes ver clase de Udemy

    //@Produces
    //@RequestScoped
    //@MongoConn



}
