package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.ChangeMemberInfoRequest;
import auth.service.ChangeMemberInfoService;
import auth.service.User;
import member.model.Member;
import member.service.DuplicateEmailException;
import member.service.MemberService;
import member.service.NotFoundIdException;
import mvc.command.CommandHandler;

public class ChangeMemberInfoHandler implements CommandHandler {
	private static final String FORM_VIEW = "changeMemberInfoForm";

	private ChangeMemberInfoService changeMemeberInfoService = new ChangeMemberInfoService();

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
		User user = (User) req.getSession().getAttribute("authUser");
		Member member = null;
		Map<String, Boolean> errors = new HashMap<>();

		try {
			member = changeMemeberInfoService.readMemeberInfo(user.getId());
		} catch (NotFoundIdException e) {
			errors.put("NotFoundIdException", true);
			return "login";
		}

		req.setAttribute("member", member);

		
		// 이메일 중복 확인버튼
		String email = null;
		email = req.getParameter("email");
		req.setAttribute("email", member.getEmail());
		
		MemberService memberService = new MemberService();
		
		//동일 이메일일 경우는 사용가능하다고 하고 바로 리턴
		if (email !=null && member.getEmail().equals(email)) {
			req.setAttribute("existEmailButton", "none");
			return FORM_VIEW;
		}
		
		if(email !=null && email.isEmpty()) {
			req.setAttribute("email", email);
			errors.put("emptyEmail", true);	
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
		User user = (User) req.getSession().getAttribute("authUser");
		Member member = new Member();
		
		String nickName = req.getParameter("nickName");
		String password = req.getParameter("password");
		String passwordConfirm = req.getParameter("passwordConfirm");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String passwordQuestion = req.getParameter("passwordQuestion");
		String passwordAnswer = req.getParameter("passwordAnswer");
	
		member.setId(user.getId());
		member.setNickName(nickName);
		member.setName(name);
		member.setEmail(email);
		member.setPasswordQuestion(passwordQuestion);
		member.setPasswordAnswer(passwordAnswer);
		req.setAttribute("member", member);
		req.getParameter(email);
		req.setAttribute("email", email);
		
		ChangeMemberInfoRequest changeMemberInfoRequest = new ChangeMemberInfoRequest(nickName, password,
				passwordConfirm, name, email, passwordQuestion, passwordAnswer);
	
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		changeMemberInfoRequest.validate(errors);
		if (!errors.isEmpty()) {
			
			return FORM_VIEW;
		}
				

		try {
			changeMemeberInfoService.updateMemeberInfo(user.getId(), changeMemberInfoRequest);
			user.setOneTimeCheck(0);
			req.getSession().setAttribute("authUser", user);
		} catch (NotFoundIdException e) {
			errors.put("NotFoundIdException", true);
			e.printStackTrace();
			return FORM_VIEW;
		}catch (DuplicateEmailException e) {
			errors.put("DuplicateEmailException", true);
			e.printStackTrace();
			return FORM_VIEW;
		} 

		return "changeMemberInfoSuccess";
	}
}
