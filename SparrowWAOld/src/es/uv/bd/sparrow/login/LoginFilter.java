package es.uv.bd.sparrow.login;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uv.bd.sparrow.users.UsersBean;

/**
 * Servlet Filter implementation class loggingFilter
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

	private static final String USERBEAN_ATTR = "UsersBean";

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest rq = (HttpServletRequest) request;
		HttpServletResponse responseHTTP = (HttpServletResponse) response;
		HttpServletRequest requestHTTP = (HttpServletRequest) request;
		HttpSession session = rq.getSession(true);
		String nextPage = requestHTTP.getRequestURI().substring(requestHTTP.getContextPath().length());
		UsersBean usersBean = (UsersBean)session.getAttribute(USERBEAN_ATTR);
		System.out.println("Filter. bean:"+usersBean);
				
		String uri = rq.getRequestURI();

		if (uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".png")
				|| uri.endsWith(".jpg") || uri.endsWith(".otf")
				|| nextPage.equals("/registerUser.jsp")
				|| nextPage.equals("/getPasswordForm.jsp")
				|| nextPage.equals("/resetPassword.jsp")
				|| nextPage.equals("/logError")
				|| nextPage.equals("/ForgetPassword")) {
			rq.getRequestDispatcher(nextPage).forward(requestHTTP, responseHTTP);
		} else {
			if (nextPage.equals("/")) {
				System.out.println("default page");
				nextPage = "Entrar";//index.jsp";
			}
			if (usersBean!=null){
				if (usersBean.getLogged()){
					System.out.println("LOGEADO");
					//nextPage="/mainPage.jsp";
				} else {
					System.out.println("NO LOGEADO");
					//nextPage="index.jsp";
				}
			} else {
				System.out.println("NO HAY BEAN");
				usersBean=new UsersBean();
				session.setAttribute(USERBEAN_ATTR, usersBean);
				nextPage="Entrar";//index.jsp";
			}
			rq.getRequestDispatcher(nextPage).forward(requestHTTP, responseHTTP);
		}
		System.out.println("final next: " + nextPage);

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
