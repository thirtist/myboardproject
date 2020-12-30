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
	
	$("#btn_recommand").click(function() {
		location.href = "${root}/auth/recommandArticle.do?boardKey=${uar.boardKey }";
	});
	
	
 	$("span").click(function() {
		var key = $(this).attr("data-key");
		boo = $(key).is("[hidden]");
		
		if (boo) {
			$(key).removeAttr("hidden");
		}
		if (!boo) {
			$(key).attr("hidden",true);
		}
	});
	
	$("#sub_btn").click(function() {
		if($("#sub_textarea").val().length<1){
			alert("리플 내용을 입력하세요");
		} else {
			return;
		};
		
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
		<button id="btn_recommand" type="button">추천하기</button>
		추천수 : <u:recommandNum boardKey="${uar.boardKey }"></u:recommandNum> ${recommandNum }
		
		<c:if test="${sameUser }">		
			<button id="btn_mod" type="button">수정하기</button>
			<button id="btn_submit" hidden="true">수정</button>
			<button id="btn_delete" type="button">삭제하기</button>
			<br />
			<br />
		</c:if>
		
		<c:if test="${NotMatchIdException }">글작성자가 일치하지 않습니다</c:if>

	</form>
	
	<br />
	
	
	<c:forEach var = "rep" items="${replyList }">
		<c:if test="${rep.depth == 1 }">&nbsp;&nbsp;&nbsp;<i class='fas fa-chevron-right'></i></c:if>
		<c:out value ="${rep.user_nickName }" />
		
		<span style="cursor: pointer;" data-key='#r${rep.replyKey }'>
			<c:out value ="${rep.reply }" />
		</span>
		
		<fmt:formatDate value="${rep.regDate }" pattern="MM-dd HH:mm:ss"/>
		<c:if test="${authUser.id eq rep.user_id }">
			<button type="button" onclick="location.href='${root}/auth/deleteReply.do?replyPrimaryKey=${rep.replyPrimaryKey }&boardKey=${uar.boardKey}'">삭제</button>
		</c:if>
		<br />
		<!--대댓글입력창  -->
		<div id="r${rep.replyKey }" hidden>
			<form action="${root }/auth/createReply.do?boardKey=${uar.boardKey}&boardName=${uar.boardName}&replyKey=${rep.replyKey}" method="post">
			<textarea id="sub_textarea"style="resize: none;" name = reply2 rows="3" cols="50" ></textarea>
			<br />
			<button id="sub_btn">대댓글입력</button>
			</form>
		</div>
		
	</c:forEach>
		
	<br />
	<br />
	
	<form action="${root }/auth/createReply.do" method="get">
		<input type="text" hidden name="boardKey" value="${uar.boardKey }" />
		<input type="text" hidden name="boardName" value="${uar.boardName }" />
		<textarea style="resize: none;" name = reply rows="3" cols="50" ></textarea>
		<br />
		<button>댓글입력</button>
		<c:if test="${param.replyEmpty }">댓글이 비었습니다</c:if>
		
	</form>
	
	
	

</div>
</body>
</html>