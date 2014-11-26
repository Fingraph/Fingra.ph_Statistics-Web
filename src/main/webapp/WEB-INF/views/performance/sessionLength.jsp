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
<meta name="menuId" content="performance" />
<meta name="currentMenu" content="sessionlength" />
<title>Fingra.ph - <spring:message code="dash.title.performance"/></title>
<script type="text/javascript" src="<c:url value="/resources/js/fingraph-yesterday-statConfig.js"/>"></script>
<script type="text/javascript">
var cate_name = ['0~3 sec','3~10 sec','10~30 sec','30~60 sec','1~3 min','3~10 min','10~30 min','over 30 min'];
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
					addPeriods($("#period").val());
					getFingraphData();
					}
				}
			}).data("dd");
	//segment setting
	segment = $("#segment").msDropdown({
			roundedCorner:true,
			on:{change: function(data, ui){	getFingraphData();}}
			}).data("dd");

	//$('.calImg').click(function(){period.setIndexByValue('c-u');});
	getPeriodCookie('fingraphPeriod');
});
function getFingraphData(){
	$.ajax({
		url: '<c:url value="/performance/getSessionLengthAjax"/>',
		data: $('#fingraphSearchParam').serialize(),
		type: 'POST',
		dataType: 'json',
		success: function(data) {
			displaySessionLengthFigures(data.figures);
			displaySessionLengthTotal(data.total);
			if('total' == $('#segment').val()){
				displaySessionLengthSection(data.slist);
				displaySessionLengthSectionExcelTable(data.slist);
			}else if('median' == $('#segment').val()){
				displaySessionLengthMedian(data.list);
				displaySessionLengthMedianExcelTable(data.list);
			}
		}
	});
}
function displaySessionLengthSectionExcelTable(result){
	var html = '';
	var term = $('#term').val();
	var excel_date="";
	// 헤더만 남기고 전부삭제
	while($("#report_table_0 tr").length > 0){
		$("#report_table_0 tr").last().remove();
	}
	html += "<tr><th width='50'>Date</th>";
	for ( var i=0; i<cate_name.length; i++){
		html += "<th width='40'>"+ cate_name[i] +"</th>";
	}
	html += "</tr>";
	for(var i = 0;i<result.length;i++) {
		if(term == 'daily'){
			excel_date = result[i].date;
		}else if(term=='weekly'){
			excel_date = result[i].fromDate + ' ~ ' + result[i].toDate;
		}else if(term=='monthly'){
			excel_date = result[i].year + '-' + result[i].month;
		}

		html += "<tr>\n";
		html += "	<td>"+excel_date+"</td>\n";
		html += "	<td class='numFormat'>"+result[i].under_three_sec+"</td>\n";
		html += "	<td class='numFormat'>"+result[i].three_ten_sec+"</td>\n";
		html += "	<td class='numFormat'>"+result[i].ten_thirty_sec+"</td>\n";
		html += "	<td class='numFormat'>"+result[i].thirty_sixty_sec+"</td>\n";
		html += "	<td class='numFormat'>"+result[i].one_three_min+"</td>\n";
		html += "	<td class='numFormat'>"+result[i].three_ten_min+"</td>\n";
		html += "	<td class='numFormat'>"+result[i].ten_thirty_min+"</td>\n";
		html += "	<td class='numFormat'>"+result[i].over_thirty_min+"</td>\n";
		html += "</tr>\n";

	}
	$("#report_table_0").append(html);
}


function displaySessionLengthSection(result){
	var chart = null;
	var term = $('#term').val();
	var fromTo = $('#fromTo').val();
	var cate = new Array(result.length);

	var data0_3s = new Array(result.length);
	var data3_10s= new Array(result.length);
	var data10_30s = new Array(result.length);
	var data30_60s = new Array(result.length);
	var data1_3m = new Array(result.length);
	var data3_10m = new Array(result.length);
	var data10_30m = new Array(result.length);
	var data30m_over = new Array(result.length);

	for(var i = 0;i<result.length;i++) {
		var row = result[i];
		if(term == 'daily'){
			cate[i] = moment(row.date).format('MMM D, YYYY');
		}else if(term=='weekly'){
			cate[i] = moment(row.fromDate).format('MMM D, YYYY') + ' ~ ' + moment(row.toDate).format('MMM D, YYYY');
		}else if(term=='monthly'){
			cate[i] = moment(row.year + '-' + row.month).format('MMM, YYYY');
		}
		data0_3s[i] = row.under_three_sec;
		data3_10s[i] = row.three_ten_sec;
		data10_30s[i] = row.ten_thirty_sec;
		data30_60s[i] = row.thirty_sixty_sec;
		data1_3m[i] = row.one_three_min;
		data3_10m[i] = row.three_ten_min;
		data10_30m[i] = row.ten_thirty_min;
		data30m_over[i] = row.over_thirty_min;
	}

	var space = term=='daily'?0.2:(term=='weekly'?0.4:0.15);
	var xstep = parseInt(result.length * space);
	var data_array = [{
        			name: '0~3 sec',
        			data: data0_3s
   		 		}, {
        			name: '3~10 sec',
        			data: data3_10s
   		 		}, {
        			name: '10~30 sec',
        			data: data10_30s
   		 		}, {
        			name: '30~60 sec',
        			data: data30_60s
   		 		}, {
        			name: '1~3 min',
        			data: data1_3m
   		 		}, {
        			name: '3~10 min',
        			data: data3_10m
   		 		}, {
        			name: '10~30 min',
        			data: data10_30m
   		 		}, {
        			name: 'over 30 min',
        			data: data30m_over
   		 		}
	];
	var subTitle = makeSubTitle(term, fromTo);
	makeDefaultAreaChart(chart, 'container1', $("#term option:selected").text() +' <spring:message code="chart.perf.sessionlen.area.title"/>', subTitle, '<spring:message code="chart.perf.sessionlen.var.y.text"/>', cate, xstep,data_array);
}
function displaySessionLengthTotal(result){
	var chart = null;
	var term = $('#term').val();
	var fromTo = $('#fromTo').val();
	var colors = Highcharts.getOptions().colors;
	var cate = ['0~3 sec', '3~10 sec','10~30 sec','30~60 sec','1~3 min','3~10 min','10~30 min','over 30 min'];
	data = [
	    {y: result.under_three_sec, color: colors[0]},
	    {y: result.three_ten_sec, color: colors[1]},
	    {y: result.ten_thirty_sec, color: colors[2]},
	    {y: result.thirty_sixty_sec, color: colors[3]},
	    {y: result.one_three_min, color: colors[4]},
	    {y: result.three_ten_min, color: colors[5]},
	    {y: result.ten_thirty_min, color: colors[6]},
	    {y: result.over_thirty_min, color: colors[7]}
	];
	var total = result.total;
	var subTitle = makeSubTitle(term, fromTo);
	makeDefaultColumnChart(chart, 'container2', '<spring:message code="chart.perf.sessionlen.var.title"/>', subTitle, '<spring:message code="chart.perf.sessionlen.var.y.text"/>', cate, data, total);
}

//sessionLength 기본 요약정보
function displaySessionLengthFigures(figure){
	$('#average').text(figure.strAverage);
	$('#average').attr('title',$.formatNumber(figure.average, {format:"#,##0.0", locale:"us"})).tooltip('destroy').tooltip();;
	$('#most').text(figure.most);
}

function displaySessionLengthMedianExcelTable(result){
	var html = "";
	var excel_date="";
	var term = $('#term').val();
	while($("#report_table_0 tr").length > 0){
		$("#report_table_0 tr").last().remove();
	}
	html += "<tr><th width='50'>Date</th>";
	html += "<th width='40'>SessionLength</th>";
	html += "</tr>";
	for(var i = 0;i<result.length;i++) {
		if(term == 'daily'){
			excel_date = result[i].date;
		}else if(term=='weekly'){
			excel_date = result[i].fromDate + ' ~ ' + result[i].toDate;
		}else if(term=='monthly'){
			excel_date = result[i].year + '-' + result[i].month;
		}

		html += "<tr>\n";
		html += "	<td>" + excel_date + "</td>\n";
		html += "	<td class='numFormat'>" + Math.round(result[i].value) + "</td>\n";
		html += "</tr>\n";
	}
	$("#report_table_0").append(html);
}

//sessionLength Median graph&table
function displaySessionLengthMedian(result){
	var chart = null;
	var term = $('#term').val();
	var fromTo = $('#fromTo').val();
	var cate = new Array(result.length);
	var data = new Array(result.length);
	var sum = 0;

	for(var i = 0;i<result.length;i++) {
		var row = result[i];
		if(term == 'daily'){
			cate[i] = moment(row.date).format('MMM D, YYYY');
		}else if(term=='weekly'){
			cate[i] = moment(row.fromDate).format('MMM D, YYYY') + ' ~ ' + moment(row.toDate).format('MMM D, YYYY');
		}else if(term=='monthly'){
			cate[i] = moment(row.year + '-' + row.month).format('MMM, YYYY');
		}
		data[i] = row.value;
		sum+=row.value;
	}

	data=makeMaxMinDataSymbol(data);

    var avg = sum/result.length;
	var space = term=='daily'?0.2:(term=='weekly'?0.4:0.15);
	var xstep = parseInt(result.length * space);
	var subTitle = makeSubTitle(term, fromTo);
	makeDefaultLineChart(chart, 'container1', $("#term option:selected").text() +' <spring:message code="chart.perf.sessionlen.line.title"/>', subTitle, '<spring:message code="chart.perf.sessionlen.line.y.text"/>'  + '(' + '<spring:message code="dash.card.indicator.sec"/>' + ')', cate, xstep, data, parseInt(avg));
}

</script>
</head>
<body>
	<div id="main-cont">
	<!-- section start -->
	<jsp:include page="/performance/include/section" flush="true">
	 <jsp:param name="appkey" value="${app.appkey}"/>
	 <jsp:param name="subMenu" value="sessionLength" />
	</jsp:include>
	<!-- section end -->
	<!-- graph start -->
	<div class="sub-content">
	   	<div class="sub-title-wrap">
				<a class="sub-down-report" href="javascript:download_report('1');"><img src="<c:url value="/resources/img/btn-excel.png"/>"  alt="Excel Down" title="Excel Down"/></a>
	            <h2 class="title"><spring:message code="dash.card.sessionlen.title"/></h2>
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
						<option value="median"><spring:message code="chart.select3.sessionlen.option2"/></option>
					    <option value="total"><spring:message code="chart.select3.sessionlen.option1"/></option>
					 </select>
	          </div>
	         </form>
	       	   <div class="chart" id="container1" class="graph_visual"></div>
	       	   <div class="chart" id="container2" class="graph_visual"></div>
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
                  <th scope="col"><spring:message code="chart.subcnt.title.mostSessions"/></th>
                  <th scope="col"><spring:message code="chart.subcnt.title.medianTime"/></th>
              </tr>
              <tr>
                  <td><span id="most" title=""></span></td>
                  <td><span id="average" title=""></span></td>
              </tr>
          </table>
	    </div>
	    <!-- figures end -->
	    <!-- Excel table -->
	    <table id="report_table_0" style="display:none; ">
        </table>
	</div>
</body>
</html>