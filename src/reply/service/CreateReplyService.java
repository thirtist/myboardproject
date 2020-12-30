package reply.service;

import java.sql.Connection;
import java.sql.SQLException;

import auth.service.User;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import reply.dao.ReplyDao;

public class CreateReplyService {
	ReplyDao replyDao = new ReplyDao();

	public void createReply(int boardKey, String boardName, User user, String reply) throws SQLException {
		Connection con = ConnectionProvider.getConnection();
		try {

			replyDao.createReplyByBoardKey(con, boardKey, boardName, user.getId(), user.getNickName(), reply);

		} finally {
			JdbcUtil.close(con);
		}
	}

	public void createSubReply(int replyKeyI, int boardKey, String boardName, User user, String reply2) throws SQLException {
		Connection con = ConnectionProvider.getConnection();
		try {

			replyDao.createSubReplyByReplyKey(con, replyKeyI, boardKey, boardName, user.getId(), user.getNickName(), reply2);

		} finally {
			JdbcUtil.close(con);
		}
	}
	
	
}
