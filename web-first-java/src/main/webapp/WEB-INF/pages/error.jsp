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
	<a href="<c:url value="/logout" />">Logout</a>
	<br />
	<br />
	<a href="<c:url value='/index' />">&lt;&lt; Retour</a>

</body>
</html>
