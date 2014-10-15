/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package case2ejbs;

import case2dtos.ProductEJBDTO;
import case2dtos.PurchaseOrderEJBDTO;
import case2dtos.PurchaseOrderLineitemEJBDTO;
import case2models.ProductModel;
import case2models.PurchaseOrderLineItemsModel;
import case2models.PurchaseOrderModel;
import case2models.VendorModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author GCouto
 */

@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@Stateless
@LocalBean
public class POFacadeBean {

    @PersistenceContext
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public int addPO(PurchaseOrderEJBDTO poDTO) {
        
        PurchaseOrderModel pm;
        VendorModel vm;
        int poRowID = -1;
        Date poDate = new java.util.Date();
        
        try {
            vm = em.find(VendorModel.class, poDTO.getVendorno());
            pm = new PurchaseOrderModel(0, poDTO.getTotal(), poDate);
            pm.setVendorno(vm);
            em.persist(pm);
            
            for (PurchaseOrderLineitemEJBDTO line : poDTO.getItems()) {
                addPOLine(line, pm);
            }
            poRowID = pm.getPonumber().intValue();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return poRowID;
        
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    private void addPOLine(PurchaseOrderLineitemEJBDTO line, PurchaseOrderModel pm) {
        PurchaseOrderLineItemsModel polm;
        try {
           
            polm = new PurchaseOrderLineItemsModel();
            polm.setPonumber(pm);
            polm.setPrice(line.getPrice());
            polm.setProdcd(line.getProdcd());
            polm.setQty(line.getQty());
            em.persist(polm);
            em.flush();
            
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public ArrayList<PurchaseOrderEJBDTO> getAllPoInfo(int vendorno)
    {
        ArrayList<PurchaseOrderEJBDTO> poList = new ArrayList();
        ArrayList<PurchaseOrderLineitemEJBDTO> items;
        List<PurchaseOrderLineItemsModel> lines;
        List<PurchaseOrderModel> pos;       
        VendorModel vm;
        PurchaseOrderLineItemsModel lm;
        ProductModel prm;
        PurchaseOrderEJBDTO poDTO;
        
        try
        {
            vm = em.find(VendorModel.class, vendorno);
            Query qry = em.createNamedQuery("PurchaseOrderModel.findByVendorno");
            qry.setParameter("vendorno", vm);
            pos = qry.getResultList();
            
            for( PurchaseOrderModel pom : pos )
            {
                poDTO = new PurchaseOrderEJBDTO();
                items = new ArrayList<>();
                lm = em.find(PurchaseOrderLineItemsModel.class, pom.getPonumber());
                Query q = em.createNamedQuery("PurchaseOrderLineItemsModel.findByponumber");
                q.setParameter("ponumber", pom);
                lines = q.getResultList();
                
                for( PurchaseOrderLineItemsModel polim : lines )
                {
                    PurchaseOrderLineitemEJBDTO oneLine = new PurchaseOrderLineitemEJBDTO();
                    oneLine.setPonumber(polim.getPonumber().getPonumber());
                    oneLine.setPrice(polim.getPrice());
                    oneLine.setProdcd(polim.getProdcd());
                    oneLine.setQty(polim.getQty());
                    oneLine.setLineid(polim.getLineid());
                    prm = em.find(ProductModel.class, polim.getProdcd());
                    oneLine.setProdnam(prm.getProdnam());
                    items.add(oneLine);                    
                }
                poDTO.setItems(items);      
                
                DateFormat dt = new SimpleDateFormat("MMMM dd, YYYY");
                poDTO.setPodate(pom.getPodate());                
                poDTO.setTotal(pom.getAmount());
                poDTO.setPonumber(pom.getPonumber());
                poDTO.setVendorno(pom.getVendorno().getVendorno());
                poList.add(poDTO);
                
            }
            
        }
        catch( Exception ex)
        {
            System.out.println("Error getting all products for vendor");
        }            
        
        return poList;
    }

    public ArrayList<PurchaseOrderEJBDTO> testPO(int vendorno) {
        ArrayList<PurchaseOrderEJBDTO> retVal = new ArrayList<>();
        VendorModel vm;
        
        
        try {
            vm = em.find(VendorModel.class, vendorno);
            Query qry = em.createNamedQuery("PurchaseOrderModel.findByVendorno");
            qry.setParameter("vendorno", vm);
            List<ProductEJBDTO> poList = qry.getResultList();
            
            for (Object o : poList) {
                PurchaseOrderModel poModel = (PurchaseOrderModel) o;
                PurchaseOrderEJBDTO onePO = new PurchaseOrderEJBDTO();
                onePO.setPonumber(poModel.getPonumber());
                onePO.setVendorno(vendorno);
                onePO.setTotal(poModel.getAmount());
                onePO.setItems(getLineItemsForPO(poModel.getPonumber()));                
                onePO.setPodate(poModel.getPodate());
                retVal.add(onePO);
                
            }
        } catch(Exception e){
            System.out.println("Error getting Vendornos from Facade - " + e.getMessage());
                    
        }
        return retVal;
        
    }
    
    public ArrayList<PurchaseOrderLineitemEJBDTO> getLineItemsForPO(int ponumber) {
        ArrayList<PurchaseOrderLineitemEJBDTO> retVal = new ArrayList<>();
        PurchaseOrderLineItemsModel lm;        
        List<PurchaseOrderLineItemsModel> lines;
        PurchaseOrderModel pom;
        
        ProductModel prm;
        
        
        try {
                
                pom = em.find(PurchaseOrderModel.class, ponumber);
                Query q = em.createNamedQuery("PurchaseOrderLineItemsModel.findByponumber");
                q.setParameter("ponumber", pom);
                lines = q.getResultList();
                
                for( PurchaseOrderLineItemsModel polim : lines )
                {
                    PurchaseOrderLineitemEJBDTO oneLine = new PurchaseOrderLineitemEJBDTO();
                    oneLine.setPonumber(polim.getPonumber().getPonumber());
                    oneLine.setPrice(polim.getPrice());
                    oneLine.setProdcd(polim.getProdcd());
                    oneLine.setQty(polim.getQty());
                    oneLine.setLineid(polim.getLineid());
                    prm = em.find(ProductModel.class, polim.getProdcd());
                    oneLine.setProdnam(prm.getProdnam());
                    retVal.add(oneLine);                    
                }
        } catch(Exception e){
            System.out.println("Error getting lines from Facade - " + e.getMessage());
                    
        }
        return retVal;
        
    }

}
