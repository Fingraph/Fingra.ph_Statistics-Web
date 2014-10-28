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
<meta name="currentMenu" content="timeofday" />
<title>Fingra.ph - <spring:message code="dash.title.distribution"/></title>
<script type="text/javascript" src="<c:url value="/resources/js/fingraph-yesterday-statConfig.js"/>"></script>
<script type="text/javascript">
var cate = ['0AM-1AM','1AM-2AM','2AM-3AM','3AM-4AM','4AM-5AM','5AM-6AM','6AM-7AM','7AM-8AM','8AM-9AM','9AM-10AM','10AM-11AM','11AM-12AM',
            '0PM-1PM','1PM-2PM','2PM-3PM','3PM-4PM','4PM-5PM','5PM-6PM','6PM-7PM','7PM-8PM','8PM-9PM','9PM-10PM','10PM-11PM','11PM-12PM'];
$(function() {
	//period setting
	period = $("#period").msDropdown({
			roundedCorner:true,
			on:{change: function(data, ui){addPeriods(data.value);}}
			}).data("dd");
	//term setting
	term = $("#term").msDropdown().data("dd").set("disabled", true);
	//segment setting
	segment = $("#segment").msDropdown().data("dd").set("disabled", true);

	//$('.calImg').click(function(){period.setIndexByValue('c-u');});
	getPeriodCookie('fingraphPeriod');
});
function getFingraphData(){
	$.ajax({
		url: '<c:url value="/distribution/getTimeOfDayAjax"/>',
		data: $('#fingraphSearchParam').serialize(),
		type: 'POST',
		dataType: 'json',
		success: function(data) {
			if (data.figures !=null){
				displayTimeOfDayFigures(data.figures);
			}
			displayTimeOfDayResult(data.result);
			displayTimeOfDayResultExcelTable(data.result);
		}
	});
}
//TimeOfDay 기본 요약정보
function displayTimeOfDayFigures(figures){
	$('#most').text(figures.most);
	$('#least').text(figures.least);
}
//TimeOfDay graph&table
function displayTimeOfDayResult(result){
	if(result==null) return;
	var colors = ['#2d3555','#1f2b53','#1b3f76','#1d5fa1','#238fc4','#2ba4e1','#44bbf7','#43f0f0','#43f099','#43f060','#77e142','#d0e142','#f0d83a','#f0a93a','#fa873a','#fa633a','#d94527','#d8302c','#d12656','#c244a2','#9d3cce','#4b2694','#232379','#191957'];
	var chart = null;
	var term = $('#term').val();
	var fromTo = $('#fromTo').val();
	/*
	for(var i=0; i<result.length; i++){
		alert(result[i]);
	}
	*/
    data = [
            {y: result.zero_session , color: colors[0]},
            {y: result.one_session , color: colors[1]},
            {y: result.two_session , color: colors[2]},
            {y: result.three_session ,color: colors[3]},
            {y: result.four_session,color: colors[4] },
            {y: result.five_session,color: colors[5] },
            {y: result.six_session ,color: colors[6]},
            {y: result.seven_session ,color: colors[7]},
            {y: result.eight_session,color: colors[8] },
            {y: result.nine_session,color: colors[9] },
            {y: result.ten_session,color: colors[10] },
            {y: result.eleven_session,color: colors[11] },
            {y: result.twelve_session,color: colors[12] },
            {y: result.thirteen_session,color: colors[13] },
            {y: result.fourteen_session ,color: colors[14]},
            {y: result.fifteen_session,color: colors[15] },
            {y: result.sixteen_session,color: colors[16] },
            {y: result.seventeen_session,color: colors[17] },
            {y: result.eighteen_session,color: colors[18] },
            {y: result.nineteen_session,color: colors[19] },
            {y: result.twenty_session,color: colors[20] },
            {y: result.twentyone_session,color: colors[21] },
            {y: result.twentytwo_session,color: colors[22] },
            {y: result.twentythree_session,color: colors[23] }
		];

	var total = result.total;
	var subTitle = makeSubTitle(term, fromTo);
    makeDefaultColumnChart(chart, 'container', '<spring:message code="chart.dist.timeOfDay.title"/>', subTitle, '<spring:message code="chart.dist.timeOfDay.y.text"/>', cate, data, total);

}
// timeOfDay Excel table
function displayTimeOfDayResultExcelTable(result){
	if(result==null) return;
	var html = "";
	// 헤더만 남기고 전부삭제
	while($("#report_table_0 tr").length > 1){
		$("#report_table_0 tr").last().remove();
	}
    html += "<tr><td>"+ cate[0] +"</td><td class='numFormat'>"+ result.zero_session +"</td></tr><tr><td>"+ cate[1] +"</td><td class='numFormat'>"+ result.one_session +"</td></tr><tr><td>"+ cate[2] +"</td><td class='numFormat'>"+ result.two_session +"</td></tr>";
    html += "<tr><td>"+ cate[3] +"</td><td class='numFormat'>"+ result.three_session +"</td></tr><tr><td>"+ cate[4] +"</td><td class='numFormat'>"+ result.four_session +"</td></tr><tr><td>"+ cate[5] +"</td><td class='numFormat'>"+ result.five_session +"</td></tr>";
    html += "<tr><td>"+ cate[6] +"</td><td class='numFormat'>"+ result.six_session +"</td></tr><tr><td>"+ cate[7] +"</td><td class='numFormat'>"+ result.seven_session +"</td></tr><tr><td>"+ cate[8] +"</td><td class='numFormat'>"+ result.eight_session +"</td></tr>";
    html += "<tr><td>"+ cate[9] +"</td><td class='numFormat'>"+ result.nine_session +"</td></tr><tr><td>"+ cate[10] +"</td><td class='numFormat'>"+ result.ten_session +"</td></tr><tr><td>"+ cate[11] +"</td><td class='numFormat'>"+ result.eleven_session +"</td></tr>";
    html += "<tr><td>"+ cate[12] +"</td><td class='numFormat'>"+ result.twelve_session +"</td></tr><tr><td>"+ cate[13] +"</td><td class='numFormat'>"+ result.thirteen_session +"</td></tr><tr><td>"+ cate[14] +"</td><td class='numFormat'>"+ result.fourteen_session +"</td></tr>";
    html += "<tr><td>"+ cate[15] +"</td><td class='numFormat'>"+ result.fifteen_session +"</td></tr><tr><td>"+ cate[16] +"</td><td class='numFormat'>"+ result.sixteen_session +"</td></tr><tr><td>"+ cate[17] +"</td><td class='numFormat'>"+ result.seventeen_session +"</td></tr>";
    html += "<tr><td>"+ cate[18] +"</td><td class='numFormat'>"+ result.eighteen_session +"</td></tr><tr><td>"+ cate[19] +"</td><td class='numFormat'>"+ result.nineteen_session +"</td></tr><tr><td>"+ cate[20] +"</td><td class='numFormat'>"+ result.twenty_session +"</td></tr>";
    html += "<tr><td>"+ cate[21] +"</td><td class='numFormat'>"+ result.twentyone_session +"</td></tr><tr><td>"+ cate[22] +"</td><td class='numFormat'>"+ result.twentytwo_session +"</td></tr><tr><td>"+ cate[23] +"</td><td class='numFormat'>"+ result.twentythree_session +"</td></tr>";
    $("#report_table_0").append(html);
}
</script>
</head>
<body>
	<div id="main-cont">
	<!-- section start -->
	<jsp:include page="/distribution/include/section" flush="true">
	 <jsp:param name="appkey" value="${app.appkey}"/>
	 <jsp:param name="subMenu" value="timeOfDay" />
	</jsp:include>
	<!-- section end -->
	<!-- graph start -->
	<div class="sub-content">
	   	<div class="sub-title-wrap">
	   			<a class="sub-down-report" href="javascript:download_report('1');"><img src="<c:url value="/resources/img/btn-excel.png"/>"  alt="Excel Down" title="Excel Down"/></a>
	           <h2 class="title"><spring:message code="dash.card.timeOfDay.title"/></h2>
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
					    <option value=""><spring:message code="chart.select3.default.option0"/></option>
					 </select>
	          </div>
	         </form>
	           <div class="chart" id="container" class="graph_visual"></div>
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
                  <th scope="col"><spring:message code="chart.subcnt.title.usedMostFrom"/></th>
                  <th scope="col"><spring:message code="chart.subcnt.title.usedLeastFrom"/></th>
              </tr>
              <tr>
                  <td><span id="most" title=""></span></td>
                  <td><span id="least" title=""></span></td>
              </tr>
          </table>
	    </div>
	    <!-- figures end -->
		<table id="report_table_0" style="display:none;">
			<tr>
				<th width="50">time</th>
				<th width="50">Sessions</th>
			</tr>
		</table>
	</div>
</body>
</html>