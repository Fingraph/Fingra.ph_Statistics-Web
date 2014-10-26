$(function() {
	$('#from, #to').datepick({
		renderer: $.datepick.themeRollerRenderer,
	    showTrigger: '#calImg',
    	firstDay:1,	//달력 월요일부터 시작
		dateFormat:'yyyy-mm-dd',
		minDate: '2012-03-01',//fingraph 시작일
		maxDate: 0,//오늘
		showOnFocus: false,//text클릭 안되게
		onSelect: customRange
	  });
	//날짜이 prev버튼 hand class가 있을때만 이동
	$('#periodPrev').click(function(){
		if($(this).hasClass('hand')){
			movePriodRange('prev');
		}
	});
	//날짜이 next버튼 hand class가 있을때만 이동
	$('#periodNext').click(function(){
		if($(this).hasClass('hand')){
			movePriodRange('next');
		}
	});

    setPrevNextBtn($("#finraphSearchParam #today").val());
});

//달력의 prev, next버튼 세팅
function setPrevNextBtn(from){
    var minDate = moment('2012-03-01');//fingraph 시작일
    var maxDate = moment();//오늘()
    //moment변환
    var mfrom = moment(from);

    //prev버튼 체크
    if(mfrom.diff(minDate,'days') > 0){
        $('#periodPrev').attr('src',$('#periodPrev').attr('src').replace('_inactive','_active')).addClass('hand').attr('title','Previous period');
    }else{
        $('#periodPrev').attr('src',$('#periodPrev').attr('src').replace('_active','_inactive')).removeClass('hand').attr('title','');
    }
    //next버튼 체크
    if(mfrom.diff(maxDate,'days') < 0){
        $('#periodNext').attr('src',$('#periodNext').attr('src').replace('_inactive','_active')).addClass('hand').attr('title','Next period');
    }else{
        $('#periodNext').attr('src',$('#periodNext').attr('src').replace('_active','_inactive')).removeClass('hand').attr('title','');
    }
}

//날짜 이동 함수
function movePriodRange(target){
	var from = moment($('#from').val());
    var today = $("#fingraphSearchParam #today");
	var periodDiffDays =  1;
	var moveFrom;
	var moveTo;
	if(target == 'prev'){
		moveFrom = from.subtract('days', periodDiffDays).format('YYYY-MM-DD');
	}else{
		moveFrom = from.add('days', periodDiffDays).format('YYYY-MM-DD');
	}
	$('#from').val(moveFrom);
    today.val(moveFrom);

    if (from.diff(moment(),'days') == 0){
        // today
        $("#nowTime").val(moment().format("HH"));
    }
    else {
        $("#nowTime").val('24');
    }


	//달력의 from to 기간체크세팅
	$('#from').datepick('option', 'maxDate', moveTo || null);

    setPrevNextBtn($("#from").val());
    getFingraphData();
}

//달력과 연동되는 from to 기간체크세팅
function customRange(dates) {
    if (this.id == 'from') {
    	var fromDate = moment(dates[0]).format( 'YYYY-MM-DD' );
        var from = moment(fromDate);
        var today = $("#fingraphSearchParam #today");
        today.val(fromDate);
        $('#from').val(fromDate);
        setPrevNextBtn($("#from").val());

        if (from.diff(moment(),'days') == 0){
            // today
            $("#nowTime").val(moment().format("HH"));
        }
        else {
            $("#nowTime").val('24');
        }
        getFingraphData();
    }
}
