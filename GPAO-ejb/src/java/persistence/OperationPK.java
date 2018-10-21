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
public class OperationPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "GAMME_DE_FABRICATION")
    private String gammeDeFabrication;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_OPERATION")
    private int numeroOperation;

    public OperationPK() {
    }

    public OperationPK(String gammeDeFabrication, int numeroOperation) {
        this.gammeDeFabrication = gammeDeFabrication;
        this.numeroOperation = numeroOperation;
    }

    public String getGammeDeFabrication() {
        return gammeDeFabrication;
    }

    public void setGammeDeFabrication(String gammeDeFabrication) {
        this.gammeDeFabrication = gammeDeFabrication;
    }

    public int getNumeroOperation() {
        return numeroOperation;
    }

    public void setNumeroOperation(int numeroOperation) {
        this.numeroOperation = numeroOperation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gammeDeFabrication != null ? gammeDeFabrication.hashCode() : 0);
        hash += (int) numeroOperation;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OperationPK)) {
            return false;
        }
        OperationPK other = (OperationPK) object;
        if ((this.gammeDeFabrication == null && other.gammeDeFabrication != null) || (this.gammeDeFabrication != null && !this.gammeDeFabrication.equals(other.gammeDeFabrication))) {
            return false;
        }
        if (this.numeroOperation != other.numeroOperation) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.OperationPK[ gammeDeFabrication=" + gammeDeFabrication + ", numeroOperation=" + numeroOperation + " ]";
    }
    
}
