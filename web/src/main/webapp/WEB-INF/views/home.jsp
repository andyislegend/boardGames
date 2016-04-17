<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="resources/bower_components/bootstrap/dist/css/bootstrap.min.css" />
<link href="resources/bower_components/bootstrap/dist/css/bootstrap.css"
	rel="stylesheet" />
<link rel="stylesheet" href="resources/css/mainStyleSheet.css" />

<script type="text/javascript"
	src="resources/bower_components/angular/angular.js"></script>
<script type="text/javascript"
	src="resources/bower_components/jquery/dist/jquery.js"></script>
<script type="text/javascript"
	src="resources/bower_components/jquery/dist/jquery.min.js"></script>
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
	<div class="container-fluid" ng-app="boardGamesApp">
		<div class="row">
			<div class="col-sm-3" ng-controller="getGamesMyController">
				<div class="well col-md-offset-0">

					<h3 class="text-center">My Games</h3>
					<br />
					<ul class="list-group" ng-repeat="x in gamesMy">
						<li class="list-group-item">{{ x.Name }}</li>
					</ul>

				</div>
			</div>
			<div class="col-sm-6" ng-controller="getGamesGlobalController">
				<table class="table">
					<thead>
						<tr>
							<th>Game</th>
							<th>Category</th>
							<th>Description</th>
							<th>Min players</th>
							<th>Max players</th>
							<th>Rating</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="x in gamesGlobal">
							<td>{{ x.name }}</td>
							<td>{{ x.categoryName }}</td>
							<td>{{ x.description }}</td>
							<td>{{ x.minPlayers }}</td>
							<td>{{ x.maxPlayers }}</td>
							<td>{{ x.rating }}</td>
							<td><button class="btn btn-success">Actions</button></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="col-sm-3" ng-controller="listOfFriendsCtrl">
				<div class="well col-md-offset-0">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Friends</th>
								<td><a href="#"><img src="resources/ico/add_user.png"
										width="25"></a></td>
								<td><a href="#"><img src="resources/ico/bell.png"
										width="25"></a></td>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="friend in friends">
								<td><img src="resources/images/{{friend.img}}.jpg" /></td>
								<td>{{ friend.name }}</td>
								<td>{{ friend.lastName }}</td>
								<td><a href="#"><img src="resources/ico/messages.png"
										width="25"></a></td>
								<td><a href="#"><img src="resources/ico/trophy.png"
										width="25"></a></td>

							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<footer class="panel-footer">vghfg</footer>
	<script type="text/javascript" src="resources/js/gamesApp.js"></script>


</body>
</html>