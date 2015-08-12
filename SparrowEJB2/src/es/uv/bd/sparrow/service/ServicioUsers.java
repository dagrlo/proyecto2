package es.uv.bd.sparrow.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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

import uv.es.bd.sparrow.entity.Following;
import uv.es.bd.sparrow.entity.User;
import uv.es.sparrow.tokenauth.TokenAuth;
import es.uv.bd.sparrow.service.entities.Follows;
import es.uv.bd.sparrow.service.entities.MiniUser;
import es.uv.bd.sparrow.service.entities.Password;
import es.uv.sparrow.bo.FollowBoRemote;
import es.uv.sparrow.bo.UserBoRemote;
import es.uv.sparrow.bo.UsersGroupBoRemote;


@Path("users")
public class ServicioUsers {
	@SuppressWarnings("unused")
	@Context
	private UriInfo context;

	@EJB
	private UserBoRemote userBo;
	
	@EJB
	private UsersGroupBoRemote userG;
	
	@EJB
	private FollowBoRemote followBo;

	private static final String USERBEAN_ATTR = "UsersBean";

	public ServicioUsers() {
		
	}

	// http://localhost:8080/SparrowEJB2/rest/users/list
	@GET
	@Produces("application/json")
	@Path("list")
	public List<User> dameListaUsers(@Context SecurityContext sc,@Context HttpServletResponse response) {
		List<User> lista = userBo.listaUsuarios();
		System.out.println("USR: " + sc.getUserPrincipal());
		for (User user : lista) {
			System.out.println("[users] USR: " + user.getNombre());
		}
		
		return lista;
	}
	
	@GET
	@Produces("application/json")	
	@Path("getFolloweds_{usr}")
	public ArrayList<MiniUser> dameSeguidos(@PathParam("usr") String usuario){
		User usr=userBo.buscaUsuario(usuario);
		List<Following> listaF=usr.getFolloweds();
		System.out.println("FOLOWEDS: "+listaF.size());
		ArrayList<MiniUser> listaU=new ArrayList<MiniUser>();
		
		for (Following follow:listaF){
			listaU.add(new MiniUser(follow.getFollowed().getUsername(),Integer.toString(follow.getFollowed().getId())));
		}
		
		return listaU;
	}
	
	@GET
	@Produces("application/json")
	@Path("getFollowers_{usr}")
	public ArrayList<MiniUser> dameSeguidores(@PathParam("usr") String usuario){
		User usr=userBo.buscaUsuario(usuario);
		List<Following> listaF=usr.getFollowers();
		ArrayList<MiniUser> listaU=new ArrayList<MiniUser>();
		
		for(Following follow:listaF){
			listaU.add(new MiniUser(follow.getFollower().getUsername(),Integer.toString(follow.getFollower().getId())));
		}
		
		return listaU;
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
		//se me olvido añadir los usuarios al grupo USERS. Sin esto el jdbcRealm daba error
		userG.ponEnGrupo(usuario.getUsername());
		System.out.println("pString: "+newUser.getPasswordString());
		System.out.println("email:"+newUser.getEMail());
		userBo.addUser(newUser);
	}
	
	
	
	@POST
	@Consumes("application/json")
	@Path("editUser")
	public void editUser(User usuario){
		User newUser=userBo.buscaUsuario(usuario.getUsername());
		
		newUser.setNombre(usuario.getNombre());
		newUser.setApellidos(usuario.getApellidos());
		newUser.setSexo(usuario.getSexo());
		newUser.setEMail(usuario.getEMail());
		newUser.setIdioma(usuario.getIdioma());
		newUser.setPasswordString(usuario.getPasswordString());
		newUser.setUsername(usuario.getUsername());
		newUser.setPassword(usuario.getPassword());
				
		userBo.editUser(newUser);
	}
	
	@POST
	@Consumes("application/json")
	@Path("followUser")
	public void sigueUsuario(Follows seguir){
		
		User miUsuario=userBo.buscaUsuario(seguir.getMiUsuario());
		User usuarioSeguido=userBo.buscaUsuarioId(seguir.getIdSeguido());
		
		Following follow=new Following();
		follow.setFollower(miUsuario);
		follow.setFollowed(usuarioSeguido);
		
		try{
			followBo.seguir(follow);
			miUsuario.addFollowed(follow);
			usuarioSeguido.addFollower(follow);
		} catch (Exception e){}
	}
	
	@POST
	@Consumes("application/json")
	@Path("noFollow")
	public void noSeguirUsuario(Follows seguir){
		User miUsuario=userBo.buscaUsuario(seguir.getMiUsuario());
		User usuarioSeguido=userBo.buscaUsuarioId(seguir.getIdSeguido());
		
		Following follow=new Following();
		follow.setFollower(miUsuario);
		follow.setFollowed(usuarioSeguido);
		
		followBo.noSeguir(follow);
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
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("genKey_{password_str}")
	public String generaClave(@PathParam("password_str") String password_str){
		String password="";
		
		try {
			 MessageDigest digest = MessageDigest.getInstance("SHA-256");
		        byte[] hash = digest.digest(password_str.getBytes("UTF-8"));
		        StringBuffer hexString = new StringBuffer();

		        for (int i = 0; i < hash.length; i++) {
		            String hex = Integer.toHexString(0xff & hash[i]);
		            if(hex.length() == 1) hexString.append('0');
		            hexString.append(hex);
		        }
		        password=hexString.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return password;
	}
	
	@GET
	@Produces("application/json")
	@Path("getUsr_{username}")
	public User dameUsuario(@PathParam("username") String username){
		User usuario=userBo.buscaUsuario(username);
		System.out.println("U: "+usuario.getUsername());
		return usuario;
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