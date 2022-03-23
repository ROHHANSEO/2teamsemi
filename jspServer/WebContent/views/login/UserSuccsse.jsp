<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.uni.user.model.vo.User" %>
<%
	String name = (String)request.getAttribute("name");
	String msg = (String)request.getAttribute("msg");
	String msg2 = (String)request.getAttribute("msg2");
	User IdUser = (User)request.getAttribute("userId");
	User pwdUser = (User)request.getAttribute("userPwd");
	String result = (String)request.getAttribute("result");
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
    <form id="input_box" method="post">
        <div class="content_box">
        <%if(name != null) {%>
            <span class="content_text"><p class="sign-up_name">"<%=name %>"님의</p>회원가입이 정상적으로 되었습니다</span>
            <button type="button" class="button" onclick="location.href='/loginPage'">로그인 하기</button>
        <% return; } %>
        
        
		<%if(IdUser != null) {%>
			<span class="content_text"><p class="sign-up_name">"<%=IdUser.getUserName()%>"님의</p>아이디는 <%=IdUser.getUserId() %>입니다.</span>
			<button type="button" class="button" onclick="location.href='/loginPage'">로그인 하기</button>
		<%} else if(msg.equals("비밀번호 수정")) {%>
			<input id="userNo" type="hidden" name="userNo" value="<%=pwdUser.getUserNo()%>">
			<div>
                <input class="userPwd" type="password" name="userPwd" placeholder="비밀번호 6자리~12자리" maxlength="12" autocomplete="off" required>
            </div>
            <div>
                <input class="userPwdCheck" type="password" name="userPwdCheck" placeholder="비밀번호확인 6자리~12자리" maxlength="12" autocomplete="off" required>
            </div>
           	<button id="button" type="submit" class="button">변경하기</button>
            <%--
        <%} else if(result.equals("true")){ %>
        	<span class="content_text">비멸번호 변경이 정상적으로 되었습니다</span>
            <button type="button" class="button" onclick="location.href='/loginPage'">로그인 하기</button>
         --%>   
		<%} else {%>
			<span class="content_text"><%=msg2 %></span>
			<button type="button" class="button" onclick="history.back()">다시 입력</button>
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