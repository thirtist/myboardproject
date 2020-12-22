package member.service;

import java.util.Map;

public class MemberRequest {
	private String id;
	private String nickName;
	private String password;
	private String passwordConfirm;
	private String name;
	private String email;
	private String passwordQuestion;
	private String passwordAnswer;
	private String auth;

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordQuestion() {
		return passwordQuestion;
	}

	public void setPasswordQuestion(String passwordQuestion) {
		this.passwordQuestion = passwordQuestion;
	}

	public String getPasswordAnswer() {
		return passwordAnswer;
	}

	public void setPasswordAnswer(String passwordAnswer) {
		this.passwordAnswer = passwordAnswer;
	}

	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors, id, "id");
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
