package member.service;

import java.util.Map;

public class FindPasswordRequest {
	private String id;
	private String name;
	private String email;
	
	public FindPasswordRequest(String id, String name, String email) {
	
		this.id = id;
		this.name = name;
		this.email = email;
	}

	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public void validate(Map<String, Boolean> errors) {

		if (id == null || id.isEmpty()) {
			errors.put("id",true);
		}
		if (name == null || name.isEmpty()) {
			errors.put("name",true);
		}
		if (email == null || email.isEmpty()) {
			errors.put("email",true);
		}
	}
	
}
