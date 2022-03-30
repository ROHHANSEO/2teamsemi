$(function() {
	//비밀번호, 비밀번호 6자리 이상이고 12자리 이하일때 스크립트
	$("input[type=password]").on("keyup", function() {
		if ($("input[type=password]").val().length >= 6 && $("input[type=password]").val().length <= 12) {
			console.log($("input[name=userPwd]").val().length)
			$(this).css("border-color", "blue");
		} else {
			$(this).css("border-color", "red");
			$(this).css("outline", "2px red");
		}
	})

	//비밀번호, 비밀번호 확인 불일치 할때 스크립트
	$(".userPwdCheck").blur(function() {
        var check_num = /[0-9]/; // 숫자
        var check_eng = /[a-zA-Z]/; // 문자
        var check_spc = /[~!@#$%^&*()_+|<>?:{}]/; // 특수문자
        var check_kor = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/; // 한글체크

		if ($("input[name=userPwd]").val() != $("input[name=userPwdCheck]").val()) {
			alert("비밀번호 불일치");
			$(".userPwd").val("");
			$(".userPwdCheck").val("");
			$(".userPwd").focus();
		}

        if(check_spc.test($("input[name=userPwd]").val()) || check_kor.test($("input[name=userPwd]").val())) {
            alert("비밀번호는 특수문자, 한글은 사용불가합니다.");
            $(".userPwd").val("");
			$(".userPwdCheck").val("");
			$(".userPwd").focus();
        }

		if($(".userPwdCheck").val().length < 6){
			alert("비밀번호는 6자리 이상이여야 합니다");
            $(".userPwd").val("");
			$(".userPwdCheck").val("");
			$(".userPwd").focus();
		}
        
	})

	$(".button").click(function() {
		if ($("#consent").is(":checked") == false) {
			alert("개인정보이용동의를 체크해주셔야 합니다");
            return;
		}

        if($("input[name=userPwd]").val().length < 6 && $("input[name=userPwdCheck]").val().length < 6){
            alert("비밀번호는 6자리 이상으로 해주세요");
            return;
        }

        $("form").submit();
	})

})

$("#userIdCheck").click(function() {

    var check_num = /[0-9]/; // 숫자
    var check_eng = /[a-zA-Z]/; // 문자
    var check_spc = /[~!@#$%^&*()_+|<>?:{}]/; // 특수문자
    var check_kor = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/; // 한글체크



	var userId = $(".input");

	if (userId.val() == "") {
		alert("아이디를 입력해주세요");
		return;
	}

    if(check_spc.test(userId.val()) || check_kor.test(userId.val())){
        alert("아이디는 영어, 숫자만 입력가능합니다");
        $("#idid").focus();
        userId.val("");
        return;
    }
	$.ajax({
		url: "idCheck",
		type: "post",
		data: { userId: userId.val() },
		success: function(result) {
			if (result == "fail") {
				alert("사용할수없는 아이디입니다.");
			} else {
				if (confirm("사용가능한 아이디입니다. 사용하시겠습니까?")) {
					userId.attr("readonly", "true");
					$(".button").removeAttr("disabled");
				} else {
					userId.focus();
				}
			}
		}
	})
})