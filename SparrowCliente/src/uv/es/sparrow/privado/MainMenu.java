package uv.es.sparrow.privado;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;

import uv.es.sparrow.entities.Topics;
import uv.es.sparrow.publico.UserBean;

import com.sun.net.httpserver.Headers;

@WebServlet("/MainMenu")
public class MainMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USERBEAN_ATTR = "UserBean";

       
    public MainMenu() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBean userBean = (UserBean) request.getSession().getAttribute(USERBEAN_ATTR);		
		
		ClientConfig clientConfig=new ClientConfig();
		clientConfig.register(Headers.class);
		
		Client client=ClientBuilder.newClient(clientConfig);
		WebTarget target=client.target("http://localhost:8080/SparrowEJB2/rest/chips/topics");
		
		//QUE CLASE USAMOS? ORIGINAL?  NUEVA SOLO PARA TEMAS?
		//usamos la clase topics
		
		
		
		//Aqui pasamos el header Authorization que lleva como datos la linea Basic y usr:pass en base64
		//Lo del final de la linea es lo que convierte la respuesta JSON en el arraylist
		//usr+pass se pueden enviar de otra forma pero esta parece mas rapida
		
		ArrayList<Topics> listaTemas=target.request(MediaType.APPLICATION_JSON).header("Authorization","Basic "+userBean.getB64()).get(new GenericType<ArrayList<Topics>>(){});
		
		
		
		request.setAttribute("listaTemas", listaTemas);
		request.getRequestDispatcher("/mainPage.jsp").forward(request,response);
	}

}
