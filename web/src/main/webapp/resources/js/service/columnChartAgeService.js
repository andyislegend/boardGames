google.load("visualization", "1", {packages:["bar"]});
google.charts.setOnLoadCallback(drawColumnAgeChart);

function drawColumnAgeChart(dataFrom) {
	
	var chartData = prepareColumnChartData(dataFrom);
	var data = new google.visualization.DataTable();
	data.addColumn('string', 'Game');
	data.addColumn('number', 'Age');
	data.addRows(chartData);

	var options = {
	    chart: {
	        title: 'Average age',
	        subtitle: 'Average age of users for game',
	    },
	    bars: 'horizontal' 
	};
	var chart = new google.charts.Bar(document.getElementById('columnagechart'));
	chart.draw(data, options);
}

function prepareColumnChartData(dataToPrepare) {
	
	var chartData = [];
	for (i = 0; i < dataToPrepare.length; i++) {
		var temp = [dataToPrepare[i].name,
		            parseInt(dataToPrepare[i].avgAge)];
		chartData.push(temp);
	}
	return chartData;
}