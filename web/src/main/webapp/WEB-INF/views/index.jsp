<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet"
	href="resources/bower_components/bootstrap/dist/css/bootstrap.min.css"
	crossorigin="anonymous" />
<link href="resources/bower_components/bootstrap/dist/css/bootstrap.css"
	rel="stylesheet" crossorigin="anonymous" />
<link
	href="resources/bower_components/bootstrap/dist/css/bootstrap-theme.min.css"
	rel="stylesheet" crossorigin="anonymous" />
<link rel="stylesheet" href="resources/css/mainStyleSheet.css" />
<link rel="stylesheet" href="resources/css/main.css" />

<script type="text/javascript"
	src="resources/bower_components/angular/angular.js"></script>
<script src="resources/bower_components/jquery/dist/jquery.min.js"></script>
<script type="text/javascript"
	src="resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="index"><img alt="logo" src="resources/images/logo.png" style="height: 100px; weight:100px;"></a>
			</div>
			<ul class="nav navbar-nav navbar-center">
				<li><a href="#">All Games</a></li>
				<li><a href="#">Events</a></li>
				<li><a href="#">Championship</a></li>
			</ul>

			<ul class="nav navbar-nav navbar-right ">
				<li class="dropdown" id="menuLogin"><a class="dropdown-toggle"
					href="#" data-toggle="dropdown" id="navLogin" data-devgib="tagged">Sign in</a>
					<div class="dropdown-menu" style="padding: 7pt; width: 185pt;">
						<div id="mainWrapper">
							<div class="login-container">
								<div class="login-card">
									<c:if test="${not empty error}">
										<div class="error">${error}</div>
									</c:if>
									<div class="login-form">
										<c:url var="j_spring_security_check" value="/login" />
										<form action="<c:url value='j_spring_security_check' />"
											method="post" class="form-horizontal">
											<c:if test="${param.error != null}">
												<div class="alert alert-danger">
													<p>Invalid username and password.</p>
												</div>
											</c:if>
											<c:if test="${param.logout != null}">
												<div class="alert alert-success">
													<p>You have been logged out successfully.</p>
												</div>
											</c:if>
											<c:if test="${success != null}">
												<div class="generic-container">
													<div class="alert alert-success lead">${success}</div>
												</div>
											</c:if>
											<div class="input-group input-sm">
												<label class="input-group-addon" for="username"><span
													class="glyphicon glyphicon-user"></span></label> <input type="text"
													class="form-control" id="username" name="username"
													placeholder="Enter Username" required>
											</div>
											<div class="input-group input-sm">
												<label class="input-group-addon" for="password"><span
													class="glyphicon glyphicon-lock"></span></label> <input
													type="password" class="form-control" id="password"
													name="password" placeholder="Enter Password" required>
											</div>
											<input type="hidden" name="${_csrf.parameterName}"
												value="${_csrf.token}" />

											<div class="form-actions">
												<input type="submit"
													class="btn btn-block btn-primary"
													value="Sign in">
											</div>
										</form>
										<br>
										<form action="newuser">
											<h5 class="register-suggestion" align="center">Wanna play but still
												don't have an account? Sign up now.</h5>
											<button class="btn btn-block btn-primary"
												type="submit"> Sign up</button>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div></li>
				<li><a href="#">Sign up</a></li>
			</ul>

		</div>
	</nav>

	<div>
		<h2 style="color: black; text-align: center;">
			<b>Some awesome information about our project</b>
		</h2>
	</div>
</body>
</html>