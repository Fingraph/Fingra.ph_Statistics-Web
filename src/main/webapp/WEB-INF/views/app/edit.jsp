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
<title>Fingra.ph - <spring:message code="app.modify.title"/></title>
<style type="text/css">
div.help-inline{
    margin-left:10px;
    color:red;
    display: inline;
}
</style>
<script type="text/javascript" src="<c:url value="/resources/js/validate/jquery.validate.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.alphanumeric.pack.js"/>"></script>
<script type="text/javascript">
$(function(){
    $.validator.addMethod("valueNotEquals", function(value, element, arg){
          return arg != value;
    }, "Value must not equal arg.");
    
    $("#appForm").validate({
        //debug:true,
        errorClass: "help-inline",
        errorElement: "span",
        rules: {
            appname: {
                required: true,
                minlength: 2
            },
            categoryid: {valueNotEquals:"default"}
        },
        messages: {
            appname: {
                required: '<spring:message code="msg.check.appNm"/>',
                minlength: jQuery.format('<spring:message code="msg.check.leastChar"/>')
            },
            categoryid:  {valueNotEquals: '<spring:message code="msg.check.appCate"/>' }
        },
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
    
    $('img.btn_platform').click(function(e){
        e.preventDefault();
        var val = $(this).attr("id")=='ios'?1:2;
        if($("#platform").val()== val) return;
        textChange(val);
        $("#platform").val(val);
    });
    
    textChange('${app.platform}');
    $("#appType").val(0);
});
function textChange(val){
    if(val == 1){
        $('#ios').attr('src','<c:url value="/resources/img/btn_ios_active.png"/>');
        $('#android').attr('src','<c:url value="/resources/img/btn_android_inactive.png"/>');
        $("#appid").attr("placeholder",'App Store application ID(9-digit numbber)');
        $(".appId").text('App Store application ID(9-digit numbber)');
        $("#marketid").attr("placeholder",'iTunes Connect ID');
        $(".marketId").text('iTunes Connect ID');
    }else{
        $('#ios').attr('src','<c:url value="/resources/img/btn_ios_inactive.png"/>');
        $('#android').attr('src','<c:url value="/resources/img/btn_android_active.png"/>');
        $("#appid").attr("placeholder",'Google Play application package name');
        $(".appId").text('Google Play application package name');
        $("#marketid").attr("placeholder",'Google Play Dev.Console ID');
        $(".marketId").text('Google Play Dev.Console ID');
    }
}
</script>
</head>
<body>
    <div id="applist-body">
        <div class="app-list-wrap">
            <div class="list-title">
                <h1><spring:message code="app.modify.title"/></h1>
            </div>
            <div class="new-app-wrap">
                <form id="appForm" name="app" action="<c:url value='/app/update'/>" method="post">
                <input type="hidden" name="appkey" id="appkey" value="${app.appkey}"/>
                <input type="hidden" name="platform" id="platform" value="${app.platform}"/>
                <input type="hidden" name="apptype" id="apptype" value="${app.apptype}"/>
                <div>
                    <img src="<c:url value="/resources/img/btn_ios_inactive.png"/>" class="btn_platform hand" id="ios" title="ios" alt="ios"/>
                    <img src="<c:url value="/resources/img/btn_android_inactive.png"/>" class="btn_platform hand" id="android" title="android" alt="android"/>
                </div>
                <h2><spring:message code="app.reg.basicInfo.title"/><small>(<spring:message code="app.form.necessary"/>)</small></h2>
                <div class="new-app-info">
                    <ul>
                        <li>
                            <p class="info-txt"><spring:message code="app.reg.basicInfo.name"/></p>
                            <input id="appname" name="appname" class="span8" type="text" value="${app.appname}" maxlength="35"/>
                        </li>
                        <li>
                            <p class="info-txt"><spring:message code="app.reg.basicInfo.cate"/></p>
                            <select name="categoryid" id="categoryid" class="span8">
                                <option value="default"></option>
                                <c:forEach var="category" items="${categories }">
                                <option value="${category.categoryid }"<c:if test="${app.categoryid eq category.categoryid}"> selected="selected"</c:if>>${category.categoryname }</option>
                                </c:forEach>
                            </select>
                        </li>
                    </ul>
                </div>
                <h2><spring:message code="app.reg.addInfo.title"/><small>(<spring:message code="app.form.optional"/>)</small></h2>
                <div class="new-app-info">
                    <ul>
                        <li>
                            <p class="info-txt"><spring:message code="app.reg.addInfo.appKey"/></p><p class="info-sub-txt appId"></p>
                            <input id="appIi" name="appid" class="span8" type="text" value="${app.appid}" maxlength="100"/>
                        </li>
                        <li>
                            <p class="info-txt"><spring:message code="app.reg.addInfo.marketAcc"/></p>
                            <p class="info-sub-txt marketId"></p>
                            <input id="marketid" name="marketid" class="span8" type="text" value="${app.marketid}" maxlength="100"/>
                        </li>
                    </ul>
                </div>
                <div class="new-app-btn">
                    <a href="<c:url value="/app/list"/>" class="btn btn-large bottom-button span2"><spring:message code="btn.applist.text"/></a>
                    <button type="submit" class="btn btn-large btn-primary bottom-button span2"><spring:message code="btn.submit.text"/></button>
                </div>
                </form>
            </div>
            <div class="gap"></div>
        </div>
    </div>
</body>
</html>
