angular.module('homeApp').controller("CtreateNewTournamentCtrl",function($scope,$http){

    $scope.createTournament = function () {
    	
    	var tournament = {
    			gameId : $scope.selectedGame,
    			name : $scope.tournamentName,
    			countOfParticipants : $scope.countOfParticipants,
    			dateOfTournament : $scope.date,
    			country : $scope.countryTournament,
    			city : $scope.cityTournament
    	}
    	
        $http({
			method : 'POST',
			url : '/addTournament',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : tournament
		}).success(function(response) {
			
		}, function errorCallback(response) {
		});
    }
});