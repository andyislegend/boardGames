angular.module('homeApp').controller("CtreateNewTournamentCtrl",function($scope,$http,$route){

    $scope.createTournament = function () {
    	 
    	console.log();
    	
    	var tournament = {
    			gameId : $scope.selectedGame,
    			name : $scope.tournamentName,
    			countOfParticipants : $scope.countOfParticipants,
    			dateOfTournament : $scope.date,
    			countryId :$scope.countryTournament.id,
    			cityId : $scope.cityTournament.id
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
    	
    	$http.get('getUser').then(function(result) {
    		  $scope.user = result.data;
    		  var ratingInterval = 11;
    		  $scope.neededRating = ratingInterval - $scope.user.userRating % 10;
    		  document.getElementById("ratingBar").value= $scope.user.userRating;
    	});
    	$route.reload();
    	$http.put('/giveRate/'+2).then(function(result) {
    	})
    	;}
	});