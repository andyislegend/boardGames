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
      <link rel="stylesheet" href="resources/css/users.css" />
      <script src="resources/bower_components/jquery/dist/jquery.min.js"></script>
      <script type="text/javascript"
         src="resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
      <script type="text/javascript"
         src="resources/bower_components/angular-ui-bootstrap-bower/ui-bootstrap-tpls.js"></script>
      <script type="text/javascript" src="resources/js/main.js"></script>
      <script type="text/javascript" src="resources/js/service.js"></script>
      <script type="text/javascript" src="resources/js/gamesGlobal.js"></script>
   </head>
   <body ng-app="usersGameApp" class="layout-boxed" ng-controller="eventsVisibleController">
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
            <nav class="secondary-menu">
               <ul ng-controller='getAvatar'>
                  <li class="dropdown" style="background-color: transparent;">
                     <a
                        href="#" class="dropdown-toggle profile-image"
                        data-toggle="dropdown"> <img ng-src="{{avatar}}"
                        style="height: 35px; width: 35px;"
                        class="img-circle dropdown-toggle profile-image"
                        data-toggle="dropdown"> <span class="caret"></span></a>
                     <ul class="dropdown-menu">
                        <li><a href="" ng-click="onlyEditProfile()"><span class="glyphicon glyphicon-pencil"
                           aria-hidden="true"></span> Edit profile</a></li>
                        <li><a href="#"><span
                           class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
                           Messages <span class="badge">2</span></a>
                        </li>
                        <!-- <li class="dropdown-header">Already leaving?</li> -->
                        <li class="divider"></li>
                        <li><a href="logout"><span
                           class="glyphicon glyphicon-off" aria-hidden="true"></span>
                           Logout </a>
                        </li>
                     </ul>
                  </li>
                  <li><a
                     class="menu-item menu-item-type-custom menu-item-object-custom current-menu-item current_page_item menu-item-home">Welcome
                     back, <b>${user}</b>
                     </a>
                  </li>
               </ul>
            </nav>
            <a href="#" id="mobile-menu-toggle" class="lines-button"> <span
               class="lines"></span>
            </a>
         </div>
      </section>
      <!-- /top-strip -->

      <!-- Main Section-->
      <section class="wrapper home-section posts-section with-sidebar">
      <button class="btn btn-success" ng-click="onlyUsers()" id="blackBut">Users</button>
      <button class="btn btn-success" ng-click="onlyGames()" id="blackBut">Games</button>
      <button class="btn btn-success" ng-click="onlyEvents()" id="blackBut">Events</button>
      <button class="btn btn-success" ng-click="onlyTournaments()" id="blackBut">Tournaments</button>
         <div class="grids">
            <div class="grid-8">
            	<div class="column-5" ng-controller="getGamesGlobalController"
					ng-hide="hideTournaments" ng-show="gamesFade">
					<div>
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
									ng-click="gameSelect(x.id, x.name)" data-toggle="modal"
									data-target="#GameModal">
									<td>{{ x.name }}</td>
									<td>{{ x.categoryName }}</td>
									<td>{{ x.minPlayers }}/{{ x.maxPlayers }}</td>
								</tr>
								<tr>
								</tr>
							</tbody>
						</table>
						<div id="GameModal" class="modal fade" role="dialog">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4 class="modal-title">Games</h4>
									</div>
									<div class="modal-body"
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
													<td><!-- <input type="range" id="ratingsRange" value="100"
														ng-change="ratingSliderChanged()" ng-model="gameRating">-->
														
														<div star-rating rating="starRating" read-only="false" max-rating="10" 
															click="ratingClick(param)" mouse-hover="ratingHover(param)" 
															mouse-leave="ratingLeave(param)">
														</div>
	            										<div> 
	            											<span class="label label-primary">
	            												Star Rating: {{starRating}}</span>
	 														<span class="label label-primary">
	 															Hover Rating: {{hoverRating}}</span>
	            										</div>
													</td>
													<td>
														<p class="text-primary">{{gameRatingDisplay}}</p>
													</td>
													<td>
														<p>{{gameDetail.rating}}</p>
													</td>
												</tr>
											</tbody>
										</table>
										<!-- <button class="btn btn-success" ng-click="ratingSaved()">Save
											rate</button>-->
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
												<table class = "table">
 								   <tr><th></th><th></th></tr>
                                    <tr ng-repeat="x in commentForGame">
                                    <td >{{x.username}}</td><td>{{x.commentText}}</td><td>{{x.date | date:dateFormat}}</td></tr>
                                    </table>
												<div >
												<form data-ng-submit=submit()>
													<input type="text" data-ng-model="comment" style = "width:80%" ><input
														type="submit" ng-click="addComment" style = "width:20%">
												</form>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- /drop-down ALL GAMES-->
							</div>
						</div>
					</div>
				</div>
               <!-- EVENTS -->
               <div ng-controller="eventListCtrl" ng-show="eventsFade">
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
               <div ng-controller="getAllUsersCtrl" ng-show="usersFade">
                  <header class="section-header">
                     <div class="section-title title-with-sep">
                        <h2 class="title">Users</h2>
                     </div>
                  </header>

                  <table class="table">
                  	 <tr>
                        <th>
                        	<input type="text" class="form-control"
                     			ng-model="searchText.username"
                     			placeholder="Search by username">
                     	</th>
                        <th></th>
                        <th></th>
                        <th>
                        	<select name="selectCountries" id="repeatSelectCountry" ng-model="searchText.address.country.name" 
                        		ng-change="getCitiesByCountry()">
                        		<option ng-repeat="country in countries" value="{{country.name}}">{{country.name}}</option>
                        	</select>
                        	<select name="repeatSelect" id="repeatSelectCity" ng-model="searchText.address.city.name">
                        		<option ng-repeat="city in cities">{{city.name}}</option>
                        	</select>
                    	</th>
                     </tr>
                     <tr>
                        <th>Username</th>
                        <th>Email</th>
                        <th>PhoneNumber</th>
                        <th>Address</th>
                     </tr>
                     <tr ng-repeat="user in users|filter:searchText">
                        <td><a href="" ng-click="getInfoAboutUserFunc(user.username)">
                           {{user.username}}</a>
                        </td>
                        <td>{{user.email}}</td>
                        <td>{{user.phoneNumber}}</td>
                        <td>{{user.address.country.name}} {{user.address.city.name}}
                           {{user.address.street}} {{user.address.houseNumber}}
                           {{user.address.roomNumber}}
                        </td>
                     </tr>
                  </table>
                  <div ng-show="showUser" class="col-sm-12" id="backgroundForOneUser">
                     <div class="col-sm-3" id="backgroundForOneUser">
                        <p>Last Name:{{oneUser.lastName}}</p>
                        <p>First Name:{{oneUser.firstName}}</p>
                        <p>Sex:{{oneUser.gender}}</p>
                        <p>Age:{{oneUser.age}}</p>
                        <p>Rating:{{oneUser.rating}}</p>
                     </div>
                     <div class="col-sm-3" id="backgroundForOneUser">
                        <b>Games user owns:</b>
                        <ul ng-repeat="game in games">
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

                                 </div>
                                 <div class="modal-body">
                                    <p>Year of production: {{oneGame.yearOfProduction}}</p>
                                    <p>Edition: {{oneGame.edition}}</p>
                                    <p>Description: {{oneGame.description}}</p>
                                    <p>Max players: {{oneGame.maxPlayers}}</p>
                                    <p>Min players: {{oneGame.minPlayers}}</p>
                                 </div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Close</button>
									</div>
								</div>
                           </div>
                        </div>
                     </div>
                     <div class="col-sm-3" id="backgroundForOneUser">
                        <b>Users tournaments:</b>
                        <ul ng-repeat="tournament in tournaments">
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
                                 </div>
                                 <div class="modal-body">
                                    <p>User creator: {{oneTournament.username}}</p>
                                    <p>Adress: {{oneTournament.country}}, {{oneTournament.city}}</p>
                                    <p>Date: {{oneTournament.dateUtil}}</p>
                                    <p>Required rating: {{oneTournament.requiredRating}}</p>
                                 </div>
                                 <div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
								</div>
                              </div>
                           </div>
                        </div>
                     </div>
                     <div class="col-sm-3" id="backgroundForOneUser">
                        <img ng-src="{{userAvatar}}" height="92" width="92">
                        <p>Score: {{oneUser.userRating}}</p>
                     </div>
                  </div>
               </div>
               <!---------------------------------------- end of users -------------------------------------------------->
               <!---------------------------------------- edit users profile -------------------------------------------->
				<div ng-controller="editProfileCtrl" ng-show="editProfileFade">
					<header class="section-header">
						<div class="section-title title-with-sep">
							<h2 class="title">Account settings</h2>
						</div>
					</header>
					<div class="col-sm-12">
					
						<ul>
							<li class="col-sm-12">
								<span class="col-sm-3">Name</span> 
								<span class="col-sm-4" ng-hide="editorNameEnabled">
									{{userProfile.firstName}} {{userProfile.lastName}}
								</span>
								<div ng-show="editorNameEnabled" class="col-sm-4">
									First Name: 
									<input ng-model="editableFirstName"> 
									<br>
									Last Name 
									<input ng-model="editableLastName"> 
									<br>
									<a href="#" ng-click="saveName()">Save</a> 
									or 
									<a href="#" ng-click="disableNameEditor()">cancel</a>.
								</div> 
								<span class="col-sm-2"> 
									<a href="" ng-click="enableNameEditor()">Edit</a>
								</span>
								<span class="col-sm-3" ng-hide="nameMessage">
									{{nameAnswer}}
								</span>
							</li>							
							<li class="col-sm-12">
								<span class="col-sm-3">Username</span>
								<span class="col-sm-4" ng-hide="editorUsernameEnabled">
									{{userProfile.username}}
								</span>
								<div ng-show="editorUsernameEnabled" class="col-sm-4">
									Username: 
									<input ng-model="editableUsername"> <br>
									<a href="#" ng-click="saveUsername()">Save</a> 
									or 
									<a href="#" ng-click="disableUsernameEditor()">cancel</a>.
								</div> 
								<span class="col-sm-2">
									<a href="" ng-click="enableUsernameEditor()">Edit</a>
								</span>
								<span class="col-sm-3" ng-hide="usernameMessage">
									{{usernameAnswer}}
								</span>
							</li>
							<li class="col-sm-12"><span class="col-sm-3">Email</span>
								<span class="col-sm-4" ng-hide="editorEmailEnabled">
									{{userProfile.email}}</span>
								<div ng-show="editorEmailEnabled" class="col-sm-4">
									Email: 
									<input ng-model="editableEmail"> <br>
									<a href="#" ng-click="saveEmail()">Save</a> 
									or 
									<a href="#" ng-click="disableEmailEditor()">cancel</a>.
								</div> 
								<span class="col-sm-2">
									<a href="" ng-click="enableEmailEditor()">Edit</a>
								</span>
								<span class="col-sm-3" ng-hide="emailMessage">
									{{emailAnswer}}
								</span>
							</li>
							<li class="col-sm-12"><span class="col-sm-3">Password</span>
								<span class="col-sm-4" ng-hide="editorPasswordEnabled">
									Change Password</span>
								<div ng-show="editorPasswordEnabled" class="col-sm-4">
									Password: 
									<input ng-model="editablePassword"> <br>
									<a href="#" ng-click="save()">Save</a> 
									or 
									<a href="#" ng-click="disablePasswordEditor()">cancel</a>.
								</div> 
								<span class="col-sm-2">
									<a href="" ng-click="enablePasswordEditor()">Edit</a>
								</span>
								<span class="col-sm-3" ng-hide="passwordMessage">
									{{passwordAnswer}}
								</span>
							</li>
							<li class="col-sm-12"><span class="col-sm-3">Gender</span>
								<span class="col-sm-4" ng-hide="editorGenderEnabled">
									{{userProfile.gender}}</span>
								<div ng-show="editorGenderEnabled" class="col-sm-4">
									Gender: 
									<select id="gender" name="gender" ng-model="editableGender">
										<option value="male">Male</option>
										<option value="female">Female</option>
									</select>
									<br>
									<a href="#" ng-click="save()">Save</a> 
									or 
									<a href="#" ng-click="disableGenderEditor()">cancel</a>.
								</div> 
								<span class="col-sm-2">
									<a href="" ng-click="enableGenderEditor()">Edit</a>
								</span>
								<span class="col-sm-3" ng-hide="genderMessage">
									{{genderAnswer}}
								</span>
							</li>
							<li class="col-sm-12"><span class="col-sm-3">Age</span>
								<span class="col-sm-4" ng-hide="editorAgeEnabled">
									{{userProfile.age}}</span>
								<div ng-show="editorAgeEnabled" class="col-sm-4">
									Age: 
									<input ng-model="editableAge"> <br>
									<a href="#" ng-click="save()">Save</a> 
									or 
									<a href="#" ng-click="disableAgeEditor()">cancel</a>.
								</div> 
								<span class="col-sm-2">
									<a href="" ng-click="enableAgeEditor()">Edit</a>
								</span>
								<span class="col-sm-3" ng-hide="ageMessage">
									{{ageAnswer}}
								</span>
							</li>
							<li class="col-sm-12"><span class="col-sm-3">Phone Number</span>
								<span class="col-sm-4" ng-hide="editorPhoneNumberEnabled">
									{{userProfile.phoneNumber}}</span>
								<div ng-show="editorPhoneNumberEnabled" class="col-sm-4">
									Phone Number: 
									<input ng-model="editablePhoneNumber"> <br>
									<a href="#" ng-click="save()">Save</a> 
									or 
									<a href="#" ng-click="disablePhoneNumberEditor()">cancel</a>.
								</div> 
								<span class="col-sm-2">
									<a href="" ng-click="enablePhoneNumberEditor()">Edit</a>
								</span>
								<span class="col-sm-3" ng-hide="phoneNumberMessage">
									{{phoneNumberAnswer}}
								</span>
							</li>
						</ul>
					</div>
				</div>
				<!---------------------------------------- end of users profile ------------------------------------------>
               <!--------------------------------------- tournaments  -------------------------------------------------->
               <div class="column-5" ng-controller="showAllTournaments"
                   ng-show="tournamentsFade">
                  <div>
                     <div class="widget widget_top-posts">
                        <h2 class="title">Tournament search</h2>
                     </div>

                     <table class="table">
                        <thead>
                        <tr>
                            <th/> <th/> <th/> <th/> <th/> <th/> <th/>
                            <th><a style="align-content: center;" href="#" type="button" data-toggle="modal" data-target="#myModalTournament">
                                <img class="tournamentIco" src="resources/ico/plus.ico"></a>
                                <div id="myModalTournament" class="modal fade" role="dialog" ng-controller="CtreateNewTournament">
                                    <div class="modal-dialog">
                                        <!-- Modal content-->
                                        <div class="modal-content"  >
                                            <div class="modal-header">
                                                <h2>Create the best tournament ever</h2>
                                                <p>P.S. ALL fields are necessarily </p>
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
                                                    <input type="number" class="field-form" data-ng-model="requiredRating" placeholder="0.0">
                                                    <br/>
                                                    <label>Max count of players</label>
                                                    <input type="number" class="field-form" data-ng-model="maxParticipants" placeholder="2">
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
                                </div></th>
                        </tr>
                           <tr>
                              <th/>Tournament name</th>
                              <th/>User creator</th>
                              <th/>Required rating</th>
                              <th/>Max Participants</th>
                              <th/>Address</th>
                               <th/>Addition</th>
                              <th/>Users Guests</th>
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
                                    <a href="#" ng-class="{myStyle: count < 1}" ng-disabled="count < 1" type="button" data-toggle="modal"
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
                                                         <button class="btn btn-info buton" data-dismiss="{{NameOfModalWindow}}"
                                                            ng-click="add(user.id)">
                                                            <div class="proba">
                                                               <img class="plus-minus" src="resources/ico/plus.ico">
                                                            </div>
                                                         </button>
                                                         <button class="btn btn-primary buton" data-dismiss="{{NameOfModalWindow}}"
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
								<div ng-repeat="user in allUsers" class="row addUsers">
									<img src="resources/images/default-avatar.jpg" width="29" style="margin-right: 5px;"/>
                                    <div class="firsName">{{ user.firstName }}</div>
                                   <div class="firsName">{{ user.lastName }} </div>
                                    <button data-toggle="modal" class="btn btn-info buton mybutton"
                                       data-target="#myAnswer"
										ng-click="addUserToFriend(user.id)" >
                                        Send offer to friend
                                    </button>


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
											<img src="resources/ico/checkmark.gif" width="250" class="col-sm-4">
                                            <div class="col-sm-8 modalUserInformation">
											     You've just sent offer to {{userWhoYouSentOffering.firstName}} {{ userWhoYouSentOffering.lastName }}, please wait for response.
                                            </div>
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
						<div ng-controller="allUsersGameCtrl">
							<div ng-controller="CreateGameCtrl">
								<div>
								<input type="submit" value="ADD GAME"
									class="btn btn-primary btn-lg" data-toggle="modal"
									data-target="#myModalHorizontal"
									style = "background-color: gray;">
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

							<div ng-controller = "getGameDetailedInfoController">
								<table class="table">
									<tr>
										<th>Name</th>
										<th>Category</th>
										<th>Comment</th>
										<th></th>
									</tr>
									<tr ng-repeat="game in allGame">
										<td><a href = "" ng-click="myFunc(game.id)" data-toggle="modal" data-target="#myModalMyGames">
										{{game.name}}</a></td>
										<td>{{game.category}}</td>
										<td>
												<a href = "" ng-click="showComments(game.id)">
												<span class="glyphicon glyphicon-comment"></span>
												</a>				
										</td>
										<td>
												<a href = "" ng-click="deleteGame(game.id)">
												<span class="glyphicon glyphicon-remove"></span>
												</a>				
										</td>
									</tr>
								</table>
									<div class="modal fade" id="myModalMyGames" tabindex="-1" role="dialog"
                           aria-labelledby="myModalLabel" aria-hidden="true">
                           <div class="modal-dialog">
                              <div class="modal-content">
                              <div class="modal-header">
                                    <h1 ng-repeat = "game in games">{{game.name}}</h1>
                                    </div>
                                 <div class="modal-body" ng-repeat = "game in games">
                                    <p>Year of production: {{game.yearOfProduction}}</p>
                                    <p>Edition: {{game.edition}}</p>
                                    <p>Description: {{game.description}}</p>
                                    <p>Max players: {{game.maxPlayers}}</p>
                                    <p>Min players: {{game.minPlayers}}</p>
                                 </div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Close</button>
									
								</div>
                           </div>
                        </div>
					</div>
								<div ng-show="isShowComment">
								
                                   <table class = "table">
 								   <tr><th></th><th></th></tr>
                                    <tr ng-repeat="x in commentForGame">
                                    <td >{{x.username}}</td><td>{{x.commentText}}</td><td>{{x.date | date:dateFormat}}</td></tr>
                                    </table>
                                     <form data-ng-submit=submit()>
													<input type="text" data-ng-model="comment" style = "width:70%" ><input
														type="submit" ng-click="addComment" style = "width:20%; margin: 5px;">
												</form>
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
      <br>
      <br>
      <br>
      <!-- Main Section-->
      <!-- Footer -->
      <section id="site">
         <footer id="footer" class=" anmtd" role="contentinfo">
            <div class="footer-sidebar">
               <div class="copyright">
                  <div class="wrapper">
                     <div class="grids">
                        <div class="grid-10">Copyright � 2016 Java-179 All Rights
                           Reserved
                        </div>
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