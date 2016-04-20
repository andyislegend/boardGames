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
					break;
				}
			}
		}
	});
});

app.controller("CreateGameCtrl", function($scope, $http,$window) {
	$scope.showText = false;
	$scope.showForm = function() {
		$scope.showText = !$scope.showText;
		};
		$scope.list = [];
	$scope.submit = function($window) {
		 var userGame  = {
			"edition" : $scope.edition,
			"yearOfProduction" : $scope.year,
			"game" : {"name" : $scope.name,
			"category" : { "name" : $scope.category},
			"description" : $scope.description,	
			"rules" : $scope.rules,
			"maxPlayers" : $scope.maxPlayers,
			"minPlayers" : $scope.minPlayers
			}
		 };
		 var response = $http.post('NewGame', userGame);
			response.success(function(data, status, headers, config) {
				$scope.list.push(data);
			});				
			$route.reload();
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
		alert("Getting games general data error");
	});
	
	$scope.gameSelect = function(obj, $event) {
		
		$scope.gameDetailsShown = true;
		
		$http({
			method: "GET",
			url : 'getGameDetails' + '/' + obj
		}).then(function mySucces(response){
			$scope.gameDetail = response.data;
		}, function myError(response) {
			alert("Getting games general data error");
		});
		
		$http({
			method: "GET",
			url : 'getUserGamesOfGame' + '/' + obj
		}).then(function mySucces(response){
			$scope.userGamesOfGame = response.data;
		}, function myError(response) {
			alert("Getting games userGames of game error");
		});
	}
	
	$scope.hideGameDetails = function() {
		$scope.gameDetailsShown = false;
	}
});

app.controller("showAllTournaments", function ($scope,$http) {
	$http.get('/tournaments').success(function(data){
		$scope.tournaments=data;
	});
	$scope.JoinTournament=function(){

	}
});