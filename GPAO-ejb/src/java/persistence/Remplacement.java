/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kimo
 */
@Entity
@Table(name = "REMPLACEMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Remplacement.findAll", query = "SELECT r FROM Remplacement r")
    , @NamedQuery(name = "Remplacement.findByRemplaceCompose", query = "SELECT r FROM Remplacement r WHERE r.remplacementPK.remplaceCompose = :remplaceCompose")
    , @NamedQuery(name = "Remplacement.findByRemplaceComposant", query = "SELECT r FROM Remplacement r WHERE r.remplacementPK.remplaceComposant = :remplaceComposant")
    , @NamedQuery(name = "Remplacement.findByRemplacantCompose", query = "SELECT r FROM Remplacement r WHERE r.remplacementPK.remplacantCompose = :remplacantCompose")
    , @NamedQuery(name = "Remplacement.findByRemplacantComposant", query = "SELECT r FROM Remplacement r WHERE r.remplacementPK.remplacantComposant = :remplacantComposant")
    , @NamedQuery(name = "Remplacement.findByDateDeRemplacement", query = "SELECT r FROM Remplacement r WHERE r.dateDeRemplacement = :dateDeRemplacement")})
public class Remplacement implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RemplacementPK remplacementPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_DE_REMPLACEMENT")
    @Temporal(TemporalType.DATE)
    private Date dateDeRemplacement;
    @JoinColumns({
        @JoinColumn(name = "REMPLACANT_COMPOSE", referencedColumnName = "COMPOSE", insertable = false, updatable = false)
        , @JoinColumn(name = "REMPLACANT_COMPOSANT", referencedColumnName = "COMPOSANT", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private LienDeNomenclature remplacant;
    @JoinColumns({
        @JoinColumn(name = "REMPLACE_COMPOSE", referencedColumnName = "COMPOSE", insertable = false, updatable = false)
        , @JoinColumn(name = "REMPLACE_COMPOSANT", referencedColumnName = "COMPOSANT", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private LienDeNomenclature remplace;

    public Remplacement() {
    }

    public Remplacement(RemplacementPK remplacementPK) {
        this.remplacementPK = remplacementPK;
    }

    public Remplacement(RemplacementPK remplacementPK, Date dateDeRemplacement) {
        this.remplacementPK = remplacementPK;
        this.dateDeRemplacement = dateDeRemplacement;
    }

    public Remplacement(String remplaceCompose, String remplaceComposant, String remplacantCompose, String remplacantComposant) {
        this.remplacementPK = new RemplacementPK(remplaceCompose, remplaceComposant, remplacantCompose, remplacantComposant);
    }

    public RemplacementPK getRemplacementPK() {
        return remplacementPK;
    }

    public void setRemplacementPK(RemplacementPK remplacementPK) {
        this.remplacementPK = remplacementPK;
    }

    public LienDeNomenclature getRemplace() {
        return remplace;
    }

    public void setRemplace(LienDeNomenclature remplace) {
        this.remplace = remplace;
    }

    public LienDeNomenclature getRemplacant() {
        return remplacant;
    }

    public void setRemplacant(LienDeNomenclature remplacant) {
        this.remplacant = remplacant;
    }

    public Date getDateDeRemplacement() {
        return dateDeRemplacement;
    }

    public void setDateDeRemplacement(Date dateDeRemplacement) {
        this.dateDeRemplacement = dateDeRemplacement;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (remplacementPK != null ? remplacementPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Remplacement)) {
            return false;
        }
        Remplacement other = (Remplacement) object;
        if ((this.remplacementPK == null && other.remplacementPK != null) || (this.remplacementPK != null && !this.remplacementPK.equals(other.remplacementPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Remplacement[ remplacementPK=" + remplacementPK + " ]";
    }

}
