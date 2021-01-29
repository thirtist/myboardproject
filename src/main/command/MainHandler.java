package main.command;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.Board;
import board.service.ReadArticleListService;
import mvc.command.CommandHandler;

public class MainHandler implements CommandHandler {
	private final String FORM_VIEW = "main";
	ReadArticleListService readArticleListService = new ReadArticleListService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		
		//최근10개표시
		int num = 10;
		List<Board> articleList = null;
		try {
			articleList = readArticleListService.getArticleListRecent(num);
		} catch (SQLException e) {
			req.setAttribute("error", "getArticleListMajorRecommand SQL에러");
			e.printStackTrace();
			return "null";
		}
		req.setAttribute("ArticleList", articleList);
		return FORM_VIEW;
	}

}
