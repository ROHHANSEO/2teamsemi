<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
            <div id="header_top_right">
                <a href="<%=request.getContextPath()%>/loginPage" class="login_page">로그인</a>
                /
                <a href="<%=request.getContextPath()%>/sign_up" class="insert_user">회원가입</a>
            </div>
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
                    <li><div onmouseover="add()" onmouseleave="remove()"><a href="<!--이동할 jsp가 위치한 경로입력 할 것-->">시세</a></div></li>
                    <li><div onmouseover="add()" onmouseleave="remove()"><a href="<!--이동할 jsp가 위치한 경로입력 할 것-->">경매</a></div></li>
                    <li><div onmouseover="add()" onmouseleave="remove()"><a href="<%=request.getContextPath() %>/communitypage.do">커뮤니티</a></div></li>
                    <li><div onmouseover="add()" onmouseleave="remove()"><a href="<%=request.getContextPath()%>/usedBoardList.do">중고거래</a></div></li>
                    <li><div onmouseover="add()" onmouseleave="remove()"><a href="<%=request.getContextPath() %>/eventpage.do">이벤트</a></div></li>
                    <li><div onmouseover="add()" onmouseleave="remove()"><a href="<%=request.getContextPath()%>/serviceCenter.do">고객센터</a></div></li>
                </ul>
            </div>
        </nav>
    </header>
    <script src="../../resources/library/jquery-3.6.0.min.js"></script>
    <script src="../../resources/js/common/common.js"></script>
</body>
</html>