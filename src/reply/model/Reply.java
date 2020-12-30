package reply.model;

import java.util.Date;

public class Reply {

	private int replyKey;
	private int depth;
	private int boardKey;
	private String boardName;
	private String user_id;
	private String user_nickName;
	private String reply;
	private Date regDate;
	private Date modDate;
	private int replyPrimaryKey;
	
	public Reply(int replyKey, int depth, int boardKey, String boardName, String user_id, String user_nickName, String reply, Date regDate,
			Date modDate, int replyPrimaryKey) {
		super();
		this.replyKey = replyKey;
		this.depth = depth;
		this.boardKey = boardKey;
		this.user_id = user_id;
		this.user_nickName = user_nickName;
		this.reply = reply;
		this.regDate = regDate;
		this.modDate = modDate;
		this.replyPrimaryKey = replyPrimaryKey;
		this.boardName = boardName;
	}
	public int getReplyKey() {
		return replyKey;
	}
	public int getDepth() {
		return depth;
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
	public int getReplyPrimaryKey() {
		return replyPrimaryKey;
	}
	public String getUser_id() {
		return user_id;
	}
	public String getBoardName() {
		return boardName;
	}
	
	

	
}
