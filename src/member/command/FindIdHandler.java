package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.NotFoundEmailException;
import auth.service.NotMatchNameOrEmail;
import member.service.FindIdRequest;
import member.service.FindIdService;
import mvc.command.CommandHandler;


public class FindIdHandler implements CommandHandler {
	private static final String FORM_VIEW = "findIdForm";
	private FindIdService findIdService = new FindIdService();
	
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
		String name = req.getParameter("name");
		String email = req.getParameter("email");

		FindIdRequest findIdRequest = new FindIdRequest(
				name, email);
		Map <String, Boolean> errors = new HashMap<>();		
		req.setAttribute("errors", errors);
		
		findIdRequest.validate(errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		String id = null;
		
		try{
			id = findIdService.findId(findIdRequest);
		} catch (NotFoundEmailException e) {
			errors.put("NotFoundEmailException", true);
			return FORM_VIEW;
		} catch (NotMatchNameOrEmail e) {
			errors.put("NotMatchNameOrEmail", true);
			return FORM_VIEW;
		} 
		req.setAttribute("id", id);
		return "findIdSuccess";
	}
	
	
}
