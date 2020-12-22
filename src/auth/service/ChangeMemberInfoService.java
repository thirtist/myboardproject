package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.dao.MemberDao;
import member.model.Member;
import member.service.DuplicateEmailException;
import member.service.NotFoundIdException;

public class ChangeMemberInfoService {
	MemberDao memberDao = new MemberDao();
	
	public Member readMemeberInfo(String id) {
		Connection con = null;
		Member member = null;
		
		try {
			con = ConnectionProvider.getConnection();
			
			con.setAutoCommit(false);
			
			member = memberDao.selectById(con,id);
			if(member == null || member.getId() == null) {
				JdbcUtil.rollback(con);
				throw new NotFoundIdException();
			}
			
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con);
		}

		return member;
	}

	public void updateMemeberInfo(String id, ChangeMemberInfoRequest changeMemberInfoRequest) {
		Connection con = null;
		Member member = null;
		Member member2 = null;
		
		String nickName = changeMemberInfoRequest.getNickName();
		String password = changeMemberInfoRequest.getPassword();
		String name = changeMemberInfoRequest.getName();
		String email = changeMemberInfoRequest.getEmail();
		String passwordQuestion = changeMemberInfoRequest.getPasswordQuestion();
		String passwordAnswer = changeMemberInfoRequest.getPasswordAnswer();
		
		try {
			con = ConnectionProvider.getConnection();
			
			con.setAutoCommit(false);
			
			member = memberDao.selectById(con,id);
			if(member == null || member.getId() == null) {
				JdbcUtil.rollback(con);
				throw new NotFoundIdException();
			}
			
			member2 = memberDao.selectByEmail(con, email);
			
			if(!email.equals(member.getEmail()) && member2 !=null) {
				throw new DuplicateEmailException();
			}

			member.setEmail(email);					
			member.setNickName(nickName);
			member.setPassword(password);
			member.setName(name);
			member.setPasswordQuestion(passwordQuestion);
			member.setPasswordAnswer(passwordAnswer);
			
			memberDao.memberUpdate(con, member);
			
			
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(con);
		}
		
	}
	
}
