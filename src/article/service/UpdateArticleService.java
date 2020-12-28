package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleDao;
import board.model.Board;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

public class UpdateArticleService {
	ArticleDao articleDao = new ArticleDao();
	
	public void updateArticle(UpdateArticleRequest uar) throws SQLException {		
			Connection con = ConnectionProvider.getConnection();
			Board board = null;
			try {
				board = articleDao.readArticleByBoardKey(con, uar.getBoardKey());
				
				if (!board.getUser_id().equals(uar.getUser_id())) {
					throw new NotMatchIdException();
				}
				
				articleDao.updateArticleByBoardKey(con, uar);

			} finally {
				JdbcUtil.close(con);
			}

			
		}

}
