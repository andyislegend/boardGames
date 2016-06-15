angular.module('homeApp').controller("showAllTournamentsCtrl", function ($scope, $rootScope, $http, $filter, $routeParams, ngTableParams) {
 	
	$http.get('/tournaments').success(function (result) {
      $scope.tournaments = result;
      $scope.$broadcast('allTournament', $scope.tournaments);
	});
	
    $http.get('/tournament/'+ $routeParams.id).success(function(result) {
    	$scope.tournament = result;
    	today = new Date();
    	$scope.isOpenGiveRate = false;
    	dateOfTournament = $scope.tournament.dateOfTournament;
 
    	if(today>dateOfTournament){
  			$scope.isOpenGiveRate = true;
  		}
    	
    	   $http.get('/getAllParticipants/'+$routeParams.id).success(function(result) {
    	    	$scope.tournamentParticipants = result;
    	    	
    	    $http.get("/getCurentUser").success(function(result) {
    	        $scope.currentUser = result;
    	        $scope.participantsForRate = [];
    	        
    	        if($scope.currentUser.username === $scope.tournament.userCreatorName){
    	             $scope.isCreator = true;
    	            }else {
    	             $scope.isCreator = false;
    	            }
    	        
    	        for(var i = 0; i<$scope.tournamentParticipants.length; i++) {
    	        	if(($scope.currentUser.username === $scope.tournamentParticipants[i].username)) {
  	        		continue;
    	        	}else{
    	        	$scope.participantsForRate.push($scope.tournamentParticipants[i]);
    	        	}
    	        }
    	        
    	        for(var i = 0; i<$scope.tournamentParticipants.length; i++) {
    	        	
    	    		if(($scope.currentUser.username === $scope.tournamentParticipants[i].username)) {    	  
    	    			$scope.joinStatus = false;
    	    			$scope.quitStatus = true;
    	    			
    	    			if($scope.tournament.tableGenerated === true || $scope.tournament.countOfParticipants === tournamentParticipants.length){
            	        	$scope.quitStatus = false;
            	        	
            	        }
    	    			break;
    	    		}
    	    		
    	    		if(($scope.currentUser.username !== $scope.tournamentParticipants[i].username)) {
    	    			$scope.joinStatus = true;
    	    			$scope.quitStatus = false;
    	    		}
    	    		
    	    		if($scope.tournament.tableGenerated === true){
        	        	$scope.quitStatus = false;
        	        }
    		     }          	    
    	    });
    	    		
    	    	if($scope.tournamentParticipants.length === 0) {
    	    		$scope.joinStatus = true;
    	    		$scope.quitStatus = false;
    	    		$scope.isCansRate = true;
    	    	}
    	    	if($scope.tournamentParticipants.length === 1) {
    	    		$scope.isCansRate = true;
    	    	}
    	  
    	    	if($scope.tournamentParticipants.length >= $scope.tournament.countOfParticipants) {
    	    		$scope.joinStatus = true;
    	    	}   	    	
    	    });
    });
    
    $scope.getAllTournaments = function(){
    	$http.get('/tournaments').success(function (result) {
            $scope.tournaments = result;
            $scope.$broadcast('allTournament', $scope.tournaments);
        });
    }
    
    $scope.getAllTournamentsSuchUserCreated = function(){
    	$http.get('/getAllTournamentsByCreator').success(function(result){
    	$scope.allTournamentsByCreator = result;
    	$scope.$broadcast('allTournament', $scope.allTournamentsByCreator);
    	});
    }
    
    
    $scope.$on('allTournament',
    		function(event, data) {
    		$scope.allTournaments = new ngTableParams({
    		    page: 1,
    		    count: 5
    		 },{
	     total: data.length, 
	     getData: function ($defer, params) {
	    	 
	    	 $scope.tournamentDisplay = params.sorting() ? 
	      			$filter('orderBy')(data, params.orderBy()) 
	       			: data;
	       	 $scope.tournamentDisplay = params.filter() ? 
	       			$filter('filter')($scope.tournamentDisplay, params.filter()) 
	       			: $scope.tournamentDisplay;
	         $scope.tournamentDisplay = $scope.tournamentDisplay.slice((params.page() - 1) 
	            	* params.count(), params.page() * params.count());
	         $defer.resolve($scope.tournamentDisplay);
	     }
	 });
  });
    
    $scope.joinToTournament = function(id) { 
     if($scope.tournament.countOfParticipants<=$scope.tournamentParticipants.length){
      $scope.status = "There are no seats available";
      
     }else{
      $http.put('joinToTournament/'+id).success(function(result) {  
    	       
    	  $http.get('/getAllParticipants/'+$routeParams.id).success(function(result) {
    		  $scope.tournamentParticipants = result;
       });
       
      });  
      $scope.tournamentParticipants.push($scope.currentUser);
      $scope.joinStatus = false;
		$scope.quitStatus = true;
      $http.get('/tournament/'+ $routeParams.id).success(function(result) {
         	$scope.tournament = result;});  
       }
    }
    
    $scope.leaveTournament = function(id) {      
     $http.put('/leaveTournament/'+id);
     
     for(var i = 0; i<$scope.tournamentParticipants.length;i++){
			if($scope.tournamentParticipants[i].id === $scope.currentUser.id) {
				$scope.tournamentParticipants.splice(i,1);
				$scope.joinStatus = true;
    			$scope.quitStatus = false;
			}
     } 
     $http.get('/tournament/'+ $routeParams.id).success(function(result) {
     	$scope.tournament = result;});     
    }
    
    $scope.deleteTournament = function(tournamentId){
    	$http.delete("/deleteTournament/"+tournamentId);
    	$('#deleteTournament').modal('hide');
    }
    
    $scope.giveRate = function(idUser, rate) {
    	$http.put('/giveUser/'+idUser+"/rate/"+rate);      
    	}
    
    
    $scope.updateTournamentParticipantsInfo = function(){
    	$http.get('/getAllParticipants/'+$routeParams.id).success(function(result) {
    		  $scope.tournamentParticipants = result; });  
    }
    
    $scope.generateTournamentTable = function(){
    	$scope.tournament.tableGenerated = true;
    	$http.put('/generateTournamentTable/'+$routeParams.id);
    }
    	    
    $scope.changeDataOfTournament = function(){
    	var curentDate = new Date();
    	if($scope.changeTournamentDate<curentDate){ 
    		$('#attentionToChangeData').modal('show'); 
    	}else{
    		$http.put("updateDateOfTournament/"+$scope.changeTournamentDate+"/"+$routeParams.id);
    		
    		$http.get('/tournament/'+ $routeParams.id).success(function(result) {
    	    $scope.tournament = result;});
    	} 	
    }
    
    $scope.getTournamentParticipants = function(){
    	$http.get('/getAllParticipants/'+$routeParams.id).success(function(result) {
  		  $scope.tournamentParticipants = result;
  		  var teams = [];
  		  var team = [];
  		  for(var i = 0; i<$scope.tournamentParticipants.length; i++){
  			  if (team.length > 1) {
  				teams.push(team);
  				team = [];
  			  }
  			  team.push($scope.tournamentParticipants[i].username)
  		  }
  		  teams.push(team);
  		
    var singleElimination = {
    	teams:   teams,
    	results : []
    }
    		 
    $(function() {
    	$('.demo').bracket({
    		init: singleElimination,
    	    save: function(){}, 
    	})
    	$('.tools').hide();
    })
    
    });
  }
});