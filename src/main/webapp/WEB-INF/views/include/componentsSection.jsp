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
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="fh"%>
<div id="snapshot">
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
   <h1 class="title"><spring:message code="dash.title.components"/><sec:authorize ifAllGranted="ROLE_ADMIN"><a href="<c:url value="/components/manage/"/>${app.appkey}" class="btn btn-small" style="margin: 0 5px;"><i class="icon-cog icon-white"></i><spring:message code="dash.component.alt.text"/></a></sec:authorize></h1>
   <div class="pannel" id="components">
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
<script type="text/javascript">
$(function() {
	//submenu setting
	var subMenu = 'com_${param.subMenu}';

	$('#' + subMenu).find('h2').addClass('title-over');
	$('#' + subMenu).find('div.inner').append('<div class="over-pointer"><img src="<c:url value="/resources/img/arrow_pointer.png"/>" /></div>');

	//components chack
	if('${app.hasComponents}' == 'false'){
		$('.com_inner_body').attr('id','inner-body').addClass('disable').html('<p class="big-txt"><spring:message code="btn.noComponents.text"/></p>');
	}

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
		if('${app.hasComponents}' == 'true'){
			var groupkey=$("#componentGrpList").val();
			var url = '<c:url value="/"/>'+$(this).parent('.pannel').attr('id')+'/'+$(this).attr('id').replace('com_','')+'/'+'${app.appkey}'+ '?groupkey=' + groupkey;
			location.href=url;
		}
	});

	//componentGrpList
	var componentGrpList = $("#componentGrpList").msDropdown({
		roundedCorner:true,
		visibleRows:8,
		on:{change: function(data, ui) {
			//alert("select text:"+data.text + "/ value:"+data.value );
			getComponetsSnapshotAjaxData('${app.appkey}',data.value);
			getFingraphData();
			}
		}
		}).data("dd");
	componentGrpList.setIndexByValue('${app.groupkey}');
	getComponetsSnapshotAjaxData('${app.appkey}',$("#componentGrpList").val());
	getPeriodCookie('fingraphPeriod');//data호출
	
	var thisweekZone = "${app.dashBoard.thisWeek} <br/><spring:message code='dash.card.thisweeek.timezone'/>";
	$(".this_week_Info").attr('title', thisweekZone);
	$('.this_week_Info').tooltip({html:true});
});

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
</script>