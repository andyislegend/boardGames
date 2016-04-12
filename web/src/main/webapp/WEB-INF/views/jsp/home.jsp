<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<link href="../../resources/css/bootstrap.css" rel="stylesheet" />
<link rel="stylesheet" href="../../resources/css/mainStyleSheet.css" />
<script src="../../resources/js/angular.js"></script>
<script src="../../resources/js/bootstrap.js"></script>
<script src="../../resources/js/gamesApp.js"></script>
</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Cross Games</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="#">Championship</a></li>
				<li><a href="#">All Games</a></li>
				<li><a href="#">Events</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<c:url value="/logout" />"><span
						class="glyphicon glyphicon-log-in"></span> Logout </a></li>
			</ul>


		</div>
	</nav>

	Dear
	<strong>${user}</strong>, Welcome to Home Page.


</body>
</html>