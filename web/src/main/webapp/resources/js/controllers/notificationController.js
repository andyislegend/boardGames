var app = angular.module('homeApp').controller("notificationCtrl", ['$scope', '$rootScope', '$http', '$interval','$filter','ngTableParams', function($scope,$rootScope, $http, $interval, $filter, ngTableParams) {
    
	$http.get('getAllNotification').success(function(data){
        console.log(data);
        $scope.allNotification = data;
        $scope.$broadcast('allNotifications', $scope.allNotification);	
    }).error(function(error){
        console.log(error);
    });
    
    $scope.$on('allNotifications',
		function(event, data) {
			$scope.allNotificationTable = new ngTableParams({
			    page: 1,
			    count: 6
			 }, {
			     total: data.length, 
			     getData: function ($defer, params) {
			    	 
			    	 $scope.allNotificationDisplay = params.sorting() ? 
			      			$filter('orderBy')(data, params.orderBy()) 
			       			: data;
			       	 $scope.allNotificationDisplay = params.filter() ? 
			       			$filter('filter')($scope.allNotificationDisplay, params.filter()) 
			       			: $scope.gameNotificationsDisplay;
			         $scope.allNotificationDisplay = $scope.allNotificationDisplay.slice((params.page() - 1) 
			            	* params.count(), params.page() * params.count());
			         $defer.resolve($scope.allNotificationDisplay);
			     }
			 });
		});
    
}]);