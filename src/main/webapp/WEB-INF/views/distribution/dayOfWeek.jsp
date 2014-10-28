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
<meta name="currentMenu" content="dayofweek" />
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
	term = $("#term").msDropdown().data("dd").set("disabled", true);
	//segment setting
	segment = $("#segment").msDropdown().data("dd").set("disabled", true);

	//$('.calImg').click(function(){period.setIndexByValue('c-u');});
	getPeriodCookie('fingraphPeriod');
});
function getFingraphData(){
	$.ajax({
		url: '<c:url value="/distribution/getDayOfWeekAjax"/>',
		data: $('#fingraphSearchParam').serialize(),
		type: 'POST',
		dataType: 'json',
		success: function(data) {
			if (data.figures !=null){
				displayDayOfWeekFigures(data.figures);
			}
			displayDayOfWeek(data.list);
			displayDayOfWeekExcelTable(data.list);
		}
	});
}
//DayOfWeek 기본 요약정보
function displayDayOfWeekFigures(figures){
	$('#most').text(figures.most);
	$('#least').text(figures.least);
}
//DayOfWeek graph&table
function displayDayOfWeek(result){
	var chart = null;
	var term = $('#term').val();
	var fromTo = $('#fromTo').val();
	var cate = new Array(result.length);
	var columnData_array = new Array(result.length);
	var lineData_array = new Array(result.length);
	
	for(var i = 0;i<result.length;i++) {
		var row = result[i];
		cate[i] = row.week;
		columnData_array[i] = row.user;
		lineData_array[i] = row.session;
		
	}
	var subTitle = makeSubTitle(term, fromTo);
	makeLineAndColumnChart(chart, 'container', '<spring:message code="chart.dist.dayOfWeek.title"/>', subTitle, '<spring:message code="chart.dist.dayOfWeek.y.text2"/>','<spring:message code="chart.dist.dayOfWeek.y.text1"/>', cate, columnData_array ,makeMaxMinDataSymbol(lineData_array));

}

// dayOfWeek Excel table
function displayDayOfWeekExcelTable(result){
	var cate = new Array(result.length);
	var columnData_array = new Array(result.length);
	var lineData_array = new Array(result.length);
	var html = "";
	
	// 헤더만 남기고 전부삭제
	while($("#report_table_0 tr").length > 1){
		$("#report_table_0 tr").last().remove();
	}
	for(var i = 0;i<result.length;i++) {
		var row = result[i];
		cate[i] = row.week;
		columnData_array[i] = row.user;
		lineData_array[i] = row.session;
		
		html += "<tr>\n";
		html += "	<td>" + cate[i] + "</td>\n";
		html += "	<td class='numFormat'>" + columnData_array[i] + "</td>\n";
		html += "	<td class='numFormat'>" + lineData_array[i] + "</td>\n";
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
	 <jsp:param name="subMenu" value="dayOfWeek" />
	</jsp:include>
	<!-- section end -->
	<!-- graph start -->
	<div class="sub-content">
	   	<div class="sub-title-wrap">
	   			<a class="sub-down-report" href="javascript:download_report('1');"><img src="<c:url value="/resources/img/btn-excel.png"/>"  alt="Excel Down" title="Excel Down"/></a>
	           <h2 class="title"><spring:message code="dash.card.dayOfWeek.title"/></h2>
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
                  <th scope="col"><spring:message code="chart.subcnt.title.usedMostOn"/></th>
                  <th scope="col"><spring:message code="chart.subcnt.title.usedLeastOn"/></th>
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
	         <th width="50">Date</th>
	         <th width="50">ActiveUser</th>
	         <th width="50">Session</th>
	       </tr>
	     </table>	    
	</div>
</body>
</html>