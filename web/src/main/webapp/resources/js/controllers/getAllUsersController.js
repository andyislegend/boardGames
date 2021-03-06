angular.module('homeApp').controller("getAllUsersCtrl", function($scope, $http, $filter, $q, $timeout, ngTableParams) {
	$scope.users = [];
	$http.get('users').then(function(result) {
		$scope.users = result.data;
		$scope.$broadcast('sharingToUsersTable', $scope.users);
		$scope.showUser = !$scope.showUser;

	});
	
	$scope.getInfoAboutUserFunc = function(username) {
		$http.get('getUserDTO?username='+ username).then(function(result) {
			$scope.oneUser = result.data;
		});
		$http.get('getUsersAvatar?username=' + username).then(function(result) {
			$scope.userAvatar = result.data;
		});
	};
	
	$scope.changeShow = function(index){
		if ($scope.isShow === index) {
			$scope.isShow = -1;
			return;
		}
		  $scope.isShow = index;
	}
	
	$scope.countries = function() {
		var def = $q.defer();
		$http.get('getAllCountries').then(function(result) {
			var filterData = [];
			angular.forEach(result.data, function(country) {
				filterData.push({
					id : country.name,
					title : country.name
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
						  $filter('orderBy')(data, params.orderBy()) : data;
				  $scope.usersByParams = params.filter() ? 
						  $filter('filter')($scope.usersByParams, params.filter()) : $scope.usersByParams;
				  params.total( $scope.usersByParams.length);
				  $scope.usersByParams = $scope.usersByParams.slice((params.page() - 1) 
						  * params.count(), params.page() * params.count());
				  $defer.resolve($scope.usersByParams);
		     }
		 });
	});
	
	$scope.banUser = function(username) {
		$http.put('banUser?username='+ username)
		.success(function(result, status) {
			$("#bannedUsers").modal('show');
			$scope.username = username;
	    	$scope.bannedUsers = result;
	    	$http.get('getUserDTO?username='+ username).then(function(result) {
				$scope.oneUser = result.data;
			});
        });
	}
	
	$scope.unbanUser = function(username) {
		$http.put('unbanUser?username='+ username)
		.success(function(result, status) {
			$("#bannedUsers").modal('show');
			$scope.username = username;
	    	$scope.bannedUsers = result;
	    	$http.get('getUserDTO?username='+ username).then(function(result) {
				$scope.oneUser = result.data;
			});
        });
	}
});