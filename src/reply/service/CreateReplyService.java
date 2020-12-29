package reply.service;

import java.sql.Connection;
import java.sql.SQLException;

import auth.service.User;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import reply.dao.ReplyDao;

public class CreateReplyService {
	ReplyDao replyDao = new ReplyDao();

	public void createReply(int boardKey, User user, String reply) throws SQLException {
		Connection con = ConnectionProvider.getConnection();
		try {

			replyDao.createReplyByBoardKey(con, boardKey, user.getNickName(), reply);

		} finally {
			JdbcUtil.close(con);
		}
	}
	
	
}
