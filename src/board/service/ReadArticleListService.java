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

	public int getTotalArticle_search(String searchWord) throws SQLException {
		
		Connection con = ConnectionProvider.getConnection();
		int totalArticle = 0;
		try {

			totalArticle = boardDao.getTotalArticleBySearchWord(con, searchWord);

		} finally {
			JdbcUtil.close(con);
		}
		
		return totalArticle;

	}

	public List<Board> getArticleList_search(String searchWord, int pageNum, int onePageNum) throws SQLException {
		Connection con = ConnectionProvider.getConnection();
		List<Board> list = null;
		try {

			list = boardDao.getArticleListBySeachWord(con, searchWord, pageNum, onePageNum);

		} finally {
			JdbcUtil.close(con);
		}
		
		return list;
	}

	public int getTotalArticle_searchInBoard(String boardName,String searchMethod, String searchWord) throws SQLException {
		Connection con = ConnectionProvider.getConnection();
		int totalArticle = 0;
		try {

			totalArticle = boardDao.getTotalArticleBySearchWordInBoard(con, boardName, searchMethod, searchWord);

		} finally {
			JdbcUtil.close(con);
		}
		
		return totalArticle;
	}

	public List<Board> getArticleList_searchInBoard(String boardName, String searchMethod, String searchWord, int pageNum, int onePageNum) throws SQLException {
		Connection con = ConnectionProvider.getConnection();
		List<Board> list = null;
		try {

			list = boardDao.getArticleListBySeachWordInBoard(con, boardName, searchMethod, searchWord, pageNum, onePageNum);

		} finally {
			JdbcUtil.close(con);
		}
		
		return list;
	}

	public List<Board> getArticleListMajorRecommand(int i) throws SQLException {
		Connection con = ConnectionProvider.getConnection();
		List<Board> list = null;
		try {

			list = boardDao.getArticleListByRecommandOrder(con, i);

		} finally {
			JdbcUtil.close(con);
		}
		
		return list;
	}
	
	public List<Board> getArticleListRecent(int i) throws SQLException {
		Connection con = ConnectionProvider.getConnection();
		List<Board> list = null;
		try {

			list = boardDao.getArticleListByRecent(con, i);

		} finally {
			JdbcUtil.close(con);
		}
		
		return list;
	}
	
}
