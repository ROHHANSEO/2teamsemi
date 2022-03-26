<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    #edit_modal_container1 {
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

    #edit_modal_container1 #modal_box {
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

    #edit_modal_container1 .modal_header .modal_title {
        font-size: 30px;
    }

    #edit_modal_container1 #modal_box .modal_body {
        height: 400px;
        position: relative;
    }

    #edit_modal_container1 .modal_body .pwd_text {
        display: block;
        font-size: 20px;
    }

    #edit_modal_container1 .modal_body .content_box {
        position: absolute;
        top: 150px;
        left: 50%;
        transform: translateX(-50%);
        text-align: center;
    }

    #edit_modal_container1 .content_box input[type=password]{
        margin-top: 30px;
        width: 300px;
        height: 30px;
        border-radius: 5px;
    }

    #edit_modal_container1 .modal_footer {
        text-align: center;
    }

    #edit_modal_container1 .modal_footer button{
        width: 150px;
        height: 40px;
        display: inline-block;
        border-radius: 10px;
    }

    .modal_view {
        z-index: 99;
    }
</style>
</head>
<body>
    <div id="edit_modal_container1" class="modal_view">
        <div id="modal_box">
            <div class="modal_header">
                <span class="modal_title">개인정보수정</span>
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
                <button class="edit1_next_btn" type="button">다음</button>
            </div>
        </div>
    </div>
</body>
</html>