package board.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.Board;
import board.service.ReadArticleListService;
import mvc.command.CommandHandler;

public class ReadArticleList_recommandHandler implements CommandHandler {
	private final String FORM_VIEW = "readArticleList_recommandForm";
	ReadArticleListService readArticleListService = new ReadArticleListService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {

		int num = 10;
		
		List<Board> articleList = null;
		
		try {
			articleList = readArticleListService.getArticleListMajorRecommand(num);
			
		} catch (SQLException e) {
			req.setAttribute("error", "getArticleListMajorRecommand SQL에러");
			e.printStackTrace();
			return "null";
		}
		req.setAttribute("ArticleList", articleList);
		
		return FORM_VIEW;
	}

}
