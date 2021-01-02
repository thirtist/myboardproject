package reply.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;
import reply.model.Reply;

public class ReplyDao {

	public void createReplyByBoardKey(Connection con, int boardKey, String boardName, String id, String nickName, String reply) throws SQLException {
		
		String sql = "INSERT INTO reply_mbp (replykey, dep, boardkey, boardname, user_id, user_nickname, reply, regdate, moddate)"
				+ "VALUES (REPLYKEY_SEQUENCE.nextval,0,?,?,?,?,?,SYSDATE, SYSDATE)";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setInt(1, boardKey);
			pstmt.setString(2, boardName);
			pstmt.setString(3, id);
			pstmt.setString(4, nickName);
			pstmt.setString(5, reply);

			pstmt.executeUpdate();
		}
		
	}
	
	public void createSubReplyByReplyKey(Connection con, int replyKeyI, int boardKey, String boardName, String id, String nickName, String reply2) throws SQLException {
		String sql = "INSERT INTO reply_mbp (replykey, dep, boardkey, boardName, user_id, user_nickname, reply, regdate, moddate)"
				+ "VALUES (?,1,?,?,?,?,?,SYSDATE, SYSDATE)";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setInt(1, replyKeyI);
			pstmt.setInt(2, boardKey);
			pstmt.setString(3, boardName);
			pstmt.setString(4, id);
			pstmt.setString(5, nickName);
			pstmt.setString(6, reply2);

			pstmt.executeUpdate();
		}
		
	}
	
	/*
	public List<Reply> readReplyByBoardKey(Connection con, int boardKey) throws SQLException {

		String sql = "SELECT * FROM reply_mbp WHERE boardkey = ? ORDER BY REPLYKEY, dep, regdate";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Reply> list = new ArrayList<>();

		try {

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardKey);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				int replyKey = rs.getInt("REPLYKEY");
				int depth = rs.getInt("DEP");
				boardKey = rs.getInt("BOARDKEY");
				String boardName = rs.getString("BOARDNAME");
				String user_id = rs.getString("USER_ID");
				String user_nickName = rs.getString("USER_NICKNAME");
				String reply = rs.getString("REPLY");
				Date regDate = rs.getTimestamp("REGDATE");
				Date modDate = rs.getTimestamp("MODDATE");
				int replyPrimaryKey = rs.getInt("REPLYPRIMARYKEY");
				
				Reply rep = new Reply(replyKey, depth, boardKey, boardName, user_id,
						user_nickName, reply, regDate, modDate, replyPrimaryKey);
				list.add(rep);
			}

		} finally {
			JdbcUtil.close(rs, pstmt);
		}

		return list;
	}
	*/
	
	public List<Reply> readReplyByBoardKey(Connection con, int boardKey, int pageNum, int onePageNum) throws SQLException {

		String sql = "SELECT rn, replykey, dep, boardKey, boardName, user_id, user_nickName, reply, regDate, moddate, replyprimaryKey FROM "
					+"(SELECT ROWNUM rn, replykey, dep, boardKey, boardName, user_id, user_nickName, reply, regDate, moddate, replyprimaryKey "
					+"FROM (SELECT * FROM reply_mbp WHERE BOARDKEY = ? ORDER BY REPLYKEY, dep, regdate) ORDER BY rn ) WHERE rn BETWEEN ? and ?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Reply> list = new ArrayList<>();

		try {

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardKey);
			pstmt.setInt(2, pageNum * onePageNum - onePageNum + 1);
			pstmt.setInt(3, pageNum * onePageNum);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {

				int replyKey = rs.getInt("REPLYKEY");
				int depth = rs.getInt("DEP");
				boardKey = rs.getInt("BOARDKEY");
				String boardName = rs.getString("BOARDNAME");
				String user_id = rs.getString("USER_ID");
				String user_nickName = rs.getString("USER_NICKNAME");
				String reply = rs.getString("REPLY");
				Date regDate = rs.getTimestamp("REGDATE");
				Date modDate = rs.getTimestamp("MODDATE");
				int replyPrimaryKey = rs.getInt("REPLYPRIMARYKEY");
				
				Reply rep = new Reply(replyKey, depth, boardKey, boardName, user_id,
						user_nickName, reply, regDate, modDate, replyPrimaryKey);
				list.add(rep);
			}

		} finally {
			JdbcUtil.close(rs, pstmt);
		}

		return list;
	}
	
	

	public Reply readReplyByReplyPrimaryKey(Connection con, int replyPrimaryKey) throws SQLException {
		String sql = "SELECT * FROM reply_mbp WHERE replyPrimaryKey = ?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Reply rep = null;
		try {

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, replyPrimaryKey);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				int replyKey = rs.getInt("REPLYKEY");
				int depth = rs.getInt("DEP");
				int boardKey = rs.getInt("BOARDKEY");
				String boardName = rs.getString("BOARDNAME");
				String user_id = rs.getString("USER_ID");
				String user_nickName = rs.getString("USER_NICKNAME");
				String reply = rs.getString("REPLY");
				Date regDate = rs.getTimestamp("REGDATE");
				Date modDate = rs.getTimestamp("MODDATE");
				replyPrimaryKey = rs.getInt("REPLYPRIMARYKEY");
				
				rep = new Reply(replyKey, depth, boardKey, boardName, user_id, user_nickName, reply, regDate, modDate, replyPrimaryKey);
			}

		} finally {
			JdbcUtil.close(rs, pstmt);
		}

		return rep;
	}

	public void deleteReplyByReplyPrimaryKey(Connection con, int replyPrimaryKey) throws SQLException {
		
		String sql = "DELETE reply_mbp WHERE replyprimarykey = ?";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setInt(1, replyPrimaryKey);

			pstmt.executeUpdate();
		}
	}

	public void deleteReplyByReplyKey(Connection con, int replyKey) throws SQLException {
		String sql = "DELETE reply_mbp WHERE replyKey = ?";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setInt(1, replyKey);

			pstmt.executeUpdate();
		}
	}

	public void deleteReplyByBoardKey(Connection con, int boardKey) throws SQLException {
		String sql = "DELETE reply_mbp WHERE boardkey = ?";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setInt(1, boardKey);

			pstmt.executeUpdate();
		}
	}

	public void deleteReplyByBoardName(Connection con, String boardName) throws SQLException {
		String sql = "DELETE reply_mbp WHERE boardName = ?";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, boardName);

			pstmt.executeUpdate();
		}
		
	}
	
	public int getReplyNumByBoardKey(Connection con, int boardKey) throws SQLException {

		String sql = "SELECT count(*) FROM reply_mbp WHERE boardKey = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardKey);
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
