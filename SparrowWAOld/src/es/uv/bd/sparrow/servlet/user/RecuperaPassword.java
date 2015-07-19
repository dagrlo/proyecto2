package es.uv.bd.sparrow.servlet.user;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uv.sparrow.bo.UserBoRemote;

/**
 * Servlet implementation class RecuperaPassword
 */
@WebServlet("/RecuperaPassword")
public class RecuperaPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private UserBoRemote userBo;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecuperaPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/resetPassword.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre=request.getParameter("nombre");		
		String email=request.getParameter("email");
		String password=userBo.recuperaPassword(nombre, email);
		request.setAttribute("password", password);
		request.getRequestDispatcher("/getPassword.jsp").forward(request, response);
		
	}

}
