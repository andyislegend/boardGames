var games = angular.module("usersGameApp", []);
games.controller("allUsersGameCtrl", function($scope, $http) {
$http({
	method:'GET',
	url:'/getAllGamesCurUser'
}).then (function mySuccses(data){
	$scope.allGames = data
},function myError(data){
	$scope.allGames = [ {
		name : "Game"
	} ];
});
});
