angular.module('homeApp').controller('gameSelectController', function($scope, $http, $routeParams) {
	var id = $routeParams.id;
	var name = $routeParams.name;

	$scope.$on('refreshingGameDetails', function(event, data) {
		$http({
			method : "GET",
			url : 'getGameDetails' + '/' + data
		}).then(function mySucces(response) {
			$scope.gameDetail = response.data;
			$scope.$broadcast('broadcastingGameId', data);
		}, function myError(response) {
			
		});
	});
	$scope.$emit('refreshingGameDetails', id);

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