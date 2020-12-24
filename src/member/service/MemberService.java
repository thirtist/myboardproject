package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.dao.MemberDao;
import member.model.Member;

public class MemberService {
	private MemberDao memberDao = new MemberDao();

	public String existEmailButton(String email) {

		Connection con = null;
		try {
			con = ConnectionProvider.getConnection();
	
			//이메일 중복검사
			Member member = memberDao.selectByEmail(con, email);
			if(member != null && member.getEmail() != null) {
				return "existEmail";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(con);
		}
		return "none";

	}
	
	public String existIdButton(String id) {

		Connection con = null;
		try {
			con = ConnectionProvider.getConnection();
	
			//아이디 중복검사
			Member member = memberDao.selectById(con, id);
			if(member != null && member.getId() != null) {
				return "existId";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con);
		}
		return "none";

	}
	
	public void join(MemberRequest memberRequest) throws SQLException {

		Connection con = null;
		Member member = null;
		Member member2 = null;
		try {
			con = ConnectionProvider.getConnection();
			
			con.setAutoCommit(false);
			//아이디 중복검사
			member = memberDao.selectById(con, memberRequest.getId());
			if(member !=null && member.getId() != null) {
				JdbcUtil.rollback(con);
				throw new DuplicateIdException();
			}
			
			member2 = memberDao.selectByEmail(con, memberRequest.getEmail());
			if(member2 !=null && member2.getEmail() != null) {
				JdbcUtil.rollback(con);
				throw new DuplicateEmailException();
			}
			
			member = new Member();
			member.setId(memberRequest.getId());
			member.setNickName(memberRequest.getNickName());
			member.setPassword(memberRequest.getPassword());
			member.setName(memberRequest.getName());
			member.setEmail(memberRequest.getEmail());
			member.setPasswordQuestion(memberRequest.getPasswordQuestion());
			member.setPasswordAnswer(memberRequest.getPasswordAnswer());
			
			memberDao.memberInsert(con, member);
			
			con.commit();
		} finally {
			JdbcUtil.close(con);
		}

	}

	

}
