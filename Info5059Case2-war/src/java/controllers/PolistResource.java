/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import case2dtos.PurchaseOrderEJBDTO;
import case2dtos.PurchaseOrderLineitemEJBDTO;
import case2ejbs.POFacadeBean;
import case2ejbs.VendorFacadeBean;
import java.util.ArrayList;
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

/**
 * REST Web Service
 *
 * @author GCouto
 */
@Path("polist")
@RequestScoped
public class PolistResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PolistResource
     */
    public PolistResource() {
    }
    @EJB
    private VendorFacadeBean vbf;
    
    @EJB
    private POFacadeBean pobf;
    
    @GET
    @Path("POList/{vendorno}")
    @Produces("application/json")
    public ArrayList<PurchaseOrderEJBDTO> getAllPoInfo(@PathParam("vendorno") int vendorno){
        return pobf.testPO(vendorno);
    }
    
    @GET
    @Path("POLine/{pono}")
    @Produces("application/json")
    public ArrayList<PurchaseOrderLineitemEJBDTO> getAllLineInfo(@PathParam("pono") int pono){
        return pobf.getLineItemsForPO(pono);
    }
}
