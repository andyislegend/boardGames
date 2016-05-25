angular.module('homeApp').controller("eventInfoCtrl", ['$scope', '$http', '$routeParams', '$route', function($scope, $http, $routeParams, $route) {
	
	var id = $routeParams.id;
	$scope.currentEvent;
	$scope.isSub;
	
	$http.get('getEventDTO?id='+ id).then(function(result) {
		$scope.currentEvent = result.data;
	});
	
	$http.get('getIfAlreadySub?id='+ id).then(function(result) {
		$scope.isSub = result.data;
	});
	
	$scope.subscribeToEvent = function(id) {
	$http.post('subscribeToEvent?id=' + id).then(
			function mySucces(response) {
				$route.reload();
			}, function myError(response) {
				alert("Failed to send your request");
			});

	};
	
	$scope.usnsubscribeFromEvent = function() {
		$http.delete('unsubscribeFromEvent?id='+ id).then(function mySucces(response) {
			$route.reload();
		}, function myError(response) {
			alert("Failed to send your request");
		});
	}


}]);

