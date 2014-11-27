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
<html>
<head>
<title><spring:message code="signup.title"/></title>
<style type="text/css">
.nxt{ display: none;}
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
    
    // Phone number - removal
    $("#phone").focusout(function(){
        $("#phone").val($("#phone").val().replace(/-/g,''));
    });
    
    var validator = $("#signupform").validate({
        errorClass: "help-inline",
        errorElement: "span",
        rules: {
            agree:{
                required:true
            },
            name: {
                required: true,
                minlength: 2
            },
            department: {
                required: true,
                minlength: 2
            },
            password: {
                required: true,
                minlength: 8,
                password: true
            },
            confirmPassword: {
                required: true,
                minlength: 8,
                equalTo: "#password"
            },
            email: {
                required: true,
                email: true,
                remote: "<c:url value="/signup/duplicateEmailCheck"/>"
            },
            phone:{
            	required: true,
                number:true
            }
        },
        messages: {
            agree:{
                required: '<spring:message code="msg.check.agree"/>'
            },
            name: {
                required: '<spring:message code="msg.check.userNm"/>',
                minlength: jQuery.format('<spring:message code="msg.check.leastChar"/>')
            },
            department: {
                required: '<spring:message code="msg.check.department"/>',
                minlength: jQuery.format('<spring:message code="msg.check.leastChar"/>')
            },
            password: {
                required: '<spring:message code="msg.check.pwd"/>',
                rangelength: jQuery.format('<spring:message code="msg.check.leastChar"/>')
            },
            confirmPassword: {
                required: '<spring:message code="msg.check.confirmPwd"/>',
                minlength: jQuery.format('<spring:message code="msg.check.leastChar"/>'),
                equalTo: '<spring:message code="msg.check.equalPwd"/>'
            },
            email: {
                required: '<spring:message code="msg.check.email"/>',
                email:'<spring:message code="msg.check.email"/>',
                minlength: '<spring:message code="msg.check.email"/>',
                remote: jQuery.format('<spring:message code="msg.check.inuse"/>')
            },
            phone:{
            	required: '<spring:message code="msg.check.phone"/>',
                number: '<spring:message code="msg.check.numOnly"/>'
            }
        },
        // set this class to error-labels to indicate valid fields
        success: function(element) {
            element
            .addClass('valid')
            .closest('.control-group').removeClass('error').addClass('success');
        },
        highlight: function(element) {
            $(element).closest('.control-group').removeClass('success').addClass('error');
        }
    });
    $("#continueBtn").click(function(event){
        event.preventDefault();
        $(this).parent().hide();
        $(".nxt").show();
    });
    var url = (window.location != window.parent.location) ? window.parent.location.href: window.location.href;
});
</script>
</head>
<body>
    <div class="signup-form">
        <h1 class="title"><spring:message code="signup.title"/></h1>
        <form id="signupform" name="member" action="<c:url value='/signup'/>" method="post">
            <div class="signup-cont">
                <h2><spring:message code="signup.subTitle"/></h2>
                <div class="comp">
                    <h3><spring:message code="signup.text.email"/><span class="imp">*</span></h3>
                    <div><input type="email" id="email" name="email" placeholder="<spring:message code="input.email"/>" class="span5" /></div>
                    <p><spring:message code="signup.subscript.verify"/></p>
                </div>
                <div class="comp">
                    <h3><spring:message code="signup.text.pwd"/><span class="imp">*</span></h3>
                    <div><input id="password" name="password" type="password" placeholder="<spring:message code="input.pwd"/>" class="span5" /></div>
                    <div><input id="confirmPassword" name="confirmPassword" type="password" placeholder="<spring:message code="input.confPwd"/>" class="span5" /></div>
                    <p><spring:message code="signup.subscript.pwd"/></p>
                </div>
                <div class="comp">
                    <button id="continueBtn" type="button" class="btn btn-large btn-primary span2"><spring:message code="btn.continue.text"/></button>
                </div>
                <br class="nxt"/>
                <h2 class="nxt"><spring:message code="signup.nxt.title"/></h2>
                <div class="comp nxt">
                    <h3><spring:message code="signup.nxt.text.name"/><span class="imp">*</span></h3>
                    <div><input id="name" name="name" type="text" placeholder="<spring:message code="input.name"/>" class="span5" /></div>
                </div>
                <div class="comp nxt">
                    <h3><spring:message code="input.department"/><span class="imp">*</span></h3>
                    <div><input id="department" name="department" type="text" placeholder="<spring:message code="input.department"/>" class="span5" /></div>
                </div>
                 <div class="comp nxt">
                    <h3><spring:message code="signup.nxt.text.phoneNumber"/><span class="imp">*</span></h3>
                    <div><input id="phone" name="phone" maxlength="20" type="text" placeholder="<spring:message code="signup.nxt.text.phoneNumber"/>"  class="span5" /></div>
                </div>
                <div class="comp nxt">
                    <p><spring:message code="msg.confirm.to.signup"/><span class="imp">*</span> <input name="agree" id="agree" type="checkbox" value="true" ></p>
                </div>
                <div class="comp nxt">
                    <button type="submit" class="btn btn-large btn-primary span2"><spring:message code="btn.submit.text"/></button>
                    <button type="reset" class="btn btn-large btn-primary span2"><spring:message code="btn.reset.text"/></button>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
