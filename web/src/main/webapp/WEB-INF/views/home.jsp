<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" name="viewport"
	content="width=device-width, initial-scale=1">

<!-- CSS -->
<link rel="stylesheet"
	href="resources/bower_components/normalize-css/normalize.css" />
<link rel="stylesheet"
	href="resources/bower_components/bootstrap/dist/css/bootstrap.min.css" />
<link href="resources/bower_components/bootstrap/dist/css/bootstrap.css"
	rel="stylesheet" />
<link href="resources/bower_components/ng-table/dist/ng-table.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="resources/bower_components/bootstrap/dist/css/bootstrap-theme.min.css"
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
	crossorigin="anonymous">
<link rel="stylesheet" href="resources/css/home.css" />
<link rel="stylesheet" href="resources/css/menu.css" />
<link rel="stylesheet" href="resources/css/search.css" />
<link rel="stylesheet" href="resources/css/users.css" />
<link rel="stylesheet" href="resources/css/friend.css" />
<link rel="stylesheet" href="resources/css/editPage.css" />
<!-- End of CSS -->

<!-- Scripts -->
<script type="text/javascript"
	src="resources/bower_components/angular/angular.js"></script>
<script src="resources/bower_components/angular-route/angular-route.js"></script>
<script src="resources/bower_components/jquery/dist/jquery.js"></script>
<script type="text/javascript"
	src="resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="resources/bower_components/angular-ui-bootstrap-bower/ui-bootstrap-tpls.js"></script>
<script type="text/javascript" src="resources/js/home.js"></script>
<script type="text/javascript"
	src="resources/js/controllers/gamesGlobalController.js"></script>
<script type="text/javascript"
	src="resources/js/controllers/gameSelectController.js"></script>
<script type="text/javascript"
	src="resources/js/controllers/gameDetailsController.js"></script>
<script type="text/javascript"
	src="resources/js/controllers/allUserGamesController.js"></script>
<script type="text/javascript"
	src="resources/js/controllers/globalSearchController.js"></script>
<script type="text/javascript"
	src="resources/js/controllers/createGameController.js"></script>
<script type="text/javascript"
	src="resources/js/controllers/editProfileController.js"></script>
<script type="text/javascript"
	src="resources/js/controllers/getAllUsersController.js"></script>
<script type="text/javascript"
	src="resources/js/controllers/friendsController.js"></script>
<script type="text/javascript"
	src="resources/js/controllers/CreateNewTournamentCtrl.js"></script>
<script type="text/javascript"
	src="resources/js/controllers/showAllTournamentsCtrl.js"></script>
<script type="text/javascript"
	src="resources/js/controllers/eventListCtrl.js"></script>
<script type="text/javascript" src="resources/js/menu.js"></script>
<script type="text/javascript"
	src="resources/bower_components/ng-table/dist/ng-table.min.js"></script>
<script type="text/javascript"
	src="resources/bower_components/ng-q/q.module.js"></script>
	<script src="resources/bower_components/angular-file-model/angular-file-model.js"></script>
<!-- End of Scripts -->

</head>
<body ng-app="homeApp">
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
					<b>Board Games Exchange</b>
				</p>

				<div>
					<div style="margin-left: 48%" ng-controller="search">
						<form data-ng-submit=submit() style="margin-left: 15%;"
							class="navbar-form navbar-left" role="search">
							<form id="searchthis" style="display: inline;" method="get">
								<input id="search-box" name="q" size="50" type="text"
									placeholder="Search" ng-model="search" /> <a
									ng-href="#search/{{search}}">
									<button id="search-btn" ng-click="searchAll()" type="submit">
										<span class="	glyphicon glyphicon-search"></span>
									</button> <!-- <input type="submit"> -->
								</a>
							</form>
						</form>
					</div>
				</div>
				<ul class="nav navbar-nav navbar-right" ng-controller='getAvatar'>
					<li><a class="btn btn-secondary btn-lg disabled">Welcome
							back, <em><b style="color: white">${user}</b></em>
					</a></li>
					<li class="dropdown" style="background-color: transparent;"><a
						href="" class="dropdown-toggle profile-image"
						data-toggle="dropdown"> <img
							style="height: 25px; width: 25px;" ng-src="{{avatar}}"
							class="img-circle dropdown-toggle profile-image"
							data-toggle="dropdown"> <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#edit"><span
									class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
									Edit profile</a></li>
							<li><a href=""><span
									class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
									Messages <span class="badge">2</span></a></li>
							<sec:authorize access="hasRole('ADMIN')">
								<li><a href="#users"><span
										class="glyphicon glyphicon-user" aria-hidden="true"></span>
										Users </a></li>
							</sec:authorize>
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

		<!-- MyGames Widget -->
		<div id="mygames_div">
			<div ng-controller="allUsersGameCtrl">
				<h1 style="text-align: center">MY GAMES:{{allGame.length}}</h1>
				<div>
					<div>
						<div ng-controller="CreateGameCtrl">
							<div>
								<input type="submit" value="ADD GAME"
									class="btn btn-primary btn-sm" data-toggle="modal"
									data-target="#myModalHorizontal">
							</div>
							<div class="modal fade" id="myModalHorizontal" tabindex="-1"
								role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">

										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">
												<span aria-hidden="true">&times;</span> <span
													class="sr-only">Close</span>
											</button>
										</div>
										<div class="modal-body">
											<div class="addGame">
												<form role="form" data-ng-submit=submit()>
													<div class="form-group">
														<label>Name: </label> <input class="form-control"
															data-ng-model="name">
													</div>
													<div class="form-group">
														<label>Select Category:</label> <select
															class="form-control" id="exampleSelect1"
															data-ng-model="category">
															<option ng-repeat="category in categories"
																value="{{category.name}}">{{category.name}}</option>
														</select>
													</div>
													<div class="form-group">
														<label>Description: </label> <input class="form-control"
															data-ng-model="description">
													</div>
													<div>
														<label>Rules :</label> <input class="form-control"
															data-ng-model="rules">
													</div>
													<div class="form-group">
														<label>Max players: </label> <input class="form-control"
															data-ng-model="maxPlayers" type="number">

													</div>
													<div class="form-group">
														<label>Min players: </label> <input class="form-control"
															ng-minlength="0" data-ng-model="minPlayers" type="number">

													</div>
													<div class="form-group">
														<label>Edition</label> <input class="form-control"
															data-ng-model="edition">
													</div>
													<div class="form-group">
														<label>Year of Production: </label> <input
															class="form-control" data-ng-model="year" type="number">
													</div>
													<div>
														<input type="submit" value="ADD"
															style="width: 30%; margin-bottom: 10px"> <input
															type="submit" value="Close" data-dismiss="modal"
															style="width: 30%; margin-bottom: 10px">
													</div>
												</form>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div ng-controller="getGameDetailedInfoController">
							
							<ul class="nav nav-tabs">
  								<li class="active"><a data-toggle="tab" href="#myGames">My</a></li>
  								<li><a data-toggle="tab" href="#sharedGames">Shared</a></li>
  								<li><a data-toggle="tab" href="#borrowedGames">Borrowed</a></li>
							</ul>
							
							<div class="tab-content">
  								<div id="myGames" class="tab-pane fade in active">
    								<table ng-table="" class="table table-condensed table-hover">
										<tr ng-repeat="game in allMyGames">
											<td title="'Name'"><a href="#gameUserDetails/{{game.id}}"
												ng-click="myFunc(game.id)"> {{game.name}}</a></td>
											<td title="'Category'">{{game.category}}</td>
											<td><a href="" ng-click="showComments(game.id)"> <span
												id="UserGameNum{{game.id}}"
												class="glyphicon glyphicon-comment"></span>
											</a></td>
											<td><a href="" ng-click="deleteGame(game.id)"> <span
												class="glyphicon glyphicon-remove"></span>
											</a></td>
										</tr>
									</table>
  								</div>
  								<div id="sharedGames" class="tab-pane fade">
    								<table ng-table="" class="table table-condensed table-hover">
										<tr ng-repeat="game in allSharedGames">
											<td title="'Name'"><a href="#gameUserDetails/{{game.id}}"
												ng-click="myFunc(game.id)"> {{game.name}}</a></td>
											<td title="'Category'">{{game.category}}</td>
											<td><a href="" ng-click="showComments(game.id)"> <span
												id="SharedGameNum{{game.id}}"
												class="glyphicon glyphicon-comment"></span>
											</a></td>
										</tr>
									</table>
  								</div>
  								<div id="borrowedGames" class="tab-pane fade">
    								<table ng-table="" class="table table-condensed table-hover">
										<tr ng-repeat="game in allBorrowedGames">
											<td title="'Name'"><a href="#gameUserDetails/{{game.id}}"
												ng-click="myFunc(game.id)"> {{game.name}}</a></td>
											<td title="'Category'">{{game.category}}</td>
											<td><a href="" ng-click="showComments(game.id)"> <span
												id="BorrowedGameNum{{game.id}}"
												class="glyphicon glyphicon-comment"></span>
											</a></td>
										</tr>
									</table>
  								</div>
							</div>
							<div ng-show="isShowComment">
								<table class="table">
									<!-- <tr><th></th><th></th></tr> -->
									<tr ng-repeat="x in commentForGame">
										<td>{{x.username}}</td>
										<td>{{x.commentText}}</td>
										<td>{{x.date | date:dateFormat}}</td>
									</tr>
								</table>
								<form data-ng-submit=submit()>
									<input type="text" data-ng-model="comment"
										style="width: 65%; margin-left: 2%; margin-bottom: 2%"><input
										value="Comment" type="submit" ng-click="addComment"
										style="width: 25%; margin-left: 2%; margin-bottom: 2%">
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- End of MyGames Widget -->

		<!-- ng-route div -->
		<div id="main_div" ng-view></div>
		<!-- End of ng-route div -->

		<!-- Friends Widget -->

		<div id="friends_div">
			<div ng-controller="friendsCtrl">
				<div class="search-result" ng-show="click">
					<div class="header-search">Find your friends in our
						Application</div>
					<div class="content">
						<div ng-repeat="user in allUsers">
							<div class="proba">
								<div class="person">
									<div class="over-ava">
										<a href="" type="button"><img class="ava"
											src="resources/images/default-avatar.jpg" /></a>
									</div>
									<div class="name">{{ user.firstName }} {{ user.lastName}}</div>
									<div class="over-add-remove">
										<a href="" type="button" ng-click="addUserToFriend(user.id)"><img
											class="add-remove" src="resources/ico/add_user.png" /></a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="global">
					<div class="main">
						<div id="header">
							<div class="overInput">
								<input type="text" class="input" placeholder="Find new friends"
									ng-model="name" ng-keyup="findAllUsers()"
									ng-click="click = !click">
							</div>
							<div class="underInput">
								<div class="headerWords">Friends {{friends.length}}</div>
								<div class="overBell">
									<a href="" type="button" data-toggle="modal"
										data-target="#myModal">
										<div class="count" ng-hide="count < 1">{{count}}</div> <img
										class="bell" src="resources/ico/bell.png" />
									</a>
								</div>
								<div class="overMessage">
									<a href="" type="button" data-toggle="modal"
										data-target="#messanger">
										<div class="count" ng-hide="countOfNotReadMessage < 1">{{countOfNotReadMessage}}</div>
										<img class="message" src="resources/ico/message.png" />
									</a>
								</div>
							</div>
						</div>
						<!-- Start modal window -->
						<div id="myModal" class="modal fade" role="dialog">
							<div class="modal-dialog">
								<div class="modal-content over-modal-content">
									<div>
										<ul class="nav nav-tabs bigger-tab">
											<li class="active"><a data-toggle="tab" href="#menu1">Fiend
													request</a></li>
											<li><a data-toggle="tab" href="#menu2">Fiend
													responce</a></li>
										</ul>
										<div class="tab-content ">
											<div id="menu1" class="tab-pane fade in active">
												<div ng-repeat="user in allOfferedUsers">
													<div class="proba">
														<div class="person">
															<a href="" type="button"><img class="ava"
																src="resources/images/default-avatar.jpg" /></a>
															<div class="name">{{ user.firstName }}
																{{user.lastName }}</div>
															<div class="over-add-remove">
																<a href="" type="button" ng-click="add(user.id)"><img
																	class="add-remove" src="resources/ico/add_user.png" /></a>
															</div>
															<div class="over-add-remove">
																<a href="" type="button" ng-click="rejected(user.id)"><img
																	class="add-remove" src="resources/ico/remove_user.png" /></a>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div id="menu2" class="tab-pane fade">
												<div ng-repeat="friendShip in userOffered">
													<div class="user-offered"
														ng-class="{myRejected: friendShip.status.statusOfFriend == 'REJECTED'}">
														<div class="person">
															<a href="" type="button"><img class="ava"
																src="resources/images/default-avatar.jpg" /></a>
															<div class="name-of-my-offered-user">
																{{ friendShip.userId.firstName }}
																{{friendShip.userId.lastName }}
																<p>
																	<strong>status:</strong>
																	{{friendShip.status.statusOfFriend.toLowerCase()}}
																</p>
															</div>
															<div class="over-cancel">
																<a href="" type="button"
																	ng-click="cancelOffering(friendShip.userId.username)"><img
																	class="cancel" src="resources/ico/cancel.png" /></a>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal" value="">Close</button>
									</div>
								</div>
							</div>
						</div>
						<!-- End modal window -->

						<!-- Modal message -->
						<div id="messanger" class="modal fade" role="dialog">
							<div class="modal-dialog">

								<!-- Modal content-->
								<div class="modal-content">
									<div class="modal-body">
										<div class="main-message-content">
											<div class="message-friends">
												<ul class="nav nav-pills nav-stacked messanger-model"
													ng-class="{myFriendMessage: allNotReadMessagesByFriend[friends.indexOf(friend)] > 0}"
													ng-repeat="friend in friends">
													<li>
														<a href="#" class="list-messangers"
														style="padding-left: 5px; padding-top: 5px;"
														ng-click="setFriendName(friend.username)"> <img
															class="ava-messanger"
															src="resources/images/default-avatar.jpg" />{{friend.firstName
															}} {{ friend.lastName}}
													</a>
														<div class="count-of-messages"
															ng-show="allNotReadMessagesByFriend[friends.indexOf(friend)] > 0 ">{{allNotReadMessagesByFriend[friends.indexOf(friend)]}}
														</div>
													</li>
												</ul>
											</div>

											<div class="message-state">
												<div id="messages">
													<div ng-repeat="message in messages"
														ng-class="{myStyle: !message.statusOfReading && message.currentUser.username == currentFriend}"
														ng-mouseenter="readMessage(message.id)">
														<div>
															<strong>{{message.currentUser.firstName}}
																{{message.currentUser.lastName}}:</strong>
														</div>
														<div>{{message.message}}</div>
													</div>
												</div>
												<div style="display: flex; margin-top: 5px;">
													<textarea rows="3" cols="40" name="text"
														style="resize: none; margin-right: 10px;"
														ng-model="newMessage" ng-enter="sendMessage(newMessage)"></textarea>
													<button type="button" class="btn btn-default"
														ng-click="sendMessage(newMessage)">Send</button>
												</div>
											</div>

										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Close</button>
									</div>
								</div>

							</div>
						</div>
						<div class="persons">

							<div ng-repeat="friend in friends">

								<div class="person">
									<div class="over-ava">
										<a href="" type="button"><img class="ava"
											src="resources/images/default-avatar.jpg" /></a>
									</div>
									<div class="name">{{ friend.firstName }} {{
										friend.lastName}}</div>
									<div class="over-mes">
										<a href="" type="button" data-toggle="modal"
											data-target="#messanger"
											ng-click="setFriendName(friend.username)">
											<img class="message" src="resources/ico/message.png" />
										</a>
									</div>
									<div class="iconMessagediv">
										<a href="" type="button"><img class="iconChampionship"
											src="resources/ico/championship.png" /></a>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- End of Friends Widget -->

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