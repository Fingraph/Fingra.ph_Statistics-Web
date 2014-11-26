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
<meta name="currentMenu" content="topcountries" />
<title>Fingra.ph - <spring:message code="dash.title.components"/></title>
<script type="text/javascript" src="<c:url value="/resources/js/fingraph-yesterday-statConfig.js"/>"></script>
<script type="text/javascript">
var countryList;
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

});


function getFingraphData(){
	$('#groupkey').val($("#componentGrpList").val());
	$.ajax({
		url: '<c:url value="/components/getTopCountriesAjax"/>',
		data: $('#fingraphSearchParam').serialize(),
		type: 'POST',
		dataType: 'json',
		success: function(data) {
			if(data.slist != null){
			displayTopCountries(data);
			displayCountryList(data.slist);//selectBox구성
			getActualData($("select[name='selectCountry']").val());
			displayTopCountriesExcelTable(data);
			}else{
				$('#container').empty();
				while($("#actual_table tr").length > 1) {
					$("#actual_table tr").last().remove();
				}

			}

		}
	});
}

//TopCountries Excel table
function displayTopCountriesExcelTable(data){
	var tlist = data.tlist;
	var slist = data.slist;
	// remove all except header
	while($("#report_table_0 tr").length > 0) {
		$("#report_table_0 tr").last().remove();
	}
	var html = "";
 	//var excel_date = '';

	html += "<tr><th width='30'>component</th>";
	for(var i = 0;i<slist.length;i++) {
		html += "<th width='30'>"+slist[i].text+"</th>";
	}
	html += "</tr>";
	for(var j=0;j<tlist.length;j++){
		html += "<tr>";
		html += "<td>" + tlist[j].name +"</td>";
		for(var k=0; k<slist.length;k++){
			html += "<td class='numFormat'>" + eval('tlist['+j+'].top'+k) +"</td>";
		}
		html += "</tr>";
	}

	$("#report_table_0").append(html);
}

function displayCountryList(result){
	if(result.length>0){
		 countryList = $("#countryList").msDropdown({
			byJson:{data:result,name:'selectCountry'},
			roundedCorner:true,
			visibleRows:8,
			on:{change: function(data, ui) {
				getActualData(data.value);
				}
			}
			}).data("dd");
		countryList.set("selectedIndex", 0);
	}
}

function getActualData(selectValue){
	$('#selectValue').val(selectValue);
	$('#groupkey').val($("#componentGrpList").val());
	$.ajax({
		url: '<c:url value="/components/getTopCountriesActualDataAjax"/>',
		data: $('#fingraphSearchParam').serialize(),
		type: 'POST',
		dataType: 'json',
		success: function(data) {
			displayTimeOfDayTable(data.alist);
		}
	});
}

//topCountries graph&table
function displayTopCountries(data){
	var chart = null;
	var term = $('#term').val();
	var fromTo = $('#fromTo').val();
	var result = data.tlist;//data 10
	var list = data.slist;//top5 of countries
	var cate = new Array(list.length);

	for(var k=0;k<list.length;k++){
		cate[k]=list[k].text;
	}

	var data_array = new Array(result.length);
	for(var i = 0;i<result.length;i++) {
		var row = result[i];
		var user_array = new Array(list.length);
		for(var j=0; j<list.length;j++){
			user_array[j] = eval('result['+i+'].top'+j);
		}
		var isVisible = i<10?true:false;
		var data_row = {name:row.name, data:user_array,visible: isVisible};
		data_array[i] = data_row;
	}
	//graph title
	var groupVal = $('#componentGrpList').val();
	var groupText = $('#componentGrpList option:selected').text();
	var barTitle='<spring:message code="chart.comp.countries.bar.title.topc"/>';
	if (groupVal==-1){
		barTitle='<spring:message code="chart.comp.countries.bar.title.interg"/>';
	}else if(groupVal>=0){
		barTitle=groupText +': '+'<spring:message code="chart.comp.countries.bar.title.group"/>';
	}
	var subTitle = makeSubTitle(term, fromTo);
	makeStackedColumnChart(chart, 'container', barTitle, subTitle, '<spring:message code="chart.comp.countries.var.y.text"/>', cate,'', data_array);
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
	           <div class="chart" id="container" class="graph_visual"style=" min-width: 100px; height: 344px; margin: 0 auto;"></div>
	           <div class="sel-menu" id="countryList">
	               <!-- countryList Selectbox  -->
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