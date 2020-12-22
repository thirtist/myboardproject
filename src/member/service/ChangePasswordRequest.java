package member.service;

import java.util.Map;

public class ChangePasswordRequest {
	private String id;
	private String password;
	private String passwordConfirm;
	
	public ChangePasswordRequest(String id, String password, String passwordConfirm) {
		this.id = id;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
	}
	
	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void validate(Map<String, Boolean> errors) {

		if (password == null || password.isEmpty()) {
			errors.put("password",true);
		}
		if (passwordConfirm == null || passwordConfirm.isEmpty()) {
			errors.put("passwordConfirm",true);
		}
		if (password !=null && !password.isEmpty() &&
			passwordConfirm !=null && !passwordConfirm.isEmpty() &&
			!password.equals(passwordConfirm)
			) {
			errors.put("passwordConfirmNotMatch",true);
		}
	}
}
