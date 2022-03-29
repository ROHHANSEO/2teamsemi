<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
    #report_modal_container {
        background-color: rgba(0, 0, 0, 0.5);
        position: absolute;
        top: 0;
        left: 0;
        bottom: 0;
        right: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        z-index: 99;
    }

    #report_modal_container #modal_box {
        width: 450px;
        background-color: #fff;
        padding: 20px 0 20px 0;
        border-radius: 30px;
    }

    #modal_box .modal_header {
        background-color: #993333;
        color: #fff;
        text-align: center;
    }

    #report_modal_container .modal_header .modal_title {
        font-size: 30px;
    }

    #report_modal_container #modal_box .modal_body {
        height: 500px;
        position: relative;
    }

    #report_modal_container .modal_body .pwd_text {
        display: block;
        font-size: 20px;
    }

    #report_modal_container .modal_body .content_box {
        position: absolute;
        top: 150px;
        left: 50%;
        transform: translateX(-50%);
        text-align: center;
    }

    #report_modal_container .content_box input[type=password]{
        margin-top: 30px;
        width: 300px;
        height: 30px;
        border-radius: 5px;
    }

    #report_modal_container .modal_footer {
        position: absolute;
        margin-left: 15%;
        bottom: 0px;
    }

    #report_modal_container .modal_footer button{
        width: 150px;
        height: 40px;
        display: inline-block;
        border-radius: 10px;
    }

    .modal_view {
        z-index: 99;
    }

    .report_title_box{
        height: 50px;
        line-height: 50px;
        padding-left: 30px;
        border-bottom: 1px solid black;
    }

    .report_title_box .report_title{
        font-size: 20px;
    }

    .report_content_box {
        padding: 30px;
    }

    .report_content_box .report_content_title{
        padding-bottom: 15px;
        font-size: 20px;
    }
    </style>
</head>
<body>
    <div id="report_modal_container">
        <div id="modal_box">
            <div class="modal_header">
                <span class="modal_title">신고 상세</span>
            </div>
            <div class="modal_body">
                <div class="report_title_box">
                    <span class="report_title">제목 : </span>
                    <span class="report_title_name"></span>
                </div>
                <div class="report_content_box">
                    <div class="report_content_title">신고 내용</div>
                    <div class="content"></div>
                </div>
                <div class="modal_footer">
                    <button class="report_modal_cancel_btn" type="button">취소</button>
                    <button class="report_sanctions_btn" type="button">경고하기</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>