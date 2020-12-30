package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import board.model.Board;
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
				+ "(BOARDNAME, ARTICLENO, PRETITLE, TITLE, CONTENT, USER_ID, USER_NICKNAME, REGDATE, MODDATE) "
				+ "VALUES (?, " + boardName + "_sequence.nextval" + ", '[운영]', " + "'[" + boardName + "]게시판이 신설되었습니다', "
				+ "'게시판이 신설되었습니다', 'admin', '[운영자]', SYSDATE, SYSDATE)";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, boardName);

			pstmt.executeUpdate();
		}

	}

	public void createSeq(Connection con, String boardName) throws SQLException {

		String sql = "CREATE SEQUENCE " + boardName + "_sequence";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.executeUpdate();
		}

	}

	public void deleteSeq(Connection con, String boardName) throws SQLException {

		String sql = "DROP SEQUENCE " + boardName + "_sequence";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.executeUpdate();
		}

	}

	public List<Board> getArticleListByBoardName(Connection con, String boardName, int pageNum, int onePageNum)
			throws SQLException {

		String sql = "SELECT rn, boardKey, boardName, articleno, preTitle, title, CONTENT, user_id, user_nickName, regDate, moddate, read_cnt FROM "
				+ "(SELECT ROWNUM rn, boardKey, boardName, articleno, preTitle, title, CONTENT, user_id, user_nickName, regDate, moddate, read_cnt "
				+ "FROM (SELECT * FROM board WHERE BOARDNAME = ? ORDER BY ARTICLENO DESC) ORDER BY rn ) WHERE rn BETWEEN ? and ?";
		List<Board> list = new ArrayList<Board>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardName);
			pstmt.setInt(2, pageNum * onePageNum - onePageNum + 1);
			pstmt.setInt(3, pageNum * onePageNum);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				int boardKey = rs.getInt("BOARDKEY");
				boardName = rs.getString("BOARDNAME");
				int articleNo = rs.getInt("ARTICLENO");
				String preTitle = rs.getString("PRETITLE");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				String user_id = rs.getString("USER_ID");
				String user_nickName = rs.getString("USER_NICKNAME");
				Date regDate = rs.getTimestamp("REGDATE");
				Date modDate = rs.getTimestamp("MODDATE");
				int read_cnt = rs.getInt("READ_CNT");

				Board board = new Board(boardKey, boardName, articleNo, preTitle, title, content, user_id,
						user_nickName, regDate, modDate, read_cnt);
				
				list.add(board);

			}

		} finally {
			JdbcUtil.close(rs, pstmt);
		}
		return list;

	}

	public int getTotalArticleByBoardName(Connection con, String boardName) throws SQLException {

		String sql = "SELECT COUNT(*) FROM board WHERE boardname = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardName);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}

}