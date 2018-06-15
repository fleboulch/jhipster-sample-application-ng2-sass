package io.github.jhipster.application.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Professionnel entity.
 */
public class ProfessionnelDTO implements Serializable {

    private Long id;

    private String nom;

    private String prenom;

    private String telephone;

    private String mail;

    private String fonction;

    private Boolean ancienEtudiant;

    private Long dateCreation;

    private Long dateModification;

    private Long entrepriseContactId;

    private Set<DiplomeDTO> diplomes = new HashSet<>();

    private Long entreprisePersonnelId;

    private String entreprisePersonnelNom;

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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public Boolean isAncienEtudiant() {
        return ancienEtudiant;
    }

    public void setAncienEtudiant(Boolean ancienEtudiant) {
        this.ancienEtudiant = ancienEtudiant;
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

    public Long getEntrepriseContactId() {
        return entrepriseContactId;
    }

    public void setEntrepriseContactId(Long entrepriseId) {
        this.entrepriseContactId = entrepriseId;
    }

    public Set<DiplomeDTO> getDiplomes() {
        return diplomes;
    }

    public void setDiplomes(Set<DiplomeDTO> diplomes) {
        this.diplomes = diplomes;
    }

    public Long getEntreprisePersonnelId() {
        return entreprisePersonnelId;
    }

    public void setEntreprisePersonnelId(Long entrepriseId) {
        this.entreprisePersonnelId = entrepriseId;
    }

    public String getEntreprisePersonnelNom() {
        return entreprisePersonnelNom;
    }

    public void setEntreprisePersonnelNom(String entrepriseNom) {
        this.entreprisePersonnelNom = entrepriseNom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProfessionnelDTO professionnelDTO = (ProfessionnelDTO) o;
        if(professionnelDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), professionnelDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProfessionnelDTO{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", mail='" + getMail() + "'" +
            ", fonction='" + getFonction() + "'" +
            ", ancienEtudiant='" + isAncienEtudiant() + "'" +
            ", dateCreation='" + getDateCreation() + "'" +
            ", dateModification='" + getDateModification() + "'" +
            "}";
    }
}
