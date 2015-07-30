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

import com.sun.net.httpserver.Headers;

import uv.es.sparrow.entities.Topics;
import uv.es.sparrow.entities.Users;
import uv.es.sparrow.publico.UserBean;


@WebServlet("/AddTopic")
public class AddTopic extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USERBEAN_ATTR = "UserBean";
       
    public AddTopic() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/nuevoChip.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBean userBean = (UserBean) request.getSession().getAttribute(USERBEAN_ATTR);
						
		String texto=request.getParameter("texto");
		String tag=request.getParameter("tag");
		
		Topics tema=new Topics();
		tema.setTag(tag);
		tema.setText(texto);
		tema.setUser(userBean.getNombre());
		
		ClientConfig clientConfig=new ClientConfig();
		clientConfig.register(Headers.class);
		
		Client client=ClientBuilder.newClient(clientConfig);
		
		WebTarget target=client.target("http://localhost:8080/SparrowEJB2/rest/chips/addtopic");
		
		target.request(MediaType.APPLICATION_JSON).post(Entity.entity(tema,MediaType.APPLICATION_JSON),Topics.class);
		
		request.getRequestDispatcher("MainMenu").forward(request, response);
	}

}
