<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.uni.user.model.service.UserService"%>
<%@ page import = "com.uni.user.model.vo.User" %>
<%@ page import = "javax.servlet.http.HttpSession" %>
<%


	Cookie[] cookies = request.getCookies();
	User user = null;
	
	if(cookies != null){
	  for(Cookie tempCookie : cookies){
	      if(tempCookie.getName().equals("loginCookie")){
				String cookie = tempCookie.getValue();
				user = new UserService().autoLoginUser(cookie);

				request.getSession().setAttribute("user", user);
				
	      }
	  }
	  
	  if(user == null){
			user = (User)session.getAttribute("user");
	  }
	  
	}
	
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
            <a href="/"><div id="logo"><img src="../../resources/images/logo.gif" alt="메인로고이미지"></div></a>
            <form id="searchform" class="searchform" method="post" action="<%=request.getContextPath()%>/searchList.do">
                <input type="search" name="search" id="search">
                <img onclick="cleansearch(this);" id="cancel" src="../../resources/images/cancelbutton.png">
                <a href="#" id="searchbutton"><button type="submit" id="opercity"><img id="searchicon" src="../../resources/images/searchIcon.png"></button></a>
            </form>
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
                <ul>
                    <li><a href="<%=request.getContextPath()%>/auctionPage.do"><div>경매</div></a></li>
                    <li><a href="<%=request.getContextPath()%>/usedBoardList.do"><div>중고거래</div></a></li>
                    <li><a href="<%=request.getContextPath() %>/eventpage.do"><div>이벤트</div></a></li>
                    <li><a href="<%=request.getContextPath()%>/serviceCenter.do"><div>고객센터</div></a></li>
                </ul>
            </div>
        </nav>
    </header>
    <script src="../../resources/library/jquery-3.6.0.min.js"></script>
    <script src="../../resources/js/common/common.js"></script>
    <script>
    	$(function(){
    		$.ajax({
    			
    		})
    	})
    	
    	$(document).click(function(){
    		$("#cancel").hide();	
    	})
    	
    </script>
</body>
</html>