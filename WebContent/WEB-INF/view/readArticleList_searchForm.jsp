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

<script>
$(function(){
	
	var opn = $(onePageNumPass).val();
	console.log(opn);
	$("#selectPageNum option[value="+opn+"]").attr("selected","true");

	
});
</script>

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

<div class="container">

<u:navbar></u:navbar>

<div class="row table_all">

	<div class="col-2">
	</div>
	
	<div class="col-md-8">
	
	<div class="d-flex justify-content-start">
		<a class="h3" href="${root }/search.do?searchWord=${param.searchWord }">${param.searchWord } 통합검색결과</a>
	</div>
	
	<form action="${root }/search.do" class="table_right mb-2">
		<input type="text" hidden name="searchWord" value="${param.searchWord}"/>
		<input id ="onePageNumPass" hidden value="${param.onePageNum }">
		<select id="selectPageNum" name="onePageNum" onchange="submit()" >
			<option value="10">10개</option>
			<option value="30">30개</option>
			<option value="50">50개</option>
		</select>
	</form>
		
	<table class="table">
		<thead>
			<tr>
				<th scope="col">게시판이름</th>
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
					<td>${a.boardName }</td>
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
	
	<!-- 페이징 -->
	<br />
	<c:if test="${pageFirst >5 }">
		<a href="${root }/search.do?searchWord=${param.searchWord}&pageNum=${pageFirst-1}&onePageNum=${param.onePageNum}">이전</a>
	</c:if>
	
	<c:forEach var="i" begin="${ pageFirst}" end="${pageLast }">
		<a href="${root }/search.do?searchWord=${param.searchWord}&pageNum=${i}&onePageNum=${param.onePageNum}">${i }</a>
	</c:forEach>
	
	<c:if test="${pageLast < pageEnd}">
		<a href="${root }/search.do?searchWord=${param.searchWord}&pageNum=${pageLast+1}&onePageNum=${param.onePageNum}">다음</a>	
	</c:if>
	<br />

	<br />
	</div>
	
	<div class="col-2">
	</div>
	
</div> 
</div>
	
</body>
</html>