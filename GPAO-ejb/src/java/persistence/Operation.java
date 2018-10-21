/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kimo
 */
@Entity
@Table(name = "OPERATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operation.findAll", query = "SELECT o FROM Operation o")
    , @NamedQuery(name = "Operation.findByGammeDeFabrication", query = "SELECT o FROM Operation o WHERE o.operationPK.gammeDeFabrication = :gammeDeFabrication")
    , @NamedQuery(name = "Operation.findByNumeroOperation", query = "SELECT o FROM Operation o WHERE o.operationPK.numeroOperation = :numeroOperation")
    , @NamedQuery(name = "Operation.findByTempsPreparation", query = "SELECT o FROM Operation o WHERE o.tempsPreparation = :tempsPreparation")
    , @NamedQuery(name = "Operation.findByTempsExecution", query = "SELECT o FROM Operation o WHERE o.tempsExecution = :tempsExecution")
    , @NamedQuery(name = "Operation.findByTempsTransfert", query = "SELECT o FROM Operation o WHERE o.tempsTransfert = :tempsTransfert")
    , @NamedQuery(name = "Operation.findByLibelleOperation", query = "SELECT o FROM Operation o WHERE o.libelleOperation = :libelleOperation")})
public class Operation implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OperationPK operationPK;
    @Column(name = "TEMPS_PREPARATION")
    private Integer tempsPreparation;
    @Column(name = "TEMPS_EXECUTION")
    private Integer tempsExecution;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TEMPS_TRANSFERT")
    private int tempsTransfert;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "LIBELLE_OPERATION")
    private String libelleOperation;
    @JoinColumn(name = "GAMME_DE_FABRICATION", referencedColumnName = "REFERENCE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Article article;
    @JoinColumns({
        @JoinColumn(name = "MAIN_D_OEUVRE_NUMERO_SECTION", referencedColumnName = "NUMERO_SECTION")
        , @JoinColumn(name = "MAIN_D_OEUVRE_NUMERO_SOUS_SECTION", referencedColumnName = "NUMERO_SOUS_SECTION")
        , @JoinColumn(name = "MAIN_D_OEUVRE_EST_MACHINE", referencedColumnName = "EST_MACHINE")})
    @ManyToOne
    private PosteDeCharge posteDeCharge;
    @JoinColumns({
        @JoinColumn(name = "MACHINE_NUMERO_SECTION", referencedColumnName = "NUMERO_SECTION")
        , @JoinColumn(name = "MACHINE_NUMERO_SOUS_SECTION", referencedColumnName = "NUMERO_SOUS_SECTION")
        , @JoinColumn(name = "MACHINE_EST_MACHINE", referencedColumnName = "EST_MACHINE")})
    @ManyToOne
    private PosteDeCharge posteDeCharge1;

    public Operation() {
    }

    public Operation(OperationPK operationPK) {
        this.operationPK = operationPK;
    }

    public Operation(OperationPK operationPK, int tempsTransfert, String libelleOperation) {
        this.operationPK = operationPK;
        this.tempsTransfert = tempsTransfert;
        this.libelleOperation = libelleOperation;
    }

    public Operation(String gammeDeFabrication, int numeroOperation) {
        this.operationPK = new OperationPK(gammeDeFabrication, numeroOperation);
    }

    public OperationPK getOperationPK() {
        return operationPK;
    }

    public void setOperationPK(OperationPK operationPK) {
        this.operationPK = operationPK;
    }

    public Integer getTempsPreparation() {
        return tempsPreparation;
    }

    public void setTempsPreparation(Integer tempsPreparation) {
        this.tempsPreparation = tempsPreparation;
    }

    public Integer getTempsExecution() {
        return tempsExecution;
    }

    public void setTempsExecution(Integer tempsExecution) {
        this.tempsExecution = tempsExecution;
    }

    public int getTempsTransfert() {
        return tempsTransfert;
    }

    public void setTempsTransfert(int tempsTransfert) {
        this.tempsTransfert = tempsTransfert;
    }

    public String getLibelleOperation() {
        return libelleOperation;
    }

    public void setLibelleOperation(String libelleOperation) {
        this.libelleOperation = libelleOperation;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public PosteDeCharge getPosteDeCharge() {
        return posteDeCharge;
    }

    public void setPosteDeCharge(PosteDeCharge posteDeCharge) {
        this.posteDeCharge = posteDeCharge;
    }

    public PosteDeCharge getPosteDeCharge1() {
        return posteDeCharge1;
    }

    public void setPosteDeCharge1(PosteDeCharge posteDeCharge1) {
        this.posteDeCharge1 = posteDeCharge1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (operationPK != null ? operationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operation)) {
            return false;
        }
        Operation other = (Operation) object;
        if ((this.operationPK == null && other.operationPK != null) || (this.operationPK != null && !this.operationPK.equals(other.operationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Operation[ operationPK=" + operationPK + " ]";
    }
    
}
