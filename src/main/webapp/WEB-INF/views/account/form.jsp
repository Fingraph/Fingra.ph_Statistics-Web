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
            }
        },
        messages: {
            name: {
                required: '<spring:message code="msg.check.userNm"/>',
                minlength: jQuery.format('<spring:message code="msg.check.leastChar"/>')
            },
            department: {
                required: '<spring:message code="msg.check.department"/>',
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
            	required: '<spring:message code="msg.check.phone"/>',
                number: '<spring:message code="msg.check.numOnly"/>'
            }
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
    $('#saveBtn').click(function(){
        var heading='<spring:message code="msg.confirm.member.modify.header"/>';
        var question='<spring:message code="msg.confirm.member.modify"/>';
        var cancelButtonTxt = '<spring:message code="btn.cancel.text"/>';
        var okButtonTxt = '<spring:message code="btn.ok.text"/>';
        var callback = function() {
        	$('form').submit();
        };
        confirm(heading, question, cancelButtonTxt, okButtonTxt, callback);
    });
});
</script>
</head>
<body>
    <h1 class="setting-title"><spring:message code="setting.title"/></h1>
    <div class="setting-cont-body">
        <div class="account-cont">
            <h2 class="title"><spring:message code="account.subTitle"/></h2>
            <form id="accountform" name="member" class="form-horizontal" action="<c:url value='/account/edit'/>" method="post">
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
                            <th><spring:message code="input.department"/></th>
                            <td><input type="text" class="span3" id="department" name="department" value="${member.department}"/></td>
                        </tr>
                        <tr>
                            <th><spring:message code="signup.nxt.text.phoneNumber"/></th>
                            <td><input type="text" class="span3" maxlength="20" id="phone" name="phone" value="${member.phone}"/></td>
                        </tr>
                    </table>
                </div>
                <div style="padding: 30px 0; text-align: right; clear: both;">
                    <button type="button" id="saveBtn" name="saveBtn" class="btn btn-large btn-primary bottom-button"><spring:message code="btn.saveSetting.text"/></button>
                    &nbsp;<a href="<c:url value="/app/list"/>" class="btn btn-large bottom-button span2"><spring:message code="btn.applist.text"/></a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>