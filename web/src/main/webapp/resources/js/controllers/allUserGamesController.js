var homeApp = angular.module('homeApp');
homeApp.controller("allUsersGameCtrl", function($scope, $uibModal, $http, $rootScope, $route, $routeParams,$interval) {
homeApp.$inject = ['$modal'];
	
	$rootScope.NN = 100;
	$rootScope.allGame = [];
	$rootScope.getAllUsersGame = [];
		
	$http.get('getAllMyGamesCurUser').then(function mySucces(response) {
		$rootScope.allGame = response.data;
		});
	
	setInterval(function(){
		for (var i = 0; i < $rootScope.allGame.length; i++) {
			$rootScope.isNewComments($rootScope.allGame[i].id);
		}
}, 3000);
	
	$http({
		method : "GET",
		url : 'getAllUserGames'
	}).then(function mySucces(result) {
		$rootScope.getAllUsersGame = result.data;
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
	
	$rootScope.isNewComments = function(id) {
		var countOfComments = 0;
		$scope.userGame = [];
		$http.get('getCountOfCommentsByGameId/' + id).then(function(result) {
			countOfComments = result.data;
			$http.get('gameUserDetail/' + id).success(function(result) {
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
		$rootScope.gameDeleteId = id;
		$http.get('/getCountOfTournamentsByGame/'+id).success(function(result) {
			$scope.countOfTornaments = result;
			if($scope.countOfTornaments>0){		
				$('#modalCantToDelete').modal('show');
			}else {				
				$('#modalDelete').modal('show');
				}			
			});
	}
	
	$scope.confirmationToDelete = function(){
		for(var i = 0; i<$rootScope.allGame.length;i++){
			if($rootScope.allGame[i].id === $rootScope.gameDeleteId) {
				$rootScope.allGame.splice(i,1);
			}
		} 
		$http.delete('deleteUserGame/'+$rootScope.gameDeleteId).success(function(data) {			
		});
	}
	
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

	$scope.$on('refreshingPage', function(event, data) {
		
		$http.get('gameUserDetail/' + $routeParams.id).then(function(result) {
			
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
			
				if (response.data === true && $scope.games.status === 'PRIVATE') {
					$scope.isYourGame = true;
					$scope.isntYourGame = false;
					$scope.isThereConfiramtion = false;
					$scope.isYourGamePrivate = false;
					$scope.canGiveBack = false;
				}
				else if (response.data === false && $scope.games.status === 'AVAILABLE') {
					$scope.isntYourGame = true;
					$scope.isYourGame = false;
					$scope.isThereConfiramtion = false;
					$scope.isYourGamePrivate = false;
					$scope.canGiveBack = false;
				}
				else if (response.data === true && $scope.games.status === 'AVAILABLE'){
					$scope.isYourGame = false;
					$scope.isntYourGame = false;
					$scope.isThereConfiramtion = false;
					$scope.isYourGamePrivate = true;
					$scope.canGiveBack = false;
				}
				else if (response.data === true && $scope.games.status === 'CONFIRMATION') {
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
	});
	$scope.$emit('refreshingPage');
	
	$scope.displayRequestBlockClick = function(id) {
		
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
			$scope.$emit('refreshingPage');
		}, function myError(response) {
			alert("Changing game status error");
		});
	});
	
	$scope.makeGameUserAvailable = function(id) {
		$http({
			method : "PUT",
			url : 'makeGameUserAvailable/' + id + '/' + $scope.returnDate 
		}).then(function mySucces(response) {
			$scope.$emit('refreshingPage');
			$('#makeGameAvailable').modal('hide');
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
		var values = [];
		angular.forEach(propositionsList, function(value, key) {
			values.push(value.id);
		}, values);
		var outMessage = message || 'no message';
		$http({
			method : "PUT",
			url : 'askGameUserOwnerToShare/' + id + '/' + outMessage + '/' + values
		}).then(function mySucces(response) {
			$scope.$emit('refreshingPage');
			$('#applyForGameModal').modal('hide');
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
});