angular.module('homeApp').controller("CreateGameCtrl", function($scope, $http, $route, $timeout) {
	$scope.showText = false;
	$scope.categories = [];
	$scope.showForm = function() {
		$scope.showText = !$scope.showText;
	};
	$http.get('getAllCategories').then(function(result) {
		$scope.categories = result.data;
	});
	
	$http({
		method : "GET", url : 'getPrincipalUsername'
	}).then(function mySucces(response) {
		$scope.username = response.data;
	}, function myError(response) {
		alert("getting unchecked notifications count error");
	});
	
	$http({
		method : "GET", url : 'getCountOfNotifications'
	}).then(function mySucces(response) {
		$scope.uncheckedNotifiCount = response.data;
	}, function myError(response) {
		alert("getting principal username error");
	});
	
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
			
		}, function errorCallback(response) {
		});
		
		$scope.$parent.allGame.push(userGame);	
		$route.reload();
		$scope.name = '';
		$scope.category = '';
		$scope.year = '';
		$scope.edition = '';
		$scope.description = '';
		$scope.rules = '';
		$scope.maxPlayers = '';
		$scope.minPlayers = '';
		
	};
});