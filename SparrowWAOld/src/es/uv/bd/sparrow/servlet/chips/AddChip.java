package es.uv.bd.sparrow.servlet.chips;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uv.es.bd.sparrow.entity.Chip;
import es.uv.bd.sparrow.users.UsersBean;
import es.uv.sparrow.bo.ChipBoRemote;

/**
 * Servlet implementation class AddChip
 */
@WebServlet("/AddChip")
public class AddChip extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USERBEAN_ATTR = "UsersBean";

	@EJB
	private ChipBoRemote chipBo;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddChip() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/nuevoChip.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String texto=request.getParameter("texto");
		String tag=request.getParameter("tag");
		HttpSession session = request.getSession();

		UsersBean usersBean = (UsersBean) session.getAttribute(USERBEAN_ATTR);
		
		Chip chip=new Chip();
		chip.setTag(tag);
		chip.setText(texto);
		chip.setUserBean(usersBean.getUser());
		
		chipBo.addChip(chip);
		request.getRequestDispatcher("MainMenu").forward(request, response);
	}

}
