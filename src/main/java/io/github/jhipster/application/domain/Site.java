package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Site.
 */
@Entity
@Table(name = "site")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Site implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "code_postal")
    private String codePostal;

    @Column(name = "ville")
    private String ville;

    @Column(name = "pays")
    private String pays;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "date_creation")
    private Long dateCreation;

    @Column(name = "date_modification")
    private Long dateModification;

    @OneToOne
    @JoinColumn(unique = true)
    private Entreprise entrepriseSiege;

    @OneToMany(mappedBy = "lieuStage")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ConventionStage> conventionStages = new HashSet<>();

    @ManyToOne
    private Entreprise entrepriseSite;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public Site adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public Site codePostal(String codePostal) {
        this.codePostal = codePostal;
        return this;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public Site ville(String ville) {
        this.ville = ville;
        return this;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public Site pays(String pays) {
        this.pays = pays;
        return this;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getTelephone() {
        return telephone;
    }

    public Site telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Long getDateCreation() {
        return dateCreation;
    }

    public Site dateCreation(Long dateCreation) {
        this.dateCreation = dateCreation;
        return this;
    }

    public void setDateCreation(Long dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Long getDateModification() {
        return dateModification;
    }

    public Site dateModification(Long dateModification) {
        this.dateModification = dateModification;
        return this;
    }

    public void setDateModification(Long dateModification) {
        this.dateModification = dateModification;
    }

    public Entreprise getEntrepriseSiege() {
        return entrepriseSiege;
    }

    public Site entrepriseSiege(Entreprise entreprise) {
        this.entrepriseSiege = entreprise;
        return this;
    }

    public void setEntrepriseSiege(Entreprise entreprise) {
        this.entrepriseSiege = entreprise;
    }

    public Set<ConventionStage> getConventionStages() {
        return conventionStages;
    }

    public Site conventionStages(Set<ConventionStage> conventionStages) {
        this.conventionStages = conventionStages;
        return this;
    }

    public Site addConventionStage(ConventionStage conventionStage) {
        this.conventionStages.add(conventionStage);
        conventionStage.setLieuStage(this);
        return this;
    }

    public Site removeConventionStage(ConventionStage conventionStage) {
        this.conventionStages.remove(conventionStage);
        conventionStage.setLieuStage(null);
        return this;
    }

    public void setConventionStages(Set<ConventionStage> conventionStages) {
        this.conventionStages = conventionStages;
    }

    public Entreprise getEntrepriseSite() {
        return entrepriseSite;
    }

    public Site entrepriseSite(Entreprise entreprise) {
        this.entrepriseSite = entreprise;
        return this;
    }

    public void setEntrepriseSite(Entreprise entreprise) {
        this.entrepriseSite = entreprise;
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
        Site site = (Site) o;
        if (site.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), site.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Site{" +
            "id=" + getId() +
            ", adresse='" + getAdresse() + "'" +
            ", codePostal='" + getCodePostal() + "'" +
            ", ville='" + getVille() + "'" +
            ", pays='" + getPays() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", dateCreation='" + getDateCreation() + "'" +
            ", dateModification='" + getDateModification() + "'" +
            "}";
    }
}
