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
	                 <p class="big-number2"><c:if test="${not empty app.dashBoard.topResolution.current }">${app.dashBoard.topResolution.current}</c:if>
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
	                 <p class="big-number3">
						APP <c:if test="${not empty app.dashBoard.topAppVersion.current}">${app.dashBoard.topAppVersion.current}</c:if><c:if test="${empty app.dashBoard.topAppVersion.current}">N/A</c:if>
		                 	<br />
		                 	OS <c:if test="${not empty app.dashBoard.topOsVersion.current}">${app.dashBoard.topOsVersion.current}</c:if><c:if test="${empty app.dashBoard.topOsVersion.current}">N/A</c:if>
					</p>
	             </div>
	         </div>

	       </div>
	   </div>

   </div>
<script type="text/javascript">
$(function() {
	//submenu setting
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
	var thisweekZone = "${app.dashBoard.thisWeek} <br/><spring:message code='dash.card.thisweeek.timezone'/>";
	$(".this_week_Info").attr('title', thisweekZone);
	$('.this_week_Info').tooltip({html:true});


});
</script>