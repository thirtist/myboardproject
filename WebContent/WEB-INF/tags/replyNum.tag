<%@tag import="reply.service.ReadReplyService"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name = "boardKey" type="java.lang.Integer" required="true"%>
<%
ReadReplyService rrs = new ReadReplyService();
	int replyNum = rrs.getReplyNum(boardKey);

	if (replyNum==0){
		return;
	}

%>


<%="["+replyNum+"]"%>