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
	<h1 class="title"><spring:message code="dash.title.performance"/></h1>
	   <div class="pannel" id="performance">
		   	<div class="item hand" id="newUsers">
		       	<div class="inner">
		        	<h2 class="pannel-name help" data-content="<spring:message code="dash.card.newUser.alt.subscript"/>" data-original-title="<spring:message code="dash.card.newUser.alt.title"/>" rel="popover"><spring:message code="dash.card.newUser.title"/></h2>
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
</div>
<script type="text/javascript">
$(function() {
	//alert('${param.subMenu}');
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

