
$(function() {
	$('#from, #to').datepick({
		renderer: $.datepick.themeRollerRenderer,
	    showTrigger: '#calImg',
    	firstDay:1,	//달력 월요일부터 시작
		dateFormat:'yyyy-mm-dd',
		minDate: '2012-03-01',//fingraph 시작일
		maxDate: -1,//어제
		showOnFocus: false,//text클릭 안되게
		onSelect: customRange,
		onClose: function() {
			   setPeriodCookie($('#from').val(),$('#to').val(),$('#period').val());
			 }
	  });
	//날짜이 prev버튼 hand class가 있을때만 이동
	$('#periodPrev').click(function(){
		if($(this).hasClass('hand')){
			movePriedRange('prev');
		}
	});
	//날짜이 next버튼 hand class가 있을때만 이동
	$('#periodNext').click(function(){
		if($(this).hasClass('hand')){
			movePriedRange('next');
		}
	});

});
//날짜 이동 함수
function movePriedRange(target){
	//사용자정의세팅
	period.setIndexByValue('c-u');
	var from = moment($('#from').val());
	var to = moment($('#to').val());
	var periodDiffDays =  to.diff(from,'days')+1;
	var moveFrom;
	var moveTo;
	if(target == 'prev'){
		moveFrom = from.subtract('days', periodDiffDays).format('YYYY-MM-DD');
		moveTo = to.subtract('days', periodDiffDays).format('YYYY-MM-DD');
	}else{
		moveFrom = from.add('days', periodDiffDays).format('YYYY-MM-DD');
		moveTo = to.add('days', periodDiffDays).format('YYYY-MM-DD');
	}

	$('#fromTo').val(moveFrom +' ~ '+moveTo);
	$('#from').val(moveFrom);
	$('#to').val(moveTo);

	//달력의 from to 기간체크세팅
	$('#to').datepick('option', 'minDate', moveFrom || null);
	$('#from').datepick('option', 'maxDate', moveTo || null);

	setPeriodCookie(moveFrom,moveTo,$('#period').val());

}

//달력과 연동되는 from to 기간체크세팅
function customRange(dates) {
    if (this.id == 'from') {
        $('#to').datepick('option', 'minDate', dates[0] || null);
    }
    else {
        $('#from').datepick('option', 'maxDate', dates[0] || null);
    }
    period.setIndexByValue('c-u');
}


function setPeriodCookie(from,to,period){
	if(period=='c-u'){
		var expire = new Date();
		var fromTo = from + ' ~ ' + to;
	    expire.setDate(expire.getDate() + 5);
	    cookies = 'fingraphPeriod=' + escape(fromTo)+'|'+escape(period) +'; path=/;'; // 한글 깨짐을 막기위해 escape(cValue)를 합니다.
	    cookies += ';expires=' + expire.toGMTString() + ';';
	    document.cookie = cookies;
	}else{
		  var expireDate = new Date();
		  //어제 날짜를 쿠키 소멸 날짜로 설정한다.
		  expireDate.setDate( expireDate.getDate() - 1 );
		  document.cookie = "fingraphPeriod= " + "; expires=" + expireDate.toGMTString() + "; path=/";

	}
	//이전, 이후 버튼 세팅 추가 2013-09-27
    setPrevNextBtn($('#from').val(),$('#to').val());
	getFingraphData();
}

function getPeriodCookie(cName) {
    cName = cName + '=';
    var cookieData = document.cookie;
    var start = cookieData.indexOf(cName);
    var cValue = '';
    if(start != -1){
         start += cName.length;
         var end = cookieData.indexOf(';', start);
         if(end == -1)end = cookieData.length;
         cValue = cookieData.substring(start, end);
         splitVal = unescape(cValue).split('|');

         $('#fromTo').val(splitVal[0]);
        	var fromTo = splitVal[0].split(' ~ ');
     		$('#from').val(fromTo[0]);
     		$('#to').val(fromTo[1]);
     		period.setIndexByValue(splitVal[1]);
     	//달력의 from to 기간체크세팅
     	$('#to').datepick('option', 'minDate', fromTo[0] || null);
 		//이전, 이후 버튼 세팅 추가 2013-09-27
 	    setPrevNextBtn($('#from').val(),$('#to').val());
        getFingraphData();
    }else{
    	period.setIndexByValue('7-d');
    	addPeriods('7-d');

    }

}
//달력의 prev, next버튼 세팅
function setPrevNextBtn(from,to){
	var minDate = moment('2012-03-01');//fingraph 시작일
	var maxDate = moment().subtract('d', 1);//어제()
	//moment변환
	var mfrom = moment(from);
	var mto = moment(to);
	//from to 의 차
	var periodDiffDays =  mto.diff(mfrom,'days');

	//prev버튼 체크
	if(mfrom.diff(minDate,'days') > periodDiffDays){
		$('#periodPrev').attr('src',$('#periodPrev').attr('src').replace('_inactive','_active')).addClass('hand').attr('title','Previous period');
	}else{
		$('#periodPrev').attr('src',$('#periodPrev').attr('src').replace('_active','_inactive')).removeClass('hand').attr('title','');
	}
	//next버튼 체크
	if(maxDate.diff(to,'days') > periodDiffDays){
		$('#periodNext').attr('src',$('#periodNext').attr('src').replace('_inactive','_active')).addClass('hand').attr('title','Next period');
	}else{
		$('#periodNext').attr('src',$('#periodNext').attr('src').replace('_active','_inactive')).removeClass('hand').attr('title','');
	}
}

//기간별 날짜세팅 (하루전기준)
function addPeriods(periodVal){
	var type = periodVal.split('-');
	var to = moment().subtract('d', 1);
	var from = "";
	if(type[1]=='d'){
		from = moment().subtract('d', eval(type[0])).format('YYYY-MM-DD');
	}else if(type[1]=='m'){
		from = moment().subtract('d', 1).subtract('M',eval(type[0])).format('YYYY-MM-DD');
	}else if(type[1]=='y'){
		from = moment().subtract('d', 1).year() + '-01-01';
	}else{
		$('.calImg').css('display','');
		return;
	}

	$('#fromTo').val(from +' ~ '+to.format('YYYY-MM-DD'));
	$('#from').val(from);
	$('#to').val(to.format('YYYY-MM-DD'));

	//달력의 from to 기간체크세팅
	$('#to').datepick('option', 'minDate', from || null);

	setPeriodCookie($('#fromTo').val(),$('#period').val());

}

/*
//기간별 날짜세팅 (오늘-실시간)
function addPeriods(periodVal){
	var type = periodVal.split('-');
	var to = moment();
	var from = "";
	if(type[1]=='d'){
		from = moment().subtract('d', eval(type[0]-1)).format('YYYY-MM-DD');
	}else if(type[1]=='m'){
		from = moment().subtract('M',eval(type[0])).format('YYYY-MM-DD');
	}else if(type[1]=='y'){
		from = moment().year() + '-01-01';
	}else{
		$('.calImg').css('display','');
		return;
	}

	$('#fromTo').val(from +' ~ '+to.format('YYYY-MM-DD'));
	$('#from').val(from);
	$('#to').val(to.format('YYYY-MM-DD'));

	//달력의 from to 기간체크세팅
	$('#to').datepick('option', 'minDate', from || null);

	setPeriodCookie($('#fromTo').val(),$('#period').val());
}
*/
//chart 표시되는 기간계산
function makeSubTitle(term, fromTo){
	var period = fromTo.split(' ~ ');
	var subTitle = '';
	if(period.length == 2){
		if(term == 'daily'){
			subTitle = moment(period[0]).format('MMM D, YYYY') +' ~ '+ moment(period[1]).format('MMM D, YYYY');
		}else if(term=='weekly'){
			subTitle = moment(moment(period[0]).isoWeekday(1)).format('MMM D, YYYY') +' ~ '+ moment(moment(period[1]).isoWeekday(7)).format('MMM D, YYYY');
		}else if(term=='monthly'){
			//subTitle = moment(moment(period[0]).startOf('month')).format('MMM D, YYYY') +' ~ '+ moment(moment(period[1]).endOf('month')).format('MMM D, YYYY');
			if(moment(period[0]).format('MMM, YYYY') == moment(period[1]).format('MMM, YYYY')){
				subTitle = moment(period[0]).format('MMM, YYYY');
			}else{
				subTitle = moment(period[0]).format('MMM, YYYY') +' ~ '+ moment(period[1]).format('MMM, YYYY');
			}
		}
		subTitle = '( '+subTitle+' )';
	}
	return subTitle ;
}



