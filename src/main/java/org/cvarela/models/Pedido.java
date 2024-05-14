package org.cvarela.models;

import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    // Campos del documento mapeados de MongoDB
    private ObjectId id;
    @BsonProperty(value = "grupo_id")
    private ObjectId grupoId;
    @BsonProperty(value = "bar_id")
    private ObjectId barId;
    @BsonProperty(value = "estado_cobro")
    private EstadoCobro estadoCobro;
    @BsonProperty(value = "camarero_id")
    private ObjectId camareroId;
    @BsonProperty("fechas_horas_pedidos")
    private FechaHorasPedidos fechaHorasPedidos;
    private List<Consumicion> consumiciones;
    private Estado estado;

    // Atributos relacionados a través de sus ids
    @BsonIgnore
    private Bar bar;
    @BsonIgnore
    private Camarero camareroResponsable;
    @BsonIgnore
    private Alumno alumno;
    @BsonIgnore
    private Grupo grupo;


    @BsonIgnore
    private float importeTotal;
    @BsonIgnore
    private float importeSatisfecho;
    @BsonIgnore
    private float importeRestante;

    public Pedido() {
        this.estado = estado.EN_COLA;
        this.estadoCobro = estadoCobro.NADA;
        this.fechaHorasPedidos = new FechaHorasPedidos();
        this.consumiciones = new ArrayList<>();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(ObjectId grupoId) {
        this.grupoId = grupoId;
    }

    public ObjectId getBarId() {
        return barId;
    }

    public void setBarId(ObjectId barId) {
        this.barId = barId;
    }

    public EstadoCobro getEstadoCobro() {
        return estadoCobro;
    }

    public void setEstadoCobro(EstadoCobro estadoCobro) {
        this.estadoCobro = estadoCobro;
    }

    public ObjectId getCamareroId() {
        return camareroId;
    }

    public void setCamareroId(ObjectId camareroId) {
        this.camareroId = camareroId;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public FechaHorasPedidos getFechaHorasPedidos() {
        return fechaHorasPedidos;
    }

    public void setFechaHorasPedidos(FechaHorasPedidos fechaHorasPedidos) {
        this.fechaHorasPedidos = fechaHorasPedidos;
    }

    public Bar getBar() {
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }

    public Camarero getCamareroResponsable() {
        return camareroResponsable;
    }

    public void setCamareroResponsable(Camarero camareroResponsable) {
        this.camareroResponsable = camareroResponsable;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public List<Consumicion> getConsumiciones() {
        return consumiciones;
    }

    public void setConsumiciones(List<Consumicion> consumiciones) {
        this.consumiciones = consumiciones;
    }

    public float getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(float importeTotal) {
        this.importeTotal = importeTotal;
    }

    public float getImporteSatisfecho() {
        return importeSatisfecho;
    }

    public void setImporteSatisfecho(float importeSatisfecho) {
        this.importeSatisfecho = importeSatisfecho;
    }

    public float getImporteRestante() {
        return importeRestante;
    }

    public void setImporteRestante(float importeRestante) {
        this.importeRestante = importeRestante;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", grupoId=" + grupoId +
                ", barId=" + barId +
                ", estadoCobro=" + estadoCobro +
                ", camareroId=" + camareroId +
                ", consumiciones=" + consumiciones+
                ", estado=" + estado +
                ", fechaHorasPedidos=" + fechaHorasPedidos +
                '}';
    }
}
