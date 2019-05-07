

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

import model.User;

/**
 * Servlet Filter implementation class Filtre
 */
@WebFilter(filterName="filter")
public class Filtre implements Filter {

    /**
     * Default constructor. 
     */
    public Filtre() {
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
		// TODO Auto-generated method stub
		HttpServletRequest httpReq=(HttpServletRequest) request;
		String referrer =httpReq.getHeader("referer");
		if(referrer==null) {
			HttpSession session = ((HttpServletRequest) request).getSession();
			User user = (User) session.getAttribute( "client" );

			HttpServletResponse httpRep=(HttpServletResponse) response;
			if(user==null) {
				httpRep.sendRedirect("/E-Shop/Log");
			}else {
				if(user.getType()==1) {
					httpRep.sendRedirect("/E-Shop/Log/User");
    			}
			}
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
