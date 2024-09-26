<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    #selectUserManagementModal {
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

    #selectUserManagementModal #modal_box {
        width: 400px;
        background-color: #fff;
        padding: 20px 0 20px 0;
        border-radius: 30px;
    }

    #selectUserManagementModal .modal_header {
        background-color: #993333;
        color: #fff;
        text-align: center;
    }

    #selectUserManagementModal .modal_header .modal_title {
        font-size: 30px;
    }

    #selectUserManagementModal #modal_box .modal_body {
        height: 400px;
        position: relative;
    }

    #selectUserManagementModal .modal_body .pwd_text {
        display: block;
        font-size: 20px;
    }

    #selectUserManagementModal .modal_body .content_box {
        position: absolute;
        top: 150px;
        left: 50%;
        transform: translateX(-50%);
        text-align: center;
    }

    #selectUserManagementModal input[type=password]{
        margin-top: 30px;
        width: 300px;
        height: 30px;
        border-radius: 5px;
    }

    #selectUserManagementModal .modal_footer {
        text-align: center;
    }

    #selectUserManagementModal .modal_footer button{
        width: 150px;
        height: 40px;
        display: inline-block;
        border-radius: 10px;
    }

    .modal_view {
        z-index: 99;
    }
    
    .modal_body .management_user_list_table{
        width: 100%;
    	margin-top: 20px;
    }
    

    #selectUserManagementModal .modal_body {
        overflow-y: scroll;
    }
    
    #selectUserManagementModal .search_input {
    	width: 250px;
    	height: 30px;
    }

    #selectUserManagementModal .search_btn {
        width: 40px;
        height: 30px;
    }
    
    #selectUserManagementModal .search_user_box{
        width: 300px;
    	margin: 10px auto;
    }

    .management_user_list_table tr{
        height: 30px;
    }

    .management_user_list_table {
        border-collapse: collapse;
    }

    #selectUserManagementModal .UserManagementInfo:hover th{
        border-bottom: 1px solid #993333;
        color: #993333;
        cursor: pointer;
    }

    #selectUserManagementModal .info{
        display: block;
        font-size: 20px;
        padding: 10px 0 10px 20px;
    }

    #selectUserManagementModal .content_text_box{
        text-align: center;
        margin-top: 50px;
    }

    #selectUserManagementModal .content_text_box>span{
        font-size: 20px;
        display: block;
        margin-bottom: 50px;
    }
    #selectUserManagementModal .content_text{
        display: inline-block;
        width: 30%;
        height: 50px;
        line-height: 50px;
        background: lightgray;
        cursor: pointer;
        border-radius: 10px;
    }

    #selectUserManagementModal .content_text:hover{
        background-color: #993333;
    }

    #selectUserManagementModal .content_text:hover span{
        color: #fff;
    }

</style>
</head>
<body>
    <div id="selectUserManagementModal" class="modal_view">
        <div id="modal_box">
            <div class="modal_header">
                <span class="modal_title">회원 상태 변경</span>
            </div>
            <div class="modal_body">
                <div class="userInfomation">
                    <span class="info">회원 번호 : 
                        <span class="userNo userinfo"></span>
                    </span>
                    <span class="info">회원 아이디 : 
                        <span class="userId userinfo"></span>
                    </span>
                    <span class="info">회원 이름 : 
                        <span class="userNm userinfo"></span>
                    </span>
                </div>
                <div class="content_text_box">
	            	<span>변경할 상태를 선택하여 주세요</span>
                    <input type="hidden" id="userNo">
                    <div class="content_text normal">
                        <span id="status_nomal">정상</span>
                    </div>
                    <div class="content_text">
                        <span id="status_banned">정지</span>
                    </div>
                    <div class="content_text">
                        <span id="status_secession">탈퇴</span>
                    </div>
                </div>
            </div>
            <div class="modal_footer">
                <button class="return_btn" type="button">뒤로가기</button>
                <button class="cancel_btn" type="button">취소</button>
            </div>
        </div>
    </div>
</body>
</html>