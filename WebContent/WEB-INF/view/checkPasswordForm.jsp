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


<div class="container mb-3">
<u:navbar></u:navbar>
	<div class="row">
		<div class="col-2">
		</div>
		
		<div class="col-8">		
		<h1>비밀번호확인</h1>		
		<form action="${root }/auth/checkPassword.do" method="post">
			<!--disable  -->
			아이디
			<input type="text" name="id" value="${sessionScope.authUser.id }" disabled="disabled"/>
			<c:if test="${errors.notFoundId }">아이디를 찾을 수 없음(에러)</c:if>
			<br />
		
			비밀번호
			<input type="password" name="password"/>
			<c:if test="${errors.password }">패스워드를 입력하세요</c:if>
			<c:if test="${errors.notMatchPassword }">패스워드가 일치하지 않습니다</c:if>
			<br />
			
			<button class="btn btn-primary">제출</button>
		</form>
				
		
		</div>
		
		<div class="col-2">
		</div>
	</div>
</div>


</body>
</html>