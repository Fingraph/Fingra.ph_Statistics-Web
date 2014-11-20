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
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="fh"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="menuId" content="dashboard" />
<title>Fingra.ph Open Source Project - DASHBOARD</title>
<script type="text/javascript">
$(function() {

	var componentGrpList;
	//components check
	if('${app.hasComponents}' == 'false'){
		$('.com_inner_body').attr('id','inner-body').addClass('disable').html('<p class="big-txt"><spring:message code="btn.noComponents.text"/></p>');
		$('.componentGroup').hide();
	}else{
		//componentGrpList
		componentGrpList = $("#componentGrpList").msDropdown({
			roundedCorner:true,
			visibleRows:8,
			on:{change: function(data, ui) {
				getComponetsSnapshotAjaxData('${app.appkey}',data.value);
				}
			}
			}).data("dd");
		getComponetsSnapshotAjaxData('${app.appkey}',$("#componentGrpList").val());
	}

	function getComponetsSnapshotAjaxData(appkey,groupkey){
		$.ajax({
			url: '<c:url value="/dashboard/getComponetsSnapshotAjax"/>',
			data: {appkey:appkey,groupkey:groupkey },
			type: 'POST',
			dataType: 'json',
			success: function(data) {
				makeComponetsSnapshot(data);//fingraph-setting.js
			}
		});
	};

	$('.item').mouseover(function(){
		if($(this).attr('id') != 'notLink'){
			$(this).find('h2').addClass('title-over');
		}
	});
	$('.item').mouseout(function(){$(this).find('h2').removeClass('title-over');});
	$('.item').click(function(){
		var selectId = $(this).attr('id');
		if(selectId.indexOf("_") !=- 1){
			selectId = selectId.substring(selectId.indexOf("_")+1);
		}
		var url;
		var snapshot = $(this).parent('.pannel').attr('id');
		if('${app.hasComponents}' == false && snapshot == 'components') return;//empty components check
			if(snapshot == 'components'){
				url = '<c:url value="/"/>' + snapshot +'/'+selectId+'/'+'${app.appkey}' +'?groupkey='+ $("#componentGrpList").val() ;

			}else{
				url = '<c:url value="/"/>' + snapshot +'/'+selectId+'/'+'${app.appkey}';
			}
		location.href=url;
	});

//TimeZone 설정-------------------------------------------------------------------------------------
	var thisweekZone = "${app.dashBoard.thisWeek} <br/><spring:message code='dash.card.thisweeek.timezone'/>";
	var nowTime = ${app.dashBoard.todayNowTime};
	var prevTime = ${app.dashBoard.todayPrevTime};
	$(".this_week_Info").attr('title', thisweekZone);
	makeTodayTimeZone(nowTime,prevTime);
//TimeZone 설정-------------------------------------------------------------------------------------

});
</script>
</head>
<body>
<div id="main-cont">

	<!-- today snapshot start -->
	<div class="snapshot">
       <div class="rtag">Till  <span class="nowTime"></span> : 00 <i class="icon-info-sign this_week_Info" id="timezone" title=""></i></div>
	   <h1 class="title"><spring:message code="main.slide.subTitle6"/>&nbsp;<i class="icon-info-sign todayNotifi" data-content="<spring:message code="today.impomation"/>" data-original-title="<spring:message code="main.slide.subTitle6"/>" rel="popover"></i></h1>
	   <div class="pannel" id="today">
	   	<div class="item hand" id="today_newUsers">
	       	<div class="inner">
	        	<h2 class="pannel-name help" data-content="<spring:message code="dash.card.today.newUser.alt.subscript"/>" data-original-title="<spring:message code="dash.card.newUser.alt.title"/>" rel="popover"><spring:message code="dash.card.newUser.title"/> </h2>
	           	<div id="inner-body" class="normal">
	              	 <p class="sub_txt"><spring:message code="today.card.totalToday"/></p>
	              	 <p class="big-number">${app.dashBoard.todayNewUsers.strToday} <span class="small2"><spring:message code="dash.card.indicator.users"/></span></p>
	                 <p class="sub_txt2"><strong><span class="prevTime"></span>:00 ~ <span class="nowTime"></span>:00 : </strong>${app.dashBoard.todayNewUsers.strCurrent} <spring:message code="dash.card.indicator.users"/></p>
	            </div>
	        </div>
	    </div>
	   	<div class="item hand" id="today_activeUsers">
	       	<div class="inner">
	            <h2 class="pannel-name help" data-content="<spring:message code="dash.card.today.activeUser.alt.subscript"/>" data-original-title="<spring:message code="dash.card.activeUser.alt.title"/>" rel="popover"><spring:message code="dash.card.activeUser.title"/></h2>
	           	<div id="inner-body" class="normal">
	              	 <p class="sub_txt"><spring:message code="today.card.totalToday"/></p>
	              	 <p class="big-number">${app.dashBoard.todayActiveUsers.strToday} <span class="small2"><spring:message code="dash.card.indicator.users"/></span></p>
	                 <p class="sub_txt2"><strong><span class="prevTime"></span>:00 ~ <span class="nowTime"></span>:00 : </strong>${app.dashBoard.todayActiveUsers.strCurrent} <spring:message code="dash.card.indicator.users"/></p>
	             </div>
	         </div>
	    </div>
	   	<div class="item hand" id="today_sessions">
	       	<div class="inner">
	    	    <h2 class="pannel-name help" data-content="<spring:message code="dash.card.today.sessions.alt.subscript"/>" data-original-title="<spring:message code="dash.card.sessions.alt.title"/>" rel="popover"><spring:message code="dash.card.sessions.title"/></h2>
	           	<div id="inner-body" class="normal">
               	  <p class="sub_txt"><spring:message code="today.card.totalToday"/></p>
               	  <p class="big-number">${app.dashBoard.todaySessions.strToday}  <span class="small2"><spring:message code="dash.card.indicator.timesrun"/></span></p>
                  <p class="sub_txt2"><strong><span class="prevTime"></span>:00 ~ <span class="nowTime"></span>:00 : </strong>${app.dashBoard.todaySessions.strCurrent} <spring:message code="dash.card.indicator.timesrun"/></p>
                </div>
            </div>
	    </div>
	   	<div class="item hand" id="today_sessionLength">
	       	<div class="inner">
	            <h2 class="pannel-name help" data-content="<spring:message code="dash.card.today.sessionLength.alt.subscript"/>" data-original-title="<spring:message code="dash.card.sessionlen.alt.title"/>" rel="popover"><spring:message code="dash.card.sessionlen.title"/></h2>
	           	<div id="inner-body" class="normal">
               	  <p class="sub_txt"><spring:message code="today.chart.medianToday"/></p>
               	  <p class="big-number">${app.dashBoard.todaySessionLength.strToday} <span class="small2"><spring:message code="dash.card.indicator.sec"/></span></p>
                  <p class="sub_txt2"><strong><span class="prevTime"></span>:00 ~ <span class="nowTime"></span>:00 : </strong>${app.dashBoard.todaySessionLength.strCurrent} <spring:message code="dash.card.indicator.sec"/></p>
               </div>
           </div>

       </div>
	   	<div class="item hand" id="today_pageViews">
	       	<div class="inner">
	            <h2 class="pannel-name help" data-content="<spring:message code="dash.card.today.pageview.alt.subscript"/>" data-original-title="<spring:message code="dash.card.pageView.alt.title"/>" rel="popover"><spring:message code="dash.card.pageView.title"/></h2>
	           	<div id="inner-body" class="normal">
               	  <p class="sub_txt"><spring:message code="today.card.totalToday"/></p>
               	  <p class="big-number">${app.dashBoard.todayPageViews.strToday} <span class="small2"><spring:message code="dash.card.indicator.views"/></span></p>
                  <p class="sub_txt2"><strong><span class="prevTime"></span>:00 ~ <span class="nowTime"></span>:00 : </strong>${app.dashBoard.todayPageViews.strCurrent} <spring:message code="dash.card.indicator.views"/></p>
                </div>
           </div>

       </div>
	   </div>
	   <hr class="line" />
	</div>
	<!-- today snapshot end -->
	
	<!-- performance snapshot start -->
	<div class="snapshot">
       <div class="rtag"><spring:message code="dash.card.thisweeek"/><i class="icon-info-sign this_week_Info" title="${app.dashBoard.thisWeek}"></i></div>
	   <h1 class="title"><spring:message code="dash.title.performance"/></h1>
	   <div class="pannel" id="performance">

	   	<div class="item hand" id="newUsers">
	       	<div class="inner">
	        	<h2 class="pannel-name help" data-content="<spring:message code="dash.card.newUser.alt.subscript"/>" data-original-title="<spring:message code="dash.card.newUser.alt.title"/>" rel="popover"><spring:message code="dash.card.newUser.title"/> </h2>
	           	<div id="inner-body" class="${app.dashBoard.newUsers.bgClass}">
	              	 <p class="sub_txt"><spring:message code="dash.card.thenlastweeek"/></p>
					 <c:choose>
	              	 	<c:when test="${app.dashBoard.compare eq true}">
	              	 		<p class="big-number">${app.dashBoard.newUsers.sign}<fmt:formatNumber pattern="###.#" value="${app.dashBoard.newUsers.growthRate}"/><span class="small">%</span></p>
	              	 	</c:when>
	              	 	<c:otherwise>
	              	 		<p class="medium-number">N/A</p>
	              	 	</c:otherwise>
	              	 </c:choose>
	                 <p class="sub_txt2"><strong><spring:message code="dash.card.avgthiseeek"/></strong><fh:formatNumber value="${app.dashBoard.newUsers.current}"/> <spring:message code="dash.card.indicator.users"/></p>
	            </div>
	        </div>
	    </div>
	   	<div class="item hand" id="activeUsers">
	       	<div class="inner">
	            <h2 class="pannel-name help" data-content="<spring:message code="dash.card.activeUser.alt.subscript"/>" data-original-title="<spring:message code="dash.card.activeUser.alt.title"/>" rel="popover"><spring:message code="dash.card.activeUser.title"/></h2>
	           	<div id="inner-body" class="${app.dashBoard.activeUsers.bgClass}">
	              	 <p class="sub_txt"><spring:message code="dash.card.thenlastweeek"/></p>
					 <c:choose>
	              	 	<c:when test="${app.dashBoard.compare eq true}">
	              	 		<p class="big-number">${app.dashBoard.activeUsers.sign}<fmt:formatNumber pattern="###.#" value="${app.dashBoard.activeUsers.growthRate}"/><span class="small">%</span></p>
	              	 	</c:when>
	              	 	<c:otherwise>
	              	 		<p class="medium-number">N/A</p>
	              	 	</c:otherwise>
	              	 </c:choose>
	                 <p class="sub_txt2"><strong><spring:message code="dash.card.avgthiseeek"/></strong><fh:formatNumber value="${app.dashBoard.activeUsers.current}"/> <spring:message code="dash.card.indicator.users"/></p>
	             </div>
	         </div>
	    </div>
	   	<div class="item hand" id="sessions">
	       	<div class="inner">
	    	    <h2 class="pannel-name help" data-content="<spring:message code="dash.card.sessions.alt.subscript"/>" data-original-title="<spring:message code="dash.card.sessions.alt.title"/>" rel="popover"><spring:message code="dash.card.sessions.title"/></h2>
	           	<div id="inner-body" class="${app.dashBoard.sessions.bgClass}">
               	  <p class="sub_txt"><spring:message code="dash.card.thenlastweeek"/></p>
					 <c:choose>
	              	 	<c:when test="${app.dashBoard.compare eq true}">
	              	 		<p class="big-number">${app.dashBoard.sessions.sign}<fmt:formatNumber pattern="###.#" value="${app.dashBoard.sessions.growthRate}"/><span class="small">%</span></p>
	              	 	</c:when>
	              	 	<c:otherwise>
	              	 		<p class="medium-number">N/A</p>
	              	 	</c:otherwise>
	              	 </c:choose>
                  <p class="sub_txt2"><strong><spring:message code="dash.card.avgthiseeek"/></strong><fh:formatNumber value="${app.dashBoard.sessions.current}"/> <spring:message code="dash.card.indicator.timesrun"/></p>
                </div>
            </div>
	    </div>
	   	<div class="item hand" id="sessionLength">
	       	<div class="inner">
	            <h2 class="pannel-name help" data-content="<spring:message code="dash.card.sessionlen.alt.subscript"/>" data-original-title="<spring:message code="dash.card.sessionlen.alt.title"/>" rel="popover"><spring:message code="dash.card.sessionlen.title"/></h2>
	           	<div id="inner-body" class="${app.dashBoard.sessionLength.bgClass}">
               	  <p class="sub_txt"><spring:message code="dash.card.thenlastweeek"/></p>
					 <c:choose>
	              	 	<c:when test="${app.dashBoard.compare eq true}">
	              	 		<p class="big-number">${app.dashBoard.sessionLength.sign}<fmt:formatNumber pattern="###.#" value="${app.dashBoard.sessionLength.growthRate}"/><span class="small">%</span></p>
	              	 	</c:when>
	              	 	<c:otherwise>
	              	 		<p class="medium-number">N/A</p>
	              	 	</c:otherwise>
	              	 </c:choose>
                  <p class="sub_txt2"><strong><spring:message code="dash.card.medianthisweeek"/></strong><fh:formatNumber value="${app.dashBoard.sessionLength.current}"/> <spring:message code="dash.card.indicator.sec"/></p>
               </div>
           </div>

       </div>
	   	<div class="item hand" id="pageViews">
	       	<div class="inner">
	            <h2 class="pannel-name help" data-content="<spring:message code="dash.card.pageView.alt.subscript"/>" data-original-title="<spring:message code="dash.card.pageView.alt.title"/>" rel="popover"><spring:message code="dash.card.pageView.title"/></h2>
	           	<div id="inner-body" class="${app.dashBoard.pageViews.bgClass}">
               	  <p class="sub_txt"><spring:message code="dash.card.thenlastweeek"/></p>
					 <c:choose>
	              	 	<c:when test="${app.dashBoard.compare eq true}">
	              	 		<p class="big-number">${app.dashBoard.pageViews.sign}<fmt:formatNumber pattern="###.#" value="${app.dashBoard.pageViews.growthRate}"/><span class="small">%</span></p>
	              	 	</c:when>
	              	 	<c:otherwise>
	              	 		<p class="medium-number">N/A</p>
	              	 	</c:otherwise>
	              	 </c:choose>
                  <p class="sub_txt2"><strong><spring:message code="dash.card.avgthiseeek"/></strong><fh:formatNumber value="${app.dashBoard.pageViews.current}"/> <spring:message code="dash.card.indicator.views"/></p>
                </div>
           </div>

       </div>
	   </div>
	   <hr class="line" />
	</div>
	<!-- performance snapshot end -->
	
	<!-- distribution snapshot start -->
	<div class="snapshot">
       <div class="rtag"><spring:message code="dash.card.thisweeek"/><i class="icon-info-sign this_week_Info" title="${app.dashBoard.thisWeek}"></i></div>
		<h1 class="title"><spring:message code="dash.title.distribution"/></h1>
		   <div class="pannel" id="distribution">
		   	<div class="item hand" id="dayOfWeek">
		       	<div class="inner">
		            <h2 class="pannel-name help" data-content="<spring:message code="dash.card.dayOfWeek.alt.subscript"/>" data-original-title="<spring:message code="dash.card.dayOfWeek.alt.title"/>" rel="popover"><spring:message code="dash.card.dayOfWeek.title"/></h2>
		           	<div id="inner-body2" class="disable">
		              	  <p class="icon"><img src="<c:url value="/resources/img/icon_calendar.png"/>" width="66" height="70" alt="" /></p>
		                 <P class="sub_txt"><spring:message code="dash.card.dayOfWeek.sub.text"/></P>
		                 <p class="big-number">${app.dashBoard.dayOfWeek}</p>
	               </div>
	           </div>
	       </div>
		   	<div class="item hand" id="timeOfDay">
		       	<div class="inner">
		               <h2 class="pannel-name help" data-content="<spring:message code="dash.card.timeOfDay.alt.subscript"/>" data-original-title="<spring:message code="dash.card.timeOfDay.alt.title"/>" rel="popover"><spring:message code="dash.card.timeOfDay.title"/></h2>
		           	<div id="inner-body2">
		              	  <p class="icon"><img src="<c:url value="/resources/img/icon_time.png"/>" width="80" height="70" alt="" /></p>
		                   <p class="sub_txt"><spring:message code="dash.card.timeOfDay.sub.text"/></p>
		                   <p class="big-number">${app.dashBoard.timeOfDay}</p>
		             </div>
		         </div>
		       </div>
		   	<div class="item hand" id="topCountries">
		       	<div class="inner">
		               <h2 class="pannel-name help" data-content="<spring:message code="dash.card.topCountries.alt.subscript"/>" data-original-title="<spring:message code="dash.card.topCountries.alt.title"/>" rel="popover"><spring:message code="dash.card.topCountries.title"/></h2>
		           	<div id="inner-body2" class="disable">
		               <table>
		               <c:if test="${empty app.dashBoard.topCountries}">
		               	<tr class="gray"><th>N/A</th><td>0%</td></tr>
		               	<tr><th>N/A</th><td>0%</td></tr>
		               	<tr class="gray"><th>N/A</th><td>0%</td></tr>
		               </c:if>
		               <c:if test="${not empty app.dashBoard.topCountries}">
		                <c:forEach items="${app.dashBoard.topCountries}" var="list" varStatus="i">
	                       <tr <c:if test="${i.count mod 2 eq 1}">class="gray"</c:if>>
	                         <th>${list.countryName}</th>
	                         <td><fmt:formatNumber pattern="##0.0" value="${list.percentage}"/>%</td>
	                       </tr>
	                      </c:forEach>
	                  </c:if>
		               </table>
		             </div>
		         </div>
		       </div>
		   	<div class="item hand" id="topResolution">
		       	<div class="inner">
		               <h2 class="pannel-name help" data-content="<spring:message code="dash.card.topResolution.alt.subscript"/>" data-original-title="<spring:message code="dash.card.topResolution.alt.title"/>" rel="popover"><spring:message code="dash.card.topResolution.title"/></h2>
		           	<div id="inner-body2" class="disable">
		              	  <p class="icon"><img src="<c:url value="/resources/img/icon_resolution.png"/>" width="42" height="70" alt="" /></p>
		                 <p class="big-number2">
		                 <c:if test="${not empty app.dashBoard.topResolution.current }">${app.dashBoard.topResolution.current}</c:if>
		                 <c:if test="${empty app.dashBoard.topResolution.current}">N/A</c:if>
		                 </p>
		             </div>
		         </div>
		       </div>
		   	<div class="item hand" id="topVersions">
		       	<div class="inner">
		               <h2 class="pannel-name help" data-content="<spring:message code="dash.card.topVersions.alt.subscript"/>" data-original-title="<spring:message code="dash.card.topVersions.alt.title"/>" rel="popover"><spring:message code="dash.card.topVersions.title"/></h2>
		           	<div id="inner-body2" class="disable">
		                 <p class="icon"><img src="<c:url value="/resources/img/icon_os.png"/>" width="70" height="70" alt="" /></p>
		                 <p class="big-number3">APP <c:if test="${not empty app.dashBoard.topAppVersion.current}">${app.dashBoard.topAppVersion.current}</c:if><c:if test="${empty app.dashBoard.topAppVersion.current}">N/A</c:if>
		                 	<br />
		                 	OS <c:if test="${not empty app.dashBoard.topOsVersion.current}">${app.dashBoard.topOsVersion.current}</c:if><c:if test="${empty app.dashBoard.topOsVersion.current}">N/A</c:if>
		                 </p>
		             </div>
		         </div>

		       </div>
		   </div>
	   		<hr class="line" />
	   </div>
	<!-- distribution snapshot end -->
	   
    <!-- components snapshot start -->
    <div class="snapshot">
       <div class="rtag"><spring:message code="dash.card.thisweeek"/><i class="icon-info-sign this_week_Info" title="${app.dashBoard.thisWeek}"></i></div>
       <div class="componentGroup">
		   <select class="span3" id="componentGrpList" name="componentGrpList">
	             <option value="-2"><spring:message code="comp.list.topComponents"/></option>
	             <option value="-1"><spring:message code="comp.list.interGroup"/></option>
	             <c:forEach var="list" items="${app.dashBoard.componentGrpList}">
	             	<option value="${list.groupkey}">${list.shortname}</option>
	             </c:forEach>
	        </select>
	   </div>
	   <h1 class="title">
		   <spring:message code="dash.title.components"/>
		   <sec:authorize ifAnyGranted="ROLE_ADMIN"><a href="<c:url value="/components/manage/"/>${app.appkey}" class="btn btn-small" style="margin: 0 5px;"><i class="icon-cog icon-white"></i><spring:message code="dash.component.alt.text"/></a></sec:authorize>
        </h1>

	   <div class="pannel pannel_position" id="components">
	   <c:choose>
			<c:when test="${app.hasComponents eq false}">
				<sec:authorize ifNotGranted="ROLE_ADMIN">
					<div class="<spring:message code="css.img.dash.comp.add"/>"></div>
				</sec:authorize>
				<sec:authorize ifAnyGranted="ROLE_ADMIN">
					<a href='<c:url value="/components/manage/${app.appkey}"/>'><div class="<spring:message code="css.img.dash.comp.add"/>"> </div></a>
				</sec:authorize>
			</c:when>
			<c:otherwise>
			<script type="text/javascript">
			$(function() {
				$('#components').removeClass('pannel_position');
			});
			</script>
			</c:otherwise>
		</c:choose>
	   	<div class="item hand" id="com_newUsers">
	       	<div class="inner">
	        	<h2 class="pannel-name help" data-content="<spring:message code="dash.card.com.newUser.alt.subscript"/>" data-original-title="<spring:message code="dash.card.newUser.alt.title"/>" rel="popover"><spring:message code="dash.card.newUser.title"/> </h2>
	           	 <div id="inner-body2" class="com_newUsersList com_inner_body">
                     <table>
                         <c:forEach begin="0" end="2" step="1" varStatus="i">
                   			<tr <c:if test="${i.index ne 1}">class="gray"</c:if>><th></th><td></td></tr>
		                </c:forEach>
                     </table>
                 </div>
	        </div>
	    </div>
	   	<div class="item hand" id="com_activeUsers">
	       	<div class="inner">
	            <h2 class="pannel-name help" data-content="<spring:message code="dash.card.com.activeUser.alt.subscript"/>" data-original-title="<spring:message code="dash.card.activeUser.alt.title"/>" rel="popover"><spring:message code="dash.card.activeUser.title"/></h2>
	           	<div id="inner-body2" class="com_activeUsersList com_inner_body">
                     <table>
                         <c:forEach begin="0" end="2" step="1" varStatus="i">
                   			<tr <c:if test="${i.index ne 1}">class="gray"</c:if>><th></th><td></td></tr>
		                 </c:forEach>
                     </table>
                 </div>
	         </div>
	    </div>
	   	<div class="item hand" id="com_pageViews">
	       	<div class="inner">
	    	    <h2 class="pannel-name help" data-content="<spring:message code="dash.card.com.pageView.alt.subscript"/>" data-original-title="<spring:message code="dash.card.pageView.alt.title"/>" rel="popover"><spring:message code="dash.card.pageView.title"/></h2>
	           	<div id="inner-body2" class="com_pageViewsList com_inner_body">
                     <table>
                         <c:forEach begin="0" end="2" step="1" varStatus="i">
                   			<tr <c:if test="${i.index ne 1}">class="gray"</c:if>><th></th><td></td></tr>
		                 </c:forEach>
                     </table>
                 </div>
            </div>
	    </div>
	   	<div class="item hand" id="com_timeOfDay">
	       	<div class="inner">
	            <h2 class="pannel-name help" data-content="<spring:message code="dash.card.com.timeOfDay.alt.subscript"/>" data-original-title="<spring:message code="dash.card.timeOfDay.alt.title"/>" rel="popover"><spring:message code="dash.card.timeOfDay.title"/></h2>
	           	<div id="inner-body2" class="com_timeOfDayList com_inner_body">
                     <table>
                         <c:forEach begin="0" end="2" step="1" varStatus="i">
                   			<tr <c:if test="${i.index ne 1}">class="gray"</c:if>><th></th><td></td></tr>
		                 </c:forEach>
                     </table>
                 </div>
           </div>

       </div>
	   	<div class="item hand" id="com_topCountries">
	       	<div class="inner">
	            <h2 class="pannel-name help" data-content="<spring:message code="dash.card.com.topCountries.alt.subscript"/>" data-original-title="<spring:message code="dash.card.topCountries.alt.title"/>" rel="popover"><spring:message code="dash.card.topCountries.title"/></h2>
	           	<div id="inner-body2" class="com_topCountriesList com_inner_body">
                     <table>
                        <c:forEach begin="0" end="2" step="1" varStatus="i">
                   			<tr <c:if test="${i.index ne 1}">class="gray"</c:if>><th></th><td></td></tr>
		                </c:forEach>
                     </table>
                 </div>
           </div>

       </div>
	   </div>
	</div>
	<!-- components snapshot end -->
</div>
</body>
</html>