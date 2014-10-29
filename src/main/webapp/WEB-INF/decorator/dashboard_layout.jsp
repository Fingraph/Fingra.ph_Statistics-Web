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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title><sitemesh:write property="title"/></title>
<link href="<c:url value="/resources/img/favicon.ico" />" rel="shortcut icon" />
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>" />
<link rel="stylesheet" href="<c:url value="/resources/css/ui.css"/>" />
<link rel="stylesheet" href="<c:url value="/resources/css/common.css"/>" />
<link href='http://fonts.googleapis.com/css?family=Sintony:400,700' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/msdropdown/dd.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/datepick/ui-flick.datepick.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/datepick/south-street/flick.jquery-ui.css"/>">

<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.8.2.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap/bootstrap-alert.js"/>"></script><!-- alert -->
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap/bootstrap-dropdown.js"/>"></script><!-- dropdown -->
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap/bootstrap-modal.js"/>"></script><!-- modal -->
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap/bootstrap-tooltip.js"/>"></script><!-- tooltip -->
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap/bootstrap-popover.js"/>"></script><!-- popover -->
<script type="text/javascript" src="<c:url value="/resources/js/jquery.dd.js"/>"></script><!-- msdropdown -->
<script type="text/javascript" src="<c:url value="/resources/js/jquery.metadata.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/datepick/jquery.datepick.js"/>"></script><!-- datepick -->
<script type="text/javascript" src="<c:url value="/resources/js/datepick/jquery.datepick.ext.min.js"/>"></script><!-- datepick -->
<script type="text/javascript" src="<c:url value="/resources/js/moment.min.js"/>"></script><!-- date -->
<script type="text/javascript" src="<c:url value="/resources/js/jshashtable-2.1.js"/>"></script><!-- hashtable -->
<script type="text/javascript" src="<c:url value="/resources/js/jquery.numberformatter-1.2.3.js"/>"></script><!-- number format -->
<script type="text/javascript" src="<c:url value="/resources/js/jstz-1.0.4.min.js"/>"></script> <!-- timezone -->
<script type="text/javascript" src="<c:url value="/resources/js/chart.js"/>"></script><!-- chart -->
<script type="text/javascript" src="<c:url value="/resources/js/highcharts/highcharts.js"/>"></script><!-- highchart -->
<script type="text/javascript" src="<c:url value="/resources/js/highcharts/highcharts-more.js"/>"></script><!-- highchart -->
<script type="text/javascript" src="<c:url value="/resources/js/highcharts/modules/exporting.js"/>"></script><!-- highchart -->
<script type="text/javascript" src="<c:url value="/resources/js/highcharts/themes/gray.js"/>"></script><!-- highchart -->
<script type="text/javascript">var context = '<c:url value="/"/>';</script>
<script type="text/javascript" src="<c:url value="/resources/js/fingraph-settings.js"/>"></script>
<script type="text/javascript">
//language setting
var zho = '<li class="lang_li" data-block="cn"><a><spring:message code="comm.footer.lang.chn"/></a></li>';
var hk = '<li class="lang_li" data-block="hk"><a><spring:message code="comm.footer.lang.ch_hk"/></a></li>';
var kor = '<li class="lang_li" data-block="ko"><a><spring:message code="comm.footer.lang.kor"/></a></li>';
var eng = '<li class="lang_li" data-block="en"><a><spring:message code="comm.footer.lang.eng"/></a></li>';
var jpn = '<li class="lang_li" data-block="ja"><a><spring:message code="comm.footer.lang.jpn"/></a></li>';

$(function() {
	
    //print
    $("#printBtn").click(function(){
        window.print();
    });
    
    //appList setting
    $.ajax({
        url: '<c:url value="/dashboard/getAppListAjax"/>',
        type: 'POST',
        dataType: 'json',
        success: function(data) {
            displayAppList(data.list);
        }
    });
    
    $('.help').popover({trigger:'hover',placement:'top'});
    $( "ul#menu li img" ).each(function( index ) {
        if($(this).attr('class') == $('#menuId').val()){
            $(this).attr('src',$(this).attr('src').replace('up','over'));
        }
    });
    
    setLanguage();
    
    $("#lang_menu").on("click",".lang_li",function(){
        var lang = ""+$(this).data("block");
        $.ajax({
            url: '<c:url value="/common/changeLocaleByAjax"/>',
            data: ({lang:lang}),
            type: 'POST',
            dataType: 'text',
            success: function(data) {
                location.reload();
            }
        });
    });
});

//Exceldown
function download_report(sheet){
    $("input[name=htmldata]").remove();
    $("input[name=sheetname]").remove();
    if (sheet.length == undefined) {
        htmldata ="<html><head></head><body><table>" + $("#report_table_0").html() + "</table></body></html>";
        $('#downloadForm').append("<input type='hidden' id='htmldata0' name='htmldata' value='"+ encodeURIComponent(htmldata) +"'/>");
    } else {
        var sheetCnt = sheet.length;
        var htmldata = new Array(sheetCnt);
        for(var i=0;i<sheetCnt;i++){
            htmldata[i] ="<html><head></head><body><table>" + $("#report_table_"+i).html() + "</table></body></html>";
            $('#downloadForm').append("<input type='hidden' id='htmldata"+i+"' name='htmldata' value='"+ encodeURIComponent(htmldata[i]) +"'/>");
            $('#downloadForm').append("<input type='hidden' id='sheetname"+i+"' name='sheetname' value='"+ encodeURIComponent(sheet[i]) +"'/>");
        }
    }
    $('#downloadForm').submit();
}

function setLanguage(){
    $.ajax({
        url: '<c:url value="/common/getLocaleByAjax"/>',
        type: 'POST',
        dataType: 'text',
        success: function(data) {
            var langHtml = '';
            if(data == 'CHN'){
                $('#selectLang').text('<spring:message code="comm.footer.lang.chn"/>');
                langHtml=kor+eng+hk+jpn;
            }else if(data == 'KOR'){
                $('#selectLang').text('<spring:message code="comm.footer.lang.kor"/>');
                langHtml=eng+zho+hk+jpn;
            }else if(data == 'TWN'){
                $('#selectLang').text('<spring:message code="comm.footer.lang.ch_hk"/>');
                langHtml=kor+eng+zho+jpn;
            }else if(data == 'JPN'){
                $('#selectLang').text('Japanese');
                langHtml=kor+eng+zho+hk;
            }else{
                $('#selectLang').text('<spring:message code="comm.footer.lang.eng"/>');
                langHtml=kor+zho+hk+jpn;
            }
            $('#lang_menu').html(langHtml);
        }
    });
}

function displayAppList(result){
    if(result.length>0){
        var appList = $(".app-select-wrap").msDropdown({
            byJson:{data:result,name:'appList',width:'400'},
            roundedCorner:true,
            visibleRows:8,
            on:{
                change: function(data, ui) {
                    var currUrl = document.location.href;
                    var menuId = $('#menuId').val();
                    if (menuId == 'dashboard') {
                        location.href = '<c:url value="/dashboard/"/>' + data.value;
                    } else{
                        location.href=currUrl.substring(0,currUrl.lastIndexOf('/'))+'/'+data.value;
                    }
                }
            }
            }).data("dd");
            appList.setIndexByValue('${app.appkey}');
    }
}
</script>
<sitemesh:write property="head" />
</head>
<body>
    <div id="global-header-wrap">
        <div id="global-header">
            <div class="logo"><a href="<c:url value="/app/list"/>"><img src="<c:url value="/resources/img/logo.png"/>" width="168" height="38" alt="fingraph logo" /></a></div>
            <div class="app-select-wrap"></div>
            <div class="app-list-btn"><a href="<c:url value="/app/list"/>"><img src="<c:url value="/resources/img/btn_header_edit.png"/>" alt="Go AppList" title="Go AppList"/></a></div>
            <div class="header-button-wrap">
                <ul>
                    <li class="dropdown"><div class="btn dropdown-toggle" data-toggle="dropdown"><span style="margin-right:3px;"><sec:authentication property="principal.name"/></span><i class="icon-chevron-down icon-white"></i></div>
                       <div class="pop-account dropdown-menu" style="">
                            <div class="account-btn-wrap ">
                                <ul>
                                    <li class="name"><sec:authentication property="principal.email"/></li>
                                    <li class="account-hr"></li>
                                    <sec:authorize ifAnyGranted="ROLE_ADMIN">
                                        <li class="account-button" id="manageAccountBtn"><i class="icon-user icon-white" style="padding-right:10px;"></i><spring:message code="comm.header.manageaccount"/></li>
                                    </sec:authorize>
                                    <sec:authorize ifNotGranted="ROLE_ADMIN">
                                        <li class="account-button" id="settingBtn"><i class="icon-cog"></i><spring:message code="comm.header.setting"/></li>
                                    </sec:authorize>
                                    <li class="account-button" id="signOutBtn"><i class="icon-logout"></i><spring:message code="comm.header.signout"/></li>
                                </ul>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
	<div id="wrap">
    	<div id="left-menu">
        	<ul id="menu">
            	<li><a href="<c:url value="/dashboard/${app.appkey}"/>"><img src="<c:url value="/resources/img/menu1_up.png"/>" width="140" height="170" alt="DASHBOARD" class="dashboard"/></a></li>
            	<li><a href="<c:url value="/today/newUsers/${app.appkey}"/>"><img src="<c:url value="/resources/img/menu8_up.png"/>" width="140" height="170" alt="TODAY SNAPSHOT" class="today"/></a></li>
            	<li><a href="<c:url value="/performance/newUsers/${app.appkey}"/>"><img src="<c:url value="/resources/img/menu3_up.png"/>" width="140" height="140" alt="PERFOMANCE SNAPSHOT" class="performance"/></a></li>
            	<li><a href="<c:url value="/distribution/dayOfWeek/${app.appkey}"/>"><img src="<c:url value="/resources/img/menu4_up.png"/>" width="140" height="140" alt="DISTRIBUTION SNAPSHOT" class="distribution"/></a></li>
				<li><a href="<c:url value="/components/newUsers/${app.appkey}"/>"><img src="<c:url value="/resources/img/menu6_up.png"/>" width="140" height="140" alt="COMPONENTS SNAPSHOT" class="components"/></a></li>
            </ul>
   	    </div>
        <div class="contents">
            <div id="app-info">
        	    <div class="app-name-wrap">
            	    <div class="app-name">
               		    <div class="app-icon">
               			    <c:choose>
               				    <c:when test="${not empty app.appInfo.smallicon}"><img src="${app.appInfo.smallicon}" /></c:when>
               				    <c:otherwise><img src="<c:url value="/resources/img/app_icon.png"/>"/></c:otherwise>
               			    </c:choose>
               		    </div>
                        <div class="name ellipsis" title="${app.appname}">${app.appname}</div>
                    </div>
          	    </div>
                <div class="button-wrap">
           	        <ul class="info-menu">
                	    <li>
                    	    <div class="info-icon"><c:choose><c:when test="${app.platform eq 1}"><img src="<c:url value="/resources/img/icon_ios.gif"/>" width="40" height="41" alt="Platform" /></c:when><c:when test="${app.platform eq 2}"><img src="<c:url value="/resources/img/icon_android.gif"/>" width="40" height="41" alt="Platform" /></c:when></c:choose></div>
                            <p class="stxt"><spring:message code="dash.head.platform"/></p>
                            <p class="btxt"><c:choose><c:when test="${app.platform eq 1}">IOS</c:when><c:when test="${app.platform eq 2}">ANDROID</c:when></c:choose></p>
                        </li>
                	    <li>
                    	    <div class="info-icon"><img src="<c:url value="/resources/img/icon_category.gif"/>" width="40" height="41" alt="Category" /></div>
                            <p class="stxt"><spring:message code="dash.head.category"/></p>
                            <p class="btxt">${app.categoryname}</p>
                        </li>
                	    <li>
                    	    <div class="info-icon"><img src="<c:url value="/resources/img/icon_market.gif"/>" width="40" height="41" alt="Market" /></div>
                            <p class="stxt"><spring:message code="dash.head.appIdentifier"/></p>
                            <p class="btxt">${app.appid}</p>
                        </li>
                    </ul>
                    <div class="info-button"><img src="<c:url value="/resources/img/btn_print.gif"/>" width="110" height="41" alt="print" id="printBtn" style="cursor: pointer;"/></div>
          	    </div>
            </div>
		    <div id="main-cont">
		        <sitemesh:write property="div.main-cont" />
		    </div>
      	    <div class="gap"></div>
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
    <form action="<c:url value="/excelexport"/>" method="post" name="downloadForm" id="downloadForm">
	<input type="hidden" name="currentMenu" id="currentMenu" value="<sitemesh:write property="meta.currentMenu" />"/>
	<input type="hidden" name="sectionMenu" id="sectionMenu" value="<sitemesh:write property="meta.menuId" />"/>
	<input type="hidden" name="appkey" id="appkey" value="${appkey}"/>
</form>
<input id="menuId" type="hidden" value="<sitemesh:write property="meta.menuId" />"/>
</body>
</html>
