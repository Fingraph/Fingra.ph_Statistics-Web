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
<title>Fingra.ph Opensource - <spring:message code="comm.header.memberdetail"/></title>
<script type="text/javascript">
$(function() {
    $('.dropdown-toggle').dropdown();
    $('.editBtn').click(function(){
        location.href='<c:url value="/manage/member/form?memberid=${member.memberid}"/>';
    });
    $('.deactivateBtn').click(function(){
        var heading='<spring:message code="msg.confirm.account.inactive.head"/>';
        var question='<spring:message code="msg.confirm.account.inactive.msg"/>';
        var cancelButtonTxt = '<spring:message code="btn.cancel.text"/>';
        var okButtonTxt = '<spring:message code="btn.ok.text"/>';
        var callback = function() {
        	location.href='<c:url value="/manage/member/inactivate?memberid=${member.memberid}"/>';
        };
        confirm(heading, question, cancelButtonTxt, okButtonTxt, callback);
    });
});
</script>
</head>
<body>
    <h1 class="setting-title"><spring:message code="comm.header.manageaccount"/></h1>
    <div class="setting-cont-body">
        <div class="account-cont">
            <h2 class="title"><spring:message code="account.subTitle"/></h2>
            <div class="tbl-wrap">
                <table class="tbl1" width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <th><spring:message code="input.email"/></th>
                        <td>${member.email}</td>
                    </tr>
                    <tr>
                        <th><spring:message code="input.name"/></th>
                        <td>${member.name}</td>
                    </tr>
                    <tr>
                        <th><spring:message code="input.department"/></th>
                        <td>${member.department}</td>
                    </tr>
                    <tr>
                        <th><spring:message code="signup.nxt.text.phoneNumber"/></th>
                        <td>${member.phone}</td>
                    </tr>
                    <tr>
                        <th><spring:message code="input.status"/></th>
                        <td><c:choose>
                        <c:when test="${member.status eq 1 }"><spring:message code="member.status.active"/></c:when>
                        <c:when test="${member.status eq 9 }"><spring:message code="member.status.delete"/></c:when>
                        </c:choose></td>
                    </tr>
                    <tr>
                        <th><spring:message code="input.joinstatus"/></th>
                        <td><c:choose>
                        <c:when test="${member.joinstatus eq 0 }"><spring:message code="member.joinstatus.wait"/></c:when>
                        <c:when test="${member.joinstatus eq 1 }"><spring:message code="member.joinstatus.approval"/></c:when>
                        <c:when test="${member.joinstatus eq 9 }"><spring:message code="member.joinstatus.refuse"/></c:when>
                        </c:choose></td>
                    </tr>
                    <tr>
                        <th><spring:message code="input.created"/></th>
                        <td><fmt:formatDate value="${member.created}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    </tr>
                </table>
            </div>
            <div style="padding: 30px 0; text-align: right; clear: both;">
                <button type="button" class="btn btn-large btn-warning bottom-button span2 editBtn"><spring:message code="btn.edit.text"/></button>
                <button type="button" class="btn btn-large btn-danger bottom-button span2 deactivateBtn"><spring:message code="btn.del.text"/></button>
                <a href="<c:url value="/manage/member"/>" class="btn btn-large bottom-button span2"><spring:message code="comm.header.manageaccount"/></a>
            </div>
        </div>
    </div>
</body>
</html>
