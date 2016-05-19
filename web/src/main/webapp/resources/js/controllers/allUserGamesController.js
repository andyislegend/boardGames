angular.module('homeApp').controller("allUsersGameCtrl", function($scope, $http, $rootScope, $route, $routeParams,$timeout) {
	
	$rootScope.NN = 100;
	$rootScope.allGame = [];
	
	$http({
		method : "GET",
		url : 'getAllMyGamesCurUser'
	}).then(function mySucces(response) {
		$rootScope.allGame = response.data;
		for (var i = 0; i < $rootScope.allGame.length; i++) {
			$rootScope.isNewComments($rootScope.allGame[i].id);
		}
	}, function myError(response) {
		alert("getting my games error");
	});
	$http({
		method : "GET",
		url : 'getAllSharedGamesCurUser'
	}).then(function mySucces(response) {
		$rootScope.allSharedGames = response.data;
	}, function myError(response) {
		alert("getting shared gaems error");
	});
	$http({
		method : "GET",
		url : 'getAllBorrowedGamesCurUser'
	}).then(function mySucces(response) {
		$rootScope.allBorrowedGames = response.data;
	}, function myError(response) {
		alert("getting borrowed games error");
	});
	
	$scope.isYourGame = false;
	$scope.isYourGamePrivate = false;
	$scope.isntYourGame = false;
	$scope.isThereConfiramtion = false;
	$scope.canGiveBack = false;
	$scope.doWantToApply = false;
	
	$scope.updateGame = function(){
		var game = {
			id : $scope.editGameData.id,
			yearOfProduction : $scope.editGameData.yearOfProduction,
			edition : $scope.editGameData.edition,
			countOfComments : $scope.editGameData.countOfComments,
			status : $scope.editGameData.status,
			description : $scope.editGameData.description,
			rules : $scope.editGameData.rules,
			maxPlayers : $scope.editGameData.maxPlayers,
			minPlayers : $scope.editGameData.minPlayers
		}
		
		$http({
			method : 'PUT',
			url : '/updateGameDetails',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : game
		}).success(function(response) {
			
		}, function errorCallback(response) {
		});
		
		scope.$apply();
	}

	$http.get('gameUserDetail/' + $routeParams.id).then(
		function(result) {
			
			$scope.games = result.data;
			
			var isBorrowed = false;
			$http ({
				method : "GET",
				url : 'checkIfGameIsBorrowed/' + $scope.games.id
			}).then(function mySucces(response) {
				isBorrowed = response.data;
			}, function myError(response) {
				alert("checking game status error");
			});
			
			$http ({
				method : "GET",
				url : 'checkIfGameBelongsToUser' + '/' + $scope.games.id
			}).then(function mySucces(response) {
			
				if (response.data === true && $scope.games.status === 'private') {
					$scope.isYourGame = true;
					$scope.isntYourGame = false;
					$scope.isThereConfiramtion = false;
					$scope.isYourGamePrivate = false;
					$scope.canGiveBack = false;
				}
				else if (response.data === false && $scope.games.status === 'available') {
					$scope.isntYourGame = true;
					$scope.isYourGame = false;
					$scope.isThereConfiramtion = false;
					$scope.isYourGamePrivate = false;
					$scope.canGiveBack = false;
				}
				else if (response.data === true && $scope.games.status === 'available'){
					$scope.isYourGame = false;
					$scope.isntYourGame = false;
					$scope.isThereConfiramtion = false;
					$scope.isYourGamePrivate = true;
					$scope.canGiveBack = false;
				}
				else if (response.data === true && $scope.games.status === 'confirmation') {
					$scope.isntYourGame = false;
					$scope.isYourGame = false;
					$scope.isThereConfiramtion = true;
					$scope.isYourGamePrivate = false;
					$scope.canGiveBack = false;
					
					$http ({
						method : "GET",
						url : 'getApplierUsername' + '/' + $scope.games.id
					}).then(function mySucces(response) {
						$scope.applierUsername = response.data.username;
						$scope.messageFromApplier = response.data.message;
					}, function myError(response) {
						alert("Getting user applier username error");
					});
					
					$http ({
						method : "GET",
						url : 'getPropositionsOfExchange' + '/' + $scope.games.id
					}).then(function mySucces(response) {
						$scope.propositions = response.data;
					}, function myError(response) {
						alert("Getting user applier username error");
					});
				}
				else if (isBorrowed === true) {
					$scope.canGiveBack = true;
					$scope.isntYourGame = false;
					$scope.isYourGame = false;
					$scope.isThereConfiramtion = false;
					$scope.isYourGamePrivate = false;
					
					$http({
						method : "GET",
						url : 'getHowManyDaysRemains/' + $scope.games.id
					}).then(function mySucces(response) {
						$scope.howManyDaysRemains = response.data;
					}, function myError(response) {
						alert("getting exchange period error");
					});
				}
				else {
					$scope.isYourGame = false;
					$scope.isntYourGame = false;
					$scope.isThereConfiramtion = false;
					$scope.isYourGamePrivate = false;
					$scope.canGiveBack = false;
				}
			}, function myError(response) {
				
			});
			 $scope.editGameData = {
					id : $scope.games.id,
					gameName : $scope.games.name,
					yearOfProduction: $scope.games.yearOfProduction,
					edition : $scope.games.edition,
					countOfComments : $scope.games.countOfComments,
					status : $scope.games.status,
					description : $scope.games.description,
					rules : $scope.games.rules,
					maxPlayers : $scope.games.maxPlayers,
					minPlayers : $scope.games.minPlayers
			 }		
	});
	
	$scope.gameDetailById = function(id) {
		$scope.games = [];
	}
	
	
	$scope.displayRequestBlockClick = function(id) {
		if ($scope.doWantToApply === true)
			$scope.doWantToApply = false;
		else {
			$scope.doWantToApply = true;
			
			$http({
				method : "GET",
				url : 'getHowManyDaysForExchange/' + id
			}).then(function mySucces(response) {
				$scope.howManyDays = response.data;
			}, function myError(response) {
				alert("getting exchange period error");
			});
			
			$http({
				method : "GET",
				url : 'getAllMyGamesCurUser'
			}).then(function mySucces(response) {
				$scope.myGames = response.data;
			}, function myError(response) {
				alert("getting my games error");
			});
		}
	}
	
	$scope.gamesToPropose = [];
	$scope.addToProposes = function() {
		console.log($scope.myGamesModel.id);
		$scope.gamesToPropose.push($scope.myGamesModel);
	}
	
	$scope.$on('changingGameStatus', function(event, data) {
		$http({
			method : "PUT",
			url : data.url + data.userGameId
		}).then(function mySucces(response) {
			$route.reload();
		}, function myError(response) {
			alert("Changing game status error");
		});
	});
	
	$scope.makeGameUserAvailable = function(id) {
		$http({
			method : "PUT",
			url : 'makeGameUserAvailable/' + id + '/' + $scope.returnDate 
		}).then(function mySucces(response) {
			$route.reload();
		}, function myError(response) {
			alert("Changing game status error");
		});
	}
	$scope.keepGameUserPrivate = function(id) {
		$scope.$emit('changingGameStatus', {
			url:'makeGameUserPrivate/',
			userGameId: id
		});
	}
	$scope.askOwnerToShare = function(id, message, propositionsList) {
		console.log(propositionsList);
		var values = [];
		angular.forEach(propositionsList, function(value, key) {
			values.push(value.id);
		}, values);
		console.log(values);
		$http({
			method : "PUT",
			url : 'askGameUserOwnerToShare/' + id + '/' + message + '/' + values
		}).then(function mySucces(response) {
			$route.reload();
		}, function myError(response) {
			alert("Failed to send your request");
		});
	}
	$scope.acceptGameConfirmation = function(id) {
		$scope.$emit('changingGameStatus', {
			url:'acceptGameConfirmationRequest/',
			userGameId: id
		});
	}
	$scope.declineGameConfirmation = function(id) {
		$scope.$emit('changingGameStatus', {
			url:'declineGameConfirmationRequest/',
			userGameId: id
		});
	}
	$scope.giveBackGame = function(id) {
		$scope.$emit('changingGameStatus', {
			url:'giveGameBack/',
			userGameId: id
		});
	}

	$rootScope.isNewComments = function(id) {
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
		for(var i = 0; i<$rootScope.allGame.length; i++){
			if($rootScope.allGame[i].id === id){
				$rootScope.allGame.splice($rootScope.allGame[i], 1);
				break;
			}
		}
		$http.delete('deleteUserGame/'+id).success(function(data) {					
		});
		
		//scope.$apply();
	}
});