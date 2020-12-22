package auth.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import auth.service.DeleteMemberService;
import auth.service.User;
import member.service.NotFoundIdException;
import mvc.command.CommandHandler;

public class DeleteMemberHandler implements CommandHandler{
	private DeleteMemberService deleteMemeberService = new DeleteMemberService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		User user = (User) req.getSession().getAttribute("authUser");
		
		try {
			deleteMemeberService.deleteMemeber(user.getId());
			req.getSession(false).invalidate();
			res.sendRedirect(req.getContextPath()+"/index.jsp");
		} catch (NotFoundIdException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
