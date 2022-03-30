<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../../resources/css/common/common.css">
<link rel="stylesheet" href="../../resources/css/admin/adminTitle.css">
</head>
<body>
    <div id="admin_title">
        <div id="content_title">
            <span class="title_name">관리자 페이지</span>
        </div>
        <div id="content_nav">
            <a id="reportPage" href="<%=request.getContextPath()%>/adminMainPage">신고 게시판</a>
            <a id="sevicecenterPage" href="<%=request.getContextPath()%>/sevicecenterPage">고객 센터</a>
            <a id="QtoAListPage" href="<%=request.getContextPath()%>/QtoAListPage">1:1 문의답변</a>
            <a href="">이벤트</a>
            <a href="">공지사항</a>
            <a href="">dashboard</a>
        </div>
    </div>
</body>
</html>