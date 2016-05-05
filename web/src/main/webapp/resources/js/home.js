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
            //controller : '...Ctrl'
        })

        .when('/tournaments', {
            templateUrl: 'resources/pages/home-tournaments.html',
            //controller : '...Ctrl'
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