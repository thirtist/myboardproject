package article.command;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.NotMatchIdException;
import article.service.ReadArticleService;
import article.service.RecommandArticleService;
import article.service.UpdateArticleRequest;
import article.service.UpdateArticleService;
import auth.service.User;
import board.model.Board;
import mvc.command.CommandHandler;
import reply.model.Reply;
import reply.service.ReadReplyService;

public class ReadArticleHandler implements CommandHandler {
	private final String FORM_VIEW = "readArticleForm";
	ReadArticleService readArticleService = new ReadArticleService();
	UpdateArticleService updateArticleService = new UpdateArticleService();
	ReadReplyService rrs = new ReadReplyService();

	
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
		req.setAttribute("boardKey", boardKey);
		
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

		int boardKeyI = Integer.parseInt(boardKey);
		
		try {
			board = readArticleService.readArticle(boardKeyI);
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
				boardKeyI, preTitle, title, content, user_id, boardName
				);
		
		req.setAttribute("uar", uar);
		
		
		/*reply
		List<Reply> replyList = null;
		try {
			replyList = rrs.readReply(boardKeyI);
		} catch (SQLException e) {
			req.setAttribute("error", "readReply SQL에러");
			e.printStackTrace();
			return "null";
		}
		req.setAttribute("replyList", replyList);
		*/
		/* reply페이징 */
		int totalNum = 0;
		try {
			totalNum = rrs.getReplyNum(boardKeyI);
		} catch (SQLException e1) {
			req.setAttribute("error", "getReplyNum에러");
			e1.printStackTrace();
			return "null";
		}
		int onePageNum = 10;
		int pageNum = 1 ;
		if (req.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(req.getParameter("pageNum"));
		}
		
		List<Reply> replyList = null;
		
		try {
			replyList = rrs.readReply(boardKeyI, pageNum, onePageNum);
			
		} catch (SQLException e) {
			req.setAttribute("error", "getReplyList에러");
			e.printStackTrace();
			return "null";
		}
		req.setAttribute("replyList", replyList);
		
		int pageEnd = (int) Math.ceil((totalNum / onePageNum) + (totalNum % onePageNum) * 0.1);
		req.setAttribute("replyList", replyList);
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
		
		/* boardName = URLEncoder.encode(boardName); */
		try {
			res.sendRedirect(req.getContextPath()+"/auth/readArticle.do?boardKey="+boardKey);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;

	}
}