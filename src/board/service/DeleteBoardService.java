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
			con.setAutoCommit(false);
			List<String> list = boardDao.getBoardList(con);

			if (!list.contains(boardName)) {
				throw new NotFoundBoardNameException();
			}

			try {
				boardDao.deleteBoardByBoardName(con, boardName);
			} catch (SQLException e) {
				JdbcUtil.rollback(con);
				throw e;
			}
			
			try {
				boardDao.deleteSeq(con, boardName);
			} catch (SQLException e) {
				JdbcUtil.rollback(con);
				throw e;
			}

			con.commit();
		} finally {
			JdbcUtil.close(con);
		}
		
	}

}
