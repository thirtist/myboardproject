package auth.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class LogoutHandler implements CommandHandler{
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.getSession(false).invalidate();
		res.sendRedirect(req.getContextPath()+"/index.jsp");
		return null;
	}
}
