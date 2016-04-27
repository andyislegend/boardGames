var app = angular.module("usersGameApp", ['ui.bootstrap']);

app.controller("allUsersGameCtrl", function ($scope, $http) {
	$scope.allGame = [];
    $http.get('getAllGamesCurUser').then(function (result) {
        $scope.allGame = result.data;});
        $scope.showMe = false;
        $scope.myFunc = function (id) {
            $scope.games = [];
            $scope.showMe = !$scope.showMe;
            for (var i = 0; i < $scope.allGame.length; i++) {
                if ($scope.allGame[i].id === id) {
                    $scope.games[0] = $scope.allGame[i];
                    break;
                }
            }
        }
});

app.controller("CreateGameCtrl", function($scope, $http) {
	$scope.showText = false;
	$scope.categories = [];
	$scope.showForm = function() {
		$scope.showText = !$scope.showText;
		};
	$http.get('getAllCategories').then(function(result ){
		$scope.categories = result.data;
	});
		$scope.list = [];
	$scope.submit = function() {
		 var userGame  = {
			"name" : $scope.name,
			"category" : $scope.category,
			"yearOfProduction" : $scope.year,
			"edition" : $scope.edition,
			"description" : $scope.description,
			"rules" : $scope.rules,
			"maxPlayers" : $scope.maxPlayers,
			"minPlayers" : $scope.minPlayers
		 };

		 $http({
			  method: 'POST',
			  url: '/NewGame',
			  headers: {
				   'Content-Type': 'application/json'
				 },
			  data:userGame
			}).then(function successCallback(response) {
			    $scope.list.push(response.data);
			  }, function errorCallback(response) {
			    
			  });
	};
});

app.controller("eventListCtrl", function($scope, $http) {
	  $scope.showText = false;
	  $scope.showForm = function() {
	        $scope.showText = !$scope.showText;
	    };
	
    $scope.events = [];
    $http({
        method: "GET",
        url: 'eventspage'
    }).then(function mySucces(response) {
        $scope.events = response.data;

    });

    $scope.addEvent = function() {
        var newEvent = {
            "name": $scope.input_name,
            "description": $scope.input_description,
            "date": $scope.input_date,
            "place": $scope.input_place,
            "game": $scope.input_game,
            "imgsrc": "resources/images/defaultImg.jpg"
        };



        var response = $http.post('addEvent', newEvent);
        response.success(function() {
            $scope.events.push(newEvent);

            $scope.input_name = "";
            $scope.input_description = "";
            $scope.input_place = "";
            $scope.input_game = "";
            $scope.input_date = new Date();

        });

    }

});
app.controller("friendsCtrl", function($scope, friendService, $http) {
    $scope.users;
	 friendService.getAllFriends().success(function(data) {
		$scope.friends = data;
	}).error(function(error) {
		console.log(error);
	})
    
    friendService.getCount().success(function(data){
         $scope.count = data;
     }).error(function(error){
         console.log(error)
     });
    
    friendService.getAllOfferedUsers().success(function(data) {
        $scope.allOfferedUsers = data;
	}).error(function(error) {
		console.log(error);
	});
    
     $scope.add = function(id){
        var userId = id;
          $scope.count =  $scope.count-1;
        $http.post('addUserToFriend', userId).success(function(data){
            var user = data;
            for(var i = 0; i < $scope.allOfferedUsers.length; i++){
                if($scope.allOfferedUsers[i].id === user.id){
                    $scope.friends.push($scope.allOfferedUsers[i]);
                    $scope.allOfferedUsers.splice(i, 1)
                };
            };
        }).error(function(error){
            console.log(error);
        });
    };

    $scope.rejected = function(id){
        var userId = id;
         $scope.count =  $scope.count-1;
        $http.post('rejectedUserToFriend', userId).success(function(data){
             var user = data;
            for(var i = 0; i < $scope.allOfferedUsers.length; i++){
                if($scope.allOfferedUsers[i].id === user.id){
                    $scope.allOfferedUsers.splice(i, 1)
                };
            };
        }).error(function(error){
            console.log(error);
        });
    };

   $scope.findAllUsers = function(){
        $http.post('findAllUsers/' + $scope.name, $scope.name).success(function(data){
            $scope.allUsers = data;
        }).error(function(error){
    });
   };
    $scope.addUserToFriend = function(id){
      console.log(id);
         $http.post('addOfferToFriendship/', id).success(function(data){
             $scope.answer = "Done";
             console.log(data);
         }).error(function(error){
             console.log(error);
         });
    };
});

/*users Angular controller -- start*/
app.controller("getAllUsersCtrl", function($scope, $http) {
	$scope.users = [];
	$http.get('users').then(function(result) {
		$scope.users = result.data;
		$scope.showUser = false;
		$scope.getInfoAboutUserFunc = function(id) {
			$scope.showUser = !$scope.showUser;
			for (var i = 0; i < $scope.users.length; i++) {
				if ($scope.users[i].id === id) {
					$scope.oneUser = $scope.users[i];
					break;
				};
			};
		};
	});
});

app.controller("getAllUsersWithNegativeRating", function($scope, $http, $window) {
	$scope.usersWithNegRate = [];
	$http.get('getUsersWithNegativeRating').then(function(result) {
		$scope.usersWithNegRate = result.data;
		if ($scope.usersWithNegRate.length != 0) {
			$("#myModalBannedUsers").modal('show');
		        $scope.bannedUsers = ('These users have to be banned: ' + $scope.usersWithNegRate);        
		}
	});
});

app.directive('fallbackSrc', function () {
	  var fallbackSrc = {
	    link: function postLink(scope, iElement, iAttrs) {
	      iElement.bind('error', function() {
	        angular.element(this).attr("src", iAttrs.fallbackSrc);
	      });
	    }
	   }
	   return fallbackSrc;
});

app.controller("getAllUsersGames", function($scope, $http) {
	$scope.showUsersGames = false;
	$scope.getInfoAboutUserGames = function(userName) {
		$scope.showUsersGames = !$scope.showUsersGames;
		$http.get('allUsersGames?userName=' + userName).then(function(result) {
			$scope.games = result.data;
		});
	};
    $scope.showModal = false;
    $scope.getInfoAboutGame = function(id){
        $scope.showModal = !$scope.showModal;
        for (var i = 0; i < $scope.games.length; i++) {
			if ($scope.games[i].id === id) {
				$scope.oneGame = $scope.games[i];
				break;
			};
		};
    };
});

app.controller("getAllUsersTournaments", function ($scope, $http) {
    $scope.showUsersTournaments = false;
    $scope.allUsersTournaments = function (username) {
        $scope.showUsersTournaments = !$scope.showUsersTournaments;
        $http.get('allUsersTournaments?username=' + username).then(function (result) {
            $scope.tournaments = result.data;
        });
    };
    $scope.showModal1 = false;
    $scope.getInfoAboutTournament = function(tournamentId){
        $scope.showModal1 = !$scope.showModal1;
        for (var i = 0; i < $scope.tournaments.length; i++) {
			if ($scope.tournaments[i].tournamentId === tournamentId) {
				$scope.oneTournament = $scope.tournaments[i];
				break;
			};
		};
    };
});

app.controller("getAvatar", function($scope, $http) {
	$http.get('getAvatar').then(function(result) {
		$scope.avatar = result.data;
	});
});

/*users Angular controller -- end*/


app.controller('getGamesGlobalController', function ($scope, $rootScope, $http) {

	$http({
		method : "GET",
		url : 'getAllGames'
	}).then(function mySucces(response) {
		$scope.gamesGlobal = response.data;
	}, function myError(response) {
		alert("Getting games general data error");
	});

	$scope.gameSelect = function(id, name, $event) {

		$scope.currentGameId = id;
		$rootScope.currentGameRootId = $scope.currentGameId;
		
		$http({
			method: "GET",
			url : 'getGameDetails' + '/' + id
		}).then(function mySucces(response){
			$scope.gameDetail = response.data;
			$scope.gameRating = $scope.gameDetail.rating;
		}, function myError(response) {
			alert("Getting games general data error");
		});

		$http({
			method: "GET",
			url : 'getGameRatedByUser' + '/' + id
		}).then(function mySucces(response){
			$scope.gameRating = response.data;
		}, function myError(response) {
			alert("Getting isRated game error");
		});
		
		$http({
			method: "GET",
			url : 'getUserGamesOfGame' + '/' + name
		}).then(function mySucces(response){
			$scope.userGamesOfGame = response.data;
		}, function myError(response) {
			alert("Getting games userGames of game error");
		});
	}
	
});

app.controller('getGameDetailedInfoController', function ($scope, $rootScope, $http) {
	
	$scope.ratingSliderChanged = function(){
		if ($scope.gameRating <= 15)
			$scope.gameRatingText = "Bad as hell";
		else if ($scope.gameRating > 15 && $scope.gameRating <= 30)
			$scope.gameRatingText = "Actually bad";
		else if ($scope.gameRating > 30 && $scope.gameRating <= 45)
			$scope.gameRatingText = "Averege";
		else if ($scope.gameRating > 45 && $scope.gameRating <= 60)
			$scope.gameRatingText = "Pretty good";
		else if ($scope.gameRating > 60 && $scope.gameRating <= 75)
			$scope.gameRatingText = "Good stuff";
		else if ($scope.gameRating > 75 && $scope.gameRating <= 90)
			$scope.gameRatingText = "Exelent!";
		else if ($scope.gameRating > 90 && $scope.gameRating <= 100)
			$scope.gameRatingText = "Legendary";
	}
	
	$scope.ratingSaved = function() {
		
		$http({
			method: "POST",
			url : 'calculateRatings' + '/' + $rootScope.currentGameRootId + '/' + $scope.gameRating,
		}).then(function mySucces(response){
			
		}, function myError(response) {
			alert("Saving rating error");
		});
	}
	
	//comment
	$scope.gameuserId = 0;
	$scope.isShowComment = false;
		
	$scope.showComments = function(id) {
		$scope.gameuserId = id;
		$scope.isShowComment = !$scope.isShowComment
		
		return $http.get('comment/'+id).then(
		function(response) {
			 response.data;
		},function(errResponse){
			console.log("Error sending comment id");
		}		
		)	
	}
	
	$http.get('comment/'+$scope.gameuserId, $scope.gameuserId).success(function(result) {
		$scope.comments = result.data;
	})
	
	$scope.list = [];
	$scope.submit = function () {
		var comment  = {
				"gameID" : ''+$scope.gameuserId,
				"commentText" : $scope.comment
			 };
		console.log(comment.gameID);
		console.log(comment.commentText);
			 $http({
				  method: 'POST',
				  url: '/NewComment',
				  headers: {
					   'Content-Type': 'application/json'
					 },
				  data:comment
				}).then(function successCallback(response) {
				    $scope.list.push(response.data);
				  }, function errorCallback(response) {

				  });
	}
});

app.controller("showAllTournaments", function ($scope, $http) {
    $http.get('/tournaments').success(function (data) {
        $scope.tournaments = data;
    });

    $http({
        method : "GET",
        url : 'getAllGames'
    }).then(function mySucces(response) {
        $scope.games = response.data;
    }, function myError(response) {
        alert("Getting games general data error");
    });

    $scope.JoinTournament = function (elem) {
        var idTournament = elem;
        console.log(idTournament);
        $http.post("/joinTournament", idTournament)
            .success(function (data) {
                if($scope.tournaments === data){
                    alert("You've already join this tournament");
                }
                $scope.tournaments = data;

            });

    }
	$scope.createTournament = function () {
		var tournament = {
			"tournamentName": $scope.tournamentName,
			"rating": $scope.requiredRating,
			"maxParticipants": $scope.maxParticipants,
			"date": $scope.date,
			"gameName": $scope.selectedGame,
			"country": $scope.countryTournament,
			"city": $scope.cityTournament,
			"addition":$scope.additionTournament
		};

		$http.post("/addTournament", tournament)
			.success(function (data) {
                console.log(data);
				$scope.tournaments = data;
			});

	}
});
