var app = angular.module("usersGameApp", []);
app.controller("allUsersGameCtrl", function($scope, $http) {
	$scope.allGame = [];
	$http.get('/getAllGamesCurUser').then(function(result) {
		$scope.allGame = result.data;
	});
});
app.controller("listOfFriendsCtrl", function($scope, $http) {
	console.log("in controller list of Friends");
	$http.get("/allFriends").success(function(data) {
		$scope.friends = data;
	}).error(function(error) {
		console.log(error);
	})
});

app.controller("getAllUsersCtrl", function($scope, $http) {
	$scope.users = [];
	$http.get('/users').then(function(result) {
		$scope.users = result.data;
	}), function myError(result) {
		$scope.users = [ {
			lastName : "Monopolly",

		} ];
	};

});

app.controller('getGamesGlobalController', function($scope, $http) {

	$http({
		method : "GET",
		url : '/getAllGames'
	}).then(function mySucces(response) {
		$scope.gamesGlobal = response.data;
	}, function myError(response) {
		$scope.gamesGlobal = [ {
			name : "Monopolly",
			category : "Buisiness",
			description : "Some awesome things about this game",
			minPlayers : "3",
			maxPlayers : "6"
		} ];
	});
});