var app = angular.module("usersGameApp", ['ui.bootstrap']);
app.controller("allUsersGameCtrl", function($scope, $http) {
	$http.get('getAllGamesCurUser').then(function(result) {
		$scope.allGame = result.data;
		$scope.showMe = false;
		$scope.myFunc = function(id) {
			$scope.games = [];
			$scope.showMe = !$scope.showMe;
			for (var i = 0; i < $scope.allGame.length; i++) {
				if ($scope.allGame[i].id === id) {
					$scope.games[0] = $scope.allGame[i];
					break;
				}
			}
		}
	});
});

app.controller("CreateGameCtrl", function($scope, $http) {
	$scope.showText = false;
	$scope.categories = [];
	$scope.showForm = function() {
		$scope.showText = !$scope.showText;
		};
	$http.get('getAllCategories').then(function(result ){
		$scope.categories = result.data;
	});
		$scope.list = [];
	$scope.submit = function() {	
		 var userGame  = {
			"edition" : $scope.edition,
			"yearOfProduction" : $scope.year,
			"game" : {"name" : $scope.name,
			"category" : { "id" : $scope.category},
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
	};
});

app.controller("eventListCtrl", function($scope, $http) {
	$scope.events = [];
	 $http({
			method : "GET",
			url : 'eventspage'
		}).then(function mySucces(response) {
			$scope.events = response.data;
		}, function myError(response) {
			$scope.events = [ {"name":"Everybody sleeps but mafia members wake up","description":"Mafia event","date":1465160400000,"place":"Lviv","game":"Mafia","user":"Super","imgsrc":"resources/images/mafiaImg.jpg"} ];
		});
	});


app.controller("listOfFriendsCtrl", function($scope, $http) {
	console.log("in controller list of Friends");
	$http.get("allFriends").success(function(data) {
		$scope.friends = data;
	}).error(function(error) {
		console.log(error);
	})
});
app.controller("countOfOffering", function($scope, $http) {
	console.log("countOfOffering");
	$http.get("allOffering").success(function(data) {
		$scope.count = data;
	}).error(function(error) {
		console.log(error);
	})
});
app.controller("OfferToFriendCtrl", function($scope, $uibModal) {
	$scope.open = function () {
		console.log("befor open");
		   $uibModal.open({
		      templateUrl: 'OfferingForm.html'
		 });
	};
});

/*users Angular controller -- start*/

app.controller("getAllUsersCtrl", function($scope, $http, $uibModal) {
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
				};
			};
		};
	});
});

/*users Angular controller -- end*/


app.controller('getGamesGlobalController', function ($scope, $http) {

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

app.controller("showAllTournaments", function ($scope, $http) {
    $http.get('/tournaments').success(function (data) {
        $scope.tournaments = data;
    });
    $scope.JoinTournament = function (elem) {
        var idTournament = elem;
        console.log(idTournament);
        $http.post("/joinTournament", idTournament)
            .success(function (data) {
                $scope.tournaments = data;

            });
    }
});

