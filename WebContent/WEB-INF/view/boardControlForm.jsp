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
			<a href="${root }/readBoardList.do">게시판목록보기</a>
			<br />
			<a href="${root }/admin/createBoard.do">신규게시판생성</a>
			<br />
			<a href="${root }/admin/deleteBoard.do">기존게시판제거</a>
		</div>
		
		<div class="col-2">
		</div>
	</div>
</div>
</body>
</html>