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

<c:if test="${!empty authUser }">
<h1>${authUser.nickName }님 환영합니다</h1>
<a href="${root }/auth/logout.do">로그아웃</a>
<a href="${root }/auth/check/changeMemberInfo.do">회원정보수정</a>
<a href="${root }/auth/check/deleteMember.do">회원탈퇴</a>
 	<c:if test="${authUser.id == 'admin' }">
		<a href="${root }/admin/boardControl.do">게시판관리</a>
 	</c:if>
</c:if>


<c:if test="${empty authUser }">
	<a href="${root}/join.do">회원가입</a>
	<a href="${root}/login.do">로그인</a>
	<a href="${root}/findId.do">아이디찾기</a>
	<a href="${root}/findPassword.do">패스워드찾기</a>
</c:if>
</body>
</html>