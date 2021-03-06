<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<h1>
	Statistiche ${nomiMotoriList} da
	<s:property value="from" default="all" />
	a
	<s:property value="to" default="all" />
</h1>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<title>Statistiche</title>
<script type="text/javascript"
	src="http://mbostock.github.com/d3/d3.js?2.1.3"></script>


<style type="text/css">
.slice text {
	font-size: 16pt;
	font-family: Arial;
}
</style>
</head>

<body>
	<script type="text/javascript">
			var w = 450, h = 450, r = 200, color = d3.scale.ordinal().range(
					[ "#98abc5", "#8a89a6" ]);
			data = [ {
				"label" : "positive",
				"value" : <s:property value="media" />
			}, {
				"label" : "negative",
				"value" : 1 - <s:property value="media" />
			}, ];

			var vis = d3.select("body").append("svg:svg").data([ data ]).attr(
					"width", w).attr("height", h).append("svg:g").attr(
					"transform", "translate(" + r + "," + r + ")")
			var arc = d3.svg.arc().outerRadius(r);
			var pie = d3.layout.pie().value(function(d) {
				return d.value;
			});
			var arcs = vis.selectAll("g.slice").data(pie).enter().append(
					"svg:g").attr("class", "slice");
			arcs.append("svg:path").attr("fill", function(d, i) {
				return color(i);
			}).attr("d", arc);
			arcs.append("svg:text").attr("transform", function(d) {
				d.innerRadius = 0;
				d.outerRadius = r;
				return "translate(" + arc.centroid(d) + ")";
			}).attr("text-anchor", "middle").text(function(d, i) {
				return (100 * data[i].value).toFixed(2) + '%';
			});
		</script>
</body>

</html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
</head>
<style>
body {
	font: 10px sans-serif;
}

.axis path, .axis line {
	fill: none;
	stroke: #000;
	shape-rendering: crispEdges;
}

.bar {
	fill: steelblue;
}

.x.axis path {
	display: none;
}
</style>
<body>
	<script src="//d3js.org/d3.v3.min.js"></script>
	<script>
		var margin = {
			top : 20,
			right : 20,
			bottom : 30,
			left : 40
		}, width = 960 - margin.left - margin.right, height = 500 - margin.top
				- margin.bottom;

		var x0 = d3.scale.ordinal().rangeRoundBands([ 0, width ], .1);

		var x1 = d3.scale.ordinal();

		var y = d3.scale.linear().range([ height, 0 ]);

		var color = d3.scale.ordinal().range(
				[ "#8A89A6", "#98abc5", "#7b6888", "#6b486b", "#a05d56",
						"#d0743c", "#ff8c00" ]);

		var xAxis = d3.svg.axis().scale(x0).orient("bottom");

		var yAxis = d3.svg.axis().scale(y).orient("left").tickFormat(
				d3.format(".2s"));

		var svg = d3.select("body").append("svg").attr("width",
				width + margin.left + margin.right).attr("height",
				height + margin.top + margin.bottom).append("g").attr(
				"transform",
				"translate(" + margin.left + "," + margin.top + ")");

		data = <s:property value="arrayForBarChart" />;

		var candidaturaMotore = d3.keys(data[0]).filter(function(key) {
			return key !== "motori";
		});
		console.log(candidaturaMotore);
		data.forEach(function(d) {
			d.candidatura = candidaturaMotore.map(function(name) {
				return {
					name : name,
					value : +d[name]
				};
			});
		});

		x0.domain(data.map(function(d) {
			return d.motori;
		}));
		x1.domain(candidaturaMotore).rangeRoundBands([ 0, x0.rangeBand() ]);
		y.domain([ 0, d3.max(data, function(d) {
			return d3.max(d.candidatura, function(d) {
				return d.value;
			});
		}) ]);

		svg.append("g").attr("class", "x axis").attr("transform",
				"translate(0," + height + ")").call(xAxis);

		svg.append("g").attr("class", "y axis").call(yAxis).append("text")
				.attr("transform", "rotate(-90)").attr("y", 6).attr("dy",
						".71em").style("text-anchor", "end")
				.text("Candidature");

		var motore = svg.selectAll(".state").data(data).enter().append("g")
				.attr("class", "state").attr("transform", function(d) {
					return "translate(" + x0(d.motori) + ",0)";
				});

		motore.selectAll("rect").data(function(d) {
			return d.candidatura;
		}).enter().append("rect").attr("width", x1.rangeBand()).attr("x",
				function(d) {
					return x1(d.name);
				}).attr("y", function(d) {
			return y(d.value);
		}).attr("height", function(d) {
			return height - y(d.value);
		}).style("fill", function(d) {
			return color(d.name);
		});

		var legend = svg.selectAll(".legend").data(
				candidaturaMotore.slice().reverse()).enter().append("g").attr(
				"class", "legend").attr("transform", function(d, i) {
			return "translate(0," + i * 20 + ")";
		});

		legend.append("rect").attr("x", width - 18).attr("width", 18).attr(
				"height", 18).style("fill", color);

		legend.append("text").attr("x", width - 24).attr("y", 9).attr("dy",
				".35em").style("text-anchor", "end").text(function(d) {
			return d;
		});
	</script>