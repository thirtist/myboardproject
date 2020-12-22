<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script src="https://kit.fontawesome.com/a076d05399.js"></script>

<title>Insert title here</title>
</head>
<body>
<h1>새 패스워드로 변경</h1>

<form action="${root}/auth/changePassword.do" method="post">
	아이디
	<input type="text" name="id" value="${authUser.id } " readonly/>
	<br />
	새 비밀번호
	<input type="password" name="password"/>
	<c:if test="${errors.password }">패스워드를 입력하세요</c:if>
	<br />
	새 비밀번호확인
	<input type="password" name="passwordConfirm"/>
	<c:if test="${errors.passwordConfirm }">비밀번호확인을 입력하세요</c:if>
	<c:if test="${errors.passwordConfirmNotMatch }">비밀번호와 비밀번호확인이 같지 않습니다</c:if>
	<br />
	<button>비밀번호변경하기</button>
	<br />
</form>

</body>
</html>