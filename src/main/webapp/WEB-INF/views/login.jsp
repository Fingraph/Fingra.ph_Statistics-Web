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
<!DOCTYPE html>
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
});
</script>
<base target="_top" />
</head>
<body>
    	<div class="signin-form">
        	<h1 class="title"><spring:message code="login.title"/></h1>
            <c:if test="${param.error != null}">
                <div class="alert alert-error">
                    <spring:message code="login.msg.error"/>
                </div>
            </c:if>
            <div class="idpw-wrap"><form action="<c:url value="/login"/>" method="post">
                <div class="idpw"><i class="icon-sign-user"></i><input name="email" type="text" class="span4" placeholder="<spring:message code="input.email"/>" ></div>
                <div class="idpw"><i class="icon-sign-pass"></i><input name="password" type="password" class="span4" placeholder="<spring:message code="input.pwd"/>"  min="8"/></div>
                <div class="idpw"><button type="submit" class="btn btn-large btn-block btn-info"><spring:message code="btn.enter.text"/></button></div></form>
            </div>
        </div>
</body>
</html>