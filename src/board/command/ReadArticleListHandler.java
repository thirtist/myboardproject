package board.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.Board;
import board.service.ReadArticleListService;
import mvc.command.CommandHandler;

public class ReadArticleListHandler implements CommandHandler {
	private final String FORM_VIEW = "readArticleListForm";
	ReadArticleListService readArticleListService = new ReadArticleListService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String boardName = req.getParameter("boardName");
		req.setAttribute("boardName",boardName);
		
		
		int totalNum = 0;
		
		totalNum = readArticleListService.getTotalArticle(boardName);
		
		int onePageNum = 10;
		
		int pageNum = totalNum / onePageNum + 1 ;
		
		if (req.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(req.getParameter("pageNum"));
		}
		

		if (boardName == null || boardName.isEmpty()) {
			try {
				res.sendRedirect(req.getContextPath() + "/readBoardList.do");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		List<Board> ArticleList = null;
		
		try {
			ArticleList = readArticleListService.getArticleList(boardName, pageNum, onePageNum);
			
		} catch (SQLException e) {
			req.setAttribute("error", "getArticleList에러");
			e.printStackTrace();
			return "null";
		}
		
		int pageEnd = totalNum / onePageNum +1 ;
		
		req.setAttribute("ArticleList", ArticleList);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("pageEnd", pageEnd);

		return FORM_VIEW;
	}

}
