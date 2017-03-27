package com.oplever.sioe.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Origen.
 */

@Document(collection = "origen")
public class Origen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("zona")
    private String zona;

    @Field("distrito")
    private String distrito;

    @Field("municipio")
    private String municipio;

    @Field("id_origen")
    private String id_origen;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZona() {
        return zona;
    }

    public Origen zona(String zona) {
        this.zona = zona;
        return this;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getDistrito() {
        return distrito;
    }

    public Origen distrito(String distrito) {
        this.distrito = distrito;
        return this;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getMunicipio() {
        return municipio;
    }

    public Origen municipio(String municipio) {
        this.municipio = municipio;
        return this;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getId_origen() {
        return id_origen;
    }

    public Origen id_origen(String id_origen) {
        this.id_origen = id_origen;
        return this;
    }

    public void setId_origen(String id_origen) {
        this.id_origen = id_origen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Origen origen = (Origen) o;
        if (origen.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, origen.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Origen{" +
            "id=" + id +
            ", zona='" + zona + "'" +
            ", distrito='" + distrito + "'" +
            ", municipio='" + municipio + "'" +
            ", id_origen='" + id_origen + "'" +
            '}';
    }
}
