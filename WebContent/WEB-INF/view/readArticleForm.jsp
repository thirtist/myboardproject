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

<style>
#btn_recommand {
  background-color: #28AEFF;
  border: none;
  color: white;
  padding: 10px 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 50%;
}

</style>

<title>Insert title here</title>
</head>
<body>

<u:page>

	<div class="d-flex justify-content-start">
		<a class="h3" href="${root }/readArticleList.do?boardName=${uar.boardName }">${uar.boardName } 게시판</a>
	</div>
		<br />

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
		<textarea class="col" id="textarea1" style="resize: none;" name = content rows="10" cols="100%" readonly>${uar.content }</textarea>
		<c:if test="${errors.content }">내용을 입력하세요</c:if>
		<br /><br />
		<div style="text-align:center">
			<u:recommandNum boardKey="${uar.boardKey }"></u:recommandNum>
			<div class="h3">${recommandNum }</div>
			<button id="btn_recommand" type="button"><i class="far fa-star"></i> <br />추천</button>
		</div>
		<div class="d-flex justify-content-end">
			<button id="btn_submit" class="btn btn-secondary ml-1" hidden="true">수정하기</button>
		</div>
		<br />
		
	</form>
	
	<br />
	
	<div class="row align-items-center">
	<c:forEach var = "rep" items="${replyList }">
		<hr>
		<div class="col-2">
			<c:if test="${rep.depth == 1 }">&nbsp;&nbsp;&nbsp;<i class='fas fa-chevron-right'></i></c:if>
			<c:out value ="${rep.user_nickName }" />
		</div>
		
		<div class="col-7">
			<span style="cursor: pointer;" data-key='#r${rep.replyKey }'>
				<c:out value ="${rep.reply }" />
			</span>
		</div>
		
		<div class="col-3 d-flex justify-content-end align-items-center">
			<div class="col-11">
			<fmt:formatDate value="${rep.regDate }" pattern="MM-dd HH:mm:ss"/>
			</div>
			<div class="col-1 d-flex">
			<c:if test="${authUser.id eq rep.user_id }">
				<button class="btn btn-secondary" style="font-size:5px" type="button" onclick="location.href='${root}/auth/deleteReply.do?replyPrimaryKey=${rep.replyPrimaryKey }&boardKey=${uar.boardKey}&pageNum=${param.pageNum}'">X</button>
			</c:if>
			</div>
		</div>
		
		<br />
		
		<!--대댓글입력창  -->
		<div id="r${rep.replyKey }" hidden class="container">
			<form action="${root }/auth/createReply.do?boardKey=${uar.boardKey}&boardName=${uar.boardName}&replyKey=${rep.replyKey}&pageNum=${param.pageNum}" method="post">
			<textarea class="col" id="sub_textarea"style="resize: none;" name = reply2 rows="3" cols="133" ></textarea>
			<br />
			<div class="d-flex justify-content-end">
				<button id="sub_btn" class="btn btn-primary">대댓글입력</button>
			</div>
			</form>
		</div>
		
	</c:forEach>
	</div>
	
	<!-- 리플페이징 -->
	<div class="d-flex justify-content-center">
	<br />
	<c:if test="${pageFirst >5 }">
		<a href="${root }/auth/readArticle.do?boardKey=${boardKey}&pageNum=${pageFirst-1}">이전</a>
	</c:if>
	
	<c:forEach var="i" begin="${ pageFirst}" end="${pageLast }">
		<a href="${root }/auth/readArticle.do?boardKey=${boardKey}&pageNum=${i}">
		<u:pageShape pageN="${pageNum }" pageNumI="${i }">${i }</u:pageShape>
		</a>
	</c:forEach>
	
	<c:if test="${pageLast < pageEnd}">
		<a href="${root }/auth/readArticle.do?boardKey=${boardKey}&pageNum=${pageLast+1}">다음</a>	
	</c:if>
	<br />
	</div>
	<!-- 리플페이징끝 -->

	
	<br />
	<form action="${root }/auth/createReply.do" method="get">
		<input type="text" hidden name="boardKey" value="${uar.boardKey }" />
		<input type="text" hidden name="boardName" value="${uar.boardName }" />
		<textarea class="col" style="resize: none;" name = reply rows="3" cols="100%" ></textarea>
		<br />
		<div class="d-flex justify-content-end ">
			<button class="btn btn-primary">댓글입력</button>
		</div>
		<c:if test="${param.replyEmpty }">댓글이 비었습니다</c:if>
		
	</form>
	
	<br />
	
	
	
	<form action="${root }/auth/writeArticle.do">
	<input type="text" name="boardName" value = "${uar.boardName}"hidden="true"/>
	<div class="d-flex justify-content-end ">
		<c:if test="${sameUser }">		

			<button id="btn_mod" class="btn btn-secondary ml-1" type="button">수정하기</button>
			<button id="btn_delete" class="btn btn-secondary ml-1" type="button">삭제하기</button>
		</c:if>
		<button class="btn btn-primary mx-1">글쓰기</button>
		<c:if test="${NotMatchIdException }"><br />글작성자가 일치하지 않습니다</c:if>		
	</div>
	</form>

</u:page>

</body>
</html>