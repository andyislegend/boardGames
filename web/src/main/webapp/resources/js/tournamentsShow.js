/**
 * Created by Dora on 17.04.2016.
 */

var app = angular.module("usersGameApp", []);
app.controller("showAllTournaments", function ($scope,$http) {
    $http.get('/tournaments').success(function(data){
        $scope.tournaments=data;
    })
})
