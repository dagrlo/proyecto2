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
 * Servlet implementation class VerPorTag
 */
@WebServlet("/VerPorTag")
public class VerPorTag extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ChipBoRemote chipBo;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerPorTag() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tag=request.getParameter("tag");
		
		List<Chip> chips=chipBo.listaPorTag(tag);
		
		if (chips==null){
			request.setAttribute("hayChips", new Boolean(false));
		} else {
			request.setAttribute("hayChips", new Boolean(true));
			request.setAttribute("listaChips", chips);
		}
		
		request.setAttribute("tagActual", tag);
		request.getRequestDispatcher("/viendoPorTag.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
