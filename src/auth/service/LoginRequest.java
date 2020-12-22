package auth.service;

import java.util.Map;

public class LoginRequest {
	private String id;
	private String password;
	
	public LoginRequest(String id, String password) {
		this.id = id;
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}
	
	public void validate(Map<String, Boolean> errors) {
		if(id==null || id.isEmpty()) {
			errors.put("id", true);
		}
		if(password==null || password.isEmpty()) {
			errors.put("password", true);
		}
		
	}
	
	

}
