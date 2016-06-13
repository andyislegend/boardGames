angular.module('homeApp').controller('statisticsController',function($scope, $http, $timeout) {
    
	$scope.initPieChart = function() {
		$http({
			method : "GET", 
			url : 'groupGamesByGameUsers'
		}).then(function mySucces(response) {
			drawPieChart(response.data);
		}, function myError(response) {
			alert("getting groups of gameUser by game error");
		});
	};
	
	$scope.initBubbleChart = function() {
		$http({
			method : "GET",
			url : 'getGamesToRatings'
		}).then(function mySucces(response) {
			drawBubbleChart(response.data);
		}, function myError(response) {
			alert("getting ratings to games error");
		});
	};
	
	$scope.initAreaChart = function() {	
		$http({
			method : "GET",
			url : 'getCountOfActions'
		}).then(function mySucces(response) {
			drawAreaChart(response.data);
		}, function myError(response) {
			alert("getting actions and dates error");
		});
	};
	
	$scope.initColumnAgeChart = function() {
		
		$http({
			method : "GET",
			url : 'getUsersAvgAge'
		}).then(function mySucces(response) {
			drawColumnAgeChart(response.data);
		}, function myError(response) {
			alert("Drawing columnage chart error");
		});
	};
});