package board.command;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.ReadBoardListService;
import mvc.command.CommandHandler;

public class ReadBoardListHandler implements CommandHandler {
	private final String FORM_VIEW = "readBoardListForm";
	ReadBoardListService readBoardListService = new ReadBoardListService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
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
}
