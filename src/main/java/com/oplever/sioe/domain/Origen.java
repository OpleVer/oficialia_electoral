package com.oplever.sioe.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;

import com.oplever.sioe.domain.enumeration.Tododistrito;

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

    @Field("disrito")
    private Tododistrito disrito;

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

    public Tododistrito getDisrito() {
        return disrito;
    }

    public Origen disrito(Tododistrito disrito) {
        this.disrito = disrito;
        return this;
    }

    public void setDisrito(Tododistrito disrito) {
        this.disrito = disrito;
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
            ", disrito='" + disrito + "'" +
            '}';
    }
}
