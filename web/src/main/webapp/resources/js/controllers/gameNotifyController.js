angular.module('homeApp').controller("gameNotifyController", function($scope, $http, $route, $routeParams) {
		
	$http({
		method : "GET",
		url : 'getAllGameNotifications'
	}).then(function mySucces(response) {
		$scope.gameNotifications = response.data;
	}, function myError(response) {
		alert("getting all confirmed actions error");
	});
	
	$scope.markAsChecked = function(id) {
		
		$http({
			method : "PUT",
			url : 'markNotificationAsChecked/' + id
		}).then(function mySucces(response) {
			$route.reload();
		}, function myError(response) {
			alert("getting all confirmed actions error");
		});
	}
});