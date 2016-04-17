var app = angular.module('boardGamesApp', []);
app.controller('getGamesGlobalController', function($scope, $http) {
	
	$http({
		method : "GET",
		url : '/getAllGames'
	}).then(function mySucces(response){
		$scope.gamesGlobal = response.data;         		
	}, function myError(response){
		$scope.gamesGlobal = [ {
			name : "Monopolly",
			category : "Buisiness",
			description : "Some awesome things about this game",
			minPlayers : "3",
			maxPlayers : "6"
		} ]; 
	});
});

app.controller('getGamesMyController', function($scope) {

});