package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.DuplicateEmailException;
import member.service.DuplicateIdException;
import member.service.MemberRequest;
import member.service.MemberService;
import mvc.command.CommandHandler;

public class MemberJoinHandler implements CommandHandler {
	private static final String FORM_VIEW = "memberJoinForm";
	private MemberService memberService = new MemberService();
	
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
		String email = req.getParameter("email");
		
		// 아이디 중복 확인버튼
		if(id !=null && id.isEmpty()) {
			req.setAttribute("existIdButton", "emptyId");			
		}
		if(id != null && !id.isEmpty()) {
			String check  = memberService.existIdButton(id);
			req.setAttribute("existIdButton", check);		
			req.setAttribute("id", id);		
		}
		// 이메일 중복 확인버튼
		if(email !=null && email.isEmpty()) {
			req.setAttribute("existEmailButton", "emptyEmail");			
		}
		if(email != null && !email.isEmpty()) {
			String check  = memberService.existEmailButton(email);
			req.setAttribute("existEmailButton", check);
			req.setAttribute("email", email);			
		}
		
		//
		
		return FORM_VIEW;
	}


	private String processPost(HttpServletRequest req, HttpServletResponse res) {
		
		MemberRequest memberRequest = new MemberRequest();
		memberRequest.setId(req.getParameter("id"));
		memberRequest.setNickName(req.getParameter("nickName"));
		memberRequest.setPassword(req.getParameter("password"));
		memberRequest.setPasswordConfirm(req.getParameter("passwordConfirm"));
		memberRequest.setName(req.getParameter("name"));
		memberRequest.setEmail(req.getParameter("email"));
		memberRequest.setPasswordQuestion(req.getParameter("passwordQuestion"));
		memberRequest.setPasswordAnswer(req.getParameter("passwordAnswer"));
		
		Map<String,Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		memberRequest.validate(errors);
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
				
		try {
			memberService.join(memberRequest);
			return "memberJoinSuccess";
		} catch (DuplicateEmailException e) {
			errors.put("DuplicateEmailException", true);
			e.printStackTrace();
			return FORM_VIEW;
		} catch (DuplicateIdException e) {
			errors.put("duplicateId", true);
			e.printStackTrace();
			return FORM_VIEW;
		}
				
	}
}
