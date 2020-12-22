package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.dao.MemberDao;
import member.service.NotFoundIdException;

public class DeleteMemberService {
	private MemberDao memberDao = new MemberDao();
	
	public void deleteMemeber(String id) throws SQLException {
		
		Connection con = null;
		int row = 0;

		try {
			con = ConnectionProvider.getConnection();
			con.setAutoCommit(false);
			row = memberDao.deleteById(con, id);

			if (row != 1) {
				con.rollback();
				throw new NotFoundIdException();
			}
			con.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(con);
			throw e;
		} finally {
			JdbcUtil.close(con);
		}
	}

}
