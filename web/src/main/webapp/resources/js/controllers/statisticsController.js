angular.module('homeApp').directive('hcChart', function () {
	return {
		restrict: 'E',
        template: '<div></div>',
        scope: {
        	options: '='
        },
        link: function (scope, element) {
        	Highcharts.chart(element[0], scope.options);
        }
    };
});
angular.module('homeApp').directive('hcPieChart', function() {
	return {
		restrict : 'E',
		template : '<div></div>',
		scope : {
			title : '@',
			data : '='
		},
		link : function(scope, element) {
			Highcharts.chart(element[0],{
					chart : {
						type : 'pie'
					},
					title : {
						text : scope.title
					},
					plotOptions : {
						pie : {
						allowPointSelect : true,
						cursor : 'pointer',
						dataLabels : {
							enabled : true,
							format : '<b>{point.name}</b>: {point.percentage:.1f} %'
						}
					}
				},
				series : [ {
					data : scope.data
				} ]
			});
		}
	};
});					
	
angular.module('homeApp').controller('statisticsController',function($scope, $http, $timeout) {
	
	$http({
		method : "GET",
		url : 'groupGamesByGameUsers'
	}).then(function mySucces(response) {
		$scope.$broadcast('settingPieData', response.data);
	}, function myError(response) {
		alert("getting groups of gameUser by game error");
	});
	
	$scope.$on('settingPieData', function(event, data) {
//		var name = [];
//		var y = [];
		for (var i = 0; i < data.length; i++) {
//			value.push(data[i].name);  
//			y.push(data[i].y);
			$scope.pieData = $scope.pieData.concat([
			     {name : data[i].name , y : data[i].y},
			]);
	    }
		console.log($scope.pieData);
		//$scope.pieData = $scope.pieData.concat(data);
	});
	
	$scope.chartOptions = {
		title : {
			text : 'Temperature data'
		},
		xAxis : {
			categories : [ 'Jan', 'Feb', 'Mar', 'Apr', 'May',
			'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov',
			'Dec' ]
		},
		series : [ {
			data : [ 29.9, 71.5, 106.4, 129.2, 144.0, 176.0,
			135.6, 148.5, 216.4, 194.1, 95.6, 54.4 ]
		} ]
	};
	
	
	$scope.pieData = [ {
		name : "Microsoft Internet Explorer",
		y : 0.0	}];
//	}, {
//		name : "Chrome",
//		y : 24.03
//	}, {
//		name : "Firefox",
//		y : 10.38
//	}, {
//		name : "Safari",
//		y : 4.77
//	}, {
//		name : "Opera",
//		y : 0.91
//	}, {
//		name : "Proprietary or Undetectable",
//		y : 0.2
//	} ];
	console.log($scope.pieData);
	
});