angular.module('homeApp').controller("editProfileCtrl", ['$rootScope','$scope', '$http', '$routeParams', function($rootScope, $scope, $http, $routeParams) {
	$scope.userProfile;
	$scope.showPasswordChange = false;
	$scope.myProfile = false;
	$scope.myAvatarUpload = false;
	$scope.username = $routeParams.username;
	if ($routeParams.username==null) {
		$scope.username = "Logged in user";
	}
	$http.get('getProfile?username='+ $scope.username).then(function(result) {
		$scope.userProfile = result.data;
		if ($routeParams.username==null) {
			$scope.myProfile = true;
		}
		$scope.editableFirstName = $scope.userProfile.firstName;
		$scope.editableLastName = $scope.userProfile.lastName;
		$scope.editableEmail = $scope.userProfile.email;
		$scope.editableGender = $scope.userProfile.gender;
		$scope.editableAge = $scope.userProfile.age;
		$scope.editablePhoneNumber = $scope.userProfile.phoneNumber;
		$scope.editableCity = $scope.userProfile.city;
		$http.get('getUsersAvatar?username=' + $scope.userProfile.username).then(function(result) {
			$scope.avatar = result.data;
		});
		
		$http.get('getAllCountries').then(function(result) {
			$scope.countries = result.data;
			if ($scope.userProfile.country!=null) {
				for (i=0; i<$scope.countries.length; i++) {				
					if ($scope.countries[i].id==$scope.userProfile.country.id) {
						$scope.editableCountry = $scope.countries[i];
						break;
					}
				}
			}
			$scope.getCitiesByCountry = function() {
				if ($scope.editableCountry==null) {
					$scope.cities =[];
				} else {
					$http.get('getAllCities?countryId=' + $scope.editableCountry.id).then(function(result) {
						$scope.cities = result.data;
						if ($scope.userProfile.city!=null) {
							for (i=0; i<$scope.cities.length; i++) {
								if ($scope.cities[i].id==$scope.userProfile.city.id) {
									$scope.editableCity = $scope.cities[i];
									break;
								} 
							}
						}
					});
				}
			};
			angular.element(document).ready(function () {
				$scope.getCitiesByCountry();
			});
		});
		
		
		
	});
	
	$scope.saveUser = function() {
		var noCountryOrCitySelected = 0;
		$scope.countryId = ($scope.editableCountry == null)?noCountryOrCitySelected:$scope.editableCountry.id;
		$scope.cityId = ($scope.editableCity == null)?noCountryOrCitySelected:$scope.editableCity.id;
		
	var userDTO = {
		firstName : $scope.editableFirstName,
		lastName : $scope.editableLastName,
		email : $scope.editableEmail,
		gender : $scope.editableGender,
		age : $scope.editableAge,
		phoneNumber : $scope.editablePhoneNumber,
		countryId:$scope.countryId,
		cityId:$scope.cityId
		};
		$http({
			method : 'PUT',
			url : 'updateUser',
			
			headers : {
				'Content-Type' : 'application/json'
			},
			data : userDTO
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
			$scope.editProfileAnswer = result.data;
			if ($scope.editProfileAnswer == "") {
				$scope.editProfileAnswer = "You have typed inappropriate data";
			}
		})

	}

	$scope.saveNewUserPassword = function() {
		var userPasswordDTO = {
				oldPassword : $scope.editableOldPassword,
				newPassword : $scope.editableNewPassword,
				confirmPassword : $scope.editableConfirmPassword
				};
		$http({
			method : 'PUT',
			url : 'updateUserPassword',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : userPasswordDTO
		}).success(function(result, status) {
			$scope.editPasswordAnswer = result;
			$scope.editPasswordMessage = false;
		}).error(function(result, status) {
			$scope.editPasswordAnswer = result;
			$scope.editableOldPassword = '';
			 $scope.editableNewPassword = '';
			 $scope.editableConfirmPassword = '';
		})
	}
	
	homeApp.directive('fileModel', ['$parse', function ($parse) {
         return {
            restrict: 'A',
            link: function(scope, element, attrs) {
               var model = $parse(attrs.fileModel);
               var modelSetter = model.assign;
               
               element.bind('change', function(){
                  scope.$apply(function(){
                     modelSetter(scope, element[0].files[0]);
                  });
               });
            }
         };
      }]);

	$scope.uploadAvatar = function() {
		if ($scope.myFile == null) {
			return $scope.editAvatarAnswer = "FOTO_ISNT_CHOOSED";			
		}
		if ($scope.myFile.size > 5242880) {
			return $scope.editAvatarAnswer = "FOTO_SIZE";			
		}
		var file = $scope.myFile;
	    var fileUpload = new FormData();
	    fileUpload.append("fileUpload", file);
	    var uploadUrl = 'updateAvatar';
	    console.log(fileUpload)

	    $http.post(uploadUrl, fileUpload, {
	        withCredentials: true,
	        headers: {'Content-Type': undefined },
	        transformRequest: angular.identity
	    }).success(function(result, status){
	    	$scope.editAvatarAnswer = result;
	    	$scope.$emit('changeAvatar');
        })
        
        .error(function(result, status){
        	$scope.editAvatarAnswer = result;
        });

	};
	
	$scope.showPassword = function() {
		$scope.showPasswordChange = !$scope.showPasswordChange;
	}
	
}]);

var loadFile = function(event) {
	var output = document.getElementById('avatar');
	output.src = URL.createObjectURL(event.target.files[0]);
};