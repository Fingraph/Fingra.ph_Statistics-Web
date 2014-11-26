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
<meta name="currentMenu" content="topversion" />
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
			on:{change: function(data, ui){
						adjustDatesByTerm();
						getFingraphData();
					}
				}
			}).data("dd");
	//segment setting
	segment = $("#segment").msDropdown().data("dd").set("disabled", true);

	//$('.calImg').click(function(){period.setIndexByValue('c-u');});
	getPeriodCookie('fingraphPeriod');
});
function getFingraphData(){
	$.ajax({
		url: '<c:url value="/distribution/getTopVersionsAjax"/>',
		data: $('#fingraphSearchParam').serialize(),
		type: 'POST',
		dataType: 'json',
		success: function(data) {
			displayOsVersions(data.ov);
			displayAppVersions(data.av);
			displayOsVersionsExcelTable(data.ov);
			displayAppVersionsExcelTable(data.av);
		}
	});
}

//Os Versions graph&table
function displayOsVersions(result){
	var chart = null;
	var term = $('#term').val();
	var fromTo = $('#fromTo').val();
	$('#topOsVer').text(result.topOsVer==''?'N/A':result.topOsVer);

	//pie chart
	if(result.sumList == null) {
		$('#osPieChart').empty();
	}else{
	var sumList = result.sumList;
	var data = new Array();
	var osArray = new Array(sumList.length);

	for(var i = 0;i<sumList.length;i++) {
		var row = sumList[i];
		var osversion = row.type;
		if (!osversion || osversion=="" || osversion=="(null)")
			osversion = "Unknown";
		var data_row = [osversion, row.user];
		osArray[i] = osversion;
		data[i] = data_row;
	}

	var subTitle = makeSubTitle(term, fromTo);
	makeDefaultPieChart(chart, 'osPieChart', '<spring:message code="chart.dist.versions.pie.os.title"/>',subTitle, data);
	}

	//area chart
	if(result.vtsList == null ){$('#osAreaChart').empty(); return;}
	var list = result.vtsList;
	var term = $('#term').val();
	var i = 0;
	var cate = new Array(list.length);

	var top0 = new Array(list.length);
	var top1 = new Array(list.length);
	var top2 = new Array(list.length);
	var top3 = new Array(list.length);
	var top4 = new Array(list.length);

	for(i = 0;i<list.length;i++) {
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
	var data_array = new Array(sumList.length);
	for(var i = 0;i<sumList.length;i++){
		data_array[i]={name:osArray[i], data:eval('top'+i)};
	}
	makeDefaultAreaChart(chart, 'osAreaChart', $('#term option:selected').text()+' <spring:message code="chart.dist.versions.area.os.title"/>', subTitle, '<spring:message code="chart.dist.resolution.area.y.text"/>', cate, xstep,data_array);

}
//App Versions graph&table
function displayAppVersions(result){
	var chart = null;
//	var html ="";
	var term = $('#term').val();
	var fromTo = $('#fromTo').val();
	$('#topAppVer').text(result.topAppVer==''?'N/A':result.topAppVer);

/* 	// 헤더만 남기고 전부 삭제
	while($("#report_table_0 tr").length > 0) {
		$("#report_table_0 tr").last().remove();
	} */

	//pie chart
	if(result.sumList == null) {
		$('#appPieChart').empty();
	}else{
	var sumList = result.sumList;
	var data = new Array();
	var appArray = new Array(sumList.length);

//	html += "<tr><th width='50'> Date</th>";
	for(var i = 0;i<sumList.length;i++) {
		var row = sumList[i];
		var appversion = row.type;
		if (!appversion || appversion=="" || appversion=="(null)")
			appversion = "Unknown";
		var data_row = [appversion, row.user];
		appArray[i] = appversion;
		data[i] = data_row;

//		html += "<th width='50'>"+appversion+"</th>";
	}
//	html += "</tr>";

	var subTitle = makeSubTitle(term, fromTo);
	makeDefaultPieChart(chart, 'appPieChart', '<spring:message code="chart.dist.versions.pie.app.title"/>',subTitle, data);
	}
	//area chart
	if(result.vtsList == null ){$('#appAreaChart').empty(); return;}
	var list = result.vtsList;
	var term = $('#term').val();
	var i = 0;
	var cate = new Array(list.length);
	//	var excel_date='';

	var top0 = new Array(list.length);
	var top1 = new Array(list.length);
	var top2 = new Array(list.length);
	var top3 = new Array(list.length);
	var top4 = new Array(list.length);

	for(i = 0;i<list.length;i++) {
		var row = list[i];
		if(term == 'daily'){
			cate[i] = moment(row.date).format('MMM D, YYYY');
			//excel_date = moment(row.date).format('YYYY-MM-DD');
		}else if(term=='weekly'){
			cate[i] = moment(row.fromDate).format('MMM D, YYYY') + ' ~ ' + moment(row.toDate).format('MMM D, YYYY');
			//excel_date = moment(row.fromDate).format('YYYY-MM-DD') + ' ~ ' + moment(row.toDate).format('YYYY-MM-DD');
		}else if(term=='monthly'){
			cate[i] = moment(row.year + '-' + row.month).format('MMM, YYYY');
			//excel_date = moment(row.year + '-' + row.month).format('YYYY-MM');
		}
		top0[i] = row.top0;
		top1[i] = row.top1;
		top2[i] = row.top2;
		top3[i] = row.top3;
		top4[i] = row.top4;

/* 		html += "<tr>\n";
		html += "	<td>" + excel_date + "</td>\n";
		for(var j=0 ; j<sumList.length;j++){
			html += "	<td>" + eval('row.top'+j) + "</td>\n";
		}
		html += "</tr>\n"; */
	}

	var space = term=='daily'?0.2:(term=='weekly'?0.4:0.05);
	var xstep = parseInt(list.length * space);
	var data_array = new Array(sumList.length);

	for(var i = 0;i<sumList.length;i++){
		data_array[i]={name:appArray[i], data:eval('top'+i)};
	}
	makeDefaultAreaChart(chart, 'appAreaChart', $('#term option:selected').text()+' <spring:message code="chart.dist.versions.area.app.title"/>', subTitle, '<spring:message code="chart.dist.resolution.area.y.text"/>', cate, xstep,data_array);

	//$("#report_table_0").append(html);
}

// OsVersions Excel Table
function displayOsVersionsExcelTable(result){
	var html = '';
	var term = $('#term').val();
	$('#topOsVer').text(result.topOsVer==''?'N/A':result.topOsVer);

	// 헤더만 남기고 전부 삭제
	while($("#report_table_1 tr").length > 0) {
		$("#report_table_1 tr").last().remove();
	}

	//pie chart
	if(result.sumList == null) {
		$('#osPieChart').empty();
	}else{
	var sumList = result.sumList;

	html += "<tr><th width='50'> Date</th>";
	for(var i = 0;i<sumList.length;i++) {
		html += "<th width='50'>"+sumList[i].type+"</th>";
	}
	html += "</tr>";
	}

	//area chart
	if(result.vtsList == null ){$('#osAreaChart').empty(); return;}
	var list = result.vtsList;
	var excel_date='';

	for(i = 0;i<list.length;i++) {
		var row = list[i];
		if(term == 'daily'){
			excel_date = row.date;
		}else if(term=='weekly'){
			excel_date = row.fromDate + ' ~ ' + row.toDate;
		}else if(term=='monthly'){
			excel_date = row.year + '-' + row.month;
		}

		html += "<tr>\n";
		html += "	<td>" + excel_date + "</td>\n";
		for(var j=0 ; j<sumList.length;j++){
			html += "	<td class='numFormat'>" + eval('row.top'+j) + "</td>\n";
		}
		html += "</tr>\n";
	}
	$("#report_table_1").append(html);
}

// AppVersions Excel Table
function displayAppVersionsExcelTable(result){
	var html ="";
	var term = $('#term').val();
	$('#topAppVer').text(result.topAppVer==''?'N/A':result.topAppVer);

	// 헤더만 남기고 전부 삭제
	while($("#report_table_0 tr").length > 0) {
		$("#report_table_0 tr").last().remove();
	}

	//pie chart
	if(result.sumList == null) {
		$('#appPieChart').empty();
	}else{
	var sumList = result.sumList;

	html += "<tr><th width='50'> Date</th>";
	for(var i = 0;i<sumList.length;i++) {
		html += "<th width='50'>"+sumList[i].type+"</th>";
	}
	html += "</tr>";

	}
	//area chart
	if(result.vtsList == null ){$('#appAreaChart').empty(); return;}
	var list = result.vtsList;
	var excel_date='';

	for(i = 0;i<list.length;i++) {
		var row = list[i];
		if(term == 'daily'){
			excel_date = row.date;
		}else if(term=='weekly'){
			excel_date = row.fromDate + ' ~ ' + row.toDate;
		}else if(term=='monthly'){
			excel_date = row.year + '-' + row.month;
		}
		html += "<tr>\n";
		html += "	<td>" + excel_date + "</td>\n";
		for(var j=0 ; j<sumList.length;j++){
			html += "	<td class='numFormat'>" + eval('row.top'+j) + "</td>\n";
		}
		html += "</tr>\n";
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
	 <jsp:param name="subMenu" value="topVersions" />
	</jsp:include>
	<!-- section end -->
	<!-- graph start -->
	<div class="sub-content">
	   	<div class="sub-title-wrap">
	   		<a class="sub-down-report" href="javascript:download_report(['appversion', 'osversion']);"><img src="<c:url value="/resources/img/btn-excel.png"/>" alt="Excel Down" title="Excel Down"/></a>
	           <h2 class="title"><spring:message code="dash.card.topVersions.title"/></h2>
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
					 <select name="segment" id="segment" class="input-medium" disabled="disabled" style="display: none;">
					    <option value=""><spring:message code="chart.select3.default.option0"/></option>
					 </select>
	          </div>
	         </form>
	           <div class="chart" id="container" class="graph_visual"  style="min-width: 100px; height: 344px; margin: 0 auto;">
	           	<div id="appPieChart" style="float:left; width:35%; height: 100%; margin: 0 auto"></div>
	            <div id="appAreaChart" style="float:left; width: 65%; margin: 0 auto"></div>
	           </div>
	           <br/>
	           <div class="chart" id="container" class="graph_visual"  style="min-width: 100px; height: 344px; margin: 0 auto;">
	       		<div id="osPieChart" style="float:left; width:35%; height: 100%; margin: 0 auto"></div>
	            <div id="osAreaChart" style="float:left; width: 65%; margin: 0 auto"></div>
	           </div>
	       </div>
	  </div>
	  <!-- graph end -->
	   <!-- figures start -->
	  <div class="figure-wrap">
          <table>
           <colgroup>
	           <col width="50%" />
	           <col />
          </colgroup>
              <tr>
                  <th scope="col"><spring:message code="chart.subcnt.title.topAppVer"/></th>
                  <th scope="col"><spring:message code="chart.subcnt.title.topOsVer"/></th>
              </tr>
              <tr>
                  <td><span id="topAppVer" title=""></span></td>
                  <td><span id="topOsVer" title=""></span></td>
              </tr>
          </table>
	    </div>
	    <!-- figures end -->
		<table id="report_table_0" style="display:none;">
	     </table>
	     <table id="report_table_1" style="display:none;">
	     </table>

	</div>
</body>
</html>