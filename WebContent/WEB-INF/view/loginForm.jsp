<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script src="https://kit.fontawesome.com/a076d05399.js"></script>

<title>Insert title here</title>
</head>
<body>

	<div class="container">
	
	<u:navbar></u:navbar>

	<div class="row">
		<div class="col-2">
		</div>
		<div class="d-flex col-4">
			<u:logInBar></u:logInBar>
		</div>	
		<div class="col-6">
		</div>
	</div>
</div>


<%-- 	<form action="${root }/login.do" method="post">
		아이디 <input type="text" name="id" value="${param.id }"/>
		<c:if test="${errors.id }">아이디를 입력하세요</c:if>
		<c:if test="${errors.notFoundId }">아이디를 찾을 수 없습니다</c:if>
		<br />
		비밀번호 <input type="password" name="password" />
		<c:if test="${errors.password }">패스워드를 입력하세요</c:if>
		<c:if test="${errors.notMatchPassword }">패스워드가 다릅니다</c:if>
		<br />
		<button>로그인</button>
	</form>
	<a href="${root }/join.do">회원가입</a>
	<a href="${root }/findId.do">아이디찾기</a>
	<a href="${root }/findPassword.do">패스워드찾기</a> --%>
</body>
</html>