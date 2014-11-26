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
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="fh"%>
<!DOCTYPE html>
<html>
<head>
<meta name="menuId" content="components" />
<meta name="currentMenu" content="timeofday" />
<title>Fingra.ph - <spring:message code="dash.title.components"/></title>
<script type="text/javascript" src="<c:url value="/resources/js/fingraph-yesterday-statConfig.js"/>"></script>
<script type="text/javascript">
var cate = ['0AM-1AM','1AM-2AM','2AM-3AM','3AM-4AM','4AM-5AM','5AM-6AM','6AM-7AM','7AM-8AM','8AM-9AM','9AM-10AM','10AM-11AM','11AM-12AM',
            '0PM-1PM','1PM-2PM','2PM-3PM','3PM-4PM','4PM-5PM','5PM-6PM','6PM-7PM','7PM-8PM','8PM-9PM','9PM-10PM','10PM-11PM','11PM-12PM'];
var selectTime;
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
	segment = $("#segment").msDropdown().data("dd").set("disabled", true);

	//selectTime setting
	selectTime = $("#selectTime").msDropdown({
			roundedCorner:true,
			on:{change: function(data, ui){getActualData(data.value);}}
			}).data("dd");
	
});

function getFingraphData(){
	$('#groupkey').val($("#componentGrpList").val());
	$.ajax({
		url: '<c:url value="/components/getTimeOfDayAjax"/>',
		data: $('#fingraphSearchParam').serialize(),
		type: 'POST',
		dataType: 'json',
		success: function(data) {
			displayTimeOfDayChart(data.tlist);
			selectTime.setIndexByValue(data.maxTime);
			if(data.maxTime != ''){
				getActualData($('#selectTime').val());
			}else{
				// 헤더만 남기고 전부 삭제
				while($("#actual_table tr").length > 1) {
					$("#actual_table tr").last().remove();
				}
			}
			displayTimeOfDayExcelTable(data.tlist);
		}
	});
}

//timeofday Excel table
function displayTimeOfDayExcelTable(tlist){
	// remove all except header
	while($("#report_table_0 tr").length > 0) {
		$("#report_table_0 tr").last().remove();
	}
	var html = "";
	html += "<tr><th width='30'>component</th>";
	for(var i = 0;i<cate.length;i++) {
		html += "<th width='30'>"+cate[i]+"</th>";
	}
	html += "</tr>";
	for(var j=0;j<tlist.length;j++){
		html += "<tr>";
		html += "<td>" + tlist[j].name +"</td>";
		html += "<td class='numFormat'>" + tlist[j].zero_session +"</td>";
		html += "<td class='numFormat'>" + tlist[j].one_session +"</td>";
		html += "<td class='numFormat'>" + tlist[j].two_session +"</td>";
		html += "<td class='numFormat'>" + tlist[j].three_session +"</td>";
		html += "<td class='numFormat'>" + tlist[j].four_session +"</td>";
		html += "<td class='numFormat'>" + tlist[j].five_session +"</td>";
		html += "<td class='numFormat'>" + tlist[j].six_session +"</td>";
		html += "<td class='numFormat'>" + tlist[j].seven_session +"</td>";
		html += "<td class='numFormat'>" + tlist[j].eight_session +"</td>";
		html += "<td class='numFormat'>" + tlist[j].nine_session +"</td>";
		html += "<td class='numFormat'>" + tlist[j].ten_session +"</td>";
		html += "<td class='numFormat'>" + tlist[j].eleven_session +"</td>";
		html += "<td class='numFormat'>" + tlist[j].twelve_session +"</td>";
		html += "<td class='numFormat'>" + tlist[j].thirteen_session +"</td>";
		html += "<td class='numFormat'>" + tlist[j].fourteen_session +"</td>";
		html += "<td class='numFormat'>" + tlist[j].fifteen_session +"</td>";
		html += "<td class='numFormat'>" + tlist[j].sixteen_session +"</td>";
		html += "<td class='numFormat'>" + tlist[j].seventeen_session +"</td>";
		html += "<td class='numFormat'>" + tlist[j].eighteen_session +"</td>";
		html += "<td class='numFormat'>" + tlist[j].nineteen_session +"</td>";
		html += "<td class='numFormat'>" + tlist[j].twenty_session +"</td>";
		html += "<td class='numFormat'>" + tlist[j].twentyone_session +"</td>";
		html += "<td class='numFormat'>" + tlist[j].twentytwo_session +"</td>";
		html += "<td class='numFormat'>" + tlist[j].twentythree_session +"</td>";
		html += "</tr>";
	}

	$("#report_table_0").append(html);
}

function getActualData(selectValue){
	$('#selectValue').val(selectValue);
	$('#groupkey').val($("#componentGrpList").val());
	$.ajax({
		url: '<c:url value="/components/getTimeOfDayActualDataAjax"/>',
		data: $('#fingraphSearchParam').serialize(),
		type: 'POST',
		dataType: 'json',
		success: function(data) {
			displayTimeOfDayTable(data.alist);
		}
	});
}
//TimeOfDay graph&table
function displayTimeOfDayChart(result){
	var chart = null;
	var term = $('#term').val();
	var fromTo = $('#fromTo').val();

	var data_array = new Array(result.length);
	for(var i = 0;i<result.length;i++) {
		var row = result[i];
		var user_array = new Array(cate.length);
		user_array[0] = row.zero_session;
		user_array[1] = row.one_session;
		user_array[2] = row.two_session;
		user_array[3] = row.three_session;
		user_array[4] = row.four_session;
		user_array[5] = row.five_session;
		user_array[6] = row.six_session;
		user_array[7] = row.seven_session;
		user_array[8] = row.eight_session;
		user_array[9] = row.nine_session;
		user_array[10] = row.ten_session;
		user_array[11] = row.eleven_session;
		user_array[12] = row.twelve_session;
		user_array[13] = row.thirteen_session;
		user_array[14] = row.fourteen_session;
		user_array[15] = row.fifteen_session;
		user_array[16] = row.sixteen_session;
		user_array[17] = row.seventeen_session;
		user_array[18] = row.eighteen_session;
		user_array[19] = row.nineteen_session;
		user_array[20] = row.twenty_session;
		user_array[21] = row.twentyone_session;
		user_array[22] = row.twentytwo_session;
		user_array[23] = row.twentythree_session;
		var isVisible = i<10?true:false;
		var data_row = {name:row.name, data:user_array,visible: isVisible};
		data_array[i] = data_row;
	}
	//graph title
	var groupVal = $('#componentGrpList').val();
	var groupText = $('#componentGrpList option:selected').text();
	var barTitle='<spring:message code="chart.comp.timeOfDay.bar.title.topc"/>';
	if (groupVal==-1){
		barTitle='<spring:message code="chart.comp.timeOfDay.bar.title.interg"/>';
	}else if(groupVal>=0){
		barTitle=groupText +': '+'<spring:message code="chart.comp.timeOfDay.bar.title.group"/>';
	}
	var subTitle = makeSubTitle(term, fromTo);
	makeStackedColumnChart(chart, 'container', barTitle, subTitle, '<spring:message code="chart.comp.timeOfDay.var.y.text"/>', cate,'', data_array);
}

function displayTimeOfDayTable(result){
	// remove all except header
	while($("#actual_table tr").length > 1) {
		$("#actual_table tr").last().remove();
	}
	//table td-title
	if($('#componentGrpList').val() ==-1){
		$("#actual_table tr th.tdName").html('<spring:message code="chart.comp.table.groupNm"/>');
		$("#actual_table tr th.tdOfAll").html('<spring:message code="chart.comp.table.ofAllGroup"/>');
	}else{
		$("#actual_table tr th.tdName").html('<spring:message code="chart.comp.table.compNm"/>');
		$("#actual_table tr th.tdOfAll").html('<spring:message code="chart.comp.table.ofAllComp"/>');
	}
	// make table
	for(var i = 0 ; i<result.length ; i++) {
		var html = "";
		var row = result[i];
			var name = row.name;
			var actual = $.formatNumber(row.actual, {format:"#,##0", locale:"us"});
			var percentAllCom = $.formatNumber(row.percentAllCom, {format:"#0.0", locale:"us"});

			html += '<tr>\n';
			html += '	<th>'+name+'</th>\n';
			html += '   <td>'+actual+'</td>\n';
			html += '	<td>'+percentAllCom+'%</td>\n';
			html += '</tr>\n'
		$("#actual_table").append(html);
	}
	$("#actual_table tbody tr:odd").addClass('m1');
	$("#actual_table tbody tr:even").addClass('m2');
}

</script>
</head>
<body>
	<div id="main-cont">
	<!-- section start -->
	<jsp:include page="/components/include/section" flush="true">
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
		    	<input type="hidden" name="selectValue" id="selectValue" value=""/>
		    	<input type="hidden" name="groupkey" id="groupkey" value=""/>
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
					 <select name="segment" id="segment" class="input-medium" style="display: none;">
					    <option value=""><spring:message code="chart.select3.default.option0"/></option>
					 </select>
	          </div>
	          </form>
	           <div class="chart" id="container" class="graph_visual"></div>
	           <div class="sel-menu">
	               <select name="selectTime" id="selectTime" class="input-medium">
						<option value="zero_session">0AM-1AM</option>
						<option value="one_session">1AM-2AM</option>
						<option value="two_session">2AM-3AM</option>
						<option value="three_session">3AM-4AM</option>
						<option value="four_session">4AM-5AM</option>
						<option value="five_session">5AM-6AM</option>
						<option value="six_session">6AM-7AM</option>
						<option value="seven_session">7AM-8AM</option>
						<option value="eight_session">8AM-9AM</option>
						<option value="nine_session">9AM-10AM</option>
						<option value="ten_session">10AM-11AM</option>
						<option value="eleven_session">11AM-12AM</option>
						<option value="twelve_session">0PM-1PM</option>
						<option value="thirteen_session">1PM-2PM</option>
						<option value="fourteen_session">2PM-3PM</option>
						<option value="fifteen_session">3PM-4PM</option>
						<option value="sixteen_session">4PM-5PM</option>
						<option value="seventeen_session">5PM-6PM</option>
						<option value="eighteen_session">6PM-7PM</option>
						<option value="nineteen_session">7PM-8PM</option>
						<option value="twenty_session">8PM-9PM</option>
						<option value="twentyone_session">9PM-10PM</option>
						<option value="twentytwo_session">10PM-11PM</option>
						<option value="twentythree_session">11PM-12PM</option>
				  </select>
	          	</div>
	          	<div>
                <table class="comp-table" id="actual_table">
	                <colgroup>
		                <col />
		                <col width="30%" />
		                <col width="30%" />
	                </colgroup>
                    <thead>
	                    <tr>
	                        <th scope="col" class="top tdName"><spring:message code="chart.comp.table.compNm"/></th>
	                        <th scope="col" class="top"><spring:message code="chart.comp.table.actualSession"/></th>
	                        <th scope="col" class="top tdOfAll"><spring:message code="chart.comp.table.ofAllComp"/></th>
	                    </tr>
                    </thead>
                    <tbody>
						<!-- actual data table -->
                    </tbody>
                 </table>
                </div>
	       </div>
	  </div>
	  <!-- graph end -->
	  <!-- Excel table -->
	  <table id="report_table_0" style="display:none;">
      </table>
	</div>
</body>
</html>