var homeApp = angular.module('indexModule', [ 'pascalprecht.translate',
		'ngCookies' ]);

homeApp.controller('loginCntrl', [
		'$scope',
		'$http',
		'$timeout',
		function($scope, $http) {
			$scope.login = function() {

				$scope.loginCorrect = false;

				var loginData = 'username=' + $scope.loginForm.username
						+ '&password=' + $scope.loginForm.password;

				var request = {
					method : 'POST',
					url : 'authenticate',
					data : loginData,
					headers : {
						'Content-Type' : "application/x-www-form-urlencoded",

					}
				};

				var response = $http(request);
				response.success(function(data) {
					var path = redirectByState(data)
					$scope.loginForm.password = null;

					if (path === "home") {
						$scope.closeModal();
						$scope.showModalLoading();
						window.setTimeout(function() {
							window.location.replace(path);
						}, 2500)

					}

					else if (path === "under_verification") {
						$scope.closeModal();
						$scope.showModalVerification();
					}

					else if (path == "banned") {
						$scope.closeModal();
						$scope.showModalBanned();
					}

					else if (path === undefined) {
						$scope.loginCorrect = true;
					}

				});
				response.error(function(data) {
					console.dir(data);
				});

				function redirectByState(state) {
					var path = undefined;

					if (state == "ACTIVE") {
						path = 'home';
					}
					if (state == "UNDER_VERIFICATION") {
						path = 'under_verification';
					}
					if (state == "BANNED") {
						path = 'banned'
					}

					return path;
				}

			};

			$scope.eraseForm = function() {

				$scope.loginForm.password = '';
				$scope.loginForm.username = '';
				$scope.loginCorrect = false;
			}

			$scope.closeModal = function() {
				$('#myModal').modal('hide');
				$scope.eraseForm();
			}

			$scope.showModalVerification = function() {
				$('#myUnderVer').modal('show');
			}

			$scope.showModalBanned = function() {
				$('#myBanned').modal('show');
			}

			$scope.showModalLoading = function() {
				$('#myLoading').modal('show');
			}

		} ]);

homeApp.controller('registerCntrl', [ '$scope', '$http',
		function($scope, $http) {

			$scope.register = function() {

				var userRegistrationDTO = {
					username : $scope.regusername,
					email : $scope.regemail,
					firstName : $scope.regfirstName,
					lastName : $scope.reglastName,
					password : $scope.regpassword,
					confirmPassword : $scope.regconfirmPassword,
					gender : $scope.reggender
				}
				$http({
					method : 'POST',
					url : 'addNewUser',
					headers : {
						'Content-Type' : 'application/json'
					},
					data: userRegistrationDTO
				}).success(function(status) {
					$scope.closeModal();
					$scope.showModal();
				}).error(function(result, status) {
					$scope.regUserResp = result;
				})

			}

			$scope.eraseForm = function() {

				$scope.regUserResp = '';
				$scope.regusername = '';
				$scope.regemail = '';
				$scope.regfirstName = '';
				$scope.reglastName = '';
				$scope.regpassword = '';
				$scope.regconfirmPassword = '';
			}

			$scope.closeModal = function() {
				$('#myReg').modal('hide');
				$scope.eraseForm();
			}

			$scope.showModal = function() {
				$('#myRegSuccess').modal('show');
			}

		} ]);


homeApp.controller('userToUnbanCtrl', ['$scope', '$http', function($scope, $http) {
		$scope.sendmail = function() {
			$http.post('sendEmailOfBannedUser?letter='+ $scope.emailOfBannedUser)
			.success(function(result, status) {
	        });
		}
}]);
