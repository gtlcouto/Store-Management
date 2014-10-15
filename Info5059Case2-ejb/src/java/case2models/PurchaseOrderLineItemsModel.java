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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "PURCHASEORDERLINEITEMS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PurchaseOrderLineItemsModel.findAll", query = "SELECT p FROM PurchaseOrderLineItemsModel p"),
    @NamedQuery(name = "PurchaseOrderLineItemsModel.findByLineid", query = "SELECT p FROM PurchaseOrderLineItemsModel p WHERE p.lineid = :lineid"),
    @NamedQuery(name = "PurchaseOrderLineItemsModel.findByProdcd", query = "SELECT p FROM PurchaseOrderLineItemsModel p WHERE p.prodcd = :prodcd"),
    @NamedQuery(name = "PurchaseOrderLineItemsModel.findByQty", query = "SELECT p FROM PurchaseOrderLineItemsModel p WHERE p.qty = :qty"),
    @NamedQuery(name = "PurchaseOrderLineItemsModel.findByPrice", query = "SELECT p FROM PurchaseOrderLineItemsModel p WHERE p.price = :price"),
    @NamedQuery(name = "PurchaseOrderLineItemsModel.findByponumber", query = "SELECT p FROM PurchaseOrderLineItemsModel p WHERE p.ponumber = :ponumber")})
public class PurchaseOrderLineItemsModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "LINEID")
    private Integer lineid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PRODCD")
    private String prodcd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QTY")
    private int qty;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRICE")
    private BigDecimal price;
    @JoinColumn(name = "PONUMBER", referencedColumnName = "PONUMBER")
    @ManyToOne(optional = false)
    private PurchaseOrderModel ponumber;

    public PurchaseOrderLineItemsModel() {
    }

    public PurchaseOrderLineItemsModel(Integer lineid) {
        this.lineid = lineid;
    }

    public PurchaseOrderLineItemsModel(Integer lineid, String prodcd, int qty, BigDecimal price) {
        this.lineid = lineid;
        this.prodcd = prodcd;
        this.qty = qty;
        this.price = price;
    }

    public Integer getLineid() {
        return lineid;
    }

    public void setLineid(Integer lineid) {
        this.lineid = lineid;
    }

    public String getProdcd() {
        return prodcd;
    }

    public void setProdcd(String prodcd) {
        this.prodcd = prodcd;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public PurchaseOrderModel getPonumber() {
        return ponumber;
    }

    public void setPonumber(PurchaseOrderModel ponumber) {
        this.ponumber = ponumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lineid != null ? lineid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchaseOrderLineItemsModel)) {
            return false;
        }
        PurchaseOrderLineItemsModel other = (PurchaseOrderLineItemsModel) object;
        if ((this.lineid == null && other.lineid != null) || (this.lineid != null && !this.lineid.equals(other.lineid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "case2models.PurchaseOrderLineItemsModel[ lineid=" + lineid + " ]";
    }
    
}
