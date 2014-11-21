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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="menuId" content="today" />
<meta name="currentMenu" content="sessions" />
<title>Fingra.ph - <spring:message code="main.slide.subTitle6"/></title>
<script type="text/javascript" src="<c:url value="/resources/js/fingraph-today-statConfig.js"/>"></script>
<script type="text/javascript">
var cate_name = ['1 times run', '2 times run','3~4 times run','5~6 times run','7~9 times run','10~14 times run','15~19 times run','20~49 times run','50~99 times run','100~499 times run','over 500 times run'];
var cate_nameDis = ['1 times run', '2 times run','3~4 times run','5~6 times run','7~9 times run','10~14 times run','15~19 times run'];
$(function(){
	getFingraphData();
	//segment setting
	segment = $("#segment").msDropdown({
			roundedCorner:true,
			on:{change: function(data, ui){	getFingraphData();}}
			}).data("dd");
});

function getFingraphData(){
	$.ajax({
		url : '<c:url value="/today/getSessionsAjax" />',
		data : $('#fingraphSearchParam').serialize(),
		type : 'POST',
		dataType : 'json',
		success : function(data){
			displaySessionsFigures(data.figures); // 수치
			displayTimeSeriessSessionsGrap(data.list, data.figures); // 시간대별 실행횟수 graph
			displayFrequenceRangesGrap(data.total); //  싨행구간별 분포 graph
			if( $("#segment").val() == 'timeSeries'){
				displaySessionsFrequenceGrap(data.slist); // 시간대별 실행구간별 사용자 graph
				displaySessionsFrequenceExcelTable(data.slist); //  시간대별 실행구간별 사용자 Excel
			}else if( $("#segment").val() == 'total'){
				displaySessionsLineGrap(data.list); //  시간대별 누적 실행횟수 graph
				displaySessionsLineExcelTable(data.list); //  시간대별 실행구간별 사용자 Excel
			}
		}
	});
}

// sessions 기본 요약정보
function displaySessionsFigures(figure){
	$('.termStr').text($("#term option:selected").text().toUpperCase());
	$('#total').text(figure.strTotal);
	$('#total').attr('title',$.formatNumber(figure.total, {format:"#,##0", locale:"us"})).tooltip('destroy').tooltip();
	$('#average').text(figure.strAverage);
	$('#average').attr('title',$.formatNumber(figure.average, {format:"#,##0.0", locale:"us"})).tooltip('destroy').tooltip();;
	$('#maximum').text(figure.strMaximum);
	$('#maximum').attr('title',$.formatNumber(figure.maximum, {format:"#,##0", locale:"us"})).tooltip('destroy').tooltip();;
	$('#minimum').text(figure.strMinimum);
	$('#minimum').attr('title',$.formatNumber(figure.minimum, {format:"#,##0", locale:"us"})).tooltip('destroy').tooltip();;
	$('#stddev').text(figure.strStddev);
	$('#stddev').attr('title',$.formatNumber(figure.stddev, {format:"#,##0.0", locale:"us"})).tooltip('destroy').tooltip();;
}

// sessions Today line grap
function displaySessionsLineGrap( result){
	if( result == null) return;
	var chart = null;
	var cate = new Array(24);
	var data = new Array(24);

	var temp=0;
	var temp2=0;
	for(var i=0; i<result.length; i++){
		temp = result[i].value;
		temp2 += temp;
		data[i] = temp2;
	}

	for (var i=0; i<24; i++){
 		if( distinctionTime > 0 ){ // 양수
			if( i + distinctionTime > 23){
				cate[i] = Math.abs((i+distinctionTime)-24);
			}else{
				cate[i] = i + distinctionTime;
			}
		}else if(distinctionTime < 0){ // 음수
			if( i < Math.abs(distinctionTime)){
				cate[i] = i + 24 + distinctionTime;
			}else{
				cate[i] = i - Math.abs(distinctionTime);
			}
		}else{
			cate[i] = i;
		}
		if( i >= result.length){
			data[i] = null;
		}
	}
	var space = 0.2;
	var xstep = parseInt(cate * space);
	var title = '<spring:message code="today.graph.title.cumul.sessions"/>';
	var subTitle = '(<spring:message code="today.card.today"/>)';
    if (moment($('#fingraphSearchParam #today').val()).diff(moment(),'days') !=0){
        subTitle = '(' + $('#fingraphSearchParam #today').val() + ')';
    }
	makeDefaultLineChartToday(chart, 'container1', title, subTitle , '<spring:message code="chart.perf.session.line.y.text"/>', cate, xstep, data );
}

//sessions Frequence graph
function displaySessionsFrequenceGrap(result){
	var chart = null;
	var cate = new Array(24);

 	var data_01 = new Array(24);
	var data_02 = new Array(24);
	var data_03 = new Array(24);
	var data_04 = new Array(24);
	var data_05 = new Array(24);
	var data_06 = new Array(24);
	var data_07 = new Array(24);
	var data_08 = new Array(24);
	var data_09 = new Array(24);
	var data_10 = new Array(24);
	var data_11 = new Array(24);

	for(var i=0; i<result.length;i++){
		data_01[i] = result[i].freq_user_1;
		data_02[i] = result[i].freq_user_2;
		data_03[i] = result[i].freq_user_3_4;
		data_04[i] = result[i].freq_user_5_6;
		data_05[i] = result[i].freq_user_7_9;
		data_06[i] = result[i].freq_user_10_14;
		data_07[i] = result[i].freq_user_15_19;
		data_08[i] = result[i].freq_user_20_49;
		data_09[i] = result[i].freq_user_50_99;
		data_10[i] = result[i].freq_user_100_499;
		data_11[i] = result[i].freq_user_over_500;
	}
	
	for (var i=0; i<24; i++){
 		if( distinctionTime > 0 ){ // 양수
			if( i + distinctionTime > 23){
				cate[i] = Math.abs((i+distinctionTime)-24);
			}else{
				cate[i] = i + distinctionTime;
			}
		}else if(distinctionTime < 0){ // 음수
			if( i < Math.abs(distinctionTime)){
				cate[i] = i + 24 + distinctionTime;
			}else{
				cate[i] = i - Math.abs(distinctionTime);
			}
		}else{
			cate[i] = i;
		}
		if(i >= result.length){
			for(var j=1; j<10; j++){
				eval('data_0'+j)[i] = null;
			}
			data_10[i] = null;
			data_11[i] = null;
		}
	}
	var space = 0.2;
	var xstep = parseInt(cate * space);
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
	var title = '<spring:message code="today.graph.title.eachRange.sessions"/>';
	var subTitle = '(<spring:message code="today.card.today"/>)';
    if (moment($('#fingraphSearchParam #today').val()).diff(moment(),'days') !=0){
        subTitle = '(' + $('#fingraphSearchParam #today').val() + ')';
    }
	makeDefaultAreaChart(chart, 'container1', title, subTitle, '<spring:message code="chart.perf.session.area.y.text"/>', cate, xstep,data_array);
}


// 시간대별 실행횟수 graph
function displayTimeSeriessSessionsGrap(result, figures){
	if( result == null) return;
	var chart = null;
	var colors = ['#2d3555','#1f2b53','#1b3f76','#1d5fa1','#238fc4','#2ba4e1','#44bbf7','#43f0f0','#43f099','#43f060','#77e142','#d0e142','#f0d83a','#f0a93a','#fa873a','#fa633a','#d94527','#d8302c','#d12656','#c244a2','#9d3cce','#4b2694','#232379','#191957'];
	var cate = new Array(24);
	var data = new Array(24);


	for (var i=0; i<result.length; i++){
		data[i] = {y:result[i].value, color:colors[i]};
	}
	for (var i=0; i<24; i++){
 		if( distinctionTime > 0 ){ // 양수
			if( i + distinctionTime > 23){
				cate[i] = Math.abs((i+distinctionTime)-24);
			}else{
				cate[i] = i + distinctionTime;
			}
		}else if(distinctionTime < 0){ // 음수
			if( i < Math.abs(distinctionTime)){
				cate[i] = i + 24 + distinctionTime;
			}else{
				cate[i] = i - Math.abs(distinctionTime);
			}
		}else{
			cate[i] = i;
		}
		if( i >= result.length){
			data[i] = {y:null, color:colors[i]};
		}
	}
	var total = figures.total;//백분율계산을 위한 총계
	var title = '<spring:message code="today.graph.title.sessions"/>';
	var subTitle = '(<spring:message code="today.card.today"/>)';
    if (moment($('#fingraphSearchParam #today').val()).diff(moment(),'days') !=0){
        subTitle = '(' + $('#fingraphSearchParam #today').val() + ')';
    }
	makeDefaultColumnChartToday(chart, 'container2', title, subTitle, '<spring:message code="chart.perf.session.line.y.text"/>', cate , data, total);
}

// 싨행구간별 분포 graph
function displayFrequenceRangesGrap(result){
	var chart = null;
	var colors = Highcharts.getOptions().colors;
	data = [
		    {y: result.freq_user_1, color: colors[0]},
		    {y: result.freq_user_2, color: colors[1]},
		    {y: result.freq_user_3_4, color: colors[2]},
		    {y: result.freq_user_5_6, color: colors[3]},
		    {y: result.freq_user_7_9, color: colors[4]},
		    {y: result.freq_user_10_14, color: colors[5]},
		    {y: result.freq_user_15_19}
		];
	var total = result.total;//백분율계산을 위한 총계
	var title = '<spring:message code="today.graph.title.range.sessions"/>';
	var subTitle = '(<spring:message code="today.card.today"/>)';
    if (moment($('#fingraphSearchParam #today').val()).diff(moment(),'days') !=0){
        subTitle = '(' + $('#fingraphSearchParam #today').val() + ')';
    }
	makeDefaultColumnChart(chart, 'container3', title, subTitle, '<spring:message code="chart.perf.session.area.y.text"/>', cate_nameDis , data, total);
}
// SessionsLine Today Excel
function displaySessionsLineExcelTable(result){
	// 헤더만 남기고 전부 삭제
	while($("#report_table_0 tr").length > 0){
		$("#report_table_0 tr").last().remove();
	}
	var html = "<tr><th width='50'>Time</th><th width='50'>Session</th></tr>";
	var cate = new Array(result.length+1);
	for(var i=0; i<=result.length; i++){
		if( distinctionTime > 0 ){ // 양수
			if( i + distinctionTime > 23){
				cate[i] = Math.abs((i+distinctionTime)-24);
			}else{
				cate[i] = i + distinctionTime;
			}
		}else if(distinctionTime < 0){ // 음수
			if( i < Math.abs(distinctionTime)){
				cate[i] = i + 24 + distinctionTime;
			}else{
				cate[i] = i - Math.abs(distinctionTime);
			}
		}else{
			cate[i] = i;
		}
	}
	for(var i=0; i<result.length; i++){
		html += "<tr>\n";
		html += "<td>" + cate[i] + " ~ " + cate[i+1] +"</td>\n";
		html += "<td class='numFormat'>" + result[i].value + "</td>\n";
		html += "</tr>\n";
	}
	$("#report_table_0").append(html);
}

//SessionsFrequence Today Excel
function displaySessionsFrequenceExcelTable(result){
	// 헤더만 남기고 전부 삭제
	while ($("#report_table_0 tr").length > 0){
		$("#report_table_0 tr").last().remove();
	}
	var cate = new Array(result.length+1);
	var html = "<tr><th width='50'>Time</th>";
	for(var i=0; i<cate_name.length; i++){
		html += "<th width='50'>"+ cate_name[i] + "</th>";
	}
	html += "</tr>";
	for(var i=0; i<=result.length; i++){
		if( distinctionTime > 0 ){ // 양수
			if( i + distinctionTime > 23){
				cate[i] = Math.abs((i+distinctionTime)-24);
			}else{
				cate[i] = i + distinctionTime;
			}
		}else if(distinctionTime < 0){ // 음수
			if( i < Math.abs(distinctionTime)){
				cate[i] = i + 24 + distinctionTime;
			}else{
				cate[i] = i - Math.abs(distinctionTime);
			}
		}else{
			cate[i] = i;
		}
	}
	for(var i=0; i<result.length; i++){
		html += "<tr>";
		html += "	<td>" + cate[i] + " ~ " + cate[i+1] + "</td>\n";
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
</script>
</head>
<body>
	<div id="main-cont">
	<!-- section start -->
	<jsp:include page="/today/include/section" flush="true">
	 <jsp:param name="appkey" value="${app.appkey}"/>
	 <jsp:param name="subMenu" value="sessions" />
	</jsp:include>
	<!-- section end -->

	<!-- graph start -->
		<div class="sub-content">
			<div class="sub-title-wrap">
				<a class="sub-down-report" href="javascript:download_report('1');"><img	src="<c:url value="/resources/img/btn-excel.png"/>"	alt="Excel Down" title="Excel Down" /></a>
				<h2 class="title">
					<spring:message code="dash.card.sessions.title" />
				</h2>
			</div>
			<div class="box">
				<form name="fingraphSearchParam" id="fingraphSearchParam">
					<input type="hidden" name="appkey" id="appkey"	value="${app.appkey}" />
					<c:choose>
						<c:when test="${app.dashBoard.todayNowTime eq 00 }">
 		 					<input type="hidden" name="today"	id="today" value="${app.dashBoard.yesterday} " />
							<input type="hidden" name="nowTime"	id="nowTime" value="24" />
						</c:when>
						<c:otherwise>
 		 					<input type="hidden" name="today"	id="today" value="${app.dashBoard.today} " />
							<input type="hidden" name="nowTime"	id="nowTime" value="${app.dashBoard.todayNowTime} " />
						</c:otherwise>
					</c:choose>
					<div class="sel-menu">
						<div class="fromToArea">
                            <img id="periodPrev" src="<c:url value="/resources/img/btn_pride_prev_inactive.png"/>" alt="" title="지정기간 앞 기간" class="periodPrev hand" >
                            <c:choose>
                                <c:when test="${app.dashBoard.todayNowTime eq 00 }">
                                    <input type="text" class="todayDate-width " id="from" name="from" value="${app.dashBoard.yesterday }" readonly="readonly" style="color: #3A414B; margin: 0; padding: 3px 0 2px 5px;" />
                                </c:when>
                                <c:otherwise>
                                    <input type="text" class="todayDate-width " id="from" name="from" value="${app.dashBoard.today }" readonly="readonly" style="color: #3A414B; margin: 0; padding: 3px 0 2px 5px;" />
                                </c:otherwise>
                            </c:choose>
                            <div id="calImgDiv" style="display: none;">
                                <img id="calImg" src="<c:url value="/resources/img/btn_calendar.png"/>" alt="" class="trigger calImg" style=" cursor: pointer; margin: 0 4px;">
                            </div>
                            <img id="periodNext" src="<c:url value="/resources/img/btn_pride_next_inactive.png"/>" alt="" title="지정기간 뒤 기간" class="periodNext" style="margin-left: -5px;">
						</div>
						<select name="segment" id="segment" class="input-large">
							<option value="total" selected="selected"><spring:message code="chart.select3.sessions.option2"/></option>
							<option value="timeSeries"><spring:message code="chart.select3.sessions.option1"/></option>
						 </select>
					</div>
				</form>
				<div class="chart" id="container1" class="graph_visual"></div>
				<div class="chart" id="container2" class="graph_visual"></div>
				<div class="chart" id="container3" class="graph_visual"></div>
			</div>
		</div>
		<!-- graph end -->
	  <!-- figures start -->
      <div class="figure-wrap">
          <table>
          <colgroup>
	           <col width="20%" />
	           <col width="20%" />
	           <col width="20%" />
	           <col width="20%" />
	           <col />
          </colgroup>
              <tr>
                  <th scope="col"><spring:message code="today.chart.totalToday"/></th>
                  <th scope="col"><span class="termStr"></span> <spring:message code="today.chart.hourlyMax"/></th>
                  <th scope="col"><span class="termStr"></span> <spring:message code="today.chart.hourlyMin"/></th>
                  <th scope="col"><span class="termStr"></span> <spring:message code="today.chart.hourlyAvg"/></th>
                  <th scope="col"><spring:message code="chart.subcnt.title.stdDev"/></th>
              </tr>
              <tr>
                  <td><span id="total" title=""></span></td>
                  <td><span id="maximum" title=""></span></td>
                  <td><span id="minimum" title=""></span></td>
                  <td><span id="average" title=""></span></td>
                  <td><span id="stddev" title=""></span></td>
              </tr>
          </table>
    </div>
    <!-- figures end -->

    <!-- Excel table -->
    <table id="report_table_0" style="display:none;">

     </table>

	</div>
</body>
</html>