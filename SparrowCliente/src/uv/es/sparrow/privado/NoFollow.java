package uv.es.sparrow.privado;

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

import uv.es.sparrow.entities.Follows;
import uv.es.sparrow.publico.UserBean;


@WebServlet("/NoFollow")
public class NoFollow extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USERBEAN_ATTR = "UserBean";

       
    public NoFollow() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idUser=request.getParameter("id");
		
		UserBean userBean = (UserBean) request.getSession().getAttribute(USERBEAN_ATTR);
		System.out.println("[NOFOLLOW]");
		Follows seguir=new Follows(userBean.getNombre(),idUser);		
		
		ClientConfig clientConfig=new ClientConfig();
		
		
		Client client=ClientBuilder.newClient(clientConfig);
		
		WebTarget target=client.target("http://localhost:8080/SparrowEJB2/rest/users/noFollow");
		
		target.request(MediaType.APPLICATION_JSON).post(Entity.entity(seguir,MediaType.APPLICATION_JSON),Follows.class);
		
		request.getRequestDispatcher("MainMenu").forward(request, response);
	}

}
