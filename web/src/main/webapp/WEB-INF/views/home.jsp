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
<link rel="stylesheet" href="resources/css/new-css.css" />
<link rel="stylesheet" href="resources/css/fonts.css" />
<link rel="stylesheet" href="resources/css/tournament.css" />
<link rel="stylesheet" href="resources/css/friend.css" />
<script type="text/javascript"
	src="resources/bower_components/angular/angular.js"></script>
<script src="resources/bower_components/jquery/dist/jquery.min.js"></script>
<script type="text/javascript"
	src="resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="resources/bower_components/angular-ui-bootstrap-bower/ui-bootstrap-tpls.js"></script>
<script type="text/javascript" src="resources/js/main.js"></script>
<script type="text/javascript" src="resources/js/service.js"></script>
<body ng-app="usersGameApp" class="layout-boxed">
	<!-- top-strip -->
	<section class="top-strip">
		<div class="wrapper clearfix">
			<div class="search-form">
				<form method="get" id="searchform" action="#" role="search">
					<input type="text" name="s" id="s" value="Search"
						onfocus="if(this.value=='Search')this.value='';"
						onblur="if(this.value=='')this.value='Search';" />
					<button type="submit">
						<i class="icomoon-search"></i>
					</button>
				</form>
				<div class="mobile-search-button"></div>
			</div>
			<ul class="social">
				<li><a href="#" class="icomoon-share social-share-link"></a>
					<ul>
						<li><a href="#" class="icomoon-feed" target="_blank"></a></li>
						<li><a href="http://www.facebook.com"
							class="icomoon-facebook" target="_blank"></a></li>
						<li><a href="http://twitter.com" class="icomoon-twitter"
							target="_blank"></a></li>
						<li><a href="http://pinterest.com" class="icomoon-pinterest"
							target="_blank"></a></li>
						<li><a href="http://instagram.com" class="icomoon-instagram"
							target="_blank"></a></li>
					</ul></li>
			</ul>
			<nav class="secondary-menu">
				<ul ng-controller='getAvatar'>
					<li class="dropdown" style="background-color: transparent;"><a
						href="#" class="dropdown-toggle profile-image"
						data-toggle="dropdown"> <img ng-src="{{avatar}}"
							style="height: 35px; width: 35px;"
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
					<li><a
						class="menu-item menu-item-type-custom menu-item-object-custom current-menu-item current_page_item menu-item-home">Welcome
							back, <b>${user}</b>
					</a></li>
				</ul>
			</nav>
			<a href="#" id="mobile-menu-toggle" class="lines-button"> <span
				class="lines"></span>
			</a>
		</div>
	</section>
	<!-- /top-strip -->
	<div class="anmtd main-menu-container" role="navigation">
		<nav class="wrapper main-menu">
			<ul id="menu-main-menu" class="menu">
				<li class="dropdown"><a href="#" class="dropdown.toggle"
					data-toggle="dropdown" style="font-weight: bold;"><span>Games</span></a>
					<ul class="dropdown-menu">
						<div class="sub-posts">
							<!-- drop-down ALL GAMES-->
							<div class="column-5" ng-controller="getGamesGlobalController"
								ng-hide="hideTournaments">
								<div class="mainbar">
									<div class="widget widget_top-posts">
										<h2 class="title">Games</h2>
									</div>
									<table class="table">
										<thead>
											<tr>
												<th>Game</th>
												<th>Category</th>
												<th>Min/Max players</th>
											</tr>
										</thead>
										<tbody>
											<tr ng-repeat="x in gamesGlobal"
												ng-click="gameSelect(x.id, x.name)"
												ng-init="gameDetailsShown=false">
												<td>{{ x.name }}</td>
												<td>{{ x.categoryName }}</td>
												<td>{{ x.minPlayers }}/{{ x.maxPlayers }}</td>
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
										<pre>{{gameDetail.rules}}</pre>
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
														<p class="text-primary">{{gameRatingText}}:
															{{gameRating}}</p>
													</td>
													<td>
														<p>{{gameDetail.rating}}</p>
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
													<td>{{ x.username }}</td>
													<td>{{ x.edition }}</td>
													<td>{{ x.yearOfProduction }}</td>
													<td><button ng-click="showComments(x.id)">Comment</button></td>
												</tr>
											</tbody>
										</table>

										 <div>
                                 <div ng-show="isShowComment">
                                   <table>
                                   <thead>
                                   <tr><th></th><th></th></tr>
                                   </thead>
                                   <tbody>
                                   <tr ng-repeat="x in comments"></td>{x.text}<td></td></tr>
                                   </tbody>
                                                                      </table>
                                    <form data-ng-submit=submit()>
                                       <input type="text" data-ng-model="comment"><input
                                          type="submit" ng-click="addComment">
                                    </form>
                                 </div>
                                 </div>
									</div>
								</div>
								<!-- /drop-down ALL GAMES-->
							</div>
                        </div>
					</ul></li>
				<li class="dropdown"><a href="#" class="dropdown.toggle"
					data-toggle="dropdown" style="font-weight: bold;"><span>Tournaments</span></a>
					<ul class="dropdown-menu">
						<div class="sub-posts">
							<!-- TOURNAMENTS -->
							<div class="column-5" ng-controller="showAllTournaments"
								ng-hide="hideTournaments">
								<div class="mainbar">
									<div class="widget widget_top-posts">
										<h2 class="title">Tournament search</h2>
										<a href="#" type="button" data-toggle="modal"
											data-target="#myModalTournament"> <img
											class="tournamentIco" src="resources/ico/plus_white.png"></a>
									</div>
									<table class="table">
										<thead>
											<tr>
												<th>Tournament name</th>
												<th>User creator</th>
												<th>Required rating</th>
												<th>Max Participants</th>
												<th>Address</th>
												<th>Users Guests</th>
												<th>Join</th>
											</tr>
										</thead>
										<tbody>
											<tr ng-repeat="x in tournaments">
												<td>{{ x.tournamentName }}</td>
												<td>{{ x.username }}</td>
												<td>{{x.requiredRating}}</td>
												<td>{{x.maxParticipants}}</td>
												<td>{{x.country}} {{x.city}} <br /> {{x.street}}
													{{x.houseNumber}} {{x.roomNumber}}
												</td>
												<td>
													<table>
														<tr ng-repeat="y in x.userGuests">
															<td>{{y}}</td>
														</tr>
													</table>
												</td>
												<td><button ng-disabled="x.isCanJoin" class="button"
														style="color: black"
														ng-click="JoinTournament(x.tournamentId)">Join</button></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							<!-- /TOURNAMENTS -->
						</div>
					</ul></li>
			</ul>
		</nav>
	</div>
	<!-- Main Section-->
	<section class="wrapper home-section posts-section with-sidebar">
		<div class="grids">
			<div class="grid-8">
				 <!-- EVENTS -->
               <div class="mainbar" ng-controller="eventListCtrl">
                  <div class="column-1" >
                     <header class="section-header" >
                        <div class="section-title title-with-sep">
                           <h2 class="title">COMING EVENTS:{{events.length}}</h2>
                        </div>
                     </header>
                     <div class="grids grid-layout columns-size-1 entries">
                        <div class=" jcarousel-skin-tango">
                           <div class="jcarousel-container jcarousel-container-horizontal"
                              style="display: block;">
                              <li class="jcarousel-item jcarousel-item-horizontal ">
                                 <a
                                    ng-repeat="event in events" href="#" class="item">
                                    <img width="240" height="240" 
                                       ng-src={{event.imgsrc}} alt={{event.name}}> 
                                    <span class="game_name">{{event.game}}</span>
                                    <div class="name" style="top: 201px;">
                                       <div class="num">
                                          {{event.date|date:MMMM }}<span>{{event.date|date:dd}}</span>
                                       </div>
                                       <div class="tema">{{event.name}}</div>
                                    </div>
                                 </a>
                              </li>
                           </div>
                        </div>
                     </div>
                  </div>
                  <div class="widget">
                     
                      <div>
                           <input type="submit" value="ADD EVENT" ng-click="showForm() "
                              style="width: 100%; height:40px; margin-top: 20px; margin-bottom: 10px">
                        </div>
                        <form data-ng-submit=submit() role="form" ng-show="showText">
                          
                              <input type="text" class="form-control"
                     ng-model="input_name" placeholder="Event" style="margin-bottom:10px">
                     
                      <input type="text" class="form-control"
                     ng-model="input_game" placeholder="Game" style="margin-bottom:10px">
                     
                     <input type="text" class="form-control"
                     ng-model="input_description" placeholder="Description" style="margin-bottom:10px">
                    
                    <input type="text" class="form-control"
                     ng-model="input_place" placeholder="Place" style="margin-bottom:10px">
                     
                      <input type="date" class="form-control"
                     ng-model="input_date" placeholder="Date" style="margin-bottom:10px">
                   
                           <div>
                              <input type="submit" value="APPLY" ng-click="addEvent()"
                                 style="width: 100%; margin-bottom: 10px">
                           </div>
                        </form>
                     
                     
                     
                  </div>
               </div>
               <!-- /EVENTS -->
                         <!------------------------------------------- Users info ------------------------------------------------>

				<div class="modal fade"
					ng-controller="getAllUsersWithNegativeRating" id="myModalBannedUsers"
					tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
					aria-hidden="true" >
					<div class="modal-dialog">
						<div class="modal-content">
							<!-- Modal Header -->
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
								</button>
								<div class="modal-body">
								<b>Attention!</b>
								<p>{{bannedUsers}}</p>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
				<div class="mainbar" ng-controller="getAllUsersCtrl">
                  <header class="section-header" ng-controller="eventListCtrl">
                     <div class="section-title title-with-sep">
                        <h2 class="title">Users</h2>
                     </div>
                  </header>
                  <input type="text" class="form-control"
                     ng-model="searchText.lastName" placeholder="Search by last name" style="margin-bottom:10px">
                  <input type="text" class="form-control"
                     ng-model="searchText.address.city" placeholder="Search by city">
                  <table class="table">
                     <tr>
                        <th>Last Name, First Name</th>
                        <th>Email</th>
                        <th>PhoneNumber</th>
                        <th>Address</th>
                     </tr>
                     <tr ng-repeat="user in users|filter:searchText">
                        <td><a href="" ng-click="getInfoAboutUserFunc(user.id)">
                           {{user.lastName}} {{user.firstName}}</a>
                        </td>
                        <td>{{user.email}}</td>
                        <td>{{user.phoneNumber}}</td>
                        <td>{{user.address.country}} {{user.address.city}}
                           {{user.address.street}} {{user.address.houseNumber}}
                           {{user.address.roomNumber}}
                        </td>
                     </tr>
                  </table>
                  <div ng-show="showUser" class="col-sm-12" id="backgroundForOneUser">
                     <div class="col-sm-3" id="backgroundForOneUser">
                        <p>Last Name:{{oneUser.lastName}}</p>
                        <p>First Name:{{oneUser.firstName}}</p>
                        <p>Username:{{oneUser.username}}</p>
                        <p>Sex:{{oneUser.sex}}</p>
                        <p>Age:{{oneUser.age}}</p>
                        <p>Rating:{{oneUser.rating}}</p>
                     </div>
                     <div class="col-sm-3" id="backgroundForOneUser"
                        ng-controller="getAllUsersGames">
                        <a href="" ng-click="getInfoAboutUserGames(oneUser.username)">
                        Games user owns</a>
                        <ul ng-show="showUsersGames" ng-repeat="game in games">
                           <li><a href="" ng-click="getInfoAboutGame(game.id)" data-toggle="modal" data-target="#myModalGames">
                              {{game.name}}</a>
                           </li>
                        </ul>
					 <div class="modal fade" id="myModalGames" tabindex="-1" role="dialog"
                           aria-labelledby="myModalLabel" aria-hidden="true">
                           <div class="modal-dialog">
                              <div class="modal-content">
                                 <!-- Modal Header -->
                                 <div class="modal-header">
                                 <h1>{{oneGame.name}}</h1>
                                    <button type="button" class="close"
                                       data-dismiss="modal">
                                    <span aria-hidden="true">&times;</span>
                                    <span class="sr-only">Close</span>
                                    </button>
                                 </div>
                                 <div class="modal-body">
									<p>Year of production: {{oneGame.yearOfProduction}}</p>
									<p>Edition: {{oneGame.edition}}</p>
									<p>Description: {{oneGame.description}}</p>
									<p>Max players: {{oneGame.maxPlayers}}</p>
									<p>Min players: {{oneGame.minPlayers}}</p>
								</div>

                                 </div>
                              </div>
                           </div>
                     </div>
                     <div class="col-sm-3" id="backgroundForOneUser"
                        ng-controller="getAllUsersTournaments">
                        <a href=""
                           ng-click="allUsersTournaments(oneUser.username)">
                        Users tournaments</a>
                        <ul ng-show="showUsersTournaments" ng-repeat="tournament in tournaments">
                           <li><a href="" ng-click="getInfoAboutTournament(tournament.tournamentId)" data-toggle="modal" data-target="#myModalTournaments">
                              {{tournament.tournamentName}}</a>
                           </li>
                        </ul>
                         <div class="modal fade" id="myModalTournaments" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                           <div class="modal-dialog">
                              <div class="modal-content">
                                 <!-- Modal Header -->
                                 <div class="modal-header">
                                 	<h1>{{oneTournament.tournamentName}}</h1>
                                    <button type="button" class="close"
                                       data-dismiss="modal">
                                    <span aria-hidden="true">&times;</span>
                                    <span class="sr-only">Close</span>
                                    </button>
                                 </div>
                                 <div class="modal-body">
									<p>User creator: {{oneTournament.username}}</p>
									<p>Adress: {{oneTournament.country}}, {{oneTournament.city}}, <br />
										{{oneTournament.street}}, {{oneTournament.houseNumber}}/{{oneTournament.roomNumber}}</p>
									<p>Date: {{oneTournament.dateUtil}}</p>
									<p>Required rating: {{oneTournament.requiredRating}}</p>
								</div>

                                 </div>
                              </div>

                     </div>
                     </div>
                     <div class="col-sm-3" id="backgroundForOneUser">
                        <img fallback-src='http://localhost/img/avatar/ava.png'
                           ng-src="{{userUrl}}" height="52" width="52">
                     </div>
                  </div>
               </div>
               <!---------------------------------------- end of users -------------------------------------------------->
                <!--------------------------------------- tournaments  -------------------------------------------------->
                <div class="column-5" ng-controller="showAllTournaments"
                     ng-hide="hideTournaments">
                    <div class="mainbar">
                        <div class="widget widget_top-posts">
                            <h2 class="title">Tournament search</h2>
                            <a style="align-content: center;" href="#" type="button" data-toggle="modal" data-target="#myModalTournament">
                                <img class="tournamentIco" src="resources/ico/plus.ico"></a>
                            <div id="myModalTournament" class="modal fade" role="dialog" ng-controller="showAllTournaments">
                                <div class="modal-dialog">
                                    <!-- Modal content-->
                                    <div class="modal-content"  >
                                        <div class="modal-header">
                                            <h2>Create the best tournament ever</h2>
                                        </div>
                                        <div class="modal-body">
                                            <form>
                                            <label>Select game for your awsome tournament</label>
                                            <select required="required" class="field-form" data-ng-model="selectedGame">
                                                <option  ng-repeat="game in games" value="{{game.name}}">{{game.name}}</option>
                                            </select>
                                            <label>Name of your awsome tournament</label>
                                            <input required="required" class="field-form" data-ng-model="tournamentName" placeholder="Name">
                                            <br/>
                                            <label>Required rating to join to your tournament</label>
                                            <input class="field-form" data-ng-model="requiredRating" placeholder="0.0">
                                            <br/>
                                            <label>Max count of players</label>
                                            <input class="field-form" data-ng-model="maxParticipants" placeholder="2">
                                            <br/>
                                            <label>Choose date of your tournament</label>
                                            <input required="required" class="field-form" data-ng-model="date" type="date">
                                            <br/><br/>
                                            <label>Enter place where tournament will be</label>
                                            <input class="field-form" data-ng-model="countryTournament" placeholder="Country">
                                            <input class="field-form" data-ng-model="cityTournament" placeholder="City">
                                            <br/>
                                            <textarea rows="3"  class="field-form" data-ng-model="additionTournament" placeholder="City"></textarea>
                                            <br/>
												<button type="button" class="btn btn-default" ng-click="createTournament()" value="Close" data-dismiss="modal"> Create</button>
												<button type="button" class="btn btn-default" ng-click="modalClose()"
														data-dismiss="modal">Close</button>
                                                </form>
                                        </div>
                                        <div class="modal-footer">

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <table class="table">
                            <thead>
                            <tr>
                                <th>Tournament name</th>
                                <th>User creator</th>
                                <th>Required rating</th>
                                <th>Max Participants</th>
                                <th>Address</th>
                                <th>Users Guests</th>
                                <th>Join</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr ng-repeat="x in tournaments">
                                <td>{{ x.tournamentName }}</td>
                                <td>{{ x.username }}</td>
                                <td>{{x.requiredRating}}</td>
                                <td>{{x.maxParticipants}}</td>
                                <td>
                                    {{x.country}} {{x.city}}
                                </td>
                                <td>{{x.addition}}</td>
                                <td>
                                    <table>
                                        <tr ng-repeat="y in x.userGuests">
                                            <td>{{y}}</td>
                                        </tr>
                                    </table>
                                </td>
                                <td>
                                    <button ng-disabled="x.isCanJoin" class="button"
                                            style="color: black"
                                            ng-click="JoinTournament(x.tournamentId)">Join</button>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <!---------------------------------------- end of tournaments -------------------------------------------------->
            </div>
            <!-- sidebar -->
            <div class="grid-4">
               <aside class="asidebar">
                  <!-- sidebar FRIENDS-->
                  <div class="column-4" ng-controller="friendsCtrl">
                     <div class="widget">
                        <h2 class="title">Friends</h2>
                        <table class="table table-striped">
                           <thead>
                              <tr>
                                 <th>Friends</th>
                                 <td>
                                    <a href="#">
                                       <div class="proba">
                                          <img class="FriendIco" src="resources/ico/add_user.png"
                                             ng-click="showFriends = !showFriends">
                                       </div>
                                    </a>
                                 </td>
                                 <td>
                                    <a href="#" type="button" data-toggle="modal"
                                       data-target="#myModal">
                                       <div class="proba">
                                          <div class="count" ng-hide="count < 1">{{count}}</div>
                                          <img class="FriendIco" src="resources/ico/bell.png">
                                       </div>
                                    </a>
                                    <!-- Modal -->
                                    <div id="myModal" class="modal fade" role="dialog">
                                       <div class="modal-dialog">
                                          <!-- Modal content-->
                                          <div class="modal-content">
                                             <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                <h4 class="modal-title">This users want to be your
                                                   friend
                                                </h4>
                                             </div>
                                             <div class="background">
                                                <div ng-repeat="user in allOfferedUsers" class="col-sm-4">
                                                   <div class="user">
                                                      <div class="rows">
                                                         <img class="ava"
                                                            src="resources/images/default-avatar.jpg" />
                                                      </div>
                                                      <div class="rows">name: {{user.firstName }}</div>
                                                      <div class="rows">Last name: {{user.lastName}}</div>
                                                      <div class="rows">
                                                         <button class="btn btn-info buton"
                                                            ng-click="add(user.id)">
                                                            <div class="proba">
                                                               <img class="plus-minus" src="resources/ico/plus.ico">
                                                            </div>
                                                         </button>
                                                         <button class="btn btn-primary buton"
                                                            ng-click="rejected(user.id)">
                                                            <div class="proba">
                                                               <img class="plus-minus"
                                                                  src="resources/ico/minus.ico">
                                                            </div>
                                                         </button>
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
                                 </td>
                              </tr>
                           </thead>
                           <tbody ng-hide="showFriends">
                              <tr ng-repeat="friend in friends">
                                 <td>{{ friend.firstName }}</td>
                                 <td>{{ friend.lastName }}</td>
                                 <td>
                                    <a href="#">
                                       <div class="proba">
                                          <img class="FriendIco" src="resources/ico/messages.png">
                                       </div>
                                    </a>
                                 </td>
                                 <td>
                                    <a href="#">
                                       <div class="proba">
                                          <img class="FriendIco" src="resources/ico/trophy.png">
                                       </div>
                                    </a>
                                 </td>
                              </tr>
                           </tbody>
                        </table>
                        <div ng-hide="!showFriends">
								<h2 class="title">All users in our App</h2>
								<input type="text" placeholder="name or last name"
									ng-model="name" ng-keyup="findAllUsers()" />
								<h3></h3>
								<div ng-repeat="user in allUsers" class="addUsers">
									<img src="resources/images/default-avatar.jpg" width="25" />
									{{ user.firstName }} {{ user.lastName }} <a href="#"
										type="button" data-toggle="modal" data-target="#myAnswer"
										ng-click="addUserToFriend(user.id)"> <img
										class="addUserImg" src="resources/ico/plus.ico"></a>
								</div>

							</div>
							<!-- Modal -->
							<div id="myAnswer" class="modal fade" role="dialog">
								<div class="modal-dialog">

									<!-- Modal content-->
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">Modal Header</h4>
										</div>
										<div class="modal-body done">
											<img ng-show="answer == 'Done'"
												src="resources/ico/checkmark.gif" width="250">
											{{answer}}!
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">Close</button>
										</div>
									</div>

								</div>
							</div>
						</div>
					</div>
					<!-- /sidebar FRIENDS-->
					<!-- sidebar MYGAMES-->
					<div class="widget">
						<h2 class="title">My Games</h2>
						<div ng-controller="CreateGameCtrl">
							<div>
								<input type="submit" value="ADD GAME"
									class="btn btn-primary btn-lg" data-toggle="modal"
									data-target="#myModalHorizontal">
							</div>
							<div class="modal fade" id="myModalHorizontal" tabindex="-1"
								role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<!-- Modal Header -->
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">
												<span aria-hidden="true">&times;</span> <span
													class="sr-only">Close</span>
											</button>
										</div>
										<div class="modal-body">
											<form data-ng-submit=submit() role="form">
												<div>
													<input class="field-form" data-ng-model="name"
														placeholder="Name">
												</div>
												<div>
													<label>Select Category:</label> <select class="field-form"
														id="exampleSelect1" data-ng-model="category">
														<option ng-repeat="category in categories"
															value="{{category.name}}">{{category.name}}</option>
													</select>
												</div>
												<div>
													<input class="field-form" data-ng-model="description"
														placeholder="Description"> <input
														class="field-form" data-ng-model="rules"
														placeholder="Rules"> <input class="field-form"
														data-ng-model="maxPlayers" placeholder="Max Players">
													<input class="field-form" data-ng-model="minPlayers"
														placeholder="Min Players"> <input
														class="field-form" data-ng-model="edition"
														placeholder="Edition"> <input class="field-form"
														data-ng-model="year" placeholder="Year">
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
											<div>
												<input type="submit" value="More" ng-click="myFunc(game.id)"
													style="border: 1px solid #787878; border-radius: 1px;">
											</div>
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
					<!-- /sidebar MYGAMES-->
				</aside>
			</div>
			<!-- /sidebar -->
		</div>
	</section>
	<!-- Main Section-->
	<!-- Footer -->
	<section id="site">
		<footer id="footer" class=" anmtd" role="contentinfo">
			<div class="footer-sidebar">
				<div class="copyright">
					<div class="wrapper">
						<div class="grids">
							<div class="grid-10">Copyright � 2016 Java-179 All Rights
								Reserved</div>
							<div class="grid-2">
								<a href="#" class="alignright back-top">Back to top <i
									class="icomoon-chevron-left"></i></a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</footer>
	</section>
	<!-- /Footer -->
</body>
</html>