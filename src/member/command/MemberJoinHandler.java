package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.DuplicateIdException;
import member.service.MemberJoinRequest;
import member.service.MemberJoinService;
import mvc.command.CommandHandler;

public class MemberJoinHandler implements CommandHandler {
	private static final String FORM_VIEW = "memberJoinForm";
	private MemberJoinService memberJoinService = new MemberJoinService();
	
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
		String id = req.getParameter("id");
		
		if(id != null) {
			String check  = memberJoinService.existIdButton(id);
			req.setAttribute("existIdButton", check);			
		}
		
		return FORM_VIEW;
	}


	private String processPost(HttpServletRequest req, HttpServletResponse res) {
		
		MemberJoinRequest memberJoinRequest = new MemberJoinRequest();
		memberJoinRequest.setId(req.getParameter("id"));
		memberJoinRequest.setNickName(req.getParameter("nickName"));
		memberJoinRequest.setPassword(req.getParameter("password"));
		memberJoinRequest.setPasswordConfirm(req.getParameter("passwordConfirm"));
		memberJoinRequest.setName(req.getParameter("name"));
		memberJoinRequest.setEmail(req.getParameter("email"));
		memberJoinRequest.setPasswordQuestion(req.getParameter("passwordQuestion"));
		memberJoinRequest.setPasswordAnswer(req.getParameter("passwordAnswer"));
		
		Map<String,Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		memberJoinRequest.validate(errors);
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
				
		try {
			memberJoinService.memberJoin(memberJoinRequest);
			return "memberJoinSuccess";
		} catch (DuplicateIdException e) {
			errors.put("duplicateId", true);
			e.printStackTrace();
			return FORM_VIEW;
		}
		
		
	}
}
