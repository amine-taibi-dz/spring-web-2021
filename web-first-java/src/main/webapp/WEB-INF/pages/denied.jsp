<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8" content="UTF-8">
<title>Page Erreur d'accès</title>
</head>
<body>
	<h1>Erreur d'accès</h1>
	<sec:authentication var="user" property="principal" />
	
	<spring:message code="label.erreur.acces"  text="ajouter le text dans les message.properties !!">
		<spring:argument value="${user.username}"></spring:argument>
	</spring:message>
	<br /><br/>
	<a href="<c:url value="/logout" />">Logout</a>
	<br /><br/>
	<a href="<c:url value='/index' />">&lt;&lt; Retour</a>

</body>
</html>