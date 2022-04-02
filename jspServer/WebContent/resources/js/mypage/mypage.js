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

            if(bno >= 1000000 && bno <= 1999999){
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
            } else if(bno >= 2000000 && bno <= 2999999){
                $.ajax({
                    url : "actionRecordDelete",
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
        var check;
        $(".select_delete_checkbox:checked").each(function(){
            check = $(this).val();
            bnoArr.push($(this).val());
        })

        if(check >= 1000000 && check <= 1999999){
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
        } else if(check >= 2000000 && check <= 2999999){
            $.ajax({
                url : "actionSelectDelete",
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
        }
    })

    //전체삭제
    $("#record_All_delete").click(function(){
        if($(this).next().val() == "1"){
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
        } else if($(this).next().val() == "2"){
            $.ajax({
                url : "actionAlldelete",
                type : "get",
                data : {a : "a"},
                success : function(){
                    location.reload();
                    if(result == "fail"){
                        alert("기록 삭제에 실패하였습니다.");
                    }
                }
            })
        }
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

        
        let arr={
            userNo : $("input[name=userNo]").val(),
            userId : $("input[name=userId]").val(),
            userPwd : $("input[name=userPwd]").val(),
            userName : $("input[name=userName]").val(),
            citiNo : $("input[name=citiNo]").val(),
            phoneNo : $("input[name=phoneNo]").val(),
            nickName : $("input[name=nickName]").val(),
            email : $("input[name=email]").val(),
            gender : $("input[name=gender]").val(),
        };

        //비밀번호 입력확인, 입력이 있다면 입력과 입력확인이 같은지 확인후 변수에 저장
        if($(".userPwd").val() !='' && $(".userPwdCheck").val() !=''){
            if($(".userPwd").val() == $(".userPwdCheck").val()){
                arr.userPwd = $(".userPwd").val();
            } else {
                alert("비밀번호가 일치하지않습니다 다시 입력해주세요");
                return;
            }
        }

        if($(".nick_name").val() != ''){
            arr.nickName = $(".nick_name").val();;
        }

        if($(".phone").val() != ''){
            arr.phone = $(".phone").val();

        }

        if($(".email").val() != ''){
            arr.email = $(".email").val();
        }

        var jsonArr = JSON.stringify(arr);

        if($(".nick_name").val() != '' ||$(".phone").val() != ''||$(".email").val() != '' ||($(".userPwd").val() != '' && $(".userPwdCheck").val() != '')){
            $.ajax({
                url : "userUpdate",
                type : "post",
                data : {jsonArr : jsonArr},
                success : function(result){
                    console.log(result);
                    if(result == "fail"){
                        alert("회원정보 변경에 실패하였습니다.");
                        $("#edit_modal_container2").hide();
                        $("#edit_modal_container2 input").val("");
                    } else if(result == "passwordsuccess"){
                        alert("변경되었습니다. 다시 로그인 해주세요");
                        location.href="/";
                    } else {
                        alert("변경되었습니다.");
                        $("#edit_modal_container2").hide();
                        $("#edit_modal_container3").fadeIn(200);
                        $("#edit_modal_container2 input").val("");
                        location.reload();
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

    //관리자 코드 입력
    $(".admin_code").click(function(){
        $("#admin_code_modal1").fadeIn(200);
    })

    $(".admin_code_next_btn").click(function(){
        if($("#admin_code_modal1 input[type=hidden]").val() == $("#admin_code_modal1 input[type=password]").val()){
            
            $.ajax({
                url : "adminUpdate",
                data : {a : "a"},
                success : function(result){
                    if(result == "fail"){
                        alert("실패하였습니다. 관리자에게 문의해주세요");
                    } else {
                        $("#admin_code_modal1").hide();
                        $("#admin_code_modal2").fadeIn(200);
                        $("#admin_code_modal1 input[type=password]").val("")
                    }
                }
            })
        } else {
            alert("비밀번호 불일치 합니다 다시 입력해주세요");
            $("input[type=password]").val("")
        }
    })
    
    $(".admin_code_cancel_btn").click(function(){
        $("#admin_code_modal1").hide();

    })
    
    $(".admin_code_modal_btn").click(function(){
        $("#admin_code_modal2").hide();
        location.href="/";
    })


    //찜리스트 찜해제, 재설정기능
    $(".heart_img_box").click(function() {
			
        let like = $(this)
        let bNo = $(this).children(".bNo").val();
        let uNo = $(this).children(".uNo").val();
        
        // colorchange 클래스가 존재하면(찜하지 않았을 시)
        if(like.hasClass('colorchange')){
            
            // sql 문에 찜을 -1
            $.ajax({
                url:'notLike.do',	
                data:{
                    bNo:bNo,
                    uNo:uNo
                },
                type:'get',
                success:function(){
                    
                    // 클래스를 지운다
                    like.removeClass('colorchange')
                }
            })
        }else{ // 존재하지 않으면
            
            // sql문에 찜을 +1
            $.ajax({
                url:'like.do',
                data:{
                    bNo:bNo,
                    uNo:uNo
                },
                type:'get',
                success:function(){
                    
                    // 클래스를 추가한다
                    like.addClass('colorchange')
                }
            })
        }
    })

