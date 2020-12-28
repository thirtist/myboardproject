package board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import board.dao.BoardDao;
import board.model.Board;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

public class ReadArticleListService {
	BoardDao boardDao = new BoardDao();
	
	public List<Board> getArticleList(String boardName, int pageNum, int onePageNum) throws SQLException {
		Connection con = ConnectionProvider.getConnection();
		List<Board> list = null;
		try {

			list = boardDao.getArticleListByBoardName(con, boardName, pageNum, onePageNum);

		} finally {
			JdbcUtil.close(con);
		}
		
		return list;
	}
	
	public int getTotalArticle(String boardName) throws SQLException {
		Connection con = ConnectionProvider.getConnection();
		int totalArticle = 0;
		try {

			totalArticle = boardDao.getTotalArticleByBoardName(con, boardName);

		} finally {
			JdbcUtil.close(con);
		}
		
		return totalArticle;
	}
	
}
