	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.uni.event.model.vo.*" %>
<%
	Event e = (Event)request.getAttribute("event");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" media="screen" href="../../resources/css/Event/detail.css">
<link rel="stylesheet" type="text/css" media="screen" href="../../resources/css/common/common.css">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<title>공지사항 상세페이지</title>

</head>
<body>
	<div>
    	<%@ include file = "../common/header.jsp" %>
    </div>
	
	<div class="outer">
		<br>
		<div class="sos1" align="right">
		<% if(user != null && user.getUserId().equals("admin")) { %> 
		<a class="commonwritebutton sse1" href="updateEvent.do?nno=<%=e.getNoticeno()%>">수정</a>  &nbsp;
		<a class="commonwritebutton sse2" href="deleteEvent.do?nno=<%=e.getNoticeno()%>">삭제</a>
		<% } %>
		</div>
		<div class="etitle">
		<h3 class="ti1" >공지사항 상세보기</h3> 
		</div>
		
		<br>
		<div class="wtitle">
		
		<h3 class="w1" >번호 <%= e.getNoticeno() %></h3> <h3 class="w2" ><%= e.getCategory() %></h3>
		
		</div>
			
		
		
			<div class="title2">제목 - <%= e.getNoticeTitle() %></div> <div class="title3"> 직성자 :  <%= e.getUserid() %> &nbsp; 조회수 : <%= e.getCount() %>   &nbsp; 작성일 :  <%= e.getCreateDate() %> </div> 
		
		
		
		
		<div class="n">
			
			<%= e.getNoticeContent() %>
			 
			
		</div>
		
		
		</div>
		
		 <a  class="commonwritebutton sss " href="eventpage.do?nno=<%=e.getNoticeno()%>">목록으로</a>
		
		


		<br>
		
		
		
		
		
		<br>
	
		
	
	
	<%@ include file="../common/footer.jsp" %>
</body>
</html>