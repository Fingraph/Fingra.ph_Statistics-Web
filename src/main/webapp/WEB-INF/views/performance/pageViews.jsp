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
<meta name="currentMenu" content="pageviews"/>
<title>Fingra.ph - <spring:message code="dash.title.performance"/></title>
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
		url: '<c:url value="/performance/getPageViewsAjax"/>',
		data: $('#fingraphSearchParam').serialize(),
		type: 'POST',
		dataType: 'json',
		success: function(data) {
			displayPageViewsFigures(data.figures);
			displayPageViews(data.figures,data.list);
			displayPageViewsExcelTable(data.list);
		}
	});
}
//PageViews 기본 요약정보
function displayPageViewsFigures(figure){
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

function displayPageViewsExcelTable(result){
	var term = $('#term').val();
	var html ="";
	var excel_date="";
	while($("#report_table_0 tr").length > 1){
		$("#report_table_0 tr").last().remove();
	}
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

//PageViews Daily graph&table
function displayPageViews(figures,result){
	var chart = null;
	var term = $('#term').val();
	var fromTo = $('#fromTo').val();
	var cate = new Array(result.length);
	var data = new Array(result.length);

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

	}

	data=makeKnowMaxMinDataSymbol(data,figures.maximum,figures.minimum);
	var space = term=='daily'?0.2:(term=='weekly'?0.4:0.15);
	var xstep = parseInt(result.length * space);
	var subTitle = makeSubTitle(term, fromTo);
	makeDefaultLineChart(chart, 'container',$("#term option:selected").text() +' <spring:message code="chart.perf.pageView.y.text"/>', subTitle, '<spring:message code="chart.perf.pageView.y.text"/>', cate, xstep, data, parseInt(figures.average));
}


</script>
</head>
<body>
	<div id="main-cont">
	<!-- section start -->
	<jsp:include page="/performance/include/section" flush="true">
	 <jsp:param name="appkey" value="${app.appkey}"/>
	 <jsp:param name="subMenu" value="pageViews" />
	</jsp:include>
	<!-- section end -->
	<!-- graph start -->
	<div class="sub-content">
	   	<div class="sub-title-wrap">
	   			<a class="sub-down-report" href="javascript:download_report('1');"><img src="<c:url value="/resources/img/btn-excel.png"/>"  alt="Excel Down" title="Excel Down"/></a>
	           <h2 class="title"><spring:message code="dash.card.pageView.title"/></h2>
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
	           <col width="20%" />
	           <col width="20%" />
	           <col width="20%" />
	           <col width="20%" />
	           <col />
             </colgroup>
              <tr>
                  <th scope="col"><spring:message code="chart.subcnt.title.totalPv"/></th>
                  <th scope="col"><span class="termStr"></span> MAX</th>
                  <th scope="col"><span class="termStr"></span> MIN</th>
                  <th scope="col"><span class="termStr"></span> AVG.</th>
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
       <tr>
         <th width="50">Date</th>
         <th width="50">pageViews</th>
       </tr>
     </table>
	</div>
</body>
</html>