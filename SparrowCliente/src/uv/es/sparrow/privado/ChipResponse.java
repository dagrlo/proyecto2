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

import uv.es.sparrow.entities.Chips;
import uv.es.sparrow.entities.Topics;
import uv.es.sparrow.publico.UserBean;


@WebServlet("/ChipResponse")
public class ChipResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USERBEAN_ATTR = "UserBean";
       
    public ChipResponse() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String chipId=request.getParameter("chipActual");
		request.setAttribute("chipAResponder", chipId);
		request.getRequestDispatcher("/responderChip.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String respuesta=request.getParameter("respuesta");
		String chipId=request.getParameter("chipId");
		UserBean userBean = (UserBean) request.getSession().getAttribute(USERBEAN_ATTR);
		
		
		Chips chipRespuesta=new Chips(respuesta,userBean.getNombre(),Integer.parseInt(chipId));
		
		ClientConfig clientConfig=new ClientConfig();
		clientConfig.register(Headers.class);
		
		Client client=ClientBuilder.newClient(clientConfig);
		
		WebTarget target=client.target("http://localhost:8080/SparrowEJB2/rest/chips/response");
		
		target.request(MediaType.APPLICATION_JSON).post(Entity.entity(chipRespuesta,MediaType.APPLICATION_JSON),Chips.class);
		request.getRequestDispatcher("MainMenu").forward(request,response);
	}

}
