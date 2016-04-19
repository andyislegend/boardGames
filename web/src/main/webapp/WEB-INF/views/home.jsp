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
            <li><a href="" ng-click="hideTournaments = false; hideAllGames = true">Championship</a></li>
            <li><a href="" ng-click="hideTournaments = true; hideAllGames = false">All Games</a></li>
            <li><a href="">Events</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="<c:url value="/logout" />"><span
                    class="glyphicon glyphicon-log-in"></span> Logout </a></li>
        </ul>
    </div>
</nav>

<<<<<<< HEAD
	Dear
	<strong>${user}</strong>, Welcome to Home Page.
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3">
				<div class="well col-md-offset-0 form-group">
					
					<h3 class="text-center">My Games</h3>
					<div controller = "CreateGameCtrl">
					<button ng-click="showForm()" >Add new Game</button>
					<form data-ng-submit role="form" ng-show = "showText">
						<div class="form-group">
						<label>Game name</label>
						<input data-ng-model = "name">
						<label>Category</label>
						<input data-ng-model = "category">
						<label>Description</label>
						<input data-ng-model = "description">
						<label>Rules</label>
						<input data-ng-model = "rules">
						<label>Max players</label>
						<input data-ng-model = "maxPleyers">
						<label>Min players</label>
						<input data-ng-model = "minPlayers">
                        <label>Edition</label>
						<input data-ng-model = "edition">
                        <label>Year of production</label>
						<input data-ng-model = "year">
                        <input type="submit" value="add">                      
						</div>
						<p ng-repeat = "i in game">{{i.name}}</p>
					</form>
					</div>
					
					<div ng-controller="allUsersGameCtrl">
						<div >
							<table class = "table">
							  <tr><th>Name</th><th>Category </th><th>More</th><th>Comment</th><tr>
							  <tr ng-repeat = "game in allGame">
							<td>{{game.name}}</td><td>{{game.yearOfProduction}}</td>
							<td>
							<button ng-click="myFunc(game.name)">M</button></td>
							<td>com</td>
							</tr>																																										
							</table>						
							<div ng-show="showMe">
							<div ng-repeat = "game in games">
							<p>Category:{{game.category}}</p>
							<p>Year of production: {{game.yearOfProduction}}</p>
							<p>Edition: {{game.edition}}</p>
							<p>Description: {{game.description}}</p>
							<p>Max players: {{game.maxPlayers}}</p>
							<p>Min players: {{game.minPlayers}}</p> 
							</div>
							</div>
						</div>
					</div>
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
=======
Dear
<strong>${user}</strong>, Welcome to Home Page.
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3">
            <div class="well col-md-offset-0">

                <h3 class="text-center">My Games</h3>
                <div ng-controller="allUsersGameCtrl">
                    <div ng-repeat = "game in allGame">
                        <div> <p>{{game.name}}</p><p><button ng-click="myFunc()"><span class="glyphicon glyphicon glyphicon-list-alt" aria-hidden="true"/></button></p></td></div>
                        <div ng-show = "showMe">
                            <p>Year of production: {{game.yearOfProduction}}</p>
                            <p>Category: {{game.category}}</p>
                        </div>
                    </div>
>>>>>>> 4426f40a323ecaf6526bec78006aa9cbe4ac216c

                    <table class = "table">
                        <tr><th>Name</th><th>Category </th><th>More</th><th>Comment</th><tr>
                        <tr ng-repeat = "game in allGame"><td>{{game.name}}</td><td>{{game.category}}</td>
                            <td>

                            <td><span class="glyphicon glyphicon-comment" aria-hidden="true"/></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="col-sm-6" ng-hide="hideAllGames" ng-controller="getGamesGlobalController">
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


<div class="col-sm-6" ng-controller="showAllTournaments" ng-hide="hideTournaments">
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
            <td>
                <table>
                    <tr ng-repeat="y in x.userGuests">
                        <td>{{y}}</td>
                    </tr>
                </table>
            </td>
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