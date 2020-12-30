<%@tag import="article.service.RecommandArticleService"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="boardKey" type="java.lang.Integer" required="true"%>
<% 

	RecommandArticleService ras = new RecommandArticleService();
	
		int recommandNum=ras.getRecommandNumber(boardKey);
		request.setAttribute("recommandNum", recommandNum);
%>