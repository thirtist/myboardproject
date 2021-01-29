<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags/"%>
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


<style type="text/css">
 .table_all {
 	font-size:12px;
 	text-align:center;
 }
 .table_all .table_left {
 	text-align:left;
 }
 .table_all .table_right {
 	text-align:right;
 }
 
 a {
 	color : black;
 }
 
 a:link {
 	text-decoration: none;
 }
 
</style>

<title>Insert title here</title>
</head>
<body>
	
	<div class="table_all">		
	<table class="table">
		<thead>
			<tr>
				<th scope="col">번호</th>
				<th scope="col">말머리</th>
				<th scope="col" class="px-0"></th>
				<th scope="col">제목</th>
				<th scope="col">글쓴이</th>
				<th scope="col">작성일</th>
				<th scope="col">조회</th>
				<th scope="col">추천수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="a" items="${ArticleList }">
				<tr>
					<u:recommandNum boardKey="${a.boardKey }"></u:recommandNum>
					<td>${a.articleNo }</td>
					<td>${a.preTitle }</td>
					<td class="px-0"><c:if test="${recommandNum >2}">
						<span style="font-size: 12px; color: Dodgerblue;">
						<i class="fas fa-star"></i>
						</span>
						</c:if>
					</td>
					<td class="table_left">
						<a href="${root }/auth/readArticle.do?boardKey=${a.boardKey }">
							<c:out value ="${a.title }" />
							<u:replyNum boardKey="${a.boardKey }"></u:replyNum>
						</a>
					</td>
					<td>${a.user_nickName }</td>
					<td>
					<jsp:useBean id="today" class="java.util.Date" />					
					<fmt:formatDate var="to" value="${today}" pattern="yyyy-MM-dd"/>
					<fmt:formatDate var="reg" value="${a.regDate}" pattern="yyyy-MM-dd"/>
					<c:if test="${to eq reg }">
						<fmt:formatDate value="${a.regDate}" pattern="HH:mm"/>
					</c:if>
					<c:if test="${to != reg }">
						<fmt:formatDate value="${a.regDate}" pattern="MM-dd"/>
					</c:if>
					</td>
					<td>${a.read_cnt }</td>
					<td>${recommandNum }</td> <!--recommandNum.tag에서 가져옴  -->
					
				</tr>
			</c:forEach>

		</tbody>

	</table>
	</div>
</body>
</html>