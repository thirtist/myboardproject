package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.dao.MemberDao;
import member.model.Member;

public class ChangePasswordService {
	private MemberDao memberDao = new MemberDao();
	
	public void changePassword(ChangePasswordRequest changePasswordRequest) {

		Connection con = null;
		Member member = null;
		try {
			con = ConnectionProvider.getConnection();
	
			member = memberDao.selectById(con, changePasswordRequest.getId());
			if(member == null) {
				throw new RuntimeException();
			}
			
			member.setPassword(changePasswordRequest.getPassword());
			memberDao.memberUpdate(con, member);		
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(con);
		}
	
	}

}
