/**
 * PurchaseOrderEJBDTO.java
 *
 *   Created:    9-10-2013 3:03 PM
 *   @author:    Gustavo
 *   Transfer Object that serializes vendor information traveling 
 *   between War and EJB Jar containers
 *   Revisions:   @version 1.0, 09/10/13:
 *      
 */
package case2dtos;

import java.io.Serializable;
import java.math.BigDecimal;

public class PurchaseOrderLineitemEJBDTO implements Serializable {
    public PurchaseOrderLineitemEJBDTO() {}
    private int lineid;
    private int ponumber;
    private String prodcd;
    private int qty;
    private BigDecimal price;
    private String prodnam;
    private BigDecimal fPrice;

    public int getLineid() {
        return lineid;
    }

    public void setLineid(int lineid) {
        this.lineid = lineid;
    }

    public int getPonumber() {
        return ponumber;
    }

    public void setPonumber(int ponumber) {
        this.ponumber = ponumber;
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

    public String getProdnam() {
        return prodnam;
    }

    public void setProdnam(String prodnam) {
        this.prodnam = prodnam;
    }

    public BigDecimal getfPrice() {
        return fPrice;
    }

    public void setfPrice(BigDecimal fPrice) {
        this.fPrice = fPrice;
    }
    
}
