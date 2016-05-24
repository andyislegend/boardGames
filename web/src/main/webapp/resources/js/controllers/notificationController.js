var app = angular.module('homeApp').controller("notificationCtrl", ['$scope', '$rootScope', '$http', '$interval', function($scope,$rootScope, $http, $interval) {
    
    $http.get('getCurrentUserName').success(function(data){
        $scope.currentUserName = data;
    }).error(function(error){
        console.log(error);
    });
	$http.get('getAllNotification').success(function(data){
        console.log(data);
        $scope.allNotification = data;
    }).error(function(error){
        console.log(error);
    });
    $scope.currentDate = new Date();
}]);