/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package case2models;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author GCouto
 */
@Entity
@Table(name = "PRODUCTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductModel.findAll", query = "SELECT p FROM ProductModel p"),
    @NamedQuery(name = "ProductModel.findByProdcd", query = "SELECT p FROM ProductModel p WHERE p.prodcd = :prodcd"),
    @NamedQuery(name = "ProductModel.findByVensku", query = "SELECT p FROM ProductModel p WHERE p.vensku = :vensku"),
    @NamedQuery(name = "ProductModel.findByProdnam", query = "SELECT p FROM ProductModel p WHERE p.prodnam = :prodnam"),
    @NamedQuery(name = "ProductModel.findByCostprice", query = "SELECT p FROM ProductModel p WHERE p.costprice = :costprice"),
    @NamedQuery(name = "ProductModel.findByMsrp", query = "SELECT p FROM ProductModel p WHERE p.msrp = :msrp"),
    @NamedQuery(name = "ProductModel.findByRop", query = "SELECT p FROM ProductModel p WHERE p.rop = :rop"),
    @NamedQuery(name = "ProductModel.findByEoq", query = "SELECT p FROM ProductModel p WHERE p.eoq = :eoq"),
    @NamedQuery(name = "ProductModel.findByQoh", query = "SELECT p FROM ProductModel p WHERE p.qoh = :qoh"),
    @NamedQuery(name = "ProductModel.findByQoo", query = "SELECT p FROM ProductModel p WHERE p.qoo = :qoo"),
    @NamedQuery(name = "ProductModel.findByVendorno", query = "SELECT p FROM ProductModel p WHERE p.vendorno = :vendorno")})
public class ProductModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "PRODCD")
    private String prodcd;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "VENSKU")
    private String vensku;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PRODNAM")
    private String prodnam;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "COSTPRICE")
    private double costprice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MSRP")
    private double msrp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ROP")
    private int rop;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EOQ")
    private int eoq;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QOH")
    private int qoh;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QOO")
    private int qoo;
    @Lob
    @Column(name = "QRCODE")
    private Serializable qrcode;
    @JoinColumn(name = "VENDORNO", referencedColumnName = "VENDORNO")
    @ManyToOne(optional = false)
    private VendorModel vendorno;

    public ProductModel() {
    }

    public ProductModel(String prodcd) {
        this.prodcd = prodcd;
    }

    public ProductModel(String prodcd, String vensku, String prodnam, double costprice, double msrp, int rop, int eoq, int qoh, int qoo) {
        this.prodcd = prodcd;
        this.vensku = vensku;
        this.prodnam = prodnam;
        this.costprice = costprice;
        this.msrp = msrp;
        this.rop = rop;
        this.eoq = eoq;
        this.qoh = qoh;
        this.qoo = qoo;
    }

    public String getProdcd() {
        return prodcd;
    }

    public void setProdcd(String prodcd) {
        this.prodcd = prodcd;
    }

    public String getVensku() {
        return vensku;
    }

    public void setVensku(String vensku) {
        this.vensku = vensku;
    }

    public String getProdnam() {
        return prodnam;
    }

    public void setProdnam(String prodnam) {
        this.prodnam = prodnam;
    }

    public double getCostprice() {
        return costprice;
    }

    public void setCostprice(double costprice) {
        this.costprice = costprice;
    }

    public double getMsrp() {
        return msrp;
    }

    public void setMsrp(double msrp) {
        this.msrp = msrp;
    }

    public int getRop() {
        return rop;
    }

    public void setRop(int rop) {
        this.rop = rop;
    }

    public int getEoq() {
        return eoq;
    }

    public void setEoq(int eoq) {
        this.eoq = eoq;
    }

    public int getQoh() {
        return qoh;
    }

    public void setQoh(int qoh) {
        this.qoh = qoh;
    }

    public int getQoo() {
        return qoo;
    }

    public void setQoo(int qoo) {
        this.qoo = qoo;
    }

    public Serializable getQrcode() {
        return qrcode;
    }

    public void setQrcode(Serializable qrcode) {
        this.qrcode = qrcode;
    }

    public int getVendorno() {
        return Integer.parseInt(vendorno.toString());
    }

    public void setVendorno(VendorModel vendorno) {
        this.vendorno = vendorno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prodcd != null ? prodcd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductModel)) {
            return false;
        }
        ProductModel other = (ProductModel) object;
        if ((this.prodcd == null && other.prodcd != null) || (this.prodcd != null && !this.prodcd.equals(other.prodcd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "case2models.ProductModel[ prodcd=" + prodcd + " ]";
    }
    
}
