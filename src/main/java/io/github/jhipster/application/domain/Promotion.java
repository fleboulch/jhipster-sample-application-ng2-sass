package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Promotion.
 */
@Entity
@Table(name = "promotion")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Promotion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "annee", nullable = false)
    private ZonedDateTime annee;

    @ManyToOne
    private Filiere filiere;

    @ManyToMany(mappedBy = "promotions")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Etudiant> etudiants = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getAnnee() {
        return annee;
    }

    public Promotion annee(ZonedDateTime annee) {
        this.annee = annee;
        return this;
    }

    public void setAnnee(ZonedDateTime annee) {
        this.annee = annee;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public Promotion filiere(Filiere filiere) {
        this.filiere = filiere;
        return this;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public Set<Etudiant> getEtudiants() {
        return etudiants;
    }

    public Promotion etudiants(Set<Etudiant> etudiants) {
        this.etudiants = etudiants;
        return this;
    }

    public Promotion addEtudiant(Etudiant etudiant) {
        this.etudiants.add(etudiant);
        etudiant.getPromotions().add(this);
        return this;
    }

    public Promotion removeEtudiant(Etudiant etudiant) {
        this.etudiants.remove(etudiant);
        etudiant.getPromotions().remove(this);
        return this;
    }

    public void setEtudiants(Set<Etudiant> etudiants) {
        this.etudiants = etudiants;
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
        Promotion promotion = (Promotion) o;
        if (promotion.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), promotion.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Promotion{" +
            "id=" + getId() +
            ", annee='" + getAnnee() + "'" +
            "}";
    }
}
