package com.oplever.sioe.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Peticionario.
 */

@Document(collection = "peticionario")
public class Peticionario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Peticionario peticionario = (Peticionario) o;
        if (peticionario.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, peticionario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Peticionario{" +
            "id=" + id +
            '}';
    }
}
