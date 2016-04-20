var app = angular.module("usersGameApp", []);
app.controller("allUsersGameCtrl", function($scope, $http) {
	$http.get('getAllGamesCurUser').then(function(result) {
		$scope.allGame = result.data;
		$scope.showMe = false;
		$scope.myFunc = function(id) {
			$scope.games = [];
			$scope.showMe = !$scope.showMe;
			for (var i = 0; i < $scope.allGame.length; i++) {
				if ($scope.allGame[i].name === id) {
					$scope.games[0] = $scope.allGame[i];
				}
			}
		}
	});
});

app.controller("CreateGameCtrl", function($scope, $http) {
	$scope.showText = false;
	$scope.showForm = function() {
		$scope.showText = !$scope.showText;
		};
	$scope.submit = function() {
		var game = [{
			"name" : $scope.name,
			"description" : $scope.description,
			"rules" : $scope.rules,
			"maxPlayers" : $scope.maxPlayers,
			"minPleyers" : $scope.minPlayers,
		}];
		var category = {
			"name" : $scope.category
		};
		var userGame = {
			"edition" : $scope.edition,
			"year" : year
		}
	};
});

app.controller("listOfFriendsCtrl", function($scope, $http) {
	console.log("in controller list of Friends");
	$http.get("allFriends").success(function(data) {
		$scope.friends = data;
	}).error(function(error) {
		console.log(error);
	})
});

app.controller("getAllUsersCtrl", function($scope, $http) {
	$scope.users = [];
	$http.get('users').then(function(result) {
		$scope.users = result.data;
		$scope.showUser = false;
		$scope.getInfoAboutUserFunc = function(id) {
			$scope.showUser = !$scope.showUser;
			for (var i = 0; i < $scope.users.length; i++) {
				if ($scope.users[i].id === id) {
					$scope.oneUser = $scope.users[i];
					break;
				}
			}
		}
	});
});

app.controller('getGamesGlobalController', function($scope, $http) {

	$http({
		method : "GET",
		url : 'getAllGames'
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

app.controller("showAllTournaments", function ($scope,$http) {
	$http.get('/tournaments').success(function(data){
		$scope.tournaments=data;
	});
	$scope.JoinTournament=function(){

	}
});