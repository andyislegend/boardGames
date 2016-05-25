angular.module('homeApp').controller("gameNotifyController", function($scope, $http, $route, $rootScope, $routeParams, $filter, ngTableParams) {
	 $scope.gameNotifications = [];	
	$http({
		method : "GET",
		url : 'getAllGameNotifications'
	}).then(function mySucces(response) {
		$scope.gameNotifications = response.data;
	}, function myError(response) {
		alert("getting all confirmed actions error");
	});
    
    $http.get('getAllNotification').success(function(data){
        $scope.gameNotifications = $scope.gameNotifications.concat(data);
        $scope.$broadcast('performingNotifications', $scope.gameNotifications);	
    }).error(function(error){
        console.log(error);
    });
    
	
	$scope.$on('performingNotifications',
		function(event, data) {
			$scope.gameNotifyTable = new ngTableParams({
			    page: 1,
			    count: 7
			 }, {
			     total: data.length, 
			     getData: function ($defer, params) {
			    	 
			    	 $scope.gameNotificationsDisplay = params.sorting() ? 
			      			$filter('orderBy')(data, params.orderBy()) 
			       			: data;
			       	 $scope.gameNotificationsDisplay = params.filter() ? 
			       			$filter('filter')($scope.gameNotificationsDisplay, params.filter()) 
			       			: $scope.gameNotificationsDisplay;
			         $scope.gameNotificationsDisplay = $scope.gameNotificationsDisplay.slice((params.page() - 1) 
			            	* params.count(), params.page() * params.count());
			         $defer.resolve($scope.gameNotificationsDisplay);
			     }
			 });
		});
	
	$scope.markAsChecked = function(id) {
		$http({
			method : "PUT",
			url : 'markNotificationAsChecked/' + id
		}).then(function mySucces(response) {
			$http({
				method : "GET", url : 'getCountOfNotifications'
			}).then(function mySucces(response) {
				$rootScope.uncheckedNotifiCount = response.data;
			}, function myError(response) {
				alert("getting principal username error");
			});
		}, function myError(response) {
			alert("getting all confirmed actions error");
		});
	}
});