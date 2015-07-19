package uv.es.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uv.es.beans.HolaBeanRemote;
import uv.es.beans.Persona;

@Path("prueba")
public class ServicioHola1 {
  

    @EJB
    private HolaBeanRemote holaBean;
    /**
     * Default constructor. 
     */
    public ServicioHola1() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Retrieves representation of an instance of ServicioHola1
     * @return an instance of String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getXml() {
        return holaBean.dameHola();
    }
    
    @GET
    @Path("persona")
    @Produces(MediaType.APPLICATION_XML)
    public Persona damePersona() throws Exception{
    	/*System.out.println("dame");
    	holaBean.creaPersona();    	
    	Persona persona=holaBean.damePersona();*/
    	Persona persona=new Persona("Nombre","Apellidos","Direccion","Telefono");
    	System.out.println(persona.getNombre());
    	return persona;
    }
    
    @GET
    @Path("json")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Persona> damePersonaJSON() throws Exception{
    	/*System.out.println("dame");
    	holaBean.creaPersona();    	
    	Persona persona=holaBean.damePersona();*/
    	//Persona persona=new Persona("Nombre","Apellidos","Direccion","Telefono");
    	//System.out.println(persona.getNombre());
    	return holaBean.dameLista();
    }

    /**
     * PUT method for updating or creating an instance of ServicioHola1
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }

}