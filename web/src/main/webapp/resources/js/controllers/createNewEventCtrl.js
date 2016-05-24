angular.module('homeApp').controller("ctreateNewEventCtrl",function($scope,$http,$route){

    $scope.createTournament = function () {
    	
    	var tournament = {
    			gameId : $scope.selectedGame,
    			name : $scope.tournamentName,
    			countOfParticipants : $scope.countOfParticipants,
    			dateOfTournament : $scope.date,
    			country : $scope.countryTournament,
    			city : $scope.cityTournament
    	}
    	
    	$scope.$on('addTournament',function(event, data) {
    		 data.push(tournament);
    	 });
        $http({
			method : 'POST',
			url : '/addTournament',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : tournament
		}).success(function(response) {
			$route.reload;
		}, function errorCallback(response) {
		});
    }
});