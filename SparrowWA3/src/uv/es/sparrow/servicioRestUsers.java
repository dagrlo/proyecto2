package uv.es.sparrow;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import es.uv.sparrow.bo.UserBoRemote;

@Path("/users")
public class servicioRestUsers {
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;

    @EJB
    private UserBoRemote userBo;
    
    /**
     * Default constructor. 
     */
    public servicioRestUsers() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Retrieves representation of an instance of servicioRestUsers
     * @return an instance of String
     */
    @GET
    @Path("/prueba")
    @Produces("application/xml")
    public String getXml() {
        return "prueba";
    }

    /**
     * PUT method for updating or creating an instance of servicioRestUsers
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }

}