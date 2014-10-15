package case2dtos;
/**
 * VendorEJBDTO.java
 *
 *   Created:    9-10-2013 3:03 PM
 *   @author:    Gustavo
 *   Transfer Object that serializes vendor information traveling 
 *   between War and EJB Jar containers
 *   Revisions:   @version 1.0, 09/10/13:
 *      
 */
import java.io.Serializable;

public class ProductEJBDTO implements Serializable {

    public ProductEJBDTO() {
    }
    private String prodcd;
    private int vendorno;
    private String vendorsku;
    private String prodname;
    private double costprice;
    private double msrp;
    private int rop;
    private int qoh;
    private int eoq;
    private int qoo;
    private byte[] qrcodearray;
    private String qrcode;

    public String getProdcd() {
        return prodcd;
    }

    public void setProdcd(String prodcd) {
        this.prodcd = prodcd;
    }

    public int getVendorno() {
        return vendorno;
    }

    public void setVendorno(int vendorno) {
        this.vendorno = vendorno;
    }

    public String getVendorsku() {
        return vendorsku;
    }

    public void setVendorsku(String vendorsku) {
        this.vendorsku = vendorsku;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
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

    public int getQoh() {
        return qoh;
    }

    public void setQoh(int qoh) {
        this.qoh = qoh;
    }

    public int getEoq() {
        return eoq;
    }

    public void setEoq(int eoq) {
        this.eoq = eoq;
    }

    public int getQoo() {
        return qoo;
    }

    public void setQoo(int qoo) {
        this.qoo = qoo;
    }

    public byte[] getQrcodearray() {
        return qrcodearray;
    }

    public void setQrcodearray(byte[] qrcodearray) {
        this.qrcodearray = qrcodearray;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }
}
