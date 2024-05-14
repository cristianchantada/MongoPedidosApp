package org.cvarela.models;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class FechaHorasPedidos {

    private ObjectId id;
    @BsonProperty(value = "fecha_hora_pedido")
    private Date fechaHoraPedido;
    @BsonProperty(value = "fecha_hora_en_proceso")
    private Date fechaHoraEnProceso;
    @BsonProperty(value = "fecha_hora_despachado")
    private Date fechaHoraDespachado;
    @BsonProperty(value = "fecha_hora_cerrado")
    private Date fechaHoraCerrado;
    @BsonProperty(value = "fecha_hora_modificado")
    private Date fechaHoraModificado;
    @BsonProperty(value = "fecha_hora_cancelado")
    private Date fechaHoraCancelado;

    public FechaHorasPedidos() {
        this.fechaHoraPedido = new Date();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public LocalDateTime getFechaHoraPedido() {
        return fechaHoraPedido.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public void setFechaHoraPedido(LocalDateTime fechaHoraPedido) {
        this.fechaHoraPedido = Date.from(fechaHoraPedido.atZone(ZoneId.systemDefault()).toInstant());
    }

    public LocalDateTime getFechaHoraEnProceso() {
        return fechaHoraEnProceso != null ? fechaHoraEnProceso.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime() : null;
    }

    public void setFechaHoraEnProceso(LocalDateTime fechaHoraEnProceso) {
        this.fechaHoraEnProceso = fechaHoraEnProceso != null ? Date.from(fechaHoraEnProceso.atZone(ZoneId.systemDefault()).toInstant()) : null;
    }

    public LocalDateTime getFechaHoraDespachado() {
        return fechaHoraDespachado != null ? fechaHoraDespachado.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime() : null;
    }

    public void setFechaHoraDespachado(LocalDateTime fechaHoraDespachado) {
        this.fechaHoraDespachado = fechaHoraDespachado != null ? Date.from(fechaHoraDespachado.atZone(ZoneId.systemDefault()).toInstant()) : null;
    }

    public LocalDateTime getFechaHoraCerrado() {
        return fechaHoraCerrado != null ? fechaHoraCerrado.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime() : null;
    }

    public void setFechaHoraCerrado(LocalDateTime fechaHoraCerrado) {
        this.fechaHoraCerrado = fechaHoraCerrado != null ? Date.from(fechaHoraCerrado.atZone(ZoneId.systemDefault()).toInstant()) : null;
    }

    public LocalDateTime getFechaHoraModificado() {
        return fechaHoraModificado != null ? fechaHoraModificado.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime() : null;
    }

    public void setFechaHoraModificado(LocalDateTime fechaHoraModificado) {
        this.fechaHoraModificado = fechaHoraModificado != null ? Date.from(fechaHoraModificado.atZone(ZoneId.systemDefault()).toInstant()) : null;
    }

    public LocalDateTime getFechaHoraCancelado() {
        return fechaHoraCancelado != null ? fechaHoraCancelado.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime() : null;
    }

    public void setFechaHoraCancelado(LocalDateTime fechaHoraCancelado) {
        this.fechaHoraCancelado = fechaHoraCancelado != null ? Date.from(fechaHoraCancelado.atZone(ZoneId.systemDefault()).toInstant()) : null;
    }
}
