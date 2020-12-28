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
	<form action="${root }/auth/writeArticle.do?boardName=${param.boardName}" method="post">
		말머리
		<input size="5" name = preTitle type="text" value="${param.preTitle }"/>	
		제목
		<input name = title type="text" value="${param.title }"/>
		<c:if test="${errors.title }">제목을 입력하세요</c:if>
		<br />
		내용
		<br />
		<textarea style="resize: none;" name = content rows="10" cols="50" >${param.content }</textarea>
		<c:if test="${errors.content }">내용을 입력하세요</c:if>
		<br />
		<button>글쓰기</button>

	</form>
</body>
</html>