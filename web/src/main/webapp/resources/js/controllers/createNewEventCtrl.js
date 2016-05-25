angular.module('homeApp').controller("newEventCtrl",function($scope, $http, $route){

    $scope.createTournament = function () {
    	
    	var newevent = {
    			
    			name : $scope.newevent.name,
    			description : $scope.newevent.description,
    			location : $scope.newevent.location,
    			date : $scope.newevent.date
    	}
    	

        $http({
			method : 'POST',
			url : '/addEvent',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : newevent
		}).success(function(response) {
			$route.reload;
		}, function errorCallback(response) {
		});
    }
    
    $scope.closeModal = function() {
		$('#myCreateEvent').modal('hide');
		$scope.eraseForm();
	}
    
    $scope.eraseForm = function() {

    	$scope.newevent.name = ' ';
		$scope.newevent.description = ' ';
		$scope.newevent.location = ' ';
		$scope.newevent.date = '';
	}
    
});