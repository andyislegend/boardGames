angular.module('homeApp').controller("CtreateNewTournamentCtrl",function($scope,$http){

    $scope.createTournament = function () {
        var tournament = {
            "tournamentName": $scope.tournamentName,
            "countOfParticipants": $scope.countOfParticipants,
            "gameUserId": $scope.selectedGame,
            "date": $scope.date,
            "country": $scope.countryTournament,
            "city": $scope.cityTournament,
        };
        console.log(tournament);
        
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
        
        $http.post("/addTournament", tournament)
            .success(function (data) {
                console.log(data);
                $scope.$parent.tournaments=data;
            });
    }
});