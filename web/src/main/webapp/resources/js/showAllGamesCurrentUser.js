var games = angular.module("usersGameApp", []);
games.controller("allUsersGameCtrl", function($scope, $http) {
$http({
	method:'GET',
	url:'/getAllGamesCurUser'
}).then (function mySuccses(response){
	$scope.allGames = response.data
},function myError(response){
	$scope.allGames = [ {
		name : "Chess",
		category:"Sport"
	} ];
});
});
