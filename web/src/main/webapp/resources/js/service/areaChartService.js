google.load("visualization", "1", {packages:["corechart"]});
google.charts.setOnLoadCallback(drawAreaChart);
function drawAreaChart(dataFrom) {
	
	var chartData = prepareAreaChartData(dataFrom);
	var data = new google.visualization.DataTable();
	data.addColumn('string', 'date');
	data.addColumn('number', 'tournaments');
	data.addColumn('number', 'events');
	data.addColumn('number', 'exchanges');
	data.addRows(chartData);

	var options = {
		title : 'Activity',
		hAxis : {
			title : 'Intensity',
			titleTextStyle : {
				color : '#333'
			}
		},
		vAxis : {
			minValue : 0
		}
	};

	var chart = new google.visualization.AreaChart(document
			.getElementById('areachart'));
	chart.draw(data, options);
}

function prepareAreaChartData(dataToPrepare) {

	var chartData = [];
	for (i = 0; i < dataToPrepare.length; i++) {
		var temp = [ dataToPrepare[i].date,
				parseInt(dataToPrepare[i].tournaments),
				parseInt(dataToPrepare[i].events),
				parseInt(dataToPrepare[i].exchanges)
				];
		chartData.push(temp);
	}
	return chartData;
}