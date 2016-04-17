<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>
<link
	href="<c:url value='/resources/bower_components/bootstrap/dist/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/resources/css/login.css' />" rel="stylesheet"></link>
<link rel="stylesheet" type="text/css"
	href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
</head>

<body>
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
							<label class="input-group-addon" for="username"><i
								class="fa fa-user"></i></label> <input type="text" class="form-control"
								id="username" name="username" placeholder="Enter Username"
								required>
						</div>
						<div class="input-group input-sm">
							<label class="input-group-addon" for="password"><i
								class="fa fa-lock"></i></label> <input type="password"
								class="form-control" id="password" name="password"
								placeholder="Enter Password" required>
						</div>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />

						<div class="form-actions">
							<input type="submit"
								class="btn btn-block btn-primary btn-default" value="Log in">
						</div>
						<br>

					</form>
					<form action="newuser">
						<h6 class="register-suggestion">Wanna play but still don't
							have an account? Sign up now.</h6>
						<button class="btn btn-block btn-primary btn-default"
							type="submit">Sign in</button>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>