<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html>
<html ng-app="homeApp">
<head>
<meta charset="utf-8">

<!-- CSS -->
<link rel="stylesheet"
	href="resources/bower_components/bootstrap/dist/css/bootstrap.min.css" />
<link href="resources/bower_components/bootstrap/dist/css/bootstrap.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="resources/bower_components/bootstrap/dist/css/bootstrap-theme.min.css"
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
	crossorigin="anonymous">
<link rel="stylesheet" href="resources/css/home.css" />
<link rel="stylesheet" href="resources/css/menu.css" />
<link rel="stylesheet" href="resources/css/search.css" />
<link rel="stylesheet" href="resources/css/users.css" />
<!-- End of CSS -->

<!-- Scripts -->
<script type="text/javascript"
	src="resources/bower_components/angular/angular.js"></script>
<script src="resources/bower_components/angular-route/angular-route.js"></script>
<script src="resources/bower_components/jquery/dist/jquery.min.js"></script>
<script type="text/javascript"
	src="resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="resources/bower_components/angular-ui-bootstrap-bower/ui-bootstrap-tpls.js"></script>
<script type="text/javascript" src="resources/js/home.js"></script>
<script type="text/javascript" src="resources/js/menu.js"></script>
<!-- End of Scripts -->

</head>
<body>
	<!-- Top header -->
	<header>
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand"><img alt="logo"
						src="resources/images/logo.png"
						style="height: 50px; weight: 50px;"></a>
				</div>

				<p style="font-size: 14pt;" class="navbar-text">
					<b>Board Games Crossing Web Project</b>
				</p>

				<form style="margin-left: 15%;" class="navbar-form navbar-left"
					role="search">
					<form id="searchthis" action="/search" style="display: inline;"
						method="get">
						<input id="search-box" name="q" size="50" type="text"
							placeholder="Search" />
						<button id="search-btn" type="submit">
							<span class="	glyphicon glyphicon-search"></span>
						</button>
					</form>
				</form>

				<ul class="nav navbar-nav navbar-right" ng-controller='getAvatar'>
					<li><a class="btn btn-secondary btn-lg disabled">Welcome
							back, <em><b style="color: white">${user}</b></em>
					</a></li>
					<li class="dropdown" style="background-color: transparent;"><a
						href="" class="dropdown-toggle profile-image"
						data-toggle="dropdown"> <img
							style="height: 35px; width: 35px;" ng-src="{{avatar}}"
							class="img-circle dropdown-toggle profile-image"
							data-toggle="dropdown"> <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#edit"><span
									class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
									Edit profile</a></li>
							<li><a href=""><span
									class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
									Messages <span class="badge">2</span></a></li>
							<li><a href="#users"><span class="glyphicon glyphicon-user"
									aria-hidden="true"></span> Users </a></li>
							<li class="divider"></li>
							<li><a href="logout"><span
									class="glyphicon glyphicon-off" aria-hidden="true"></span>
									Logout </a></li>
						</ul></li>
				</ul>
			</div>
		</nav>
	</header>
	<!-- End of Top header -->

	<!-- Menu slider -->
	<div>
		<button class="btn btn-default" id="menu_btn">
			<span class="glyphicon glyphicon-play-circle"></span>
		</button>
		<nav id="main_menu">
			<ul class="lvl1">
				<li class="lvl1"><a href="#allGames"> All Games</a></li>
				<li class="lvl1"><a href="#events"> Events</a></li>
				<li class="lvl1"><a href="#tournaments"> Tournaments</a></li>
				<li class="lvl1"><a href="#statistics"> Statistics</a></li>
			</ul>
		</nav>
	</div>
	<!-- End of Menu slider -->


	<div id="main_container">

		<!-- ng-route div -->
		<div id="main_div" ng-view></div>
		<!-- End of ng-route div -->

		<!-- Sidebar -->
		<div id="sidebar">
			
			<!-- Friends Widget -->
			<div id="friends_div">
				<div class="jumbotron">
					<h1 style="text-align: center">FRIENDS</h1>
				</div>
			</div>
			<!-- End of Friends Widget -->


			<!-- MyGames Widget -->
			<div id="mygames_div">
				<div class="jumbotron">
					<h1 style="text-align: center">MY GAMES</h1>
				</div>
			</div>
			<!-- End of MyGames Widget -->

		</div>
		<!-- end of Sidebar -->

		<div style="clear: both;"></div>

	</div>


	<!-- Footer -->
	<footer id="footer">
		<div class="container">
			<p class="text-muted">
				Copyright <span class="glyphicon glyphicon-copyright-mark"></span>
				2016 | Lv.179-Java | All Rights Reserved
			</p>
		</div>
	</footer>
	<!-- End of Footer -->
	
</body>
</html>