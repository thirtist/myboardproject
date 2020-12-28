package article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import article.dao.ArticleDao;
import board.service.DuplicateBoardNameException;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

public class WriteArticleService {
	ArticleDao articleDao = new ArticleDao();
	
	public void writeArticle(WriteArticleRequest war) throws SQLException {
		
		Connection con = ConnectionProvider.getConnection();
		try {
			
			articleDao.writeArticleByRequest(con, war);

		} finally {
			JdbcUtil.close(con);
		}

		
	}
	
}
