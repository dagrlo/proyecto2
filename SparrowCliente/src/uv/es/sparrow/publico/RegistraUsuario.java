package uv.es.sparrow.publico;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.eclipse.persistence.internal.oxm.conversion.Base64;
import org.glassfish.jersey.client.ClientConfig;

import uv.es.sparrow.entities.Users;

import com.sun.net.httpserver.Headers;


@WebServlet("/RegistraUsuario")
public class RegistraUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegistraUsuario() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/registerUser.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre=(request.getParameter("nombre"));
		String apellidos=(request.getParameter("apellidos"));
		String sexo=request.getParameter("sexo");
		String idioma=request.getParameter("idioma");
		String username=request.getParameter("username");
		String password_str=request.getParameter("password");
		String password="";
		
		//la clave va en sha256
		
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
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		String email=request.getParameter("email");
		
		
		
		Users usuarioNuevo=new Users(0,apellidos,email,idioma,nombre,password,password_str,sexo,username);
		
		ClientConfig clientConfig=new ClientConfig();
		clientConfig.register(Headers.class);
		
		Client client=ClientBuilder.newClient(clientConfig);
		
		WebTarget target=client.target("http://localhost:8080/SparrowEJB2/rest/users/addUser");
		
		target.request(MediaType.APPLICATION_JSON).post(Entity.entity(usuarioNuevo,MediaType.APPLICATION_JSON),Users.class);
		
		request.getRequestDispatcher("/registered.jsp").forward(request,response);
	}

}
