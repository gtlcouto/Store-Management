package case2ejbs;

import case2dtos.VendorEJBDTO;
import case2models.VendorModel;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Gustavo
 *  VendorFacadeBean - stateless EJB to act as a facade to the JPA Vendor Model
 *  Create - 10/18/2013
 *  Revised - 11/8/2013
 *  New method getVendor(int)
 */
@Stateless
@LocalBean
public class VendorFacadeBean {

    @PersistenceContext
    private EntityManager em;

    public int addVendor(VendorEJBDTO ven) {

        VendorModel vm;
        int retVal = -1;

        try {
            vm = new VendorModel(null, ven.getAddress1(), ven.getCity(),
                    ven.getProvince(), ven.getPostalCode(),
                    ven.getPhone(), ven.getType(), ven.getName(),
                    ven.getEmail());
            em.persist(vm);
            em.flush();
            retVal = vm.getVendorno().intValue();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return retVal;
    }
    
    public int[] getVendornos()
    {
        int[] retVal = null;
        try {
           Query qry = em.createNamedQuery("VendorModel.findAllVendorNos");
           List<?> nos = qry.getResultList();
           retVal = new int[nos.size()];
           
           for(int i = 0; i < nos.size(); i++)
           {
               retVal[i] = ((Integer) nos.get(i)).intValue();
           }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("EJB vendornos from entity bean " + retVal.length);
        return retVal;
    }
    
    public List<Integer> getVendorNos() {
        List<Integer> nos = null;
        try {
            Query qry = em.createNamedQuery("VendorModel.findAllVendorNos");
            nos = qry.getResultList();
        } catch(Exception e){
            System.out.println("Error getting Vendornos from Facade - " + e.getMessage());
                    
        }
        return nos;
    }
    
    public VendorEJBDTO getVendor(int vendorno) {
        VendorModel vm;
        VendorEJBDTO dto = new VendorEJBDTO();
        try{
            vm = em.find(VendorModel.class, vendorno);
            dto.setAddress1(vm.getAddress1());
            dto.setCity(vm.getCity());
            dto.setEmail(vm.getEmail());
            dto.setName(vm.getName());
            dto.setPhone(vm.getPhone());
            dto.setPostalCode(vm.getPostalcode());
            dto.setProvince(vm.getProvince());
            dto.setType(vm.getVendortype());
            dto.setVendorno(vendorno);
        }catch ( Exception e ) {
            System.out.println(e.getMessage());
        }
        return dto;
    }
      
}
 