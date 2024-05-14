package org.cvarela.repositories;

import org.bson.types.ObjectId;

import java.util.List;

public interface Repository<T> {
    T get(ObjectId id) throws Exception;

    List<T> getAll() throws Exception;

    void save(T t) throws Exception;

    void delete(ObjectId id) throws Exception;


}
