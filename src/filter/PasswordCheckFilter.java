package filter;

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

import auth.service.User;

/**
 * Servlet Filter implementation class LoginCheckFilter
 */

@WebFilter("/auth/check/*")
public class PasswordCheckFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public PasswordCheckFilter() {
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
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("authUser");
		String requestedURI = request.getRequestURI();
		
		if(session == null || user==null) {
			response.sendRedirect(request.getContextPath() + "/login.do");
		} else if (user.getOneTimeCheck() == 0) {
			session.setAttribute("requestedURI", requestedURI);
			response.sendRedirect(request.getContextPath() + "/auth/checkPassword.do");
		} else {
			chain.doFilter(req, res);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
