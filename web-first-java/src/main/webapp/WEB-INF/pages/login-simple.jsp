<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML5 Transitional//EN"
   "http://www.w3.org/TR/html5/loose.dtd">

<html>
<head>
<meta charset="UTF-8">
<link href="<c:url value='/static/css/main.css' />" rel="stylesheet"
	type="text/css" />
<title>Page de login</title>
</head>
<body>
	<c:url var="loginUrl" value="/login" />
	<form action="${loginUrl}" method="post">
		<label for="login">Username :</label> 
		
		<input type="text" name="username" id="username" /> <br /> <br /> 
		
		<label for="password">Password :</label>
		<input type="password" name="password" id="password" /> <br /> <br />
		 
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input type="submit" value="Login" />

	</form>






















</body>
</html>