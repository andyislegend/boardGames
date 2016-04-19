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
<script src="resources/bower_components/jquery/dist/jquery.min.js"></script>
<script type="text/javascript"
	src="resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

</head>
<body ng-app="usersGameApp">
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand"><img alt="logo"
					src="resources/images/logo.png" style="height: 75px; weight: 75px;"></a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="tournaments">Championship</a></li>
				<li><a href="#">All Games</a></li>
				<li><a href="#">Events</a></li>
			</ul>

			<ul class="nav navbar-nav navbar-left">
				<form class="navbar-form navbar-center" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Find Game">
					</div>
					<button type="submit" class="btn btn-success">Search</button>
				</form>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a class="btn btn-secondary btn-lg disabled">Welcome
						back, <em><b style="color: white">${user}</b></em>
				</a></li>
				<li class="dropdown" style="background-color: transparent;"><a href="#"
					class="dropdown-toggle profile-image" data-toggle="dropdown"> <img
						style="height: 35px; width: 35px;"
						src="/resources/images/test_avatar.jpeg"
						class="img-circle dropdown-toggle profile-image"
						data-toggle="dropdown"> <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#"><span class="glyphicon glyphicon-pencil"
								aria-hidden="true"></span> Edit profile</a></li>
						<li><a href="#"><span
								class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
								Messages <span class="badge">2</span></a></li>
						<!-- <li class="dropdown-header">Already leaving?</li> -->	
						<li class="divider"></li>	
						<li><a href="logout"><span
								class="glyphicon glyphicon-off" aria-hidden="true"></span>
								Logout </a></li>
					</ul></li>
			</ul>

		</div>
	</nav>


	<div style="margin-top: 6%;" class="container-fluid">
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
							<th>Min/Max players</th>
							<th>Rating</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="x in gamesGlobal">
							<td>{{ x.name }}</td>
							<td>{{ x.categoryName }}</td>
							<td>{{ x.minPlayers }}/{{ x.maxPlayers }}</td>
							<td>{{ x.rating }}</td>
							<td><button class="btn btn-success">Actions</button></td>
						</tr>
					</tbody>
				</table>
			</div>


			<div class="col-sm-6" ng-controller="showAllTournaments">
				<table class="table">
					<thead>
						<tr>
							<th>Tournament name</th>
							<th>User creator</th>
							<th>Users Guests</th>
							<th>Join</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="x in tournaments">
							<td>{{ x.tournamentName }}</td>
							<td>{{ x.userName }}</td>
							<td>{{ x.userGuests}}</td>
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
								<td>{{ friend.firstName }}</td>
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
			<div class="col-sm-4">
				<input type="text" class="form-control"
					ng-model="searchText.lastName" placeholder="Search by last name">
			</div>
			<div class="col-sm-4">
				<input type="text" class="form-control"
					ng-model="searchText.address.city" placeholder="Search by city">
			</div>
			<table class="table" ng-controller="getAllUsersCtrl">
				<tr>
					<th>Last Name, First Name</th>
					<th>Email</th>
					<th>PhoneNumber</th>
					<th>Address</th>
				</tr>
				<tr ng-repeat="user in users|filter:searchText">
					<td>{{user.lastName}} {{user.firstName}}</td>
					<td>{{user.email}}</td>
					<td>{{user.phoneNumber}}</td>
					<td>{{user.address.country}},{{user.address.city}},
						{{user.address.street}}, {{user.address.houseNumber}},
						{{user.address.roomNumber}}</td>
				</tr>
			</table>
		</div>
	</div>
	<footer class="panel-footer"> Copyright (C) 2016 Softserve
		inc, Lv-179.Java. All rights reserved. </footer>
</body>
</html>