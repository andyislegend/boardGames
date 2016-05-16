angular.module('homeApp').controller("getUser", function($scope, $http) {
	$http.get('getUser').then(function(result) {
		$scope.user = result.data;
		document.getElementById("ratingBar").value= $scope.user.userRating;
	});
	
});