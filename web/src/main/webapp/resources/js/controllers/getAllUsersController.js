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
		         id: country.name,
		         title: country.name
		        })
		   });
		   def.resolve(filterData);
		  });

		  return def;
	};

		$scope.searchCities = function() {
			var def = $q.defer();
			$http.get('getAllCities?countryName=' + $scope.country).then(function (result) {
				var filterData = [];
				angular.forEach(result.data, function (city) {
					filterData.push({
						id: city.name,
						title: city.name
					})
				});
				def.resolve(filterData);
			});
			return def;
		};

	$scope.$on('sharingToUsersTable', function(event, data) {
	$scope.usersTable = new ngTableParams({
		page: 1,
		total:7,
		count: 7
	}, {
		defaultSort: "asc",
		total: data.length,
		getData: function ($defer, params) {
				  for (var i = 0; i < data.length; i++) {
					  data[i].countryName = "";
					  if (data[i].country != null) {
						  data[i].countryName = data[i].country.name;
					  }	
					  data[i].cityName = "";
					  if (data[i].city != null) {
						  data[i].cityName = data[i].city.name;
					  }	
				  }
		    	 $scope.usersByParams = params.sorting() ? 
		      			$filter('orderBy')(data, params.orderBy()) 
		       			: data;
		       	 $scope.usersByParams = params.filter() ? 
		       			$filter('filter')($scope.usersByParams, params.filter()) 
		       			: $scope.usersByParams;
		       			params.total( $scope.usersByParams.length);
		         $scope.usersByParams = $scope.usersByParams.slice((params.page() - 1) 
		            	* params.count(), params.page() * params.count());
		         $defer.resolve($scope.usersByParams);
		     }
		 });
	});
});