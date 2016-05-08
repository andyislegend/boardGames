var app = angular.module("usersGameApp", ['ui.bootstrap']);

app.controller("allUsersGameCtrl", function ($scope, $http,$rootScope) {
	$rootScope.NN = 100;
	$scope.allGame = [];
    $http.get('getAllGamesCurUser').then(function (result) {
    	$scope.allGame = result.data;
    	for(var i = 0; i<$scope.allGame.length;i++){
        	$scope.isNewComments($scope.allGame[i].id);
        }
    	});
        
        $scope.showMe = false;
        $scope.myFunc = function (id) {
            $scope.games = [];
            $scope.showMe = !$scope.showMe;
            $http.get('gameUserDetail/'+id).then(function(result) {
            	$scope.games = result.data;
			});
        }
        
        $scope.isNewComments = function(id){
        	var countOfComments = 0;
        	$scope.userGame = [];
        	$http.get('getCountOfCommentsByGameId/'+id).then(function(result){
        		countOfComments = result.data;
        		$http.get('gameUserDetail/'+id).success(function(result) {				
            		$scope.userGame = result;           	
            		if(countOfComments>$scope.userGame.countOfComments){
            			$rootScope.NN = countOfComments;
                		document.getElementById("UserGameNum"+$scope.userGame.id).className = "glyphicon glyphicon-envelope";
                	}
            	});    	
        	}); 	
			
        	}
        
        
        $scope.deleteGame = function(id) {
    		$http.get('deleteUserGame').success(function (data) {    			
    	    });
    		 $scope.allGame.splice(id-1,1);
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
			    $scope.list.push(response.data); 		
			  }, function errorCallback(response) {			    
			  });
		 $scope.$parent.allGame.push(userGame);
		 $scope.$parent.allGame = {};
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
		$scope.editableFirstName = $scope.userProfile.firstName;
	    $scope.editableLastName = $scope.userProfile.lastName;
	    $scope.editableEmail = $scope.userProfile.email;
	    $scope.editableGender = $scope.userProfile.gender;
	    $scope.editableAge = $scope.userProfile.age;
	    $scope.editablePhoneNumber = $scope.userProfile.phoneNumber;
	});
	$scope.saveUser = function() {
		$http({
		    method: 'PUT',
		    url: 'updateUser',
		    data: $.param({
	            firstName: $scope.editableFirstName,
	            lastName: $scope.editableLastName,
	            username: $scope.editableUsername,
	            email: $scope.editableEmail,
	            gender: $scope.editableGender,
	            age : $scope.editableAge,
	            phoneNumber: $scope.editablePhoneNumber
	        }),
	        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
		})
		.success(function(result, status) {
			$scope.editProfileAnswer = result;
			$scope.userProfile.firstName = $scope.editableFirstName;
			$scope.userProfile.lastName = $scope.editableLastName;
			$scope.userProfile.username = $scope.editableUsername;
		    $scope.userProfile.email = $scope.editableEmail;
		    $scope.userProfile.gender = $scope.editableGender;
		    $scope.userProfile.age = $scope.editableAge;
		    $scope.userProfile.phoneNumber = $scope.editablePhoneNumber;
			$scope.editProfileMessage = false;
		})
		.error(function(result, status) {
			$scope.editProfileAnswer = result;
		})		
	}
	
	$scope.saveNewUserPassword = function() {
		$http({
		    method: 'PUT',
		    url: 'updateUserPassword',
		    data: $.param({
	            oldPassword: $scope.editableOldPassword,
	            newPassword: $scope.editableNewPassword,
	            confirmPassword: $scope.editableConfirmPassword,
	        }),
	        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
		})
		.success(function(result, status) {
			$scope.editPasswordAnswer = result;
			$scope.editPasswordMessage = false;
		})
		.error(function(data, status) {
			$scope.editPasswordAnswer = result;
		});		
	}
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


app.controller('getGamesGlobalController', function ($scope, $http,$rootScope) {

	$http({
		method : "GET",
		url : 'getAllGames'
	}).then(function mySucces(response) {
		$scope.gamesGlobal = response.data;
	}, function myError(response) {
		alert("Getting games general data error");
	});

	$scope.gameSelect = function(id, name, $event) {
		
		$scope.$on('settingRootRating', function (event, data) {
			 $scope.gameRatingDisplay = data;
		});
		
		$http({
			method: "GET",
			url : 'getGameDetails' + '/' + id
		}).then(function mySucces(response){
			$scope.gameDetail = response.data;
		}, function myError(response) {
			alert("Getting games general data error");
		});
		
		$http({
			method: "GET",
			url : 'getGameRatedByUser' + '/' + id
		}).then(function mySucces(response){
			$scope.gameRatingDisplay = response.data;
			$scope.currentGameId = id;
			
			$scope.$broadcast('sharingIdToDetailsModal', 
					{id:$scope.currentGameId, rating:$scope.gameRatingDisplay});
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


app.controller('getGameDetailedInfoController', function ($scope, $http,$rootScope) {
		
	$scope.ratingSliderChanged = function(){
		$scope.$emit('settingRootRating', $scope.gameRating);
	}
	

app.controller('getGameDetailedInfoController', function ($scope, $http) {

	$scope.$on('sharingIdToDetailsModal', function (event, data) {
		console.log('rating transferes(' + data.rating + ')');
		console.log('id transferes(' + data.id + ')');
		$scope.currentGameId = data.id;
		$scope.starRating = data.rating;
		$scope.hoverRating = 0;
		console.log('setting successfully');
	});
	
    $scope.ratingClick = function (param) {
    	console.log('mouseClick(' + param + ')');
    	$http({
    		method: "POST",
			url : 'calculateRatings' + '/' + $scope.currentGameId + '/' + param,
		}).then(function mySucces(response){
			$scope.$emit('settingRootRating', $scope.starRating);
		}, function myError(response) {
			alert("Saving rating error");
		});
    };

    $scope.ratingHover = function (param) {
        $scope.hoverRating = param;
    };

    $scope.ratingLeave = function (param) {
        $scope.hoverRating = param + '*';
    };
	
	//comment
	$scope.gameuserId = 0;
	$scope.isShowComment = false;	
	$scope.showComments = function(id) {
		
		$scope.gameuserId = id;
		$scope.isShowComment = !$scope.isShowComment
		
		$scope.commentForGame = [];
		
		$http.get('getCommentsForGame/'+id).then(function(result) {
			$scope.commentForGame = result.data;
		});
				
				if(document.getElementById("UserGameNum"+id).className === "glyphicon glyphicon-envelope"){
					console.log($rootScope.NN);
					$http.put("updateCountOfComment/"+id+"/"+$rootScope.NN).then(function(result) {						
					});
				document.getElementById("UserGameNum"+id).className = "glyphicon glyphicon-comment";}
				
		
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
				  url: 'newComment',
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

app.directive('starRating', function () {
    return {
        scope: {
            rating: '=',
            maxRating: '@',
            readOnly: '@',
            click: "&",
            mouseHover: "&",
            mouseLeave: "&"
        },
        restrict: 'EA',
        template:
            "<div style='display: inline-block; margin: 0px; padding: 0px; cursor:pointer;' " +
            		"ng-repeat='idx in maxRatings track by $index'> \
                    <img ng-src='{{((hoverValue + _rating) <= $index) " +
                    "&& \"http://www.codeproject.com/script/ratings/images/star-empty-lg.png\" " +
                    "|| \"http://www.codeproject.com/script/ratings/images/star-fill-lg.png\"}}' \
                    ng-Click='isolatedClick($index + 1)' \
                    ng-mouseenter='isolatedMouseHover($index + 1)' \
                    ng-mouseleave='isolatedMouseLeave($index + 1)'></img> \
            </div>",
        compile: function (element, attrs) {
            if (!attrs.maxRating || (Number(attrs.maxRating) <= 0)) {
                attrs.maxRating = '5';
            };
        },
        controller: function ($scope, $element, $attrs) {
            $scope.maxRatings = [];

            for (var i = 1; i <= $scope.maxRating; i++) {
                $scope.maxRatings.push({});
            };

            $scope._rating = $scope.rating;
			
			$scope.isolatedClick = function (param) {

				$scope.rating = $scope._rating = param;
				$scope.hoverValue = 0;
				$scope.click({
					param: param
				});
			};

			$scope.isolatedMouseHover = function (param) {

				$scope._rating = 0;
				$scope.hoverValue = param;
				$scope.mouseHover({
					param: param
				});
			};

			$scope.isolatedMouseLeave = function (param) {

				$scope._rating = $scope.rating;
				$scope.hoverValue = 0;
				$scope.mouseLeave({
					param: param
				});
			};
        }
    };
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