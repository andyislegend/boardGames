<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration Form</title>
<link rel="stylesheet"
	href="resources/bower_components/bootstrap/dist/css/bootstrap.min.css"
	crossorigin="anonymous" />
<link href="resources/bower_components/bootstrap/dist/css/bootstrap.css"
	rel="stylesheet" crossorigin="anonymous" />
<link rel="stylesheet"
	href="resources/bower_components/bootstrap/dist/css/bootstrap-theme.min.css"
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
	crossorigin="anonymous">
<link rel="stylesheet" href="resources/css/new-css.css" />
<link rel="stylesheet" href="resources/css/fonts.css" />

</head>

<body>

	<header>
		<nav class="navbar navbar-default">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand"><img alt="logo"
						src="resources/images/logo.png"
						style="height: 100px; weight: 100px;"></a> <a
						class="navbar-brand">Cross Game: The Exchange Web Project</a>
				</div>

				<ul class="nav navbar-nav navbar-right">
					<li><a href="/"><i class="fa fa-home"></i> Home</a></li>
				</ul>

			</div>
		</nav>
	</header>

	<div style="margin-top: 2%;"
		class="generic-container col-md-8 col-md-offset-2">
		<div class="panel panel-default">
			<div class="panel-heading">
				<p style="text-align: center;">
					WELCOME, NEW USER!<br> PLEASE, FILL THE FORM BELOW TO GET
					STARTED
				</p>
			</div>

			<form:form method="POST" modelAttribute="user" enctype="multipart/form-data"
				class="form-horizontal">
				<form:input type="hidden" path="id" id="id" />

				<div class="row" style="margin-top: 1%;">
					<div class="form-group col-md-12" style="margin-left: 1%;">
						<label class="col-md-3 control-lable" for="username">Username</label>
						<div class="col-md-7">
							<form:input type="text" path="username" id="username"
								class="form-control input-sm" required="required" />
							<div class="has-error">
								<form:errors path="username" class="label label-warning" />
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-12" style="margin-left: 1%;">
						<label class="col-md-3 control-lable" for="firstName">First
							Name</label>
						<div class="col-md-7">
							<form:input type="text" path="firstName" id="firstName"
								class="form-control input-sm" />
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-12" style="margin-left: 1%;">
						<label class="col-md-3 control-lable" for="lastName">Last
							Name</label>
						<div class="col-md-7">
							<form:input type="text" path="lastName" id="lastName"
								class="form-control input-sm" />
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-12" style="margin-left: 1%;">
						<label class="col-md-3 control-lable" for="password">Password</label>
						<div class="col-md-7">
							<form:input type="password" path="password" id="password"
								class="form-control input-sm" required="required" />
							<div class="has-error">
								<form:errors path="password" class="label label-danger" />
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-12" style="margin-left: 1%;">
						<label class="col-md-3 control-lable" for="password">Confirm
							Password</label>
						<div class="col-md-7">
							<input type="password" name="confirmPassword"
								id="confirmPassword" class="form-control input-sm"
								required="required" />
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-12" style="margin-left: 1%;">
						<label class="col-md-3 control-lable" for="sex">Gender</label>
						<div class="col-md-7">
							<select class="form-control" id="sex" name="sex" required>
								<option value="male">Male</option>
								<option value="female">Female</option>
							</select>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-12" style="margin-left: 1%;">
						<label class="col-md-3 control-lable" for="age">Age</label>
						<div class="col-md-7">
							<form:input type="text" path="age" id="age"
								class="form-control input-sm" />
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-12" style="margin-left: 1%;">
						<label class="col-md-3 control-lable" for="email">Email</label>
						<div class="col-md-7">
							<form:input type="text" path="email" id="email"
								class="form-control input-sm" required="required" />
							<div class="has-error">
								<form:errors path="email" class="label label-danger" />
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-12" style="margin-left: 1%;">
						<label class="col-md-3 control-lable" for="phoneNumber">Phone
							Number</label>
						<div class="col-md-7">
							<form:input type="text" path="phoneNumber" id="phoneNumber"
								class="form-control input-sm" />
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="form-group col-md-12" style="margin-left: 1%;">
						<div class="col-md-7">
							 Choose foto to upload : <input type="file" name="fileUpload"
                              class="fileUpload">
						</div>
					</div>
				</div>

				<div class="row">
					<div style="margin-left: 2%;" class="form-actions floatRight">
						<input type="submit" value="Register"
							class="btn btn-primary btn-bg" /> or <a href="index">Cancel</a>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>