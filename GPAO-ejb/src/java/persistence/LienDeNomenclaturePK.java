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
public class LienDeNomenclaturePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "COMPOSE")
    private String compose;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "COMPOSANT")
    private String composant;

    public LienDeNomenclaturePK() {
    }

    public LienDeNomenclaturePK(String compose, String composant) {
        this.compose = compose;
        this.composant = composant;
    }

    public String getCompose() {
        return compose;
    }

    public void setCompose(String compose) {
        this.compose = compose;
    }

    public String getComposant() {
        return composant;
    }

    public void setComposant(String composant) {
        this.composant = composant;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (compose != null ? compose.hashCode() : 0);
        hash += (composant != null ? composant.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LienDeNomenclaturePK)) {
            return false;
        }
        LienDeNomenclaturePK other = (LienDeNomenclaturePK) object;
        if ((this.compose == null && other.compose != null) || (this.compose != null && !this.compose.equals(other.compose))) {
            return false;
        }
        if ((this.composant == null && other.composant != null) || (this.composant != null && !this.composant.equals(other.composant))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "compose=" + compose + ", composant=" + composant;
    }
    
}
