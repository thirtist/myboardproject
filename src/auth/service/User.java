package auth.service;

public class User {
	private String id;
	private String nickName;
	private String auth;
	private int oneTimeCheck;
		
	public User(String id, String nickName, String auth) {
		this.id = id;
		this.nickName = nickName;
		this.auth = auth;
	}
	public String getId() {
		return id;
	}
	public String getNickName() {
		return nickName;
	}
	public String getAuth() {
		return auth;
	}
	public int getOneTimeCheck() {
		return oneTimeCheck;
	}
	public void setOneTimeCheck(int oneTimeCheck) {
		this.oneTimeCheck = oneTimeCheck;
	}
	
	
	
	
	
	
}
