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
<meta name="currentMenu" content="activeusers" />
<title>Fingra.ph - <spring:message code="main.slide.subTitle6"/></title>
<script type="text/javascript" src="<c:url value="/resources/js/fingraph-today-statConfig.js"/>"></script>
<script type="text/javascript">
    function getFingraphData() {
        $.ajax({
            url: '<c:url value="/today/getActiveUsersAjax" />',
            data: $('#fingraphSearchParam').serialize(),
            type: 'POST',
            dataType: 'json',
            success: function (data) {
                displayActiveUsersFigures(data.figures); // 수치
                displayActiveUsersLineGrap(data.list); // ActiveUsers Today line grap
                displayTimeSeriesActiveUsersGrap(data.list, data.figures); // ActiveUsers Today timeSeries grap
                displayActiveUsersExcelTable(data.list); ////ActiveUsers Today Excel
            }
        });
    }
    $(function(){
        getFingraphData();
    });

// newUser 기본 요약정보
function displayActiveUsersFigures(figure){
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

// ActiveUsers Today line grap
function displayActiveUsersLineGrap( result){
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
		//alert(data[i]);
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
	var space = 0.2;
	var xstep = parseInt(cate * space);
	var title = '<spring:message code="today.graph.title.cumul.activeUsers"/>';
	var subTitle = '(<spring:message code="today.card.today"/>)';
    if (moment($('#fingraphSearchParam #today').val()).diff(moment(),'days') !=0){
        subTitle = '(' + $('#fingraphSearchParam #today').val() + ')';
    }
	makeDefaultLineChartToday(chart, 'container1', title, subTitle , '<spring:message code="chart.perf.activeUser.y.text"/>', cate, xstep, data );
}

//ActiveUsers Today timeSeries grap
function displayTimeSeriesActiveUsersGrap(result, figures){
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
	var title = '<spring:message code="today.graph.title.activeUsers"/>';
	var subTitle = '(<spring:message code="today.card.today"/>)';
    if (moment($('#fingraphSearchParam #today').val()).diff(moment(),'days') !=0){
        subTitle = '(' + $('#fingraphSearchParam #today').val() + ')';
    }
	makeDefaultColumnChartToday(chart, 'container2', title, subTitle, '<spring:message code="chart.perf.activeUser.y.text"/>', cate , data, total);
}

//ActiveUsers Today Excel
function displayActiveUsersExcelTable(result){
	// 헤더만 남기고 전부 삭제
	while($("#report_table_0 tr").length > 1){
		$("#report_table_0 tr").last().remove();
	}
	var html = "";
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
</script>
</head>
<body>
	<div id="main-cont">
	<!-- section start -->
	<jsp:include page="/today/include/section" flush="true">
	 <jsp:param name="appkey" value="${app.appkey}"/>
	 <jsp:param name="subMenu" value="activeUsers" />
	</jsp:include>
	<!-- section end -->

	<!-- graph start -->
		<div class="sub-content">
			<div class="sub-title-wrap">
				<a class="sub-down-report" href="javascript:download_report('1');"><img src="<c:url value="/resources/img/btn-excel.png"/>"	alt="Excel Down" title="Excel Down" /></a>
				<h2 class="title">
					<spring:message code="dash.card.activeUser.title" />
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
    <table id="report_table_0" style="display: none;">
       <tr>
         <th width="50">Time</th>
         <th width="50">NewUsers</th>
       </tr>
     </table>

	</div>
</body>
</html>