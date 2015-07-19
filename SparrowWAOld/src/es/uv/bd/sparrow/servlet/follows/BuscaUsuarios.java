package es.uv.bd.sparrow.servlet.follows;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class BuscaUsuarios
 */
@WebServlet("/BuscaUsuarios")
public class BuscaUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UserBoRemote userBo;
	
	private static final String USERBEAN_ATTR = "UsersBean";

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscaUsuarios() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/searchUsr.jsp").forward(request, response);
	}

	
	private int dameMiIndex(List<User> lista, String nombre){
		int result=-10;
		
		for (int i=0;i<lista.size();i++){
			if (lista.get(i).getUsername().equals(nombre)){
				result=i;
			}
		}
		return result;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String apellidos=request.getParameter("apellidos");
		
		List<User> listaUsuarios=userBo.buscaApellidos(apellidos);
		
		HttpSession session = request.getSession();

		UsersBean usersBean = (UsersBean) session.getAttribute(USERBEAN_ATTR);
		
		//me quito de la lista para no poder seguirme a mi mismo
		int yo=dameMiIndex(listaUsuarios, usersBean.getUser().getUsername());
		
		if (yo!=-10){
			listaUsuarios.remove(yo);	
		}
		
		
		if ((listaUsuarios==null)||(listaUsuarios.size()==0)){
			String error="No se han encontrado coincidencias";
			request.setAttribute("error", error);
			request.getRequestDispatcher("/searchUsr.jsp").forward(request, response);	
		} else {
			
			request.setAttribute("lista", listaUsuarios);
			request.getRequestDispatcher("/searchUsrOk.jsp").forward(request, response);
		}
	}

}
