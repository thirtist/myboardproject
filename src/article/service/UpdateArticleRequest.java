package article.service;

import java.util.Map;

public class UpdateArticleRequest {

	private int boardKey;
	private String preTitle;
	private String title;
	private String content;
	private String user_id;
	private String boardName;

	public UpdateArticleRequest(int boardKey, String preTitle, String title, String content, String user_id, String boardName) {
		this.boardKey = boardKey;
		this.preTitle = preTitle;
		this.title = title;
		this.content = content;
		this.user_id = user_id;
		this.boardName = boardName;
	}

	public int getBoardKey() {
		return boardKey;
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
	
	public String getBoardName() {
		return boardName;
	}

	public void validate(Map<String, Boolean> errors) {

		if (title == null || title.isEmpty()) {
			errors.put("title",true);
		}
		if (content == null || content.isEmpty()) {
			errors.put("content",true);
		}
		
	}
	

}
