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
<meta name="currentMenu" content="sessionlength" />
<title>Fingra.ph - <spring:message code="main.slide.subTitle6"/></title>
<script type="text/javascript" src="<c:url value="/resources/js/fingraph-today-statConfig.js"/>"></script>
<script type="text/javascript">
var cate_name = ['0~3 sec','3~10 sec','10~30 sec','30~60 sec','1~3 min','3~10 min','10~30 min','over 30 min'];
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
		url : '<c:url value="/today/getSessionLengthAjax" />',
		data : $('#fingraphSearchParam').serialize(),
		type : 'POST',
		dataType : 'json',
		success : function(data){
			displaySessionLengthFigures(data.figures); // 수치
			displayFrequenceRangesGrap(data.total); //  싨행구간별 분포 graph
			if( $("#segment").val() == 'median'){
				displaySessionLengthFrequenceGrap(data.slist); // 시간대별 실행시간구간별 실행횟수 graph
				displaySessionLengthFrequenceExcelTable(data.slist); // 시간대별 실행시간구간별 실행횟수  Excel
			}else if( $("#segment").val() == 'total'){
				displaySessionLengthLineGrap(data.list); //  시간대별 실행시간 중앙값 graph
				displaySessionLengthLineExcelTable(data.list); //  시간대별 실행시간 중앙값 Excel
			}
		}
	});	
}

// sessions 기본 요약정보
function displaySessionLengthFigures(figure){
	$('#median').text(figure.strMedian);
	$('#median').attr('title',$.formatNumber(figure.median, {format:"#,##0.0", locale:"us"})).tooltip('destroy').tooltip();;
	$('#most').text(figure.most);
}

//시간대별 누적 실행횟수 graph
function displaySessionLengthLineGrap( result){
	if( result == null) return;
	var chart = null;
	var cate = new Array(24);
	var data = new Array(24);
	var sum = 0;
	
	for(var i=0; i<result.length; i++){
		data[i] = result[i].value;
		sum += result[i].value;
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
	//console.log(cate);	console.log(data);
	var avg = sum/result.length;
	var space = 0.2;
	var xstep = parseInt(cate * space);
	var title = '<spring:message code="today.graph.title.median.sessionLength"/>';
	var subTitle = '(<spring:message code="today.card.today"/>)';
    if (moment($('#fingraphSearchParam #today').val()).diff(moment(),'days') !=0){
        subTitle = '(' + $('#fingraphSearchParam #today').val() + ')';
    }
	makeDefaultLineChart(chart, 'container1', title, subTitle, '<spring:message code="chart.perf.sessionlen.line.y.text"/>', cate, xstep, data, parseInt(avg));
}

//시간대별 실행시간구간별 실행횟수 graph
function displaySessionLengthFrequenceGrap(result){
	var chart = null;
	var cate = new Array(24);

	var data0_3s = new Array(24);
	var data3_10s= new Array(24);
	var data10_30s = new Array(24);
	var data30_60s = new Array(24);
	var data1_3m = new Array(24);
	var data3_10m = new Array(24);
	var data10_30m = new Array(24);
	var data30m_over = new Array(24);
	
	for(var i=0; i<result.length;i++){
		data0_3s[i] = result[i].under_three_sec;
		data3_10s[i] = result[i].three_ten_sec;
		data10_30s[i] = result[i].ten_thirty_sec;
		data30_60s[i] = result[i].thirty_sixty_sec;
		data1_3m[i] = result[i].one_three_min;
		data3_10m[i] = result[i].three_ten_min;
		data10_30m[i] = result[i].ten_thirty_min;
		data30m_over[i] = result[i].over_thirty_min;
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
			data0_3s[i] = null;
			data3_10s[i] = null;
			data10_30s[i] = null;
			data30_60s[i] = null;
			data1_3m[i] = null;
			data3_10m[i] = null;
			data10_30m[i] = null;
			data30m_over[i] = null;
		}
	}	
	var space = 0.2;
	var xstep = parseInt(cate * space);
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
	var title = '<spring:message code="today.graph.title.eachRange.sessionLength"/>';
	var subTitle = '(<spring:message code="today.card.today"/>)';
    if (moment($('#fingraphSearchParam #today').val()).diff(moment(),'days') !=0){
        subTitle = '(' + $('#fingraphSearchParam #today').val() + ')';
    }
	makeDefaultAreaChart(chart, 'container1', title, subTitle, '<spring:message code="chart.perf.sessionlen.var.y.text"/>', cate, xstep, data_array);
}



//  싨행구간별 분포 graph
function displayFrequenceRangesGrap(result){
	var chart = null;
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
	var total = result.total;//백분율계산을 위한 총계
	var title = '<spring:message code="today.graph.title.range.sessionLength"/>';
	var subTitle = '(<spring:message code="today.card.today"/>)';
    if (moment($('#fingraphSearchParam #today').val()).diff(moment(),'days') !=0){
        subTitle = '(' + $('#fingraphSearchParam #today').val() + ')';
    }
	makeDefaultColumnChart(chart, 'container2', title, subTitle, '<spring:message code="chart.perf.sessionlen.var.y.text"/>', cate, data, total);
}
//시간대별 실행구간별 사용자 Excel
function displaySessionLengthLineExcelTable(result){
	// 헤더만 남기고 전부 삭제
	while($("#report_table_0 tr").length > 0){
		$("#report_table_0 tr").last().remove();
	}
	var html = "<tr><th width='50'>Time</th><th width='50'>SessionLength</th></tr>";
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
		html += "	<td class='numFormat'>" + Math.round(result[i].value) + "</td>\n";
		html += "</tr>\n";
	}
	$("#report_table_0").append(html);
}

// 시간대별 실행시간구간별 실행횟수  Excel
function displaySessionLengthFrequenceExcelTable(result){
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
		html += "<tr>\n";
		html += "<td>" + cate[i] + " ~ " + cate[i+1] +"</td>\n";
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
</script>
</head>
<body>
	<div id="main-cont">
	<!-- section start -->
	<jsp:include page="/today/include/section" flush="true">
	 <jsp:param name="appkey" value="${app.appkey}"/>
	 <jsp:param name="subMenu" value="sessionLength" />
	</jsp:include>
	<!-- section end -->
	
	<!-- graph start -->
		<div class="sub-content">
			<div class="sub-title-wrap">
				<a class="sub-down-report" href="javascript:download_report('1');"><img	src="<c:url value="/resources/img/btn-excel.png"/>"	alt="Excel Down" title="Excel Down" /></a>
				<h2 class="title"><spring:message code="dash.card.sessionlen.title"/></h2>
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
							<option value="total" selected="selected"><spring:message code="chart.select3.sessionlen.option2"/></option>
							<option value="median"><spring:message code="chart.select3.sessionlen.option1"/></option>
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
                  <th scope="col"><spring:message code="chart.subcnt.title.medianTime"/></th>
                  <th scope="col"><spring:message code="chart.subcnt.title.mostSessions"/></th>
              </tr>
              <tr>
                  <td><span id="median" title=""></span></td>
                  <td><span id="most" title=""></span></td>
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