angular.module('homeApp').controller("showAllTournamentsCtrl", function ($scope, $http) {
    $http.get('/tournaments').success(function (result) {
        $scope.tournaments = result;
    });
});