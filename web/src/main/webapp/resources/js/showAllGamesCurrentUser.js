var games = angular.module("usersGameApp", []);
games.controller("allUsersGameCtrl", function($scope, $http) {
	$scope.allGame = [];
	$http.get('/getAllGamesCurUser')
	.then(function(result) {
		$scope.allGame = result.data;
		});
	});
games.controller("list_of_Friends_Ctrl", function($scope, $http){
    console.log("in controller list of Friends");
	$http.get("allFriends").success(function(data){
         $scope.friends = data;
    }).error(function(error){
        console.log(error);
    })
});