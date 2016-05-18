angular.module('homeApp').controller("showAllTournamentsCtrl", function ($scope, $http,$routeParams) {
    $http.get('/tournaments').success(function (result) {
        $scope.tournaments = result;
    });
     
    $http.get('tournament/'+ $routeParams.id).success(function(result) {
		$scope.tournament = result;		
	});
    
    $http.get('/getAllParticipants/'+$routeParams.id).success(function(result) {
		$scope.tournamentParticipants = result;
	})
    
    $scope.joinToTournament = function(id) {
    	$http.put('joinToTournament/'+id).success(function(result) {
		})
    }
    
    $scope.leaveTournament = function(id) {
    	$http.put('/leaveTournament/'+id).success(function(result) {
		});
    }
    
});