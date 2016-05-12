angular.module('homeApp').controller("eventListCtrl", function($scope, $http) {
	  
	$scope.showText = false;
	  $scope.showForm = function() {
	        $scope.showText = !$scope.showText;
	    };
	
    $scope.events = [];
    $http({
        method: "GET",
        url: 'eventspage'
    }).then(function mySucces(response) {
        $scope.events = response.data;

    });

    $scope.addEvent = function() {
        var newEvent = {
            "name": $scope.input_name,
            "description": $scope.input_description,
            "date": $scope.input_date,
            "place": $scope.input_place,
            "game": $scope.input_game,
            "imgsrc": "resources/images/defaultImg.jpg"
        };



        var response = $http.post('addEvent', newEvent);
        response.success(function() {
            $scope.events.push(newEvent);

            $scope.input_name = "";
            $scope.input_description = "";
            $scope.input_place = "";
            $scope.input_game = "";
            $scope.input_date = new Date();

        });
    }

});