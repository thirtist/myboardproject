package article.command;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.WriteArticleRequest;
import article.service.WriteArticleService;
import auth.service.User;
import mvc.command.CommandHandler;

public class WriteArticleHandler implements CommandHandler {
	private final String FORM_VIEW = "writeArticleForm";
	WriteArticleService writeArticleService = new WriteArticleService();

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

		String boardName = req.getParameter("boardName");
		req.setAttribute("boardName",boardName);

		if (boardName == null || boardName.isEmpty()) {
			try {
				res.sendRedirect(req.getContextPath()+"/readBoardList.do");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		return FORM_VIEW;

	}

	private String processPost(HttpServletRequest req, HttpServletResponse res) {
		User user = (User) req.getSession().getAttribute("authUser");

		String boardName = req.getParameter("boardName");
		String preTitle = req.getParameter("preTitle");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String id = user.getId();
		String nickName = user.getNickName();

		WriteArticleRequest war = new WriteArticleRequest(boardName, preTitle, title, content, id, nickName);

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		war.validate(errors);

		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}

		try {
			writeArticleService.writeArticle(war);
		} catch (SQLException e) {
			e.printStackTrace();
			req.setAttribute("error", "writeArticle SQL에러");
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
