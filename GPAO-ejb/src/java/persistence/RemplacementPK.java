/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author kimo
 */
@Embeddable
public class RemplacementPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "REMPLACE_COMPOSE")
    private String remplaceCompose;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "REMPLACE_COMPOSANT")
    private String remplaceComposant;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "REMPLACANT_COMPOSE")
    private String remplacantCompose;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "REMPLACANT_COMPOSANT")
    private String remplacantComposant;

    public RemplacementPK() {
    }

    public RemplacementPK(String remplaceCompose, String remplaceComposant, String remplacantCompose, String remplacantComposant) {
        this.remplaceCompose = remplaceCompose;
        this.remplaceComposant = remplaceComposant;
        this.remplacantCompose = remplacantCompose;
        this.remplacantComposant = remplacantComposant;
    }

    public String getRemplaceCompose() {
        return remplaceCompose;
    }

    public void setRemplaceCompose(String remplaceCompose) {
        this.remplaceCompose = remplaceCompose;
    }

    public String getRemplaceComposant() {
        return remplaceComposant;
    }

    public void setRemplaceComposant(String remplaceComposant) {
        this.remplaceComposant = remplaceComposant;
    }

    public String getRemplacantCompose() {
        return remplacantCompose;
    }

    public void setRemplacantCompose(String remplacantCompose) {
        this.remplacantCompose = remplacantCompose;
    }

    public String getRemplacantComposant() {
        return remplacantComposant;
    }

    public void setRemplacantComposant(String remplacantComposant) {
        this.remplacantComposant = remplacantComposant;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (remplaceCompose != null ? remplaceCompose.hashCode() : 0);
        hash += (remplaceComposant != null ? remplaceComposant.hashCode() : 0);
        hash += (remplacantCompose != null ? remplacantCompose.hashCode() : 0);
        hash += (remplacantComposant != null ? remplacantComposant.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RemplacementPK)) {
            return false;
        }
        RemplacementPK other = (RemplacementPK) object;
        if ((this.remplaceCompose == null && other.remplaceCompose != null) || (this.remplaceCompose != null && !this.remplaceCompose.equals(other.remplaceCompose))) {
            return false;
        }
        if ((this.remplaceComposant == null && other.remplaceComposant != null) || (this.remplaceComposant != null && !this.remplaceComposant.equals(other.remplaceComposant))) {
            return false;
        }
        if ((this.remplacantCompose == null && other.remplacantCompose != null) || (this.remplacantCompose != null && !this.remplacantCompose.equals(other.remplacantCompose))) {
            return false;
        }
        if ((this.remplacantComposant == null && other.remplacantComposant != null) || (this.remplacantComposant != null && !this.remplacantComposant.equals(other.remplacantComposant))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.RemplacementPK[ remplaceCompose=" + remplaceCompose + ", remplaceComposant=" + remplaceComposant + ", remplacantCompose=" + remplacantCompose + ", remplacantComposant=" + remplacantComposant + " ]";
    }
    
}
