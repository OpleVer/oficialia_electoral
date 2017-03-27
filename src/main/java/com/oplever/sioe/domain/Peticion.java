package com.oplever.sioe.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Peticion.
 */

@Document(collection = "peticion")
public class Peticion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("nomsolicitante")
    private String nomsolicitante;

    @NotNull
    @Field("paternosolicitante")
    private String paternosolicitante;

    @NotNull
    @Field("maternosolicitante")
    private String maternosolicitante;

    @Field("cargosolicitante")
    private String cargosolicitante;

    @Field("direccionsolicitante")
    private String direccionsolicitante;

    @NotNull
    @Field("fechayhora")
    private ZonedDateTime fechayhora;

    @NotNull
    @Field("actocertificar")
    private String actocertificar;

    @Field("oficio")
    private byte[] oficio;

    @Field("oficio_content_type")
    private String oficioContentType;

    @Field("nom_reps_solicitud")
    private String nom_reps_solicitud;

    @NotNull
    @Field("id_numero_solicitud")
    private String id_numero_solicitud;

    @Field("id_origen")
    private String id_origen;

    @Field("oficio_prevencion")
    private byte[] oficio_prevencion;

    @Field("oficio_prevencion_content_type")
    private String oficio_prevencionContentType;

    @Field("num_oficio_prevencion")
    private String num_oficio_prevencion;

    @Field("notificacion_prevencion")
    private byte[] notificacion_prevencion;

    @Field("notificacion_prevencion_content_type")
    private String notificacion_prevencionContentType;

    @Field("num_notificacion_prevencion")
    private String num_notificacion_prevencion;

    @Field("respuesta_prevencion")
    private byte[] respuesta_prevencion;

    @Field("respuesta_prevencion_content_type")
    private String respuesta_prevencionContentType;

    @Field("num_respuesta_prevencion")
    private String num_respuesta_prevencion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomsolicitante() {
        return nomsolicitante;
    }

    public Peticion nomsolicitante(String nomsolicitante) {
        this.nomsolicitante = nomsolicitante;
        return this;
    }

    public void setNomsolicitante(String nomsolicitante) {
        this.nomsolicitante = nomsolicitante;
    }

    public String getPaternosolicitante() {
        return paternosolicitante;
    }

    public Peticion paternosolicitante(String paternosolicitante) {
        this.paternosolicitante = paternosolicitante;
        return this;
    }

    public void setPaternosolicitante(String paternosolicitante) {
        this.paternosolicitante = paternosolicitante;
    }

    public String getMaternosolicitante() {
        return maternosolicitante;
    }

    public Peticion maternosolicitante(String maternosolicitante) {
        this.maternosolicitante = maternosolicitante;
        return this;
    }

    public void setMaternosolicitante(String maternosolicitante) {
        this.maternosolicitante = maternosolicitante;
    }

    public String getCargosolicitante() {
        return cargosolicitante;
    }

    public Peticion cargosolicitante(String cargosolicitante) {
        this.cargosolicitante = cargosolicitante;
        return this;
    }

    public void setCargosolicitante(String cargosolicitante) {
        this.cargosolicitante = cargosolicitante;
    }

    public String getDireccionsolicitante() {
        return direccionsolicitante;
    }

    public Peticion direccionsolicitante(String direccionsolicitante) {
        this.direccionsolicitante = direccionsolicitante;
        return this;
    }

    public void setDireccionsolicitante(String direccionsolicitante) {
        this.direccionsolicitante = direccionsolicitante;
    }

    public ZonedDateTime getFechayhora() {
        return fechayhora;
    }

    public Peticion fechayhora(ZonedDateTime fechayhora) {
        this.fechayhora = fechayhora;
        return this;
    }

    public void setFechayhora(ZonedDateTime fechayhora) {
        this.fechayhora = fechayhora;
    }

    public String getActocertificar() {
        return actocertificar;
    }

    public Peticion actocertificar(String actocertificar) {
        this.actocertificar = actocertificar;
        return this;
    }

    public void setActocertificar(String actocertificar) {
        this.actocertificar = actocertificar;
    }

    public byte[] getOficio() {
        return oficio;
    }

    public Peticion oficio(byte[] oficio) {
        this.oficio = oficio;
        return this;
    }

    public void setOficio(byte[] oficio) {
        this.oficio = oficio;
    }

    public String getOficioContentType() {
        return oficioContentType;
    }

    public Peticion oficioContentType(String oficioContentType) {
        this.oficioContentType = oficioContentType;
        return this;
    }

    public void setOficioContentType(String oficioContentType) {
        this.oficioContentType = oficioContentType;
    }

    public String getNom_reps_solicitud() {
        return nom_reps_solicitud;
    }

    public Peticion nom_reps_solicitud(String nom_reps_solicitud) {
        this.nom_reps_solicitud = nom_reps_solicitud;
        return this;
    }

    public void setNom_reps_solicitud(String nom_reps_solicitud) {
        this.nom_reps_solicitud = nom_reps_solicitud;
    }

    public String getId_numero_solicitud() {
        return id_numero_solicitud;
    }

    public Peticion id_numero_solicitud(String id_numero_solicitud) {
        this.id_numero_solicitud = id_numero_solicitud;
        return this;
    }

    public void setId_numero_solicitud(String id_numero_solicitud) {
        this.id_numero_solicitud = id_numero_solicitud;
    }

    public String getId_origen() {
        return id_origen;
    }

    public Peticion id_origen(String id_origen) {
        this.id_origen = id_origen;
        return this;
    }

    public void setId_origen(String id_origen) {
        this.id_origen = id_origen;
    }

    public byte[] getOficio_prevencion() {
        return oficio_prevencion;
    }

    public Peticion oficio_prevencion(byte[] oficio_prevencion) {
        this.oficio_prevencion = oficio_prevencion;
        return this;
    }

    public void setOficio_prevencion(byte[] oficio_prevencion) {
        this.oficio_prevencion = oficio_prevencion;
    }

    public String getOficio_prevencionContentType() {
        return oficio_prevencionContentType;
    }

    public Peticion oficio_prevencionContentType(String oficio_prevencionContentType) {
        this.oficio_prevencionContentType = oficio_prevencionContentType;
        return this;
    }

    public void setOficio_prevencionContentType(String oficio_prevencionContentType) {
        this.oficio_prevencionContentType = oficio_prevencionContentType;
    }

    public String getNum_oficio_prevencion() {
        return num_oficio_prevencion;
    }

    public Peticion num_oficio_prevencion(String num_oficio_prevencion) {
        this.num_oficio_prevencion = num_oficio_prevencion;
        return this;
    }

    public void setNum_oficio_prevencion(String num_oficio_prevencion) {
        this.num_oficio_prevencion = num_oficio_prevencion;
    }

    public byte[] getNotificacion_prevencion() {
        return notificacion_prevencion;
    }

    public Peticion notificacion_prevencion(byte[] notificacion_prevencion) {
        this.notificacion_prevencion = notificacion_prevencion;
        return this;
    }

    public void setNotificacion_prevencion(byte[] notificacion_prevencion) {
        this.notificacion_prevencion = notificacion_prevencion;
    }

    public String getNotificacion_prevencionContentType() {
        return notificacion_prevencionContentType;
    }

    public Peticion notificacion_prevencionContentType(String notificacion_prevencionContentType) {
        this.notificacion_prevencionContentType = notificacion_prevencionContentType;
        return this;
    }

    public void setNotificacion_prevencionContentType(String notificacion_prevencionContentType) {
        this.notificacion_prevencionContentType = notificacion_prevencionContentType;
    }

    public String getNum_notificacion_prevencion() {
        return num_notificacion_prevencion;
    }

    public Peticion num_notificacion_prevencion(String num_notificacion_prevencion) {
        this.num_notificacion_prevencion = num_notificacion_prevencion;
        return this;
    }

    public void setNum_notificacion_prevencion(String num_notificacion_prevencion) {
        this.num_notificacion_prevencion = num_notificacion_prevencion;
    }

    public byte[] getRespuesta_prevencion() {
        return respuesta_prevencion;
    }

    public Peticion respuesta_prevencion(byte[] respuesta_prevencion) {
        this.respuesta_prevencion = respuesta_prevencion;
        return this;
    }

    public void setRespuesta_prevencion(byte[] respuesta_prevencion) {
        this.respuesta_prevencion = respuesta_prevencion;
    }

    public String getRespuesta_prevencionContentType() {
        return respuesta_prevencionContentType;
    }

    public Peticion respuesta_prevencionContentType(String respuesta_prevencionContentType) {
        this.respuesta_prevencionContentType = respuesta_prevencionContentType;
        return this;
    }

    public void setRespuesta_prevencionContentType(String respuesta_prevencionContentType) {
        this.respuesta_prevencionContentType = respuesta_prevencionContentType;
    }

    public String getNum_respuesta_prevencion() {
        return num_respuesta_prevencion;
    }

    public Peticion num_respuesta_prevencion(String num_respuesta_prevencion) {
        this.num_respuesta_prevencion = num_respuesta_prevencion;
        return this;
    }

    public void setNum_respuesta_prevencion(String num_respuesta_prevencion) {
        this.num_respuesta_prevencion = num_respuesta_prevencion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Peticion peticion = (Peticion) o;
        if (peticion.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, peticion.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Peticion{" +
            "id=" + id +
            ", nomsolicitante='" + nomsolicitante + "'" +
            ", paternosolicitante='" + paternosolicitante + "'" +
            ", maternosolicitante='" + maternosolicitante + "'" +
            ", cargosolicitante='" + cargosolicitante + "'" +
            ", direccionsolicitante='" + direccionsolicitante + "'" +
            ", fechayhora='" + fechayhora + "'" +
            ", actocertificar='" + actocertificar + "'" +
            ", oficio='" + oficio + "'" +
            ", oficioContentType='" + oficioContentType + "'" +
            ", nom_reps_solicitud='" + nom_reps_solicitud + "'" +
            ", id_numero_solicitud='" + id_numero_solicitud + "'" +
            ", id_origen='" + id_origen + "'" +
            ", oficio_prevencion='" + oficio_prevencion + "'" +
            ", oficio_prevencionContentType='" + oficio_prevencionContentType + "'" +
            ", num_oficio_prevencion='" + num_oficio_prevencion + "'" +
            ", notificacion_prevencion='" + notificacion_prevencion + "'" +
            ", notificacion_prevencionContentType='" + notificacion_prevencionContentType + "'" +
            ", num_notificacion_prevencion='" + num_notificacion_prevencion + "'" +
            ", respuesta_prevencion='" + respuesta_prevencion + "'" +
            ", respuesta_prevencionContentType='" + respuesta_prevencionContentType + "'" +
            ", num_respuesta_prevencion='" + num_respuesta_prevencion + "'" +
            '}';
    }
}
