var app = angular.module('homeApp').controller("notificationCtrl", ['$scope', '$rootScope', '$http', '$interval','$filter','ngTableParams', '$q', function($scope,$rootScope, $http, $interval, $filter, ngTableParams, $q) {
    
	$http.get('getAllNotification').success(function(data){
        $scope.allNotification = data;
        $scope.$broadcast('allNotifications', $scope.allNotification);	
    }).error(function(error){
        console.log(error);
    });
    
    $scope.$on('allNotifications',
		function(event, data) {
			$scope.allNotificationTable = new ngTableParams({
			    page: 1,
			    count: 6
			 }, {
			     total: data.length, 
			     getData: function ($defer, params) {
			    	 
			    	 $scope.allNotificationDisplay = params.sorting() ? 
			      			$filter('orderBy')(data, params.orderBy()) 
			       			: data;
			       	 $scope.allNotificationDisplay = params.filter() ? 
			       			$filter('filter')($scope.allNotificationDisplay, params.filter()) 
			       			: $scope.gameNotificationsDisplay;
			         $scope.allNotificationDisplay = $scope.allNotificationDisplay.slice((params.page() - 1) 
			            	* params.count(), params.page() * params.count());
			         $defer.resolve($scope.allNotificationDisplay);
			     }
			 });
		});
    $scope.option = [
        'EVENT',
        'NOTIFICATION',
        'MESSAGE',
         ''
    ];
    $scope.typeOfNotification = function(){
        var option = ['EVENT',
        'NOTIFICATION',
        'MESSAGE'
        ];
        var def = $q.defer();
		   var filterData = [];
		   for(var i = 0; i < option.length; i++){
			   filterData.push({
			         id: option[i],
			         title: option[i]
	          })
		   }
		   
		   def.resolve(filterData);
		  return def;
    };
    
    $scope.setNotificationSend = function(option) {
		$http.post('setNotification', !option).success(function(data){
         }).error(function(error){
            console.log(error);
        });
	}
    
   $http.get('getStatusOfNotification').success(function(option){
       $scope.checkboxModel = option;
   }).error(function(error){
       console.log(error);
   });
    
}]);