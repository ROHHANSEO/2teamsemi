<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.uni.user.model.vo.User"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    #edit_modal_container2 {
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

    #edit_modal_container2 #modal_box {
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

    #edit_modal_container2 .modal_header .modal_title {
        font-size: 30px;
    }

    #edit_modal_container2 #modal_box .modal_body {
        height: 400px;
        position: relative;
    }

    #edit_modal_container2 .modal_body .pwd_text {
        display: block;
        font-size: 20px;
    }

    #edit_modal_container2 .modal_body .content_box {
        position: absolute;
        top: 20px;
        left: 50%;
        transform: translateX(-50%);
        margin-top: 5px;
    }

    #edit_modal_container2 .content_box input{
        margin-top: 10px;
        width: 300px;
        height: 25px;
    }


    #edit_modal_container2 .modal_footer {
        text-align: center;
    }

    #edit_modal_container2 .modal_footer button{
        width: 150px;
        height: 40px;
        display: inline-block;
        border-radius: 10px;
    }

    #edit_modal_container2 .content_box div{
        margin-top: 5px;
    }


</style>
</head>
<body>
    <div id="edit_modal_container2" class="modal_view">
        <div id="modal_box">
            <div class="modal_header">
                <span class="modal_title">개인정보수정</span>
            </div>
            <div class="modal_body">
                <div class="content_box">
                    <input type="hidden" name="userNo" value="<%=user.getUserNo()%>">
                    <input type="hidden" name="userId" value="<%=user.getUserId()%>">
                    <input type="hidden" name="userPwd" value="<%=user.getUserPwd()%>">
                    <input type="hidden" name="userName" value="<%=user.getUserName()%>">
                    <input type="hidden" name="citiNo" value="<%=user.getCitiNo()%>">
                    <input type="hidden" name="phoneNo" value="<%=user.getPhone()%>">
                    <input type="hidden" name="nickName" value="<%=user.getNickName()%>">
                    <input type="hidden" name="email" value="<%=user.getEmail()%>">
                    <input type="hidden" name="gender" value="<%=user.getGender()%>">
                    <div>
                        <span>바꾸실 비밀번호 입력</span>
                        <input class="userPwd" type="password" maxlength="12" required>
                    </div>
                    <div>
                        <span>바꾸실 비밀번호 입력 확인</span>
                        <input class="userPwdCheck" type="password" maxlength="12" required>
                    </div>
                    <div>
                        <span>바꾸실 닉네임</span>
                        <input class="nick_name" type="text" maxlength="10">
                    </div>
                    <div>
                        <span>바꾸실 전화번호</span>
                        <input class="phone" type="text"  placeholder="전화번호(-없이)" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="11" required>
                    </div>
                    <div>
                        <span>바꾸실 이메일</span>
                        <input class="email" type="text"  placeholder="@포함 전체 작성" maxlength="50">
                    </div>
                </div>
            </div>
            <div class="modal_footer">
                <button class="edit1_cancel_btn" type="button">취소</button>
                <button class="edit_submit_btn" type="button">다음</button>
            </div>
        </div>
    </div>
</body>
</html>