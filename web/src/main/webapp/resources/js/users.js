var app = angular.module('usersApp', []);
app.controller('getAllUsers', function($scope, $http) {
	$scope.users = [];
	$http.get('users')
	.then(function(result) {
		$scope.users = result.data;
	})
});

