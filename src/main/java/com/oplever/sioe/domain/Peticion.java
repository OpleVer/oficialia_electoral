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
    @Field("numero_peticion")
    private String numero_peticion;

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

    @Field("oficio_2")
    private byte[] oficio2;

    @Field("oficio_2_content_type")
    private String oficio2ContentType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumero_peticion() {
        return numero_peticion;
    }

    public Peticion numero_peticion(String numero_peticion) {
        this.numero_peticion = numero_peticion;
        return this;
    }

    public void setNumero_peticion(String numero_peticion) {
        this.numero_peticion = numero_peticion;
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

    public byte[] getOficio2() {
        return oficio2;
    }

    public Peticion oficio2(byte[] oficio2) {
        this.oficio2 = oficio2;
        return this;
    }

    public void setOficio2(byte[] oficio2) {
        this.oficio2 = oficio2;
    }

    public String getOficio2ContentType() {
        return oficio2ContentType;
    }

    public Peticion oficio2ContentType(String oficio2ContentType) {
        this.oficio2ContentType = oficio2ContentType;
        return this;
    }

    public void setOficio2ContentType(String oficio2ContentType) {
        this.oficio2ContentType = oficio2ContentType;
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
            ", numero_peticion='" + numero_peticion + "'" +
            ", nomsolicitante='" + nomsolicitante + "'" +
            ", paternosolicitante='" + paternosolicitante + "'" +
            ", maternosolicitante='" + maternosolicitante + "'" +
            ", cargosolicitante='" + cargosolicitante + "'" +
            ", direccionsolicitante='" + direccionsolicitante + "'" +
            ", fechayhora='" + fechayhora + "'" +
            ", actocertificar='" + actocertificar + "'" +
            ", oficio='" + oficio + "'" +
            ", oficioContentType='" + oficioContentType + "'" +
            ", oficio2='" + oficio2 + "'" +
            ", oficio2ContentType='" + oficio2ContentType + "'" +
            '}';
    }
}
