angular.module('homeApp').controller('statisticsController',function($scope, $http, $timeout) {
    
	$http({
		method : "GET",
		url : 'groupGamesByGameUsers'
	}).then(function mySucces(response) {
		drawChart(response.data);
	}, function myError(response) {
		alert("getting groups of gameUser by game error");
	});
	
});