package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Filiere.
 */
@Entity
@Table(name = "filiere")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Filiere implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "niveau", nullable = false)
    private String niveau;

    @OneToMany(mappedBy = "filiere")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Promotion> promotions = new HashSet<>();

    @ManyToOne
    private Diplome diplome;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNiveau() {
        return niveau;
    }

    public Filiere niveau(String niveau) {
        this.niveau = niveau;
        return this;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public Set<Promotion> getPromotions() {
        return promotions;
    }

    public Filiere promotions(Set<Promotion> promotions) {
        this.promotions = promotions;
        return this;
    }

    public Filiere addPromotion(Promotion promotion) {
        this.promotions.add(promotion);
        promotion.setFiliere(this);
        return this;
    }

    public Filiere removePromotion(Promotion promotion) {
        this.promotions.remove(promotion);
        promotion.setFiliere(null);
        return this;
    }

    public void setPromotions(Set<Promotion> promotions) {
        this.promotions = promotions;
    }

    public Diplome getDiplome() {
        return diplome;
    }

    public Filiere diplome(Diplome diplome) {
        this.diplome = diplome;
        return this;
    }

    public void setDiplome(Diplome diplome) {
        this.diplome = diplome;
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
        Filiere filiere = (Filiere) o;
        if (filiere.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), filiere.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Filiere{" +
            "id=" + getId() +
            ", niveau='" + getNiveau() + "'" +
            "}";
    }
}
