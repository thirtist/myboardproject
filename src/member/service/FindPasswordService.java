package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import auth.service.NotMatchNameOrEmail;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.dao.MemberDao;
import member.model.Member;

public class FindPasswordService {

	private MemberDao memberDao = new MemberDao();
	
	public Member checkValidate(FindPasswordRequest findPasswordRequest) {
		Connection con = ConnectionProvider.getConnection();
		Member member = null;
		try {
			member = memberDao.selectById(con, findPasswordRequest.getId());
			
			if(member == null) {
				throw new NotFoundIdException();
			} 
			if(!member.getName().equals(findPasswordRequest.getName())) {
				throw new NotMatchNameOrEmail();
			}
			if(!member.getEmail().equals(findPasswordRequest.getEmail())) {
				throw new NotMatchNameOrEmail();
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(con);
		}
		
		return member;
	}

}
