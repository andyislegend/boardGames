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
<link rel="stylesheet" href="resources/css/users.css" />

<script type="text/javascript"
	src="resources/bower_components/angular/angular.js"></script>
<script src="resources/bower_components/jquery/dist/jquery.js"></script>
<script
	src="resources/bower_components/bootstrap/dist/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous"></script>
<script type="text/javascript"
	src="resources/bower_components/angular-ui-bootstrap-bower/ui-bootstrap-tpls.js"></script>
<script src="resources/js/index.js"></script>
<script
	src="resources/bower_components/angular-translate/angular-translate.js"></script>
<script type="text/javascript" src="resources/js/localization.js"></script>
<script
	src="resources/bower_components/angular-translate-storage-cookie/angular-translate-storage-cookie.js"></script>
<script
	src="resources/bower_components/angular-cookies/angular-cookies.js"></script>

<title>REGISTARTION CONFIRMATION</title>
</head>
<body ng-app="indexModule" ng-controller="localizationController">
	<header>
		<nav class="navbar navbar-default">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="index"><img alt="logo"
						src="resources/images/logo.png"
						style="height: 100px; weight: 100px;"></a>
				</div>

				<p style="font-size: 14pt;" class="navbar-text">
					<b translate="PROJECT_NAME"></b>
				</p>

				<ul class="nav navbar-nav navbar-right">

					<li><span class="btn btn-link"> <img id="flag"
							src="resources/images/gb.png" ng-click="changeLanguage('en')" />
							<img id="flag" src="resources/images/ua.png"
							ng-click="changeLanguage('ua')" /></span></li>
				</ul>
			</div>


		</nav>
	</header>

	<div class="col-md-8 col-md-offset-2">
		<div>
			<c:if test="${success}">
				<h1 style="text-align: center;" translate="USERINFO_SUCCESS"></h1>
				<br />
			</c:if>

		</div>

		<div>
			<c:if test="${expired}">
				<h1 style="text-align: center;" class="alert alert-danger"
					translate="USERINFO_FAILURE"></h1>
				<br />
			</c:if>

			<form action="index">
				<button class="btn btn-info btn-lg btn-block" type="submit"
					translate="USERINFO_PROCEED"></button>
			</form>

		</div>

	</div>
</body>
</html>