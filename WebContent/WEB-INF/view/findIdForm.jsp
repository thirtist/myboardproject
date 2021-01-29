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

<form action="${root }/findId.do" method="post">
	<h1>아이디 찾기</h1>
	<c:if test="${errors.NotMatchNameOrEmail }">
	가입시 이메일과 이름이 일치하지 않습니다</c:if>
	가입시 이메일
	<br />
	<input type="email" name="email" value="${param.email }"/>
	<c:if test="${errors.email }">이메일을 입력하세요</c:if>
	<c:if test="${errors.NotFoundEmailException }">등록된이메일이 없습니다</c:if>
	<br />
	가입 시 이름
	<br />
	<input type="text" name="name" value="${param.name }"/>
	<c:if test="${errors.name }">이름를 입력하세요</c:if>
	<br />
	<br />
	<button class="btn btn-primary">찾기</button>
</form>

</u:page>
</body>
</html>