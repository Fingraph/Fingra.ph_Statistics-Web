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
<base target="_top" />
</head>
<body>
    <div class="signup-form">
        <h1 class="title"><spring:message code="signup.title"/></h1>
        <div class="signup-cont">
            <div class="signup-finish">
                <p class="finish-title2"><spring:message code="signup.result.text.welcome"/>,${member.name}</p>
                <p class="finish-sub">${member.email} <br/><br/>Fingra.ph Opensource 등록 요청이 완료되었습니다.<br />
                관리자의 수락 후 Fingra.ph Opensource를 사용하실 수 있습니다.<br/>
                </p>
                <p class="finish-sub"><a class="btn btn-large btn-primary" style="margin-top: 15px;width: 140px;" href="<c:url value="/"/>"><spring:message code="findpwd.result.link.text"/></a></p>
            </div>
        </div>
    </div>
</body>
</html>