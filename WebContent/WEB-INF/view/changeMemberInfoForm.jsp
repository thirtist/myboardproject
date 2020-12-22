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

<script>

$(function(){
	$("#btn2").click(function() {
		location.href = "changeMemberInfo.do?email=" + $("#input2").val();
	});
});

</script>


<title>Insert title here</title>
</head>
<body>

<form action="${root }/auth/check/changeMemberInfo.do" method="post">
	
	<!--disable  -->
	아이디
	<input type="text" name="id" value="${member.id }" disabled="disabled"/>
	<c:if test="${errors.NotFoundIdException }">아이디를 찾을 수 없음(에러)</c:if>
	<br />

	이메일(중복불가)
	<input type="email" name="email" id="input2" value="${email }" />
	<c:if test="${errors.email }">이메일을 입력하세요</c:if>
	<c:if test="${errors.DuplicateEmailException}">중복된 이메일입니다</c:if>
	
	<!--버튼  -->	
	<button id="btn2" type="button">email중복확인</button>
	<c:if test="${existEmailButton == 'existEmail' }">중복된 이메일 입니다.</c:if>	
	<c:if test="${existEmailButton == 'none' }">사용가능한 이메일 입니다.</c:if>	
	<c:if test="${existEmailButton == 'emptyEmail' }">이메일을 입력하세요</c:if>	
	<!--  -->	
	<c:if test="${errors.DuplicateEmailException }">이메일이 중복됩니다</c:if>
	
	
	<br />

	닉네임
	<input type="text" name="nickName" value="${member.nickName }"/>
	<c:if test="${errors.nickName }">닉네임을 입력하세요</c:if>
	<br />
	비밀번호
	<input type="password" name="password"/>
	<c:if test="${errors.password }">패스워드를 입력하세요</c:if>
	<br />
	비밀번호확인
	<input type="password" name="passwordConfirm"/>
	<c:if test="${errors.passwordConfirm }">비밀번호확인을 입력하세요</c:if>
	<c:if test="${errors.passwordConfirmNotMatch }">비밀번호와 비밀번호확인이 같지 않습니다</c:if>
	<br />
	이름
	<input type="text" name="name" value="${member.name }"/>
	<c:if test="${errors.name }">이름을 입력하세요</c:if>
	<br />	
	
	비밀번호찾기문제
	<input type="text" name="passwordQuestion" value="${member.passwordQuestion }"/>
	<c:if test="${errors.passwordQuestion }">비밀번호 찾기 문제를 입력하세요</c:if>
	<br />
	비밀번호찾기답
	<input type="text" name="passwordAnswer" value="${member.passwordAnswer }"/>
	<c:if test="${errors.passwordAnswer }">비밀번호 찾기 답을 입력하세요</c:if>
	<br />
	<button>제출</button>
</form>

</body>
</html>