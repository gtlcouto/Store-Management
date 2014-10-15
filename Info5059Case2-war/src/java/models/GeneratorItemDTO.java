/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author GCouto
 */
@Named(value = "singleItem")
@RequestScoped
public class GeneratorItemDTO implements Serializable {
    public GeneratorItemDTO() {    }
    
    private String prodcd;
    private String prodname;
    private int qty;
    private double price;
    private double ext;

    public String getProdcd() {
        return prodcd;
    }

    public void setProdcd(String prodcd) {
        this.prodcd = prodcd;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getExt() {
        return ext;
    }

    public void setExt(double ext) {
        this.ext = ext;
    }

    
}
