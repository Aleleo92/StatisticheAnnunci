<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>Confronto motore su periodi differenti</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="http://code.jquery.com/jquery-1.9.1.js"
	type="text/javascript"></script>
<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="http://code.highcharts.com/highcharts-3d.js"></script>
<!--     <script src="http://code.highcharts.com/modules/exporting.js" type="text/javascript"></script> -->

<script type="text/javascript">
	var range1 = ${range1};
	var range2 = ${range2};
	var from1 = '${cmpFrom1}'.substring(5);
	var from2 = '${cmpFrom2}'.substring(5);
	var to1 = '${cmpTo1}'.substring(5);
	var to2 = '${cmpTo2}'.substring(5);
	var strRange1 = from1 +  ' to ' + to1; 
 	var strRange2 = from2 + ' to ' + to2;

	$(document).ready(function() {
		var chart1 = new Highcharts.Chart({
			chart : {
				renderTo : 'container',
				type : 'column',
				margin : 75,
				options3d : {
					enabled : true,
					alpha : 15,
					beta : 30,
					depth : 110
				}
			},
			plotOptions : {
				column : {
					depth : 40,
					stacking : true,
					grouping : false,
					groupZPadding : 20
				}
			},
			title : {
				text : 'Confronto motore di ricerca su diverso periodo'
			},
			xAxis : {
				categories : [ 'positive', 'negative' ]
			},
			yAxis : {
				title : {
					text : 'Candidature'
				}
			},
			series : [ {
				name : strRange1,
				data : range1,
				stack : 0
			}, {
				name : strRange2,
				data : range2,
				stack : 1
			} ]
		});
	});
</script>
</head>
<body>
	<s:property value="range1" id="range1" default="all" />
	<s:property value="range2" default="all" />
	<div id="container"
		style="min-width: 400px; height: 400px; margin: 0 auto"></div>
</body>
</html>