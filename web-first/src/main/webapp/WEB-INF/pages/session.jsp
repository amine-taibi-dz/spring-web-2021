<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="ISO-8859-1">
<title>Page de session</title>
</head>
<body>
	<sec:authentication var="user" property="principal" />
	<h1>Bonjour ${user.username}</h1>
	<h3>
		Crédit :
		<%=response.getHeader("Number") %>
		points
	</h3>
	<br/>
	<a href="<c:url value="/logout" />">Logout</a>
	<br/>
	<a href="<c:url value="/index" />">&lt; Retour</a>
</body>
</html>