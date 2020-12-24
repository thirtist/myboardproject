package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;

public class BoardDao {

	public void deleteBoardByBoardName(Connection con, String boardName) throws SQLException {
		String sql = "DELETE board WHERE boardname = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, boardName);

			pstmt.executeUpdate();
		}

	}
	
	public List<String> getBoardList(Connection con) throws SQLException {
		String sql = "SELECT boardname FROM board GROUP by boardname ORDER BY boardname";
		List<String> list = new ArrayList<String>();
		Statement stmt = null;
		ResultSet rs = null;

		try {

			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				list.add(rs.getString("BOARDNAME"));
			}
			
		} finally {
			JdbcUtil.close(rs, stmt);
		}
		return list;
	}

	public void createBoardByAdmin(Connection con, String boardName) throws SQLException {

		String sql = "INSERT INTO board "
				+ "(BOARDNAME, PRETITLE, TITLE, CONTENT, USER_ID, USER_NICKNAME, REGDATE, MODDATE) "
				+ "VALUES (?, '[운영]', " + "'[" + boardName + "]게시판이 신설되었습니다', "
				+ "'게시판이 신설되었습니다', 'admin', '[운영자]', SYSDATE, SYSDATE)";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, boardName);

			pstmt.executeUpdate();
		}

	}

}