/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package case2models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author GCouto
 */
@Entity
@Table(name = "PURCHASEORDERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PurchaseOrderModel.findAll", query = "SELECT p FROM PurchaseOrderModel p"),
    @NamedQuery(name = "PurchaseOrderModel.findByPonumber", query = "SELECT p FROM PurchaseOrderModel p WHERE p.ponumber = :ponumber"),
    @NamedQuery(name = "PurchaseOrderModel.findByAmount", query = "SELECT p FROM PurchaseOrderModel p WHERE p.amount = :amount"),
    @NamedQuery(name = "PurchaseOrderModel.findByPodate", query = "SELECT p FROM PurchaseOrderModel p WHERE p.podate = :podate"),
    @NamedQuery(name = "PurchaseOrderModel.findByVendorno", query = "SELECT p FROM PurchaseOrderModel p WHERE p.vendorno = :vendorno")})
public class PurchaseOrderModel implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ponumber")
    private Collection<PurchaseOrderLineItemsModel> purchaseOrderLineItemsModelCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PONUMBER")
    private Integer ponumber;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PODATE")
    @Temporal(TemporalType.DATE)
    private Date podate;
    @JoinColumn(name = "VENDORNO", referencedColumnName = "VENDORNO")
    @ManyToOne(optional = false)
    private VendorModel vendorno;

    public PurchaseOrderModel() {
    }

    public PurchaseOrderModel(Integer ponumber) {
        this.ponumber = ponumber;
    }

    public PurchaseOrderModel(Integer ponumber, BigDecimal amount, Date podate) {
        this.ponumber = ponumber;
        this.amount = amount;
        this.podate = podate;
    }

    public Integer getPonumber() {
        return ponumber;
    }

    public void setPonumber(Integer ponumber) {
        this.ponumber = ponumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getPodate() {
        return podate;
    }

    public void setPodate(Date podate) {
        this.podate = podate;
    }

    public VendorModel getVendorno() {
        return vendorno;
    }

    public void setVendorno(VendorModel vendorno) {
        this.vendorno = vendorno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ponumber != null ? ponumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchaseOrderModel)) {
            return false;
        }
        PurchaseOrderModel other = (PurchaseOrderModel) object;
        if ((this.ponumber == null && other.ponumber != null) || (this.ponumber != null && !this.ponumber.equals(other.ponumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "case2models.PurchaseOrderModel[ ponumber=" + ponumber + " ]";
    }

    @XmlTransient
    public Collection<PurchaseOrderLineItemsModel> getPurchaseOrderLineItemsModelCollection() {
        return purchaseOrderLineItemsModelCollection;
    }

    public void setPurchaseOrderLineItemsModelCollection(Collection<PurchaseOrderLineItemsModel> purchaseOrderLineItemsModelCollection) {
        this.purchaseOrderLineItemsModelCollection = purchaseOrderLineItemsModelCollection;
    }
    
}
