package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.RecommandDao;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

public class RecommandArticleService {
	RecommandDao recommandDao = new RecommandDao();

	public void recommandArticle(int boardKey, String id) throws SQLException {
		Connection con = ConnectionProvider.getConnection();
		int num = 0;
		try {
			
			num = recommandDao.recommandCheckByBoardKeyAndId(con, boardKey, id);

			if(num >=1) {
				recommandDao.removeRecommandByBoardKey(con, boardKey, id);
			} else {
				recommandDao.createRecommandByBoardKey(con, boardKey, id);				
			}

		} finally {
			JdbcUtil.close(con);
		}
	}
	
	public int getRecommandNumber(int boardKey) throws SQLException {
		Connection con = ConnectionProvider.getConnection();
		int num = 0;
		try {
			
			num = recommandDao.recommandNumberByBoardKey(con, boardKey);

		} finally {
			JdbcUtil.close(con);
		}
		return num;
	}
	
	

}
