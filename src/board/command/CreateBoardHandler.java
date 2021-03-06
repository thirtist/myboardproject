package board.command;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.CreateBoardService;
import board.service.DuplicateBoardNameException;
import mvc.command.CommandHandler;

public class CreateBoardHandler implements CommandHandler {
	private final String FORM_VIEW = "createBoardForm";
	CreateBoardService createBoardService = new CreateBoardService();
	
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
			createBoardService.createBoard(boardName);
		} catch (DuplicateBoardNameException e) {
			errors.put("DuplicateBoardNameException", true);
			e.printStackTrace();
			return FORM_VIEW;
		} catch (SQLException e) {
			e.printStackTrace();
			req.setAttribute("error", "createBoard SQL에러");
			return "null";
		}

		return "createBoardSuccess";
	}
	
}
