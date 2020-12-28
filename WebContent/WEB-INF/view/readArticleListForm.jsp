<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<div class="container">
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>말머리</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>작성일</th>
				<th>조회</th>
				<th>추천수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="a" items="${ArticleList }">
				<tr>

					<td>${a.articleNo }</td>
					<td>${a.preTitle }</td>
					<td>
						<a href="${root }/auth/readArticle.do?boardKey=${a.boardKey }">
							${a.title }
						</a>
					</td>
					<td>${a.user_nickName }</td>
					<td>${a.regDate }</td>
					<td>${a.read_cnt }</td>
					<td></td>
				</tr>
			</c:forEach>

		</tbody>

	</table>
	
	<br />
	<c:forEach var="i" begin="1" end="${pageEnd }">
		<a href="${root }/readArticleList.do?boardName=${boardName}&pageNum=${pageEnd-i+1}">${pageEnd-i+1 }</a>
	</c:forEach>
	<br />

	<br />
	<form action="${root }/auth/writeArticle.do">
	<input type="text" name="boardName" value = "${boardName}"hidden="true"/>
		<button>글쓰기</button>
	</form>
	
	
	
	

</div>

</body>
</html>