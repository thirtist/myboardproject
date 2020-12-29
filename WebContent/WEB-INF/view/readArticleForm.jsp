<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="u" tagdir="/WEB-INF/tags/"%>
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
	
	
	$("#btn_mod").click(function() {
		$("#input1").removeAttr("readonly");
		$("#input2").removeAttr("readonly");
		$("#textarea1").removeAttr("readonly");
		$(this).attr("hidden",true);
		$("#btn_submit").attr("hidden",false);
	});
	
	$("#btn_delete").click(function() {
		if (confirm("정말 삭제하시겠습니까 ?")) {
			location.href = "${root}/auth/deleteArticle.do?boardKey=${uar.boardKey }&boardName=${uar.boardName}";
		} else {
			return;
		}
	});
	
 	$("span").click(function() {
		var key = $(this).attr("data-key");
		$(key).removeAttr("hidden");
	});
	
	
});
</script>

<title>Insert title here</title>
</head>
<body>
<div class="container" >
<form action="${root }/auth/readArticle.do?boardKey=${uar.boardKey}" method="post">

		<input type="text" name ="boardName" value="${uar.boardName }" hidden/>
		말머리
		<input id="input1" size="5" name = preTitle type="text" value="${uar.preTitle }" readonly/>	
		제목
		<input id="input2" name = title type="text" value="${uar.title }" readonly/>
		<c:if test="${errors.title }">제목을 입력하세요</c:if>
		<br />
		내용
		<br />
		<textarea id="textarea1" style="resize: none;" name = content rows="10" cols="50" readonly>${uar.content }</textarea>
		<c:if test="${errors.content }">내용을 입력하세요</c:if>
		<br />
		
		<c:if test="${sameUser }">		
			<button id="btn_mod" type="button">수정하기</button>
			<button id="btn_submit" hidden="true">수정</button>
			<button id="btn_delete" type="button">삭제하기</button>
		</c:if>
		
		<c:if test="${NotMatchIdException }">글작성자가 일치하지 않습니다</c:if>

	</form>
	
	<br />
	
	
	<c:forEach var = "rep" items="${replyList }">
		<c:out value ="${rep.user_nickName }" />
		
		<span style="cursor: pointer;" data-key='#r${rep.replyKey }'>
			<c:out value ="${rep.reply }" />
		</span>
		
		<fmt:formatDate value="${rep.regDate }" pattern="MM-dd HH:mm:ss"/>
		<br />
		<div id="r${rep.replyKey }" hidden>
			<form action="${root }/auth/createReply.do?boardKey=${uar.boardKey}" method="post">
			<textarea style="resize: none;" name = reply rows="3" cols="50" ></textarea>
			<br />
			<button>댓글입력</button>
			<c:if test="${param.replyEmpty }">댓글이 비엇습니다</c:if>
			</form>
		</div>
		
	</c:forEach>
		
	
	
	<form action="${root }/auth/createReply.do?boardKey=${uar.boardKey}" method="post">
		
		<textarea style="resize: none;" name = reply rows="3" cols="50" ></textarea>
		<br />
		<button>댓글입력</button>
		<c:if test="${param.replyEmpty }">댓글이 비엇습니다</c:if>
		
	</form>
	
	
	

</div>
</body>
</html>