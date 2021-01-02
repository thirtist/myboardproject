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

<div class="container mb-3">
<u:navbar></u:navbar>
	<div class="row">
		<div class="col-2">
		</div>
		
		<div class="col-8 d-flex align-content-center">
			${error }
			<form action="${root }/admin/deleteBoard.do" method="post">
			제거할 게시판 선택
			<br />
			<select name="boardName">
				<c:forEach var="i" items="${boardList }">
					<option value="${i }">${i }</option>
				</c:forEach>
			</select>
			<button class="btn-primary">제거</button>
			</form>
		</div>
		
		<div class="col-2">
		</div>
	</div>
</div>


</body>
</html>