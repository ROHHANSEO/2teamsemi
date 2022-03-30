<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DashBoard</title>
<style>
        #dashboard_area {
            width: 70%;
            margin: 0 auto;
            text-align: center;
            margin-bottom: 40px;
            
        }

        .article_area{
            border: 1px solid black;
            background-color: lightgray;
            width: 40%;
            height: 70px;
            border-radius: 20px;
            display: inline-block;
            margin: 30px;
            line-height: 70px;
            cursor: pointer;
            
        }

        .article_area:hover{
            background-color: #993333;
            color: #fff;
        }
        
        #DashBoardPage {
        	color: #993333;
   			border-bottom: 1px solid #993333;
   			font-weight: 600;
        }
</style>
</head>
<body>
<%@include file="../common/header.jsp" %>
<%@include file="../admin/AdminTitle.jsp" %>
<%@include file="../common/userLogModal.jsp" %>
    <section id="dashboard_area">
        <div class="article_area dashboard_user_list">
            <span class="content_text">회원 목록</span>
        </div>
        <div class="article_area dashboard_ban_user_list">
            <span class="content_text">정지 회원 목록</span>
        </div>
        <div class="article_area dashboard_user_management">
            <span class="content_text">회원 관리</span>
        </div>
        <div class="article_area dashboard_admin_code">
            <span class="content_text">운영자 코드 관리</span>
        </div>
    </section>
<%@include file="../common/footer.jsp" %>
    
</body>
</html>