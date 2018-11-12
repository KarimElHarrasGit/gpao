/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kimo
 */
@Entity
@Table(name = "LIEN_DE_NOMENCLATURE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LienDeNomenclature.findAll", query = "SELECT l FROM LienDeNomenclature l")
    , @NamedQuery(name = "LienDeNomenclature.findByCompose", query = "SELECT l FROM LienDeNomenclature l WHERE l.lienDeNomenclaturePK.compose = :compose")
    , @NamedQuery(name = "LienDeNomenclature.findByComposant", query = "SELECT l FROM LienDeNomenclature l WHERE l.lienDeNomenclaturePK.composant = :composant")
    , @NamedQuery(name = "LienDeNomenclature.findByQuantiteDeComposition", query = "SELECT l FROM LienDeNomenclature l WHERE l.quantiteDeComposition = :quantiteDeComposition")})
public class LienDeNomenclature implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LienDeNomenclaturePK lienDeNomenclaturePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUANTITE_DE_COMPOSITION")
    private double quantiteDeComposition;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "remplacant")
    private Collection<Remplacement> remplacementCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "remplace")
    private Collection<Remplacement> remplacementCollection1;
    @JoinColumn(name = "COMPOSE", referencedColumnName = "REFERENCE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Article compose;
    @JoinColumn(name = "COMPOSANT", referencedColumnName = "REFERENCE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Article composant;

    public LienDeNomenclature() {
    }

    public LienDeNomenclature(LienDeNomenclaturePK lienDeNomenclaturePK) {
        this.lienDeNomenclaturePK = lienDeNomenclaturePK;
    }

    public LienDeNomenclature(LienDeNomenclaturePK lienDeNomenclaturePK, double quantiteDeComposition) {
        this.lienDeNomenclaturePK = lienDeNomenclaturePK;
        this.quantiteDeComposition = quantiteDeComposition;
    }

    public LienDeNomenclature(String compose, String composant) {
        this.lienDeNomenclaturePK = new LienDeNomenclaturePK(compose, composant);
    }

    public LienDeNomenclaturePK getLienDeNomenclaturePK() {
        return lienDeNomenclaturePK;
    }

    public void setLienDeNomenclaturePK(LienDeNomenclaturePK lienDeNomenclaturePK) {
        this.lienDeNomenclaturePK = lienDeNomenclaturePK;
    }

    @XmlTransient
    public Collection<Remplacement> getRemplacementCollection() {
        return remplacementCollection;
    }

    public void setRemplacementCollection(Collection<Remplacement> remplacementCollection) {
        this.remplacementCollection = remplacementCollection;
    }

    @XmlTransient
    public Collection<Remplacement> getRemplacementCollection1() {
        return remplacementCollection1;
    }

    public void setRemplacementCollection1(Collection<Remplacement> remplacementCollection1) {
        this.remplacementCollection1 = remplacementCollection1;
    }

    public Article getCompose() {
        return compose;
    }

    public void setCompose(Article compose) {
        this.compose = compose;
    }

    public Article getComposant() {
        return composant;
    }

    public void setComposant(Article composant) {
        this.composant = composant;
    }

    public double getQuantiteDeComposition() {
        return quantiteDeComposition;
    }

    public void setQuantiteDeComposition(double quantiteDeComposition) {
        this.quantiteDeComposition = quantiteDeComposition;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lienDeNomenclaturePK != null ? lienDeNomenclaturePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LienDeNomenclature)) {
            return false;
        }
        LienDeNomenclature other = (LienDeNomenclature) object;
        if ((this.lienDeNomenclaturePK == null && other.lienDeNomenclaturePK != null) || (this.lienDeNomenclaturePK != null && !this.lienDeNomenclaturePK.equals(other.lienDeNomenclaturePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return lienDeNomenclaturePK.toString();
    }

}
