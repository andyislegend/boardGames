angular.module('homeApp').controller("eventAdminCtrl", function($scope, $http, $filter, $q, $timeout, ngTableParams) {
	
	$http({
		method : "GET",
		url : 'allEventsDTO'
	}).then(function mySucces(response) {
		$scope.allEvents = response.data;
		$scope.$broadcast('sharingToInitEventsTable', $scope.allEvents);	
	}, function myError(response) {
		alert("Getting events general data error");
	});
	
	$scope.$on('sharingToInitEventsTable',
		function(event, data) {
		$scope.allEventsTable = new ngTableParams({
		    page: 1,
		    count: 8
		 }, {
		     total: data.length, 
		     getData: function ($defer, params) {
		    	 
		    	 $scope.allEvents = params.sorting() ? 
		      			$filter('orderBy')(data, params.orderBy()) 
		       			: data;
		       	 $scope.allEvents = params.filter() ? 
		       			$filter('filter')($scope.gamesGlobalDisplay, params.filter()) 
		       			: $scope.allEvents;
		         $scope.allEvents = $scope.allEvents.slice((params.page() - 1) 
		            	* params.count(), params.page() * params.count());
		         $defer.resolve($scope.allEvents);
		     }
		 });
	});

});