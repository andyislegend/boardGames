angular.module('homeApp').controller(
		"eventUserCtrl",
		function($scope, $http, $filter, ngTableParams, $route) {

			$scope.allEvents = [];
			$http.get('allUsersEventsDTO').then(
					function(result) {
						$scope.allEvents = result.data;

						$scope.$broadcast('sharingToInitEventsUserTable',
								$scope.allEvents);

					});

			$scope.$on('sharingToInitEventsUserTable', function(event, data) {
				$scope.allEventsUserTable = new ngTableParams({
					page : 1,
					count : 8,
					sorting : {
						date : 'asc'
					}
				}, {
					total : data.length,
					getData : function($defer, params) {

						$scope.allEventsDisplay = params.sorting() ? $filter(
								'orderBy')(data, params.orderBy()) : data;
						$scope.allEventsDisplay = params.filter() ? $filter(
								'filter')($scope.allEventsDisplay,
								params.filter()) : $scope.allEventsDisplay;
						$scope.allEventsDisplay = $scope.allEventsDisplay
								.slice((params.page() - 1) * params.count(),
										params.page() * params.count());
						$defer.resolve($scope.allEventsDisplay);
					}
				});
			});
			
	});