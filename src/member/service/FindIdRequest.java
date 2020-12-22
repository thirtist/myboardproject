package member.service;

import java.util.Map;

public class FindIdRequest {
	private String name;
	private String email;
		
	public FindIdRequest(String name, String email) {
		this.name = name;
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	
	public void validate(Map<String, Boolean> errors) {

		if (name == null || name.isEmpty()) {
			errors.put("name",true);
		}
		if (email == null || email.isEmpty()) {
			errors.put("email",true);
		}
	}
	
	
}
