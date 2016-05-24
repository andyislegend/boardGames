angular.module('homeApp').controller("ctreateNewEventCtrl",function($scope,$http,$route){

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
});