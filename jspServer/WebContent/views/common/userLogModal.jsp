<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    #userLogModal {
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

    #userLogModal #modal_box {
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

    #userLogModal .modal_header .modal_title {
        font-size: 30px;
    }

    #userLogModal #modal_box .modal_body {
        height: 400px;
        position: relative;
    }

    #userLogModal .modal_body .pwd_text {
        display: block;
        font-size: 20px;
    }

    #userLogModal .modal_body .content_box {
        position: absolute;
        top: 150px;
        left: 50%;
        transform: translateX(-50%);
        text-align: center;
    }

    #userLogModal input[type=password]{
        margin-top: 30px;
        width: 300px;
        height: 30px;
        border-radius: 5px;
    }

    #userLogModal .modal_footer {
        text-align: center;
    }

    #userLogModal .modal_footer button{
        width: 150px;
        height: 40px;
        display: inline-block;
        border-radius: 10px;
    }

    .modal_view {
        z-index: 99;
    }
    
    .modal_body .user_log_table{
        width: 100%;
    	margin-top: 20px;
    }
    

    #userLogModal .modal_body {
        overflow-y: scroll;
    }
</style>
</head>
<body>
    <div id="userLogModal" class="modal_view">
        <div id="modal_box">
            <div class="modal_header">
                <span class="modal_title">회원목록</span>
            </div>
            <div class="modal_body">
                <div>
					<table class="user_log_table">
						<tr>
							<th class="userNo">회원번호</th>
							<th>아이디</th>
							<th>제재횟수</th>
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