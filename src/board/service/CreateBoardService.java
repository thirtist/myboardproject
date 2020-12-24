package board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import board.dao.BoardDao;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

public class CreateBoardService {
	BoardDao boardDao = new BoardDao();

	public void createBoard(String boardName) throws SQLException {
		Connection con = ConnectionProvider.getConnection();
		try {

			List<String> list = boardDao.getBoardList(con);

			if (list.contains(boardName)) {
				throw new DuplicateBoardNameException();
			}

			boardDao.createBoardByAdmin(con, boardName);

		} finally {
			JdbcUtil.close(con);
		}

	}

}
