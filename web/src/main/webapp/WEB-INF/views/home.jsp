<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
  uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" name="viewport">
    <!-- CSS -->
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
    <link rel="stylesheet" href="resources/css/gameUser.css" />
    <link rel="stylesheet" href="resources/css/tornaments.css" />
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
      src="resources/js/controllers/gameNotifyController.js"></script>
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
      src="resources/js/service/friendsService.js"></script>    
    <script type="text/javascript"
      src="resources/js/controllers/CreateNewTournamentCtrl.js"></script>
    <script type="text/javascript"
      src="resources/js/controllers/showAllTournamentsCtrl.js"></script>
    <script type="text/javascript"
      src="resources/js/controllers/eventAdminCtrl.js"></script>
    <script type="text/javascript"
      src="resources/js/controllers/getOneUser.js"></script>
    <script type="text/javascript"
      src="resources/js/controllers/notificationController.js"></script>
    <script type="text/javascript" src="resources/js/menu.js"></script>
    <script type="text/javascript"
      src="resources/bower_components/ng-table/dist/ng-table.min.js"></script>
    <script type="text/javascript"
      src="resources/bower_components/ng-q/q.module.js"></script>
    <script
      src="resources/bower_components/angular-file-model/angular-file-model.js"></script>
    <script src="resources/bower_components/angular-translate/angular-translate.js"></script>
    <script type="text/javascript" src="resources/js/localization.js"></script>
    <script src="resources/bower_components/angular-translate-storage-cookie/angular-translate-storage-cookie.js"></script>
    <script src="resources/bower_components/angular-cookies/angular-cookies.js"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script src="resources/js/controllers/statisticsController.js"></script>
    <script src="resources/js/service/pieChartService.js"></script>
    <!-- End of Scripts -->
  </head>
  <body ng-app="homeApp" ng-controller="localizationController">
    <!-- Top header -->
    <header>
      <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid" ng-controller="getUser">
          <div class="navbar-header">
            <a class="navbar-brand"><img alt="logo"
              src="resources/images/logo.png"
              style="height: 50px; weight: 50px;"></a>
          </div>
          <p style="font-size: 14pt;" class="navbar-text">
            <b translate="PROJECT_NAME"></b>
          </p>
          <div ng-mouseenter="showIt()"
            ng-mouseleave="hideIt()">
            <div class="navbar-form navbar-left">
              <ul id="container">
                <li id="userRating" translate="USER_LEVEL" 
                  translate-values="{userLevel:'{{user.level}}'}"></li>
                <li><progress value="0" max="100" id=ratingBar></progress></li>
                <li class="outerDiv" ng-show="hovering">
                  <p translate="CUR_RATING" 
                    translate-values="{rating:'{{user.userRating}}'}" class="innerDiv">
                  </p>
                  <p translate="NEEDED_RATING" 
                    translate-values="{neededRating:'{{neededRating}}'}" class="innerDiv">
                  </p>
                </li>
              </ul>
            </div>
          </div>
          <div>
            <div style="margin-left: 30%" ng-controller="GlobalSearchCTRL">
              <form data-ng-submit=submit() style="margin-left: 3%;"
                class="navbar-form navbar-left" role="search">
              <form id="searchthis" style="display: inline;" method="get">
                <input id="search-box" name="q" size="50" type="text" ng-model="search" />                
                  <a ng-href= "#search/{{search}}">
                <button id="search-btn" ng-click="searchAll()" type="submit">
                <span class="glyphicon glyphicon-search"></span>
                </button>
                </a>
              </form>
              </form>
            </div>
          </div>
          <ul class="nav navbar-nav navbar-right">
            <li><span class="btn btn-link">
              <img id="flag"src="resources/images/gb.png" ng-click="changeLanguage('en')"/>
              <img id="flag"src="resources/images/ua.png" ng-click="changeLanguage('ua')"/></span>
            </li>
            <li><a class="btn btn-secondary btn-lg disabled"><span translate="WELCOME_BACK"></span>
              <em><b style="color: white; font-size: 12pt;">${user}</b></em>
              </a>
            </li>
            <li class="dropdown" style="background-color: transparent;">
              <a
                href="" class="dropdown-toggle profile-image"
                data-toggle="dropdown"> <img
                style="height: 25px; width: 25px;" ng-src="{{avatar}}"
                class="img-circle dropdown-toggle profile-image"
                data-toggle="dropdown"> <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="#/edit" ><span	class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                  <span translate="EDIT_PROFILE"></span></a>
                </li>
                <li><a ng-href="#/notification" ><span
                  class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
                  <span translate="MESSAGES"></span></a>
                </li>
                <sec:authorize access="hasRole('ADMIN')">
                  <li><a href="#users"><span
                    class="glyphicon glyphicon-user" aria-hidden="true"></span>
                    <span translate="USERS"></span></a>
                  </li>
                </sec:authorize>
                <sec:authorize access="hasRole('ADMIN')">
                  <li><a href="#moderateEvent"><span
                    class="glyphicon glyphicon-scissors" aria-hidden="true"></span>
                    <span translate="MODERATE_EVENT"></span></a>
                  </li>
                </sec:authorize>
                <li class="divider"></li>
                <li><a href="logout"><span
                  class="glyphicon glyphicon-off" aria-hidden="true"></span>
                  <span translate="LOGOUT"></span></a>
                </li>
              </ul>
            </li>
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
        <a id="menutoggle"></a>
        <ul class="lvl1">
          <li><a href="#allGames" translate="ALL_GAMES"></a></li>
          <li><a href="#events" translate="EVENTS"></a></li>
          <li><a href="#tournaments" translate="TOURNAMENTS"></a></li>
          <li class="isSelected" class="lvl1"><a href="#statistics" translate="STATISTICS">
            Statistics</a>
          </li>
        </ul>
      </nav>
    </div>
    <!-- End of Menu slider -->
    <div id="main_container">
     
      <!-- ng-route div -->
      <div id="main_div" ng-view></div>
      <!-- End of ng-route div -->
      
      <!-- My Side Widget -->
      <div id="widget_div">
        <ul class="nav nav-tabs nav-justified">
          <li class="active"><a data-toggle="tab" data-target="#Games"> My
            Games</a>
          </li>
          <li><a data-toggle="tab" data-target="#Friends"> My Friends </a></li>
        </ul>
        <div class="tab-content">
          <div id="Games" class="tab-pane fade in active">
            <!-- MyGames Widget -->
            <div id="mygames_div">
              <div ng-controller="allUsersGameCtrl">
                <h1 style="text-align: center" class="text-primary">Games</h1>
                <div>
                  <div>
                    <div ng-controller="CreateGameCtrl">
                    	<div class="centeredObjects">
                        	<a class="btn btn-success" >
                        		<span class="glyphicon glyphicon-plus" 
                        			data-toggle="modal"
                          			data-target="#myModalHorizontal"></span> 
                        	</a>
                        	<a href="#gameNotifications/{{username}}" class="btn btn-info" >
                        		<span class="glyphicon glyphicon-bell" ></span> 
                        	</a>
                        	<span class="badge badge-notify">{{uncheckedNotifiCount}}</span>
                      	</div>
                      <br/>
                      <div class="modal fade" id="myModalHorizontal" tabindex="-1"
                        role="dialog" aria-labelledby="myModalLabel"
                        aria-hidden="true">
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
                                <form name = "addGame" role="form" data-ng-submit=submit() novalidate>
                                  <div class="form-group" ng-class="{ 'has-error' : addGame.name.$invalid && !addGame.name.$pristine }">
                                    <label>Name: </label>
                                     <input name = "name" class="form-control" 
                                      data-ng-model="name" required>
                                  		<p ng-show="addGame.name.$invalid && !addGame.name.$pristine" class="help-block">Name for game is required.</p>
                                  </div>
                                  <div class="form-group">
                                    <label>Select Category:</label> 
                                    <select
                                      class="form-control" id="exampleSelect1"
                                      data-ng-model="category">
                                      <option ng-repeat="category in categories"
                                        value="{{category.name}}">{{category.name}}</option>
                                    </select>
                                  </div>
                                  <div class="form-group">
                                    <label>Description: </label> <input
                                      class="form-control" data-ng-model="description">
                                  </div>
                                  <div>
                                    <label>Rules :</label> <input class="form-control"
                                      data-ng-model="rules">
                                  </div>
                                  <div class="form-group">
                                    <label>Min players: </label> <input
                                      class="form-control" ng-minlength="0"
                                      data-ng-model="minPlayers" type="number"
                                      min = "0">
                                  </div>
                                  <div class="form-group">
                                    <label>Max players: </label> <input
                                      class="form-control" data-ng-model="maxPlayers"
                                      type="number"  min = "{{minPlayers}}">
                                  </div>
                                  <div class="form-group">
                                    <label>Edition</label> <input class="form-control"
                                      data-ng-model="edition">
                                  </div>
                                  <div class="form-group">
                                    <label>Year of Production: </label> <input
                                      class="form-control" data-ng-model="year"
                                      type="number" min = "1900" max = "2016">
                                  </div>
                                  <div>
                                    <input type="submit" value="ADD"
                                      ng-disabled="addGame.$invalid"
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
                      <ul class="nav nav-tabs nav-justified">
                        <li class="active"><a data-toggle="tab" data-target="#ownGames">My</a></li>
                        <li><a data-toggle="tab" data-target="#sharedGames">Shared</a></li>
                        <li><a data-toggle="tab" data-target="#borrowedGames">Borrowed</a></li>
                      </ul>
                      <div class="tab-content">
                        <div id="ownGames" class="tab-pane fade in active">
                          <table ng-table="" class="table table-condensed table-hover">
                            <tr ng-repeat="game in allGame">
                              <td title="'Name'"><a
                                href="#gameUserDetails/{{game.id}}">
                                {{game.name}}</a>
                              </td>
                              <td title="'Category'">{{game.category}}</td>
                              <td title="'Comments'"><a href=""
                                ng-click="showComments(game.id)"> <span
                                id="UserGameNum{{game.id}}"
                                class="glyphicon glyphicon-comment"></span>
                                </a>
                              </td>
                              <td title="'Delete'"><a href=""
                                ng-click="deleteGame(game.id)" > 
                                <span class="glyphicon glyphicon-remove"></span>
                                </a>
                              </td>
                            </tr>
                          </table>
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
                          <div class="modal fade" id="modalCantToDelete" role="dialog">
                            <div class="modal-dialog modal-sm">
                              <div class="modal-content">
                                <div class="modal-body">
                                  <p>Sorry, but you can't delete this game, becouse this game use in tournaments</p>
                                </div>
                                <div class="modal-footer">
                                  <button type="button"  class="btn btn-default" data-dismiss="modal">Close</button>
                                </div>
                              </div>
                            </div>
                          </div>
                          <div class="modal fade" id="modalDelete" role="dialog">
                            <div class="modal-dialog modal-sm">
                              <div class="modal-content">
                                <div class="modal-body">
                                  <p>Are you really want to delete this Game?</p>
                                </div>
                                <div class="modal-footer">
                                  <button type="button" ng-click = "confirmationToDelete()" class="btn btn-default" data-dismiss="modal">Delete</button>
                                  <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div id="sharedGames" class="tab-pane fade">
                          <table ng-table="" class="table table-condensed table-hover">
                            <tr ng-repeat="game in allSharedGames">
                              <td title="'Name'"><a
                                href="#gameUserDetails/{{game.id}}"
                                ng-click="myFunc(game.id)"> {{game.name}}</a></td>
                              <td title="'Category'">{{game.category}}</td>
                              <td title="'Comments'"><a href=""
                                ng-click="showComments(game.id)"> <span
                                id="SharedGameNum{{game.id}}"
                                class="glyphicon glyphicon-comment"></span>
                                </a>
                              </td>
                              <td title="'Applier'"><a href="#">{{game.applierUsername}}</a></td>
                            </tr>
                          </table>
                        </div>
                        <div id="borrowedGames" class="tab-pane fade">
                          <table ng-table="" class="table table-condensed table-hover">
                            <tr ng-repeat="game in allBorrowedGames">
                              <td title="'Name'"><a
                                href="#gameUserDetails/{{game.gameId}}"
                                ng-click="myFunc(game.gameId)"> {{game.gameUserName}}</a></td>
                              <td title="'Category'">{{game.gameUserCategory}}</td>
                              <td title="'Owner'"><a href="#">{{game.username}}</a></td>
                            </tr>
                          </table>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!-- End of MyGames Widget -->
          <div id="Friends" class="tab-pane fade">
            <!-- Friends Widget -->
            <div id="friends_div">
              <div ng-controller="friendsCtrl">
                <div class="make-offer-to-take-part-in-tournament" ng-show="tournament">
                  <div class="proba-message-header">
                    Make offer to take part in tournament
                    <a ng-click="tournament = false"><img class="close"
                      style="margin-top: -6px; margin-right: 0px;"
                      src="resources/ico/close2.png" /></a>
                  </div>
                  <div>{{myfriendTournament}}</div>
                  <div ng-repeat="tournament in allTournaments">
                    <div>tournamentName: {{tournament.tournamentName}}</div>
                    <div>countOfParticipants: {{tournament.countOfParticipants}}</div>
                    <div>userCreatorName: {{tournament.userCreatorName}}</div>
                    <div>dateOfTournament: {{tournament.dateOfTournament | date : format : timezone}}</div>
                    <div>country: {{tournament.country}}</div>
                    <div>city: {{tournament.city}}</div>
                  </div>
                </div>
                <!--Widget to find users-->
                <div class="search-result" ng-show="click">
                  <div class="header-search">
                    <div class="find-friend-heder">Find your friends in our
                      Application
                    </div>
                    <a ng-click="click = false"><img class="close"
                      style="margin-top: -55px; margin-right: 0px;"
                      src="resources/ico/close2.png" /></a>
                  </div>
                  <div class="content">
                    <div ng-repeat="user in allUsers">
                      <div class="proba">
                        <div class="person">
                          <div class="over-ava">
                            <a href="" type="button"><img class="ava"
                              src="resources/images/default-avatar.jpg" /></a>
                          </div>
                          <div class="name">{{ user.firstName }} {{
                            user.lastName}}
                          </div>
                          <div class="over-add-remove">
                            <a href="" type="button"
                              ng-click="addUserToFriend(user.id)"><img
                              class="add-remove" src="resources/ico/add_user.png" /></a>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <!--End Widget to find users-->
                <!--Widget to write a message-->
                <div class="proba-message" ng-show="jmessage">
                  <div class="proba-message-header">
                    {{myfriend}} <a ng-click="jmessage = false"><img
                      class="close" src="resources/ico/close2.png" /></a>
                  </div>
                  <div class="proba-message-body" id="messages" jq-scroll>
                    <div class="message-state" ng-repeat="message in messages"
                      ng-class="{myStyle: !message.statusOfReading && message.userSender.username == currentFriend}"
                      ng-mouseenter="readMessage(message)">
                      <div>
                        <strong>{{message.userSender.firstName}}
                        {{message.userSender.lastName}}:</strong>
                      </div>
                      <div>{{message.message}}</div>
                    </div>
                  </div>
                  <div class="proba-message-bootom">
                    <textarea name="text" placeholder="write a message"
                      ng-model="newMessage" ng-enter="sendMessage(newMessage)"></textarea>
                  </div>
                </div>
                <!--End widget to write a message-->
                <div class="users-offer" ng-show="showRequest">
                  <div class="tabs">
                    <ul class="tab-links">
                      <li class="active"><a href="#tab1">Fiend request</a></li>
                      <li><a href="#tab2">Fiend responce</a></li>
                    </ul>
                    <div class="my-tab-content">
                      <div id="tab1" class="tab active">
                        <div ng-repeat="user in allOfferedUsers">
                          <div class="proba">
                            <div class="person">
                              <a href="" type="button"><img class="ava"
                                src="resources/images/default-avatar.jpg" /></a>
                              <div class="name">{{ user.firstName }}
                                {{user.lastName }}
                              </div>
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
                      <div id="tab2" class="tab">
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
                </div>
                <div class="global">
                  <div class="main">
                    <div id="header">
                      <div class="name-main-part">Friends {{friends.length}}</div>
                      <div class="overBell">
                        <a href="" type="button" data-toggle="modal"
                          ng-click="showRequest = !showRequest; click = false; tournament = false">
                          <div class="count" ng-hide="count < 1">{{count}}</div>
                          <img
                            class="bell" src="resources/ico/bell.png" />
                        </a>
                      </div>
                      <!--<img class="search" src="resources/ico/search.png" />-->
                      <input class="form-control" type="text"
                        placeholder="Find new friends" ng-model="name"
                        ng-keyup="findAllUsers()"
                        ng-click="click = true; showRequest = false; tournament = false">
                    </div>
                    <div class="persons">
                      <div ng-repeat="friend in friends">
                        <div class="person">
                          <div class="over-ava">
                            <a href="#edit/{{friend.username}}" type="button"> <img
                              class="ava" src="resources/images/default-avatar.jpg" /></a>
                          </div>
                          <div class="name">{{ friend.firstName }} {{
                            friend.lastName}}
                          </div>
                          <div class="over-mes">
                            <a href="" type="button"
                              ng-click="$parent.jmessage = true; 
                              $parent.myfriend=friend.firstName +' ' + friend.lastName; 
                              setFriendName(friend.username)">
                              <img class="message" src="resources/ico/message.png" />
                              <div class="count-of-messages"
                                ng-show="allNotReadMessagesByFriend[friends.indexOf(friend)] > 0 ">{{allNotReadMessagesByFriend[friends.indexOf(friend)]}}
                              </div>
                            </a>
                          </div>
                            <!--<a href=""><img class="delete-user" src="resources/ico/delete_user.png"/></a>-->
                            <a href="" type="submit" uib-popover="sdfffff" popover-title="Do you realy want to delete {{ friend.firstName }} {{
                            friend.lastName}}"><img class="delete-user" src="resources/ico/delete_user.png"/></a>
                          <!--<a href="" type="button" ng-click="$parent.tournament = true; click = false; showRequest = false;  
                            $parent.myfriendTournament=friend.firstName +' ' + friend.lastName; 
                            setFriendNameForTournament(friend.username)">
                          <img class="iconChampionship"
                            src="resources/ico/championship.png" />
                          </a>-->
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!-- End of Friends Widget -->
          </div>
        </div>
      </div>
      <!-- End of Side Widget -->
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