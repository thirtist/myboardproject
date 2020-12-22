package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import member.model.Member;

public class MemberDao {

	public Member selectByEmail(Connection con, String email) throws SQLException {
		String sql = "SELECT * FROM member_mbp WHERE email=?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member member = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				member = new Member();
				member.setId(rs.getString("ID"));
				member.setNickName(rs.getString("NICKNAME"));
				member.setPassword(rs.getString("PASSWORD"));
				member.setRegDate(rs.getTimestamp("REGDATE"));
				member.setName(rs.getString("NAME"));
				member.setEmail(rs.getString("EMAIL"));
				member.setPasswordQuestion(rs.getString("PASSWORDQUESTION"));
				member.setPasswordAnswer(rs.getString("PASSWORDANSWER"));
				member.setAuth(rs.getString("AUTH"));
			}

		} finally {
			JdbcUtil.close(rs, pstmt);
		}

		return member;
	}
	
	public int deleteById(Connection con, String id) throws SQLException {
		
		String sql = "DELETE member_mbp "
				+ "WHERE id = ?";
		int row = 0;

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, id);

			row = pstmt.executeUpdate();
		}
		
		return row;
	}
	
	public void memberUpdate(Connection con, Member member) throws SQLException {

		String sql = "UPDATE member_mbp SET "
				+ "NICKNAME = ?, PASSWORD = ?, NAME = ?, EMAIL = ?, PASSWORDQUESTION = ?, PASSWORDANSWER = ? "
				+ "WHERE id = ?";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, member.getNickName());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getPasswordQuestion());
			pstmt.setString(6, member.getPasswordAnswer());
			pstmt.setString(7, member.getId());

			pstmt.executeUpdate();
		}
		
	}
	
	public Member selectById(Connection con, String id) throws SQLException {
		
		String sql = "SELECT * FROM member_mbp WHERE id=?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member member = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				member = new Member();
				member.setId(rs.getString("ID"));
				member.setNickName(rs.getString("NICKNAME"));
				member.setPassword(rs.getString("PASSWORD"));
				member.setRegDate(rs.getTimestamp("REGDATE"));
				member.setName(rs.getString("NAME"));
				member.setEmail(rs.getString("EMAIL"));
				member.setPasswordQuestion(rs.getString("PASSWORDQUESTION"));
				member.setPasswordAnswer(rs.getString("PASSWORDANSWER"));
				member.setAuth(rs.getString("AUTH"));
			}

		} finally {
			JdbcUtil.close(rs, pstmt);
		}

		return member;
	}

	public void memberInsert(Connection con, Member member) throws SQLException {

		String sql = "INSERT INTO member_mbp VALUES " + "(?, ?, ?, SYSDATE, ?, ?, ?, ?, NULL)";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getNickName());
			pstmt.setString(3, member.getPassword());
			pstmt.setString(4, member.getName());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getPasswordQuestion());
			pstmt.setString(7, member.getPasswordAnswer());

			pstmt.executeUpdate();
		}

	}

	

}
