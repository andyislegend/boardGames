angular.module('homeApp').controller("getAllUsersWithNegativeRating",function($scope, $http) {
	$scope.usersWithNegRate = [];
	$http.get('getUsersWithNegativeRating').then(function(result) {
		$scope.usersWithNegRate = result.data;
		if ($scope.usersWithNegRate.length != 0) {
			$("#myModalBannedUsers").modal('show');
			$scope.bannedUsers = ('These users have to be banned: ' + $scope.usersWithNegRate);
		}
	});
});