angular.module('homeApp').controller("showAllTournamentsCtrl", function ($scope, $http) {
    $http.get('/tournaments').success(function (data) {
        $scope.tournaments = data;
    });

    $http({
        method : "GET",
        url : 'getAllGames'
    }).then(function mySucces(response) {
        $scope.games = response.data;
    }, function myError(response) {
        alert("Getting games general data error");
    });

    $scope.JoinTournament = function (elem) {
        var idTournament = elem;
        console.log(idTournament);
        $http.post("/joinTournament", idTournament)
            .success(function (data) {
                if($scope.tournaments == data){
                    alert("You've already join this tournament");
                }
                $scope.tournaments = data;

            });
    }
});