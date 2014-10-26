$(function() {
	$('.dropdown-toggle').dropdown();
	$('#signOutBtn').click(function(){
		 var expireDate = new Date();
		  expireDate.setDate( expireDate.getDate() - 1 );
		  document.cookie = "fingraphPeriod= " + "; expires=" + expireDate.toGMTString() + "; path=/";
   		  location.href=context+'logout';
	});
	$('#settingBtn').click(function(){
		location.href=context+'account/edit';
	});
	$('#subscriptionBtn').click(function(){
		location.href=context+'account/subscription';
	});

	$('body').on('click','.status-close',function(e){
		e.preventDefault();
		$("#status_change").slideUp("slow");
		$('#status_change').remove();
	});
});

//-- slideBox open
//.status-error(빨간배경)
//.status-success(초록배경)
//.status-info(파란배경)
function showSlideMsgBox(type,msg,time){
	var bgcolor ='status-error';
	if(type == 'success' ){
		bgcolor ='status-success';
	}else if(type == 'info'){
		bgcolor ='status-info';
	}

	window.scrollTo(0,0) ;
	var html='';
	html += '<div id="status_change" class="'+ bgcolor +'">\n';
	html +=	'<div class="status-wrap">\n';
	html +=	msg;
	html += '	<img src="'+context+'resources/img/status_close.png" class="status-close"/>\n';
	html += '	</div>\n';
	html += '</div>';

	$('body').prepend(html);
	$("#status_change").slideDown("slow");

	if(time=='3sec'){
		setTimeout("hiddenSlideMsgBox()",3000);
	}else if(time=='5sec'){
		setTimeout("hiddenSlideMsgBox()",5000);
	}


}

//-- slideBox closed
function hiddenSlideMsgBox(){
	//$("#status_change").html("");
	$("#status_change").slideUp("slow");
	//
}


// -- Generic Confirm
var confirmModal;
function confirm(heading, question, cancelButtonTxt, okButtonTxt, callback) {
	$('div.modal').remove();

  if(cancelButtonTxt !=''){
	  confirmModal =
	    $('<div class="modal hide fade">' +
	        '<div class="modal-header">' +
	          '<a class="close" data-dismiss="modal" >&times;</a>' +
	          '<h3>' + heading +'</h3>' +
	        '</div>' +

	        '<div class="modal-body">' +
	          '<p>' + question + '</p>' +
	        '</div>' +
	        '<div class="modal-footer">' +
	          '<a href="#" class="btn" data-dismiss="modal">' +
	            cancelButtonTxt +
	          '</a>' +
	          '<a href="#" id="okButton" class="btn btn-primary">' +
	            okButtonTxt +
	          '</a>' +
	        '</div>' +
	      '</div>');
  }else{
	  confirmModal =
	    $('<div class="modal hide fade">' +
	        '<div class="modal-header">' +
	          '<a class="close" data-dismiss="modal" >&times;</a>' +
	          '<h3>' + heading +'</h3>' +
	        '</div>' +
	        '<div class="modal-body">' +
	          '<p>' + question + '</p>' +
	        '</div>' +
	        '<div class="modal-footer">' +
	          '<a href="#" id="okButton" class="btn btn-primary">' +
	            okButtonTxt +
	          '</a>' +
	        '</div>' +
	      '</div>');
  }

  confirmModal.find('#okButton').click(function(event) {
	  confirmModal.modal('hide');
	  callback();
  });

  confirmModal.find('.copyBtn').click(function(event) {
	  confirmModal.modal('hide');
	  callback();
  });

  confirmModal.modal('show');


  //confirmModal.find('#msg').text('test')



  return true;
};

//-- Generic Confirm
function showAlert(targetId,msg){
	var alertHtml='<div class="alert alert-error"><a class="close" href="#" data-dismiss="alert">×</a>'+msg+'</div>';
	$('#'+targetId).html(alertHtml);
}

// -- COMPONENTS SNAPSHOT

function makeComponetsSnapshot(result){
	$('#components div#inner-body2 table th').text('');
	$('#components div#inner-body2 table td').text('');
	if(result.newUsersList.length>0){
		var newUserList = result.newUsersList;
		for(var i=0;i<newUserList.length;i++){
			$('#components div.com_newUsersList table tr:eq('+i+') th').text(newUserList[i].shortName);
			$('#components div.com_newUsersList table tr:eq('+i+') td').text(newUserList[i].strIntValue);
		}
		var activeUsersList = result.activeUsersList;
		for(var i=0;i<activeUsersList.length;i++){
			$('#components div.com_activeUsersList table tr:eq('+i+') th').text(activeUsersList[i].shortName);
			$('#components div.com_activeUsersList table tr:eq('+i+') td').text(activeUsersList[i].strIntValue);
		}
		var pageViewsList = result.pageViewsList;
		for(var i=0;i<pageViewsList.length;i++){
			$('#components div.com_pageViewsList table tr:eq('+i+') th').text(pageViewsList[i].shortName);
			$('#components div.com_pageViewsList table tr:eq('+i+') td').text(pageViewsList[i].strIntValue);
		}
		var timeOfDayList = result.timeOfDayList;
		for(var i=0;i<timeOfDayList.length;i++){
			$('#components div.com_timeOfDayList table tr:eq('+i+') th').text(timeOfDayList[i].shortName);
			$('#components div.com_timeOfDayList table tr:eq('+i+') td').text(timeOfDayList[i].strValue);
		}
		var topCountriesList = result.topCountriesList;
		for(var i=0;i<topCountriesList.length;i++){
			$('#components div.com_topCountriesList table tr:eq('+i+') th').text(topCountriesList[i].shortName);
			$('#components div.com_topCountriesList table tr:eq('+i+') td').text(topCountriesList[i].country3);
		}
	}
}

//-- AD CHANNEL SNAPSHOT

function makeAdChannelSnapshot(data,returnedUsersTitle){
	$('#adChannelTerm').val(data.adChannel.fromDate + ' ~ ' + data.adChannel.toDate);
	if(data.data == true){
		$('#adChannel').removeClass('pannel_position');
		$('.ad_pannel').hide();
	}else{
		$('#adChannel').addClass('pannel_position');
		$('.ad_pannel').show();

	}

	$('.clicksSum').text(data.strClicksSum);
	$('.clicksAvg').text(data.strClicksAvg);
	$('.installsSum').text(data.strInstallsSum);
	$('.installsRate').text(data.installsRate);
	$('.newUsersSum').text(data.strNewUsersSum);
	$('.newUsersRate').text(data.newUsersRate);
	$('.activeUsersSum').text(data.strActiveUsers);
	$('.activeUsersAvg').text(data.strActiveUsersAvg);
	$('.returnedUsers').text(data.strReturnedUsers);
	$('.returnedUsersRate').text(data.returnedUsersRate);
	$('.returnedUsersTitle').text(returnedUsersTitle);
}

//TimeZone 설정-------------------------------------------------------------------------------------
function makeTodayTimeZone(nowTime, prevTime){
	var timecheck = -(new Date().getTimezoneOffset()/60); // 표준시
	var koreaTime = 9;
	var distinctionTime = Math.floor(timecheck - koreaTime); // 한국 표준시 - 클라이언트 표준시
	var timeZoneText = "";
	var timeZone = jstz.determine();
	timecheck = Math.floor(timecheck);
	if( typeof(timeZone) != 'undefined'){
		if( timecheck < 0)		timeZoneText = "(UTC - "+Math.abs(timecheck)+":00)<br/> "+timeZone.name();
		else 	timeZoneText = "(UTC + "+timecheck+":00)<br/> "+timeZone.name();
	}else{
		timeZoneText = "(UTC + 09:00) <br/>Asia/Seoul";
	}
	
	$('#timezone').attr('title',timeZoneText);
	$('.this_week_Info').tooltip({html:true});

	if( distinctionTime != 0){
		if( distinctionTime > 0){ // 양수
			nowTime = nowTime + distinctionTime;
			prevTime = prevTime + distinctionTime;
			if ( nowTime > 23)	nowTime = nowTime - 24;
			if ( prevTime > 23)	prevTime = prevTime - 24;
		}else{
			nowTime = nowTime + distinctionTime;
			prevTime = prevTime + distinctionTime;
			if ( nowTime < 0)  nowTime = 24 + nowTime;
			if ( prevTime < 0)  prevTime = 24 + prevTime;
		}
	}
	if ( nowTime < 10 )  nowTime = "0"+nowTime;
	if ( prevTime < 10 )  prevTime = "0"+prevTime;
	$(".nowTime").text(nowTime);
	$(".prevTime").text(prevTime);
}

