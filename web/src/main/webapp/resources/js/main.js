var app = angular.module("usersGameApp", ['ui.bootstrap']);

app.controller("allUsersGameCtrl", function ($scope, $http) {
	$scope.countsOfComments = 1;
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
			}).success(function(response) {
				console.log("fjsdbfhbdshfsdhds")
			    $scope.list.push(response.data); 
				
			  }, function errorCallback(response) {
			    
			  });
		 $scope.$parent.allGame.push(userGame);
		 
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
        if($scope.count == 1){
            $scope.NameOfModalWindow = 'modal';
        }
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
        if($scope.count == 1){
            $scope.NameOfModalWindow = 'modal';
        }
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
             $scope.userWhoYouSentOffering = data;
             for(var i = 0; i < $scope.allUsers.length; i++){
                if($scope.allUsers[i].id === id){
                    $scope.allUsers.splice(i, 1)
                };
            };
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
		$scope.getInfoAboutUserFunc = function(username) {
			$scope.oneUser
			$scope.showUser = !$scope.showUser;
			for (var i = 0; i < $scope.users.length; i++) {
				if ($scope.users[i].username === username) {
					$scope.oneUser = $scope.users[i];
					break;
				};
			};
			$http.get('allUsersGames?username=' + username).then(function(result) {
				$scope.games = result.data;
			});
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
		    $http.get('allUsersTournaments?username=' + username).then(function (result) {
		        $scope.tournaments = result.data;
		    });
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
			$http.get('getUsersAvatar?username=' + $scope.oneUser.username).then(function(result) {
				$scope.userAvatar = result.data;
			});
		};
	});
	$http.get('getAllCountries').then(function(result) {
		$scope.countries = result.data;	
		$scope.getCitiesByCountry = function(){
			var countryName = $('select[name=selectCountries]').val();
			$http.get('getAllCities?countryName=' + countryName).then(function(result) {
				$scope.cities = result.data;
			});
		};
	});
});

app.controller("getAllUsersWithNegativeRating", function($scope, $http) {
	$scope.usersWithNegRate = [];
	$http.get('getUsersWithNegativeRating').then(function(result) {
		$scope.usersWithNegRate = result.data;
		if ($scope.usersWithNegRate.length != 0) {
			$("#myModalBannedUsers").modal('show');
		        $scope.bannedUsers = ('These users have to be banned: ' + $scope.usersWithNegRate);        
		}
	});
});

app.controller("getAvatar", function($scope, $http) {
	$http.get('getAvatar').then(function(result) {
		$scope.avatar = result.data;
	});
});

app.controller("editProfileCtrl", function($scope, $http) {
	$http.get('getProfile').then(function(result) {
		$scope.userProfile = result.data;
	});
	
	$scope.editorNameEnabled = false;
	$scope.enableNameEditor = function() {
	    $scope.editorNameEnabled = true;
	    $scope.editableFirstName = $scope.userProfile.firstName;
	    $scope.editableLastName = $scope.userProfile.lastName;
	};
	$scope.disableNameEditor = function() {
	    $scope.editorNameEnabled = false;
	};
	$scope.saveName = function() {
		$http({
		    method: 'PUT',
		    url: 'updateUserFirstLastName',
		    data: $.param({
	            firstName: $scope.editableFirstName,
	            lastName: $scope.editableLastName,
	        }),
	        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
		})
		$scope.userProfile.firstName = $scope.editableFirstName;
		$scope.userProfile.lastName = $scope.editableLastName;
		$scope.editorNameEnabled = false;
	}
	
	 
	$scope.editorUsernameEnabled = false;
	$scope.enableUsernameEditor = function() {
	    $scope.editorUsernameEnabled = true;
	    $scope.editableUsername = $scope.userProfile.username;
	};
	$scope.disableUsernameEditor = function() {
	    $scope.editorUsernameEnabled = false;
	};
	$scope.saveUsername = function() {
		$http({
		    method: 'PUT',
		    url: 'updateUsername',
		    data: $.param({
	            username: $scope.editableUsername
	        }),
	        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
		})
		$scope.userProfile.username = $scope.editableUsername;
		$scope.editorUsernameEnabled = false;
	}
	  
	$scope.editorEmailEnabled = false;
	$scope.enableEmailEditor = function() {
	    $scope.editorEmailEnabled = true;
	    $scope.editableEmail = $scope.userProfile.email;
	};
	$scope.disableEmailEditor = function() {
	    $scope.editorEmailEnabled = false;
	};
	
	$scope.editorPasswordEnabled = false;
	$scope.enablePasswordEditor = function() {
	    $scope.editorPasswordEnabled = true;
	    $scope.editablePassword = "Type new password";
	};
	$scope.disablePasswordEditor = function() {
	    $scope.editorPasswordEnabled = false;
	};
	
	$scope.editorGenderEnabled = false;
	$scope.enableGenderEditor = function() {
	    $scope.editorGenderEnabled = true;
	};
	$scope.disableGenderEditor = function() {
	    $scope.editorGenderEnabled = false;
	};
	
	$scope.editorAgeEnabled = false;
	$scope.enableAgeEditor = function() {
	    $scope.editorAgeEnabled = true;
	    $scope.editableAge = $scope.userProfile.age;
	};
	$scope.disableAgeEditor = function() {
	    $scope.editorAgeEnabled = false;
	};
	
	$scope.editorPhoneNumberEnabled = false;
	$scope.enablePhoneNumberEditor = function() {
	    $scope.editorPhoneNumberEnabled = true;
	    $scope.editablePhoneNumber = $scope.userProfile.phoneNumber;
	};
	$scope.disablePhoneNumberEditor = function() {
	    $scope.editorPhoneNumberEnabled = false;
	};
	
});


app.controller("eventsVisibleController", function($scope) {
	$scope.eventsFade = false;
	$scope.tournamentsFade = false;
	$scope.gamesFade = true;
	$scope.usersFade = false;
	$scope.editProfileFade = false;
	$scope.onlyUsers = function () {
		$scope.eventsFade = false;
		$scope.tournamentsFade = false;
		$scope.gamesFade = false;
		$scope.usersFade = true;
		$scope.editProfileFade = false;
	};
	$scope.onlyGames = function () {
		$scope.eventsFade = false;
		$scope.tournamentsFade = false;
		$scope.gamesFade = true;
		$scope.usersFade = false;
		$scope.editProfileFade = false;
	};
	$scope.onlyEvents = function () {
		$scope.eventsFade = true;
		$scope.tournamentsFade = false;
		$scope.gamesFade = false;
		$scope.usersFade = false;
		$scope.editProfileFade = false;
	};
	$scope.onlyTournaments = function () {
		$scope.eventsFade = false;
		$scope.tournamentsFade = true;
		$scope.gamesFade = false;
		$scope.usersFade = false;
		$scope.editProfileFade = false;
	};
	$scope.onlyEditProfile = function () {
		$scope.eventsFade = false;
		$scope.tournamentsFade = false;
		$scope.gamesFade = false;
		$scope.usersFade = false;
		$scope.editProfileFade = true;
	};
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
		$rootScope.gameRatingRoot = $scope.gameRating
		
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
			$rootScope.gameRatingRoot = response.data;
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
		$rootScope.gameRatingRoot = $scope.gameRating;
	}
	
	$scope.ratingSaved = function() {
		
		$http({
			method: "POST",
			url : 'calculateRatings' + '/' + $rootScope.currentGameRootId + '/' + $scope.gameRating,
		}).then(function mySucces(response){
			$rootScope.gameRatingRoot = $scope.gameRating;
		}, function myError(response) {
			alert("Saving rating error");
		});
	}
	
	//comment
	$scope.gameuserId = 0;
	$scope.isShowComment = false;	
	$scope.comments = [];
	$http.get('comment').then(function(result) {
		$scope.comments = result.data;
	},function Error(result) {
		$scope.comments = [{"text":"jdsfsjd"}];
	})
	
	$scope.showComments = function(id) {
		$scope.gameuserId = id;
		$scope.isShowComment = !$scope.isShowComment
		$scope.commentForGame = [];
		for(var i = 0; i<$scope.comments.length;i++){
			if($scope.comments[i].gameID === id){
				console.log(id);
				$scope.commentForGame.push($scope.comments[i]);
			}
		}	
	}	
	
	$scope.list = [];
	$scope.submit = function () {
		var comment  = {
				"gameID" : ''+$scope.gameuserId,
				"commentText" : $scope.comment,
				"username":"",
				"date":new Date()
			 };
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
			 $scope.commentForGame.push(comment);
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
                if($scope.tournaments == data){
                    alert("You've already join this tournament");
                }
                $scope.tournaments = data;

            });

    }

});

app.controller("CtreateNewTournament",function($scope,$http){

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
        console.log(tournament);
        $http.post("/addTournament", tournament)
            .success(function (data) {
                console.log(data);
                $scope.$parent.tournaments=data;
            });

    }
});