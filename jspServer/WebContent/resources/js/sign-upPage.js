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
		if ($("input[name=userPwd]").val() != $("input[name=userPwdCheck]").val()) {
			alert("비밀번호 불일치");
			$(".userPwd").val("");
			$(".userPwdCheck").val("");
			$(".userPwd").focus();
		}
	})

	$(".button").click(function() {
		if ($("#consent").is(":checked") == false) {
			alert("개인정보이용동의를 체크해주셔야 합니다");
		}
	})

})

$("#userIdCheck").click(function() {

	var userId = $(".input");

	if (userId.val() == "") {
		alert("아이디를 입력해주세요");
		return false;
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
					$("#joinBtn").removeAttr("disabled");
				} else {
					userId.focus();
				}
			}
		}
	})
})