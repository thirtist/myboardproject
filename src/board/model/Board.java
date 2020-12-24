package board.model;

import java.util.Date;

public class Board {
	private int boardKey;
	private String boardName;
	private String preTitle;
	private String title;
	private String content;
	private String user_id;
	private String user_nickName;
	private Date regDate;
	private Date modDate;
	private int read_cnt;
	
	public Board(int boardKey, String boardName, String preTitle, String title, String content, String user_id,
			String user_nickName, Date regDate, Date modDate, int read_cnt) {

		this.boardKey = boardKey;
		this.boardName = boardName;
		this.preTitle = preTitle;
		this.title = title;
		this.content = content;
		this.user_id = user_id;
		this.user_nickName = user_nickName;
		this.regDate = regDate;
		this.modDate = modDate;
		this.read_cnt = read_cnt;
	}

	public int getBoardKey() {
		return boardKey;
	}

	public String getBoardName() {
		return boardName;
	}

	public String getPreTitle() {
		return preTitle;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getUser_id() {
		return user_id;
	}

	public String getUser_nickName() {
		return user_nickName;
	}

	public Date getRegDate() {
		return regDate;
	}

	public Date getModDate() {
		return modDate;
	}

	public int getRead_cnt() {
		return read_cnt;
	}
	
	
	
	
}
