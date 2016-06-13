google.load("visualization", "1", {packages:["corechart"]});
google.charts.setOnLoadCallback(drawBubbleChart);

function drawBubbleChart(dataFrom) {
	
	var chartData = prepareBubbleChartData(dataFrom);
	var data = new google.visualization.DataTable();
	data.addColumn('string', 'Game');
	data.addColumn('number', 'Rating');
	data.addColumn('number', 'Count of rates');
	data.addColumn('string', 'Category');
	data.addColumn('number', 'Instances count')
	data.addRows(chartData);

	var options = {
	    title: 'Game ratings according to count of rates and instances of games',
	    hAxis: {title: 'Rating'},
	    vAxis: {title: 'Count of rates'},
	    bubble: {textStyle: {fontSize: 11}}
	};
	var chart = new google.visualization.BubbleChart(document.getElementById('bubblechart'));
	chart.draw(data, options);
}

function prepareBubbleChartData(dataToPrepare) {
	
	var chartData = [];
	for (i = 0; i < dataToPrepare.length; i++) {
		var temp = [dataToPrepare[i].name,
		            parseInt(dataToPrepare[i].generalRating),
		            parseInt(dataToPrepare[i].countOfRates),
		            dataToPrepare[i].category,
		            parseInt(dataToPrepare[i].countOfGames)];
		chartData.push(temp);
	}
	return chartData;
}