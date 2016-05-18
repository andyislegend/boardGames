<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="resources/bower_components/bootstrap/dist/css/bootstrap.min.css"
	crossorigin="anonymous" />
<link href="resources/bower_components/bootstrap/dist/css/bootstrap.css"
	rel="stylesheet" crossorigin="anonymous" />
<link rel="stylesheet"
	href="resources/bower_components/bootstrap/dist/css/bootstrap-theme.min.css"
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
	crossorigin="anonymous">

<link rel="stylesheet" href="resources/css/index.css" />
<link rel="stylesheet" href="resources/css/fonts.css" />

<title>REGISTARTION CONFIRMATION</title>
</head>
<body>
	<header>
		<nav class="navbar navbar-default">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="index"><img alt="logo"
						src="resources/images/logo.png"
						style="height: 100px; weight: 100px;"></a>
				</div>

				<p style="font-size: 14pt;" class="navbar-text">
					<b>Board Games: Exchange</b>
				</p>

			</div>
		</nav>
	</header>

	<div class="col-md-8 col-md-offset-2">
		<div>
			<c:if test="${success}">
				<h1 style="text-align: center;">${message}</h1>
				<br />
			</c:if>

		</div>

		<div>
			<c:if test="${expired}">
				<h1 style="text-align: center;" class="alert alert-danger">${message}</h1>
				<br />
			</c:if>

			<form action="index">
				<button class="btn btn-info btn-lg btn-block" type="submit">Proceed to login page</button>
			</form>
			
		</div>

	</div>
</body>
</html>