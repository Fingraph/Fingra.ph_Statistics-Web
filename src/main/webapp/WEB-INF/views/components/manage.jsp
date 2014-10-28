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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="fh"%>
<!DOCTYPE html>
<html>
<head>
<meta name="menuId" content="components" />
<title>Fingra.ph - <spring:message code="dash.title.components"/></title>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.zclip.1.1.1/jquery.zclip.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap/bootstrap-collapse.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap/bootstrap-transition.js"/>"></script>

<script type="text/javascript">
var tempGroupHtml = "";
var $groupName;
var tempEventHtml = "";
var $eventName;
$(function() {
	$(".collapse").collapse();//initialize collapse
	$("div#collapse_0").addClass('in')
	initCopyButton();//initialize copyBtn
	$('button.componentLink').click(function(){
		location.href='<c:url value="/components/newUsers/${appkey}"/>';
	});


	// Add a form for new group
	$('#addGroup').click(function(e){
		e.preventDefault();
		$('div#accordion2 div.group:last').after($('#addGroupHtml').html());
	});
	// Remove a form for new group
	$('#accordion2').on('click','#addGroupCancel',function(e){
		e.preventDefault();
		$(this).parent().parent().parent().remove();
	});
	// Register a new group
	$('#accordion2').on('click','#addGroupSave',function(e){
		e.preventDefault();
		var newGroupName = $(this).parent().prev().find('input[name=newGroupName]').val().trim();
		if(newGroupName == ''){showNameCheckModal('group',1); return;}
		if(specialLetters('group',newGroupName)) return;
		$('#componentsGroup #groupname').val(newGroupName);
		$(this).parent().parent().parent().remove();
		$.ajax({
			url: '<c:url value="/components/addGroupHtmlAjax"/>',
			data: $('#componentsGroup').serialize(),
			type: 'POST',
			dataType: 'html',
			success: function(data) {
				$('div#accordion2 div.group:last').after(data);
				$("form").each(function() {
	                if(this.id == "componentsGroup") this.reset();
	             });
				$(".collapse").collapse();
				buttonDisable();
			}
		});
	});

	// Add a form to edit name of the group
	$('#accordion2').on('click','.editGroup',function(e){
		e.preventDefault();
		$.metadata.setType("class");
		var groupkey=$(this).closest('.group').metadata().groupkey;
		if(tempGroupHtml != ""){
			$groupName.html(tempGroupHtml);
		}
		$groupName = $('#groupname_'+groupkey);
		var groupName=$(this).parent().prev('a').children('span.groupname').text();
		tempGroupHtml = $groupName.html();

		$groupName.html($('div#editGroupHtml').html());
		$('#groupname_'+ groupkey).find('input[name=editGroupName]').val(groupName);

	});
	// Remove a form to edit name of the group
	$('#accordion2').on('click','.editGroupCancel',function(e){
		e.preventDefault();
		$groupName.html(tempGroupHtml);
	});
	// Save to edit name of the group.
	$('#accordion2').on('click','.editGroupSave',function(e){
		e.preventDefault();
		var groupkey=$(this).closest('.group').metadata().groupkey;
		var editGroupName = $(this).parent().find('input[name=editGroupName]').val().trim();
		if(editGroupName == ''){showNameCheckModal('group',1); return;}
		if(specialLetters('group',editGroupName)) return;
		$('#componentsGroup #groupname').val(editGroupName);
		$('#componentsGroup #groupkey').val(groupkey);
		$.ajax({
			url: '<c:url value="/components/editGroupAjax"/>',
			data: $('#componentsGroup').serialize(),
			type: 'POST',
			dataType: 'json',
			success: function(data) {
				if(data == true){
					$groupName.html(tempGroupHtml);
					tempGroupHtml='';
					$('#groupname_'+groupkey+' span.groupname').text(editGroupName);
					$("form").each(function() {
		                if(this.id == "componentsGroup") this.reset();
		             });
				}
			}
		});

	});
	// Remove a group
	$('#accordion2').on('click','.removeGroup',function(e){
		$.metadata.setType("class");
		var groupkey = $(this).closest('.group').metadata().groupkey;
		var eventCnt = $('#collapse_'+groupkey+ ' input[name=eventkey]').length;

		var heading = '<spring:message code="modal.heading.delete"/>';
		var question;
	    if(eventCnt>0){
	    	question = '<spring:message code="modal.comp.question.group.delete.Attention"/>';
	    }else{
	    	question = '<spring:message code="modal.comp.question.group.delete"/>';
	    }
	    var cancelButtonTxt = '<spring:message code="btn.cancel.text"/>';
	    var okButtonTxt = '<spring:message code="btn.del.text"/>';
	    $removeGroupDiv = $(this).closest('.group');
	    var callback = function() {
	    	$('#componentsGroup #groupkey').val(groupkey);
	    	$.ajax({
				url: '<c:url value="/components/removeGroupAjax"/>',
				data: $('#componentsGroup').serialize(),
				type: 'POST',
				dataType: 'json',
				success: function(data) {
					if(data == true){
						$removeGroupDiv.remove();
						$("form").each(function() {
			                if(this.id == "componentsGroup") this.reset();
			             });
						buttonDisable();
					}
				}
			});
	    };
		confirm(heading, question, cancelButtonTxt, okButtonTxt, callback);
	});

	// Add a form for new component
	$('#accordion2').on('click','.addEvent',function(e){
		e.preventDefault();
		$(this).before($('#addEventHtml').html());
	});
	// Remove a form for new component
	$('#accordion2').on('click','.addEventCancel',function(e){
		e.preventDefault();
		$(this).closest('.comp-add').remove();
	});

	// Save a form for new component
	$('#accordion2').on('click','.addEventSave',function(e){
		e.preventDefault();
		$.metadata.setType("class");
		var groupkey = $(this).closest('.group').metadata().groupkey;
		var newEventName = $(this).closest('.comp-add').find('input[name=newEventName]').val().trim();
		if(newEventName == ''){showNameCheckModal('component',1); return;}
		if(specialLetters('component',newEventName)) return;
		$('#event #componentname').val(newEventName);
		$('#event #groupkey').val(groupkey);
		$removeEventDiv = $(this).closest('.comp-add');
		$.ajax({
			url: '<c:url value="/components/addHtmlAjax"/>',
			data: $('#event').serialize(),
			type: 'POST',
			dataType: 'html',
			success: function(data) {
				$('div#collapse_'+groupkey+' table tr:last').after(data);
				$removeEventDiv.closest('.comp-add').remove();
				$("form").each(function() {
	                if(this.id == "event") this.reset();
	             });
				initCopyButton();

			}
		});
	});

	// Add a form to rename a component.
	$('#accordion2').on('click','.editEvent',function(e){
		e.preventDefault();
		var eventkey = ($(this).closest('tr').attr('id'));
		if(tempEventHtml != ""){
			$eventName.html(tempEventHtml);
		}
		$eventName = $(this).parent();
		var eventName= $(this).prev('span.eventName').text().trim();
		tempEventHtml = $eventName.html();
		$eventName.html($('div#editEventHtml').html());
		$('tr#'+eventkey+' td.tdEvent input[name=editEventName]').val(eventName);
	});

	// Remove a form to rename a component.
	$('#accordion2').on('click','.editEventCancel',function(e){
		e.preventDefault();
		$eventName.html(tempEventHtml);
	});


	// Save a form to rename a component.
	$('#accordion2').on('click','.editEventSave',function(e){
		e.preventDefault();
		var groupkey=$(this).closest('.group').metadata().groupkey;
		var eventkey = $(this).closest('tr').attr('id');
		var editEventName = $(this).parent().find('input[name=editEventName]').val().trim();
		if(editEventName == ''){showNameCheckModal('component',1); return;}
		if(specialLetters('component',editEventName)) return;
		$('#event #groupkey').val(groupkey);
		$('#event #componentkey').val(eventkey);
		$('#event #componentname').val(editEventName);
		$.ajax({
			url: '<c:url value="/components/editAjax"/>',
			data: $('#event').serialize(),
			type: 'POST',
			dataType: 'json',
			success: function(data) {
				if(data == true){
					$eventName.html(tempEventHtml);
					tempEventHtml='';
					$('tr#'+eventkey+' td.tdEvent span.eventName').text(editEventName);
					$("form").each(function() {
		                if(this.id == "event") this.reset();
		             });
				}
			}
		});
	});

	// Remove an event.
	$('#accordion2').on('click','.removeEvent',function(e){
		e.preventDefault();
		var eventkey =$(this).closest('tr').attr('id');
		var heading = '<spring:message code="modal.heading.delete"/>';
	    var	question = '<spring:message code="modal.comp.question.component.delete"/>';
	    var cancelButtonTxt = '<spring:message code="btn.cancel.text"/>';
	    var okButtonTxt = '<spring:message code="btn.del.text"/>';
	    var iMap = [eventkey];
	    var callback = function() {
	    	$.ajax({
				url: '<c:url value="/components/removeMapAjax"/>',
				data: {'ieventkey[]':iMap,appkey:'${appkey}'},
				type: 'POST',
				dataType: 'json',
				success: function(data) {
					if(data == true){
						for(var i=0;i<iMap.length;i++){
					    	$('tr#'+iMap[i]).remove();
					    }
						buttonDisable();
					}
				}
			});


	    };
		confirm(heading, question, cancelButtonTxt, okButtonTxt, callback);
	});

	//chackAll
	$('#accordion2').on('click','.checkAll',function(e){
	    if($(this).is(':checked')){
			$(this).closest('.gr-comp-tbl').find('input[name=event]').prop('checked',true);
	    }else{
	    	$(this).closest('.gr-comp-tbl').find('input[name=event]').prop('checked',false);
	    }
	    buttonDisable();
	});


	$('#accordion2').on('click','input:checkbox[name=event]',function(e){
		buttonDisable();
	});


	$('.multiRemove').click(function(e){
		e.preventDefault();
		if($('input:checkbox[name=event]:checked').length==0) return;
		var iMap = $('input[name=event]:checked').map(function(i,n) {
	        return $(n).val().split('_')[1];
	    }).get(); //get converts it to an array
	    var heading = 'Delete Confirmation';
	    var	question = 'Are you sure to delete this component?';
	    var cancelButtonTxt = '<spring:message code="btn.cancel.text"/>';
	    var okButtonTxt = '<spring:message code="btn.del.text"/>';
	    var callback = function() {
	    	$.ajax({
				url: '<c:url value="/components/removeMapAjax"/>',
				data: {'ieventkey[]':iMap,appkey:'${appkey}'},
				type: 'POST',
				dataType: 'json',
				success: function(data) {
					if(data == true){
						for(var i=0;i<iMap.length;i++){
					    	$('tr#'+iMap[i]).remove();
					    }
						buttonDisable();
					}
				}
			});


	    };
		confirm(heading, question, cancelButtonTxt, okButtonTxt, callback);
	});

	$('#accordion2').on('click','.moveToGroup',function(e){
		$('div.group div.comp-add').remove();
		var iMap = $('input[name=event]:checked').map(function(i,n) {
	        return $(n).val();
	    }).get(); //get converts it to an array
	    var groupName=$(this).parent().prev('a').children('span.groupname').text();
	    var moveGroupkey = $(this).closest('.group').metadata().groupkey;

		if(true){
			var heading = '<spring:message code="modal.heading.move"/>';
		    var	question = '<spring:message code="modal.comp.question.move"/><br/><spring:message code="modal.comp.question.move2"/> : <strong>'+groupName +'</strong>';
		    var cancelButtonTxt = '<spring:message code="btn.cancel.text"/>';
		    var okButtonTxt = '<spring:message code="btn.ok.text"/>';
		    var callback = function() {
		    	$.ajax({
					url: '<c:url value="/components/moveMapAjax"/>',
					data: {'ieventkey[]':iMap,appkey:'${appkey}',groupkey:moveGroupkey},
					type: 'POST',
					dataType: 'json',
					success: function(data) {
						if(data == true){
							for(var i=0;i<iMap.length;i++){
								var tempkey = iMap[i].split('_');
								if(moveGroupkey != tempkey[0] ){
									$temp=$('tr#'+tempkey[1]).clone();
									$temp.find('input[name=event]').val(moveGroupkey+'_'+tempkey[1]);
									$temp.find('td').css('background','#fffde8');
									$('tr#'+tempkey[1]).remove();
									$('div#collapse_'+moveGroupkey+' table tr:last').after($temp);
								}
							}
							$('div#collapse_'+moveGroupkey+' table input[name=event]').attr('checked',false);
							buttonDisable();
							initCopyButton();
						}
					}
				});


		    };
			confirm(heading, question, cancelButtonTxt, okButtonTxt, callback);
		}
	});


	// Change arrow icon when group is clicked.
 	$('#accordion2').on('click','.accordion-toggle',function(e){
 		e.preventDefault();
		if($('#collapse_'+$(this).attr('href').split("_")[1]).hasClass('in')){
			$(this).children('img.toggleBtn').attr('src','<c:url value="/"/>resources/img/ico-gr-view.png');
			$(this).closest('.group').find('div.comp-add').remove();
		}else{
			$(this).children('img.toggleBtn').attr('src','<c:url value="/"/>resources/img/ico-gr-close.png');
		}
	});

});

function buttonDisable(){
	if($('input:checkbox[name=event]:checked').length == 0){
		$('.multiRemove').addClass('disabled');
		$('.moveToGroup').css('display','none');
	}else{
		$('.multiRemove').removeClass('disabled');
		$('.moveToGroup').css('display','');
	}
}

// reset a copy bundle.
function initCopyButton(){
	$('.zclip').remove();
	//componentkey copy
	$("div#accordion2 button.copyBtn").on('click', function (e) {
		   e.preventDefault();
	}).zclip({
		path:'<c:url value="/resources/js/jquery.zclip.1.1.1/ZeroClipboard.swf"/>',
		copy:function(){
			return $(this).parent().prev('input[name=componentkey]').val();
			}
	});
}
// Check if name contain special characters.
function specialLetters(type,str){
	var RegExpTag =/[~!*()']/;
	if(RegExpTag.test(str)){
		showNameCheckModal(type,2);
		return true;
	}
	return false;
}
// Check a validity of the name  type2->space:1 special characters:2
function showNameCheckModal(type1,type2){
	var heading='<spring:message code="modal.heading.notification"/>';
	var question='';
	if(type2==1){
		question = type1=='group'?'<spring:message code="modal.comp.question.empty.group.name"/>':'<spring:message code="modal.comp.question.empty.component.name"/>';
	}else if(type2==2){
		question = "<spring:message code="modal.comp.question.check.special.characters"/>";
	}
	var cancelButtonTxt = '';
	var okButtonTxt = '<spring:message code="btn.ok.text"/>';
	var callback = function() {};
	confirm(heading, question, cancelButtonTxt, okButtonTxt, callback);
}


</script>
</head>
<body>
<div id="main-cont">
<div id="snapshot">
    	<!--
    	<div class="float-right">
        	<button id="multiRemove" class="btn btn-danger multiRemove disabled"><i class="icon-trash icon-white"></i> <spring:message code="btn.del.text"/></button>
        </div>
         -->
        <h1 class="title"><spring:message code="comp.add.title"/></h1>
        <div class="sdk_info20">
       		<span class="sdk_info_text"><spring:message code="comm.add.guide.text"/></span>
       		<span><spring:message code="comm.add.guide.down.text"/> <a class="btn btn-danger btn-small btn_sdk_info" href="${pageContext.request.contextPath}<spring:message code="file.sdk.guide.ios"/>" target="_blank">IOS</a>&nbsp;<a class="btn btn-info btn-small btn_sdk_info" href="${pageContext.request.contextPath}<spring:message code="file.sdk.guide.android"/>" target="_blank">Android</a></span>
       	</div>
        <div id="accordion2" class="list-body">
        	<c:forEach items="${list}" var="list" varStatus="i">
	        	<div  class="comp-group-wrap group {groupkey:'${list.groupkey}'}">
	                <div class="gr-header">
	                    <div class="gr-title" id="groupname_${list.groupkey}">
	                    	<h3><a href="#collapse_${list.groupkey}" data-parent="#accordion2" data-toggle="collapse" class="accordion-toggle"><img class="toggleBtn" src="<c:url value="/resources/img/ico-gr-view.png"/>"  alt=""/><span class="groupname" style="padding: 0 7px;">${list.groupname}</span></a> <span><button class="btn btn-small editGroup" style="margin: 0 5px;"><i class="icon-pencil icon-white"></i> <spring:message code="btn.rename.text"/></button>&nbsp;&nbsp;<button class="btn btn-small btn-primary moveToGroup" style="display: none"><i class=" icon-arrow-down icon-white"></i> <spring:message code="btn.movetohere.text"/></button></span></h3>
	                    </div>
	                    <c:if test="${list.groupkey ne 0}">
	                    <div class="gr-btn {groupkey:'${list.groupkey}'}">
	                        <ul>
	                            <li><img src="<c:url value="/resources/img/btn-group-del.png"/>" alt="<spring:message code="btn.del.text"/>" title="<spring:message code="btn.del.text"/>" class="hand removeGroup"></li>
	                        </ul>
	                    </div>
	                    </c:if>
	                </div>
	                <div class="gr-body collapse" id="collapse_${list.groupkey}">
	                	<table class="gr-comp-tbl">
	                    <colgroup>
	                    <col width="49px">
	                    <col>
	                    <col width="300px">
	                    </colgroup>
	                        <tbody>
	                        <tr>
	                            <th scope="col">
	                                <input type="checkbox" id="checkAll_${list.groupkey}" class="input_check checkAll" name="checkAll" value="" >
	                            </th>
	                            <th scope="col"><spring:message code="comp.add.comfName"/></th>
	                            <th scope="col"><spring:message code="comp.add.comfKey"/></th>
	                        </tr>
	                        <c:forEach items="${list.clist}" var="clist">
	                        <tr id="${clist.componentkey}" class="trEvent">
							 <td>
							     <input type="checkbox" id="event_${clist.componentkey}" name="event" value="${list.groupkey}_${clist.componentkey}"/>
							 </td>
							 <td class="tdEvent"><span class="eventName" style="padding: 0 7px;">${clist.componentname}</span><img class="editEvent hand" src="<c:url value="/resources/img/btn-comp-rename.png"/>"  alt="<spring:message code="btn.rename.text"/>" title="<spring:message code="btn.rename.text"/>"/></td>
							 <td>
							 	<input type="text" disabled="disabled" name="componentkey" class="key-input" style="width: 80px;" value="${clist.componentkey}" />
							    	<span class="z-clip-wrap"><button class="btn btn-primary btn-small copyBtn"><i class="icon-file icon-white"></i> <spring:message code="btn.copy.text"/></button>&nbsp;&nbsp;&nbsp;<button class="btn btn-danger btn-small removeEvent"><i class="icon-trash icon-white"></i> <spring:message code="btn.del.text"/></button></span>
							 </td>
							</tr>
	                        </c:forEach>
	                       </tbody>
	                    </table>

	                    <div class="gr-add-comp addEvent {groupkey:'${list.groupkey}'}"><a href="#">+<spring:message code="comp.add.newComponent"/></a></div>
	                </div>
	                <div class="gr-shadow"></div>
	            </div>
			</c:forEach>

            <div id="addGroupDiv" class="add-group-wrap"><button class="btn btn-large add-group btn-primary btn-block" id="addGroup">+<spring:message code="comp.add.newGroup"/></button></div>

        </div>
        <div style="text-align: right;"><button class="btn btn-warning componentLink"><spring:message code="btn.backtosnapshot.txt"/></button></div>
    </div>
<!-- manage end -->
<form name="event" id="event">
	<input type="hidden" name="appkey" id="appkey" value="${appkey}"/>
	<input type="hidden" name="componentname" id="componentname" value=""/>
	<input type="hidden" name="componentkey" id="componentkey" value=""/>
	<input type="hidden" name="groupkey" id="groupkey" value=""/>
</form>
<form name="componentsGroup" id="componentsGroup">
	<input type="hidden" name="appkey" id="appkey" value="${appkey}"/>
	<input type="hidden" name="groupname" id="groupname" value=""/>
	<input type="hidden" name="groupkey" id="groupkey" value="-2"/>
</form>
<div id="addGroupHtml" style="display:none">
	<div class="comp-group-wrap addGroupHtml">
        <div class="gr-header">
            <div class="gr-title">
            	<div class="ad-gr-in"><input type="text" style="width:300px;" id="newGroupName" name="newGroupName" placeholder="<spring:message code="comp.add.comfGroupName"/>" maxlength="25"/></div>
            </div>
            <div class="ad-gr-btn">
            	<button class="btn btn-small" id="addGroupCancel"><spring:message code="btn.cancel.text"/></button>
            	<button class="btn btn-small btn-primary" id="addGroupSave"><spring:message code="btn.save.text"/></button>
            </div>
        </div>
        <div class="gr-shadow"></div>
    </div>
</div>
<div id="editGroupHtml" style="display:none">
	<div class="ad-gr-in">
		<input id="editGroupName" type="text" maxlength="25" name="editGroupName" style="width:300px;">
		<button class="btn btn-small editGroupCancel"><spring:message code="btn.cancel.text"/></button>
	    <button class="btn btn-small btn-primary editGroupSave"><spring:message code="btn.save.text"/></button>
	</div>
</div>
<div id="addEventHtml" style="display:none">
	<div class="comp-add">
		<div class="gr-comp-in"><input style="width:300px" type="text" name="newEventName" placeholder="<spring:message code="comp.add.comfName"/>" /></div>
		<div class="gr-comp-btn">
			<button class="btn btn-small addEventCancel"><spring:message code="btn.cancel.text"/></button>
			<button class="btn btn-primary btn-small addEventSave"><spring:message code="btn.save.text"/></button>
		</div>
	</div>
</div>
<div id="editEventHtml" style="display:none">
	<input id="editEventName" type="text" maxlength="25" name="editEventName" style="width:300px;">
	<button class="btn btn-small editEventCancel"><spring:message code="btn.cancel.text"/></button>
    <button class="btn btn-small btn-primary editEventSave"><spring:message code="btn.save.text"/></button>
</div>
</div>
</body>
</html>
