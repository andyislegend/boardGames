var homeApp = angular.module('homeApp', [ 'ngRoute', 'ui.bootstrap', 'ngTable', 'ng.q']);

homeApp.config(function($routeProvider) {
	$routeProvider

	.when('/statistics', {
		templateUrl : 'resources/pages/home-statistics.html',
	// controller : "...Ctrl"
	})

	.when('/allGames', {
		templateUrl : 'resources/pages/home-allGames.html',
		controller : 'getGamesGlobalController'
	})

	.when('/gameSelect/:id/:name', {
		templateUrl : 'resources/pages/home-gameDetails.html',
		controller : 'gameSelectController'
	})

	.when('/events', {
		templateUrl : 'resources/pages/home-events.html',
	// controller : '...Ctrl'
	})

	.when('/edit', {
		templateUrl : 'resources/pages/home-editUser.html',
		controller : 'editProfileCtrl'
	})

	.when('/tournaments', {
		templateUrl : 'resources/pages/home-tournaments.html',
	// controller : '...Ctrl'
	})

	.when('/users', {
		templateUrl : 'resources/pages/home-users.html',
		controller : 'getAllUsersCtrl'
	})
	.when('/search/:word', {
		templateUrl : 'resources/pages/home-GlobalSearch.html',
		controller : 'search'
	})
	.when('/gameUserDetails/:id', {
		templateUrl : 'resources/pages/home-gameUserDetails.html',
		controller : 'getGameDetailedInfoController'
	})

	.otherwise({
		redirectTo : '/statistics'
	});

});

homeApp.controller("getAvatar", function($scope, $http) {
	$http.get('getAvatar').then(function(result) {
		$scope.avatar = result.data;
	});
});
homeApp.controller('getSearchWordCTRL', function($scope, $rootScope) {
	$scope.searchWord = {id:'Monopf'};
	$scope.submit = function(){
		
		$rootScope.n = 'n';
	}
})
homeApp.controller('search', function($scope, $rootScope){

	$scope.searchWord = {id:'f'+document.getElementById("search-box").value}; 
	
});

homeApp.controller('GlobalSearchCTRL', function($scope, $http, $routeParams, ngTableParams) {
	
	$scope.searchWord = $scope.searchWord
		$http.get('searchBy/'+$routeParams.word).then(function(response) {			
			$scope.searchResult = response;
			$scope.tournaments = $scope.searchResult.data.tournaments;
			$scope.games = $scope.searchResult.data.gameUsers;
			$scope.events = $scope.earchResult.data.events;
			
			$scope.usersTable = new ngTableParams({
		        page: 1,
		        count: 10
		    }, {
		        total: $scope.tournaments.length+$scope.games.length+$scope.events.length, 
		        getData: function ($defer, params) {
		            $scope.data = $scope.tournaments.slice((params.page() - 1) * params.count(), params.page() * params.count());
		            $defer.resolve($scope.data);
		        }
		    });
			
		}) ; 
	
	
	
});

homeApp.controller("allUsersGameCtrl", function($scope, $http, $rootScope, $routeParams) {
	
	$scope.isYourGame = false;
	$scope.isntYourGame = false;
	$scope.isThereConfiramtion = false;
	
	$rootScope.NN = 100;
	$scope.allGame = [];
	$http.get('getAllGamesCurUser').then(function(result) {
		$scope.allGame = result.data;
		for (var i = 0; i < $scope.allGame.length; i++) {
			$scope.isNewComments($scope.allGame[i].id);
		}
	});

	$http.get('gameUserDetail/' + $routeParams.id).then(
		function(result) {
			$scope.games = result.data;
			$http({
				method : "GET",
				url : 'checkIfGameBelongsToUser' + '/' + $scope.games.id
			}).then(function mySucces(response) {
				alert(response.data);
				if (response.data === true && $scope.games.status === 'private') {
					$scope.isYourGame = true;
					$scope.isntYourGame = false;
					$scope.isThereConfiramtion = false;
				}
				else if (response.data === false && $scope.games.status === 'available') {
					$scope.isntYourGame = true;
					$scope.isYourGame = false;
					$scope.isThereConfiramtion = false;
				}
				else if (response.data === true && $scope.games.status === 'confirmation') {
					$scope.isntYourGame = false;
					$scope.isYourGame = false;
					$scope.isThereConfiramtion = true;
				}
				else {
					$scope.isYourGame = false;
					$scope.isntYourGame = false;
					$scope.isThereConfiramtion = false;
				}
			}, function myError(response) {
				alert("checking if game belongs to user error");
			});
			
	});
	
	$scope.showMe = false;
	$scope.myFunc = function(id) {
		$scope.games = [];
		$scope.showMe = !$scope.showMe;
		
	}
	
	$scope.makeGameUserAvailable = function(id) {
		$http({
			method : "PUT",
			url : 'makeGameUserAvailable' + '/' + id
		}).then(function mySucces(response) {
			alert("Making game available success!");
		}, function myError(response) {
			alert("Making game available failed");
		});
	}
	
	$scope.askOwnerToShare = function(id) {
		$http({
			method : "PUT",
			url : 'askGameUserOwnerToShare' + '/' + id
		}).then(function mySucces(response) {
			alert("Your request has been sent!)");
		}, function myError(response) {
			alert("Failed to send your request");
		});
	}
	
	$scope.acceptGameConfirmation = function(id) {
		$http({
			method : "PUT",
			url : 'acceptGameConfirmationRequest' + '/' + id
		}).then(function mySucces(response) {
			alert("Accept request success");
		}, function myError(response) {
			alert("Accept request failed");
		});
	}
	
	$scope.declineGameConfirmation = function(id) {
		$http({
			method : "PUT",
			url : 'declineGameConfirmationRequest' + '/' + id
		}).then(function mySucces(response) {
			alert("Decline request success");
		}, function myError(response) {
			alert("Decline request failed");
		});
	}

	$scope.isNewComments = function(id) {
		var countOfComments = 0;
		$scope.userGame = [];
		$http.get('getCountOfCommentsByGameId/' + id)
			.then(function(result) {
				countOfComments = result.data;
				$http.get('gameUserDetail/' + id)
					.success(function(result) {
						$scope.userGame = result;
						if (countOfComments > $scope.userGame.countOfComments) {
							$rootScope.NN = countOfComments;
							document.getElementById("UserGameNum" + 
									$scope.userGame.id).className = "glyphicon glyphicon-envelope";
						}
				});
		});
	}
	$scope.deleteGame = function(id) {
		$http.get('deleteUserGame').success(function(data) {
		});
		$scope.allGame.splice(id - 1, 1);
	}
});

homeApp.controller("CreateGameCtrl", function($scope, $http) {
	$scope.showText = false;
	$scope.categories = [];
	$scope.showForm = function() {
		$scope.showText = !$scope.showText;
	};
	$http.get('getAllCategories').then(function(result) {
		$scope.categories = result.data;
	});
	$scope.list = [];
	$scope.submit = function() {
		var userGame = {
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
			method : 'POST',
			url : '/NewGame',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : userGame
		}).success(function(response) {
			$scope.list.push(response.data);
		}, function errorCallback(response) {
		});
		$scope.$parent.allGame.push(userGame);
		$scope.$parent.allGame = {};
	};
});

homeApp.controller('getGamesGlobalController', function($scope, $http, $filter, ngTableParams) {

	$http({
		method : "GET",
		url : 'getAllGames'
	}).then(function mySucces(response) {
		$scope.gamesGlobal = response.data;
		$scope.$broadcast('sharingToInitGamesTable', $scope.gamesGlobal);	
	}, function myError(response) {
		alert("Getting games general data error");
	});
	
	$scope.$on('sharingToInitGamesTable',
		function(event, data) {
		$scope.allGamesTable = new ngTableParams({
		    page: 1,
		    count: 8
		 }, {
		     total: data.length, 
		     getData: function ($defer, params) {
		    	 
		    	 $scope.gamesGlobalDisplay = params.sorting() ? 
		      			$filter('orderBy')(data, params.orderBy()) 
		       			: data;
		       	 $scope.gamesGlobalDisplay = params.filter() ? 
		       			$filter('filter')($scope.gamesGlobalDisplay, params.filter()) 
		       			: $scope.gamesGlobalDisplay;
		         $scope.gamesGlobalDisplay = $scope.gamesGlobalDisplay.slice((params.page() - 1) 
		            	* params.count(), params.page() * params.count());
		         $defer.resolve($scope.gamesGlobalDisplay);
		     }
		 });
	});
});

homeApp.controller('gameSelectController', function($scope, $http, $routeParams) {
	var id = $routeParams.id;
	var name = $routeParams.name;
	$scope.$on('settingRootRating', function(event, data) {
		$scope.gameRatingDisplay = data;
	});

	$scope.$on('refreshingGameDetails', function(event, data) {
		$http({
			method : "GET",
			url : 'getGameDetails' + '/' + data
		}).then(function mySucces(response) {
			$scope.gameDetail = response.data;
			console.log("Response data: " + responseData);
		}, function myError(response) {
			alert("Getting games general data error");
		});
	});
	$scope.$emit('refreshingGameDetails', id);

	$http({
		method : "GET",
		url : 'getGameRatedByUser' + '/' + id
	}).then(function mySucces(response) {
		$scope.gameRatingDisplay = response.data;
		$scope.currentGameId = id;

		$scope.$broadcast('sharingIdToDetailsModal', {
			id : $scope.currentGameId,
			rating : $scope.gameRatingDisplay
		});

	}, function myError(response) {
		alert("Getting isRated game error");
	});

	$http({
		method : "GET",
		url : 'getUserGamesOfGame' + '/' + name
	}).then(function mySucces(response) {
		$scope.userGamesOfGame = response.data;
		$scope.$broadcast('sharingUserGamesOfGame', $scope.userGamesOfGame);
	}, function myError(response) {
		alert("Getting games userGames of game error");
	});
});	

homeApp.controller('getGameDetailedInfoController', function($scope, $http, $rootScope, $filter, ngTableParams) {
	
		$scope.$on('sharingIdToDetailsModal', function(event, data) {
			$scope.currentGameId = data.id;
			$scope.starRating = data.rating;
			$scope.hoverRating = 0;
		});   
		
		$scope.$on('sharingUserGamesOfGame',
			function(event, data) {
			$scope.userGamesOfGameTable = new ngTableParams({
				page: 1,
				count: 8
				}, {
				    total: data.length, 
				    getData: function ($defer, params) {	 
				    $scope.userGamesOfGameDisplay = params.sorting() ? 
				      	$filter('orderBy')(data, params.orderBy()) 
				        : data;
				    $scope.userGamesOfGameDisplay = params.filter() ? 
				       	$filter('filter')($scope.userGamesOfGameDisplay, params.filter()) 
				        : $scope.userGamesOfGameDisplay;
				    $scope.userGamesOfGameDisplay = $scope.userGamesOfGameDisplay.slice((params.page() - 1) 
				         * params.count(), params.page() * params.count());
				    $defer.resolve($scope.userGamesOfGameDisplay);
				}
			});
		});

		$scope.ratingClick = function(param) {
			
			console.log('mouseClick(' + param + ')');
			$http({
				method : "POST",
				url : 'calculateRatings' + '/'
					+ $scope.currentGameId + '/' + param,
			}).then(function mySucces(response) {
				$scope.$emit('settingRootRating',
				$scope.starRating);
				$scope.$emit('refreshingGameDetails',
				$scope.currentGameId);
			}, function myError(response) {
				alert("Saving rating error");
			});
		};

		$scope.ratingHover = function(param) {
			$scope.hoverRating = param;
		};

		$scope.ratingLeave = function(param) {
			$scope.hoverRating = param + '*';
		};

		// comment
		$scope.gameuserId = 0;
		$scope.isShowComment = false;
		$scope.showComments = function(id) {

			$scope.gameuserId = id;
			$scope.isShowComment = !$scope.isShowComment
			$scope.commentForGame = [];

			$http.get('getCommentsForGame/' + id).then(
					function(result) {
						$scope.commentForGame = result.data;
					});

			if (document.getElementById("UserGameNum" + id).className === "glyphicon glyphicon-envelope") {
				$http.put(
						"updateCountOfComment/" + id + "/"
								+ $rootScope.NN).then(
						function(result) {
						});
				document.getElementById("UserGameNum" + id).className = "glyphicon glyphicon-comment";
			}
		}

		$scope.list = [];
		$scope.submit = function() {
		var comment = {
			"gameID" : '' + $scope.gameuserId,
			"commentText" : $scope.comment,
			"username" : "",
			"date" : new Date()
		};
		$http({
			method : 'POST',
			url : 'newComment',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : comment
		}).then(function successCallback(response) {
				$scope.list.push(response.data);
		}, function errorCallback(response) {
		});
		$scope.commentForGame.push(comment);
	}
});

homeApp
		.directive(
				'starRating',
				function() {
					return {
						scope : {
							rating : '=',
							maxRating : '@',
							readOnly : '@',
							click : "&",
							mouseHover : "&",
							mouseLeave : "&"
						},
						restrict : 'EA',
						template : "<div style='display: inline-block; margin: 0px; padding: 0px; cursor:pointer;' "
								+ "ng-repeat='idx in maxRatings track by $index'> \
                    <img ng-src='{{((hoverValue + _rating) <= $index) "
								+ "&& \"http://www.codeproject.com/script/ratings/images/star-empty-lg.png\" "
								+ "|| \"http://www.codeproject.com/script/ratings/images/star-fill-lg.png\"}}' \
                    ng-Click='isolatedClick($index + 1)' \
                    ng-mouseenter='isolatedMouseHover($index + 1)' \
                    ng-mouseleave='isolatedMouseLeave($index + 1)'></img> \
            </div>",
						compile : function(element, attrs) {
							if (!attrs.maxRating
									|| (Number(attrs.maxRating) <= 0)) {
								attrs.maxRating = '5';
							}
							;
						},
						controller : function($scope, $element, $attrs) {
							$scope.maxRatings = [];

							for (var i = 1; i <= $scope.maxRating; i++) {
								$scope.maxRatings.push({});
							}
							;

							$scope._rating = $scope.rating;

							$scope.isolatedClick = function(param) {

								$scope.rating = $scope._rating = param;
								$scope.hoverValue = 0;
								$scope.click({
									param : param
								});
							};

							$scope.isolatedMouseHover = function(param) {

								$scope._rating = 0;
								$scope.hoverValue = param;
								$scope.mouseHover({
									param : param
								});
							};

							$scope.isolatedMouseLeave = function(param) {

								$scope._rating = $scope.rating;
								$scope.hoverValue = 0;
								$scope.mouseLeave({
									param : param
								});
							};
						}
					};
				});

homeApp.controller("editProfileCtrl", function($scope, $http) {
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
			method : 'PUT',
			url : 'updateUser',
			data : $.param({
				firstName : $scope.editableFirstName,
				lastName : $scope.editableLastName,
				username : $scope.editableUsername,
				email : $scope.editableEmail,
				gender : $scope.editableGender,
				age : $scope.editableAge,
				phoneNumber : $scope.editablePhoneNumber
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).success(function(result, status) {
			$scope.editProfileAnswer = result;
			$scope.userProfile.firstName = $scope.editableFirstName;
			$scope.userProfile.lastName = $scope.editableLastName;
			$scope.userProfile.username = $scope.editableUsername;
			$scope.userProfile.email = $scope.editableEmail;
			$scope.userProfile.gender = $scope.editableGender;
			$scope.userProfile.age = $scope.editableAge;
			$scope.userProfile.phoneNumber = $scope.editablePhoneNumber;
			$scope.editProfileMessage = false;
		}).error(function(result, status) {
			$scope.editProfileAnswer = result;
		})
	}

	$scope.saveNewUserPassword = function() {
		$http({
			method : 'PUT',
			url : 'updateUserPassword',
			data : $.param({
				oldPassword : $scope.editableOldPassword,
				newPassword : $scope.editableNewPassword,
				confirmPassword : $scope.editableConfirmPassword,
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).success(function(result, status) {
			$scope.editPasswordAnswer = result;
			$scope.editPasswordMessage = false;
		}).error(function(result, status) {
			$scope.editPasswordAnswer = result;
		})
	}
});

homeApp.controller("getAllUsersCtrl", function($scope, $http, $filter, $q, ngTableParams) {
	$scope.users = [];
	$http.get('users').then(function(result) {
		$scope.users = result.data;
		$scope.$broadcast('sharingToUsersTable', $scope.users);
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
			$http.get('allUsersGames?username='+ username).then(function(result) {
				$scope.games = result.data;
			});
			$scope.showModal = false;
			$scope.getInfoAboutGame = function(id) {
				$scope.showModal = !$scope.showModal;
				for (var i = 0; i < $scope.games.length; i++) {
					if ($scope.games[i].id === id) {
						$scope.oneGame = $scope.games[i];
						break;
					};
				};
			};
			$http.get('allUsersTournaments?username='+ username).then(function(result) {
				$scope.tournaments = result.data;
			});
			$scope.showModal1 = false;
			$scope.getInfoAboutTournament = function(tournamentId) {
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
	
	$scope.countries = function ($scope, $defer) {
		var def = $q.defer();
	    var country = $http.get('getAllCountries');
	    def.resolve(country);
	    return def;
	};
	
	$http.get('getAllCountries').then(function(result) {
		$scope.countries = result.data;
		$scope.getCitiesByCountry = function() {
			var countryName = $('select[name=selectCountries]').val();
			$http.get('getAllCities?countryName=' + countryName).then(function(result) {
				$scope.cities = result.data;
				$scope.$broadcast('sharingAddress', { countries:$scope.countries, cities:$scope.cities});
			});
		};
	});

	$scope.$on('sharingToUsersTable', function(event, data) {
	$scope.usersTable = new ngTableParams({
		page: 1,
		count: 7
	}, {
		total: data.length, 
		getData: function ($defer, params) {
		    	 $scope.usersByParams = params.sorting() ? 
		      			$filter('orderBy')(data, params.orderBy()) 
		       			: data;
		       	 $scope.usersByParams = params.filter() ? 
		       			$filter('filter')($scope.usersByParams, params.filter()) 
		       			: $scope.usersByParams;
		         $scope.usersByParams = $scope.usersByParams.slice((params.page() - 1) 
		            	* params.count(), params.page() * params.count());
		         $defer.resolve($scope.usersByParams);
		     }
		 });
	});
});

homeApp
		.controller(
				"getAllUsersWithNegativeRating",
				function($scope, $http) {
					$scope.usersWithNegRate = [];
					$http
							.get('getUsersWithNegativeRating')
							.then(
									function(result) {
										$scope.usersWithNegRate = result.data;
										if ($scope.usersWithNegRate.length != 0) {
											$("#myModalBannedUsers").modal(
													'show');
											$scope.bannedUsers = ('These users have to be banned: ' + $scope.usersWithNegRate);
										}
									});
				});

homeApp.controller("friendsCtrl", ['$scope', '$interval', '$http', function($scope, friendService, $http, $interval) {
    $scope.users;
    $http.get("allFriends").success(function(data) {
		$scope.friends = data;
	}).error(function(error) {
		console.log(error);
	});
    
    $http.get("allMyOffering").success(function(data) {
		$scope.userOffered = data;
	}).error(function(error) {
		console.log(error);
	});
    
    $http.get('allOffering').success(function(data){
         $scope.count = data;
     }).error(function(error){
         console.log(error)
     });
    
    $http.get("allOfferedUsers").success(function(data) {
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
    $scope.setFriendName = function(friendName){
        $scope.currentFriend = friendName;
        getUpdate(friendName);
    };
    var getUpdate = function() { 
          $http.post('getAllMessage/' +  $scope.currentFriend ,  $scope.currentFriend ).success(function(data){
              
              if($scope.messages != data){
                $scope.messages = data;
                  console.log($scope.messages == data);
                  console.log($scope.messages.length);
                  console.log(data.length);
              }
        }).error(function(error){
            console.log(error);
        });
    };
    $scope.sendMessage = function(message){
        $scope.currentFriend
        $http.post('sendMessage/' + $scope.currentFriend + "/" + message, $scope.currentFriend, message).success(function(){
        }).error(function(error){
            console.log(error);
        });
    };
    $scope.readMessage = function(messageId){
        $http.post('readMessage/' + messageId, messageId).success(function(){
        }).error(function(error){
            console.log(error);
        });
        
    };
    
    $http.get('findAllNotReadMessage').success(function(data){
         $scope.countOfNotReadMessage = data;
     }).error(function(error){
         console.log(error)
     });
    $scope.cancelOffering = function(userName){
        $http.post('canselOffering/' + userName, userName).success(function(){
            for(var i = 0; i < $scope.userOffered.length; i++){
                if($scope.userOffered[i].user.username === userName || $scope.userOffered[i].userId.username === userName){
                    $scope.userOffered.splice(i, 1)
                };
            };
        }).error(function(error){
            console.log(error);
    });
};
    $http.get('allMessageByCurrentUserFriends').success(function(data){
        $scope.allNotReadMessagesByFriend = data;
    }).error(function(error){
        console.log(error);
    });
    
   
  /*setInterval(function(){
       getUpdate();
   }, 10000)*/
   
}]);