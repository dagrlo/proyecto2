package es.uv.bd.sparrow.servlet.user;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uv.es.bd.sparrow.entity.Chip;
import uv.es.bd.sparrow.entity.Following;
import uv.es.bd.sparrow.entity.User;
import es.uv.bd.sparrow.users.UsersBean;
import es.uv.sparrow.bo.ChipBoRemote;
import es.uv.sparrow.bo.FollowBoRemote;
import es.uv.sparrow.bo.UserBoRemote;

/**
 * Servlet implementation class Menu
 */
@WebServlet("/MainMenu")
public class MainMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USERBEAN_ATTR = "UsersBean";

	
	@EJB
	private ChipBoRemote chipBo;
	
	@EJB
	private FollowBoRemote followBo;
	
	@EJB
	private UserBoRemote userBo;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainMenu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Chip> temas=chipBo.listaTemas();

		HttpSession session = request.getSession();
		
		//followBo.actualizar();

		UsersBean usersBean = (UsersBean) session.getAttribute(USERBEAN_ATTR);
		List<Following> followers=usersBean.getUser().getFollowers();
		
		User yo=userBo.buscaUsuarioId(Integer.toString(usersBean.getUser().getId()));
		System.out.println("menu. user:"+yo.getUsername());
		System.out.println("followeds:"+yo.getFolloweds().size());
		List<Following> followeds=usersBean.getUser().getFolloweds();  
			
		System.out.println("ID:"+usersBean.getUser().getId());
		System.out.println("followers "+followers.size());
		System.out.println("followeds "+followeds.size());
		
		if (temas==null){
			request.setAttribute("hayTemas", new Boolean(false));
			System.out.println("Sin temas");
		} else {
			request.setAttribute("listaTemas", temas);
			request.setAttribute("hayTemas", new Boolean(true));
			for (Chip chip : temas) {
				System.out.println("-->"+chip.getText());
			}
			System.out.println("TEmas cargados");
			System.out.println(temas);
		}
		
		request.setAttribute("listaFollowers", followers);
		request.setAttribute("listaFolloweds", followeds);
		
		request.getRequestDispatcher("/mainPage.jsp").forward(request,response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
