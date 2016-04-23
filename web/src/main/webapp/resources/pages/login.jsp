<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="col-md-3 col-md-offset-5" id="mainWrapper">
	<div class="login-container">
		<div class="login-card">

			<div class="login-form">
				<c:url var="authenticate" value="/login" />
				<form action="<c:url value='authenticate' />" method="post"
					class="form-horizontal">

					<div class="input-group input-sm">
						<label class="input-group-addon" for="username"><span
							class="glyphicon glyphicon-user"></span></label> <input type="text"
							class="form-control" id="username" name="username"
							placeholder="Enter Username" required />
					</div>
					<div class="input-group input-sm">
						<label class="input-group-addon" for="password"><span
							class="glyphicon glyphicon-lock"></span></label> <input type="password"
							class="form-control" id="password" name="password"
							placeholder="Enter Password" required />
					</div>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />

					<div class="form-actions">
						<input type="submit" class="btn btn-block btn-primary"
							value="Sign in">
					</div>
				</form>
				<div class="form-actions">
			<form action="#">
			<button  class="btn btn-block btn-primary" type="submit" id="button">Cancel</button>
			</form>	
			</div>
			</div>
		</div>
	</div>
</div>

