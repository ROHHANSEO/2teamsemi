<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    #secession_modal_container1 {
        background-color: rgba(0, 0, 0, 0.5);
        position: absolute;
        top: 0;
        left: 0;
        bottom: 0;
        right: 0;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    #secession_modal_container1 #modal_box {
        width: 400px;
        background-color: #fff;
        padding: 20px 0 20px 0;
        border-radius: 30px;
    }

    #modal_box .modal_header {
        background-color: #993333;
        color: #fff;
        text-align: center;
    }

    #secession_modal_container1 .modal_header .modal_title {
        font-size: 30px;
    }

    #secession_modal_container1 #modal_box .modal_body {
        height: 400px;
        position: relative;
    }

    #secession_modal_container1 .modal_body .pwd_text {
        display: block;
        font-size: 20px;
    }

    #secession_modal_container1 .modal_body .content_box {
        position: absolute;
        top: 150px;
        left: 30px;
    }

    #secession_modal_container1 .content_box input[type=password]{
        margin-top: 30px;
        width: 300px;
        height: 25px;
    }

    #secession_modal_container1 .modal_footer {
        text-align: center;
    }

    #secession_modal_container1 .modal_footer button{
        width: 150px;
        height: 40px;
        display: inline-block;
        border-radius: 10px;
    }


</style>
</head>
<body>
    <div id="secession_modal_container1" class="modal_view">
        <div id="modal_box">
            <div class="modal_header">
                <span class="modal_title">회원탈퇴</span>
            </div>
            <div class="modal_body">
                <div class="content_box">
                    <input type="hidden" value="<%=(String)session.getAttribute("userPwd")%>">
                    <span class="pwd_text">비밀번호 확인</span>
                    <input type="password" name="userPwdCheck" maxlength="12" required>
                </div>
            </div>
            <div class="modal_footer">
                <button class="edit1_cancel_btn" type="button">취소</button>
                <button class="secession_next_btn" type="button">다음</button>
            </div>
        </div>
    </div>
</body>
</html>