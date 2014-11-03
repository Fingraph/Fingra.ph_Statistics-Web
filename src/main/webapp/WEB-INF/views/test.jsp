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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
	<title>Test</title>
    <style type="text/css">
${demo.css}
    </style>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.8.2.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/highcharts/highcharts.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/highcharts/modules/exporting.js"/>"></script>
    <script src="<c:url value="/resources/js/highcharts/themes/dark-unica.js"/>"></script>
    <script type="text/javascript">
$(function() {
    $('#container').highcharts({
        title: {
            text: 'Monthly Average Temperature',
            x: -20 //center
        },
        subtitle: {
            text: 'Source: WorldClimate.com',
            x: -20
        },
        xAxis: {
            categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
                'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
        },
        yAxis: {
            title: {
                text: 'Temperature (°C)'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: '°C'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: 'Tokyo',
            data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
        }, {
            name: 'New York',
            data: [-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5]
        }, {
            name: 'Berlin',
            data: [-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 1.0]
        }, {
            name: 'London',
            data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
        }]
    });
});
    </script>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P> The time on the server is ${serverTime}. </P>

<hr/>

<table>
    <tr>
        <td>email</td>
        <td>name</td>
        <td>department</td>
        <td>created</td>
        <td>role</td>
    </tr>
	<c:forEach items="${memberlist}" var="list">
    <tr>
        <td>${list.email}</td>
        <td>${list.name}</td>
        <td>${list.department}</td>
        <td>${list.created}</td>
        <td>${list.role}</td>
    </tr>
	</c:forEach>
</table>

<hr/>

<h1>
    admin.properties  
</h1>

name : ${admin.name}<br/>
email : ${admin.email}<br/>
password : ${admin.password}<br/>
role : ${admin.role}<br/>

<hr/>

<h1>
    login  
</h1>

<a class="btn btn-svc" href="<c:url value="/login/form"/>"> login </a>
&nbsp;&nbsp;&nbsp;
<a class="btn btn-svc" href="<c:url value="/logout"/>"> logout </a>

<hr/>

<h1>
    Highcharts  
</h1>

<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

</body>
</html>
