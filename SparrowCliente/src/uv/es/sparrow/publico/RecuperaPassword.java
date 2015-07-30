package uv.es.sparrow.publico;

import java.io.IOException;

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

import uv.es.sparrow.entities.Password;
import uv.es.sparrow.entities.Users;


@WebServlet("/RecuperaPassword")
public class RecuperaPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RecuperaPassword() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/resetPassword.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre=request.getParameter("nombre");		
		String email=request.getParameter("email");
		Password password=new Password(email,nombre);
		Password newPassword=new Password();
		
		ClientConfig clientConfig=new ClientConfig();
		clientConfig.register(Headers.class);
		
		Client client=ClientBuilder.newClient(clientConfig);
		
		WebTarget target=client.target("http://localhost:8080/SparrowEJB2/rest/users/resetPassword");
		
		//Se recibe el mismo tipo que se envia
		
		newPassword=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(password,MediaType.APPLICATION_JSON),Password.class);
		
		//Envio el pass en el email
		System.out.println("Pass: "+newPassword.getEmail());
		request.setAttribute("password", newPassword.getEmail());
		request.getRequestDispatcher("/getPassword.jsp").forward(request, response);
	}

}
