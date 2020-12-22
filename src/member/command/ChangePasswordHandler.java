package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import member.service.ChangePasswordRequest;
import member.service.ChangePasswordService;
import mvc.command.CommandHandler;


public class ChangePasswordHandler implements CommandHandler {
	private static final String FORM_VIEW = "changePasswordForm";
	private ChangePasswordService changePasswordService = new ChangePasswordService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processGet(req,res);
		} else	if(req.getMethod().equalsIgnoreCase("POST")) {
			return processPost(req,res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processGet(HttpServletRequest req, HttpServletResponse res) {
		
		return FORM_VIEW;
	}		
		
	private String processPost(HttpServletRequest req, HttpServletResponse res) {
		User user = (User) req.getSession().getAttribute("authUser");
		String id = user.getId();
		String password = req.getParameter("password");
		String passwordConfirm = req.getParameter("passwordConfirm");

		ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest(
				id, password, passwordConfirm);
		Map <String, Boolean> errors = new HashMap<>();		
		req.setAttribute("errors", errors);
		
		changePasswordRequest.validate(errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		changePasswordService.changePassword(changePasswordRequest);
		
		return "changePasswordSuccess";
	}
	
	
}
