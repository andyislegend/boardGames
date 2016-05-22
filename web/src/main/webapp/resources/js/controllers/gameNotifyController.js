angular.module('homeApp').controller("gameNotifyController", function($scope, $http, $route, $routeParams) {
		
	$scope.date = new Date();
	$http({
		method : "GET",
		url : 'getAllConfirmedActions'
	}).then(function mySucces(response) {
		
		$scope.gameNotifications = response.data;
		console.log(response.data);
	}, function myError(response) {
		alert("getting all confirmed actions error");
	});
});