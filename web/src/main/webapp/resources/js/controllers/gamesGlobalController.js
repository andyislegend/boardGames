angular.module('homeApp').controller('getGamesGlobalController', function($scope, $http, $filter, ngTableParams) {

	$http({
		method : "GET",
		url : 'getAllGames'
	}).then(function mySucces(response) {
		$scope.gamesGlobal = response.data;
		$scope.$broadcast('sharingToInitGamesTable', $scope.gamesGlobal);	
	}, function myError(response) {
		alert("Getting games general data error");
	});
	
	$scope.$on('sharingToInitGamesTable',
		function(event, data) {
		$scope.allGamesTable = new ngTableParams({
		    page: 1,
		    count: 10
		 }, {
		     total: data.length, 
		     getData: function ($defer, params) {
		    	 
		    	 $scope.gamesGlobalDisplay = params.sorting() ? 
		      			$filter('orderBy')(data, params.orderBy()) 
		       			: data;
		       	 $scope.gamesGlobalDisplay = params.filter() ? 
		       			$filter('filter')($scope.gamesGlobalDisplay, params.filter()) 
		       			: $scope.gamesGlobalDisplay;
		         $scope.gamesGlobalDisplay = $scope.gamesGlobalDisplay.slice((params.page() - 1) 
		            	* params.count(), params.page() * params.count());
		         $defer.resolve($scope.gamesGlobalDisplay);
		     }
		 });
	});
});