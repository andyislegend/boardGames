var indexModule = angular.module('indexModule', ['ngRoute']);


indexModule.config(function($routeProvider) {
    $routeProvider

        
        .when('/', {
            templateUrl : 'resources/pages/index-home.html'
            
        })

        
        .when('/login', {
            templateUrl : 'resources/pages/login.jsp',
//            controller: 'loginCntrl'
            		
        })

        
        .when('/registration', {
            templateUrl : 'resources/pages/index-registration.html'
            
        });
});

//indexModule.controller('loginCntrl', ['$scope', '$http', function ($scope, $http) {
//    $scope.login = function () {
//    	
//    	$scope.incorrectStyle = false;
//    	$scope.incorrectStylePlaceholder = false;
//    	$scope.loginCorrect = false;
//    	
//        var loginData = 'username=' + $scope.loginForm.username
//            + '&password=' + $scope.loginForm.password;
//
//        var request = {
//            method: 'POST',
//            url: 'authenticate',
//            data: loginData,
//            headers: {
//                'Content-Type': "application/x-www-form-urlencoded",
//                'X-Login-Ajax-call': 'true'
//            }
//        };
//
//        var response = $http(request);
//        response.success(function (data) {
//            var path = 'home';
//            $scope.loginForm.password = null;
//        
//            if (path)
//                window.location.replace(path);
//            if (path == undefined){
//            	$scope.loginCorrect = true;
//            	$scope.incorrectStyle = true;
//            	$scope.incorrectStylePlaceholder = true;
//            }
//        });
//        response.error(function (data) {
//            console.dir(data);
//        });
//
//   };
//
//}]);


