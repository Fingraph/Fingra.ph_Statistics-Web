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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title><sitemesh:write property="title" /></title>
<link href="<c:url value="/resources/img/favicon.ico"/>" rel="shortcut icon" />
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>" />
<link rel="stylesheet" href="<c:url value="/resources/css/ui.css"/>" />
<link rel="stylesheet" href="<c:url value="/resources/css/common.css"/>" />
<link href='http://fonts.googleapis.com/css?family=Sintony:400,700' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="<c:url value="/resources/css/setting.css"/>" />
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.8.2.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script type="text/javascript">var context = '<c:url value="/"/>';</script>
<script type="text/javascript" src="<c:url value="/resources/js/fingraph-settings.js"/>"></script>
<script type="text/javascript">
    //language setting
    var zho = '<li class="lang_li" data-block="cn"><a><spring:message code="comm.footer.lang.chn"/></a></li>';
    var hk = '<li class="lang_li" data-block="hk"><a><spring:message code="comm.footer.lang.ch_hk"/></a></li>';
    var kor = '<li class="lang_li" data-block="ko"><a><spring:message code="comm.footer.lang.kor"/></a></li>';
    var eng = '<li class="lang_li" data-block="en"><a><spring:message code="comm.footer.lang.eng"/></a></li>';
    var jpn = '<li class="lang_li" data-block="ja"><a><spring:message code="comm.footer.lang.jpn"/></a></li>';
    $(function(){
    	
        setLanguage();
        
        $("#lang_menu").on("click", ".lang_li", function() {
            var lang = "" + $(this).data("block");
            $.ajax({
                url : '<c:url value="/common/changeLocaleByAjax"/>',
                data : ({
                    lang : lang
                }),
                type : 'POST',
                dataType : 'text',
                success : function(data) {
                    location.reload();
                }
            });
        });
    });
    function setLanguage() {
         $.ajax({
             url : '<c:url value="/common/getLocaleByAjax"/>',
             type : 'POST',
             dataType : 'text',
             success : function(data) {
                var langHtml = '';
                if (data == 'CHN') {
                    $('#selectLang').text(
                            '<spring:message code="comm.footer.lang.chn"/>');
                    langHtml = kor + eng + hk + jpn;
                    $('.footer-txt, .footer_kr').hide();
                    $('.footer_others').show();
                } else if (data == 'KOR') {
                    $('#selectLang').text(
                            '<spring:message code="comm.footer.lang.kor"/>');
                    langHtml = eng + zho + hk + jpn;
                    $('.footer-txt, .footer_kr').show();
                    $('.footer_others').hide();
                } else if (data == 'TWN') {
                    $('#selectLang').text(
                            '<spring:message code="comm.footer.lang.ch_hk"/>');
                    langHtml = kor + eng + zho + jpn;
                    $('.footer-txt, .footer_kr').hide();
                    $('.footer_others').show();
                } else if (data == 'JPN') {
                    $('#selectLang').text(
                            '<spring:message code="comm.footer.lang.jpn"/>');
                    langHtml = kor + eng + zho + hk;
                    $('.footer-txt, .footer_kr').hide();
                    $('.footer_others').show();
                } else {
                    $('#selectLang').text(
                            '<spring:message code="comm.footer.lang.eng"/>');
                    langHtml = kor + zho + hk + jpn;
                    $('.footer-txt').hide();
                }
                $('#lang_menu').html(langHtml);
             }
         });
    }
</script>
<sitemesh:write property="head"/>
</head>
<body>
    <div id="global-header-wrap">
        <div id="global-header">
            <div class="logo"><a href="<c:url value="/"/>"><img src="<c:url value="/resources/img/logo.png"/>" width="168" height="38" alt="fingraph logo" /></a></div>
        </div>
    </div>
    <div class="setting-wrap">
        <div class="setting-cont">
            <sitemesh:write property='body'></sitemesh:write>
        </div>
    </div>
    <div class="footer-wrap">
        <div class="footer">
            <div>
                <ul>
                    <li class="foot-menu">
                        <div class="dropup dropdown"  style="cursor: pointer;">
                            <i class="lang" style="vertical-align: middle;"></i>
                            <a class="dropdown-toggle" data-toggle="dropdown"><span id="selectLang"></span><b class="caret"></b></a>
                            <ul class="dropdown-menu" id="lang_menu">
                                <li><a href=""><spring:message code="comm.footer.lang.chn"/></a></li>
                                <li><a href=""><spring:message code="comm.footer.lang.kor"/></a></li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="foot-copy">
                <div class="about-terms">
                    <ul>
                        <li class="foot-menu"><a href=mailto:support@fingra.ph>support@fingra.ph</a></li>
                    </ul>
                </div>
                <div class="company-name">Fingra.ph Opensource Software © 2014</div>
            </div>
        </div>
    </div>
</body>
</html>