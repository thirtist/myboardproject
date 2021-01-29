<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags/"%>


<style type="text/css">
 .table_all {
 	font-size:6px;
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

	<div class="table_all">	
		<h3>최근등록글</h3>
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
	</div>