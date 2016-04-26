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
<link rel="stylesheet" href="resources/css/mainStyleSheet.css" />
<script type="text/javascript"
	src="resources/bower_components/angular/angular.js"></script>
<script src="resources/bower_components/angular-route/angular-route.js"></script>
<script src="resources/bower_components/spring-security-csrf-token-interceptor/src/spring-security-csrf-token-interceptor.js"></script>
<script src="resources/bower_components/jquery/dist/jquery.min.js"></script>
<script
	src="resources/bower_components/bootstrap/dist/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous"></script>
<script src="resources/js/index.js"></script>
<!-- <script src="resources/js/service/create_user_service.js"></script> -->

</head>
<body ng-app="indexModule">
	<header>
		<nav class="navbar navbar-default">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="index"><img alt="logo"
						src="resources/images/logo.png"
						style="height: 100px; weight: 100px;"></a>
				</div>

				<ul class="nav navbar-nav navbar-center">
					<li><a href="#/"><i class="fa fa-home"></i> Home</a></li>
					<li><a href="#allGames"><i class="fa fa-home"></i> All
							Games</a></li>
					<li><a href="#about"><i class="fa fa-shield"></i> Events</a></li>
					<li><a href="#tournaments"><i class="fa fa-comment"></i>
							Tournaments</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#login"><i class="fa fa-home"></i> Login</a></li>
					<!-- <li><a href="#registration"><i class="fa fa-home"></i>
							Registration</a></li> -->
				</ul>
			</div>
		</nav>
	</header>
	<div id="loginView">
		<div ng-view></div>
	</div>
</body>
</html>