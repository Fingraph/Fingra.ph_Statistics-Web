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
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<title>Fingra.ph Opensource - <spring:message code="setting.title"/></title>
<style type="text/css">
.help-inline{
    margin-left:10px;
    color:red;
    display: inline;
}
</style>
<script type="text/javascript" src="<c:url value="/resources/js/validate/jquery.validate.js"/>"></script>
<script type="text/javascript">
$(function(){
    $.validator.addMethod("password", function(value, element) {
        return this.optional(element) || /^(?=.*[0-9])(?=.*[a-zA-Z])(?=\S+$).{8,32}$/g.test(value);
    }, '<spring:message code="msg.check.invalidPwd"/>');
    $.validator.addMethod("valueNotEquals", function(value, element, arg){
        return arg != value;
    }, '<spring:message code="msg.check.valueNotEquals"/>');
    $.validator.addMethod("notEqualTo",function( value, element, param ) {
        var target = $(param);
        return value != target.val();
    },'<spring:message code="msg.check.notEqualPwd"/>');
    
    var validator = $("#accountform").validate({
        rules: {
            name: {
                required: true,
                minlength: 2
            },
            department: {
                required: true,
                minlength: 2
            },
            password: {
                minlength: 8,
                password: true
            },
            confirmPassword: {
                minlength: 8,
                equalTo: "#password"
            },
            phone:{
                required: true,
                number:true
            },
            status: {valueNotEquals:""},
            joinstatus: {valueNotEquals:""}
        },
        messages: {
            name: {
                required: '<spring:message code="msg.check.userNm"/>',
                minlength: jQuery.format('<spring:message code="msg.check.leastChar"/>')
            },
            department: {
                required: '부서명을 입력하여 주십시오',
                minlength: jQuery.format('<spring:message code="msg.check.leastChar"/>')
            },
            password: {
                rangelength: jQuery.format('<spring:message code="msg.check.leastChar"/>')
            },
            confirmPassword: {
                minlength: jQuery.format('<spring:message code="msg.check.leastChar"/>'),
                equalTo: '<spring:message code="msg.check.equalPwd"/>'
            },
            phone:{
                required: '전화번호를 입력하여 주십시오',
                number:'숫자만 입력하세요.'
            },
            status: { valueNotEquals: 'Status를 선택하세요' },
            joinstatus: { valueNotEquals: 'Join-Status를 선택하세요' }
        },
        errorClass: "help-inline",
        errorElement: "div",
        errorPlacement: function(error, element) {
            $(element).after(error);
        },
        success: function(element) {
            element.removeClass('error').addClass('success');
        },
        highlight: function(element) {
            $(element).removeClass('success').addClass('error');
        }
    });
});
</script>
</head>
<body>
    <h1 class="setting-title"><spring:message code="setting.title"/></h1>
    <div class="setting-cont-body">
        <div class="account-cont">
            <h2 class="title"><spring:message code="account.subTitle"/></h2>
            <form id="accountform" name="member" class="form-horizontal" action="<c:url value='/manage/member/edit'/>" method="post">
                <input type="hidden" id="memberid" name="memberid" value="${member.memberid}" />
                <div class="tbl-wrap">
                    <table class="tbl1" width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <th><spring:message code="input.email"/></th>
                            <td>${member.email}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="input.newPwd"/></th>
                            <td><input type="password" class="span3"  id="password" name="password"/><br /><br/><spring:message code="new.password.info"/></td>
                        </tr>
                        <tr>
                            <th><spring:message code="input.confNewPwd"/></th>
                            <td><input type="password" class="span3" id="confirmPassword" name="confirmPassword" /></td>
                        </tr>
                        <tr>
                            <th><spring:message code="input.name"/></th>
                            <td><input type="text" class="span3" id="name" name="name" value="${member.name}"/></td>
                        </tr>
                        <tr>
                            <th>Department</th>
                            <td><input type="text" class="span3" id="department" name="department" value="${member.department}"/></td>
                        </tr>
                        <tr>
                            <th><spring:message code="signup.nxt.text.phoneNumber"/></th>
                            <td><input type="text" class="span3" maxlength="20" id="phone" name="phone" value="${member.phone}"/></td>
                        </tr>
                        <tr>
                            <th>Status</th>
                            <td><select name="status" id="status" class="selectpicker">
                                <option value=""></option>
                                <option value="1"<c:if test="${member.status eq 1}"> selected="selected"</c:if>>Active</option>
                                <option value="9"<c:if test="${member.status eq 9}"> selected="selected"</c:if>>Inactive</option>
                            </select></td>
                        </tr>
                        <tr>
                            <th>Join-Status</th>
                            <td><select name="joinstatus" id="joinstatus" class="selectpicker">
                                <option value=""></option>
                                <option value="0"<c:if test="${member.joinstatus eq 0}"> selected="selected"</c:if>>Wait</option>
                                <option value="1"<c:if test="${member.joinstatus eq 1}"> selected="selected"</c:if>>Approval</option>
                                <option value="9"<c:if test="${member.joinstatus eq 9}"> selected="selected"</c:if>>Refuse</option>
                            </select></td>
                        </tr>
                    </table>
                </div>
                <div style="padding: 30px 0; text-align: right; clear: both;">
                    <button type="submit" class="btn btn-large btn-primary bottom-button"><spring:message code="btn.saveSetting.text"/></button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>