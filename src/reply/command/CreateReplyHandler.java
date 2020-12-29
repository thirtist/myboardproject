package reply.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mvc.command.CommandHandler;
import reply.service.CreateReplyService;

public class CreateReplyHandler implements CommandHandler{
	CreateReplyService createReplyService = new CreateReplyService();
	
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
					
		return "null";
	}

	private String processPost(HttpServletRequest req, HttpServletResponse res) {
		
		User user = (User) req.getSession().getAttribute("authUser");
		String boardKey =req.getParameter("boardKey");
		String reply = req.getParameter("reply");
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		if(boardKey == null || boardKey.isEmpty()) {
			req.setAttribute("error", "boardKey를 못찾아서 리플못담");
			return "null";
		}
		int boardKeyI = Integer.parseInt(boardKey);
		
		
		if(reply == null || reply.isEmpty()) {
			try {
				res.sendRedirect(req.getContextPath()+"/auth/readArticle.do?boardKey="+boardKey+"&replyEmpty=true");
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			createReplyService.createReply(boardKeyI, user, reply);
		} catch (SQLException e) {
			e.printStackTrace();
			req.setAttribute("error", "createReply SQL에러");
			return "null";
		}

		try {
			res.sendRedirect(req.getContextPath()+"/auth/readArticle.do?boardKey="+boardKey);
		} catch (IOException e) {
			e.printStackTrace();
			return "null";
		}
		
		return null;
	}
}
