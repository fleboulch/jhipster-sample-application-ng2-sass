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
 * A Diplome.
 */
@Entity
@Table(name = "diplome")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Diplome implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "date_creation")
    private Long dateCreation;

    @Column(name = "date_modification")
    private Long dateModification;

    @OneToMany(mappedBy = "diplome")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Filiere> filieres = new HashSet<>();

    @ManyToMany(mappedBy = "diplomes")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Partenariat> partenariats = new HashSet<>();

    @ManyToMany(mappedBy = "diplomes")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Professionnel> intervenants = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Diplome nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getDateCreation() {
        return dateCreation;
    }

    public Diplome dateCreation(Long dateCreation) {
        this.dateCreation = dateCreation;
        return this;
    }

    public void setDateCreation(Long dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Long getDateModification() {
        return dateModification;
    }

    public Diplome dateModification(Long dateModification) {
        this.dateModification = dateModification;
        return this;
    }

    public void setDateModification(Long dateModification) {
        this.dateModification = dateModification;
    }

    public Set<Filiere> getFilieres() {
        return filieres;
    }

    public Diplome filieres(Set<Filiere> filieres) {
        this.filieres = filieres;
        return this;
    }

    public Diplome addFiliere(Filiere filiere) {
        this.filieres.add(filiere);
        filiere.setDiplome(this);
        return this;
    }

    public Diplome removeFiliere(Filiere filiere) {
        this.filieres.remove(filiere);
        filiere.setDiplome(null);
        return this;
    }

    public void setFilieres(Set<Filiere> filieres) {
        this.filieres = filieres;
    }

    public Set<Partenariat> getPartenariats() {
        return partenariats;
    }

    public Diplome partenariats(Set<Partenariat> partenariats) {
        this.partenariats = partenariats;
        return this;
    }

    public Diplome addPartenariat(Partenariat partenariat) {
        this.partenariats.add(partenariat);
        partenariat.getDiplomes().add(this);
        return this;
    }

    public Diplome removePartenariat(Partenariat partenariat) {
        this.partenariats.remove(partenariat);
        partenariat.getDiplomes().remove(this);
        return this;
    }

    public void setPartenariats(Set<Partenariat> partenariats) {
        this.partenariats = partenariats;
    }

    public Set<Professionnel> getIntervenants() {
        return intervenants;
    }

    public Diplome intervenants(Set<Professionnel> professionnels) {
        this.intervenants = professionnels;
        return this;
    }

    public Diplome addIntervenant(Professionnel professionnel) {
        this.intervenants.add(professionnel);
        professionnel.getDiplomes().add(this);
        return this;
    }

    public Diplome removeIntervenant(Professionnel professionnel) {
        this.intervenants.remove(professionnel);
        professionnel.getDiplomes().remove(this);
        return this;
    }

    public void setIntervenants(Set<Professionnel> professionnels) {
        this.intervenants = professionnels;
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
        Diplome diplome = (Diplome) o;
        if (diplome.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), diplome.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Diplome{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", dateCreation='" + getDateCreation() + "'" +
            ", dateModification='" + getDateModification() + "'" +
            "}";
    }
}
