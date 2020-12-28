package article.command;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.DeleteArticleService;
import article.service.NotMatchIdException;
import auth.service.User;
import mvc.command.CommandHandler;

public class DeleteArticleHandler implements CommandHandler {
	DeleteArticleService deleteArticleService = new DeleteArticleService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		User user = (User) req.getSession().getAttribute("authUser");
		String boardName = req.getParameter("boardName");
				
		int boardKey = Integer.parseInt(req.getParameter("boardKey"));

		try {
			deleteArticleService.deleteArticle(boardKey, user.getId());
		} catch (NotMatchIdException e) {
			req.setAttribute("error", "deleteArticle NotMatchIdException문제발생");
			e.printStackTrace();
			return "null";			
		} catch (SQLException e) {
			req.setAttribute("error", "deleteArticle SQL문제발생");
			e.printStackTrace();
			return "null";
		}
		
		boardName = URLEncoder.encode(boardName);
		try {
			res.sendRedirect(req.getContextPath() + "/readArticleList.do?boardName=" + boardName);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}
}
