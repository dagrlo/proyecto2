package es.uv.bd.sparrow.servlet.chips;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uv.es.bd.sparrow.entity.Chip;
import es.uv.sparrow.bo.ChipBoRemote;

/**
 * Servlet implementation class VerChipCompleto
 */
@WebServlet("/VerChipCompleto")
public class VerChipCompleto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ChipBoRemote chipBo;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerChipCompleto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String chipId=request.getParameter("chipActual");
		
		Chip chip=chipBo.damePorId(chipId);
		
		request.setAttribute("chipCompleto", chip);
		request.getRequestDispatcher("/viendoCompleto.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
