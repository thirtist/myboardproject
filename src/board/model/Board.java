package board.model;

import java.util.Date;

public class Board {
	private int boardKey;
	private String boardName;
	private int articleNo;
	private String preTitle;
	private String title;
	private String content;
	private String user_id;
	private String user_nickName;
	private Date regDate;
	private Date modDate;
	private int read_cnt;
	
	
	public Board(int boardKey, String boardName, int articleNo, String preTitle, String title, String content,
			String user_id, String user_nickName, Date regDate, Date modDate, int read_cnt) {
		
		this.boardKey = boardKey;
		this.boardName = boardName;
		this.articleNo = articleNo;
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

	public int getArticleNo() {
		return articleNo;
	}

	public void setBoardKey(int boardKey) {
		this.boardKey = boardKey;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}

	public void setPreTitle(String preTitle) {
		this.preTitle = preTitle;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public void setUser_nickName(String user_nickName) {
		this.user_nickName = user_nickName;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}

	public void setRead_cnt(int read_cnt) {
		this.read_cnt = read_cnt;
	}	
	
	
}
