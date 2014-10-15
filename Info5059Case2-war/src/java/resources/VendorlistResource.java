/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import case2dtos.PurchaseOrderEJBDTO;
import case2ejbs.POFacadeBean;
import case2ejbs.VendorFacadeBean;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * REST Web Service
 *
 * @author GCouto
 */

@Path("vendorlist")
@RequestScoped
public class VendorlistResource {

    @Context
    private UriInfo context;
    
    

    /**
     * Creates a new instance of VendorlistResource
     */
    public VendorlistResource() {
    }
    @EJB
    private VendorFacadeBean vbf;
    
    @EJB
    private POFacadeBean pobf;
 
    @GET
    @Path("vendorlist")
    @Produces("application/json")
    public List<Integer> getAllVendorNos() {
        return vbf.getVendorNos();
    }
    
    @GET
    @Path("POList/{vendorno}")
    @Produces("application/json")
    public ArrayList<PurchaseOrderEJBDTO> getAllPoInfo(@PathParam("vendorno") int vendorno){
        return pobf.getAllPoInfo(vendorno);
    }
    
    


}
