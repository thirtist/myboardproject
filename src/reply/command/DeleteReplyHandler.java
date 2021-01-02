package reply.command;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.NotMatchIdException;
import auth.service.User;
import mvc.command.CommandHandler;
import reply.service.DeleteReplyService;
import reply.service.NullReplyException;

public class DeleteReplyHandler implements CommandHandler{
	DeleteReplyService deleteReplyService = new DeleteReplyService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		User user = (User) req.getSession().getAttribute("authUser");
		String replyPrimaryKey =req.getParameter("replyPrimaryKey");
		String boardKey = req.getParameter("boardKey");
		
		String pageNum =req.getParameter("pageNum");
		if(pageNum == null || pageNum.isEmpty()) {
			pageNum = "1";
		}
		
		if(replyPrimaryKey == null || replyPrimaryKey.isEmpty()) {
			req.setAttribute("error", "replyPrimaryKey를 못찾아서 리플제거못함");
			return "null";
		}
		int replyPrimaryKeyI = Integer.parseInt(replyPrimaryKey);
		
		try {
			deleteReplyService.deleteReply(replyPrimaryKeyI, user.getId());
		} catch (NullReplyException e) {
			e.printStackTrace();
			req.setAttribute("error", "replyPrimaryKey에따른 reply을 찾지못함");
			return "null";
		}catch (NotMatchIdException e) {
			e.printStackTrace();
			req.setAttribute("error", "아이디가 리플작성자와 같지않아 리플지우기실패");
			return "null";
		} catch (SQLException e) {
			e.printStackTrace();
			req.setAttribute("error", "deleteReply SQL에러");
			return "null";
		} 

		try {
			res.sendRedirect(req.getContextPath()+"/auth/readArticle.do?boardKey="+boardKey+"&pageNum="+pageNum);
		} catch (IOException e) {
			e.printStackTrace();
			return "null";
		}
		
		return null;
	}
}
