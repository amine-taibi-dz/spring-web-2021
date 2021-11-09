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
 <link rel="stylesheet" href="<spring:theme code='styleSheet'/>" type="text/css"/>
<title>Page d'index</title>
</head>
<body >






	<sec:authentication  var="user" property="principal" />
	<h1><spring:message code="welcome.label.message"/> ${user.username}</h1>
	<sec:authorize access="hasRole('USER')">
		<h3><spring:message code="welcome.label.go.session"/> <a href="formation/session?nb=1001"><spring:message code="welcome.label.go.session"/></a></h3>
		
		<br/>
	</sec:authorize>
	
	
	<sec:authorize access="hasRole('ADMIN')">
		<h3><spring:message code="welcome.label.go.list.projets"/> .. <a href="formation/projets"><spring:message code="welcome.label.go.list.projets"/></a></h3>
		
		<br/>
		
		<br/>
		<h3><spring:message code="welcome.label.go.new.projet"/> .. <a href="formation/projets/addProjet"><spring:message code="welcome.label.go.new.projet"/></a></h3>
		
	
	</sec:authorize> 
	<br/><br/>
	<a href="<c:url value="/logout" />"><spring:message code="welcome.label.logout"/></a>
	<br/><br/>
	<a href="<c:url value='/index' />">&lt;&lt; <spring:message code="welcome.label.retour"/></a>
	<br/>
	<a href="<c:url value='/index?lang=en' />"> <spring:message code="label.en"/></a>
	-
	<a href="<c:url value='/index?lang=fr' />"> <spring:message code="label.fr"/></a>

</body>
</html>