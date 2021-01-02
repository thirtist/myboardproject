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
	public String process(HttpServletRequest req, HttpServletResponse res) {

		String boardName = req.getParameter("boardName");
		req.setAttribute("boardName",boardName);
		
		
		int totalNum = 0;
		
		try {
			totalNum = readArticleListService.getTotalArticle(boardName);
		} catch (SQLException e1) {
			req.setAttribute("error", "getTotalArticle SQL에러");
			e1.printStackTrace();
			return "null";
		}
		
		
		String onePageNumS = req.getParameter("onePageNum");
		if(onePageNumS==null || onePageNumS.isEmpty()) {
			onePageNumS="10";
		}
		int onePageNum = Integer.parseInt(onePageNumS);
		req.setAttribute("onePageNum", onePageNum);
		
		
		int pageNum = 1 ;
		
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

		List<Board> articleList = null;
		
		try {
			articleList = readArticleListService.getArticleList(boardName, pageNum, onePageNum);
			
		} catch (SQLException e) {
			req.setAttribute("error", "getArticleList SQL에러");
			e.printStackTrace();
			return "null";
		}
		
		double totalNumDouble = (double) totalNum;
		double totalOnePageNum = (double) onePageNum;
		
		int pageEnd = (int) Math.ceil(totalNumDouble / totalOnePageNum);
		req.setAttribute("ArticleList", articleList);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("pageEnd", pageEnd);
		
		int pageFirst = (pageNum-1)/5*5+1;
		int pageLast = pageFirst+4;
		if (pageLast >= pageEnd) {
			pageLast = pageEnd;
		}
		
		req.setAttribute("pageFirst", pageFirst);
		req.setAttribute("pageLast", pageLast);
		
		
		return FORM_VIEW;
	}

}
