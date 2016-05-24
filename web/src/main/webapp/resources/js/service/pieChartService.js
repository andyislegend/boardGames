google.load("visualization", "1", {packages:["corechart"]});
google.charts.setOnLoadCallback(drawChart);

function drawChart(dataFrom) {

	var chartData = prepareChartData(dataFrom);
	console.log(chartData);
	var data = new google.visualization.DataTable();
	data.addColumn('string', 'Game');
	data.addColumn('number', 'Percentage');
	data.addRows(chartData);
	console.log(data);

	var options = {
		title : 'Instances of game statistics'
	};

	var chart = new google.visualization.PieChart(document.getElementById('piechart'));

	chart.draw(data, options);
}

function prepareChartData(dataToPrepare) {
	
	var chartData = [];
	for (i = 0; i < dataToPrepare.length; i++) {
		var temp = [dataToPrepare[i].name,
		            parseInt(dataToPrepare[i].countOfGames)];
		chartData.push(temp);
	}
	return chartData;
}