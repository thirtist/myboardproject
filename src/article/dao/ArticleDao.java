package article.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import article.service.UpdateArticleRequest;
import article.service.WriteArticleRequest;
import board.model.Board;
import jdbc.JdbcUtil;

public class ArticleDao {

	public void writeArticleByRequest(Connection con, WriteArticleRequest war) throws SQLException {

		String sql = "INSERT INTO board "
				+ "(BOARDNAME, ARTICLENO, PRETITLE, TITLE, CONTENT, USER_ID, USER_NICKNAME, REGDATE, MODDATE) "
				+ "VALUES (?," + war.getBoardName() + "_sequence.nextval" + ",?, ?, ?, ?, ?, SYSDATE, SYSDATE)";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, war.getBoardName());
			pstmt.setString(2, war.getPreTitle());
			pstmt.setString(3, war.getTitle());
			pstmt.setString(4, war.getContent());
			pstmt.setString(5, war.getId());
			pstmt.setString(6, war.getNickName());

			pstmt.executeUpdate();
		}

	}

	public Board readArticleByBoardKey(Connection con, int boardKey) throws SQLException {

		String sql = "SELECT * FROM board WHERE boardkey = ?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board board = null;

		try {

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardKey);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				String boardName = rs.getString("BOARDNAME");
				int articleNo = rs.getInt("ARTICLENO");
				String preTitle = rs.getString("PRETITLE");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				String user_id = rs.getString("USER_ID");
				String user_nickName = rs.getString("USER_NICKNAME");
				Date regDate = rs.getTimestamp("REGDATE");
				Date modDate = rs.getTimestamp("MODDATE");
				int read_cnt = rs.getInt("READ_CNT");

				board = new Board(boardKey, boardName, articleNo, preTitle, title, content, user_id, user_nickName,
						regDate, modDate, read_cnt);

			}

		} finally {
			JdbcUtil.close(rs, pstmt);
		}

		return board;
	}

	public void updateArticleByBoardKey(Connection con, UpdateArticleRequest uar) throws SQLException {

		String sql = "UPDATE board SET " + "PRETITLE = ?, TITLE = ?, CONTENT = ?, MODDATE = SYSDATE "
				+ "WHERE boardkey = ?";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, uar.getPreTitle());
			pstmt.setString(2, uar.getTitle());
			pstmt.setString(3, uar.getContent());
			pstmt.setInt(4, uar.getBoardKey());

			pstmt.executeUpdate();
		}
	}

	public void deleteArticleByBoardKey(Connection con, int boardKey) throws SQLException {
		String sql = "DELETE board WHERE boardkey = ?";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setInt(1, boardKey);

			pstmt.executeUpdate();
		}
		
	}

	public void addCountByBoardKey(Connection con, int boardKey) throws SQLException {
		String sql = "UPDATE board SET read_cnt = read_cnt + 1  WHERE boardkey = ?";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setInt(1, boardKey);

			pstmt.executeUpdate();
		}
		
	}
}
