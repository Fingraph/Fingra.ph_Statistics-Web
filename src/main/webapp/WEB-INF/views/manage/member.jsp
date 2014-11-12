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
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<title>Fingra.ph Opensource - <spring:message code="comm.header.manageaccount"/></title>
<script type="text/javascript">
$(function() {
    $('.dropdown-toggle').dropdown();
    $('#memberlistDiv').on('click','.editBtn',function(e){
    	e.preventDefault();
    	var id = $(this).attr('id').replace('edit_','');
    	location.href='<c:url value="/manage/member/form?memberid='+id+'"/>';
    });
    $('#memberlistDiv').on('click','.approvalBtn',function(e){
        e.preventDefault();
        var id = $(this).attr('id').replace('approval_','');
        location.href='<c:url value="/manage/member/approval?memberid='+id+'"/>';
    });
    $('#memberlistDiv').on('click','.refuseBtn',function(e){
        e.preventDefault();
        var id = $(this).attr('id').replace('refuse_','');
        location.href='<c:url value="/manage/member/refuse?memberid='+id+'"/>';
    });
});
</script>
</head>
<body>
    <div id="applist-body">
        <div class="app-list-wrap">
            <div class="list-title">
                <h1><spring:message code="comm.header.manageaccount"/></h1>
            </div>
            <div class="new-app-wrap">
                <div class="new-app-info" id="memberlistDiv">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th><spring:message code="input.name"/></th> <!-- Username -->
                                <th><spring:message code="input.email"/></th> <!-- Accountname -->
                                <th><spring:message code="input.department"/></th> <!-- Department -->
                                <th><spring:message code="signup.nxt.text.phoneNumber"/></th> <!-- Contact -->
                                <th><spring:message code="input.created"/></th> <!-- Registered -->
                                <th><spring:message code="input.status"/></th> <!-- Status -->
                                <th><spring:message code="input.actions"/></th> <!-- Actions -->
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="list" items="${list}" varStatus="status">
                            <tr>
                                <td valign="middle">${list.name}</td>
                                <td valign="middle"><a href="<c:url value="/manage/member/detail?memberid=${list.memberid}"/>">${list.email}</a></td>
                                <td valign="middle">${list.department}</td>
                                <td valign="middle">${list.phone}</td>
                                <td valign="middle"><fmt:formatDate value="${list.created}" pattern="yyyy-MM-dd"/></td>
                                <td valign="middle"><c:choose>
                                <c:when test="${list.status eq 1 }"><spring:message code="member.status.active"/></c:when>
                                <c:when test="${list.status eq 9 }"><spring:message code="member.status.delete"/></c:when>
                                </c:choose></td>
                                <td>
                                    <span class="btn btn-small btn-primary editBtn" id="edit_${list.memberid}"><spring:message code="btn.edit.text"/></span>
                                    <c:if test="${list.joinstatus eq 0 }">
                                        <span class="btn btn-small btn-warning approvalBtn" id="approval_${list.memberid}"><spring:message code="member.joinstatus.approval"/></span>
                                        <span class="btn btn-small btn-danger refuseBtn" id="refuse_${list.memberid}"><spring:message code="member.joinstatus.refuse"/></span>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
   </div>
</body>
</html>
