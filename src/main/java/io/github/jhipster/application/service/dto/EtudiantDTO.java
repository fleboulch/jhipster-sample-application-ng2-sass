package io.github.jhipster.application.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import io.github.jhipster.application.domain.enumeration.Sexe;

/**
 * A DTO for the Etudiant entity.
 */
public class EtudiantDTO implements Serializable {

    private Long id;

    private String nom;

    private String prenom;

    private String mail;

    private Sexe sexe;

    @NotNull
    private String numEtudiant;

    private Set<PromotionDTO> promotions = new HashSet<>();

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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public String getNumEtudiant() {
        return numEtudiant;
    }

    public void setNumEtudiant(String numEtudiant) {
        this.numEtudiant = numEtudiant;
    }

    public Set<PromotionDTO> getPromotions() {
        return promotions;
    }

    public void setPromotions(Set<PromotionDTO> promotions) {
        this.promotions = promotions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EtudiantDTO etudiantDTO = (EtudiantDTO) o;
        if(etudiantDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), etudiantDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EtudiantDTO{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", mail='" + getMail() + "'" +
            ", sexe='" + getSexe() + "'" +
            ", numEtudiant='" + getNumEtudiant() + "'" +
            "}";
    }
}
