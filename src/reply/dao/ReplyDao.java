package reply.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import board.model.Board;
import jdbc.JdbcUtil;
import reply.model.Reply;

public class ReplyDao {

	public void createReplyByBoardKey(Connection con, int boardKey, String nickName, String reply) throws SQLException {
		
		String sql = "INSERT INTO reply_mbp (boardkey, user_nickname, reply, regdate, moddate)"
				+ "VALUES (?,?,?,SYSDATE, SYSDATE)";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setInt(1, boardKey);
			pstmt.setString(2, nickName);
			pstmt.setString(3, reply);

			pstmt.executeUpdate();
		}
		
	}
	
	public List<Reply> readReplyByBoardKey(Connection con, int boardKey) throws SQLException {

		String sql = "SELECT * FROM reply_mbp WHERE boardkey = ? ORDER BY regdate DESC";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Reply> list = new ArrayList<>();

		try {

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardKey);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				int replyKey = rs.getInt("REPLYKEY");
				int parentReplyKey = rs.getInt("PARENTREPLYKEY");
				boardKey = rs.getInt("BOARDKEY");
				String user_nickName = rs.getString("USER_NICKNAME");
				String reply = rs.getString("REPLY");
				Date regDate = rs.getTimestamp("REGDATE");
				Date modDate = rs.getTimestamp("MODDATE");
				
				Reply rep = new Reply(replyKey, parentReplyKey, boardKey,
						user_nickName, reply, regDate, modDate);
				list.add(rep);
			}

		} finally {
			JdbcUtil.close(rs, pstmt);
		}

		return list;
	}
	

}
