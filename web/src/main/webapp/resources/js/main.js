var app = angular.module("usersGameApp", ['ui.bootstrap']);

app.controller("MainController", function ($scope) {
    $scope.hideTournaments = true;
    $scope.hideAllGames = true;
});
app.controller("HideController", function ($scope) {
    $scope.championshipClick = function () {
        $scope.hideTournaments = false;
        $scope.hideAllGames = true;
    }
    $scope.allGamesClick = function () {
        $scope.hideTournaments = true;
        $scope.hideAllGames = false;
    }
});

app.controller("allUsersGameCtrl", function ($scope, $http) {
    $http.get('getAllGamesCurUser').then(function (result) {
        $scope.allGame = result.data;
        $scope.showMe = false;
        $scope.myFunc = function (id) {
            $scope.games = [];
            $scope.showMe = !$scope.showMe;
            for (var i = 0; i < $scope.allGame.length; i++) {
                if ($scope.allGame[i].id === id) {
                    $scope.games[0] = $scope.allGame[i];
                    break;
                }
            }
        }
    });
});

app.controller("CreateGameCtrl", function ($scope, $http, $window) {
    $scope.showText = false;
    $scope.categories = [];
    $scope.showForm = function () {
        $scope.showText = !$scope.showText;
    };
    $http.get('getAllCategories').then(function (result) {
        $scope.categories = result.data;
    });
    $scope.list = [];
    $scope.submit = function () {
        var userGame = {
            "edition": $scope.edition,
            "yearOfProduction": $scope.year,
            "game": {
                "name": $scope.name,
                "category": {"id": $scope.category},
                "description": $scope.description,
                "rules": $scope.rules,
                "maxPlayers": $scope.maxPlayers,
                "minPlayers": $scope.minPlayers
            }
        };
        var response = $http.post('NewGame', userGame);
        response.success(function (data, status, headers, config) {
            $scope.list.push(data);

        });
    };
});

app.controller("eventListCtrl", function ($scope, $http) {
    $scope.events = [];
    $http({
        method: "GET",
        url: 'eventspage'
    }).then(function mySucces(response) {
        $scope.events = response.data;
    }, function myError(response) {
        $scope.events = [{
            "name": "Everybody sleeps but mafia members wake up",
            "description": "Mafia event",
            "date": 1465160400000,
            "place": "Lviv",
            "game": "Mafia",
            "user": "Super",
            "imgsrc": "resources/images/mafiaImg.jpg"
        }];
    });
});

app.controller("listOfFriendsCtrl", function ($scope, $http) {
    console.log("in controller list of Friends");
    $http.get("allFriends").success(function (data) {
        $scope.friends = data;
    }).error(function (error) {
        console.log(error);
    })
});

app.controller("countOfOffering", function ($scope, $http) {
    console.log("countOfOffering");
    $http.get("allOffering").success(function (data) {
        $scope.count = data;
    }).error(function (error) {
        console.log(error);
    })
});

app.controller("OfferToFriendCtrl", function ($scope, $uibModal) {
    $scope.open = function () {
        console.log("befor open");
        $uibModal.open({
            templateUrl: 'OfferingForm.html'
        });
    };
});

/*users Angular controller -- start*/

app.controller("getAllUsersCtrl", function ($scope, $http) {
    $scope.users = [];
    $http.get('users').then(function (result) {
        $scope.users = result.data;
        $scope.showUser = false;
        $scope.getInfoAboutUserFunc = function (id) {
            $scope.showUser = !$scope.showUser;
            for (var i = 0; i < $scope.users.length; i++) {
                if ($scope.users[i].id === id) {
                    $scope.oneUser = $scope.users[i];
                    break;
                }
                ;
            }
            ;
        };
    });
});

app.controller("getAllUsersGames", function ($scope, $http) {
    $scope.showUsersGames = false;
    $scope.getInfoAboutUserGames = function (userName) {
        $scope.showUsersGames = !$scope.showUsersGames;
        $http.get('allUsersGames?userName=' + userName).then(function (result) {
            $scope.games = result.data;
        });

    };
});

app.controller("getAllUsersTournaments", function ($scope, $http) {
    $scope.showUsersTournaments = false;
    $scope.getInfoAboutUsersTournaments = function (userName) {
        $scope.showUsersTournaments = !$scope.showUsersTournaments;
        $http.get('allUsersTournaments?userName=' + userName).then(function (result) {
            $scope.games = result.data;
        });

    };
});

app.directive('modal', function () {
    return {
        template: '<div class="modal fade">' +
        '<div class="modal-dialog">' +
        '<div class="modal-content">' +
        '<div class="modal-header">' +
        '<p>Last Name:{{oneUser.lastName}}</p>' +
        '<p>First Name:{{oneUser.firstName}}</p>' +
        '<p>Username:{{oneUser.username}}</p>' +
        '<p>Sex:{{oneUser.sex}}</p>' +
        '<p>Age:{{oneUser.age}}</p>' +
        '<p>Rating:{{oneUser.rating}}</p>' +
        '</div>' +
        '<div class="modal-body" ng-transclude></div>' +
        '</div>' +
        '</div>' +
        '</div>',
        restrict: 'E',
        transclude: true,
        replace: true,
        scope: true,
        link: function postLink(scope, element, attrs) {
            scope.$watch(attrs.visible, function (value) {
                if (value == true)
                    $(element).modal('show');
                else
                    $(element).modal('hide');
            });

            $(element).on('shown.bs.modal', function () {
                scope.$apply(function () {
                    scope.$parent[attrs.visible] = true;
                });
            });

            $(element).on('hidden.bs.modal', function () {
                scope.$apply(function () {
                    scope.$parent[attrs.visible] = false;
                });
            });
        }
    };
});

/*users Angular controller -- end*/


app.controller('getGamesGlobalController', function ($scope, $http) {

    $http({
        method: "GET",
        url: 'getAllGames'
    }).then(function mySucces(response) {
        $scope.gamesGlobal = response.data;
    }, function myError(response) {
        alert("Getting games general data error");
    });

    $scope.gameSelect = function (obj, $event) {

        $scope.gameDetailsShown = true;

        $http({
            method: "GET",
            url: 'getGameDetails' + '/' + obj
        }).then(function mySucces(response) {
            $scope.gameDetail = response.data;
        }, function myError(response) {
            alert("Getting games general data error");
        });

        $http({
            method: "GET",
            url: 'getUserGamesOfGame' + '/' + obj
        }).then(function mySucces(response) {
            $scope.userGamesOfGame = response.data;
        }, function myError(response) {
            alert("Getting games userGames of game error");
        });
    }

    $scope.hideGameDetails = function () {
        $scope.gameDetailsShown = false;
    }
});

app.controller("showAllTournaments", function ($scope, $http) {
    $http.get('/tournaments').success(function (data) {
        $scope.tournaments = data;
    });

    $scope.JoinTournament = function (elem) {
        var idTournament = elem;
        console.log(idTournament);
        $http.post("/joinTournament", idTournament)
            .success(function (data) {
                if (data != null) {
                    $scope.tournaments = data;
                }
                else {
                    alert("Null");
                }

            });
    }
});

app.controller("AddTournament", function ($scope, $uibModal, $http) {
    $scope.addTournament = function () {

        $uibModal.open({
            templateUrl: 'AddTournament.html'
        });

    }
    $scope.createTournament = function () {
        alert("ENTER");
        var e = document.getElementById("inputselectGame");
        var gameName = e.options[e.selectedIndex].value;
        var tournament = {
            tournamentName: (document.getElementById('inputTournamentName')).value,
            rating: document.getElementById('inputTournamentRating').value,
            maxParticipants: document.getElementById('inputTournamentParticipants').value,
            date: document.getElementById('inputTournamentDate').value,
            gameName: gameName,
            country: document.getElementById('inputTournamentCountry').value,
            city: document.getElementById('inputTournamentCity').value,
            street: document.getElementById('inputTournamentStreet').value,
            houseNumber: document.getElementById('inputTournamentBuilding').value,
            roomNumber: document.getElementById('inputTournamentApartment').value
        };

        $http.post("/addTournament", tournament)
            .success(function () {

            });

    }

});
