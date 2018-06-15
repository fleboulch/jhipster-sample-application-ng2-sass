package io.github.jhipster.application.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the ConventionStage entity.
 */
public class ConventionStageDTO implements Serializable {

    private Long id;

    private String sujet;

    private String fonctions;

    private String competences;

    private ZonedDateTime dateDebut;

    private ZonedDateTime dateFin;

    private Long etudiantId;

    private String etudiantNom;

    private Long lieuStageId;

    private String lieuStageAdresse;

    private Long tuteurId;

    private String tuteurNom;

    private Long maitreStageId;

    private String maitreStageNom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getFonctions() {
        return fonctions;
    }

    public void setFonctions(String fonctions) {
        this.fonctions = fonctions;
    }

    public String getCompetences() {
        return competences;
    }

    public void setCompetences(String competences) {
        this.competences = competences;
    }

    public ZonedDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(ZonedDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public ZonedDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(ZonedDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public Long getEtudiantId() {
        return etudiantId;
    }

    public void setEtudiantId(Long etudiantId) {
        this.etudiantId = etudiantId;
    }

    public String getEtudiantNom() {
        return etudiantNom;
    }

    public void setEtudiantNom(String etudiantNom) {
        this.etudiantNom = etudiantNom;
    }

    public Long getLieuStageId() {
        return lieuStageId;
    }

    public void setLieuStageId(Long siteId) {
        this.lieuStageId = siteId;
    }

    public String getLieuStageAdresse() {
        return lieuStageAdresse;
    }

    public void setLieuStageAdresse(String siteAdresse) {
        this.lieuStageAdresse = siteAdresse;
    }

    public Long getTuteurId() {
        return tuteurId;
    }

    public void setTuteurId(Long tuteurId) {
        this.tuteurId = tuteurId;
    }

    public String getTuteurNom() {
        return tuteurNom;
    }

    public void setTuteurNom(String tuteurNom) {
        this.tuteurNom = tuteurNom;
    }

    public Long getMaitreStageId() {
        return maitreStageId;
    }

    public void setMaitreStageId(Long professionnelId) {
        this.maitreStageId = professionnelId;
    }

    public String getMaitreStageNom() {
        return maitreStageNom;
    }

    public void setMaitreStageNom(String professionnelNom) {
        this.maitreStageNom = professionnelNom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ConventionStageDTO conventionStageDTO = (ConventionStageDTO) o;
        if(conventionStageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), conventionStageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ConventionStageDTO{" +
            "id=" + getId() +
            ", sujet='" + getSujet() + "'" +
            ", fonctions='" + getFonctions() + "'" +
            ", competences='" + getCompetences() + "'" +
            ", dateDebut='" + getDateDebut() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            "}";
    }
}
