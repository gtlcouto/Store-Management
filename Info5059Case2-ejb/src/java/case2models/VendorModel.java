/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package case2models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * @author GCouto
 * 11/2/2013 -- Added findAllVendorNo query
 */
@Entity
@Table(name = "VENDORS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VendorModel.findAll", query = "SELECT v FROM VendorModel v"),
    @NamedQuery(name = "VendorModel.findByVendorno", query = "SELECT v FROM VendorModel v WHERE v.vendorno = :vendorno"),
    @NamedQuery(name = "VendorModel.findByAddress1", query = "SELECT v FROM VendorModel v WHERE v.address1 = :address1"),
    @NamedQuery(name = "VendorModel.findByCity", query = "SELECT v FROM VendorModel v WHERE v.city = :city"),
    @NamedQuery(name = "VendorModel.findByProvince", query = "SELECT v FROM VendorModel v WHERE v.province = :province"),
    @NamedQuery(name = "VendorModel.findByPostalcode", query = "SELECT v FROM VendorModel v WHERE v.postalcode = :postalcode"),
    @NamedQuery(name = "VendorModel.findByPhone", query = "SELECT v FROM VendorModel v WHERE v.phone = :phone"),
    @NamedQuery(name = "VendorModel.findByVendortype", query = "SELECT v FROM VendorModel v WHERE v.vendortype = :vendortype"),
    @NamedQuery(name = "VendorModel.findAllVendorNos", query = "SELECT v.vendorno FROM VendorModel v"),
    @NamedQuery(name = "VendorModel.findByName", query = "SELECT v FROM VendorModel v WHERE v.name = :name"),
    @NamedQuery(name = "VendorModel.findByEmail", query = "SELECT v FROM VendorModel v WHERE v.email = :email")})
public class VendorModel implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendorno")
    private Collection<PurchaseOrderModel> purchaseOrderModelCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendorno")
    private Collection<ProductModel> productModelCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "VENDORNO")
    private Integer vendorno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "ADDRESS1")
    private String address1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "CITY")
    private String city;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "PROVINCE")
    private String province;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "POSTALCODE")
    private String postalcode;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "PHONE")
    private String phone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "VENDORTYPE")
    private String vendortype;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "NAME")
    private String name;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "EMAIL")
    private String email;

    public VendorModel() {
    }

    public VendorModel(Integer vendorno) {
        this.vendorno = vendorno;
    }

    public VendorModel(Integer vendorno, String address1, String city, String province, String postalcode, String phone, String vendortype, String name, String email) {
        this.vendorno = vendorno;
        this.address1 = address1;
        this.city = city;
        this.province = province;
        this.postalcode = postalcode;
        this.phone = phone;
        this.vendortype = vendortype;
        this.name = name;
        this.email = email;
    }

    public Integer getVendorno() {
        return vendorno;
    }

    public void setVendorno(Integer vendorno) {
        this.vendorno = vendorno;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVendortype() {
        return vendortype;
    }

    public void setVendortype(String vendortype) {
        this.vendortype = vendortype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vendorno != null ? vendorno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VendorModel)) {
            return false;
        }
        VendorModel other = (VendorModel) object;
        if ((this.vendorno == null && other.vendorno != null) || (this.vendorno != null && !this.vendorno.equals(other.vendorno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "case2models.VendorModel[ vendorno=" + vendorno + " ]";
    }

    @XmlTransient
    public Collection<ProductModel> getProductModelCollection() {
        return productModelCollection;
    }

    public void setProductModelCollection(Collection<ProductModel> productModelCollection) {
        this.productModelCollection = productModelCollection;
    }

    @XmlTransient
    public Collection<PurchaseOrderModel> getPurchaseOrderModelCollection() {
        return purchaseOrderModelCollection;
    }

    public void setPurchaseOrderModelCollection(Collection<PurchaseOrderModel> purchaseOrderModelCollection) {
        this.purchaseOrderModelCollection = purchaseOrderModelCollection;
    }
    
}
