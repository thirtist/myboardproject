<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty sessionScope.authUser and sessionScope.authUser.id eq 'admin' }">
	<jsp:doBody></jsp:doBody>
</c:if>	
