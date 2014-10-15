package viewmodels;

import case2dtos.ProductEJBDTO;
import case2ejbs.ProductFacadeBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import models.*;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;


/**
 *
 * @author GCouto
 * 11/2/2013 -- Removed @Inject model -- added EJB -- changed addProduct()
 *              added new variables/getters/setters for qrcode and qrcodebin --
 *              commented out remaining methods yet to be updated
 * 11/8/2013 -- Updated getAllProductsForVendor to obtain an ArrayList of
 *              Products dto from Facade.
 */
@Named(value = "productViewModel")
@SessionScoped
public class ProductViewModel implements Serializable {
    @EJB
    private ProductFacadeBean pfb;
    
    @Inject
    VendorViewModel vendorView;
    
    public ProductViewModel() {}
    
    private ArrayList<SelectItem> prodcds = null;
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
    private String qrcode;
    private String msg;
    private byte[] qrcodebin;
    private ArrayList<ProductEJBDTO> vendorProducts;
    private boolean pickedVendor = false;
    private boolean pickedItem = false;
    private ArrayList<SelectItem> items;
    private ArrayList<SelectItem> vendornos;


    
        //Funtion changeVendor
    //sets data for when a vendor is selected by the user
    public void changeVendor() {
        setMsg("");
        items = getProdsByVendor(vendorno);
        pickedVendor = true;
        
        //clear out or init other key field for new vendor
//        prodcd = "";
//        pickedVendor = false;
//        pickedItem = false;
        
    }    
    
//    public void changeProd() {
//        setPickedItem(true);
//        loadProdInfo();
//       
//    }
//    
//    public void loadProdInfo()
//    {
//        ProductEJBDTO prodInfo;
//        prodInfo = pfb.getProdInfo(prodcd);
//        prodname = prodInfo.getProdname();
//        vendorsku = prodInfo.getVendorsku();
//        costprice = prodInfo.getCostprice();
//        msrp = prodInfo.getMsrp();
//        rop = prodInfo.getRop();
//        qoh = prodInfo.getQoh();
//        eoq = prodInfo.getEoq();
//        qoo = prodInfo.getQoo();
//    }
 public void productChanged() {            
               
        try {
            msg = "";
            ProductEJBDTO dto = pfb.getProdInfo(prodcd);
            if(dto != null) {
                
                this.setCostprice(dto.getCostprice());
                this.setEoq(dto.getEoq());
                this.setMsrp(dto.getMsrp());
                this.setProdcd(dto.getProdcd());
                this.setProdname(dto.getProdname());
                this.setQoh(dto.getQoh());
                this.setQoo(dto.getQoo());
                this.setQrcode("");               
                this.setRop(dto.getRop());
                this.setVendorsku(dto.getVendorsku());
            }
        } catch(Exception e) {
            System.out.print("PVM - Change Product - " + e.getMessage());
        }
    }

    public ArrayList<SelectItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<SelectItem> items) {
        this.items = items;
    }
    public boolean isPickedVendor() {
        return pickedVendor;
    }

    public void setPickedVendor(boolean pickedVendor) {
        this.pickedVendor = pickedVendor;
    }

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
    
    public boolean isPickedItem() {
        return pickedItem;
    }

    public void setPickedItem(boolean pickedItem) {
        this.pickedItem = pickedItem;
    }

    public double getCostprice() {
        return costprice;
    }

    public void setCostprice(double costprice) {
        this.costprice = costprice;
    }
    
    
    public ArrayList<SelectItem> getVendornos() {
        return vendornos;
    }

    public void setVendornos(ArrayList<SelectItem> vendornos) {
        this.vendornos = vendornos;
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
    
    public String getMsg() {
        return (msg);
    }
    
    public void setMsg(String uMsg) {
        this.msg = uMsg;
    } 
    public void addProduct() {
        boolean addOk = false;
        try {
            ProductEJBDTO details = new ProductEJBDTO();
            details.setProdcd(prodcd);
            details.setCostprice(costprice);            
            details.setEoq(eoq);
            details.setMsrp(msrp);
            details.setProdname(prodname);
            details.setQoh(qoh);
            details.setQoo(qoo);
            details.setRop(rop);
            details.setVendorno(vendorno);
            details.setVendorsku(vendorsku);
            qrcodebin = QRCode.from(qrcode).to(ImageType.PNG).stream().toByteArray();
            details.setQrcodearray(qrcodebin);
            addOk = pfb.addProduct(details);
            
            if(addOk) {
                msg = "Product " + prodname + " Added!";
            } else {
                msg = "Product not added - check log";
            }
            
        } catch (Exception e) {
            msg = "Error: " + e.getMessage();
        }
    }
//    public int getEoqByProdcd(String prod)
//    {
//        int eoqj;
//        eoqj = model.getEoqByProdcd(prod);
//        return eoqj;
//    }
//    
    public ArrayList<SelectItem> getProdsByVendor(int vendorno) {
       ArrayList<SelectItem> prodcds = new ArrayList<>();
        try{
                ArrayList<ProductEJBDTO> retArray = pfb.getAllProductsForVendor(vendorno);
            for (ProductEJBDTO s : retArray) {
                SelectItem item = new SelectItem(s.getProdcd());
                prodcds.add(item);
            }
        } catch (Exception e) {
            System.out.println("cant get prods " + e);
        }
        return prodcds;
    }
//    
//    public void getProdByProdCd(String prodcdInput)
//    {
//        ArrayList<String> item = new ArrayList<String>();
//        try{
//            item = model.getProdByProdcd(prodcdInput);
//            vendorsku = item.get(0);
//            prodname = item.get(1);
//            costprice = Double.parseDouble(item.get(2));
//            msrp = Double.parseDouble(item.get(3));
//            rop = Integer.parseInt(item.get(4));
//            qoh  = Integer.parseInt(item.get(5));
//            eoq  = Integer.parseInt(item.get(6));
//            qoo = Integer.parseInt(item.get(7));
//        }catch (Exception e) {
//            System.out.println("cant get prod from prodcd" + e);
//        }
//    }
    public void getAllProductsForVendor(int vendorno) {
        try {
            vendorProducts = pfb.getAllProductsForVendor(vendorno);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public ArrayList<ProductEJBDTO> getAllVendorProducts(int vendorno){
        try {
        vendorProducts = pfb.getAllProductsForVendor(vendorno);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        return vendorProducts;
    }
    
  

    public ArrayList<SelectItem> getProdcds() {
        return prodcds;
    }

    public void setProdcds(ArrayList<SelectItem> prodcds) {
        this.prodcds = prodcds;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public byte[] getQrcodebin() {
        return qrcodebin;
    }

    public void setQrcodebin(byte[] qrcodebin) {
        this.qrcodebin = qrcodebin;
    }

    
    
}
