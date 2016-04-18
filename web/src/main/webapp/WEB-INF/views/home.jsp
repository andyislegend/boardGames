<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<script type="text/javascript" src="resources/js/main.js"></script>

</head>
<body ng-app="usersGameApp">

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
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3">
				<div class="well col-md-offset-0">

					<h3 class="text-center">My Games</h3>
					<br />
					<table class="table" ng-controller="allUsersGameCtrl">
						<tr>
							<th>Name</th>
							<th>Category</th>
						</tr>
						<tr ng-repeat="x in allGame">
							<td>{{ x.name }}</td>
							<td>{{ x.category }}</td>
						</tr>
					</table>
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
	<div class="col-sm-3"></div>
	<div class="container-fluid">
		<div class="col-sm-6">
			<table class="table table-striped" ng-controller="getAllUsersCtrl">
				<tr>
					<th>Last Name, First Name</th>
					<th>Email</th>
					<th>PhoneNumber</th>
					<th>Address</th>
				</tr>
				<tr ng-repeat="user in users">
					<td>{{user.lastName}} {{user.firstName}}</td>
					<td>{{user.email}}</td>
					<td>{{user.phoneNumber}}</td>
					<td>{{user.country}},{{user.city}}, {{user.street}},
						{{user.houseNumber}}, {{user.roomNumber}}</td>
				</tr>
			</table>
			<div class="col-sm-3">
				<form:form method="get" action="getUsersByCityName">
					<p class="findUsers">
						Find users by city: <input type="text" class="form-control"
							name="cityName" data-ng-model="cityName">
						<button type="submit" class="btn btn-success">Search</button>
					</p>
				</form:form>
			</div>
			<div class="col-sm-4">
				<form:form method="get" action="getUsersByLastName">
					<p class="findUsers">
						Find users by Last Name: <input type="text" class="form-control"
							name="lastName">
						<button type="submit" class="btn btn-success">Search</button>
					</p>
				</form:form>
			</div>
		</div>
	</div>
	<footer class="panel-footer">vghfg</footer>
</body>
</html>