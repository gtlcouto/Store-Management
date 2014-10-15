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

public class VendorEJBDTO implements Serializable {

    public VendorEJBDTO() {
    }
    private int vendorno;
    private String name;
    private String address1;
    private String city;
    private String province;
    private String postalCode;
    private String phone;
    private String type;
    private String email;

    public int getVendorno() {
        return this.vendorno;
    }

    public void setVendorno(int inValue) {
        this.vendorno = inValue;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String inValue) {
        this.name = inValue;
    }

    public String getAddress1() {
        return this.address1;
    }

    public void setAddress1(String inValue) {
        this.address1 = inValue;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String inValue) {
        this.city = inValue;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String inValue) {
        this.province = inValue;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String inValue) {
        this.postalCode = inValue;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String inValue) {
        this.phone = inValue;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String inValue) {
        this.type = inValue;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String inValue) {
        this.email = inValue;
    }
}