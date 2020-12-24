package board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.dao.BoardDao;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

public class ReadBoardListService {
	BoardDao boardDao = new BoardDao();

	public List<String> readBoardList() throws SQLException {
		Connection con = ConnectionProvider.getConnection();
		List<String> list = null;
		try {

			list = boardDao.getBoardList(con);

		} finally {
			JdbcUtil.close(con);
		}
		return list;
	}
}
