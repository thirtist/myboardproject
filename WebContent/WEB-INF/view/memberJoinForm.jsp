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

<script>
$(function(){
	$("#btn1").click(function() {
		location.href = "join.do?id="+$("#input1").val()
	/* 			+"&email="+$("#input2").val(); */
	});
});

$(function(){
	$("#btn2").click(function() {
		location.href = "join.do?id="+$("#input1").val()
		+"&email="+$("#input2").val();/* 
		location.href = "join.do?email="+$("#input2").val(); */
	});
});

</script>

<u:css></u:css>

<title>Insert title here</title>
</head>
<body>

<u:page>

	<h1>회원가입</h1>

	<form action="${root }/join.do" method="post">
	<div class="row">
	<div class="col">
		<div class="form-group">
		<div class="d-flex">
		<div class="col">
			아이디
			<br />
			<input class="form-control" type="text" name="id" value="${param.id }" id="input1"/>
			<small>
			<c:if test="${existIdButton == 'existId' }">중복된 아이디 입니다.</c:if>	
			<c:if test="${existIdButton == 'none' }">사용가능한 아이디 입니다.</c:if>	
			<c:if test="${existIdButton == 'emptyId' }">아이디를입력하세요</c:if>	
			<c:if test="${errors.id }">아이디를 입력하세요</c:if>
			<c:if test="${errors.duplicateId }">중복된 아이디 입니다.</c:if>
			</small>
		</div>
		
		<div class="col-5">
		<!--버튼  -->
		<br />	
			<button id="btn1" type="button" class="btn btn-primary"><small>ID중복확인</small></button>
		</div>
		</div>	
		
		</div>
		<div class="form-group">
		<div class="d-flex">
		<div class="col">
			이메일(중복불가)
			<br />
			<input class="form-control" type="email" name="email" value="${param.email }" id="input2"/>
			<small>
			<c:if test="${existEmailButton == 'existEmail' }">중복된 이메일 입니다.</c:if>	
			<c:if test="${existEmailButton == 'none' }">사용가능한 이메일 입니다.</c:if>	
			<c:if test="${existEmailButton == 'emptyEmail' }">이메일을 입력하세요</c:if>		
			<c:if test="${errors.DuplicateEmailException }">이메일이 중복됩니다</c:if>
			<c:if test="${errors.email }">이메일을 입력하세요</c:if>
			</small>
		</div>
		<div class="col-5">
		<!--버튼  -->	
		<br />
		<button id="btn2" type="button" class="btn btn-primary"><small>Email중복확인</small></button>
		</div>		
		</div>
		</div>
		
		<div class="form-group">
		<div class="col">
		닉네임
		<br />
		<input class="form-control" type="text" name="nickName" value="${param.nickName }"/>
		<small>
		<c:if test="${errors.nickName }">닉네임을 입력하세요</c:if>
		</small>
		</div>
		</div>
		
		<div class="form-group">
		<div class="col">
		비밀번호
		<br />
		<input class="form-control" type="password" name="password"/>
		<small>
		<c:if test="${errors.password }">패스워드를 입력하세요</c:if>
		</small>
		</div>
		</div>
		
		<div class="form-group">
		<div class="col">
		비밀번호확인
		<br />
		<input class="form-control" type="password" name="passwordConfirm"/>
		<small>
		<c:if test="${errors.passwordConfirm }">비밀번호확인을 입력하세요</c:if>
		<c:if test="${errors.passwordConfirmNotMatch }">비밀번호와 비밀번호확인이 같지 않습니다</c:if>
		</small>
		<br />
		</div>
		</div>
		
	</div>
	<div class="col">
		<div class="form-group">
		<div class="col">
		이름
		<br />
		<input type="text" class="form-control" name="name" value="${param.name }"/>
		<c:if test="${errors.name }">이름을 입력하세요</c:if>
		<br />
		</div>
		</div>
		
		<div class="form-group">
		<div class="col">		
		비밀번호찾기문제
		<br />
		<input type="text" class="form-control" name="passwordQuestion" value="${param.passwordQuestion }"/>
		<c:if test="${errors.passwordQuestion }">비밀번호 찾기 문제를 입력하세요</c:if>
		<br />
		</div>
		</div>
		
		<div class="form-group">
		<div class="col">
		비밀번호찾기답
		<br />
		<input type="text" class="form-control" name="passwordAnswer" value="${param.passwordAnswer }"/>
		<c:if test="${errors.passwordAnswer }">비밀번호 찾기 답을 입력하세요</c:if>
		<br />
		<br />
		</div>
		</div>
		
		<div class="col d-flex justify-content-end">
		<button class="btn btn-primary">제출</button>
		</div>
	</div>
	</div>
	</form>

</u:page>

</body>
</html>