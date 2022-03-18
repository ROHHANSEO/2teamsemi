$("input[name=userPwdCheck]").on("focusout",function(){
    if($("input[name=userPwdCheck]"))
    if($("input[name=userPwd]")===$("input[name=userPwdCheck]")){
        $("input[name=userPwd]").addClass("pwd_check");
        $("input[name=userPwdCheck]").addClass("pwd_check");
    } else {
        alert("비밀번호가 틀립니다");
    }
})