<!DOCTYPE html>
<html>
   <head>
      <link rel="stylesheet" href="resources/bower_components/bootstrap/dist/css/bootstrap.min.css"/>
      <link href="resources/bower_components/bootstrap/dist/css/bootstrap.css" rel="stylesheet"/>
      <link rel="stylesheet" href="resources/css/mainStyleSheet.css"/>
      
      <script type="text/javascript" src="resources/bower_components/angular/angular.js"></script>
      <script type="text/javascript" src="resources/bower_components/jquery/dist/jquery.js"></script>
      <script type="text/javascript" src="resources/bower_components/jquery/dist/jquery.min.js"></script>
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
               <li><a href="login"><span class="glyphicon glyphicon-log-in"></span> GET STARTED </a></li>
            </ul>
         </div>
      </nav>
   </body>
</html>