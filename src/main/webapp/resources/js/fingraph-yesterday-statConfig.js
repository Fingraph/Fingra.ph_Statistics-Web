
$(function() {
	$('#from, #to').datepick({
		renderer: $.datepick.themeRollerRenderer,
		onDate: validationCheckByTerm,
	    showTrigger: '#calImg',
    	firstDay:1,	//Start week from Monday.
		dateFormat:'yyyy-mm-dd',
		minDate: '2014-01-01',//This date have to set with start date of the service.
		maxDate: -1,//Yesterday is last day to select.
		showOnFocus: false,//Text can not be clicked.
		onSelect: customRange,
		onClose: function() {
			   setPeriodCookie($('#from').val(),$('#to').val(),$('#period').val());
			 }
	  });
	//Previous button will be work when document have 'hand' class.
	$('#periodPrev').click(function(){
		if($(this).hasClass('hand')){
			movePriedRange('prev');
		}
	});
	//Next button will be work when document have 'hand' class.
	$('#periodNext').click(function(){
		if($(this).hasClass('hand')){
			movePriedRange('next');
		}
	});

});
//Move period function
function movePriedRange(target){
	//custom setting
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

	//from-to period setting by datepicker.
	$('#to').datepick('option', 'minDate', moveFrom || null);
	$('#from').datepick('option', 'maxDate', moveTo || null);

	setPeriodCookie(moveFrom,moveTo,$('#period').val());

}

//from-to date setting by datepicker.
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
		  //set a expiration date to yesterday.
		  expireDate.setDate( expireDate.getDate() - 1 );
		  document.cookie = "fingraphPeriod= " + "; expires=" + expireDate.toGMTString() + "; path=/";

	}
	//Set values on the previous and next button.
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
     	//Set from-to date. 
     	$('#to').datepick('option', 'minDate', fromTo[0] || null);
 		//Set values on the previous and next button.
 	    setPrevNextBtn($('#from').val(),$('#to').val());
        getFingraphData();
    }else{
    	period.setIndexByValue('7-d');
    	addPeriods('7-d');

    }

}
//Function to set values on the previous and next button.
function setPrevNextBtn(from,to){
	var minDate = moment('2014-01-01');//This date have to set with start date of the service.
	var maxDate = moment().subtract('d', 1);//yesterday()
	//Get 'from' and 'to' date from moment
	var mfrom = moment(from);
	var mto = moment(to);
	//difference between 'from' and 'to' date
	var periodDiffDays =  mto.diff(mfrom,'days');

	//Check previous button.
	if(mfrom.diff(minDate,'days') > periodDiffDays){
		$('#periodPrev').attr('src',$('#periodPrev').attr('src').replace('_inactive','_active')).addClass('hand').attr('title','Previous period');
	}else{
		$('#periodPrev').attr('src',$('#periodPrev').attr('src').replace('_active','_inactive')).removeClass('hand').attr('title','');
	}
	//Check next button.
	if(maxDate.diff(to,'days') > periodDiffDays){
		$('#periodNext').attr('src',$('#periodNext').attr('src').replace('_inactive','_active')).addClass('hand').attr('title','Next period');
	}else{
		$('#periodNext').attr('src',$('#periodNext').attr('src').replace('_active','_inactive')).removeClass('hand').attr('title','');
	}
}

//Adjust from-to date by term selection.
function adjustDatesByTerm() {
	//Check period value is custom or not.
	var periodVal = $("#period").val();
	var type = periodVal.split('-');
	if(type[1]=='d' || type[1]=='m' || type[1]=='y'){
		addPeriods(periodVal);
		return;
	}
	
	var term = $('#term').val();
	var to = $('#to').val();
	var from = $('#from').val();
	
	if(term=='daily'){
		return;
	}else if(term=='weekly'){
		to = moment(to).endOf('week');
		to = moment(to).add('day', 1);
		if (moment().isBefore(to)) {
			to = moment(to).subtract('week', 1);
			from = moment(from).subtract('week', 1);
		}
		from = moment(from).startOf('week');
		from = from.add('day', 1);
	}else if(term=='monthly'){
		to = moment(to).endOf('month');
		if (moment().isBefore(to)) {
			to = moment(to).subtract('month', 1);
			to = moment(to).endOf('month');
			from = moment(from).subtract('month', 1);
		}
		from = moment(from).startOf('month');
	}

	$('#fromTo').val(from.format('YYYY-MM-DD') +' ~ '+to.format('YYYY-MM-DD'));
	$('#from').val(from.format('YYYY-MM-DD'));
	$('#to').val(to.format('YYYY-MM-DD'));

	//Set from-to date of date-picker. 
	$('#to').datepick('option', 'minDate', from || null);

	setPeriodCookie($('#fromTo').val(),$('#period').val());
}



//Set a date by value of period and term. (basis on yesterday)
function addPeriods(periodVal){
	var term = $('#term').val();
	var to = moment().subtract('day', 1);
	if(term=='weekly'){
		to = moment().subtract('week', 1);
		to = moment(to).endOf('week');
		to = moment(to).add('day', 1);
	}else if(term=='monthly'){
		to = moment().subtract('month', 1);
		to = moment(to).endOf('month');
	}
	
	var type = periodVal.split('-');
	var from = "";
	
	if(type[1]=='d'){
		from = moment(to).subtract('day', eval(type[0])-1);
	}else if(type[1]=='m'){
		from = moment(to).subtract('month', eval(type[0])-1);
	}else if(type[1]=='y'){
		from = moment(to).startOf('year');
	}else{
		$('.calImg').css('display','');
		return;
	}
	
	if(term=='weekly'){
		from = moment(from).startOf('week');
		from = from.add('day', 1);
		if(type[1]=='y' && from.year() != to.year()) from = moment(from).add('day', 7);
	} else if(term=='monthly'){
		from = moment(from).startOf('month');
	}

	$('#fromTo').val(from.format('YYYY-MM-DD') +' ~ '+to.format('YYYY-MM-DD'));
	$('#from').val(from.format('YYYY-MM-DD'));
	$('#to').val(to.format('YYYY-MM-DD'));

	//Set from-to date of date-picker. 
	$('#to').datepick('option', 'minDate', from || null);

	setPeriodCookie($('#fromTo').val(),$('#period').val());
}

//Make subtitle to show range of date.
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

//Strict day of week or day of month when user use term option "week" or "month"
function validationCheckByTerm(date) {
	//return {selectable: true, dateClass: 'highlight', title: 'tooltip'};
	var term = $('#term').val();
	var isFromPicker = (this.id == 'from');
	
	if (term == 'daily') {
		return {selectable: true};
	} else if (term == 'weekly') {
		if (isFromPicker) {
			if (date.getDay() == 1) return {selectable: true};
		} else {
			if (date.getDay() == 0) return {selectable: true};
		}
		return {selectable: false};
	} else if (term == 'monthly') {
		if (isFromPicker) {
			if (date.getDate() == 1) return {selectable: true};
		} else {
			if (isLastDayOfMonth(date)) return {selectable: true};
		}
		return {selectable: false};
	}
}

function isLastDayOfMonth(date){
	var y = date.getFullYear();
	var m = date.getMonth();
    var lastDayOfMonth = new Date(y, m + 1, 0);
	
    if (date.getDate() == lastDayOfMonth.getDate()) return true;
    else return false;
}