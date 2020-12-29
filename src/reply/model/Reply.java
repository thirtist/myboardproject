package reply.model;

import java.util.Date;

public class Reply {

	private int replyKey;
	private int parentReplyKey;
	private int boardKey;
	private String user_nickName;
	private String reply;
	private Date regDate;
	private Date modDate;

	public Reply(int replyKey, int parentReplyKey, int boardKey, String user_nickName, String reply, Date regDate,
			Date modDate) {
				this.replyKey = replyKey;
				this.parentReplyKey = parentReplyKey;
				this.boardKey = boardKey;
				this.user_nickName = user_nickName;
				this.reply = reply;
				this.regDate = regDate;
				this.modDate = modDate;
	}

	public int getReplyKey() {
		return replyKey;
	}

	public int getParentReplyKey() {
		return parentReplyKey;
	}

	public int getBoardKey() {
		return boardKey;
	}

	public String getUser_nickName() {
		return user_nickName;
	}

	public String getReply() {
		return reply;
	}

	public Date getRegDate() {
		return regDate;
	}

	public Date getModDate() {
		return modDate;
	}

}
