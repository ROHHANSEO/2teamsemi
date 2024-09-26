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
    
    if($(".userPwdCheck").val().length < 6){
        alert("비밀번호는 6자리 이상이여야 합니다");
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

    
})

