package es.uv.bd.sparrow.service;

import java.util.List;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import uv.es.bd.sparrow.entity.User;
import uv.es.sparrow.tokenauth.TokenAuth;
import es.uv.bd.sparrow.service.entities.Password;
import es.uv.sparrow.bo.UserBoRemote;


@Path("users")
public class ServicioUsers {
	@SuppressWarnings("unused")
	@Context
	private UriInfo context;

	@EJB
	private UserBoRemote userBo;

	private static final String USERBEAN_ATTR = "UsersBean";

	public ServicioUsers() {

	}

	// http://localhost:8080/SparrowEJB2/rest/list
	@GET
	@Produces("application/json")
	@Path("list")
	public List<User> dameListaUsers(@Context SecurityContext sc) {
		List<User> lista = userBo.listaUsuarios();
		System.out.println("USR: " + sc.getUserPrincipal());
		for (User user : lista) {
			System.out.println("[users] USR: " + user.getNombre());
		}
		return lista;
	}

	//http://localhost:8080/SparrowEJB2/rest/users/addUser
	//le pasamos un objeto Users que se parece a User por json
	@POST
	@Consumes("application/json")
	@Path("addUser")
	public void addUser(User usuario){
		System.out.println("post: "+usuario.getPassword()+" - str - "+usuario.getPasswordString());
		User newUser=new User();
		newUser.setNombre(usuario.getNombre());
		newUser.setApellidos(usuario.getApellidos());
		newUser.setSexo(usuario.getSexo());
		newUser.setEMail(usuario.getEMail());
		newUser.setIdioma(usuario.getIdioma());
		newUser.setPasswordString(usuario.getPasswordString());
		newUser.setUsername(usuario.getUsername());
		newUser.setPassword(usuario.getPassword());
		newUser.setId(0);
		System.out.println("pString: "+newUser.getPasswordString());
		userBo.addUser(newUser);
	}
	
	
	//http://localhost:8080/SparrowEJB2/rest/users/resetPassword
	//Le pasamos un objeto Password y devuelve un objeto Password
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("resetPassword")
	public Password resetPassword(Password pass){
		String recuperado=userBo.recuperaPassword(pass.getNombre(), pass.getEmail());
		System.out.println("rec:"+recuperado);
		//como se tiene que enviar el mismo que tipo que se ha recibido utilizo email para poner el password nuevo
		
		return new Password(recuperado,"");
	}
	
	// http://localhost:8080/SparrowEJB2/rest/users/find_APELLIDO
	@GET
	@Produces("application/json")
	@Path("find_{apellido}")
	public List<User> buscaUsuario(@PathParam("apellido") String apellido) {
		System.out.println("->" + apellido);
		List<User> lista = userBo.buscaApellidos(apellido);
		for (User user : lista) {
			System.out.println(user.getNombre());
		}
		return lista;
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("prueba")
	public String prueba() {
		return "prueba";
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("dameheaders")
	public String probandoHeaders() {
		return "Probando Headers";
	}

	@GET
	@Path("login")
	@Produces(MediaType.TEXT_PLAIN)
	public String login(){
		return "1";
	}
	
	
	// autorizacion por tokens

	@Path("loginTokens_{usr}_{pass}")
	@Produces("application/json")
	@GET	
	public Response login(@PathParam("usr") String usr,	@PathParam("pass") String pass,@Context HttpServletResponse response) {
		TokenAuth auth = TokenAuth.getInstance();
		System.out.println(usr+"--"+pass);
		try {
			String token = auth.login(usr, pass);
			
			JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();

			jsonObjBuilder.add("auth_token", token);

			JsonObject jsonObj = jsonObjBuilder.build();
			System.out.println("devuelve token");
			
		
			return Response.status(200).header("kk",jsonObj).build();
		} catch (final LoginException e) {
			JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();

			jsonObjBuilder.add("message",
					"Problem matching service key, username and password");

			JsonObject jsonObj = jsonObjBuilder.build();

			return getNoCacheResponseBuilder(Response.Status.UNAUTHORIZED)
					.entity(jsonObj.toString()).build();
		}
	}

	private Response.ResponseBuilder getNoCacheResponseBuilder(
			Response.Status status) {

		CacheControl cc = new CacheControl();

		cc.setNoCache(true);

		cc.setMaxAge(-1);

		cc.setMustRevalidate(true);

		return Response.status(status).cacheControl(cc);
	}

}