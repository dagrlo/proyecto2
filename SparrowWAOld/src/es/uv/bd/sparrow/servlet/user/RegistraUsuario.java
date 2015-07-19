package es.uv.bd.sparrow.servlet.user;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uv.sparrow.bo.UserBoRemote;
import uv.es.bd.sparrow.entity.User;

/**
 * Servlet implementation class RegistraUsuario
 */
@WebServlet("/RegistraUsuario")
public class RegistraUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private UserBoRemote userBo;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistraUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/registerUser.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre=(request.getParameter("nombre"));
		String apellidos=(request.getParameter("apellidos"));
		String sexo=request.getParameter("sexo");
		String idioma=request.getParameter("idioma");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		System.out.println("Existe:"+userBo.buscaUsuario(username));
		if (userBo.buscaUsuario(username)==null){
			User newUser=new User();
			newUser.setNombre(nombre);
			newUser.setApellidos(apellidos);
			newUser.setSexo(sexo);
			newUser.setEMail(email);
			newUser.setIdioma(idioma);
			
			newUser.setUsername(username);
			newUser.setPassword(password);
			newUser.setId(0);
			
		    userBo.addUser(newUser);
		    request.getRequestDispatcher("/registered.jsp").forward(request,response);
		} else {
			request.getRequestDispatcher("/yaExiste.jsp").forward(request,response);
		}
		
		
		
	}

}
