<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="pageN" type="java.lang.String" required="true"%>
<%@ attribute name="pageNumI" type="java.lang.String" required="true"%>

<%-- <%
if(pageN == null){
	pageN = "1";
}
%> --%>

<c:if test="${pageN eq pageNumI }">
<jsp:doBody></jsp:doBody>
</c:if>

<c:if test="${pageN != pageNumI }">
[<jsp:doBody></jsp:doBody>]
</c:if>