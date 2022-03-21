<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String name = (String)request.getAttribute("name");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="../../resources/css/common/common.css">
<link rel="stylesheet" href="../../resources/css/sign-up_successe.css">
</head>
<body>
<header id="header">
    <div id="logo">
        <a href="/"><img src="../../resources/images/logo.gif" alt="로고이미지"></a>
    </div>
    <span id="page_title">간편 회원가입</span>
</header>
<div id="input_container">
    <form id="input_box">
        <div class="content_box">
            <span class="content_text"><p class="sign-up_name">"<%=name %>"님의</p>회원가입이 정상적으로 되었습니다</span>
            <button type="button" class="button" onclick="location.href='/loginPage'">로그인 하기</button>
        </div>
    </form>
</div>
<%@ include file = "../common/footer.jsp" %>
<script src="../../resources/library/jquery-3.6.0.min.js"></script>
<script src="../../resources/js/common/common.js"></script>
<script src="../../resources/js/sign-up_successe.js"></script>
</body>
</html>