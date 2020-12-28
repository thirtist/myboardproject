package article.command;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.NotMatchIdException;
import article.service.ReadArticleService;
import article.service.UpdateArticleRequest;
import article.service.UpdateArticleService;
import auth.service.User;
import board.model.Board;
import mvc.command.CommandHandler;

public class ReadArticleHandler implements CommandHandler {
	private final String FORM_VIEW = "readArticleForm";
	ReadArticleService readArticleService = new ReadArticleService();
	UpdateArticleService updateArticleService = new UpdateArticleService();
	
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

		String boardKey = req.getParameter("boardKey");
		
		
		
		User user = (User) req.getSession().getAttribute("authUser");
		Board board = null;

		if (boardKey == null || boardKey.isEmpty()) {
			try {
				res.sendRedirect(req.getContextPath() + "/readBoardList.do");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		int boardKey2 = Integer.parseInt(boardKey);
		
		try {
			board = readArticleService.readArticle(boardKey2);
		} catch (NumberFormatException e) {
			req.setAttribute("error", "boardKey를 int로 변환할 수 없음");
			e.printStackTrace();
			return "null";
		} catch (SQLException e) {
			req.setAttribute("error", "readArticle SQL에러");
			e.printStackTrace();
			return "null";
		}
		
		if (board == null) {
			req.setAttribute("error", "boardKey에 대한 board를 찾을 수 없음");
			return "null";
		}
		
		if (user != null && board.getUser_id().equals(user.getId())) {
			req.setAttribute("sameUser", true);
		}
		
		String preTitle = board.getPreTitle();
		String title = board.getTitle();
		String content = board.getContent();
		String user_id = user.getId();
		String boardName = board.getBoardName();
		UpdateArticleRequest uar = new UpdateArticleRequest(
				boardKey2, preTitle, title, content, user_id, boardName
				);
		req.setAttribute("uar", uar);
		
		return FORM_VIEW;

	}

	private String processPost(HttpServletRequest req, HttpServletResponse res) {

		User user = (User) req.getSession().getAttribute("authUser");
		String boardName = req.getParameter("boardName");
		
		int boardKey = Integer.parseInt(req.getParameter("boardKey"));
		String preTitle = req.getParameter("preTitle");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String user_id = user.getId();

		UpdateArticleRequest uar = new UpdateArticleRequest(boardKey, preTitle, title, content, user_id, boardName);
		req.setAttribute("uar", uar);

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		uar.validate(errors);

		if (!errors.isEmpty()) {
			req.setAttribute("sameUser", true);
			return FORM_VIEW;
		}

		try {
			updateArticleService.updateArticle(uar);
		} catch (NotMatchIdException e) {
			e.printStackTrace();
			req.setAttribute("error", "수정하는 작성자가 일치하지 않음");
			return "null";
		} catch (SQLException e) {
			e.printStackTrace();
			req.setAttribute("error", "updateArticle SQL에러");
			return "null";
		}
		
		boardName = URLEncoder.encode(boardName);
		try {
			res.sendRedirect(req.getContextPath()+"/readArticleList.do?boardName="+boardName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;

	}
}