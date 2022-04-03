function requestPay() {
	
	var buyerNo = $(".uNo").text();
    var boardNo = $(".bNo").text();
    var price = $(".product_price").val();
    alert(price);
    
    var IMP = window.IMP; //변경x
    IMP.init('imp35302156');  //변경x
      // IMP.request_pay(param, callback) 결제창 호출
    IMP.request_pay({ // param
        pg: "html5_inicis",//변경x
        pay_method: "card",//변경x
        merchant_uid: 'merchant_' + new Date().getTime(),//변경x
        name: $(".product_name").text(),//게시판 제목
        amount: price,//가격
        buyer_email: "asd",
        buyer_name: "aaa",
        buyer_tel: "as"
    }, function (rsp) {// callback
        if (rsp.success) {

	        $.ajax({
	          url : "Itempayment",
	          type : "post",
	          data : {
	            imp_uid: rsp.imp_uid,
	            merchant_uid: rsp.merchant_uid,
	            boardNo : boardNo,
	            buyerNo : buyerNo,
	            price : price,
	          },
	          success : function(){
                alert('결제가 완료되었습니다.')
                $(".buyme").attr("disabled", true);
                $(".buyme").css("background","lightgray");
                $(".buyme").css("color","black");
                if(boardNo > 999999 || boardNo <2000000){
                donttouch(boardNo);
                }
                if(boardNo > 1999999 || boardNo <3000000){
                youdonthavethisfun(boardNo);
                }
	          },


	        })

        } else {
          var msg = '결제에 실패하였습니다.';
          msg += '에러내용 : ' + rsp.error_msg;
          alert(msg);
        }

    });
  }

	function youdonthavethisfun(boardNo){
		clearTimeout(timerId);
		//경매 종료로 내용 변경
		$("p.time-title").html("경매 종료");
		//옆에 시간 부분 사라지게 하기
		$(".time").fadeOut();
		
		$.ajax({
			url:"updateAuctionStatus.do", 
			data:{boardNo:boardNo}, 
			type:"get", 
			success: function(change){
			var changeSS = $(".fronta")
			changeSS.empty();
			changeSS.append(change)
			}
			
		})
		
	}
	
	function donttouch(boardNo){
		console.log("바꾸러 가자")
		$.ajax({
			url:"updateUsedBoardStatus.do", 
			data:{boardNo:boardNo, payments:"거래완료"}, 
			type:"get", 
			success: function(payment){
			let changestatus = $("#transaction")
			changestatus.empty();
			changestatus.append(payment)
			changestatus.addclass("colorchangego")
			}
			
		})
		
	}