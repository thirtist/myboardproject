package board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import board.command.NotFoundBoardNameException;
import board.dao.BoardDao;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

public class DeleteBoardService {
	BoardDao boardDao = new BoardDao();
	
	public void deleteBoard(String boardName) throws SQLException {
		Connection con = ConnectionProvider.getConnection();
		try {

			List<String> list = boardDao.getBoardList(con);

			if (!list.contains(boardName)) {
				throw new NotFoundBoardNameException();
			}

			boardDao.deleteBoardByBoardName(con, boardName);

		} finally {
			JdbcUtil.close(con);
		}
		
	}

}
