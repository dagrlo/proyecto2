package es.uv.bd.sparrow.servlet.user;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uv.es.bd.sparrow.entity.User;
import es.uv.sparrow.bo.UserBoRemote;

/**
 * Servlet implementation class ListaUser
 */
@WebServlet("/ListaUser")
public class ListaUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private UserBoRemote userBo;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListaUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
		List<User> usuarios;
		usuarios=userBo.listaUsuarios();
		
		request.setAttribute("usuarios", usuarios);
		request.getRequestDispatcher("/ListaUsuarios.jsp").forward(request, response);
		
	}

}
