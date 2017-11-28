package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Taxe.
 */
@Entity
@Table(name = "taxe")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Taxe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "montant")
    private Double montant;

    @Column(name = "annee")
    private ZonedDateTime annee;

    @ManyToOne
    private Entreprise entreprise;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMontant() {
        return montant;
    }

    public Taxe montant(Double montant) {
        this.montant = montant;
        return this;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public ZonedDateTime getAnnee() {
        return annee;
    }

    public Taxe annee(ZonedDateTime annee) {
        this.annee = annee;
        return this;
    }

    public void setAnnee(ZonedDateTime annee) {
        this.annee = annee;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public Taxe entreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
        return this;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Taxe taxe = (Taxe) o;
        if (taxe.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), taxe.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Taxe{" +
            "id=" + getId() +
            ", montant='" + getMontant() + "'" +
            ", annee='" + getAnnee() + "'" +
            "}";
    }
}
