<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML5 Transitional//EN"
   "http://www.w3.org/TR/html5/loose.dtd">

<html>
<head>
<meta charset="UTF-8">
<link href="<c:url value='/static/css/main.css' />" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<title>Page de login</title>
</head>
<body>
	<c:url var="loginUrl" value="/login" />

	<section class="vh-100 gradient-custom">
	<div class="container py-5 h-100">
		<form action="${loginUrl}" method="post">
		<div
			class="row d-flex justify-content-center align-items-center h-100">
			<div class="col-12 col-md-8 col-lg-6 col-xl-5">
				<div class="card bg-primary text-white" style="border-radius: 1rem;">
					<div class="card-body p-5 text-center">

						<div class="mb-md-5 mt-md-4 pb-5">

							<h2 class="fw-bold mb-2 text-uppercase">Login</h2>
							<p class="text-white-50 mb-5">Please enter your login and
								password!</p>

							<div class="form-outline form-white mb-4">
								<input type="text" id="typeEmailX" name="username"
									class="form-control form-control-lg" /> <label
									class="form-label" for="typeEmailX">Username</label>
							</div>

							<div class="form-outline form-white mb-4">
								<input type="password" id="typePasswordX" name="password"
									class="form-control form-control-lg" /> <label
									class="form-label" for="typePasswordX">Password</label>
							</div>
							
							<div class="form-check mb-2 form-white ">
								<input type="checkbox" id="rememberMeid" value="true" name="remember-me"
									class="form-check-input" /> <label
									class="form-check-label" for="rememberMeid">Remember me</label>
							</div>
							
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />

							<button class="btn btn-outline-light btn-lg px-5" type="submit">Login</button>							

						</div>

					</div>
				</div>
			</div>
		</div>
		</form>
	</div>
	</section>




















</body>
</html>