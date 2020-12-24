package board.command;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.BoardDao;
import board.service.DeleteBoardService;
import board.service.DuplicateBoardNameException;
import board.service.ReadBoardListService;
import mvc.command.CommandHandler;

public class DeleteBoardHandler implements CommandHandler {
	private final String FORM_VIEW = "deleteBoardForm";
	DeleteBoardService deleteBoardService = new DeleteBoardService();
	
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
		ReadBoardListService readBoardListService = new ReadBoardListService();
		
		List<String> boardList = null;
		try {
			boardList = readBoardListService.readBoardList();
		} catch (SQLException e) {
			req.setAttribute("error", "readBoardListService SQL에러");
			e.printStackTrace();
			return FORM_VIEW;
		}
		req.setAttribute("boardList", boardList);
		
		return FORM_VIEW;
	}

	private String processPost(HttpServletRequest req, HttpServletResponse res) {
		
		String boardName = req.getParameter("boardName");

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		if(boardName == null || boardName.isEmpty()) {
			errors.put("boardName", true);
			return FORM_VIEW;
		}

		try {
			deleteBoardService.deleteBoard(boardName);
		} catch (NotFoundBoardNameException e) {
			errors.put("NotFoundBoardNameException", true);
			e.printStackTrace();
			return FORM_VIEW;
		} catch (SQLException e) {
			e.printStackTrace();
			req.setAttribute("error", "deleteBoard SQL에러");
			return "null";
		}
		
		return "deleteBoardSuccess";
	}
	
}
