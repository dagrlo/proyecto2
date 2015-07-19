package es.uv.bd.sparrow.servlet.chips;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uv.bd.sparrow.users.UsersBean;
import es.uv.sparrow.bo.ChipBoRemote;
import uv.es.bd.sparrow.entity.Chip;

/**
 * Servlet implementation class RespondeChip
 */
@WebServlet("/RespondeChip")
public class RespondeChip extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USERBEAN_ATTR = "UsersBean";

	
	@EJB
	private ChipBoRemote chipBo;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RespondeChip() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String chipId=request.getParameter("chipActual");
		System.out.println("Chip id="+chipId);
		
		Chip chip=chipBo.damePorId(chipId);
		
		request.setAttribute("chipAResponder", chip);
		request.getRequestDispatcher("/responderChip.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String respuesta=request.getParameter("respuesta");
		String chipId=request.getParameter("chipId");
		
		HttpSession session = request.getSession();

		UsersBean usersBean = (UsersBean) session.getAttribute(USERBEAN_ATTR);
		
		
		Chip chip=chipBo.damePorId(chipId);
		Chip chipRespuesta=new Chip();
		chipRespuesta.setTag(chip.getTag());
		chipRespuesta.setText(respuesta);
		chipRespuesta.setUserBean(usersBean.getUser());
		chipRespuesta.setChip(chip);
		chipBo.addChip(chipRespuesta);
		//chip.addChip(chipRespuesta);
		request.getRequestDispatcher("MainMenu").forward(request,response);
	}

}
