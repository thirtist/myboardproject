package reply.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.service.NotMatchIdException;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import reply.dao.ReplyDao;
import reply.model.Reply;

public class DeleteReplyService {
	ReplyDao replyDao = new ReplyDao();
	
	public void deleteReply(int replyPrimaryKey, String id) throws SQLException {
		Connection con = ConnectionProvider.getConnection();
		Reply reply= null;
		
		try {

			reply = replyDao.readReplyByReplyPrimaryKey(con, replyPrimaryKey);
			
			if(reply == null) {
				throw new NullReplyException();
			}
			
			if(reply !=null && !reply.getUser_id().equals(id)) {
				throw new NotMatchIdException();
			}
			
			if(reply.getDepth()==0) {
				int replyKey = reply.getReplyKey();
				replyDao.deleteReplyByReplyKey(con, replyKey);
			} else {				
				replyDao.deleteReplyByReplyPrimaryKey(con, replyPrimaryKey);
			}


		
		} finally {
			JdbcUtil.close(con);
		}
	}

}
