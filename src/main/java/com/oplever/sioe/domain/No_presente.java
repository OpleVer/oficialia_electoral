package com.oplever.sioe.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;

/**
 * A No_presente.
 */

@Document(collection = "no_presente")
public class No_presente implements Serializable {

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

    public No_presente id_numero_solicitud(String id_numero_solicitud) {
        this.id_numero_solicitud = id_numero_solicitud;
        return this;
    }

    public void setId_numero_solicitud(String id_numero_solicitud) {
        this.id_numero_solicitud = id_numero_solicitud;
    }

    public byte[] getAcuerdo() {
        return acuerdo;
    }

    public No_presente acuerdo(byte[] acuerdo) {
        this.acuerdo = acuerdo;
        return this;
    }

    public void setAcuerdo(byte[] acuerdo) {
        this.acuerdo = acuerdo;
    }

    public String getAcuerdoContentType() {
        return acuerdoContentType;
    }

    public No_presente acuerdoContentType(String acuerdoContentType) {
        this.acuerdoContentType = acuerdoContentType;
        return this;
    }

    public void setAcuerdoContentType(String acuerdoContentType) {
        this.acuerdoContentType = acuerdoContentType;
    }

    public String getNum_acuerdo() {
        return num_acuerdo;
    }

    public No_presente num_acuerdo(String num_acuerdo) {
        this.num_acuerdo = num_acuerdo;
        return this;
    }

    public void setNum_acuerdo(String num_acuerdo) {
        this.num_acuerdo = num_acuerdo;
    }

    public byte[] getNotificacion() {
        return notificacion;
    }

    public No_presente notificacion(byte[] notificacion) {
        this.notificacion = notificacion;
        return this;
    }

    public void setNotificacion(byte[] notificacion) {
        this.notificacion = notificacion;
    }

    public String getNotificacionContentType() {
        return notificacionContentType;
    }

    public No_presente notificacionContentType(String notificacionContentType) {
        this.notificacionContentType = notificacionContentType;
        return this;
    }

    public void setNotificacionContentType(String notificacionContentType) {
        this.notificacionContentType = notificacionContentType;
    }

    public String getNum_notificacion() {
        return num_notificacion;
    }

    public No_presente num_notificacion(String num_notificacion) {
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
        No_presente no_presente = (No_presente) o;
        if (no_presente.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, no_presente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "No_presente{" +
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
