package article.service;

import java.util.Map;

public class WriteArticleRequest {
	private String boardName;
	private String preTitle;
	private String title;
	private String content;
	private String id;
	private String nickName;
	
	public WriteArticleRequest(String boardName, String preTitle, String title, String content, String id,
			String nickName) {

		this.boardName = boardName;
		this.preTitle = preTitle;
		this.title = title;
		this.content = content;
		this.id = id;
		this.nickName = nickName;
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

	public String getId() {
		return id;
	}

	public String getNickName() {
		return nickName;
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
