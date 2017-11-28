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
 * A Professionnel.
 */
@Entity
@Table(name = "professionnel")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Professionnel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "mail")
    private String mail;

    @Column(name = "fonction")
    private String fonction;

    @Column(name = "ancien_etudiant")
    private Boolean ancienEtudiant;

    @Column(name = "date_creation")
    private Long dateCreation;

    @Column(name = "date_modification")
    private Long dateModification;

    @OneToOne
    @JoinColumn(unique = true)
    private Entreprise entrepriseContact;

    @OneToMany(mappedBy = "maitreStage")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ConventionStage> conventionStages = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "professionnel_diplome",
               joinColumns = @JoinColumn(name="professionnels_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="diplomes_id", referencedColumnName="id"))
    private Set<Diplome> diplomes = new HashSet<>();

    @ManyToOne
    private Entreprise entreprisePersonnel;

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

    public Professionnel nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Professionnel prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public Professionnel telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public Professionnel mail(String mail) {
        this.mail = mail;
        return this;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFonction() {
        return fonction;
    }

    public Professionnel fonction(String fonction) {
        this.fonction = fonction;
        return this;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public Boolean isAncienEtudiant() {
        return ancienEtudiant;
    }

    public Professionnel ancienEtudiant(Boolean ancienEtudiant) {
        this.ancienEtudiant = ancienEtudiant;
        return this;
    }

    public void setAncienEtudiant(Boolean ancienEtudiant) {
        this.ancienEtudiant = ancienEtudiant;
    }

    public Long getDateCreation() {
        return dateCreation;
    }

    public Professionnel dateCreation(Long dateCreation) {
        this.dateCreation = dateCreation;
        return this;
    }

    public void setDateCreation(Long dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Long getDateModification() {
        return dateModification;
    }

    public Professionnel dateModification(Long dateModification) {
        this.dateModification = dateModification;
        return this;
    }

    public void setDateModification(Long dateModification) {
        this.dateModification = dateModification;
    }

    public Entreprise getEntrepriseContact() {
        return entrepriseContact;
    }

    public Professionnel entrepriseContact(Entreprise entreprise) {
        this.entrepriseContact = entreprise;
        return this;
    }

    public void setEntrepriseContact(Entreprise entreprise) {
        this.entrepriseContact = entreprise;
    }

    public Set<ConventionStage> getConventionStages() {
        return conventionStages;
    }

    public Professionnel conventionStages(Set<ConventionStage> conventionStages) {
        this.conventionStages = conventionStages;
        return this;
    }

    public Professionnel addConventionStage(ConventionStage conventionStage) {
        this.conventionStages.add(conventionStage);
        conventionStage.setMaitreStage(this);
        return this;
    }

    public Professionnel removeConventionStage(ConventionStage conventionStage) {
        this.conventionStages.remove(conventionStage);
        conventionStage.setMaitreStage(null);
        return this;
    }

    public void setConventionStages(Set<ConventionStage> conventionStages) {
        this.conventionStages = conventionStages;
    }

    public Set<Diplome> getDiplomes() {
        return diplomes;
    }

    public Professionnel diplomes(Set<Diplome> diplomes) {
        this.diplomes = diplomes;
        return this;
    }

    public Professionnel addDiplome(Diplome diplome) {
        this.diplomes.add(diplome);
        diplome.getIntervenants().add(this);
        return this;
    }

    public Professionnel removeDiplome(Diplome diplome) {
        this.diplomes.remove(diplome);
        diplome.getIntervenants().remove(this);
        return this;
    }

    public void setDiplomes(Set<Diplome> diplomes) {
        this.diplomes = diplomes;
    }

    public Entreprise getEntreprisePersonnel() {
        return entreprisePersonnel;
    }

    public Professionnel entreprisePersonnel(Entreprise entreprise) {
        this.entreprisePersonnel = entreprise;
        return this;
    }

    public void setEntreprisePersonnel(Entreprise entreprise) {
        this.entreprisePersonnel = entreprise;
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
        Professionnel professionnel = (Professionnel) o;
        if (professionnel.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), professionnel.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Professionnel{" +
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
