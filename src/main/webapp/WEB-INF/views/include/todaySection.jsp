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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="fh"%>

	<div id="snapshot">
       <div class="rtag">Till  <span class="nowTime"></span> : 00 <i class="icon-info-sign this_week_Info" id="timezone" title=""></i></div>
	   <h1 class="title"><spring:message code="main.slide.subTitle6"/>&nbsp;<i class="icon-info-sign todayNotifi" style="mar" data-content="<spring:message code="today.impomation"/>" data-original-title="<spring:message code="main.slide.subTitle6"/>" rel="popover"></i></h1>
	   <div class="pannel" id="today">
	   	<div class="item hand" id="newUsers">
	       	<div class="inner">
	        	<h2 class="pannel-name help" data-content="<spring:message code="dash.card.today.newUser.alt.subscript"/>" data-original-title="<spring:message code="dash.card.newUser.alt.title"/>" rel="popover"><spring:message code="dash.card.newUser.title"/> </h2>
	           	<div id="inner-body" class="normal">
	              	 <p class="sub_txt"><spring:message code="today.card.totalToday"/></p>
	              	 <p class="big-number">${app.dashBoard.todayNewUsers.strToday} <span class="small2"><spring:message code="dash.card.indicator.users"/></span></p>
	                 <p class="sub_txt2"><strong> <span class="prevTime"></span>:00 ~  <span class="nowTime"></span>:00 : </strong>${app.dashBoard.todayNewUsers.strCurrent} <spring:message code="dash.card.indicator.users"/></p>
	            </div>
	        </div>
	    </div>
	   	<div class="item hand" id="activeUsers">
	       	<div class="inner">
	            <h2 class="pannel-name help" data-content="<spring:message code="dash.card.today.activeUser.alt.subscript"/>" data-original-title="<spring:message code="dash.card.activeUser.alt.title"/>" rel="popover"><spring:message code="dash.card.activeUser.title"/></h2>
	           	<div id="inner-body" class="normal">
	              	 <p class="sub_txt"><spring:message code="today.card.totalToday"/></p>
	              	 <p class="big-number">${app.dashBoard.todayActiveUsers.strToday} <span class="small2"><spring:message code="dash.card.indicator.users"/></span></p>
	                 <p class="sub_txt2"><strong> <span class="prevTime"></span>:00 ~  <span class="nowTime"></span>:00 : </strong>${app.dashBoard.todayActiveUsers.strCurrent} <spring:message code="dash.card.indicator.users"/></p>
	             </div>
	         </div>
	    </div>
	   	<div class="item hand" id="sessions">
	       	<div class="inner">
	    	    <h2 class="pannel-name help" data-content="<spring:message code="dash.card.today.sessions.alt.subscript"/>" data-original-title="<spring:message code="dash.card.sessions.alt.title"/>" rel="popover"><spring:message code="dash.card.sessions.title"/></h2>
	           	<div id="inner-body" class="normal">
               	  <p class="sub_txt"><spring:message code="today.card.totalToday"/></p>
               	  <p class="big-number">${app.dashBoard.todaySessions.strToday}  <span class="small2"><spring:message code="dash.card.indicator.timesrun"/></span></p>
                  <p class="sub_txt2"><strong> <span class="prevTime"></span>:00 ~  <span class="nowTime"></span>:00 : </strong>${app.dashBoard.todaySessions.strCurrent} <spring:message code="dash.card.indicator.timesrun"/></p>
                </div>
            </div>
	    </div>
	   	<div class="item hand" id="sessionLength">
	       	<div class="inner">
	            <h2 class="pannel-name help" data-content="<spring:message code="dash.card.today.sessionLength.alt.subscript"/>" data-original-title="<spring:message code="dash.card.sessionlen.alt.title"/>" rel="popover"><spring:message code="dash.card.sessionlen.title"/></h2>
	           	<div id="inner-body" class="normal">
               	  <p class="sub_txt"><spring:message code="today.chart.medianToday"/></p>
               	  <p class="big-number">${app.dashBoard.todaySessionLength.strToday} <span class="small2"><spring:message code="dash.card.indicator.sec"/></span></p>
                  <p class="sub_txt2"><strong> <span class="prevTime"></span>:00 ~  <span class="nowTime"></span>:00 : </strong>${app.dashBoard.todaySessionLength.strCurrent} <spring:message code="dash.card.indicator.sec"/></p>
               </div>
           </div>

       </div>
	   	<div class="item hand" id="pageViews">
	       	<div class="inner">
	            <h2 class="pannel-name help" data-content="<spring:message code="dash.card.today.pageview.alt.subscript"/>" data-original-title="<spring:message code="dash.card.pageView.alt.title"/>" rel="popover"><spring:message code="dash.card.pageView.title"/></h2>
	           	<div id="inner-body" class="normal">
               	  <p class="sub_txt"><spring:message code="today.card.totalToday"/></p>
               	  <p class="big-number">${app.dashBoard.todayPageViews.strToday} <span class="small2"><spring:message code="dash.card.indicator.views"/></span></p>
                  <p class="sub_txt2"><strong> <span class="prevTime"></span>:00 ~  <span class="nowTime"></span>:00 : </strong>${app.dashBoard.todayPageViews.strCurrent} <spring:message code="dash.card.indicator.views"/></p>
                </div>
           </div>

       </div>
	   </div>
	   <hr class="line" />
	</div>

<script type="text/javascript">
var timecheck = -(new Date().getTimezoneOffset()/60); // 표준시 
var koreaTime = 9;
var distinctionTime = Math.floor(timecheck - koreaTime); // 한국 표준시 - 클라이언트 표준시 
$(function() {
	// TimeZone 설정-------------------------------------------------------------------------------------
	var nowTime = ${app.dashBoard.todayNowTime};
	var prevTime = ${app.dashBoard.todayPrevTime};
	if( distinctionTime != 0){
		var date = new Date();
		$(".todayDate-width").attr('value', "<spring:message code='today.card.today'/> : "+date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()); // today : 2013-12-18 설정
	}
	makeTodayTimeZone(nowTime,prevTime);
	// TimeZone 설정-------------------------------------------------------------------------------------	

	//alert('${param.subMenu}');
	//submenu setting
	$('.todayNotifi').popover({trigger:'hover',placement:'right'});
	
	var subMenu = '${param.subMenu}';
	$('#' + subMenu).find('h2').addClass('title-over');
	$('#' + subMenu).find('div.inner').append('<div class="over-pointer"><img src="<c:url value="/resources/img/arrow_pointer.png"/>" /></div>');


	$('.item').mouseover(function(){
		if($(this).attr('id') != subMenu){
			$(this).find('h2').addClass('title-over');
		}
	});

	$('.item').mouseout(function(){
		if($(this).attr('id') != subMenu){
			$(this).find('h2').removeClass('title-over');
		}
	});

	$('.item').click(function(){
		if($(this).attr('id') != subMenu){
			var url = '<c:url value="/"/>'+$(this).parent('.pannel').attr('id')+'/'+$(this).attr('id')+'/'+'${app.appkey}';
			location.href=url;
		}
	});
});
</script>

