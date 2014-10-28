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
<%
	response.setStatus(404);
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame Remove this if you use the .htaccess -->
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<title>Fingra.ph Opensource Project</title>
		<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>" />
		<link rel="stylesheet" href="<c:url value="/resources/css/ui.css"/>" />
		<link rel="stylesheet" href="<c:url value="/resources/css/common.css"/>" />
		<link href="<c:url value="/resources/css/setting.css"/>" rel="stylesheet" />
		<link href='http://fonts.googleapis.com/css?family=Ubuntu+Condensed' rel='stylesheet' type='text/css' />
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="<c:url value="/resources/js/bootstrap/bootstrap-dropdown.js"/>"></script>
		<!-- dropdown -->
		<script src="<c:url value="/resources/js/jquery.dd.js"/>"></script>
		<script type="text/javascript">var context = '<c:url value="/"/>';</script>
		<script src="<c:url value="/resources/js/bootstrap/bootstrap-modal.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/fingraph-settings.js"/>"></script>
	</head>
	<body>
		<div id="global-header-wrap">
			<div id="global-header">
				<div class="logo">
					<a href="<c:url value="/home"/>"><img src="<c:url value="/resources/img/logo.png"/>" width="168" height="38" alt="fingraph logo" /></a>
				</div>
			</div>
		</div>
		<div class="error-wrap">
			<div class="error-cont">
		    	<img src="<c:url value="/resources/img/sorry.png"/>" alt="SORRY" />
		        <P class="error-title">404 ERROR</P>
		        <p class="error-txt">As you know, It means we could not find the you’re looking for.<br />
		        Please check the URL you’ve typed. If you think it’s supposed to be found,<br />
		        please contact us. </p>
		        <div class="error-btn-wrap">
		        	<a href="<c:url value="/"/>" class="btn btn-large btn-primary span2">Go to Main</a>
		        </div>
		    </div>
		</div>
	</body>
</html>
