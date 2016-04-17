var games = angular.module("usersGameApp", []);
games.controller("allUsersGameCtrl", function($scope, $http) {
	$scope.allGame = [];
	$http.get('/getAllGamesCurUser')
	.then(function(result) {
		$scope.allGame = result.data;
		});
	});