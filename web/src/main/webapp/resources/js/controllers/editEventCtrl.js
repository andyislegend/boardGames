angular.module('homeApp').controller("editEventCtrl", ['$scope', '$http', '$routeParams', '$location', function($scope, $http, $routeParams, $location) {
	
	var id = $routeParams.id;
	$scope.currentEvent;
	
	$http.get('getEventDTO?id='+ id).then(function(result) {
		$scope.currentEvent = result.data;
	
		$scope.editableName = $scope.currentEvent.name;
		$scope.editableDescription = $scope.currentEvent.description;
		$scope.editableLocation = $scope.currentEvent.location;
	
	});
	
	$scope.updateEvent = function() {
		var updatedEvent = {
				
			eventId : $scope.currentEvent.eventId,	
			name : $scope.editableName,
			description : $scope.editableDescription,
			location : $scope.editableLocation,
			date : $scope.editableDate,
			
			};
			$http({
				method : 'PUT',
				url : 'updateEvent',
				
				headers : {
					'Content-Type' : 'application/json'
				},
				data : updatedEvent
			}).success(function(result, status) {
				$location.path("moderateEvent");
			}).error(function(result, status) {
				
			})

		};
	
	$scope.cancelEvent = function() {
		$http.delete('cancelEvent?id='+ id).then(function mySucces(response) {
			$location.path("moderateEvent");
		}, function myError(response) {
			alert("Failed to send your request");
		});
	}
}]);

