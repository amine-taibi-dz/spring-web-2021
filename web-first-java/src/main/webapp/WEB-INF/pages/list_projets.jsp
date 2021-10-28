<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link href="<c:url value="/static/css/main.css" />" rel="stylesheet"
	type="text/css" />
<meta charset="ISO-8859-1">
<title>Page de Projets</title>
</head>
<body>
	<sec:authentication var="user" property="principal" />
	<h1>Bonjour ${user.username}</h1>
	<h1>
		<spring:message code="label.list.projects" text="'Liste des projet'" >
			<spring:argument  value="  "/>
		</spring:message>
	</h1>

	<table>
		<!-- here should go some titles... -->
		<tr>
			<th>Id</th>
			<th>Nom</th>
			<th>Répository</th>
			<th>URL</th>
			<th>Visible</th>
		</tr>
		<c:forEach items="${projets}" var="prj">
			<tr>
				<td><c:out value="${prj.id}" /></td>
				<td><c:out value="${prj.name}" /></td>
				<td><c:out value="${prj.repoName}" /></td>
				<td><c:out value="${prj.url}" /></td>
				<td><c:out value="${prj.visible?'Oui':'Non'}" /></td>
			</tr>
		</c:forEach>
	</table>
	<br/><br/>
	<a href="<c:url value="/logout" />">Logout</a>
	<br/><br/>
	<a href="<c:url value="/index" />">&lt;&lt; Retour</a>

</body>
</html>