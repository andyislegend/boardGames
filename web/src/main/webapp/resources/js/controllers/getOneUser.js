angular.module('homeApp').controller("getUser", function($scope, $http) {
	$http.get('getUser').then(function(result) {
		$scope.user = result.data;
		var ratingInterval = 11;
		$scope.neededRating = ratingInterval - $scope.user.userRating % 10;
		document.getElementById("ratingBar").value= $scope.user.userRating;
	});
	
	$scope.hovering = false;
    $scope.showIt = function () {
            $scope.hovering = true;
    };
    $scope.hideIt = function () {
        $scope.hovering = false;
    };
});