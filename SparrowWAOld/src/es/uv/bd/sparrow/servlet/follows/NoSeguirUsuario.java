package es.uv.bd.sparrow.servlet.follows;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uv.es.bd.sparrow.entity.Following;
import uv.es.bd.sparrow.entity.User;
import es.uv.bd.sparrow.users.UsersBean;
import es.uv.sparrow.bo.FollowBoRemote;
import es.uv.sparrow.bo.UserBoRemote;

/**
 * Servlet implementation class NoSeguirUsuario
 */
@WebServlet("/NoSeguirUsuario")
public class NoSeguirUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String USERBEAN_ATTR = "UsersBean";

	@EJB
	private FollowBoRemote followBo;
	
	@EJB
	private UserBoRemote userBo;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoSeguirUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idUser=request.getParameter("id");
		HttpSession session = request.getSession();

		UsersBean usersBean = (UsersBean) session.getAttribute(USERBEAN_ATTR);
		
		User seguido=userBo.buscaUsuarioId((idUser));
		
		Following follow=new Following();
		follow.setFollower(usersBean.getUser());
		
		follow.setFollowed(seguido);
		
		followBo.noSeguir(usersBean.getUser().removeFollowed(follow));
		request.getRequestDispatcher("MainMenu").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
