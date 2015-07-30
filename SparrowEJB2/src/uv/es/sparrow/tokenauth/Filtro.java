package uv.es.sparrow.tokenauth;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class Filtro
 */
@WebFilter("/lalalal/*")
public class Filtro implements Filter {

    /**
     * Default constructor. 
     */
    public Filtro() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest rq = (HttpServletRequest) request;
		String path=rq.getRequestURI();
		
		TokenAuth auth=TokenAuth.getInstance();
		System.out.println("Filtrando: "+path );
		if (path.endsWith("/rest/users/prueba")){
			//String token=request.getHeaderString(name)
			Enumeration<String> headerNames = (Enumeration<String>) rq.getHeaderNames();
			while (headerNames.hasMoreElements()){
				System.out.println(headerNames.nextElement());
			}
			
		}
		System.out.println("Token:"+rq.getHeader("auth_token"));
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
