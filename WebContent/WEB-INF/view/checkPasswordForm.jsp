<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
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
<u:page>

	<h1>비밀번호확인</h1>
		<form class="form-group" action="${root }/auth/checkPassword.do" method="post">
			<div class="col-4">
			<!--disable  -->
			아이디
			<input class="form-control" type="text" name="id" value="${sessionScope.authUser.id }" disabled="disabled"/>
			<small>
			<c:if test="${errors.notFoundId }">아이디를 찾을 수 없음(에러)</c:if>
			</small>
			<br />
		
			비밀번호
			<input class="form-control" type="password" name="password"/>
			<c:if test="${errors.password }">패스워드를 입력하세요</c:if>
			<c:if test="${errors.notMatchPassword }">패스워드가 일치하지 않습니다</c:if>
			<br />
			
			<button class="btn btn-primary">제출</button>
			</div>
		</form>
</u:page>
</body>
</html>