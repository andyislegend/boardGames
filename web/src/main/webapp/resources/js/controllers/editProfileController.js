angular.module('homeApp').controller("editProfileCtrl", function($scope, $http) {
	$scope.showPasswordChange = false;
	$http.get('getProfile').then(function(result) {
		$scope.userProfile = result.data;
		$scope.editableFirstName = $scope.userProfile.firstName;
		$scope.editableLastName = $scope.userProfile.lastName;
		$scope.editableEmail = $scope.userProfile.email;
		$scope.editableGender = $scope.userProfile.gender;
		$scope.editableAge = $scope.userProfile.age;
		$scope.editablePhoneNumber = $scope.userProfile.phoneNumber;

			$http.get('getAvatar').then(function(result) {
				$scope.avatar = result.data;
			});

	});
	$scope.saveUser = function() {
		$http({
			method : 'PUT',
			url : 'updateUser',
			data : $.param({
				firstName : $scope.editableFirstName,
				lastName : $scope.editableLastName,
				username : $scope.editableUsername,
				email : $scope.editableEmail,
				gender : $scope.editableGender,
				age : $scope.editableAge,
				phoneNumber : $scope.editablePhoneNumber
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).success(function(result, status) {
			$scope.editProfileAnswer = result;
			$scope.userProfile.firstName = $scope.editableFirstName;
			$scope.userProfile.lastName = $scope.editableLastName;
			$scope.userProfile.username = $scope.editableUsername;
			$scope.userProfile.email = $scope.editableEmail;
			$scope.userProfile.gender = $scope.editableGender;
			$scope.userProfile.age = $scope.editableAge;
			$scope.userProfile.phoneNumber = $scope.editablePhoneNumber;
			$scope.editProfileMessage = false;
		}).error(function(result, status) {
			$scope.editProfileAnswer = result;
		})

	}

	$scope.saveNewUserPassword = function() {
		$http({
			method : 'PUT',
			url : 'updateUserPassword',
			data : $.param({
				oldPassword : $scope.editableOldPassword,
				newPassword : $scope.editableNewPassword,
				confirmPassword : $scope.editableConfirmPassword,
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).success(function(result, status) {
			$scope.editPasswordAnswer = result;
			$scope.editPasswordMessage = false;
		}).error(function(result, status) {
			$scope.editPasswordAnswer = result;
		})
	}

});
var loadFile = function(event) {
    var output = document.getElementById('avatar');
    output.src = URL.createObjectURL(event.target.files[0]);
  };