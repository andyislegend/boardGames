angular.module('homeApp').controller("showAllTournamentsCtrl", function ($scope, $rootScope, $http, $routeParams,ngTableParams) {
 
 $http.get('/tournaments').success(function (result) {
        $scope.tournaments = result;
    });
     
    $http.get('tournament/'+ $routeParams.id).success(function(result) {
  $scope.tournament = result;
  today = new Date();
  $scope.isOpenGiveRate = false;
  dateOfTournament = $scope.tournament.dateOfTournament;
  if(today>dateOfTournament){
   $scope.isOpenGiveRate = true;
  }
 });
    
    $scope.allTournaments = new ngTableParams({
	    page: 1,
	    count: 5
	 });
     
    $http.get('/getAllParticipants/'+$routeParams.id).success(function(result) {
  $scope.tournamentParticipants = result;
  for(a in $scope.tournamentParticipants) {
      console.log(a.id+" ppppp");
  }
  
 });
    
    $http.get("/getCurentUser").success(function(result) {
     $scope.currentUser = result;
     if($scope.currentUser.username === $scope.tournament.userCreatorName){
      $scope.isCreator = true;
     }else {
      $scope.isCreator = false;
     }
    });
    
    $scope.joinToTournament = function(id) { 
     if($scope.isContains($scope.currentUser.username)) {
      $scope.status = "You are joined";
      
     }if($scope.tournament.countOfParticipants<=$scope.tournamentParticipants.length){
      $scope.status = "There are no seats available";
      
     }else{
      $http.put('joinToTournament/'+id).success(function(result) {  
       $http.get('/getAllParticipants/'+$routeParams.id).success(function(result) {
        $scope.tournamentParticipants = result;
       });
       $scope.status = "";
       $scope.tournamentParticipants.push($scope.currentUser);
      });     
      location.reload();
     }
    }
    
    $scope.isContains = function(username){
     for(var i = 0; i<$scope.tournamentParticipants.length-1; i++) {
      if($scope.tournamentParticipants[i].username === username) {
       return true;
      }else{
       return false;
      }   
     }
    }
    
    $scope.leaveTournament = function(id) {  
     if($scope.isContains($scope.currentUser.username)){
     $http.put('/leaveTournament/'+id).success(function(result) {
  });
     $scope.status = "";
     console.log($scope.tournamentParticipants.indexOf($scope.currentUser));
     delete $scope.tournamentParticipants[$scope.tournamentParticipants.indexOf($scope.currentUser)+1];
     location.reload();
     }else{
      $scope.status = "You are't not joined";
     }     
    } 
    
    $scope.giveRate = function(idUser, rate) {
     console.log(idUser,rate);
     $http.put('/giveUser/'+idUser+"/rate/"+rate).success(function(result) {
      console.log('Rate success transfer');
     });  
     }
    
    $scope.changeDataOfTournament = function(){
    	$http.put("updateDateOfTournament/"+$scope.changeTournamentDate+"/"+$routeParams.id).success(function(result) {
		
    	});
    	location.reload();
    }
    
});