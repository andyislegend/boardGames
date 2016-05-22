angular.module('homeApp').controller('gameSelectController', function($scope, $http, $routeParams) {
	var id = $routeParams.id;
	var name = $routeParams.name;
	$scope.$on('settingRootRating', function(event, data) {
		$scope.gameRatingDisplay = data;
	});

	$scope.$on('refreshingGameDetails', function(event, data) {
		console.log(data);
		$http({
			method : "GET",
			url : 'getGameDetails' + '/' + data
		}).then(function mySucces(response) {
			$scope.gameDetail = response.data;
			console.log($scope.gameDetail);
		}, function myError(response) {
			
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