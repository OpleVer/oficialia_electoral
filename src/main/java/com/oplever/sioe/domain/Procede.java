package com.oplever.sioe.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Procede.
 */

@Document(collection = "procede")
public class Procede implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("acta")
    private byte[] acta;

    @Field("acta_content_type")
    private String actaContentType;

    @Field("acuerdo")
    private byte[] acuerdo;

    @Field("acuerdo_content_type")
    private String acuerdoContentType;

    @Field("id_procede")
    private String id_procede;

    @Field("notificacion")
    private byte[] notificacion;

    @Field("notificacion_content_type")
    private String notificacionContentType;

    @Field("id_numero_solicitud")
    private String id_numero_solicitud;

    @Field("num_acta")
    private String num_acta;

    @Field("num_acuerdo")
    private String num_acuerdo;

    @Field("num_notificacion")
    private String num_notificacion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getActa() {
        return acta;
    }

    public Procede acta(byte[] acta) {
        this.acta = acta;
        return this;
    }

    public void setActa(byte[] acta) {
        this.acta = acta;
    }

    public String getActaContentType() {
        return actaContentType;
    }

    public Procede actaContentType(String actaContentType) {
        this.actaContentType = actaContentType;
        return this;
    }

    public void setActaContentType(String actaContentType) {
        this.actaContentType = actaContentType;
    }

    public byte[] getAcuerdo() {
        return acuerdo;
    }

    public Procede acuerdo(byte[] acuerdo) {
        this.acuerdo = acuerdo;
        return this;
    }

    public void setAcuerdo(byte[] acuerdo) {
        this.acuerdo = acuerdo;
    }

    public String getAcuerdoContentType() {
        return acuerdoContentType;
    }

    public Procede acuerdoContentType(String acuerdoContentType) {
        this.acuerdoContentType = acuerdoContentType;
        return this;
    }

    public void setAcuerdoContentType(String acuerdoContentType) {
        this.acuerdoContentType = acuerdoContentType;
    }

    public String getId_procede() {
        return id_procede;
    }

    public Procede id_procede(String id_procede) {
        this.id_procede = id_procede;
        return this;
    }

    public void setId_procede(String id_procede) {
        this.id_procede = id_procede;
    }

    public byte[] getNotificacion() {
        return notificacion;
    }

    public Procede notificacion(byte[] notificacion) {
        this.notificacion = notificacion;
        return this;
    }

    public void setNotificacion(byte[] notificacion) {
        this.notificacion = notificacion;
    }

    public String getNotificacionContentType() {
        return notificacionContentType;
    }

    public Procede notificacionContentType(String notificacionContentType) {
        this.notificacionContentType = notificacionContentType;
        return this;
    }

    public void setNotificacionContentType(String notificacionContentType) {
        this.notificacionContentType = notificacionContentType;
    }

    public String getId_numero_solicitud() {
        return id_numero_solicitud;
    }

    public Procede id_numero_solicitud(String id_numero_solicitud) {
        this.id_numero_solicitud = id_numero_solicitud;
        return this;
    }

    public void setId_numero_solicitud(String id_numero_solicitud) {
        this.id_numero_solicitud = id_numero_solicitud;
    }

    public String getNum_acta() {
        return num_acta;
    }

    public Procede num_acta(String num_acta) {
        this.num_acta = num_acta;
        return this;
    }

    public void setNum_acta(String num_acta) {
        this.num_acta = num_acta;
    }

    public String getNum_acuerdo() {
        return num_acuerdo;
    }

    public Procede num_acuerdo(String num_acuerdo) {
        this.num_acuerdo = num_acuerdo;
        return this;
    }

    public void setNum_acuerdo(String num_acuerdo) {
        this.num_acuerdo = num_acuerdo;
    }

    public String getNum_notificacion() {
        return num_notificacion;
    }

    public Procede num_notificacion(String num_notificacion) {
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
        Procede procede = (Procede) o;
        if (procede.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, procede.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Procede{" +
            "id=" + id +
            ", acta='" + acta + "'" +
            ", actaContentType='" + actaContentType + "'" +
            ", acuerdo='" + acuerdo + "'" +
            ", acuerdoContentType='" + acuerdoContentType + "'" +
            ", id_procede='" + id_procede + "'" +
            ", notificacion='" + notificacion + "'" +
            ", notificacionContentType='" + notificacionContentType + "'" +
            ", id_numero_solicitud='" + id_numero_solicitud + "'" +
            ", num_acta='" + num_acta + "'" +
            ", num_acuerdo='" + num_acuerdo + "'" +
            ", num_notificacion='" + num_notificacion + "'" +
            '}';
    }
}
