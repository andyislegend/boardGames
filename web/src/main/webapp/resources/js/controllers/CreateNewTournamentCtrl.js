angular.module('homeApp').controller("CtreateNewTournamentCtrl",function($scope,$http){

    $scope.createTournament = function () {
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