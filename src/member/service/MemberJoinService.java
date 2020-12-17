package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.dao.MemberDao;
import member.model.Member;

public class MemberJoinService {
	private MemberDao memberDao = new MemberDao();

	
	public String existIdButton(String id) {

		Connection con = null;
		try {
			con = ConnectionProvider.getConnection();
	
			//아이디 중복검사
			Member member = memberDao.selectById(con, id);
			if(member.getId() != null) {
				return "exist";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con);
		}
		return "none";

	}
	
	public void memberJoin(MemberJoinRequest memberJoinRequest) {

		Connection con = null;
		try {
			con = ConnectionProvider.getConnection();
			
			con.setAutoCommit(false);
			//아이디 중복검사
			Member member = memberDao.selectById(con, memberJoinRequest.getId());
			if(member.getId() != null) {
				JdbcUtil.rollback(con);
				throw new DuplicateIdException();
			}
			
			member = new Member();
			member.setId(memberJoinRequest.getId());
			member.setNickName(memberJoinRequest.getNickName());
			member.setPassword(memberJoinRequest.getPassword());
			member.setName(memberJoinRequest.getName());
			member.setEmail(memberJoinRequest.getEmail());
			member.setPasswordQuestion(memberJoinRequest.getPasswordQuestion());
			member.setPasswordAnswer(memberJoinRequest.getPasswordAnswer());
			
			memberDao.memberInsert(con, member);
			
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con);
		}

	}

}
