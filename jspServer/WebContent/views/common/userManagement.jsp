<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    #userManagementModal {
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

    #userManagementModal #modal_box {
        width: 400px;
        background-color: #fff;
        padding: 20px 0 20px 0;
        border-radius: 30px;
    }

    #userManagementModal .modal_header {
        background-color: #993333;
        color: #fff;
        text-align: center;
    }

    #userManagementModal .modal_header .modal_title {
        font-size: 30px;
    }

    #userManagementModal #modal_box .modal_body {
        height: 400px;
        position: relative;
    }

    #userManagementModal .modal_body .pwd_text {
        display: block;
        font-size: 20px;
    }

    #userManagementModal .modal_body .content_box {
        position: absolute;
        top: 150px;
        left: 50%;
        transform: translateX(-50%);
        text-align: center;
    }

    #userManagementModal input[type=password]{
        margin-top: 30px;
        width: 300px;
        height: 30px;
        border-radius: 5px;
    }

    #userManagementModal .modal_footer {
        text-align: center;
    }

    #userManagementModal .modal_footer button{
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
    

    #userManagementModal .modal_body {
        overflow-y: scroll;
    }
    
    #userManagementModal .search_input {
    	width: 250px;
    	height: 30px;
    }

    #userManagementModal .search_btn {
        width: 40px;
        height: 30px;
    }
    
    #userManagementModal .search_user_box{
        width: 300px;
    	margin: 10px auto;
    }

    .management_user_list_table tr{
        height: 30px;
    }

    .management_user_list_table {
        border-collapse: collapse;
    }

    #userManagementModal .UserManagementInfo:hover th{
        border-bottom: 1px solid #993333;
        color: #993333;
        cursor: pointer;
    }
</style>
</head>
<body>
    <div id="userManagementModal" class="modal_view">
        <div id="modal_box">
            <div class="modal_header">
                <span class="modal_title">회원 관리</span>
            </div>
           	<div class="search_user_box">
           		<input class="search_input" type="text" placeholder="검색할 아이디 입력">
                <button class="search_btn" type="button">검색</button>
           	</div>
            <div class="modal_body">
                <div>
					<table class="management_user_list_table">
						<tr>
							<th class="userNo">회원번호</th>
							<th class="userId">아이디</th>
							<th>회원상태</th>
						</tr>
					</table>
                </div>
            </div>
            <div class="modal_footer">
                <button class="cancel_btn" type="button">취소</button>
            </div>
        </div>
    </div>
</body>
</html>