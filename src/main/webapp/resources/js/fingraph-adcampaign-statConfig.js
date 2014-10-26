var data_array;
var figures;
var campaignMinDate;
var campaignMaxDate;

$(function() {
	period = $("#period").msDropdown().data("dd").set("disabled", true);
	if($("#term").msDropdown().data("dd")!=null)
		term = $("#term").msDropdown().data("dd").set("disabled", true);
	//$("#calImg")
	
	//segment setting
	segment = $("#segment").msDropdown({
		on:{change: function(data, ui){getFingraphData();}}
	}).data("dd");
	
	//selectValue setting
	if ($("#selectValue")!=null) {
		selectValue = $("#selectValue").msDropdown({
			on:{change: function(data, ui){getFingraphData();}}
		}).data("dd");
	}
});

function settingFromTo(fromDate,toDate){
	if ((fromDate==null||fromDate=='')
			&& (toDate==null||toDate=='')) {
		$('#fromTo').val(' ~ ');
		$('#from').val('');
		$('#to').val('');
		return;
	}
	
	campaignMinDate = fromDate;
	campaignMaxDate = toDate;

	$('#from, #to').datepick({
		renderer: $.datepick.themeRollerRenderer,
	    //showTrigger: '#calImg',
    	firstDay:1,	//달력 월요일부터 시작
		dateFormat:'yyyy-mm-dd',
		minDate: campaignMinDate,//fingraph 시작일
		maxDate: campaignMaxDate,//오늘
		showOnFocus: false,//text클릭 안되게
		onSelect: customRange,
		onClose: function() {
			getFingraphData();
		}
	});

	var to;
	//if(moment().isSame(toDate)){
	//	to = moment().subtract('days', 1).format('YYYY-MM-DD');
	//}else if(moment().isBefore(toDate)){
	//	to = moment().subtract('days', 1).format('YYYY-MM-DD');
	//}else{
		to = moment(toDate).format('YYYY-MM-DD');	//ad campaign 통계view 에서는 날짜조정 불필요
	//}
	var from = fromDate;

	$('#fromTo').val(from +' ~ '+to);
	$('#from').val(from);
	$('#to').val(to);

	//달력의 from to 기간체크세팅
	$('#to').datepick('option', 'minDate', fromDate || null);
	$('#from').datepick('option', 'maxDate', toDate || null);
	$('#periodNext').attr('src',$('#periodNext').attr('src').replace('_active','_inactive')).removeClass('hand').attr('title','');
	$('#periodPrev').attr('src',$('#periodPrev').attr('src').replace('_active','_inactive')).removeClass('hand').attr('title','');
}

//달력과 연동되는 from to 기간체크세팅
function customRange(dates) {
    if (this.id == 'from') {
        $('#to').datepick('option', 'minDate', campaignMinDate || null);
    }
    else {
        $('#from').datepick('option', 'maxDate', campaignMaxDate || null);
    }
    $('#fromTo').val($('#from').val() +' ~ '+$('#to').val());
}

//chart 표시되는 기간계산
function makeSubTitle(term, fromTo){
	//console.log(fromTo);
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

function displaySummaryTable(target,summary) {
	// 타이틀
	if (target != null) {
		if (target.type=='R')
			$('#targetname').text('Revenue New Buyer');
		else if (target.type=='C')
			$('#targetname').text(target.eventname);
	}
	// 헤더만 남기고 전부 삭제
	while($("#summary_table tr").length > 1) {
		$("#summary_table tr").last().remove();
	}
	if(summary == null) return;
	// make table
	for(var i = 0 ; i<summary.length ; i++) {
		var html = "";
		var row = summary[i];

		html += '<tr>\n';
		html += '	<th>'+row.name+'</th>\n';
		html += '   <td>'+row.impression+'</td>\n';
		html += '   <td>'+row.clicks+'</td>\n';
		html += '   <td>'+row.ctr+'</td>\n';
		html += '   <td>'+row.convs+'</td>\n';
		html += '   <td>'+row.convs_rate+'</td>\n';
		html += '   <td>'+row.reach+'</td>\n';
		html += '   <td>'+row.reach_rate+'</td>\n';
		html += '   <td>'+row.budget+'</td>\n';
		html += '   <td>'+row.convs_cost+'</td>\n';
		html += '   <td>'+row.reach_cost+'</td>\n';
		html += '</tr>\n';
		$("#summary_table").append(html);
		$("#summary_table tbody tr:odd").addClass('m1');
		$("#summary_table tbody tr:even").addClass('m2');
	}
}
