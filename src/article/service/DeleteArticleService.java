package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleDao;
import board.model.Board;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

public class DeleteArticleService {
	ArticleDao articleDao = new ArticleDao();
	
	public void deleteArticle(int boardKey, String id) throws SQLException {
		Connection con = ConnectionProvider.getConnection();
		Board board = null;
		try {
			board = articleDao.readArticleByBoardKey(con, boardKey);
			
			if (!board.getUser_id().equals(id)) {
				throw new NotMatchIdException();
			}
			
			articleDao.deleteArticleByBoardKey(con, boardKey);

		} finally {
			JdbcUtil.close(con);
		}

		
	}

}
