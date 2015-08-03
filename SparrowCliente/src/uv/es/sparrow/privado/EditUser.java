package uv.es.sparrow.privado;

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

import org.glassfish.jersey.client.ClientConfig;

import com.sun.net.httpserver.Headers;

import uv.es.sparrow.entities.Topics;
import uv.es.sparrow.entities.Users;
import uv.es.sparrow.publico.UserBean;


@WebServlet("/EditUser")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String USERBEAN_ATTR = "UserBean";
	
       
    public EditUser() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserBean userBean = (UserBean)request.getSession().getAttribute(USERBEAN_ATTR);
		
		
				
		
		System.out.println("edit: "+userBean.getNombre());
		
		Client client = ClientBuilder.newClient(new ClientConfig());
        
		
		Users datos = client.target("http://localhost:8080/SparrowEJB2/rest/users/getUsr_"+userBean.getNombre())                        
        .request(MediaType.APPLICATION_JSON)        
        .get(Users.class);
		
		
		request.setAttribute("datosUsr", datos);
		request.getRequestDispatcher("/editUsr.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password=request.getParameter("password");
		String nombre=request.getParameter("nombre");
		String apellidos=request.getParameter("apellidos");
		String sexo=request.getParameter("sexo");
		String idioma=request.getParameter("idioma");
		String email=request.getParameter("email");
		String passwordSHA="";
		String username=request.getParameter("username");
		
		try {
			 MessageDigest digest = MessageDigest.getInstance("SHA-256");
		        byte[] hash = digest.digest(password.getBytes("UTF-8"));
		        StringBuffer hexString = new StringBuffer();

		        for (int i = 0; i < hash.length; i++) {
		            String hex = Integer.toHexString(0xff & hash[i]);
		            if(hex.length() == 1) hexString.append('0');
		            hexString.append(hex);
		        }
		        passwordSHA=hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Users usrEditado=new Users();
		usrEditado.setApellidos(apellidos);
		usrEditado.setEMail(email);
		usrEditado.setIdioma(idioma);
		usrEditado.setNombre(nombre);
		usrEditado.setPasswordString(password);
		usrEditado.setPassword(passwordSHA);
		usrEditado.setSexo(sexo);
		usrEditado.setUsername(username);
		
		ClientConfig clientConfig=new ClientConfig();
		clientConfig.register(Headers.class);
		
		Client client=ClientBuilder.newClient(clientConfig);
		
		WebTarget target=client.target("http://localhost:8080/SparrowEJB2/rest/users/editUser");
		
		target.request(MediaType.APPLICATION_JSON).post(Entity.entity(usrEditado,MediaType.APPLICATION_JSON),Users.class);
		
		request.getRequestDispatcher("MainMenu").forward(request, response);
		
	}

}
