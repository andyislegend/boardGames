var app = angular.module('homeApp').controller("notificationCtrl", ['$scope', '$rootScope', '$http', '$interval', function($scope,$rootScope, $http, $interval) {
    
    $http.get('getCurrentUserName').success(function(data){
        $scope.currentUserName = data;
    }).error(function(error){
        console.log(error);
    });
    $scope.arrayOfNotification = [];
    function Notification(type, emergency, head, body, date){
        this.type = type;
        this.emergency = emergency;
        this.head = head;
        this.body = body;
        this.date = date;
    };
	$http.get('getAllLastMessage').success(function(data){
        for(var i = 0 ; i < data.length; i++){
            var emergency = !data[i].statusOfReading && (data[i].currentUser.username !== $scope.currentUserName);
            var messageNotification = new Notification('message', emergency, data[i].currentUser.firstName + " " + data[i].currentUser.lastName +  " :", data[i].message, data[i].date);
            $scope.arrayOfNotification.push(messageNotification);
        }       
    }).error(function(error){
        console.log(error);
    });
    
    $http.get('getAllCurrentUserTournament').success(function(data){
        for(var i = 0 ; i < data.length; i++){
            var emergency =  (data[i][2] - new Date().getTime()) <= 24*60*60*1000;
            var tournamentNotification = new Notification('Tournament', emergency, data[i][4] + ", " + data[i][5], data[i][1], data[i][2]);
            $scope.arrayOfNotification.push(tournamentNotification);
        }
    }).error(function(error){
        console.log(error);
    });
    $http.get('getAllCurrentUserEvent').success(function(data){
        for(var i = 0 ; i < data.length; i++){
            var emergency =  (data[i][4] - new Date().getTime()) <= 24*60*60*1000;
            var eventNotification = new Notification('Event', emergency, data[i][3],data[i][2] +  ": " +  data[i][1], data[i][4]);
            $scope.arrayOfNotification.push(eventNotification);
        }
    }).error(function(error){
        console.log(error);
    });
	$scope.specificClassLine = function(emergency, type){
        if(emergency && type == 'message'){
            return 'notreadmessage';
        }else if(emergency && type == 'Tournament'){
            return 'notification-line-tournament';
        };
    };
    $scope.specificClass = function(emergency, type){
        if(emergency && type == 'message'){
            return 'notreadmessage-body';
        }else if(emergency && type == 'Tournament'){
            return 'tournament-body';
        }; 
    }
	
}]);