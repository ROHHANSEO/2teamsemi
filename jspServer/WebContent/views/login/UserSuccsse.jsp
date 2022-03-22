<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.uni.user.model.vo.User" %>
<%
	String name = (String)request.getAttribute("name");
	String msg = (String)request.getAttribute("msg");
	String msg2 = (String)request.getAttribute("msg2");
	User user = (User)request.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=msg %></title>
<link rel="stylesheet" href="../../resources/css/common/common.css">
<link rel="stylesheet" href="../../resources/css/sign-up_successe.css">
</head>
<body>
<header id="header">
    <div id="logo">
        <a href="/"><img src="../../resources/images/logo.gif" alt="로고이미지"></a>
    </div>
    <span id="page_title"><%=msg %></span>
</header>
<div id="input_container">
    <form id="input_box">
        <div class="content_box">
        <%if(name == null) {%>
			<%if(user != null) {%>
			<span class="content_text"><p class="sign-up_name">"<%=user.getUserName()%>"님의</p>아이디는 <%=user.getUserId() %>입니다.</span>
			<button type="button" class="button" onclick="location.href='/loginPage'">로그인 하기</button>
			<%} else {%>
			<span class="content_text"><%=msg2 %></span>
			<button type="button" class="button" onclick="location.href='/id_search'">다시 입력</button>
			<%} %>
        <%} else { %>
            <span class="content_text"><p class="sign-up_name">"<%=name %>"님의</p>회원가입이 정상적으로 되었습니다</span>
            <button type="button" class="button" onclick="location.href='/loginPage'">로그인 하기</button>
        <%} %>
        </div>
    </form>
</div>
<%@ include file = "../common/footer.jsp" %>
<script src="../../resources/library/jquery-3.6.0.min.js"></script>
<script src="../../resources/js/common/common.js"></script>
<script src="../../resources/js/sign-up_successe.js"></script>
</body>
</html>