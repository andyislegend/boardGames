angular.module('homeApp').controller("eventAdminCtrl", function($scope, $http, $filter, $route, ngTableParams) {
	
	$scope.allEvents = [];
	$http.get('allEventsDTO').then(function(result) {
		$scope.allEvents = result.data;
		$scope.$broadcast('sharingToInitEventsTable', $scope.allEvents);
	});
	
	$scope.$on('sharingToInitEventsTable',
		function(event, data) {
		$scope.allEventsTable = new ngTableParams({
		    page: 1,
		    count: 5,
		    sorting: { date: 'asc' }
		 }, {
		     total: data.length, 
		     getData: function ($defer, params) {
		    	 
		    	 $scope.allEventsDisplay = params.sorting() ? 
		      			$filter('orderBy')(data, params.orderBy()) 
		       			: data;
		       	 $scope.allEventsDisplay = params.filter() ? 
		       			$filter('filter')($scope.allEventsDisplay, params.filter()) 
		       			: $scope.allEventsDisplay;
		         $scope.allEventsDisplay = $scope.allEventsDisplay.slice((params.page() - 1) 
		            	* params.count(), params.page() * params.count());
		         $defer.resolve($scope.allEventsDisplay);
		     }
		 });
	});
	
	$scope.cancelEvent = function(id) {
		$http.delete('cancelEvent?id='+ id).then(function mySucces(response) {
			$route.reload();
		}, function myError(response) {
			alert("Failed to send your request");
		});
	}
	
});