/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kimo
 */
@Entity
@Table(name = "POSTE_DE_CHARGE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PosteDeCharge.findAll", query = "SELECT p FROM PosteDeCharge p")
    , @NamedQuery(name = "PosteDeCharge.findByNumeroSection", query = "SELECT p FROM PosteDeCharge p WHERE p.posteDeChargePK.numeroSection = :numeroSection")
    , @NamedQuery(name = "PosteDeCharge.findByNumeroSousSection", query = "SELECT p FROM PosteDeCharge p WHERE p.posteDeChargePK.numeroSousSection = :numeroSousSection")
    , @NamedQuery(name = "PosteDeCharge.findByEstMachine", query = "SELECT p FROM PosteDeCharge p WHERE p.posteDeChargePK.estMachine = :estMachine")
    , @NamedQuery(name = "PosteDeCharge.findByDesignation", query = "SELECT p FROM PosteDeCharge p WHERE p.designation = :designation")
    , @NamedQuery(name = "PosteDeCharge.findByTauxHoraireOuForfait", query = "SELECT p FROM PosteDeCharge p WHERE p.tauxHoraireOuForfait = :tauxHoraireOuForfait")
    , @NamedQuery(name = "PosteDeCharge.findByNombreDePostes", query = "SELECT p FROM PosteDeCharge p WHERE p.nombreDePostes = :nombreDePostes")
    , @NamedQuery(name = "PosteDeCharge.findByCapaciteNominale", query = "SELECT p FROM PosteDeCharge p WHERE p.capaciteNominale = :capaciteNominale")
    , @NamedQuery(name = "PosteDeCharge.findByTypeTauxHoraireOuForfait", query = "SELECT p FROM PosteDeCharge p WHERE p.typeTauxHoraireOuForfait = :typeTauxHoraireOuForfait")})
public class PosteDeCharge implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PosteDeChargePK posteDeChargePK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "DESIGNATION")
    private String designation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TAUX_HORAIRE_OU_FORFAIT")
    private int tauxHoraireOuForfait;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NOMBRE_DE_POSTES")
    private int nombreDePostes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAPACITE_NOMINALE")
    private int capaciteNominale;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "TYPE_TAUX_HORAIRE_OU_FORFAIT")
    private String typeTauxHoraireOuForfait;
    @OneToMany(mappedBy = "posteDeCharge")
    private Collection<Operation> operationCollection;
    @OneToMany(mappedBy = "posteDeCharge1")
    private Collection<Operation> operationCollection1;

    public PosteDeCharge() {
    }

    public PosteDeCharge(PosteDeChargePK posteDeChargePK) {
        this.posteDeChargePK = posteDeChargePK;
    }

    public PosteDeCharge(PosteDeChargePK posteDeChargePK, String designation, int tauxHoraireOuForfait, int nombreDePostes, int capaciteNominale, String typeTauxHoraireOuForfait) {
        this.posteDeChargePK = posteDeChargePK;
        this.designation = designation;
        this.tauxHoraireOuForfait = tauxHoraireOuForfait;
        this.nombreDePostes = nombreDePostes;
        this.capaciteNominale = capaciteNominale;
        this.typeTauxHoraireOuForfait = typeTauxHoraireOuForfait;
    }

    public PosteDeCharge(int numeroSection, int numeroSousSection, short estMachine) {
        this.posteDeChargePK = new PosteDeChargePK(numeroSection, numeroSousSection, estMachine);
    }

    public PosteDeChargePK getPosteDeChargePK() {
        return posteDeChargePK;
    }

    public void setPosteDeChargePK(PosteDeChargePK posteDeChargePK) {
        this.posteDeChargePK = posteDeChargePK;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getTauxHoraireOuForfait() {
        return tauxHoraireOuForfait;
    }

    public void setTauxHoraireOuForfait(int tauxHoraireOuForfait) {
        this.tauxHoraireOuForfait = tauxHoraireOuForfait;
    }

    public int getNombreDePostes() {
        return nombreDePostes;
    }

    public void setNombreDePostes(int nombreDePostes) {
        this.nombreDePostes = nombreDePostes;
    }

    public int getCapaciteNominale() {
        return capaciteNominale;
    }

    public void setCapaciteNominale(int capaciteNominale) {
        this.capaciteNominale = capaciteNominale;
    }

    public String getTypeTauxHoraireOuForfait() {
        return typeTauxHoraireOuForfait;
    }

    public void setTypeTauxHoraireOuForfait(String typeTauxHoraireOuForfait) {
        this.typeTauxHoraireOuForfait = typeTauxHoraireOuForfait;
    }

    @XmlTransient
    public Collection<Operation> getOperationCollection() {
        return operationCollection;
    }

    public void setOperationCollection(Collection<Operation> operationCollection) {
        this.operationCollection = operationCollection;
    }

    @XmlTransient
    public Collection<Operation> getOperationCollection1() {
        return operationCollection1;
    }

    public void setOperationCollection1(Collection<Operation> operationCollection1) {
        this.operationCollection1 = operationCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (posteDeChargePK != null ? posteDeChargePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PosteDeCharge)) {
            return false;
        }
        PosteDeCharge other = (PosteDeCharge) object;
        if ((this.posteDeChargePK == null && other.posteDeChargePK != null) || (this.posteDeChargePK != null && !this.posteDeChargePK.equals(other.posteDeChargePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.PosteDeCharge[ posteDeChargePK=" + posteDeChargePK + " ]";
    }
    
}
