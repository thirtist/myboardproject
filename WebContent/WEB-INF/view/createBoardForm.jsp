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
		
		<div class="col-8 d-flex align-content-center">
			<form action="${root }/admin/createBoard.do" method="post">
				생성할 게시판 이름
				<br />
				<input type="text" name="boardName"/>
				<button class="btn btn-primary">게시판생성</button>
				<br />
				<c:if test="${errors.boardName }">게시판이름을 입력하세요</c:if>
				<c:if test="${errors.DuplicateBoardNameException }">게시판이름이 중복됩니다.</c:if>
			</form>
		</div>
		
		<div class="col-2">
		</div>
	</div>
</div>


</body>
</html>