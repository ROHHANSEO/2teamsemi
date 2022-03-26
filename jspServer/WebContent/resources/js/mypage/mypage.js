$(function(){

    //상태변경 스크립트
    $(".status_swap").click(function(){
        if($(this).next().hasClass("action")){
            $(this).next().removeClass("action");
        } else {
            $(this).next().addClass("action");
        }
    })

    //상태변경 ajax
    $(".status_level").click(function(){
        var status = $(this).children().text();
        var bno = $(this).parents(".content_box").children("input").val();

        $.ajax({
            url : "statusSwap",
            type : "get",
            data :{
                status : status,
                bno : bno
            },
            success : function(result){
                if(result == "fail"){
                    alert("변경에 실패 하였습니다.");
                } else if(result == "success") {
                    $(".status"+bno).text(status);
                    $(".action").removeClass("action");
                }
            }
        })
    })

    //단일 기록 삭제
    $(".content_delete").click(function(){
        var bno = $(this).parents(".content_box").children("input").val();
        if(confirm("정말 삭제하시겠습니까?")){
            $.ajax({
                url : "recordDelete",
                type : "get",
                data :{bno : bno},
                success : function(result){
                    location.reload();
                    if(result == "fail"){
                        alert("변경에 실패 하였습니다.");
                    }
                }
            })
        }
    })

    //기록삭제 버튼 스크립트
    $("#record_delete").click(function(){
        $(this).css("display","none");
        $("#delete_btn_box").css("display","block");
        $(".select_delete_checkbox").css("display","block")
    })

    //취소버튼 스크립트
    $("#delete_cancel").click(function(){
        $("#delete_btn_box").css("display","none");
        $(".select_delete_checkbox").css("display","none")
        $("#record_delete").css("display","block");
    })

    //선택삭제
    $("#record_select_delete").click(function(){
        var bnoArr = [];
        $(".select_delete_checkbox:checked").each(function(){
            bnoArr.push($(this).val());
        })

        $.ajax({
            url : "selectDelete",
            type : "post",
            traditional : true,
            data : {bnoArr : bnoArr},
            success : function(){
                location.reload();
                if(result == "fail"){
                    alert("기록 삭제에 실패하였습니다.");
                }
            }
        })
    })

    //전체삭제
    $("#record_All_delete").click(function(){
        $.ajax({
            url : "Alldelete",
            type : "get",
            data : {a : "a"},
            success : function(){
                location.reload();
                if(result == "fail"){
                    alert("기록 삭제에 실패하였습니다.");
                }
            }
        })
    })

    //높이조절
    var count = $(".content_box").length;
    if(count > 0){
        var height = count*300+100;
    
        $("#salescontent_box").css("height",height);
        $("#mypage_list_form").css("height",height);
    } else {
        $("#salescontent_box").css("height","600px");
        $("#mypage_list_form").css("height","600px");
    }

})
    //모달 숨김
    $(".modal_view").hide();

    //개인정보수정 모달창 fade-in
    $(".edit").click(function(){
        $("#edit_modal_container1").fadeIn(200);
    })

    //개인정보수정 모달창 fade-out
    $(".edit1_cancel_btn").click(function(){
        $(".modal_view").fadeOut(200);
    })

    //다음 버튼 클릭시 현재 로그인된 계정의 비밀번호와 비교후 맞을경우 다음 모달 fadeIn
    $(".edit1_next_btn").click(function(){
        if($("input[type=hidden]").val() == $("input[type=password]").val()){
            $("#edit_modal_container1").hide();
            $("#edit_modal_container2").fadeIn(200);
            $("#edit_modal_container1 input[type=password]").val("")
        } else {
            alert("비밀번호 불일치 합니다 다시 입력해주세요");
            $("input[type=password]").val("")
        }
    })

    //개인정보수정 바꿀 내용 입력후 다음버튼 클릭시 ajax실행 결과값에 따라 ui출력
    $(".edit_submit_btn").click(function(){

        
        let arr={};
        //비밀번호 입력확인, 입력이 있다면 입력과 입력확인이 같은지 확인후 변수에 저장
        if($(".userPwd").val() !='' && $(".userPwdCheck").val() !=''){
            if($(".userPwd").val() == $(".userPwdCheck").val()){
                var userPwd = $(".userPwd").val();
                arr.userPwd = userPwd;
            } else {
                alert("비밀번호가 일치하지않습니다 다시 입력해주세요");
                return;
            }
        }

        if($(".nick_name").val() != ''){
            var nickName = $(".nick_name").val();
            arr.nickName = nickName;
        }

        if($(".phone").val() != ''){
            var phone = $(".phone").val();
            arr.phone = phone;

        }

        if($(".email").val() != ''){
            var email = $(".email").val();
            arr.email = email;
        }

        console.log(Object.keys(arr).length > 0);
        if(Object.keys(arr).length > 0){
            $.ajax({
                url : "userUpdate",
                type : "post",
                contentType : "application/json; charset=utf-8",
                data : JSON.stringify(arr),
                success : function(result){
                    if(result == "success"){
                        $("#edit_modal_container2").hide();
                        $("#edit_modal_container3").fadeIn(200);
                        $("#edit_modal_container2 input").val("");
                    } else {
                        alert("회원정보 변경에 실패하였습니다.");
                    }
                }
            })
        } else {
            alert("입력해주세요")
            $("#edit_modal_container2 input").val("");
        }
    })


    //회원탈퇴 모달창 오픈
    $(".secession").click(function(){
        $("#secession_modal_container1").fadeIn(200);
    })

    $(".secession_next_btn").click(function(){
        if($("#secession_modal_container1 input[type=hidden]").val() == $("#secession_modal_container1 input[type=password]").val()){
            $(".modal_view").hide();
            $("#secession_modal_container2").fadeIn(200);
            $("#secession_modal_container1 input[type=password]").val("")
        } else {
            alert("비밀번호 불일치 합니다 다시 입력해주세요");
            $("input[type=password]").val("")
        }
    })

    $(".secession_true_btn").click(function(){
        $.ajax({
            url : "deleteUser",
            data :{aaa:"aaa"},
            type : "get",
            success : function(result){
                if(result == "success"){
                    $(".modal_view").hide();
                    alert("탈퇴되었습니다.");
                    location.href="/";
                } else {
                    alert("탈퇴 처리가 되지 않았습니다.");
                }
            }
        })
    })