var homeApp = angular.module('homeApp', [ 'ngRoute', 'ui.bootstrap', 'ngTable', 'ng.q', 'file-model', 'pascalprecht.translate']);

homeApp.config(function($routeProvider) {
	$routeProvider

	.when('/', {
		templateUrl : 'resources/pages/home-statistics.html',
	// controller : "...Ctrl"
	})
	
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
		controller : 'eventListCtrl'
	})

	.when('/edit/:username', {
		templateUrl : 'resources/pages/home-editUser.html',
		controller : 'editProfileCtrl'
	})
	
	.when('/edit/', {
		templateUrl : 'resources/pages/home-editUser.html',
		controller : 'editProfileCtrl'
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

homeApp.controller("getAvatar", function($scope, $http) {
	$http.get('getAvatar').then(function(result) {
		$scope.avatar = result.data;
	});
});

homeApp.controller('search', function($scope, $rootScope){
});

homeApp.config(function ($translateProvider) {
	  $translateProvider.translations('en', {
	    PROJECT_NAME:'Board Games: Exchange',
	    COUNTRY:'Country',
	    USER_LEVEL:'Your level is {{level}}',
	    CUR_RATING:'Your rating is {{rating}}',
	    NEEDED_RATING:'You need {{neededRating}} to achieve next level',
	    SEARCH:'Search',
	    WELCOME_BACK:'Welcome back, ',
	    EDIT_PROFILE:'Edit profile',
	    MESSAGES:'Messages',
	    USERS:'Users',
	    LOGOUT:'Logout',
	    ALL_GAMES:'All games',
	    EVENTS:'Events',
	    TOURNAMENTS:'Tournaments',
	    STATISTICS:'Statistics'
	  });
	  $translateProvider.translations('ua', {
		PROJECT_NAME:'Настільні ігри: Обмін',
		COUNTRY:'Країна',
		USER_LEVEL:'Ваше звання {{level}}',
	    CUR_RATING:'Ваш рейтинг {{rating}}',
	    NEEDED_RATING:'Вам потрібно набрати {{neededRating}} для отримання наступного звання',
	    SEARCH:'Пошук',
	    WELCOME_BACK:'З поверненням,',
	    EDIT_PROFILE:'Редагувати профіль',
	    MESSAGES:'Листи',
	    USERS:'Користувачі',
	    LOGOUT:'Вийти',
	    ALL_GAMES:'Всі ігри',
	    EVENTS:'Події',
	    TOURNAMENTS:'Турніри',
	    STATISTICS:'Статистика'
	  });
	  $translateProvider.preferredLanguage('en');
	  $translateProvider.useSanitizeValueStrategy('escape');
});

homeApp.controller('localizationController', function($scope, $translate) {
	$scope.changeLanguage = function (key) {
	    $translate.use(key);
	};
});
