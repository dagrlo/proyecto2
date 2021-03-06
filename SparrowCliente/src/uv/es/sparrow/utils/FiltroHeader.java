package uv.es.sparrow.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class FiltroHeader
 */
@WebFilter("/*")
public class FiltroHeader implements Filter {

    /**
     * Default constructor. 
     */
    public FiltroHeader() {
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
		
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		
		Enumeration hdr=req.getHeaderNames();
		ArrayList<String> lista=(ArrayList<String>) res.getHeaderNames();
		
		
		System.out.println("REQUEST");
		while (hdr.hasMoreElements()){
			String key=(String) hdr.nextElement();
			System.out.println(key+" = "+req.getHeader(key));
		}
		System.out.println("===================================================================");
		
		System.out.println("RESPONSE");
		for (int i=0;i<lista.size();i++){
			System.out.println(lista.get(i));
		}
		System.out.println("===================================================================");
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
