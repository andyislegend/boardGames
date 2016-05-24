angular.module('homeApp').controller('statisticsController',function($scope, $http, $timeout) {
    
	$http({
		method : "GET", 
		url : 'groupGamesByGameUsers'
	}).then(function mySucces(response) {
		drawPieChart(response.data);
	}, function myError(response) {
		alert("getting groups of gameUser by game error");
	});
	
	$http({
		method : "GET",
		url : 'getGamesToRatings'
	}).then(function mySucces(response) {
		drawColumnChart(response.data);
	}, function myError(response) {
		alert("getting ratings to games error");
	});
	
	$http({
		method : "GET",
		url : 'getCountOfActions'
	}).then(function mySucces(response) {
		drawAreaChart(response.data);
	}, function myError(response) {
		alert("getting actions and dates error");
	});
	
});