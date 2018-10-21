/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kimo
 */
@Entity
@Table(name = "MOUVEMENT_DE_STOCK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MouvementDeStock.findAll", query = "SELECT m FROM MouvementDeStock m")
    , @NamedQuery(name = "MouvementDeStock.findByReference", query = "SELECT m FROM MouvementDeStock m WHERE m.mouvementDeStockPK.reference = :reference")
    , @NamedQuery(name = "MouvementDeStock.findByNumeroMagasin", query = "SELECT m FROM MouvementDeStock m WHERE m.numeroMagasin = :numeroMagasin")
    , @NamedQuery(name = "MouvementDeStock.findByQuantite", query = "SELECT m FROM MouvementDeStock m WHERE m.quantite = :quantite")
    , @NamedQuery(name = "MouvementDeStock.findByPeriode", query = "SELECT m FROM MouvementDeStock m WHERE m.mouvementDeStockPK.periode = :periode")
    , @NamedQuery(name = "MouvementDeStock.findByType", query = "SELECT m FROM MouvementDeStock m WHERE m.mouvementDeStockPK.type = :type")})
public class MouvementDeStock implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MouvementDeStockPK mouvementDeStockPK;
    @Column(name = "NUMERO_MAGASIN")
    private Integer numeroMagasin;
    @Column(name = "QUANTITE")
    private Integer quantite;
    @JoinColumn(name = "REFERENCE", referencedColumnName = "REFERENCE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Article article;

    public MouvementDeStock() {
    }

    public MouvementDeStock(MouvementDeStockPK mouvementDeStockPK) {
        this.mouvementDeStockPK = mouvementDeStockPK;
    }

    public MouvementDeStock(String reference, Date periode, String type) {
        this.mouvementDeStockPK = new MouvementDeStockPK(reference, periode, type);
    }

    public MouvementDeStockPK getMouvementDeStockPK() {
        return mouvementDeStockPK;
    }

    public void setMouvementDeStockPK(MouvementDeStockPK mouvementDeStockPK) {
        this.mouvementDeStockPK = mouvementDeStockPK;
    }

    public Integer getNumeroMagasin() {
        return numeroMagasin;
    }

    public void setNumeroMagasin(Integer numeroMagasin) {
        this.numeroMagasin = numeroMagasin;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mouvementDeStockPK != null ? mouvementDeStockPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MouvementDeStock)) {
            return false;
        }
        MouvementDeStock other = (MouvementDeStock) object;
        if ((this.mouvementDeStockPK == null && other.mouvementDeStockPK != null) || (this.mouvementDeStockPK != null && !this.mouvementDeStockPK.equals(other.mouvementDeStockPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.MouvementDeStock[ mouvementDeStockPK=" + mouvementDeStockPK + " ]";
    }
    
}
