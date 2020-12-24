package auth.service;

public class User {
	private String id;
	private String nickName;
	private int oneTimeCheck;
		
	public User(String id, String nickName) {
		this.id = id;
		this.nickName = nickName;
	}
	public String getId() {
		return id;
	}
	public String getNickName() {
		return nickName;
	}
	public int getOneTimeCheck() {
		return oneTimeCheck;
	}
	public void setOneTimeCheck(int oneTimeCheck) {
		this.oneTimeCheck = oneTimeCheck;
	}
	
	
	
	
	
	
}
