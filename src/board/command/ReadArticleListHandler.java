package board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.ReadArticleListService;
import mvc.command.CommandHandler;

public class ReadArticleListHandler implements CommandHandler{
	private final String FORM_VIEW = "readArticleListForm";
	ReadArticleListService readArticleListService = new ReadArticleListService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String boardName = req.getParameter("boardName");
		
		if (boardName == null || boardName.isEmpty()) {
			res.sendRedirect(req.getContextPath()+"readBoardList.do");
			return null;
		}
		
		readArticleListService.getArticleList(boardName);
//	
		//readArticleListForm에 뿌려줄거 여기에 만들기
		
		return FORM_VIEW;
	}
		
}
