var homeApp = angular.module('homeApp', [ 'ngRoute', 'ui.bootstrap', 'ngTable', 'ng.q']);

homeApp.config(function($routeProvider) {
	$routeProvider

	.when('/statistics', {
		templateUrl : 'resources/pages/home-statistics.html',
	// controller : "...Ctrl"
	})

	.when('/allGames', {
		templateUrl : 'resources/pages/home-allGames.html',
		controller : 'getGamesGlobalController'
	})

	.when('/gameSelect/:id/:name', {
		templateUrl : 'resources/pages/home-gameDetails.html',
		controller : 'gameSelectController'
	})

	.when('/events', {
		templateUrl : 'resources/pages/home-events.html',
	// controller : '...Ctrl'
	})

	.when('/edit', {
		templateUrl : 'resources/pages/home-editUser.html',
		controller : 'editProfileCtrl'
	})

	.when('/tournaments', {
		templateUrl : 'resources/pages/home-tournaments.html',
	// controller : '...Ctrl'
	})

	.when('/users', {
		templateUrl : 'resources/pages/home-users.html',
		controller : 'getAllUsersCtrl'
	})
	.when('/search/:word', {
		templateUrl : 'resources/pages/home-GlobalSearch.html',
		controller : 'search'
	})
	.when('/gameUserDetails/:id', {
		templateUrl : 'resources/pages/home-gameUserDetails.html',
		controller : 'getGameDetailedInfoController'
	})
	.when('/gameEdit/:id', {
		templateUrl : 'resources/pages/home-editGame.html',
		controller : 'allUsersGameCtrl'
	})

	.otherwise({
		redirectTo : '/statistics'
	});

});

homeApp.controller("getAvatar", function($scope, $http) {
	$http.get('getAvatar').then(function(result) {
		$scope.avatar = result.data;
	});
});

homeApp.controller('search', function($scope, $rootScope){
});