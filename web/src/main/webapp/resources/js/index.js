var indexModule = angular.module('indexModule', []);

indexModule.controller('loginCntrl', [
		'$scope',
		'$http',
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
						'X-Login-Ajax-call' : 'true'
					}
				};

				var response = $http(request);
				response.success(function(data) {
					var path = redirectByRole(data);
					$scope.loginForm.password = null;

					if (path)
						window.location.replace(path);
					if (path == undefined) {
						$scope.loginCorrect = true;
					}
				});
				response.error(function(data) {
					console.dir(data);
				});

				function redirectByRole(role) {
					var path = undefined;
					if (role == "ROLE_USER" || role == "ROLE_ADMIN"
							|| role == "ROLE_MODERATOR"
							|| role == "ROLE_SUPERADMIN" || role == "ROLE_DBA")
						path = 'home';

					return path;
				}

			};

		} ]);