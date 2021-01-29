<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>

<div class="container mb-3" >	
  <form class="form-inline mt-2 ml-3 justify-content-center" action="${root }/search.do">
	<div class="navbar rounded-circle text-light bg-primary mr-5 h3 p-3">JAinside</div>
      <input name="searchWord" class="form-control mx-sm-4" size="50px" type="search" placeholder="게시판통합검색" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">검색</button>
  </form>

 <nav class="navbar navbar-expand-lg navbar-dark bg-primary row">
  <div class="col-1">
	  <a class="navbar-brand" href="${root }">HOME</a>
  </div>
  
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse col row" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto container">
      <li class="nav-item col-8">
        <a class="nav-link" href="${root }/readBoardList.do">게시판리스트</a>
      </li>
      
      <li class="nav-item">
      <u:isAdmin>
        <a class="nav-link" href="${root }/admin/boardControl.do">게시판관리</a>
      </u:isAdmin>
      </li>
      <u:isLogin>
      <li class="nav-item">
        <a class="nav-link" href="${root }/auth/check/changeMemberInfo.do">회원정보수정</a>
      </li>
      <li class="nav-item">    
      	<a class="nav-link" href="${root }/auth/logout.do">로그아웃</a>
      </li>
      </u:isLogin>      
      
    </ul>
  </div>
  
 </nav>
</div>