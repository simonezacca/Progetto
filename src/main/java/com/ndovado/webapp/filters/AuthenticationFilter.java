package com.ndovado.webapp.filters;

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

import com.ndovado.webapp.beans.core.UtenteBean;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter({ "/gestore/*", "/locatario/*" })
public class AuthenticationFilter implements Filter {

	private FilterConfig config;
    /**
     * Default constructor. 
     */
    public AuthenticationFilter() {
       
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		config = null;
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		UtenteBean ubean = (UtenteBean) ((HttpServletRequest) request).getSession().getAttribute("utenteBean");
		
		if (ubean!=null && ubean.isLogged()) {
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse) response).sendRedirect("/ndovado/index.xhtml");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.config = config;
	}

}
