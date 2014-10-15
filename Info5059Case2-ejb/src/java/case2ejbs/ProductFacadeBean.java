/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package case2ejbs;

import case2dtos.ProductEJBDTO;
import case2models.VendorModel;
import case2models.ProductModel;
import case2dtos.VendorEJBDTO;
import case2models.VendorModel;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author GCouto
 * Revisions: 
 * Added getAllProductsForVendor(int){};
 */
@Stateless
@LocalBean
public class ProductFacadeBean {
    
    @PersistenceContext
    private EntityManager em;

    public boolean addProduct(ProductEJBDTO prod) {
        ProductModel pm;
        VendorModel vm;
        boolean addOk = false;
        
        try{
            
            pm = new ProductModel(prod.getProdcd(), prod.getVendorsku(), prod.getProdname(), prod.getCostprice(), prod.getMsrp(), prod.getRop(), prod.getEoq(), prod.getQoh(), prod.getQoo());
            vm = em.find(VendorModel.class, prod.getVendorno());
            pm.setVendorno(vm);
            pm.setQrcode(prod.getQrcodearray());
            em.persist(pm);
            em.flush();
            addOk = true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return addOk;
        
    }
    
    public ProductEJBDTO getProdInfo(String prodcd) {
        ProductModel pm;
        ProductEJBDTO dto = new ProductEJBDTO();
        try{
            pm = em.find(ProductModel.class, prodcd);
            dto.setProdname(pm.getProdnam());
            dto.setCostprice(pm.getCostprice());
            dto.setMsrp(pm.getMsrp());
            dto.setProdcd(prodcd);
            dto.setQoh(pm.getQoh());
            dto.setQoo(pm.getQoo());
            dto.setRop(pm.getRop());
            dto.setEoq(pm.getEoq());
            dto.setVendorsku(pm.getVensku());
            dto.setQrcodearray((byte[]) pm.getQrcode());
            
        }catch ( Exception e ) {
            System.out.println(e.getMessage());
        }
        return dto;
        
    }
    

    public ArrayList<ProductEJBDTO> getAllProductsForVendor(int vendorno) {
        ArrayList<ProductEJBDTO> products = new ArrayList<>();
        VendorModel vm;
        
        try {
            vm = em.find(VendorModel.class, vendorno);
            Query qry = em.createNamedQuery("ProductModel.findByVendorno");
            qry.setParameter("vendorno", vm);
            List<ProductEJBDTO> prods = qry.getResultList();
            
            for (Object o : prods) {
                ProductModel prod = (ProductModel) o;
                ProductEJBDTO oneProd = new ProductEJBDTO();
                oneProd.setProdcd(prod.getProdcd());
                oneProd.setCostprice(prod.getCostprice());
                oneProd.setEoq(prod.getEoq());
                oneProd.setMsrp(prod.getMsrp());
                oneProd.setProdname(prod.getProdnam());
                oneProd.setQoh(prod.getQoh());
                oneProd.setQoo(prod.getQoo());
//                oneProd.setQrcodearray((byte[]) prod.getQrcode());
                oneProd.setRop(prod.getRop());
                oneProd.setVendorno(vendorno);
                oneProd.setVendorsku(prod.getVensku());
                products.add(oneProd);
                
            } 
            
        }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        return products;
    }


}
