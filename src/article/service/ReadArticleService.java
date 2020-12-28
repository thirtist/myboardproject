package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleDao;
import board.model.Board;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

public class ReadArticleService {

	ArticleDao articleDao = new ArticleDao();

	public Board readArticle(int boardKey) throws SQLException {

		Board board = null;
		
		Connection con = ConnectionProvider.getConnection();
		try {
			board = articleDao.readArticleByBoardKey(con, boardKey);
			
			articleDao.addCountByBoardKey(con, boardKey);

		} finally {
			JdbcUtil.close(con);
		}
		return board;

	}

}
