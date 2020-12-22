package auth.service;

import java.util.Map;

public class ChangeMemberInfoRequest {
	private String nickName;
	private String password;
	private String passwordConfirm;
	private String name;
	private String email;
	private String passwordQuestion;
	private String passwordAnswer;
	
	
	
	
	public ChangeMemberInfoRequest(String nickName, String password, String passwordConfirm, String name, String email,
			String passwordQuestion, String passwordAnswer) {
		this.nickName = nickName;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
		this.name = name;
		this.email = email;
		this.passwordQuestion = passwordQuestion;
		this.passwordAnswer = passwordAnswer;
	}

	
	
	public String getNickName() {
		return nickName;
	}



	public String getPassword() {
		return password;
	}



	public String getPasswordConfirm() {
		return passwordConfirm;
	}



	public String getName() {
		return name;
	}



	public String getEmail() {
		return email;
	}



	public String getPasswordQuestion() {
		return passwordQuestion;
	}



	public String getPasswordAnswer() {
		return passwordAnswer;
	}



	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors, nickName, "nickName");
		checkEmpty(errors, password, "password");
		checkEmpty(errors, passwordConfirm, "passwordConfirm");
		checkEmpty(errors, name, "name");
		checkEmpty(errors, email, "email");
		checkEmpty(errors, passwordQuestion, "passwordQuestion");
		checkEmpty(errors, passwordAnswer, "passwordAnswer");
		
		if(!password.isEmpty() && !passwordConfirm.isEmpty() && !password.equals(passwordConfirm)) {
			errors.put("passwordConfirmNotMatch",true);
		}
		
	}

	private void checkEmpty(Map<String, Boolean> errors, String field, String key ) {
		if (field == null || field.isEmpty()) {
			errors.put(key, true);
		}
	}
}
