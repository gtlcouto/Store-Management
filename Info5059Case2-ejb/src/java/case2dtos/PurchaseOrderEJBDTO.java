package case2dtos;
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
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PurchaseOrderEJBDTO implements Serializable {
    public PurchaseOrderEJBDTO() {}
    private int Ponumber;
    private int Vendorno;
    private BigDecimal Total;
    private Date Podate;
    private ArrayList<PurchaseOrderLineitemEJBDTO> items;

    public int getPonumber() {
        return Ponumber;
    }

    public void setPonumber(int Ponumber) {
        this.Ponumber = Ponumber;
    }

    public int getVendorno() {
        return Vendorno;
    }

    public void setVendorno(int Vendorno) {
        this.Vendorno = Vendorno;
    }

    public BigDecimal getTotal() {
        return Total;
    }

    public void setTotal(BigDecimal Total) {
        this.Total = Total;
    }

    public String getPodate() {
        SimpleDateFormat dt = new SimpleDateFormat("MMMM dd, YYYY");
        return dt.format(Podate);
    }

    public void setPodate(Date Podate) {
        this.Podate = Podate;
    }

    public ArrayList<PurchaseOrderLineitemEJBDTO> getItems() {
        return items;
    }

    public void setItems(ArrayList<PurchaseOrderLineitemEJBDTO> items) {
        this.items = items;
    }
   
    
}
