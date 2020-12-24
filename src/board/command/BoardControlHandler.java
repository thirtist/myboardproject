package board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class BoardControlHandler implements CommandHandler{
	private final String FORM_VIEW = "boardControlForm";
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		return FORM_VIEW;
		
	}

}
