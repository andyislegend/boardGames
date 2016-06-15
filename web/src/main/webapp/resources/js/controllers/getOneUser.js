angular.module('homeApp').controller("getUser", ['$rootScope','$scope', '$http', function($rootScope, $scope, $http) {
	$scope.user;
	$http.get('getUser').then(function(result) {
		$scope.user = result.data;
		var ratingInterval = 11;
		$scope.neededRating = ratingInterval - $scope.user.userRating % 10;
		document.getElementById("ratingBar").value= $scope.user.userRating;
		$http.get('getUsersAvatar?username=' + $scope.user.username).then(function(result) {
			$scope.avatar = result.data;
		});
	});
	
	$rootScope.$on('changeAvatar', function(event) {
		$http.get('getUsersAvatar?username=' + $scope.user.username).then(function(result) {
			$scope.avatar = result.data;
		});
	});   
}]);