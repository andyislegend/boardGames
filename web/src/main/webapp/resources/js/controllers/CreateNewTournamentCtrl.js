angular.module('homeApp').controller("CtreateNewTournamentCtrl",function($scope,$http){

    $scope.createTournament = function () {
        var tournament = {
            "tournamentName": $scope.tournamentName,
            "rating": $scope.requiredRating,
            "maxParticipants": $scope.maxParticipants,
            "date": $scope.date,
            "gameName": $scope.selectedGame,
            "country": $scope.countryTournament,
            "city": $scope.cityTournament,
            "addition":$scope.additionTournament
        };
        console.log(tournament);
        $http.post("/addTournament", tournament)
            .success(function (data) {
                console.log(data);
                $scope.$parent.tournaments=data;
            });

    }
});