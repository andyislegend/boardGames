<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="col-md-3 col-md-offset-5" id="mainWrapper">
	<div class="login-container">
		<div class="login-card">
			<c:if test="${not empty error}">
				<div class="error">${error}</div>
			</c:if>
			<div class="login-form">
				<c:url var="authenticate" value="/login" />
				<form action="<c:url value='authenticate' />"
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
							class="glyphicon glyphicon-lock"></span></label> <input type="password"
							class="form-control" id="password" name="password"
							placeholder="Enter Password" required>
					</div>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />

					<div class="form-actions">
						<input type="submit" class="btn btn-block btn-primary"
							value="Sign in">
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

