angular.module('homeApp').controller('GlobalSearchCTRL', function($scope, $http, $routeParams, ngTableParams) {
	
	$scope.searchWord = $scope.searchWord
		$http.get('searchBy/'+$routeParams.word).then(function(response) {			
			$scope.searchResult = response;
			$scope.tournament = $scope.searchResult.data.tournaments;
			$scope.games = $scope.searchResult.data.gameUsers;
			$scope.events = $scope.searchResult.data.events;	
			$scope.searchResult = [{game:$scope.games},
					   			   {tournaments:$scope.tournaments},
					   			   {events:$scope.events}]
			$scope.$broadcast('globalSearch', $scope.searchResult);
	  }) ;
	$scope.$on('globalSearch',function(event, data) {		
			$scope.allFilesTable = new ngTableParams({
			    page: 1,
			    count: 5
			 }, {
			     total: $scope.searchResult[0].length, 
			     getData: function ($defer, params) {
			    	// $scope.searchResult.games = data.slice((params.page() - 1) * params.count(), params.page() * params.count());
			    	 	$scope.searchResult = data.slice((params.page() - 1) * params.count(), params.page() * params.count());
	                    $defer.resolve($scope.searchResult);
	                    //$defer.resolve($scope.searchResult.tournaments);
	                }
			 });
		});
});