package reply.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import reply.dao.ReplyDao;
import reply.model.Reply;

public class ReadReplyService {
	ReplyDao replyDao = new ReplyDao();

	/*
	public List<Reply> readReply(int boardKey) throws SQLException {
		Connection con = ConnectionProvider.getConnection();
		
		List<Reply> list = null;
		try {

			list =replyDao.readReplyByBoardKey(con, boardKey);

		} finally {
			JdbcUtil.close(con);
		}
		
		return list;
	}
	*/
	
	public List<Reply> readReply(int boardKey, int pageNum, int onePageNum) throws SQLException {
		Connection con = ConnectionProvider.getConnection();
		
		List<Reply> list = null;
		try {

			list =replyDao.readReplyByBoardKey(con, boardKey, pageNum, onePageNum);

		} finally {
			JdbcUtil.close(con);
		}
		
		return list;
	}
	
	
	public int getReplyNum(int boardKey) throws SQLException {
		Connection con = ConnectionProvider.getConnection();
		
		int replyNum = 0;
		try {

			replyNum =replyDao.getReplyNumByBoardKey(con, boardKey);

		} finally {
			JdbcUtil.close(con);
		}
		
		return replyNum;
	}
	
}
