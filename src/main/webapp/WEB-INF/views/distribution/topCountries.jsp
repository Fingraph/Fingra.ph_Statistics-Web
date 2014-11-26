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
<meta name="currentMenu" content="topcountries" />
<title>Fingra.ph - <spring:message code="dash.title.distribution"/></title>
<script type="text/javascript" src="<c:url value="/resources/js/fingraph-yesterday-statConfig.js"/>"></script>
<script type='text/javascript' src='https://www.google.com/jsapi'></script>
<script type="text/javascript">
var country = new Array();
$(function() {
	//period setting
	period = $("#period").msDropdown({
			roundedCorner:true,
			on:{change: function(data, ui){addPeriods(data.value);}}
			}).data("dd");
	//term setting
	term = $("#term").msDropdown({
			roundedCorner:true,
			on:{change: function(data, ui){
						adjustDatesByTerm();
						getFingraphData();
					}
				}
			}).data("dd");
	//segment setting
	segment = $("#segment").msDropdown({
			roundedCorner:true,
			on:{change: function(data, ui){getFingraphData();}}
			}).data("dd");

	//$('.calImg').click(function(){period.setIndexByValue('c-u');});
	getPeriodCookie('fingraphPeriod');
});
function getFingraphData(){
	$.ajax({
		url: '<c:url value="/distribution/getTopCountriesAjax"/>',
		data: $('#fingraphSearchParam').serialize(),
		type: 'POST',
		dataType: 'json',
		success: function(data) {
			displayTopCountriesChart(data.cv);
			displayTopCountriesChartExcelTable(data.cv);
		}
	});
}
//Top Countries GeoChart
function displayTopCountriesGeoChart(result){
	var type='NewUsers';
	var segment = $("#segment").val();
	if(segment == 'sessions'){
		type='Sessions';
	}else if(segment == 'activeUsers'){
		type='Users';
	}else if(segment == 'sessionLength'){
		type='Sec';
	}else if(segment == 'pageViews'){
		type='Views';
	}else if(segment == 'newUsers'){
		type='NewUsers';
	}

	var dataArray = new Array(result.length+1);
	dataArray[0]=['country',type];
	for(var i=0; i<result.length;i++){
		var row = result[i];
		dataArray[i+1]=[row.country,row.value];
		if( i <= 4)	country[i] = row.country;
	}
  var data = google.visualization.arrayToDataTable(dataArray);
  // Set chart options
  var options = {
		  		region:'world',
		  		//displayMode: 'markers',
		  		colorAxis: {colors: ['#B2CCFF','#002266']},
 				legend:{numberFormat:'#,###'},
 				markerOpacity:0.8,
 				};

  var formatter = new google.visualization.NumberFormat({pattern:'#,###'});
	  formatter.format(data, 1); // Apply formatter to second column
  var geoChart = new google.visualization.GeoChart(document.getElementById('geoChart'));
  geoChart.draw(data, options);

}
google.load('visualization', '1', {'packages': ['geoChart']});

//Top Countries
function displayTopCountriesChart(result){
	/*
	data개요
	result.sumList => 지표의 sumList (국가명:country, 두자리국가명: iso2, 지표합:value)
	result.topNList => sum기준 top5의 두자리국가명 String list
	result.ctsList => top5국가의 timeSeries List
	*/
	var sumList = result.sumList;
	//GeoChart
	if(sumList !=null){
		displayTopCountriesGeoChart(sumList);
	}else{
		$('#geoChart').empty();
	}

	var term = $('#term').val();
	var fromTo = $('#fromTo').val();
	//area chart
	if(result.ctsList == null) {
		makeDefaultAreaChart(chart, 'areaChart', $('#term option:selected').text() +' '+$('#segment option:selected').text()+' <spring:message code="chart.dist.countries.title"/>',makeSubTitle(term, fromTo), $('#segment option:selected').text(), null, null,null);
		return;
	}
    var list = result.ctsList;
	var topNList = result.topNList;
	var chart = null;
	var cate = new Array(list.length);
	var top0 = new Array(list.length);
	var top1 = new Array(list.length);
	var top2 = new Array(list.length);
	var top3 = new Array(list.length);
	var top4 = new Array(list.length);

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

	}

	var space = term=='daily'?0.2:(term=='weekly'?0.4:0.05);
	var xstep = parseInt(list.length * space);
	var data_array = new Array(topNList.length);
	for(var i = 0;i<topNList.length;i++){
		data_array[i]={name:sumList[i].country, data:eval('top'+i)};
	}
	var subTitle = makeSubTitle(term, fromTo);
	makeDefaultAreaChart(chart, 'areaChart', $('#term option:selected').text() +' '+$('#segment option:selected').text()+' <spring:message code="chart.dist.countries.title"/>',subTitle, $('#segment option:selected').text(), cate, xstep,data_array);
}

// topCountry Excel table
function displayTopCountriesChartExcelTable(result){
	var term = $('#term').val();
    var list = result.ctsList;
	var html = "";
	var excel_date ="";
	var status = $("#segment option:selected").text();

	// 헤더만 남기고 전부삭제
	while($("#report_table_0 tr").length > 0){
		$("#report_table_0 tr").last().remove();
	}
	html = "<tr><th width='50'>Date("+status+")</th>";
	for ( var i=0; i<country.length; i++){
		html += "<th width='50'>"+country[i]+"</th>";
	}
	html += "</tr>";
	for(var i = 0;i<list.length;i++) {
		var row = list[i];
		if(term == 'daily'){
			excel_date = row.date;
		}else if(term=='weekly'){
			excel_date = row.fromDate + ' ~ ' + row.toDate;
		}else if(term=='monthly'){
			excel_date = row.year + '-' + row.month;
		}

		html += "<tr><td>"+excel_date+"</td>";
		for( var j=0; j<country.length; j++){
			html += "<td class='numFormat'>"+eval('row.top'+j)+"</td>";
		}
		html += "</tr>";
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
	 <jsp:param name="subMenu" value="topCountries" />
	</jsp:include>
	<!-- section end -->
	<!-- graph start -->
	<div class="sub-content">
	   	<div class="sub-title-wrap">
	   			<a class="sub-down-report" href="javascript:download_report('1');"><img src="<c:url value="/resources/img/btn-excel.png"/>"  alt="Excel Down" title="Excel Down"/></a>
	           <h2 class="title"><spring:message code="dash.card.topCountries.title"/></h2>
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
					 <select name="segment" id="segment" class="input-medium">
					    <option value="newUsers"><spring:message code="chart.select3.default.option5"/></option>
					    <option value="activeUsers"><spring:message code="chart.select3.default.option2"/></option>
					    <option value="sessions"><spring:message code="chart.select3.default.option1"/></option>
					    <option value="sessionLength"><spring:message code="chart.select3.default.option3"/></option>
					    <option value="pageViews"><spring:message code="chart.select3.default.option4"/></option>
					 </select>
	          </div>
	         </form>
	           <div class="chart" id="geoChart" class="graph_visual" style="min-width: 100px; height: 344px; margin: 0 auto;"></div>
	           <div class="chart" id="areaChart" class="graph_visual"></div>
	       </div>
	  </div>
	  <!-- graph end -->


		<table id="report_table_0" style="display:none;">

		</table>
	</div>
</body>
</html>