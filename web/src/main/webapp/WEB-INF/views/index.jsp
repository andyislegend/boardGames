<!DOCTYPE html>
<html>
   <head>
      <link rel="stylesheet" href="resources/bower_components/bootstrap/dist/css/bootstrap.min.css"/>
      <link href="resources/bower_components/bootstrap/dist/css/bootstrap.css" rel="stylesheet"/>
      <link rel="stylesheet" href="resources/css/mainStyleSheet.css"/>
      <script src = "resources/bower_components/angular/angular.js"></script>
      <script src = "resources/bower_components/angular/angular.min.js"></script>
      
   </head>
   <body>
      <nav class="navbar navbar-default">
         <div class="container-fluid">
            <div class="navbar-header">
               <a class="navbar-brand" href="#">Bla Bla Game</a>
            </div>
            <ul class="nav navbar-nav">
               <li><a href="#">Championship</a></li>
               <li><a href="#">All Games</a></li>
               <li><a href="#">Events</a></li>
               <li><a href="users">Users</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
               <form class="navbar-form navbar-left" role="search">
                  <div class="form-group">
                     <input type="text" class="form-control" placeholder="Search Game">
                  </div>
                  <button type="submit" class="btn btn-success">Search</button>
               </form>
               <li><a href="#"><span class="glyphicon glyphicon-user"></span> User</a></li>
               <li><a href="#"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
                  <span class="badge">1</span></a>
               </li>
               <li><a href="login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            </ul>
         </div>
      </nav>
     
        <div class="container-fluid" ng-app="usersGameApp">
        <div class="row">
            <div class="col-sm-3" ng-controller="allUsersGameCtrl">
                <div class="well col-md-offset-0">
                    
                    <h3 class="text-center">My Games</h3>
                    <br/>
                    <ul class="list-group" ng-repeat="x in allGames">
                        <li class="list-group-item">{{ x.name }}</li>
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
                        <th>Actions</th>
                     </tr>
                  </thead>
                  <tbody>
                     <tr ng-repeat="x in gamesGlobal">
                        <td>{{ x.Name }}</td>
                        <td>{{ x.Category }}</td>
                        <td>{{ x.Description }}</td>
                        <td>{{ x.MinPlayers }}</td>
                        <td>{{ x.MaxPlayers }}</td>
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
                                <td><a href="#"><img src="resources/ico/add_user.png" width="25"></a></td>
                                <td><a href="#"><img src="resources/ico/bell.png" width="25"></a></td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr ng-repeat="friend in friends">
                                <td> <img src="resources/images/{{friend.img}}.jpg"/></td>
                                <td>{{ friend.name }}</td>
                                <td>{{ friend.lastName }}</td>
                                <td><a href="#"><img src="resources/ico/messages.png" width="25"></a></td>
                                <td><a href="#"><img src="resources/ico/trophy.png" width="25"></a></td>

                            </tr>
                        </tbody>
                    </table>  
                </div>
            </div>
         </div>
      </div>
       <footer class = "panel-footer" ></footer>
       <script src = "resources/js/showAllGamesCurrentUser.js"></script>
   </body>
</html>