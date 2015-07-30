package uv.es.sparrow.publico;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.persistence.internal.oxm.conversion.Base64;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String USERBEAN_ATTR = "UserBean";

    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre=request.getParameter("usr");
		String pass=request.getParameter("pass");
		String datos=nombre+":"+pass;
		String b64=new String(Base64.base64Encode(datos.getBytes()));
		
		UserBean usrBean=new UserBean(nombre,pass,b64);
		request.getSession().setAttribute(USERBEAN_ATTR,usrBean);
		request.getRequestDispatcher("MainMenu").forward(request,response);
	}

}
