var app = angular.module('homeApp').controller("notificationCtrl", ['$scope', '$rootScope', '$http', '$interval', function($scope,$rootScope, $http, $interval) {
	$scope.message = "Hello";
	$http.get('getAllLastMessage').success(function(data){
        $scope.allLastMessages = data;
    }).error(function(error){
        console.log(error);
    });
}]);