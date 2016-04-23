<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script type="text/javascript"
	src="resources/bower_components/angular/angular.js"></script>
<link rel="stylesheet"
	href="resources/bower_components/bootstrap/dist/css/bootstrap.min.css" />
<link href="resources/bower_components/bootstrap/dist/css/bootstrap.css"
	rel="stylesheet" />
<link rel="stylesheet" href="resources/css/mainStyleSheet.css" />
<script type="text/javascript"
	src="resources/bower_components/angular/angular.js"></script>
<script src="resources/bower_components/jquery/dist/jquery.min.js"></script>
<script type="text/javascript"
	src="resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="resources/bower_components/angular-ui-bootstrap-bower/ui-bootstrap-tpls.js"></script>
<script type="text/javascript" src="resources/js/main.js"></script>

</head>
<body ng-app="usersGameApp">
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand"><img alt="logo"
					src="resources/images/logo.png" style="height: 75px; weight: 75px;"></a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href=""
					ng-click="hideTournaments = false; hideAllGames = true">Championship</a></li>
				<li><a href=""
					ng-click="hideTournaments = true; hideAllGames = false">All
						Games</a></li>
				<li><a href="#">Events</a></li>
				<li><a href="<c:url value="uploadJSP"/>">Foto Uploader</a></li>
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
				<li class="dropdown" style="background-color: transparent;"><a
					href="#" class="dropdown-toggle profile-image"
					data-toggle="dropdown"> <img style="height: 35px; width: 35px;"
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

	<div class="container-fluid">
		<div class="row" style="margin-top: 10%;">
			<div class="col-sm-3">
				<div class="well col-md-offset-0">
					<h3 class="text-center">My Games</h3>

					<div ng-controller="CreateGameCtrl">
						<button ng-click="showForm()">Add new Game</button>
						<form data-ng-submit=submit() role="form" ng-show="showText">
							<div class="form-group">
								<input class="form-control" data-ng-model="name"
									placeholder="Name">
							</div>
							<div class="form-group">
								<label>Select Category:</label> <select class="form-control"
									id="exampleSelect1" data-ng-model="category">
									<option ng-repeat="category in categories"
										value="{{category.id}}">{{category.name}}</option>
								</select>
							</div>
							<div class="form-group">
								<input class="form-control" data-ng-model="description"
									placeholder="Description"> <input class="form-control"
									data-ng-model="rules" placeholder="Rules"> <input
									class="form-control" data-ng-model="maxPlayers"
									placeholder="Max Players"> <input class="form-control"
									data-ng-model="minPlayers" placeholder="Min Players"> <input
									class="form-control" data-ng-model="edition"
									placeholder="Edition"> <input class="form-control"
									data-ng-model="year" placeholder="Year">
							</div>
							<button type="submit" value="add" class="btn btn-primary" />

						</form>
					</div>
					<div ng-controller="allUsersGameCtrl">
						<div>
							<table class="table">
								<tr>
									<th>Name</th>
									<th>Category</th>
									<th>More</th>
									<th>Comment</th>
								<tr>
								<tr ng-repeat="game in allGame">
									<td>{{game.name}}</td>
									<td>{{game.category}}</td>
									<td>
										<button ng-click="myFunc(game.id)">M</button>
									</td>
									<td>com</td>
								</tr>
							</table>
							<div ng-show="showMe">
								<div ng-repeat="game in games">
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

			<%----------------------------All Game info table -------------------------------------------------%>
			<div class="col-sm-6" ng-controller="getGamesGlobalController">
				<table class="table">
					<thead>
						<tr>
							<th>Game</th>
							<th>Category</th>
							<th>Min/Max players</th>
							<th>Rating</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="x in gamesGlobal"
							ng-click="gameSelect(x.id, x.name)"
							ng-init="gameDetailsShown=false">
							<td>{{ x.name }}</td>
							<td>{{ x.categoryName }}</td>
							<td>{{ x.minPlayers }}/{{ x.maxPlayers }}</td>
							<td>{{ x.rating }}</td>
						</tr>
						<tr>
						</tr>
					</tbody>
				</table>
				<div class="well" ng-show="gameDetailsShown"
					ng-controller="getGameDetailedInfoController">
					<h4>Name</h4>
					<p>{{gameDetail.name}}</p>
					<hr />
					<h4>Description</h4>
					<pre>{{gameDetail.description}}</pre>
					<hr />
					<h4>Rules</h4>
					<pre>{{gameRules.rules}}</pre>
					<hr />
					<h4>Rating</h4>
					<table class="table">
						<thead>
							<tr>
								<th>Rate this game</th>
								<th>Your rate</th>
								<th>General</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><input type="range" id="myRange" value="100"
									ng-change="ratingSliderChanged()" ng-model="gameRating" /></td>
								<td>
									<p class="text-primary">{{gameRatingText}}</p>
								</td>
								<td>
									<p>{{gameRating}}</p>
								</td>
							</tr>
						</tbody>
					</table>
					<button class="btn btn-success" ng-click="ratingSaved()">Save
						rate</button>
					<hr />
					<h4>Owners</h4>
					<table class="table">
						<thead>
							<tr>
								<th>Name</th>
								<th>Game edition</th>
								<th>Year of production</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="x in userGamesOfGame">
								<td>{{ x.user.username }}</td>
								<td>{{ x.edition }}</td>
								<td>{{ x.yearOfProduction }}</td>
								<td><button class="btn btn-success">Action</button></td>
							</tr>
						</tbody>
					</table>
					<hr />
					<button ng-click="hideGameDetails()" class="btn btn-success">Hide</button>
				</div>
				
				<div class="container-fluid">
					<div class="col-sm-6" ng-controller="getAllUsersCtrl">
						<div class="col-sm-4">
							<input type="text" class="form-control"
								ng-model="searchText.lastName" placeholder="Search by last name">
						</div>
					</div>

					<div class="col-sm-6">

						<div class="col-sm-4">
							<input type="text" class="form-control"
								ng-model="searchText.address.city" placeholder="Search by city">
						</div>
						<table class="table">
							<tr>
								<th>Last Name, First Name</th>
								<th>Email</th>
								<th>PhoneNumber</th>
								<th>Address</th>
							</tr>
							<tr ng-repeat="user in users|filter:searchText">
								<td><a href="" ng-click="getInfoAboutUserFunc(user.id)">
										{{user.lastName}} {{user.firstName}}</a></td>
								<td>{{user.email}}</td>
								<td>{{user.phoneNumber}}</td>
								<td>{{user.address.country}},{{user.address.city}},
									{{user.address.street}}, {{user.address.houseNumber}},
									{{user.address.roomNumber}}</td>
							</tr>
						</table>
						<div ng-show="showUser" class="col-sm-12"
							id="backgroundForOneUser">
							<div class="col-sm-4" id="backgroundForOneUser">
								<p>Last Name:{{oneUser.lastName}}</p>
								<p>First Name:{{oneUser.firstName}}</p>
								<p>Username:{{oneUser.username}}</p>
								<p>Sex:{{oneUser.sex}}</p>
								<p>Age:{{oneUser.age}}</p>
								<p>Rating:{{oneUser.rating}}</p>
							</div>

							<div class="col-sm-4" id="backgroundForOneUser"
								ng-controller="getAllUsersGames">
								<a href="" ng-click="getInfoAboutUserGames(oneUser.username)">
									Games user owns</a>
								<ul ng-show="showUsersGames" ng-repeat="game in games">
									<li><a href="" ng-click="getInfoAboutGame(game.id)">
											{{game.name}}</a></li>
								</ul>
							</div>
							<div class="col-sm-4" id="backgroundForOneUser"
								ng-controller="getAllUsersTournaments">
								<a href=""
									ng-click="getInfoAboutUsersTournaments(oneUser.username)">
									Users tournaments</a>
								<ul ng-show="showUsersTournaments"
									ng-repeat="tournament in tournaments">
									<li><a href=""
										ng-click="getInfoAboutTournament(tournament.id)">
											{{tournament.name}}</a></li>
								</ul>
							</div>
						</div>
						<modal visible="showModal"></modal>
					</div>
				</div>
				<table class="table" ng-controller="showAllTournaments"
					ng-hide="hideTournaments">
					<thead>
						<tr>
							<th>Tournament name</th>
							<th>User creator</th>
							<th>Adress</th>
							<th>Date</th>
							<th>Required rating</th>
							<th>Users Guests</th>
							<th>Join</th>
							<th ng-controller="AddTournament"><a href=""
								ng-click="addTournament()"> <img
									src="resources/ico/add_tournament.png"></a> <script
									type="text/ng-template" id="AddTournament.html">
                            <div class="modal-header">
                                <h3 class="modal-title">Whant to cteate new tournament?</h3>
                            </div>
                            <div class="modal-body">
                                <div>
                                    <form>
                                        <p>Enter a special name of your tournament</p>
                                        <input class="form-control" data-ng-model="inputTournamentName" placeholder="Some Awsome Name">
                                        <br/>
                                        <p>Choose game for your awsome tournament</p>
                                        <select class="form-control" id="inputselectGame" data-ng-model="category">
                                            <option ng-repeat="game in allGame" value="{{game.name}}">{{game.name}}
                                            </option>
                                        </select>
                                        <br/>
                                        <p>Input required rating for all who wants to join to your awsome tournament</p>
                                        <input class="form-control" data-ng-model="inputTournamentRating" placeholder="5.0">
                                        <br/>
                                        <p>Choose the date for your awsome tournamnent</p>
                                        <input type="date" data-ng-model="inputTournamentDate">
                                        <br/>
                                        <br/>
                                        <p>Enter place of your tournament:</p>
                                        <p>Country</p>
                                        <input class="form-control" data-ng-model="inputTournamentCountry" placeholder="country">
                                        <br/>
                                        <p>City</p>
                                        <input class="form-control" data-ng-model="inputTournamentCountry" placeholder="city">
                                        <br/>
                                        <p>Street</p>
                                        <input class="form-control" data-ng-model="inputTournamentCountry" placeholder="street">
                                        <br/>
                                        <p>Number of building</p>
                                        <input class="form-control" data-ng-model="inputTournamentCountry" placeholder="building">
                                        <br/>
                                        <p>Number of apartment</p>
                                        <input class="form-control" data-ng-model="inputTournamentCountry" placeholder="apartment">
                                        <br/>
                                        <br/>
                                        <button ng-click="">Create</button>
                                    </form>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <h3>This is footer</h3>
                            </div>
                        </script></th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="x in tournaments">
							<td>{{ x.tournamentName }}</td>
							<td>{{ x.username }}</td>
							<td>{{x.country}}, {{x.city}}, <br /> {{x.street}},
								{{x.houseNumber}}/{{x.roomNumber}}
							</td>
							<td>{{x.date}}</td>
							<td>{{x.requiredRating}}</td>
							<td>
								<table>
									<tr ng-repeat="y in x.userGuests">
										<td>{{y}}</td>
									</tr>
								</table>
							</td>
							<td>
								<button class="btn btn-success"
									ng-click="JoinTournament(x.tournamentId)">Join</button>
							</td>
						</tr>
					</tbody>
				</table>

			</div>

			<!------------------------ Start Frinds ------------------------------>
			<div class="col-sm-3" ng-controller="listOfFriendsCtrl">
				<div class="well col-md-offset-0">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Friends</th>
								<td><a href="#"><img src="resources/ico/add_user.png"
										width="25"></a></td>

								<td>
									<div ng-controller="countOfOffering" class="count">{{count}}</div>
									<div ng-controller="OfferToFriendCtrl">
										<script type="text/ng-template" id="OfferingForm.html">
                                    <div class="modal-header">
                                        <h3 class="modal-title">This users want to be tour friend</h3>
                                    </div>
                                    <div class="modal-body">
                                        <div>Hello Everyone</div>
                                    </div>
                                    <div class="modal-footer">
                                        <h3>This is footer</h3>
                                    </div>
                                </script>
										<a href="#" ng-click="open()"><img
											src="resources/ico/bell.png" width="25"></a>
									</div>
								</td>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="friend in friends">
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

	</div>
	<!----------------------- End of users ------------------------------>
	<!----------------------- Start of Events ------------------------------>
	<div ng-controller="eventListCtrl">
		<header class="section-header" ng-controller="eventListCtrl">
			<h2 class="title">COMING EVENTS:{{events.length}}</h2>

		</header>

		<div class=" jcarousel-skin-tango">
			<div class="jcarousel-container jcarousel-container-horizontal"
				style="display: block;">
				<li class="jcarousel-item jcarousel-item-horizontal "><a
					ng-repeat="event in events" href="#" class="item">
						<div id={{event.id}} style="display: none;">
							<img src={{event.imgsrc}} width="64" height="64" class="loader">
						</div> <img width="245" height="245"
						onLoad="$({{event.id}}).hide(); $(this).show();"
						src={{event.imgsrc}} alt={{event.name}}> <span
						class="game_name">{{event.game}}</span>

						<div class="name" style="top: 201px;">
							<div class="num">
								{{event.datenum}}<span>{{event.datemonth}}</span>
							</div>
							<div class="tema">{{event.name}}</div>


						</div>
				</a></li>
			</div>
		</div>
	</div>
	<!----------------------- End of Events ------------------------------>

	<footer class="panel-footer"> Copyright (C) 2016 Softserveinc,
		Lv-179.Java. All rights reserved. </footer>
</body>
</html>