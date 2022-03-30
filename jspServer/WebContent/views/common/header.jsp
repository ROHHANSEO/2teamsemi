<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.uni.user.model.vo.User" %>
<%
	User user = (User)session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../resources/css/common/common.css">
</head>
<body>
    <header id="main_header">
        <div id="header">
            <div id="logo"><a href="/"><img src="../../resources/images/logo.gif" alt="메인로고이미지"></a></div>
            <div id="search">
                
            </div>
            <%if(user==null) { %>
            <div id="header_top_right">
                <a href="<%=request.getContextPath()%>/loginPage" class="login_page">로그인</a>
                /
                <a href="<%=request.getContextPath()%>/sign_up" class="insert_user">회원가입</a>
            </div>
            <%} else if(user.getAdminStatus().equals("Y")) {%>
            <div id="header_top_right">
                <span><%=user.getUserName()%> 관리자님 어서오세요</span>
                <a href="<%=request.getContextPath()%>/logout" class="login_page">로그아웃</a>
                /
                <a href="<%=request.getContextPath()%>/adminMainPage" class="insert_user">관리자 페이지</a>
            </div>
            <%} else if(user.getAdminStatus().equals("N")) { %>
            <div id="header_top_right">
            	<span><%=user.getUserName()%>님 어서오세요</span>
                <a href="<%=request.getContextPath()%>/logout" class="login_page">로그아웃</a>
                /
                <a href="<%=request.getContextPath()%>/salesRecordPage" class="insert_user">마이페이지</a>
            </div>
            <%} %>
        </div>
        <nav id="main_nav">
            <div class="nav">
                <div class="category">
                    <div class="category_box">
                        <i class="berger1"></i>
                        <i class="berger2"></i>
                        <i class="berger3"></i>
                    </div>
                </div>
                <ul>
                    <li><a href="<!--이동할 jsp가 위치한 경로입력 할 것-->"><div>시세</div></a></li>
                    <li><a href="<%=request.getContextPath()%>/auctionPage.do"><div>경매</div></a></li>
                    <li><a href="<%=request.getContextPath() %>/communitypage.do"><div>커뮤니티</div></a></li>
                    <li><a href="<%=request.getContextPath()%>/usedBoardList.do"><div>중고거래</div></a></li>
                    <li><a href="<%=request.getContextPath() %>/eventpage.do"><div>이벤트</div></a></li>
                    <li><a href="<%=request.getContextPath()%>/serviceCenter.do"><div>고객센터</div></a></li>
                </ul>
            </div>
        </nav>
    </header>
    <script src="../../resources/library/jquery-3.6.0.min.js"></script>
    <script src="../../resources/js/common/common.js"></script>
</body>
</html>