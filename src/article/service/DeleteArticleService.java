package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleDao;
import board.model.Board;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import reply.dao.ReplyDao;

public class DeleteArticleService {
	ArticleDao articleDao = new ArticleDao();
	ReplyDao replyDao = new ReplyDao();
	
	public void deleteArticle(int boardKey, String id) throws SQLException {
		Connection con = ConnectionProvider.getConnection();
		Board board = null;
		try {
			con.setAutoCommit(false);
			board = articleDao.readArticleByBoardKey(con, boardKey);
			
			if (!board.getUser_id().equals(id)) {
				throw new NotMatchIdException();
			}
			
			try {
				articleDao.deleteArticleByBoardKey(con, boardKey);
			} catch (SQLException e) {
				JdbcUtil.rollback(con);
				e.printStackTrace();
				throw e;
			}
			
			try {
				replyDao.deleteReplyByBoardKey(con,boardKey);
			} catch (SQLException e) {
				JdbcUtil.rollback(con);
				e.printStackTrace();
				throw e;
			}
			
			con.commit();
		} finally {
			JdbcUtil.close(con);
		}

		
	}

}
