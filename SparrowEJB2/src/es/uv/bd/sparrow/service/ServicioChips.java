package es.uv.bd.sparrow.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import uv.es.bd.sparrow.entity.Chip;
import es.uv.sparrow.bo.ChipBoRemote;

@Path("chips")
public class ServicioChips {
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;
    
    @EJB
    private ChipBoRemote chipBo;

    /**
     * Default constructor. 
     */
    public ServicioChips() {
        // TODO Auto-generated constructor stub
    }

    //http://localhost:8080/SparrowEJB2/rest/chips/topics
    @GET
    @Produces("application/json")
    @Path("topics")
    public List<Chip> dameTemas() {
        return chipBo.listaTemas();
    }
    
    //http://localhost:8080/SparrowEJB2/rest/chips/tag_TAG DEL CHIP
    @GET
    @Produces("application/json")
    @Path("tag_{tag}")
    public List<Chip> dameTemasPorTag(@PathParam("tag") String tag){
    	return chipBo.listaPorTag(tag);
    }

 
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

}