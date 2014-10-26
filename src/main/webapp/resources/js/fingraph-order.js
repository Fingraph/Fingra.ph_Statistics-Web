//var invalidRedeemCodeFormatMsg = "Invalid redeem code. Please try again.";
//var notApplicableRedeemCodeMsg = "적용 불가능한 코드입니다. 사이트 관리자에게 문의해 주십시오.";
//var purchaseConfirmHeading = 'Continue CheckOut';
//var purchaseConfirmText = '결제금액보다 입력하신 리딤코드의 금액어 더 큽니다. 주문 내역을 조정하지않고 결제를 진행하시겠습니까?';
//var purchaseConfirmOkBtnTxt = 'OK';
//var purchaseConfirmCancelBtnTxt = 'Cancel';
// 다국어 번역으로 인해 호출 페이지에서 정의해야함
$(function() {
	  $("#redeemCodeApplyBtn").click(function(event){
		  event.preventDefault();
		  var redeemCode = $('#redeemCodeApply').val();
		  var redeemCodeRex = /^[0-9a-zA-Z-_]{12}$/;
		  if(!redeemCodeRex.test(redeemCode)){
			  showSlideMsgBox('error',invalidRedeemCodeFormatMsg,'5sec');
			  return;
		 }
		 $.getJSON(context+"order/checkCoupon.json", {redeemCode:redeemCode},function(data, textStatus) {
			 if(data.isAvail){
				 $("#redeemCode").val(data.redeemCode);
				 $("#redeemAmount").val(data.campaign.amount);
				 $("#redeemAmountTxt").text("-$"+data.campaign.amount);
				 $("#redeemExpire").text("Expires on "+data.expireDateStr);
				$(".reedeemresult").show();
			 }else{
				 showSlideMsgBox('error',notApplicableRedeemCodeMsg,'5sec');
			 }
		 });
	  });

	  $("#redeemCancelBtn").click(function(event){
		  event.preventDefault();
			 $("#redeemCode").val('');
			 $("#redeemAmount").val(0);
			 $("#redeemAmountTxt").text('');
			 $("#redeemExpire").text('');
			$(".reedeemresult").hide();
	  });

	  $("#purchaseForm").submit(function(event){

		  	var totalGross = parseFloat($('#totalGross').val());
		  	var redeemAmount = parseFloat($('#redeemAmount').val());
		  if($("#redeemCode").val()){
			  if(redeemAmount > totalGross){
				    event.preventDefault();
					confirm(purchaseConfirmHeading, purchaseConfirmText, purchaseConfirmCancelBtnTxt, purchaseConfirmOkBtnTxt, function(){
						$("#purchaseForm").unbind('submit').submit();
					});
			  }
		  }
	  });
});


