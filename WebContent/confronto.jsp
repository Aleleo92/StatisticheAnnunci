<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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

</head>

<style type="text/css">
.container {
	width: 400px;
	height: 200px;
	border: 1px solid #ddd;
	padding: 10px;
}

.btn {
	display: inline-block;
	padding: 6px 12px;
	margin-bottom: 0;
	font-size: 14px;
	font-weight: 400;
	line-height: 1.42857143;
	text-align: center;
	white-space: nowrap;
	vertical-align: middle;
	-ms-touch-action: manipulation;
	touch-action: manipulation;
	cursor: pointer;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
	background-image: none;
	border: 1px solid transparent;
	border-radius: 4px;
}

.btn-primary {
	color: #fff;
	background-color: #337ab7;
	border-color: #428bca;
	position: absolute;
	left: 355px;
	top: 240px;
}

.mostra {
	color: #fff;
	background-color: #337ab7;
	border-color: #428bca;
	position: absolute;
	left: 250px;
	top: 75px;
	display: inline-block;
	padding: 6px 12px;
	margin-bottom: 0;
	font-size: 14px;
	font-weight: 400;
	background-image: none;
	border: 1px solid transparent;
	border-radius: 4px;
}

.to {
	width: 30px;
	height: 30px;
	padding: 6px;
	padding-left: 12px;
	font-size: 16px;
	font-weight: 400;
	line-height: 23px;
	color: #FFF;
	text-align: center;
	background-color: #428bca;
	border-top: 1px solid rgba(255, 255, 255, 0);
	border-bottom: 1px solid rgba(204, 204, 204, 0);
	border-radius: 4px;
	padding-right: 12px;
}

.date-range {
	position: absolute;
	left: 50px;
	top: 30px;
}

.date-range1 {
	position: absolute;
	left: 50px;
	top: 115px;
}
<
body
>
</style>
<div class="container">
	<s:form action="inviaConfronto">
		<select name="motore" id="menu">
			<s:iterator value="motoriList">
				<option value="${id}">${nome}</option>
			</s:iterator>
		</select>
		<br>

		<div class="date-range">
			<h2>Range1</h2>
			<input type="date" name="cmpFrom1" max="${dateMax}" min="${dateMin}">
			<span class="to">to</span> <input type="date" name="cmpTo1"
				max="${dateMax}" min="${dateMin}">
		</div>

		<div class="date-range1">
			<h2>Range2</h2>
			<input type="date" name="cmpFrom2" max="${dateMax}" min="${dateMin}">
			<span class="to">to</span> <input type="date" name="cmpTo2"
				max="${dateMax}" min="${dateMin}">
		</div>

		<s:submit cssClass="btn btn-primary" value="cerca"></s:submit>
	</s:form>
</div>
</body>
</html>