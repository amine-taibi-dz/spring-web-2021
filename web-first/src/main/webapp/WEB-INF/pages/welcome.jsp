<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="ISO-8859-1">
<link href="<c:url value="/static/css/main.css" />" rel="stylesheet" type="text/css" />
<title>Page d'index</title>
</head>
<body>
	<sec:authentication  var="user" property="principal" />
	<h1>Bonjour ${user.username}</h1>
	<sec:authorize access="hasRole('USER')">
		<h3>Aller à la page session <a href="formation/session?nb=1001">Page de session de formation</a></h3>
		
		<br/>
	</sec:authorize>
	
	
	<sec:authorize access="hasRole('ADMIN')">
		<h3>Aller à la liste des projets .. <a href="formation/projets">Liste des projets</a></h3>
		
		<br/>
		
		<br/>
		<h3>Ajouter un nouveau projet .. <a href="formation/projets/addProjet">Ajouter un projet</a></h3>
		
	
	</sec:authorize> 
	<br/><br/>
	<a href="<c:url value="/logout" />">Logout</a>
	<br/><br/>
	<a href="<c:url value='/index' />">&lt;&lt; Retour</a>

</body>
</html>