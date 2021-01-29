package search.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.Board;
import board.service.ReadArticleListService;
import mvc.command.CommandHandler;

public class SearchInBoardHandler implements CommandHandler{

	private final String FORM_VIEW = "readArticleList_searchInBoardForm";
	ReadArticleListService readArticleListService = new ReadArticleListService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {

		String searchMethod = req.getParameter("searchMethod");
		String boardName = req.getParameter("boardName");
		
		String searchWord = req.getParameter("searchWord");
		req.setAttribute("searchWord", searchWord);

		int totalNum = 0;

		try {
			totalNum = readArticleListService.getTotalArticle_searchInBoard(boardName,searchMethod,searchWord);
		} catch (SQLException e1) {
			req.setAttribute("error", "getTotalArticle_search SQL에러");
			e1.printStackTrace();
			return "null";
		}

		String onePageNumS = req.getParameter("onePageNum");
		if (onePageNumS == null || onePageNumS.isEmpty()) {
			onePageNumS = "10";
		}
		int onePageNum = Integer.parseInt(onePageNumS);
		req.setAttribute("onePageNum", onePageNum);

		int pageNum = 1;

		if (req.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(req.getParameter("pageNum"));
		}

		if (searchWord == null || searchWord.isEmpty()) {
			try {
				res.sendRedirect(req.getContextPath() + "/readBoardList.do");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		List<Board> articleList = null;

		try {
			articleList = readArticleListService.getArticleList_searchInBoard(boardName, searchMethod, searchWord, pageNum, onePageNum);

		} catch (SQLException e) {
			req.setAttribute("error", "getArticleList_search SQL에러");
			e.printStackTrace();
			return "null";
		}

		double totalNumDouble = (double) totalNum;
		double totalOnePageNum = (double) onePageNum;

		int pageEnd = (int) Math.ceil(totalNumDouble / totalOnePageNum);
		req.setAttribute("ArticleList", articleList);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("pageEnd", pageEnd);

		int pageFirst = (pageNum - 1) / 5 * 5 + 1;
		int pageLast = pageFirst + 4;
		if (pageLast >= pageEnd) {
			pageLast = pageEnd;
		}

		req.setAttribute("pageFirst", pageFirst);
		req.setAttribute("pageLast", pageLast);

		return FORM_VIEW;
	}

}
