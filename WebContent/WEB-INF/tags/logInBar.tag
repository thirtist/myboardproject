<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>

<u:notLogin>
	<div class="container d-flex d-inline justify-content-end">
		<div class="border border-5 p-2" style="font-size:14px">
			<form action="${root }/login.do" method="post">
				<div class="form-group">
					아이디 <input class="form-control" type="text" name="id"
						value="${param.id }" />
					<c:if test="${errors.id }">아이디를 입력하세요</c:if>
					<c:if test="${errors.notFoundId }">아이디를 찾을 수 없습니다</c:if>
				</div>
				<div class="form-group">
					비밀번호 <input class="form-control" type="password" name="password" />
					<c:if test="${errors.password }">패스워드를 입력하세요</c:if>
					<c:if test="${errors.notMatchPassword }">패스워드가 다릅니다</c:if>
				</div>
				<button class="btn btn-primary">로그인</button>
			</form>
			<div class="mt-2">
				<a href="${root }/join.do">회원가입</a> / <a href="${root }/findId.do">아이디찾기</a>
				/<a href="${root }/findPassword.do">패스워드찾기</a>
			</div>
		</div>
	</div>
</u:notLogin>

<u:isLogin>
	<div class="container d-flex d-inline justify-content-end">
		<div class="border border=5 p-2 style="font-size:14px"">
			<c:if test="${!empty authUser }">
				<p>${authUser.nickName }님환영합니다</p>
				<a href="${root }/auth/logout.do">로그아웃</a>
				<a href="${root }/auth/check/changeMemberInfo.do">회원정보수정</a>
				<a href="${root }/auth/check/deleteMember.do">회원탈퇴</a>
				<c:if test="${authUser.id == 'admin' }">
					<br />
					<a href="${root }/admin/boardControl.do">게시판관리</a>
				</c:if>
			</c:if>
		</div>
	</div>
</u:isLogin>

