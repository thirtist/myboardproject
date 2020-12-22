package auth.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.LoginRequest;
import auth.service.LoginService;
import auth.service.User;
import member.service.NotFoundIdException;
import member.service.NotMatchPasswordException;
import mvc.command.CommandHandler;

public class CheckPasswordHandler implements CommandHandler {
	private static final String FORM_VIEW = "checkPasswordForm";
	private LoginService loginService = new LoginService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processGet(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processPost(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processGet(HttpServletRequest req, HttpServletResponse res) {

		return FORM_VIEW;
	}

	private String processPost(HttpServletRequest req, HttpServletResponse res) {
		User user = (User) req.getSession(false).getAttribute("authUser");
		String id = user.getId();
		LoginRequest loginRequest = new LoginRequest
		(id, req.getParameter("password"));

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		loginRequest.validate(errors);
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		String uri = (String)req.getSession().getAttribute("requestedURI");
		try {
			user = loginService.login(loginRequest);
			user.setOneTimeCheck(1);
			req.getSession().setAttribute("authUser", user);
			res.sendRedirect(uri);
		} catch (NotFoundIdException e) {
			errors.put("notFoundId", true);
			return FORM_VIEW;
		} catch (NotMatchPasswordException e) {
			errors.put("notMatchPassword", true);
			return FORM_VIEW;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
