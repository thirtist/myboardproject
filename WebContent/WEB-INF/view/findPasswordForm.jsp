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
<h1>패스워드 찾기</h1>
<form action="${root }/findPassword.do" method="post">
	
	<c:if test="${errors.NotMatchNameOrEmail }">
	등록된 아이디의 이메일 또는 이름이 일치하지 않습니다</c:if>
	<br />
	아이디
	<input type="text" name="id" value="${param.id }"/>
	<c:if test="${errors.id }">아이디를 입력하세요</c:if>
	<c:if test="${errors.NotFoundIdException }">등록된아이디가 없습니다</c:if>
	<br />
	이메일
	<input type="email" name="email" value="${param.email }"/>
	<c:if test="${errors.email }">이메일을 입력하세요</c:if>
	<c:if test="${errors.NotFoundEmailException }">등록된이메일이 없습니다</c:if>
	<br />
	이름
	<input type="text" name="name" value="${param.name }"/>
	<c:if test="${errors.name }">이름를 입력하세요</c:if>
	<br />
	
	<c:if test="${find }">
	가입시 입력했던 패스워드 찾기 질문에 알맞은 답을 입력하고 패스워드 찾기를 누르세요
	<br />
	패스워드찾기 질문
	<input type="text" name="passwordQuestion" value="${passwordQuestion }" readonly/>
	<br />
	패스워드찾기 답
	<input type="text" name="passwordAnswer"/>
		<c:if test="${errors.password }">패스워드찾기 답을 입력하세요</c:if>
		<c:if test="${errors.passwordDiff }">패스워드찾기 답이 다릅니다</c:if>
	
	</c:if>
	<br />
	<button>패스워드 찾기</button>
</form>

</body>
</html>