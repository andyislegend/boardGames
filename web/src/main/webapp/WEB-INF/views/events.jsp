<!DOCTYPE html>

<html ng-app="eventListApp">
<head>
	<meta charset="utf-8" />
	<title>Events</title>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
	<link rel="stylesheet" href="css/fonts.css" />
	<link rel="stylesheet" href="css/main.css" />
	<link rel="stylesheet" href="css/events.css" />
	<link rel="stylesheet" href="css/media.css" />
	<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
	<script src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
	<script src="js/common.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>


	
	
	
	<script>
	// Model
        var model = {
            user: "user1",
            events: [{ id:01, name: "Everybody sleeps but mafia members wake up", user:"user1", img_src: "images/mafiaImg.jpg", game_name: "Mafia", date_num:"07", date_month:"April", href:"#"},
                { id:02, name: "Game of kings", user:"user2", img_src: "images/chessImg.jpg", game_name: "Chess", date_num:"09", date_month:"April", href:"#"},
                { id:03, name: "One of the best games of the Western world", user:"user1", img_src: "images/scrabbleImg.jpg", game_name: "Skrabble", date_num:"27", date_month:"April", href:"#"},
                { id:04, name: "Come with your kids: fun for everybody", user:"user1", img_src: "images/tic-tac-toeImg.jpg", game_name: "Tic Tac Toe", date_num:"02", date_month:"May", href:"#"},
                { id:05, name: "If America was a game of Monopoly", user:"user1", img_src: "images/monopolyImg.jpg", game_name: "Monopoly", date_num:"07", date_month:"May", href:"#"},
				{ id:06, name: "Loving and Linkin", user:"user1", img_src: "images/dominosImg.jpg", game_name: "Dominos", date_num:"17", date_month:"May", href:"#"},
				{ id:07, name: "When your body is the canvas", user:"user1", img_src: "images/pictionaryImg.jpg", game_name: "Pictionary", date_num:"21", date_month:"May", href:"#"},
				{ id:08, name: "This is simply a wonderful visual feast", user:"user1", img_src: "images/utopiaImg.jpg", game_name: "Utopia", date_num:"02", date_month:"June", href:"#"}]
        };
		
	 // Module
        var eventListApp = angular.module("eventListApp", []);

     // Controller
        eventListApp.controller("eventListCtrl", function ($scope) {
            $scope.data = model;

            // button-click handler
            $scope.addNewEvent = function () {
                $scope.data.events.push({
					id:"10",
                    name: $scope.eventName,
					user: $scope.eventUser,
					img_src:"images/defaultImg.jpg",
                    game_name: $scope.eventGame,
					date_num:$scope.eventDateNum,
					date_month:$scope.eventDateMonth,
					
                });

                $scope.eventName = "1111";
				$scope.eventUser= "";
				$scope.eventGame ="";
				$scope.eventDateNum = "";
				$scope.eventDateMonth="";
				
            }        
		});
		




    </script>

</head>
<body ng-controller="eventListCtrl">

    <div class="top_header">
        <div class="header_topline">
            <div class="row">
                <div class="container">
                    <div class="col-md-12">
                        <div class="row">
                            <div class="logo">
                                <a href="#"><img src="images/rubic1.png"  alt="logo" height="30" /></a> 
                                   
                            </div>
                            <button class = "login_button hidden-md hidden-lg hidden-sm"><a href="#" ><i class="fa fa-user" aria-hidden="true"></i></a></button>
                            <div class="log_join">
                                
                                <a href="index1Copy" >Login</a> / 
                                <a href="#" >Join</a>
                            </div>
                            <div class="top_links" >
                                <a href="#">Championship</a> / 
                                <a href="#">Events</a> / 
                                <a href="#">Games</a>
                            </div>
                         </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
   
     
    <header class="section-header">
                 <h2 class="title">COMING EVENTS:{{data.events.length}}</h2>
            
    </header>

			<div class=" jcarousel-skin-tango">
			   <div class="jcarousel-container jcarousel-container-horizontal" style="display: block;">				
						<li class="jcarousel-item jcarousel-item-horizontal " >						 
						  <a ng-repeat="event in data.events" href={{event.href}} class="item">
							  <div id={{event.id}} style="display: none;"><img src={{event.img_src}} width="64" height="64" class="loader"></div>
							  <img width="245" height="245"  onLoad="$({{event.id}}).hide(); $(this).show();" src={{event.img_src}} alt={{event.name}}>
							  <span class="game_name">{{event.game_name}}</span>
							  <div class="name" style="top: 201px;">
								<div class="num">{{event.date_num}}<span>{{event.date_month}}</span></div>
								<div class="tema">{{event.name}}</div>
							  </div>
						   </a>						   
						</li>				
				  </div>
			   </div>
			</div>
			<div class="clear"></div>
			
	
					
   <div class=" jcarousel-skin-tango">
     <div class="title">Add event</div>    

<!-- <form name="addEvent" method="post" action="addEvent" modelAttribute="event">-->
	  
        <div class="field">
          Event: <input type="text" ng-model="eventName" size="100px" />
        </div>
		
        <div class="field">
        User: <input type="text" ng-model="eventUser" size="100px" />
        </div>
        
		<div class="field">
        Game type: <input type="text" ng-model="eventGame" size="100px" />
        </div>
		
        <div class="field">
        Date: <input type="text" ng-model="eventDateNum" size="100px" />
        </div>
		
        <div class="field">
        Month: <input type="text" ng-model="eventDateMonth" size="100px" />
        </div>
       
        <div class="field">
		<button class="btn btn-default" ng-click="addNewEvent()">Add Event</button>
        </div>
	</form> 
  </div>

		
</body>
</html>