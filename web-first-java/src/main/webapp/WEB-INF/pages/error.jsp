<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta charset="UTF-8" content="UTF-8">
 <link rel="stylesheet" href="<spring:theme code='styleSheet'/>" type="text/css"/>
<title>Error Page</title>
</head>
<body>
	<h1>Error Page</h1>
	<p>Application has encountered an error. Please contact support on
		...</p>

	Failed URL: ${url} Exception: ${exception.message}
	<c:forEach items="${exception.stackTrace}" var="ste">    
	 ${ste} 
    </c:forEach>

	<br />
	<br />
	<a href="<c:url value="/logout" />"><spring:message code="welcome.label.logout"/></a>
	<br/><br/>
	<a href="<c:url value='/index' />">&lt;&lt; <spring:message code="welcome.label.retour"/></a>
	<br/>
	<a href="<c:url value='/index?lang=en' />"> <spring:message code="label.en"/></a>
	-
	<a href="<c:url value='/index?lang=fr' />"> <spring:message code="label.fr"/></a>
</body>
</html>
