google.load("visualization", "1", {packages:["corechart"]});
google.charts.setOnLoadCallback(drawPieChart);

function drawPieChart(dataFrom) {

	var chartData = preparePieChartData(dataFrom);
	var data = new google.visualization.DataTable();
	data.addColumn('string', 'Game');
	data.addColumn('number', 'Percentage');
	data.addRows(chartData);

	var options = {
		title : 'Instances of game statistics'
	};

	var chart = new google.visualization.PieChart(document.getElementById('piechart'));

	chart.draw(data, options);
}

function preparePieChartData(dataToPrepare) {
	
	var chartData = [];
	for (i = 0; i < dataToPrepare.length; i++) {
		var temp = [dataToPrepare[i].name,
		            parseInt(dataToPrepare[i].countOfGames)];
		chartData.push(temp);
	}
	return chartData;
}