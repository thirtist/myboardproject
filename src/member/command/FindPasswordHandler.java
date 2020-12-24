package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.NotMatchNameOrEmail;
import auth.service.User;
import member.model.Member;
import member.service.FindPasswordRequest;
import member.service.FindPasswordService;
import member.service.NotFoundIdException;
import mvc.command.CommandHandler;


public class FindPasswordHandler implements CommandHandler {
	private static final String FORM_VIEW = "findPasswordForm";
	private FindPasswordService findPasswordService = new FindPasswordService();
	
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
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String email = req.getParameter("email");

		FindPasswordRequest findPasswordRequest = new FindPasswordRequest(
				id, name, email);
		Map <String, Boolean> errors = new HashMap<>();		
		req.setAttribute("errors", errors);
		
		findPasswordRequest.validate(errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		Member member = null;
		
		try{
			member = findPasswordService.checkValidate(findPasswordRequest);
		} catch (NotFoundIdException e) {
			errors.put("NotFoundIdException", true);
			return FORM_VIEW;
		} catch (NotMatchNameOrEmail e) {
			errors.put("NotMatchNameOrEmail", true);
			return FORM_VIEW;
		} 
		
		req.setAttribute("find",true);
		req.setAttribute("passwordQuestion",member.getPasswordQuestion());
		
		String passwordAnswer = null;
		passwordAnswer = req.getParameter("passwordAnswer");
		
		if (passwordAnswer !=null && passwordAnswer.isEmpty()) {
			errors.put("password",true);
		}
		
		if (passwordAnswer !=null && !passwordAnswer.isEmpty() && 
				!passwordAnswer.equals(member.getPasswordAnswer())
				) {
			errors.put("passwordDiff",true);			
		}

		if (passwordAnswer !=null && !passwordAnswer.isEmpty() && 
			passwordAnswer.equals(member.getPasswordAnswer())
			) {
			User user = new User(member.getId(),member.getNickName());
			req.getSession().setAttribute("authUser", user);
			return "changePasswordForm";
		}

		return FORM_VIEW;
	}
	
	
}
