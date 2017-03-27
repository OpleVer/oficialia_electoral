package com.oplever.sioe.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Anexos.
 */

@Document(collection = "anexos")
public class Anexos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("descripcion")
    private String descripcion;

    @Field("archivo")
    private String archivo;

    @Field("id_numero_solicitud")
    private String id_numero_solicitud;

    @Field("id_procede")
    private String id_procede;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Anexos descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getArchivo() {
        return archivo;
    }

    public Anexos archivo(String archivo) {
        this.archivo = archivo;
        return this;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getId_numero_solicitud() {
        return id_numero_solicitud;
    }

    public Anexos id_numero_solicitud(String id_numero_solicitud) {
        this.id_numero_solicitud = id_numero_solicitud;
        return this;
    }

    public void setId_numero_solicitud(String id_numero_solicitud) {
        this.id_numero_solicitud = id_numero_solicitud;
    }

    public String getId_procede() {
        return id_procede;
    }

    public Anexos id_procede(String id_procede) {
        this.id_procede = id_procede;
        return this;
    }

    public void setId_procede(String id_procede) {
        this.id_procede = id_procede;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Anexos anexos = (Anexos) o;
        if (anexos.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, anexos.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Anexos{" +
            "id=" + id +
            ", descripcion='" + descripcion + "'" +
            ", archivo='" + archivo + "'" +
            ", id_numero_solicitud='" + id_numero_solicitud + "'" +
            ", id_procede='" + id_procede + "'" +
            '}';
    }
}
