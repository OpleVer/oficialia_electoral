package com.oplever.sioe.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;

/**
 * A No_procede.
 */

@Document(collection = "no_procede")
public class No_procede implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("id_numero_solicitud")
    private String id_numero_solicitud;

    @Field("acuerdo")
    private byte[] acuerdo;

    @Field("acuerdo_content_type")
    private String acuerdoContentType;

    @Field("num_acuerdo")
    private String num_acuerdo;

    @Field("notificacion")
    private byte[] notificacion;

    @Field("notificacion_content_type")
    private String notificacionContentType;

    @Field("num_notificacion")
    private String num_notificacion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_numero_solicitud() {
        return id_numero_solicitud;
    }

    public No_procede id_numero_solicitud(String id_numero_solicitud) {
        this.id_numero_solicitud = id_numero_solicitud;
        return this;
    }

    public void setId_numero_solicitud(String id_numero_solicitud) {
        this.id_numero_solicitud = id_numero_solicitud;
    }

    public byte[] getAcuerdo() {
        return acuerdo;
    }

    public No_procede acuerdo(byte[] acuerdo) {
        this.acuerdo = acuerdo;
        return this;
    }

    public void setAcuerdo(byte[] acuerdo) {
        this.acuerdo = acuerdo;
    }

    public String getAcuerdoContentType() {
        return acuerdoContentType;
    }

    public No_procede acuerdoContentType(String acuerdoContentType) {
        this.acuerdoContentType = acuerdoContentType;
        return this;
    }

    public void setAcuerdoContentType(String acuerdoContentType) {
        this.acuerdoContentType = acuerdoContentType;
    }

    public String getNum_acuerdo() {
        return num_acuerdo;
    }

    public No_procede num_acuerdo(String num_acuerdo) {
        this.num_acuerdo = num_acuerdo;
        return this;
    }

    public void setNum_acuerdo(String num_acuerdo) {
        this.num_acuerdo = num_acuerdo;
    }

    public byte[] getNotificacion() {
        return notificacion;
    }

    public No_procede notificacion(byte[] notificacion) {
        this.notificacion = notificacion;
        return this;
    }

    public void setNotificacion(byte[] notificacion) {
        this.notificacion = notificacion;
    }

    public String getNotificacionContentType() {
        return notificacionContentType;
    }

    public No_procede notificacionContentType(String notificacionContentType) {
        this.notificacionContentType = notificacionContentType;
        return this;
    }

    public void setNotificacionContentType(String notificacionContentType) {
        this.notificacionContentType = notificacionContentType;
    }

    public String getNum_notificacion() {
        return num_notificacion;
    }

    public No_procede num_notificacion(String num_notificacion) {
        this.num_notificacion = num_notificacion;
        return this;
    }

    public void setNum_notificacion(String num_notificacion) {
        this.num_notificacion = num_notificacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        No_procede no_procede = (No_procede) o;
        if (no_procede.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, no_procede.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "No_procede{" +
            "id=" + id +
            ", id_numero_solicitud='" + id_numero_solicitud + "'" +
            ", acuerdo='" + acuerdo + "'" +
            ", acuerdoContentType='" + acuerdoContentType + "'" +
            ", num_acuerdo='" + num_acuerdo + "'" +
            ", notificacion='" + notificacion + "'" +
            ", notificacionContentType='" + notificacionContentType + "'" +
            ", num_notificacion='" + num_notificacion + "'" +
            '}';
    }
}
