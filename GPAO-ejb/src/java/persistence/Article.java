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
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "ARTICLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Article.findAll", query = "SELECT a FROM Article a")
    , @NamedQuery(name = "Article.findByReference", query = "SELECT a FROM Article a WHERE a.reference = :reference")
    , @NamedQuery(name = "Article.findByDesignation", query = "SELECT a FROM Article a WHERE a.designation = :designation")
    , @NamedQuery(name = "Article.findByTypeFabricationAchat", query = "SELECT a FROM Article a WHERE a.typeFabricationAchat = :typeFabricationAchat")
    , @NamedQuery(name = "Article.findByUniteAchatStock", query = "SELECT a FROM Article a WHERE a.uniteAchatStock = :uniteAchatStock")
    , @NamedQuery(name = "Article.findByDelaiEnSemaine", query = "SELECT a FROM Article a WHERE a.delaiEnSemaine = :delaiEnSemaine")
    , @NamedQuery(name = "Article.findByPrixStandard", query = "SELECT a FROM Article a WHERE a.prixStandard = :prixStandard")
    , @NamedQuery(name = "Article.findByLotDeReapprovisionnement", query = "SELECT a FROM Article a WHERE a.lotDeReapprovisionnement = :lotDeReapprovisionnement")
    , @NamedQuery(name = "Article.findByStockMini", query = "SELECT a FROM Article a WHERE a.stockMini = :stockMini")
    , @NamedQuery(name = "Article.findByStockMaxi", query = "SELECT a FROM Article a WHERE a.stockMaxi = :stockMaxi")
    , @NamedQuery(name = "Article.findByPourcentageDePerte", query = "SELECT a FROM Article a WHERE a.pourcentageDePerte = :pourcentageDePerte")
    , @NamedQuery(name = "Article.findByInventaire", query = "SELECT a FROM Article a WHERE a.inventaire = :inventaire")
    , @NamedQuery(name = "Article.findByPfOuMpOuPieceOuSe", query = "SELECT a FROM Article a WHERE a.pfOuMpOuPieceOuSe = :pfOuMpOuPieceOuSe")})
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "REFERENCE")
    private String reference;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "DESIGNATION")
    private String designation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "TYPE_FABRICATION_ACHAT")
    private String typeFabricationAchat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "UNITE_ACHAT_STOCK")
    private String uniteAchatStock;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DELAI_EN_SEMAINE")
    private int delaiEnSemaine;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRIX_STANDARD")
    private Double prixStandard;
    @Column(name = "LOT_DE_REAPPROVISIONNEMENT")
    private Integer lotDeReapprovisionnement;
    @Column(name = "STOCK_MINI")
    private Integer stockMini;
    @Column(name = "STOCK_MAXI")
    private Integer stockMaxi;
    @Column(name = "POURCENTAGE_DE_PERTE")
    private Double pourcentageDePerte;
    @Column(name = "INVENTAIRE")
    private Integer inventaire;
    @Size(max = 2)
    @Column(name = "PF_OU_MP_OU_PIECE_OU_SE")
    private String pfOuMpOuPieceOuSe;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article")
    private Collection<MouvementDeStock> mouvementDeStockCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article")
    private Collection<Operation> operationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article")
    private Collection<LienDeNomenclature> lienDeNomenclatureCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article1")
    private Collection<LienDeNomenclature> lienDeNomenclatureCollection1;

    public Article() {
    }

    public Article(String reference) {
        this.reference = reference;
    }

    public Article(String reference, String designation, String typeFabricationAchat, String uniteAchatStock, int delaiEnSemaine) {
        this.reference = reference;
        this.designation = designation;
        this.typeFabricationAchat = typeFabricationAchat;
        this.uniteAchatStock = uniteAchatStock;
        this.delaiEnSemaine = delaiEnSemaine;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getTypeFabricationAchat() {
        return typeFabricationAchat;
    }

    public void setTypeFabricationAchat(String typeFabricationAchat) {
        this.typeFabricationAchat = typeFabricationAchat;
    }

    public String getUniteAchatStock() {
        return uniteAchatStock;
    }

    public void setUniteAchatStock(String uniteAchatStock) {
        this.uniteAchatStock = uniteAchatStock;
    }

    public int getDelaiEnSemaine() {
        return delaiEnSemaine;
    }

    public void setDelaiEnSemaine(int delaiEnSemaine) {
        this.delaiEnSemaine = delaiEnSemaine;
    }

    public Double getPrixStandard() {
        return prixStandard;
    }

    public void setPrixStandard(Double prixStandard) {
        this.prixStandard = prixStandard;
    }

    public Integer getLotDeReapprovisionnement() {
        return lotDeReapprovisionnement;
    }

    public void setLotDeReapprovisionnement(Integer lotDeReapprovisionnement) {
        this.lotDeReapprovisionnement = lotDeReapprovisionnement;
    }

    public Integer getStockMini() {
        return stockMini;
    }

    public void setStockMini(Integer stockMini) {
        this.stockMini = stockMini;
    }

    public Integer getStockMaxi() {
        return stockMaxi;
    }

    public void setStockMaxi(Integer stockMaxi) {
        this.stockMaxi = stockMaxi;
    }

    public Double getPourcentageDePerte() {
        return pourcentageDePerte;
    }

    public void setPourcentageDePerte(Double pourcentageDePerte) {
        this.pourcentageDePerte = pourcentageDePerte;
    }

    public Integer getInventaire() {
        return inventaire;
    }

    public void setInventaire(Integer inventaire) {
        this.inventaire = inventaire;
    }

    public String getPfOuMpOuPieceOuSe() {
        return pfOuMpOuPieceOuSe;
    }

    public void setPfOuMpOuPieceOuSe(String pfOuMpOuPieceOuSe) {
        this.pfOuMpOuPieceOuSe = pfOuMpOuPieceOuSe;
    }

    @XmlTransient
    public Collection<MouvementDeStock> getMouvementDeStockCollection() {
        return mouvementDeStockCollection;
    }

    public void setMouvementDeStockCollection(Collection<MouvementDeStock> mouvementDeStockCollection) {
        this.mouvementDeStockCollection = mouvementDeStockCollection;
    }

    @XmlTransient
    public Collection<Operation> getOperationCollection() {
        return operationCollection;
    }

    public void setOperationCollection(Collection<Operation> operationCollection) {
        this.operationCollection = operationCollection;
    }

    @XmlTransient
    public Collection<LienDeNomenclature> getLienDeNomenclatureCollection() {
        return lienDeNomenclatureCollection;
    }

    public void setLienDeNomenclatureCollection(Collection<LienDeNomenclature> lienDeNomenclatureCollection) {
        this.lienDeNomenclatureCollection = lienDeNomenclatureCollection;
    }

    @XmlTransient
    public Collection<LienDeNomenclature> getLienDeNomenclatureCollection1() {
        return lienDeNomenclatureCollection1;
    }

    public void setLienDeNomenclatureCollection1(Collection<LienDeNomenclature> lienDeNomenclatureCollection1) {
        this.lienDeNomenclatureCollection1 = lienDeNomenclatureCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reference != null ? reference.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        if ((this.reference == null && other.reference != null) || (this.reference != null && !this.reference.equals(other.reference))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Article[ reference=" + reference + " ]";
    }
    
}
