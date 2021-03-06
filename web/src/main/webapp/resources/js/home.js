var homeApp = angular.module('homeApp', [ 'ngRoute', 'ui.bootstrap', 'ngTable', 'ng.q', 'file-model', 'pascalprecht.translate', 'ngCookies', 'angularUtils.directives.dirPagination', 'ngMaterial']);

homeApp.config(function($routeProvider) {
	$routeProvider

	.when('/', {
		templateUrl : 'resources/pages/home-statistics.html',
	    controller : 'localizationController'
	})
	
	.when('/statistics', {
		templateUrl : 'resources/pages/home-statistics.html',
		controller : 'localizationController'
	})

	.when('/allGames', {
		templateUrl : 'resources/pages/home-allGames.html',
		controller : 'getGamesGlobalController'
	})

	.when('/gameSelect/:id/:name', {
		templateUrl : 'resources/pages/home-gameDetails.html',
		controller : 'gameSelectController'
	})
	
	.when('/editEvent/:id', {
		templateUrl : 'resources/pages/home-editEvent.html',
		controller : 'editEventCtrl'
	})

	.when('/eventInfo/:id', {
		templateUrl : 'resources/pages/home-eventInfo.html',
	    controller : 'eventInfoCtrl'
	})
	
	.when('/events', {
		templateUrl : 'resources/pages/home-events.html',
		controller : 'eventUserCtrl'
	})
	
	.when('/moderateEvent', {
		templateUrl : 'resources/pages/home-editEventsByAdmin.html',
		controller : 'eventAdminCtrl'
	})

	.when('/edit/:username', {
		templateUrl : 'resources/pages/home-editUser.html',
		controller : 'editProfileCtrl',
		controller : 'getGameDetailedInfoController'
	})
	
	.when('/edit/', {
		templateUrl : 'resources/pages/home-editUser.html',
		controller : 'editProfileCtrl',
		reloadOnSearch: false
	})
	.when('/notification', {
		templateUrl : 'resources/pages/home-notification.html',
		controller : 'notificationCtrl'
	})

	.when('/tournaments', {
		templateUrl : 'resources/pages/home-tournaments.html',
	    controller : 'showAllTournamentsCtrl'
	})

	.when('/users', {
		templateUrl : 'resources/pages/home-users.html',
		controller : 'getAllUsersCtrl'
	})
	
	.when('/search/:word', {
		templateUrl : 'resources/pages/home-GlobalSearch.html',
		controller : 'GlobalSearchCTRL'
	})
	
	.when('/gameUserDetails/:id', {
		templateUrl : 'resources/pages/home-gameUserDetails.html',
		controller : 'getGameDetailedInfoController', 
		controller : 'gameNotifyController',
		controller : 'CreateGameCtrl'
	})
	.when('/gameNotifications/:username', {
		templateUrl : 'resources/pages/home-gameNotifications.html',
		controller : 'allUsersGameCtrl'
	})
	
	.when('/gameEdit/:id', {
		templateUrl : 'resources/pages/home-editGame.html',
		controller : 'allUsersGameCtrl'
	})
	
	.when('/tournament/:id', {
		templateUrl : 'resources/pages/home-tournamentDetails.html',
	    controller : 'showAllTournamentsCtrl'
	})
	
	.otherwise({
		redirectTo : '/statistics'
	})

});

homeApp.controller("getCurrentUser",function($scope, $rootScope, $http){
	$http.get("/getCurentUser").success(function(result) {
		$rootScope.currentUser = result.data;
	});
});

