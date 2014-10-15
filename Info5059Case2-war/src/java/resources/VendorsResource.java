/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import case2dtos.VendorEJBDTO;
import case2ejbs.VendorFacadeBean;
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
@Path("vendors")
@RequestScoped
public class VendorsResource {
    

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of VendorsResource
     */
    public VendorsResource() {
    }
    
    @EJB
    private VendorFacadeBean vbf;

    @GET
    @Path("getAVendor/{vendorno}")
    @Produces("application/json")
    public VendorEJBDTO getAVendor(@PathParam("vendorno") int vendorno){
        return vbf.getVendor(vendorno);
    }
}
