package io.github.jhipster.application.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Diplome entity.
 */
public class DiplomeDTO implements Serializable {

    private Long id;

    @NotNull
    private String nom;

    private Long dateCreation;

    private Long dateModification;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Long dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Long getDateModification() {
        return dateModification;
    }

    public void setDateModification(Long dateModification) {
        this.dateModification = dateModification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DiplomeDTO diplomeDTO = (DiplomeDTO) o;
        if(diplomeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), diplomeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DiplomeDTO{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", dateCreation='" + getDateCreation() + "'" +
            ", dateModification='" + getDateModification() + "'" +
            "}";
    }
}
