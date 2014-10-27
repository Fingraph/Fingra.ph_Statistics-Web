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
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:choose>
	<c:when test="${type eq 'addGroupResult' }">
	<div class="comp-group-wrap group {groupkey:'${group.groupkey}'}">
	     <div class="gr-header">
	         <div class="gr-title" id="groupname_${group.groupkey}">
	         	<h3><a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapse_${group.groupkey}"><img alt="" src="<c:url value="/resources/img/ico-gr-close.png"/>" class="toggleBtn"><span class="groupname" style="padding: 0 7px;">${group.groupname}</span></a> <span><button class="btn btn-small editGroup" style="margin: 0 5px;"><i class="icon-pencil icon-white"></i> <spring:message code="btn.rename.text"/></button>&nbsp;&nbsp;<button class="btn btn-small btn-primary moveToGroup" style="display: none"><i class=" icon-arrow-down icon-white"></i> <spring:message code="btn.movetohere.text"/></button></span></h3>
	         </div>
	         <div class="gr-btn">
	             <ul>
	                 <li><img src="<c:url value="/resources/img/btn-group-del.png"/>" alt="<spring:message code="btn.del.text"/>" title="<spring:message code="btn.del.text"/>" class="hand removeGroup"></li>
	             </ul>
	         </div>
	     </div>
	     <div id="collapse_${group.groupkey}" class="gr-body in collapse">
	     	<table class="gr-comp-tbl">
	         <colgroup>
	         <col width="49px">
	         <col>
	         <col width="300px">
	         </colgroup>
	             <tbody>
	             <tr>
	                 <th scope="col">
	                 <input type="checkbox" id="checkAll_${group.groupkey}" class="input_check checkAll" name="checkAll" value="" >
	                 </th>
	                 <th scope="col"><spring:message code="comp.add.comfName"/></th>
	                 <th scope="col"><spring:message code="comp.add.comfKey"/></th>
	             </tr>

	            </tbody>
	         </table>
	         <div class="gr-add-comp addEvent {groupkey:'${group.groupkey}'}"><a href="#">+<spring:message code="comp.add.newComponent"/></a></div>
	     </div>
	     <div class="gr-shadow"></div>
	 </div>
	</c:when>
	<c:when test="${type eq 'addComponentResult'}">
	<tr id="${event.componentkey}" class="trEvent">
	 <td>
	 	<!-- label class="checkbox" for="event_${event.componentkey}"-->
	 	 <input type="checkbox" id="event_${event.componentkey}" name="event" value="${event.groupkey}_${event.componentkey}"/>
	     <!-- /label-->
	 </td>
	 <td class="tdEvent"><span class="eventName" style="padding: 0 7px;">${event.componentname}</span><img class="editEvent hand" src="<c:url value="/resources/img/btn-comp-rename.png"/>"  alt="<spring:message code="btn.rename.text"/>" title="<spring:message code="btn.rename.text"/>"/></td>
	 <td>
	 	<input type="text" disabled="disabled" name="componentkey" class="key-input" style="width: 80px;" value="${event.componentkey}" />
	    	<span class="z-clip-wrap"><button class="btn btn-primary btn-small copyBtn"><i class="icon-file icon-white"></i> <spring:message code="btn.copy.text"/></button>&nbsp;&nbsp;&nbsp;<button class="btn btn-danger btn-small removeEvent"><i class="icon-trash icon-white"></i> <spring:message code="btn.del.text"/></button></span>
    </td>
	</tr>
	</c:when>
</c:choose>
