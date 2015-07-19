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
 * Servlet implementation class SeguirUsuario
 */
@WebServlet("/SeguirUsuario")
public class SeguirUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USERBEAN_ATTR = "UsersBean";
	
	@EJB
	private UserBoRemote userBo;
	@EJB
	private FollowBoRemote followBo;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeguirUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idUser=request.getParameter("id");
		
		User seguido=userBo.buscaUsuarioId(idUser);
		HttpSession session = request.getSession();
		UsersBean usersBean = (UsersBean) session.getAttribute(USERBEAN_ATTR);
		User miUsuario=usersBean.getUser();
		
		Following follow=new Following();
		follow.setFollowed(seguido);
		follow.setFollower(miUsuario);
		
		Following follow2=new Following();
		follow2.setFollowed(miUsuario);
		follow2.setFollower(seguido);
		
		//evitamos que error al seguir a un usr que ya seguimos
		try{
			//esto si que guarda los datos en la bd
			followBo.seguir(follow);
			//esto hace falta?
			//esto actualiza los datos del bean solo?
			
			//miUsuario.addFollowed(follow);
			miUsuario.addFollowed(follow);
			//usersBean.getUser().addFollowed(follow);
			seguido.addFollower(follow);
		} catch (Exception e){}
		
		
		request.getRequestDispatcher("MainMenu").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
