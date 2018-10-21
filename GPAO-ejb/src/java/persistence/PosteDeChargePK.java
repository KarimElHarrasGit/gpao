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

/**
 *
 * @author kimo
 */
@Embeddable
public class PosteDeChargePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_SECTION")
    private int numeroSection;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_SOUS_SECTION")
    private int numeroSousSection;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EST_MACHINE")
    private short estMachine;

    public PosteDeChargePK() {
    }

    public PosteDeChargePK(int numeroSection, int numeroSousSection, short estMachine) {
        this.numeroSection = numeroSection;
        this.numeroSousSection = numeroSousSection;
        this.estMachine = estMachine;
    }

    public int getNumeroSection() {
        return numeroSection;
    }

    public void setNumeroSection(int numeroSection) {
        this.numeroSection = numeroSection;
    }

    public int getNumeroSousSection() {
        return numeroSousSection;
    }

    public void setNumeroSousSection(int numeroSousSection) {
        this.numeroSousSection = numeroSousSection;
    }

    public short getEstMachine() {
        return estMachine;
    }

    public void setEstMachine(short estMachine) {
        this.estMachine = estMachine;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) numeroSection;
        hash += (int) numeroSousSection;
        hash += (int) estMachine;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PosteDeChargePK)) {
            return false;
        }
        PosteDeChargePK other = (PosteDeChargePK) object;
        if (this.numeroSection != other.numeroSection) {
            return false;
        }
        if (this.numeroSousSection != other.numeroSousSection) {
            return false;
        }
        if (this.estMachine != other.estMachine) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.PosteDeChargePK[ numeroSection=" + numeroSection + ", numeroSousSection=" + numeroSousSection + ", estMachine=" + estMachine + " ]";
    }
    
}
