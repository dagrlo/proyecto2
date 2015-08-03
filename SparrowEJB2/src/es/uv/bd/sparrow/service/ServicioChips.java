package es.uv.bd.sparrow.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import uv.es.bd.sparrow.entity.Chip;
import uv.es.bd.sparrow.entity.User;
import es.uv.bd.sparrow.service.entities.Chips;
import es.uv.bd.sparrow.service.entities.Topics;
import es.uv.sparrow.bo.ChipBoRemote;
import es.uv.sparrow.bo.UserBoRemote;

@Path("chips")
public class ServicioChips {
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;
    
    @EJB
    private ChipBoRemote chipBo;
    
    @EJB
    private UserBoRemote userBo;

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
    public ArrayList<Topics> dameTemas(@Context SecurityContext sc) {
    	
    	System.out.println("listasize: "+chipBo.listaTemas().size());
    	System.out.println("[chips] USR: "+sc.getUserPrincipal());
    	
    	List<Chip> listaChips=chipBo.listaTemas();
    	ArrayList<Topics> listaTemas=new ArrayList<Topics>();
    	
    	for (Chip chip:listaChips){
    		listaTemas.add(new Topics(chip.getId(),chip.getTag(),chip.getText(),chip.getUserBean().getUsername()));
    	}
    	
    	return listaTemas;
    	
        
    }
    
    //http://localhost:8080/SparrowEJB2/rest/chips/tag_TAG DEL CHIP
    @GET
    @Produces("application/json")
    @Path("tag_{tag}")
    public ArrayList<Chips> dameTemasPorTag(@PathParam("tag") String tag){
    	List<Chip> chips=chipBo.listaPorTag(tag);
    	ArrayList<Chips> listaChips=new ArrayList<Chips>();
    	//public Chips(String texto, String autor, int id) 
    	for (Chip chip:chips){
    		listaChips.add(new Chips(chip.getText(),chip.getUserBean().getUsername(),chip.getId()));
    	}
    	
    	return listaChips;
    }

    //http://localhost:8080/SparrowEJB2/rest/chips/addtopic
    @POST
    @Consumes("application/json")
    @Path("addtopic")
    public void ponTema(@Context SecurityContext sc, Topics tema){
    	User usuario=userBo.buscaUsuario(tema.getUser());
    	Chip temaNuevo=new Chip();
    	temaNuevo.setTag(tema.getTag());
    	temaNuevo.setText(tema.getText());
    	temaNuevo.setUserBean(usuario);
    	chipBo.addChip(temaNuevo);
    }
    
    //http://localhost:8080/SparrowEJB2/rest/chips/response
    @POST
    @Consumes("application/json")
    @Path("response")
    public void respondeChip(@Context SecurityContext sc, Chips chip){
    	Chip original=chipBo.damePorId(Integer.toString(chip.getId()));
    	Chip respuesta=new Chip();
    	User usuario=userBo.buscaUsuario(chip.getAutor());
    	respuesta.setTag(original.getTag());
    	respuesta.setText(chip.getTexto());
    	respuesta.setUserBean(usuario);
    	respuesta.setChip(original);
    	chipBo.addChip(respuesta);
    }
 
}