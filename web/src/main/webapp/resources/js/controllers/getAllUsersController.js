angular.module('homeApp').controller("getAllUsersCtrl", function($scope, $http, $filter, $q, $timeout, ngTableParams) {
	$scope.users = [];
	$http.get('users').then(function(result) {
		$scope.users = result.data;
		$scope.$broadcast('sharingToUsersTable', $scope.users);
		$scope.showUser = false;
		$scope.getInfoAboutUserFunc = function(username) {			
			for (var i = 0; i < $scope.users.length; i++) {
				if ($scope.users[i].username === username) {
					$scope.oneUser = $scope.users[i];
					break;
				};
			};
			$http.get('allUsersGames?username='+ username).then(function(result) {
				$scope.games = result.data;
			});
			$scope.showModal = false;
			$scope.getInfoAboutGame = function(id) {
				$scope.showModal = !$scope.showModal;
				for (var i = 0; i < $scope.games.length; i++) {
					if ($scope.games[i].id === id) {
						$scope.oneGame = $scope.games[i];
						break;
					};
				};
			};
			$http.get('allUsersTournaments?username='+ username).then(function(result) {
				$scope.tournaments = result.data;
			});
			$scope.showModal1 = false;
			$scope.getInfoAboutTournament = function(tournamentId) {
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
	
	$scope.countries = function () {
		  var def = $q.defer();
		  $http.get('getAllCountries').then(function (result) {
		   var filterData = [];
		   angular.forEach(result.data, function (country) {
		    filterData.push({
		         id: country,
		         title: country
		        })
		   });
		   def.resolve(filterData);
		  });

		  return def;
	};
	
	$scope.cities = function () {
		  var def = $q.defer();
		  var countryName = $('select[name=countryName]').val();
		  $http.get('getAllCities?countryName=' + countryName).then(function (result) {
		   var filterData = [];
		   angular.forEach(result.data, function (city) {
		    filterData.push({
		         id: city,
		         title: city
		        })
		   });
		   def.resolve(filterData);
		  });

		  return def;
	};
	
/*	$http.get('getAllCountries').then(function(result) {
		$scope.countries = result.data;
		$scope.getCitiesByCountry = function() {
			var countryName = $('select[name=selectCountries]').val();
			$http.get('getAllCities?countryName=' + countryName).then(function(result) {
				$scope.cities = result.data;
				$scope.$broadcast('sharingAddress', { countries:$scope.countries, cities:$scope.cities});
			});
		};
	});*/

	$scope.$on('sharingToUsersTable', function(event, data) {
	$scope.usersTable = new ngTableParams({
		page: 1,
		count: 7
	}, {
		total: data.length,
		getData: function ($defer, params) {
				  for (var i = 0; i < data.length; i++) {
					  data[i].countryName = "";
					  data[i].cityName = "";
					  data[i].countryName = data[i].address.country.name;
					  data[i].cityName = data[i].address.city.name;
				  }
		    	 $scope.usersByParams = params.sorting() ? 
		      			$filter('orderBy')(data, params.orderBy()) 
		       			: data;
		       	 $scope.usersByParams = params.filter() ? 
		       			$filter('filter')($scope.usersByParams, params.filter()) 
		       			: $scope.usersByParams;
		         $scope.usersByParams = $scope.usersByParams.slice((params.page() - 1) 
		            	* params.count(), params.page() * params.count());
		         $defer.resolve($scope.usersByParams);
		     }
		 });
	});
});