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

import uv.es.sparrow.entities.Chips;
import uv.es.sparrow.publico.UserBean;

import com.sun.net.httpserver.Headers;


@WebServlet("/ViewByTag")
public class ViewByTag extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USERBEAN_ATTR = "UserBean";
       
    public ViewByTag() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tag=request.getParameter("tag");
		UserBean userBean = (UserBean) request.getSession().getAttribute(USERBEAN_ATTR);
		
		ClientConfig clientConfig=new ClientConfig();
		clientConfig.register(Headers.class);
		
		Client client=ClientBuilder.newClient(clientConfig);
		//http://localhost:8080/SparrowEJB2/rest/chips/tag_TAG DEL CHIP
		WebTarget target=client.target("http://localhost:8080/SparrowEJB2/rest/chips/tag_"+tag);
		
		ArrayList<Chips> listaChips=target.request(MediaType.APPLICATION_JSON).header("Authorization","Basic "+userBean.getB64()).get(new GenericType<ArrayList<Chips>>(){});
						
		request.setAttribute("listaChips", listaChips);
		request.setAttribute("tagActual", tag);
		request.getRequestDispatcher("/viendoPorTag.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
