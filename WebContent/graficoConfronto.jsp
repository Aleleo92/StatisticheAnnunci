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
var a = '${range1}';
var b = '${range2}'
alert('Endpoint: '+ a);
    $(function () {
    	var chart = new Highcharts.Chart(options);
        $('#container').highcharts({
            chart: {
                type: 'column',
                margin: 75,
                options3d: {
    				enabled: true,
                    alpha: 15,
                    beta: 30,
                    depth: 110
                }
            },
            plotOptions: {
                column: {
                    depth: 40,
                    stacking: true,
                    grouping: false,
                    groupZPadding: 10
                }
            },
/*             series: [{
                data: [10, 8],
                stack: 0
            }, {
                data: [4, 6],
                stack: 1
            }] */
            series: [{data : [10,8] , stack: 0},{data : [4,6] , stack: 1}]
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