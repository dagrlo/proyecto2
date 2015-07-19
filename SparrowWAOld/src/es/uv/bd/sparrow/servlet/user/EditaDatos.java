package es.uv.bd.sparrow.servlet.user;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uv.es.bd.sparrow.entity.User;
import es.uv.bd.sparrow.users.UsersBean;
import es.uv.sparrow.bo.UserBoRemote;

/**
 * Servlet implementation class EditaDatos
 */
@WebServlet("/EditaDatos")
public class EditaDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String USERBEAN_ATTR = "UsersBean";
	
	@EJB
	private UserBoRemote userBo;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditaDatos() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/editUsr.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		UsersBean usersBean = (UsersBean) session.getAttribute(USERBEAN_ATTR);
		//System.out.println("edit:"+usersBean);
		String password=request.getParameter("password");
		String nombre=request.getParameter("nombre");
		String apellidos=request.getParameter("apellidos");
		String sexo=request.getParameter("sexo");
		String idioma=request.getParameter("idioma");
		//String usr=usersBean.getUser().getUsername();
		//System.out.println("usr:"+usr);
		User usrEditado=usersBean.getUser();//new User();
		//usrEditado=userBo.buscaUsuario(usr);
		
		//System.out.println("nombre:"+usrEditado.getNombre());
		usrEditado.setPassword(password);
		usrEditado.setNombre(nombre);
		usrEditado.setApellidos(apellidos);
		usrEditado.setSexo(sexo);
		usrEditado.setIdioma(idioma);
		
		
		userBo.editUser(usrEditado);
		
		request.getRequestDispatcher("MainMenu").forward(request, response);
	}

}
