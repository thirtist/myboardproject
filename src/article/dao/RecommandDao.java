package article.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;

public class RecommandDao {

	public void createRecommandByBoardKey(Connection con, int boardKey, String id) throws SQLException {
		String sql = "INSERT INTO board_recommand (boardkey, id_for_recommand) VALUES " + "(?,?)";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setInt(1, boardKey);
			pstmt.setString(2, id);

			pstmt.executeUpdate();
		}

	}
	
	public int recommandCheckByBoardKeyAndId(Connection con, int boardKey, String id) throws SQLException {
		String sql = "SELECT ROWNUM FROM board_recommand WHERE boardkey = ? and id_for_recommand = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int number = 0;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardKey);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				number = rs.getInt(1);
			}

		} finally {
			JdbcUtil.close(rs, pstmt);
		}

		return number;

	}

	
	public int recommandNumberByBoardKey(Connection con, int boardKey) throws SQLException {
		String sql = "SELECT COUNT(*) FROM board_recommand WHERE boardkey = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int number = 0;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardKey);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				number = rs.getInt(1);
			}

		} finally {
			JdbcUtil.close(rs, pstmt);
		}

		return number;

	}

	public void removeRecommandByBoardKey(Connection con, int boardKey, String id) throws SQLException {
		String sql = "DELETE board_recommand WHERE boardkey = ? and id_for_recommand = ?";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setInt(1, boardKey);
			pstmt.setString(2, id);

			pstmt.executeUpdate();
		}
	}

}
