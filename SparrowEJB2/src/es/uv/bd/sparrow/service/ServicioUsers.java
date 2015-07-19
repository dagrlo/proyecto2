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

import uv.es.bd.sparrow.entity.User;
import es.uv.sparrow.bo.UserBoRemote;

@Path("users")
public class ServicioUsers {
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;
    
    @EJB
    private UserBoRemote userBo;

    public ServicioUsers() {
   
    }

   
    //http://localhost:8080/SparrowEJB2/rest/list
    @GET
    @Produces("application/json")
    @Path("list")
    public List<User> dameListaUsers() {
    	List<User> lista=userBo.listaUsuarios();
    	for (User user:lista){
    		System.out.println(user.getNombre());
    	}
        return lista;
    }
    
    
    //http://localhost:8080/SparrowEJB2/rest/users/find_APELLIDO
    @GET
    @Produces("application/json")
    @Path("find_{apellido}")
    public List<User> buscaUsuario(@PathParam("apellido") String apellido){
    	System.out.println("->"+apellido);
    	List<User> lista=userBo.buscaApellidos(apellido);
    	for (User user:lista){
    		System.out.println(user.getNombre());
    	}
    	return lista;
    }
    
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

}