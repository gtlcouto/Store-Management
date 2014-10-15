/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodels;

import case2dtos.VendorEJBDTO;
import case2ejbs.VendorFacadeBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import models.*;

/**
 *
 * @author GCouto
 * A Sample backing bean that exposes 2 properties and one method
 * revised:
 * 11/8/2013 - updated getVendor - this method will receive a vendor dto instance from facade
 * then populate the member variable from its contents
 * 11/8/2013 - Changed to use EJBDTO details on add vendor
 */
@Named(value = "vendorViewModel")
@SessionScoped
public class VendorViewModel implements Serializable {
    @EJB
    private VendorFacadeBean vbf;
    public VendorViewModel() {}
    

    
    private ArrayList<SelectItem> vendornos = null;
    private int vendorno;
    private String name;
    private String address;
    private String city;
    private String province;
    private String postalcode;
    private String phone;
    private String type;
    private String email;
    private String msg;
    
    public String getName() {
        return (name);
    }
    
    public void setName(String uName) {
        this.name = uName;
    }
    
    public String getAddress() {
        return (address);
    }
    
    public void setAddress(String uAddress) {
        this.address = uAddress;
    }
    
    public String getCity() {
        return (city);
    }
    
    public void setCity(String uCity) {
        this.city = uCity;
    }
    public String getProvince() {
        return (province);
    }
    
    public void setProvince(String uProvince) {
        this.province = uProvince;
    }
    public String getPostalCode() {
        return (postalcode);
    }
    
    public void setPostalCode(String uPostalCode) {
        this.postalcode = uPostalCode;
    }
    public String getPhone() {
        return (phone);
    }
    
    public void setPhone(String uPhone) {
        this.phone = uPhone;
    }
    public String getType() {
        return (type);
    }
    
    public void setType(String uType) {
        this.type = uType;
    }
    
    public String getEmail() {
        return (email);
    }
    
    public void setEmail(String uEmail) {
        this.email = uEmail;
    } 
    
    public String getMsg() {
        return (msg);
    }
    
    public void setMsg(String uMsg) {
        this.msg = uMsg;
    } 
    
    
    
    public int getVendorNo() {
    return (vendorno);
}
    public void setVendorNo(int vNo) {
        this.vendorno = vNo;
    }
    
    public void addVendor() {
        try {
            VendorEJBDTO details = new VendorEJBDTO();
            details.setAddress1(address);
            details.setName(name);
            details.setCity(city);
            details.setProvince(province);
            details.setPhone(phone);
            details.setType(type);
            details.setEmail(email);
            details.setPostalCode(postalcode);
            vendorno = vbf.addVendor(details); //call to facade
            
            if(vendorno > 0) {
                msg = "Vendor " + vendorno + " Added!";
            } else {
                msg = "Vendor not added - check log";
            }
            
        } catch (Exception e) {
            msg = "Error: " + e.getMessage();
        }
    }
    
//    public void doSomething() {
//        try {
//            msg = "Hey " + name + " the time is now:"
//                    + new java.util.Date().toString();
//        } catch (Exception e) {
//            msg = "Error: " + e.getMessage();
//        }
//    }
    
//    public void findVendorOneName() {
//        try {
//            name = model.dbGetVendorOneName();
//            msg = "Vendor 1's Name = " + name;
//            
//        } catch (Exception e) {
//            msg = e.getMessage();
//            
//        }
//        
//    }
    
    public void getVendor(){
        try {
            VendorEJBDTO details = vbf.getVendor(vendorno);

            if(details != null)
            {
                address = details.getAddress1();
                city = details.getCity();
                province = details.getProvince();
                postalcode = details.getPostalCode();
                phone = details.getPhone();
                type = details.getType();
                name = details.getName();
                email = details.getEmail();
            }
                        
        } catch (Exception e) {
            msg = e.getMessage();
            
        }        
    }
    
    public ArrayList<SelectItem> getVendornos() {
       ArrayList<SelectItem> vendornos = new ArrayList<SelectItem>();
       int[] nos = null;
        try{
             nos = vbf.getVendornos();
            for (Integer i : nos) {
                SelectItem item = new SelectItem(Integer.toString(i));
                vendornos.add(item);
            }
        } catch (Exception e) {
            System.out.println("cant get vendornos " + e);
        }
        return vendornos;
    }
    

}
