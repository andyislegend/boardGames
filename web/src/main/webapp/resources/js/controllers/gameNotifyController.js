angular.module('homeApp').controller("gameNotifyController", function($scope, $http, $route, $routeParams) {
		
	$http({
		method : "GET",
		url : 'getAllGameNotifications'
	}).then(function mySucces(response) {
		$scope.gameNotifications = response.data;
		console.log(response.data);
	}, function myError(response) {
		alert("getting all confirmed actions error");
	});
});