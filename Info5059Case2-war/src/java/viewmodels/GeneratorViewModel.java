package viewmodels;

import case2dtos.ProductEJBDTO;
import case2dtos.PurchaseOrderLineitemEJBDTO;
import case2dtos.PurchaseOrderEJBDTO;
import case2ejbs.POFacadeBean;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import models.*;


/**
 *
 * @author GCouto
 * revised:
 * 11/8/2013 -- Updated getVendorProductCodes method
 */
@Named(value = "generatorViewModel")
@SessionScoped
public class GeneratorViewModel implements Serializable {
    @EJB(name = "pofb")
    private POFacadeBean pofb;
    public GeneratorViewModel() {}
    
    
    @Inject
    VendorViewModel vendorView;
    
    @Inject
    ProductViewModel productView;

    
    private boolean pickedVendor = false;
    private boolean pickedItem = false;
    private String displayName;
    private String msg;
    private int poNum;
    private String vendorno;
    private String prodcd;
    private double total;
    private String prodname;
    private String qty;
    private int eoq;
    private double subTotal;
    private double tax;
    private ArrayList<SelectItem> items;
    private ArrayList<GeneratorItemDTO> poArray = new ArrayList<GeneratorItemDTO>();
    private ArrayList<PurchaseOrderLineitemEJBDTO> itemsEJB;
    
    //Funtion changeVendor
    //sets data for when a vendor is selected by the user
    public void changeVendor() {
        setMsg("");
        setPickedVendor(true);
        vendorView.setVendorNo(Integer.parseInt(vendorno));
        vendorView.getVendor();
        displayName = vendorView.getName();
        items = getVendorProductCodes();
//        getVendorProductCodes();
        
        //clear out or init other key field for new vendor
        
        poNum = -1;
        prodcd = "";
        subTotal = 0;
        qty = "EOQ";
        pickedItem = false;
        
    }    
    
    //Function addToPo
    //takes: nothing
    //outputs: fills the poArray with a productorderDTO from the product selected by the user.
    public void addToPO()
    {
        setMsg("");
        setPickedItem(true);
        
        if(poArray != null) {
            for (int i = 0; i<poArray.size(); i++) {
                if(poArray.get(i).getProdcd().equalsIgnoreCase(prodcd)) {
                    subTotal -= poArray.get(i).getExt();
                    poArray.remove(i);
                }
            }
        }
        
        GeneratorItemDTO singleProd = new GeneratorItemDTO();
        productView.setProdcd(prodcd);
        productView.productChanged();
        singleProd.setProdcd(prodcd);
        if("EOQ".equals(qty)){
            singleProd.setQty(productView.getEoq());
        }else {
            singleProd.setQty(Integer.parseInt(qty));
        }
        singleProd.setProdname(productView.getProdname());
        singleProd.setPrice(productView.getCostprice());
        singleProd.setExt(productView.getCostprice() * singleProd.getQty());
        
        
        subTotal += singleProd.getExt();
        
        
        
        tax = subTotal*0.15;
        total = subTotal + tax;
        poArray.add(singleProd);
        
        
    }
    
//    Function addPOtoDB
//    takes: Nothing
//    outputs: Msg with sucess or failure
//    use: call AddPO from the poModel to add the information in the poArray to the databse.
    public void poAdd() 
    {
        int ponum;
        
        try {    
          itemsEJB = new ArrayList();
          for(GeneratorItemDTO item : poArray) {
              if (item.getQty() > 0) {
                  PurchaseOrderLineitemEJBDTO ejbItem = new PurchaseOrderLineitemEJBDTO();
                  ejbItem.setPonumber(0);
                  ejbItem.setPrice(BigDecimal.valueOf(item.getPrice() * item.getQty()));
                  ejbItem.setProdcd(item.getProdcd());
                  ejbItem.setQty(item.getQty());
                  itemsEJB.add(ejbItem);
              }
          }
          PurchaseOrderEJBDTO poDTO = new PurchaseOrderEJBDTO();
          poDTO.setItems(itemsEJB);
          poDTO.setVendorno(Integer.parseInt(vendorno));
          poDTO.setTotal(BigDecimal.valueOf(total));
          ponum = pofb.addPO(poDTO);
          msg = "PO " + ponum + " - Added!";
        } catch (Exception e) {
            msg = "POAdd failed " + e;
        }
        pickedItem = false;
        
    }
    
    public ArrayList<SelectItem> getVendorProductCodes() {
        ArrayList<SelectItem> retNamesAndCodes = new ArrayList<>();
        
        try {
            for (ProductEJBDTO details : productView.getAllVendorProducts(Integer.parseInt(vendorno)))   {
                SelectItem item = new SelectItem(details.getProdcd());
                retNamesAndCodes.add(item);
            }
        } catch (Exception e) {
            msg = "getVendorProductCodes failed " + e;
        }
        return retNamesAndCodes;
    }
    
        public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public boolean isPickedVendor() {
        return pickedVendor;
    }

    public void setPickedVendor(boolean pickedVendor) {
        this.pickedVendor = pickedVendor;
    }

    public boolean isPickedItem() {
        return pickedItem;
    }

    public void setPickedItem(boolean pickedItem) {
        this.pickedItem = pickedItem;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getPoNum() {
        return poNum;
    }

    public void setPoNum(int poNum) {
        this.poNum = poNum;
    }

    public String getVendorno() {
        return vendorno;
    }

    public void setVendorno(String vendorno) {
        this.vendorno = vendorno;
    }

    public String getProdcd() {
        return prodcd;
    }

    public void setProdcd(String prodcd) {
        this.prodcd = prodcd;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.setTotal(total);
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public int getEoq() {
        return eoq;
    }

    public void setEoq(int eoq) {
        this.eoq = eoq;
    }


    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public ArrayList<SelectItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<SelectItem> items) {
        this.items = items;
    }

    public ArrayList<GeneratorItemDTO> getPoArray() {
        return poArray;
    }

    public void setPoArray(ArrayList<GeneratorItemDTO> poArray) {
        this.poArray = poArray;
    }

    public ArrayList<PurchaseOrderLineitemEJBDTO> getItemsEJB() {
        return itemsEJB;
    }

    public void setItemsEJB(ArrayList<PurchaseOrderLineitemEJBDTO> itemsEJB) {
        this.itemsEJB = itemsEJB;
    }
    
}