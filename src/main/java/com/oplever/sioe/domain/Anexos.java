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

    @Field("archivoanexo")
    private byte[] archivoanexo;

    @Field("archivoanexo_content_type")
    private String archivoanexoContentType;

    @Field("descripcion")
    private String descripcion;

    @Field("numero_peticion")
    private String numero_peticion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getArchivoanexo() {
        return archivoanexo;
    }

    public Anexos archivoanexo(byte[] archivoanexo) {
        this.archivoanexo = archivoanexo;
        return this;
    }

    public void setArchivoanexo(byte[] archivoanexo) {
        this.archivoanexo = archivoanexo;
    }

    public String getArchivoanexoContentType() {
        return archivoanexoContentType;
    }

    public Anexos archivoanexoContentType(String archivoanexoContentType) {
        this.archivoanexoContentType = archivoanexoContentType;
        return this;
    }

    public void setArchivoanexoContentType(String archivoanexoContentType) {
        this.archivoanexoContentType = archivoanexoContentType;
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

    public String getNumero_peticion() {
        return numero_peticion;
    }

    public Anexos numero_peticion(String numero_peticion) {
        this.numero_peticion = numero_peticion;
        return this;
    }

    public void setNumero_peticion(String numero_peticion) {
        this.numero_peticion = numero_peticion;
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
            ", archivoanexo='" + archivoanexo + "'" +
            ", archivoanexoContentType='" + archivoanexoContentType + "'" +
            ", descripcion='" + descripcion + "'" +
            ", numero_peticion='" + numero_peticion + "'" +
            '}';
    }
}
