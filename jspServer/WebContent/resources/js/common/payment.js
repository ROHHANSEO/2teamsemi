function requestPay() {
    var IMP = window.IMP; //변경x
    IMP.init('imp35302156');  //변경x
      // IMP.request_pay(param, callback) 결제창 호출
    IMP.request_pay({ // param
        pg: "html5_inicis",//변경x
        pay_method: "card",//변경x
        merchant_uid: 'merchant_' + new Date().getTime(),//변경x
        name: $(".product_name").text(),//게시판 제목
        amount: $(".product_price").text(),//가격
        buyer_email: <%=user.getEmail%>,
        buyer_name: <%=user.getUserId%>,
        buyer_tel: <%=user.getPhone%>
    }, function (rsp) { // callback
        if (rsp.success) {
        
	        $.ajax({
	          url : "Itempayment",
	          type : "post",
	          data : {
	            imp_uid: rsp.imp_uid,
	            merchant_uid: rsp.merchant_uid,
	            boardNo : boardNo,
	            buyerNo : buyerNo,
	            amount : rsp.amount,
	          },
	          success : function(){
	            var msg = '결제가 완료되었습니다.';
	          }
	        })

        } else {
          var msg = '결제에 실패하였습니다.';
          msg += '에러내용 : ' + rsp.error_msg;
        }
        alert(msg);
    });
  }

