package org.cvarela.models;

public enum EstadoCobro {

    NADA("Nada"),
    PARCIAL("Parcial"),
    COMPLETO("Completo");

    private String estadoCobro;

    EstadoCobro(String estadoCobro){
        this.estadoCobro = estadoCobro;
    }

    public String getEstadoCobro(){
        return estadoCobro;
    }

    public boolean isFinished(EstadoCobro estadoCobro) {
        return estadoCobro == COMPLETO;
    }

}
