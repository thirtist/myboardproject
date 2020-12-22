package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import auth.service.NotFoundEmailException;
import auth.service.NotMatchNameOrEmail;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.dao.MemberDao;
import member.model.Member;

public class FindIdService {
	private MemberDao memberDao = new MemberDao();
	
	public String findId(FindIdRequest findIdRequest) {
		Connection con = ConnectionProvider.getConnection();
		Member member = null;
		String id = null;
		try {
			member = memberDao.selectByEmail(con, findIdRequest.getEmail());
			
			if(member == null) {
				throw new NotFoundEmailException();
			} 
			if(!member.getName().equals(findIdRequest.getName())) {
				throw new NotMatchNameOrEmail();
			}
			id = member.getId();
					
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(con);
		}
		return id;
	}
	
}
