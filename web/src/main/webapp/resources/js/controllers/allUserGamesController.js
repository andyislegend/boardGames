angular.module('homeApp').controller("allUsersGameCtrl", function($scope, $http, $rootScope, $routeParams,$timeout) {
	
	$rootScope.NN = 100;
	$rootScope.allGame = [];
	$http.get('getAllGamesCurUser').then(function(result) {
		$rootScope.allGame = result.data;
		
			for (var i = 0; i < $rootScope.allGame.length; i++) {
				$rootScope.isNewComments($rootScope.allGame[i].id);
			}
	});
	
	
	
	$scope.isYourGame = false;
	$scope.isYourGamePrivate = false;
	$scope.isntYourGame = false;
	$scope.isThereConfiramtion = false;
	$scope.makingGameAvailableMessage = "";
	$scope.makingGamePrivateMessage = "";
	$scope.sendingRequestToOwnerMessage = "";
	$scope.aceptingRequestMessage = "";
	$scope.decliningRequestMessage = "";
	
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
			$http({
				method : "GET",
				url : 'checkIfGameBelongsToUser' + '/' + $scope.games.id
			}).then(function mySucces(response) {
				
				if (response.data === true && $scope.games.status === 'private') {
					$scope.isYourGame = true;
					$scope.isntYourGame = false;
					$scope.isThereConfiramtion = false;
					$scope.isYourGamePrivate = false;
				}
				else if (response.data === false && $scope.games.status === 'available') {
					$scope.isntYourGame = true;
					$scope.isYourGame = false;
					$scope.isThereConfiramtion = false;
					$scope.isYourGamePrivate = false;
				}
				else if (response.data === true && $scope.games.status === 'available'){
					$scope.isYourGame = false;
					$scope.isntYourGame = false;
					$scope.isThereConfiramtion = false;
					$scope.isYourGamePrivate = true;
				}
				else if (response.data === true && $scope.games.status === 'confirmation') {
					$scope.isntYourGame = false;
					$scope.isYourGame = false;
					$scope.isThereConfiramtion = true;
					$scope.isYourGamePrivate = false;
				}
				else {
					$scope.isYourGame = false;
					$scope.isntYourGame = false;
					$scope.isThereConfiramtion = false;
					$scope.isYourGamePrivate = false;
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
			$scope.makingGameAvailableMessage = "your game now is available!";	
		}, function myError(response) {
			alert("Making game available failed");
		});
		
	}
	
	$scope.keepGameUserPrivate = function(id) {
		$http({
			method : "PUT",
			url : 'makeGameUserPrivate' + '/' + id
		}).then(function mySucces(response) {
			$scope.makingGamePrivateMessage = "your game is now private";	
		}, function myError(response) {
			alert("Making game available failed");
		});
		
		
	}
	
	$scope.askOwnerToShare = function(id) {
		$http({
			method : "PUT",
			url : 'askGameUserOwnerToShare' + '/' + id
		}).then(function mySucces(response) {
			$scope.sendingRequestToOwnerMessage = "Your request was successfully sent!";
		}, function myError(response) {
			alert("Failed to send your request");
		});
	}
	
	$scope.acceptGameConfirmation = function(id) {
		$http({
			method : "PUT",
			url : 'acceptGameConfirmationRequest' + '/' + id
		}).then(function mySucces(response) {
			$scope.aceptingRequestMessage = "Generous action!";
		}, function myError(response) {
			alert("Accept request failed");
		});
	}
	
	$scope.declineGameConfirmation = function(id) {
		$http({
			method : "PUT",
			url : 'declineGameConfirmationRequest' + '/' + id
		}).then(function mySucces(response) {
			$scope.decliningRequestMessage = "This man is not worth of my game!";
			
		}, function myError(response) {
			alert("Decline request failed");
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
		$http.delete('deleteUserGame/'+id).success(function(data) {
		});
		$rootScope.allGame.splice($scope.allGame[id], 1);
		scope.$apply();
	}
});