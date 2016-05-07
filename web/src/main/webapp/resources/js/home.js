var homeApp = angular.module('homeApp', [ 'ngRoute', 'ui.bootstrap', 'ngTable' ]);

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

	.otherwise({
		redirectTo : '/statistics'
	});

});

homeApp.controller("getAvatar", function($scope, $http) {
	$http.get('getAvatar').then(function(result) {
		$scope.avatar = result.data;
	});
});

homeApp
		.controller(
				"allUsersGameCtrl",
				function($scope, $http, $rootScope) {
					$rootScope.NN = 100;
					$scope.allGame = [];
					$http.get('getAllGamesCurUser').then(function(result) {
						$scope.allGame = result.data;
						for (var i = 0; i < $scope.allGame.length; i++) {
							$scope.isNewComments($scope.allGame[i].id);
						}
					});

					$scope.showMe = false;
					$scope.myFunc = function(id) {
						$scope.games = [];
						$scope.showMe = !$scope.showMe;
						$http.get('gameUserDetail/' + id).then(
								function(result) {
									$scope.games = result;
								});
					}

					$scope.isNewComments = function(id) {
						var countOfComments = 0;
						$scope.userGame = [];
						$http
								.get('getCountOfCommentsByGameId/' + id)
								.then(
										function(result) {
											countOfComments = result.data;
											$http
													.get('gameUserDetail/' + id)
													.success(
															function(result) {
																$scope.userGame = result;
																if (countOfComments > $scope.userGame.countOfComments) {
																	$rootScope.NN = countOfComments;
																	document
																			.getElementById("UserGameNum"
																					+ $scope.userGame.id).className = "glyphicon glyphicon-envelope";
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

homeApp.controller('getGamesGlobalController', 
		function($scope, $http, $filter, ngTableParams) {

	$http({
		method : "GET",
		url : 'getAllGames'
	}).then(function mySucces(response) {
		$scope.gamesGlobal = response.data;
		$scope.allGamesTable = new ngTableParams({
	        page: 1,
	        count: 10
	    }, {
	        total: $scope.gamesGlobal.length, 
	        getData: function ($defer, params) {
	            $scope.gamesGlobalDisplay = $scope.gamesGlobal.slice(
	            		(params.page() - 1) * params.count()
	            		, params.page() * params.count());
	            $defer.resolve($scope.gamesGlobalDisplay);
	        }
	    });
		
	}, function myError(response) {
		alert("Getting games general data error");
	});
});

homeApp.controller('gameSelectController',
		function($scope, $http, $routeParams) {

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
			}, function myError(response) {
				alert("Getting games userGames of game error");
			});
		});

homeApp
		.controller(
				'getGameDetailedInfoController',
				function($scope, $http, $rootScope) {

					$scope.$on('sharingIdToDetailsModal',
							function(event, data) {
								$scope.currentGameId = data.id;
								$scope.starRating = data.rating;
								$scope.hoverRating = 0;
							});

					$scope.ratingClick = function(param) {
						console.log('mouseClick(' + param + ')');
						$http(
								{
									method : "POST",
									url : 'calculateRatings' + '/'
											+ $scope.currentGameId + '/'
											+ param,
								}).then(
								function mySucces(response) {
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

homeApp
		.controller(
				"getAllUsersCtrl",
				function($scope, $http) {
					$scope.users = [];
					$http
							.get('users')
							.then(
									function(result) {
										$scope.users = result.data;
										$scope.showUser = false;
										$scope.getInfoAboutUserFunc = function(
												username) {
											$scope.oneUser
											$scope.showUser = !$scope.showUser;
											for (var i = 0; i < $scope.users.length; i++) {
												if ($scope.users[i].username === username) {
													$scope.oneUser = $scope.users[i];
													break;
												}
												;
											}
											;
											$http
													.get(
															'allUsersGames?username='
																	+ username)
													.then(
															function(result) {
																$scope.games = result.data;
															});
											$scope.showModal = false;
											$scope.getInfoAboutGame = function(
													id) {
												$scope.showModal = !$scope.showModal;
												for (var i = 0; i < $scope.games.length; i++) {
													if ($scope.games[i].id === id) {
														$scope.oneGame = $scope.games[i];
														break;
													}
													;
												}
												;
											};
											$http
													.get(
															'allUsersTournaments?username='
																	+ username)
													.then(
															function(result) {
																$scope.tournaments = result.data;
															});
											$scope.showModal1 = false;
											$scope.getInfoAboutTournament = function(
													tournamentId) {
												$scope.showModal1 = !$scope.showModal1;
												for (var i = 0; i < $scope.tournaments.length; i++) {
													if ($scope.tournaments[i].tournamentId === tournamentId) {
														$scope.oneTournament = $scope.tournaments[i];
														break;
													}
													;
												}
												;
											};
											$http
													.get(
															'getUsersAvatar?username='
																	+ $scope.oneUser.username)
													.then(
															function(result) {
																$scope.userAvatar = result.data;
															});
										};
									});
					$http.get('getAllCountries').then(
							function(result) {
								$scope.countries = result.data;
								$scope.getCitiesByCountry = function() {
									var countryName = $(
											'select[name=selectCountries]')
											.val();
									$http.get(
											'getAllCities?countryName='
													+ countryName).then(
											function(result) {
												$scope.cities = result.data;
											});
								};
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