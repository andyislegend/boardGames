angular.module('homeApp').controller("showAllTournamentsCtrl", function ($scope, $http,$routeParams) {
    $http.get('/tournaments').success(function (result) {
        $scope.tournaments = result;
    });
    
    
    $http.get('tournament/'+ $routeParams.id).success(function(result) {
		$scope.tournament = result;		
	}); 
    
});