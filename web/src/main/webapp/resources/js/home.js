var homeApp = angular.module('homeApp', ['ngRoute', 'ui.bootstrap']);

homeApp.config(function ($routeProvider) {
    $routeProvider

    	.when('/statistics', {
            templateUrl: 'resources/pages/home-statistics.html',
            //controller : "...Ctrl"

        })

        .when('/allGames', {
            templateUrl: 'resources/pages/home-allGames.html',
            //controller : '...Cntrl'

        })

        .when('/events', {
            templateUrl: 'resources/pages/home-events.html',
            //controller : '...Ctrl'
        })

        .when('/edit', {
            templateUrl: 'resources/pages/home-editUser.html',
            controller : 'editProfileCtrl'
        })

        .when('/tournaments', {
            templateUrl: 'resources/pages/home-tournaments.html',
            //controller : '...Ctrl'
        })
        
        .when('/users', {
            templateUrl: 'resources/pages/home-users.html',
            controller : 'getAllUsersCtrl'
        })

        .otherwise({
        redirectTo: '/statistics'
    });

});

homeApp.controller("getAvatar", function ($scope, $http) {
    $http.get('getAvatar').then(function (result) {
        $scope.avatar = result.data;
    });
});

homeApp.controller("editProfileCtrl", function($scope, $http) {
	$http.get('getProfile').then(function(result) {
		$scope.userProfile = result.data;
		$scope.editableFirstName = $scope.userProfile.firstName;
	    $scope.editableLastName = $scope.userProfile.lastName;
	    $scope.editableEmail = $scope.userProfile.email;
	    $scope.editableGender = $scope.userProfile.gender;
	    $scope.editableAge = $scope.userProfile.age;
	    $scope.editablePhoneNumber = $scope.userProfile.phoneNumber;
	});
	$scope.saveUser = function() {
		$http({
		    method: 'PUT',
		    url: 'updateUser',
		    data: $.param({
	            firstName: $scope.editableFirstName,
	            lastName: $scope.editableLastName,
	            username: $scope.editableUsername,
	            email: $scope.editableEmail,
	            gender: $scope.editableGender,
	            age : $scope.editableAge,
	            phoneNumber: $scope.editablePhoneNumber
	        }),
	        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
		})
		.success(function(result, status) {
			$scope.editProfileAnswer = result;
			$scope.userProfile.firstName = $scope.editableFirstName;
			$scope.userProfile.lastName = $scope.editableLastName;
			$scope.userProfile.username = $scope.editableUsername;
		    $scope.userProfile.email = $scope.editableEmail;
		    $scope.userProfile.gender = $scope.editableGender;
		    $scope.userProfile.age = $scope.editableAge;
		    $scope.userProfile.phoneNumber = $scope.editablePhoneNumber;
			$scope.editProfileMessage = false;
		})
		.error(function(result, status) {
			$scope.editProfileAnswer = result;
		})		
	}
	
	$scope.saveNewUserPassword = function() {
		$http({
		    method: 'PUT',
		    url: 'updateUserPassword',
		    data: $.param({
	            oldPassword: $scope.editableOldPassword,
	            newPassword: $scope.editableNewPassword,
	            confirmPassword: $scope.editableConfirmPassword,
	        }),
	        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
		})
		.success(function(result, status) {
			$scope.editPasswordAnswer = result;
			$scope.editPasswordMessage = false;
		})
		.error(function(result, status) {
			$scope.editPasswordAnswer = result;
		})		
	}
});

homeApp.controller("getAllUsersCtrl", function($scope, $http) {
	$scope.users = [];
	$http.get('users').then(function(result) {
		$scope.users = result.data;
		$scope.showUser = false;
		$scope.getInfoAboutUserFunc = function(username) {
			$scope.oneUser
			$scope.showUser = !$scope.showUser;
			for (var i = 0; i < $scope.users.length; i++) {
				if ($scope.users[i].username === username) {
					$scope.oneUser = $scope.users[i];
					break;
				};
			};
			$http.get('allUsersGames?username=' + username).then(function(result) {
				$scope.games = result.data;
			});
		    $scope.showModal = false;
		    $scope.getInfoAboutGame = function(id){
		        $scope.showModal = !$scope.showModal;
		        for (var i = 0; i < $scope.games.length; i++) {
					if ($scope.games[i].id === id) {
						$scope.oneGame = $scope.games[i];
						break;
					};
				};
		    };
		    $http.get('allUsersTournaments?username=' + username).then(function (result) {
		        $scope.tournaments = result.data;
		    });
		    $scope.showModal1 = false;
		    $scope.getInfoAboutTournament = function(tournamentId){
		        $scope.showModal1 = !$scope.showModal1;
		        for (var i = 0; i < $scope.tournaments.length; i++) {
					if ($scope.tournaments[i].tournamentId === tournamentId) {
						$scope.oneTournament = $scope.tournaments[i];
						break;
					};
				};
		    };
			$http.get('getUsersAvatar?username=' + $scope.oneUser.username).then(function(result) {
				$scope.userAvatar = result.data;
			});
		};
	});
	$http.get('getAllCountries').then(function(result) {
		$scope.countries = result.data;	
		$scope.getCitiesByCountry = function(){
			var countryName = $('select[name=selectCountries]').val();
			$http.get('getAllCities?countryName=' + countryName).then(function(result) {
				$scope.cities = result.data;
			});
		};
	});
});

homeApp.controller("getAllUsersWithNegativeRating", function($scope, $http) {
	$scope.usersWithNegRate = [];
	$http.get('getUsersWithNegativeRating').then(function(result) {
		$scope.usersWithNegRate = result.data;
		if ($scope.usersWithNegRate.length != 0) {
			$("#myModalBannedUsers").modal('show');
		        $scope.bannedUsers = ('These users have to be banned: ' + $scope.usersWithNegRate);        
		}
	});
});