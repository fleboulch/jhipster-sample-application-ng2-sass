package io.github.jhipster.application.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Taxe entity.
 */
public class TaxeDTO implements Serializable {

    private Long id;

    private Double montant;

    private ZonedDateTime annee;

    private Long entrepriseId;

    private String entrepriseNom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public ZonedDateTime getAnnee() {
        return annee;
    }

    public void setAnnee(ZonedDateTime annee) {
        this.annee = annee;
    }

    public Long getEntrepriseId() {
        return entrepriseId;
    }

    public void setEntrepriseId(Long entrepriseId) {
        this.entrepriseId = entrepriseId;
    }

    public String getEntrepriseNom() {
        return entrepriseNom;
    }

    public void setEntrepriseNom(String entrepriseNom) {
        this.entrepriseNom = entrepriseNom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TaxeDTO taxeDTO = (TaxeDTO) o;
        if(taxeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), taxeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TaxeDTO{" +
            "id=" + getId() +
            ", montant='" + getMontant() + "'" +
            ", annee='" + getAnnee() + "'" +
            "}";
    }
}
