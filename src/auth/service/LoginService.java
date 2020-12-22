package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.dao.MemberDao;
import member.model.Member;
import member.service.NotFoundIdException;
import member.service.NotMatchPasswordException;

public class LoginService {
	private MemberDao memberDao = new MemberDao();

	public User login(LoginRequest loginRequest) {
		Connection con = ConnectionProvider.getConnection();
		Member member = null;
		User user = null;
		try {
			member = memberDao.selectById(con, loginRequest.getId());

			if (member == null) {
				throw new NotFoundIdException();
			}
			if (member.getPassword() != null && !member.getPassword().equals(loginRequest.getPassword())) {
				throw new NotMatchPasswordException();
			}
			user = new User(member.getId(), member.getNickName(), member.getAuth());

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(con);
		}
		return user;
	}
}
