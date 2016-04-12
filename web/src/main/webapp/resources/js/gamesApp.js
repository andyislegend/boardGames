var app = angular.module('boardGamesApp', []);
app.controller('getGamesGlobalController', function($scope) {
    $scope.gamesGlobal = [
        {
            Name:"Chess", Category:"Strategic", 
            Description:"Most popular game ever",
            MinPlayers:"2", MaxPlayers:"2"
        },
        {
            Name:"Monopolly", Category:"Buisiness",
            Description:"Some awesome things about this game",
            MinPlayers:"3", MaxPlayers:"6"
        }
    ];
});
app.controller('getGamesMyController', function($scope) {
    $scope.gamesMy = [
        {Name:"Dangeons of dragons"},
        {Name:"Game of thrones"},
        {Name:"Chess"},
        {Name:"Awesome dudes"}
    ];
});

app.controller("listOfFriendsCtrl", function($scope, $http){
    $http.get('resources/json/friends.json').success(function(data) { 
    $scope.friends = data;
    }).error(function(error){
        console.log(error);
    }); 
});