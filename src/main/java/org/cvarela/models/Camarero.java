package org.cvarela.models;

import org.bson.types.ObjectId;

public class Camarero {

    private ObjectId id;
    private String nombre;
    private String apellido;

    public Camarero(){}

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
