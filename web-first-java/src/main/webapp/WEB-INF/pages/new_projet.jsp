<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 5 Transitional//EN"
   "http://www.w3.org/TR/html5/loose.dtd">

<html>
<head>
<link href="<c:url value="/static/css/main.css" />" rel="stylesheet"
	type="text/css" />
<meta charset="ISO-8859-1">
<title>Page de Projets</title>
</head>
<body>
	<h1>Nouveau projet</h1>
	<div class="projet">		
		<spring:url value="/formation/projets/addProjet" var="addUrl"/>			
		<sf:form modelAttribute="projet" action="${addUrl}" method="POST">
			<table>
				<tr>
					<th><label for="name"> <span class="man">*</span>
							<spring:message code="label.projet.name" text="name"/> :
						</label>
					</th>
					<td><sf:input path="name" /> 
					<sf:errors cssClass="error" path="name" /></td>
				</tr>
				
				<tr>
					<th><label for="repoName"> <span class="man">*</span>
							<spring:message code="label.projet.repoName" text="repoName"/> :
						</label>
					</th>
					<td><sf:input path="repoName" /> 
					<sf:errors cssClass="error" path="repoName" /></td>
				</tr>
				
				<tr>
					<th><label for="url"> <span class="url">*</span>
							<spring:message code="label.projet.url" text="url"/> :
						</label>
					</th>
					<td><sf:input path="url" /> 
					<sf:errors cssClass="error" path="url" /></td>
				</tr>
				
				<tr>
					<th><label for="visible"> 
						<spring:message code="label.projet.visible" text="visibilité"/> :
						</label>
				    </th>
					<td>
						<sf:radiobutton path="visible" value="true" /> 
						<spring:message code="label.public" text="Publique" /> 
						<sf:radiobutton path="visible" value="false" /> 
						<spring:message code="label.private" text="Privé" />
					</td>
				</tr>
				
				<tr>
					<td>
						<button id="saveButton" type="submit">
							<spring:message code="command.save" text="Sauvegarder"/>
						</button>
					</td>
					<td><a href="${editUrl}"> <spring:message
								code="command.cancel" text="Annuler" />
					</a></td>
				</tr>
			</table>
		</sf:form>
	</div>
	<br/>
	<a href="<c:url value="/logout" />">Logout</a>
	
	<br/>
	<a href="<c:url value="/index" />">&lt; Retour</a>

</body>
</html>