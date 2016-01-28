<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
    top: 200px;
}

</style>
<h1>Statistiche motori di ricerca</h1>
<div class="container">
	<s:form action="showMotori">
		<s:submit value="mostra motori"></s:submit>
	</s:form>
	<s:form action="cerca">
		<%-- <select name="motore" id="menu">
			<s:iterator value="motoriList">
				<option value="${id}">${nome}</option>
			</s:iterator>
		</select> --%>

		<s:iterator value="motoriList">
			<input type="checkbox" name="motoriList" value="${id}">${nome}
			<br>
		</s:iterator>
		<br>
		<div class="date-range">
			<input type="date" name="from" > <span
				class="to">to</span> <input type="date" name="to">
		</div>
		<s:submit cssClass="btn btn-primary" value="cerca"></s:submit>
	</s:form>
</div>
