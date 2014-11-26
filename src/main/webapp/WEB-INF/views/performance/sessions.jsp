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
<meta name="currentMenu" content="sessions" />
<title>Fingra.ph - <spring:message code="dash.title.performance"/></title>
<script type="text/javascript" src="<c:url value="/resources/js/fingraph-yesterday-statConfig.js"/>"></script>
<script type="text/javascript">
var cate_name = ['1 times run', '2 times run','3~4 times run','5~6 times run','7~9 times run','10~14 times run','15~19 times run','20~49 times run','50~99 times run','100~499 times run','over 500 times run'];
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
			on:{change: function(data, ui){	getFingraphData();}}
			}).data("dd");

	//$('.calImg').click(function(){period.setIndexByValue('c-u');});
	getPeriodCookie('fingraphPeriod');
});
function getFingraphData(){
	$.ajax({
		url: '<c:url value="/performance/getSessionsAjax"/>',
		data: $('#fingraphSearchParam').serialize(),
		type: 'POST',
		dataType: 'json',
		success: function(data) {
			displaySessionsFigures(data.figures);
			displayFrequenceRanges(data.total);//bar default:2
			if('timeSeries' == $('#segment').val()){
				displayTimeSeriesSessions(data.slist);//area
				displayTimeSeriesSessionsExcelTable(data.slist);
			}else if('total' == $('#segment').val()){
				displayTotalSessions(data.list);//line
				displayTotalSessionsExcelTable(data.list);
			}
		}
	});


}
function displayTimeSeriesSessionsExcelTable(result){
	var html="";
	var excel_date = "";
	var term = $('#term').val();
	// 헤더만 남기고 전부삭제
	while($("#report_table_0 tr").length > 0){
		$("#report_table_0 tr").last().remove();
	}
	html = "<tr><th width='50'>Date</th>";
	for (var i=0; i<cate_name.length; i++){
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
		html += "	<td>" + excel_date + "</td>\n";
		html += "	<td class='numFormat'>" + result[i].freq_user_1 + "</td>\n";
		html += "	<td class='numFormat'>" + result[i].freq_user_2 + "</td>\n";
		html += "	<td class='numFormat'>" + result[i].freq_user_3_4 + "</td>\n";
		html += "	<td class='numFormat'>" + result[i].freq_user_5_6 + "</td>\n";
		html += "	<td class='numFormat'>" + result[i].freq_user_7_9 + "</td>\n";
		html += "	<td class='numFormat'>" + result[i].freq_user_10_14 + "</td>\n";
		html += "	<td class='numFormat'>" + result[i].freq_user_15_19 + "</td>\n";
		html += "	<td class='numFormat'>" + result[i].freq_user_20_49 + "</td>\n";
		html += "	<td class='numFormat'>" + result[i].freq_user_50_99 + "</td>\n";
		html += "	<td class='numFormat'>" + result[i].freq_user_100_499 + "</td>\n";
		html += "	<td class='numFormat'>" + result[i].freq_user_over_500 + "</td>\n";
		html += "</tr>\n";
	}
	$("#report_table_0").append(html);
}

function displayTimeSeriesSessions(result){
	var chart = null;
	var term = $('#term').val();
	var fromTo = $('#fromTo').val();
	var cate = new Array(result.length);

	var data_01 = new Array(result.length);
	var data_02= new Array(result.length);
	var data_03 = new Array(result.length);
	var data_04 = new Array(result.length);
	var data_05 = new Array(result.length);
	var data_06 = new Array(result.length);
	var data_07 = new Array(result.length);
	var data_08 = new Array(result.length);
	var data_09 = new Array(result.length);
	var data_10 = new Array(result.length);
	var data_11 = new Array(result.length);

	for(var i = 0;i<result.length;i++) {
		var row = result[i];
		if(term == 'daily'){
			cate[i] = moment(row.date).format('MMM D, YYYY');
		}else if(term=='weekly'){
			cate[i] = moment(row.fromDate).format('MMM D, YYYY') + ' ~ ' + moment(row.toDate).format('MMM D, YYYY');
		}else if(term=='monthly'){
			cate[i] = moment(row.year + '-' + row.month).format('MMM, YYYY');
		}
		data_01[i] = row.freq_user_1;
		data_02[i] = row.freq_user_2;
		data_03[i] = row.freq_user_3_4;
		data_04[i] = row.freq_user_5_6;
		data_05[i] = row.freq_user_7_9;
		data_06[i] = row.freq_user_10_14;
		data_07[i] = row.freq_user_15_19;
		data_08[i] = row.freq_user_20_49;
		data_09[i] = row.freq_user_50_99;
		data_10[i] = row.freq_user_100_499;
		data_11[i] = row.freq_user_over_500;
	}

	var space = term=='daily'?0.2:(term=='weekly'?0.4:0.15);
	var xstep = parseInt(result.length * space);
	var data_array = [{
        			name: cate_name[0],
        			data: data_01
   		 		}, {
        			name: cate_name[1],
        			data: data_02
   		 		}, {
        			name: cate_name[2],
        			data: data_03
   		 		}, {
        			name: cate_name[3],
        			data: data_04
   		 		}, {
        			name: cate_name[4],
        			data: data_05
   		 		}, {
        			name: cate_name[5],
        			data: data_06
   		 		}, {
        			name: cate_name[6],
        			data: data_07
   		 		}, {
        			name: cate_name[7],
        			data: data_08
   		 		}, {
        			name: cate_name[8],
        			data: data_09
   		 		}, {
        			name: cate_name[9],
        			data: data_10
   		 		}, {
        			name: cate_name[10],
        			data: data_11
   		 		}

	];
	var subTitle = makeSubTitle(term, fromTo);
	makeDefaultAreaChart(chart, 'container1', $("#term option:selected").text() +' <spring:message code="chart.perf.session.area.title"/>', subTitle, '<spring:message code="chart.perf.session.area.y.text"/>', cate, xstep,data_array);
}

function displayFrequenceRanges(result){
	var chart = null;
	var term = $('#term').val();
	var fromTo = $('#fromTo').val();
	// 막대 차트를 만들어준다.
	var colors = Highcharts.getOptions().colors;
		data = [
			    {y: result.freq_user_1, color: colors[0]},
			    {y: result.freq_user_2, color: colors[1]},
			    {y: result.freq_user_3_4, color: colors[2]},
			    {y: result.freq_user_5_6, color: colors[3]},
			    {y: result.freq_user_7_9, color: colors[4]},
			    {y: result.freq_user_10_14, color: colors[5]},
			    {y: result.freq_user_15_19, color: colors[6]},
			    {y: result.freq_user_20_49, color: colors[7]},
			    {y: result.freq_user_50_99, color: colors[8]},
			    {y: result.freq_user_100_499, color: colors[9]},
			    {y: result.freq_user_over_500, color: colors[10]}
			];
	var total = result.total;//백분율계산을 위한 총계
	var subTitle = makeSubTitle(term, fromTo);
	makeDefaultColumnChart(chart, 'container2', '<spring:message code="chart.perf.session.var.title"/>', subTitle, '<spring:message code="chart.perf.session.var.y.text"/>', cate_name , data, total);
}

//sessions 기본 요약정보
function displaySessionsFigures(figure){
	$('#total').text(figure.strTotal);
	$('#total').attr('title',$.formatNumber(figure.total, {format:"#,##0", locale:"us"})).tooltip('destroy').tooltip();
	$('#average').text(figure.strAverage);
	$('#average').attr('title',$.formatNumber(figure.average, {format:"#,##0.0", locale:"us"})).tooltip('destroy').tooltip();;
}

function displayTotalSessionsExcelTable(result){
	var html = '';
	var term = $('#term').val();
	var excel_date = "";

	// 헤더만 남기고 전부삭제
	while($("#report_table_0 tr").length > 0){
		$("#report_table_0 tr").last().remove();
	}
	html = "<tr><th width='50'>Date</th><th width='30'>Sessions</th></tr>";
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
		html += "	<td class='numFormat'>" + result[i].value + "</td>\n";
		html += "</tr>\n";
	}
	$("#report_table_0").append(html);
}

//total Sessions graph&table
function displayTotalSessions(result){
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
	makeDefaultLineChart(chart, 'container1', $("#term option:selected").text() +' <spring:message code="chart.perf.session.line.title"/>', subTitle, '<spring:message code="chart.perf.session.line.y.text"/>', cate, xstep, data, parseInt(avg));
}

</script>
</head>
<body>
	<div id="main-cont">
	<!-- section start -->
	<jsp:include page="/performance/include/section" flush="true">
	 <jsp:param name="appkey" value="${app.appkey}"/>
	 <jsp:param name="subMenu" value="sessions" />
	</jsp:include>
	<!-- section end -->
	<!-- graph start -->
	<div class="sub-content">
	   	<div class="sub-title-wrap">
				<a class="sub-down-report" href="javascript:download_report('1');"><img src="<c:url value="/resources/img/btn-excel.png"/>"  alt="Excel Down" title="Excel Down"/></a>
	            <h2 class="title"><spring:message code="dash.card.sessions.title"/></h2>
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
					<select name="segment" id="segment" class="input-large">
						<option value="total"><spring:message code="chart.select3.sessions.option2"/></option>
					    <option value="timeSeries"><spring:message code="chart.select3.sessions.option1"/></option>
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
                  <th scope="col"><spring:message code="chart.subcnt.title.totalSession"/></th>
                  <th scope="col"><spring:message code="chart.subcnt.title.dailyAvg.session"/></th>
              </tr>
              <tr>
              	  <td><span id="total" title=""></span></td>
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