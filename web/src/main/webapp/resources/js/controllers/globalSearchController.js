angular.module('homeApp').controller('GlobalSearchCTRL', function($scope, $http, $routeParams, ngTableParams) {
	
	$scope.searchWord = $scope.searchWord
		$http.get('searchBy/'+$routeParams.word).then(function(response) {			
			$scope.searchResult = response;
			$scope.tournaments = $scope.searchResult.data.tournaments;
			$scope.games = $scope.searchResult.data.gameUsers;
			$scope.events = $scope.searchResult.data.events;	
			$scope.$broadcast('globalSearch', $scope.games);
	  }) ;
	$scope.$on('globalSearch',
			function(event, data) {
			$scope.allFilesTable = new ngTableParams({
			    page: 1,
			    count: 5
			 }, {
			     total: data.length, 
			     getData: function ($defer, params) {
	                    $scope.games = data.slice((params.page() - 1) * params.count(), params.page() * params.count());
	                    $defer.resolve($scope.games);
	                }
			 });
		});
});