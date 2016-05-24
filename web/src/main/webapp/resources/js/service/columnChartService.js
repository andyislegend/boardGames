google.load("visualization", "1", {packages:["bar"]});
google.charts.setOnLoadCallback(drawColumnChart);

function drawColumnChart(dataFrom) {
	
	var chartData = prepareColumnChartData(dataFrom);
	console.log(chartData);
	var data = new google.visualization.DataTable();
	data.addColumn('string', 'Game');
	data.addColumn('number', 'Rating');
	data.addColumn('number', 'Count of rates');
	data.addRows(chartData);

	var options = {
	    chart: {
	        title: 'Game rating',
	        subtitle: 'Average rating for each game',
	    }
	};
	var chart = new google.charts.Bar(document.getElementById('columnchart'));
	chart.draw(data, options);
}

function prepareColumnChartData(dataToPrepare) {
	
	var chartData = [];
	for (i = 0; i < dataToPrepare.length; i++) {
		var temp = [dataToPrepare[i].name,
		            parseInt(dataToPrepare[i].generalRating),
		            parseInt(dataToPrepare[i].countOfRates)];
		chartData.push(temp);
	}
	return chartData;
}