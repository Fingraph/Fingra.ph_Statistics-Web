<!--
 * Copyright 2014 tgrape Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="fh"%>
<!DOCTYPE html>
<html>
<head>
<meta name="menuId" content="distribution" />
<meta name="currentMenu" content="topresolution" />
<title>Fingra.ph - <spring:message code="dash.title.distribution"/></title>
<script type="text/javascript" src="<c:url value="/resources/js/fingraph-yesterday-statConfig.js"/>"></script>
<script type="text/javascript">
$(function() {
	//period setting
	period = $("#period").msDropdown({
			roundedCorner:true,
			on:{change: function(data, ui){addPeriods(data.value);}}
			}).data("dd");
	//term setting
	term = $("#term").msDropdown({
			roundedCorner:true,
			on:{change: function(data, ui){getFingraphData();}}
			}).data("dd");
	//segment setting
	segment = $("#segment").msDropdown().data("dd").set("disabled", true);

	//$('.calImg').click(function(){period.setIndexByValue('c-u');});
	getPeriodCookie('fingraphPeriod');
});
function getFingraphData(){
	$.ajax({
		url: '<c:url value="/distribution/getTopResolutionAjax"/>',
		data: $('#fingraphSearchParam').serialize(),
		type: 'POST',
		dataType: 'json',
		success: function(data) {
			if(data.tr !=null){
				displayTopResolution(data.tr);
				displayTopResolutionExcelTable(data.tr);
			}
		}
	});
}
//Top Resolution graph&table
function displayTopResolution(result){
	var colors = Highcharts.getOptions().colors;
	var chart = null;
	var term = $('#term').val();
	var fromTo = $('#fromTo').val();
	//bar chart
	if(result.sumList == null) {
		$('#barChart').empty();
	}else{
	var sumList = result.sumList;
	var total = 0;
	var category = new Array(sumList.length);
	var data_array = new Array(sumList.length);
	for(var i = 0;i<sumList.length;i++) {
		var row = sumList[i];
		var resolution = row.resolution;
		if (!resolution || resolution=="" || resolution=="(null)")
			resolution = "Unknown";
		category[i]= resolution;
		data_array[i] = {y: row.user, color: colors[i]};
		total += row.user;
	}
	var subTitle = makeSubTitle(term, fromTo);
    makeDefaultColumnChart(chart, 'barChart', '<spring:message code="chart.dist.resolution.var.title"/>', subTitle, '<spring:message code="chart.dist.resolution.var.y.text"/>', category, data_array, total);
	}

    //area chart

    if(result.rtsList == null) { $('#secondChart').empty(); return;}
    var list = result.rtsList;
	var cate = new Array(list.length);
	var top0 = new Array(list.length);
	var top1 = new Array(list.length);
	var top2 = new Array(list.length);
	var top3 = new Array(list.length);
	var top4 = new Array(list.length);
	var others = new Array(list.length);

	
	for(var i = 0;i<list.length;i++) {
		var row = list[i];
		if(term == 'daily'){
			cate[i] = moment(row.date).format('MMM D, YYYY');
		}else if(term=='weekly'){
			cate[i] = moment(row.fromDate).format('MMM D, YYYY') + ' ~ ' + moment(row.toDate).format('MMM D, YYYY');
		}else if(term=='monthly'){
			cate[i] = moment(row.year + '-' + row.month).format('MMM, YYYY');
		}
		top0[i] = row.top0;
		top1[i] = row.top1;
		top2[i] = row.top2;
		top3[i] = row.top3;
		top4[i] = row.top4;
		if(sumList.length==6){
		others[i] = row.others;
		}
	}

	var space = term=='daily'?0.2:(term=='weekly'?0.4:0.05);
	var xstep = parseInt(list.length * space);
	var data_array = new Array(sumList.length);
	for(var i = 0;i<sumList.length;i++){
		if(category[i]=='others'){
			data_array[i]={name:category[i], data:others};
		}else{
			data_array[i]={name:category[i], data:eval('top'+i)};
		}
	}
	var subTitle = makeSubTitle(term, fromTo);
	makeDefaultAreaChart(chart, 'areaChart', $('#term option:selected').text()+' <spring:message code="chart.dist.resolution.area.title"/>', subTitle, '<spring:message code="chart.dist.resolution.area.y.text"/>', cate, xstep,data_array);
}

// topResolution Excel Table
function displayTopResolutionExcelTable(result){
	var term = $('#term').val();
	if(result.sumList == null) {
		$('#barChart').empty();
	}else{
	var sumList = result.sumList;
	var html = "";
	var excel_date = "";
	// 헤더만 남기고 전부삭제
	while($("#report_table_0 tr").length > 0){
		$("#report_table_0 tr").last().remove();
	}
	html = "<tr><th width='50'>Date</th>";
	for(var i = 0;i<sumList.length;i++) {
		html += "<th width='50'>"+sumList[i].resolution+"</th>";
	}
	html += "</tr>";
	}
    if(result.rtsList == null) { $('#secondChart').empty(); return;}
    var list = result.rtsList;
	var others = new Array(list.length);

	
	for(var i = 0;i<list.length;i++) {
		var row = list[i];
		if(term == 'daily'){
			excel_date = row.date;
		}else if(term=='weekly'){
			excel_date = row.fromDate + ' ~ ' + row.toDate;
		}else if(term=='monthly'){
			excel_date = row.year + '-' + row.month;
		}
		if(sumList.length==6){
		others[i] = row.others;
		}
		html += "<tr><td>"+excel_date+"";
		for(var j=0; j<sumList.length-1; j++){
			html += "<td class='numFormat'>"+eval('row.top'+j)+"</td>";	
		}
		html += "<td class='numFormat'>"+others[i]+"</td></tr>";
	}
	$("#report_table_0").append(html);	
}
</script>
</head>
<body>
	<div id="main-cont">
	<!-- section start -->
	<jsp:include page="/distribution/include/section" flush="true">
	 <jsp:param name="appkey" value="${app.appkey}"/>
	 <jsp:param name="subMenu" value="topResolution" />
	</jsp:include>
	<!-- section end -->
	<!-- graph start -->
	<div class="sub-content">
	   	<div class="sub-title-wrap">
	   			<a class="sub-down-report" href="javascript:download_report('1');"><img src="<c:url value="/resources/img/btn-excel.png"/>"  alt="Excel Down" title="Excel Down"/></a>
	           <h2 class="title"><spring:message code="dash.card.topResolution.title"/></h2>
	       </div>
	       <div class="box">
	       	<form name="fingraphSearchParam" id="fingraphSearchParam">
	   			<input type="hidden" name="appkey" id="appkey" value="${app.appkey}"/>
		    	<input type="hidden" name="fromTo" id="fromTo" value=""/>
		    	<div class="sel-menu">
	       			<div class="fromToArea">
	       			<img id="periodPrev" src="<c:url value="/resources/img/btn_pride_prev_inactive.png"/>" alt="" title="지정기간 앞 기간" class="periodPrev" >
	           		<input type="text" class="date-width" id="from" name="from" value="" readonly="readonly" style="color: #3A414B;margin: 0;padding:3px 0 2px 5px;"/><span id="periodDiffDays">to</span>
					<input type="text" class="date-width" id="to" name="to" value="" readonly="readonly" style="color: #3A414B;margin: 0;padding:3px 0 2px 10px;"/>
                    <div id="calImgDiv" style="display: none;">
					    <img id="calImg" src="<c:url value="/resources/img/btn_calendar.png"/>" alt="" class="trigger calImg" style=" cursor: pointer; margin: 0 4px;">
					</div>
					<img id="periodNext" src="<c:url value="/resources/img/btn_pride_next_inactive.png"/>" alt="" title="지정기간 뒤 기간" class="periodNext" style="margin-left: -5px;">
	               </div>
	               <select name="period" id="period" class="period-width">
					    <option value="7-d"><spring:message code="chart.select1.option1"/></option>
					    <option value="30-d" selected="selected"><spring:message code="chart.select1.option2"/></option>
					    <option value="3-m"><spring:message code="chart.select1.option4"/></option>
					    <option value="6-m"><spring:message code="chart.select1.option5"/></option>
					    <option value="t-y"><spring:message code="chart.select1.option6"/></option>
					    <option value="c-u"><spring:message code="chart.select1.option7"/></option>
					 </select>
					 <select name="term" id="term" class="input-small">
					    <option value="daily"><spring:message code="chart.select2.option1"/></option>
					    <option value="weekly"><spring:message code="chart.select2.option2"/></option>
					    <option value="monthly"><spring:message code="chart.select2.option3"/></option>
					 </select>
					 <select name="segment" id="segment" class="input-medium" disabled="disabled">
					    <option value=""><spring:message code="chart.select3.default.option0"/></option>
					 </select>
	          </div>
	         </form>
	           <div class="chart" id="barChart" class="graph_visual" style="min-width: 100px; height: 344px; margin: 0 auto;"></div>
	           <div class="chart" id="areaChart" class="graph_visual" style="min-width: 100px; height: 344px; margin: 0 auto;"></div>
	       </div>
	  </div>
	  <!-- graph end -->


		<table id="report_table_0" style="display:none;">

		</table>
	</div>
</body>
</html>