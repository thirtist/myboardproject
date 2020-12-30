package article.command;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.RecommandArticleService;
import auth.service.User;
import mvc.command.CommandHandler;

public class RecommandArticleHandler implements CommandHandler {
	RecommandArticleService recommandArticleService = new RecommandArticleService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User user = (User) req.getSession().getAttribute("authUser");
		String boardKey = req.getParameter("boardKey");
		
		if(boardKey == null || boardKey.isEmpty()) {
			req.setAttribute("error", "boardKey를 못찾아서 추천을못함");
			return "null";
		}
		
		int boardKeyI = Integer.parseInt(boardKey);
		
		try {
			recommandArticleService.recommandArticle(boardKeyI, user.getId());
		} catch (SQLException e) {
			e.printStackTrace();
			req.setAttribute("error", "createSubReply SQL에러");
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
