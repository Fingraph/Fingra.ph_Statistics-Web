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
<title><spring:message code="login.title"/></title>
<script type="text/javascript">
$(function(){
	$('input[name=email]').focus();
	$('button[type=submit]').click(function(event){
	    event.preventDefault();
		$('form').submit();
	});
	$('input').keydown(function(event){
		if(event.which == 13){
			event.preventDefault();
			$('form').submit();
		}
	});
	$('.findPassword').click(function(){
        var heading='비밀번호 찾기';
        var question='Fingra.ph Opensource 버전에서는 비밀번호 찾기를 지원하지 않습니다. 관리자에게 문의하세요.';
        var cancelButtonTxt = '';
        var okButtonTxt = '<spring:message code="btn.ok.text"/>';
        var callback = function() {};
        confirm(heading, question, cancelButtonTxt, okButtonTxt, callback);
    });
});
</script>
<base target="_top" />
</head>
<body>
    	<div class="signin-form">
        	<h1 class="title"><spring:message code="login.title"/></h1>
            <c:if test="${param.error != null}">
                <div class="alert alert-error">
                <c:choose>
                    <c:when test="${param.error eq '100'}">아이디나 비밀번호가 일치하지 않습니다.<br/>문제가 반복되면 관리자에게 문의하시길 바랍니다.</c:when>
                    <c:when test="${param.error eq '200'}">계정 등록 요청이 아직 승인되지 않았습니다.<br/>승인 요청은 관리자에게 문의하시길 바랍니다.</c:when>
                    <c:when test="${param.error eq ''}"><spring:message code="login.msg.error"/></c:when>
                </c:choose>
                </div>
            </c:if>
            <div class="idpw-wrap"><form action="<c:url value="/login"/>" method="post">
                <div class="idpw"><i class="icon-sign-user"></i><input name="email" type="text" class="span4" placeholder="<spring:message code="input.email"/>" ></div>
                <div class="idpw"><i class="icon-sign-pass"></i><input name="password" type="password" class="span4" placeholder="<spring:message code="input.pwd"/>"  min="8"/></div>
                <div class="idpw"><button type="submit" class="btn btn-large btn-block btn-info"><spring:message code="btn.enter.text"/></button></div>
                <P class="signin-txt findPassword"><spring:message code="login.text.pwd"/>&nbsp; <a href="#"><spring:message code="login.text.pwd.find"/>.</a></P>
                <P class="signin-txt"><spring:message code="login.text.signup"/>&nbsp; <a href="<c:url value="/signup/form"/>"><spring:message code="login.text.signup.now"/></a></P></form>
            </div>
        </div>
</body>
</html>