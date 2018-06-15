package io.github.jhipster.application.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Filiere entity.
 */
public class FiliereDTO implements Serializable {

    private Long id;

    @NotNull
    private String niveau;

    private Long diplomeId;

    private String diplomeNom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public Long getDiplomeId() {
        return diplomeId;
    }

    public void setDiplomeId(Long diplomeId) {
        this.diplomeId = diplomeId;
    }

    public String getDiplomeNom() {
        return diplomeNom;
    }

    public void setDiplomeNom(String diplomeNom) {
        this.diplomeNom = diplomeNom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FiliereDTO filiereDTO = (FiliereDTO) o;
        if(filiereDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), filiereDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FiliereDTO{" +
            "id=" + getId() +
            ", niveau='" + getNiveau() + "'" +
            "}";
    }
}
