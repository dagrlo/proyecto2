package es.uv.bd.sparrow.servlet.user;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uv.bd.sparrow.users.UsersBean;
import es.uv.sparrow.bo.UserBoRemote;


/**
 * Servlet implementation class Entrar
 */
@WebServlet("/Entrar")
public class Entrar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USERBEAN_ATTR = "UsersBean";

	@EJB
	private UserBoRemote userBo;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Entrar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/index.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre=request.getParameter("username");
		String pass=request.getParameter("password");
		
		
		HttpSession session = request.getSession();
		
		UsersBean usersBean = (UsersBean)session.getAttribute(USERBEAN_ATTR);
		
		if (userBo.validaUsuario(nombre, pass)){
			usersBean.setLogged(true);
			usersBean.setUser(userBo.buscaUsuario(nombre));
			
			System.out.println("BEAN="+usersBean.getLogged());
			session.setAttribute("usuario", usersBean);
			//request.setAttribute("datos", usersBean);
			System.out.println("al menu");
			request.getRequestDispatcher("MainMenu").forward(request,response);
		} else {
			request.getRequestDispatcher("/logError.jsp").forward(request,response);
		}
		
		
    	
    	
	}

}
