<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.uni.event.model.vo.*"%>
<%
	ArrayList<Event> list = (ArrayList<Event>) request.getAttribute("list");
	
	
	String style = "background: #993333; color : white;";
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" media="screen" href="../../resources/css/Event/list.css">
<link rel="stylesheet" type="text/css" media="screen" href="../../resources/css/common/common.css">
<title>이벤트&공지사항</title>
<script type="text/javascript">

 function keyword_check(){

  if(document.search.keyword.value==''){  

  alert('검색어를 입력하세요'); 

  document.search.keyword.focus();

  return false; 

  }

  else return true;

 }

</script>

</head>
	<%@ include file = "../common/header.jsp" %>
<div class="normal">
    	
    </div>

<body>
<div class="normal">
	<div class="outer">
		<br>
		<h2 align="center" >이벤트&공지사항</h2>
		
		
		<div class="sear">
		<form name="search" align="right"  method = "get"  action ="searchEvent.jsp" onsubmit="return keyword_check()">
		<td>
		<input type="text" name="keyword" class="keyword" placeholder="검색어를 입력해주세요"> 
		</td>

		<td>
		<input type="submit" value="검색" class="commonwritebutton btt">
		</td>  
		
		</form>
		</div>
		
		<table class="listArea" >
		 
			<thead>
				<tr class="menubar">
					<th width="10">no.</th>
					<th width="500">글제목</th>
					<th width="100">작성자</th>
					<th width="100">카테고리</th>
					<th width="100">조회수</th>
					<th width="100">작성일</th>
				</tr>
			</thead>
			<tbody>
				

				<% if(list.isEmpty()){ %>
				 	<tr>
						<td colspan="6">존재하는 공지사항이 없습니다.</td>
					</tr>
				 <% }else{  %>
				 	<% for(Event e : list){ %>
				 		<tr>
				 			<td><%= e.getNoticeno() %></td>
				 			<td><%= e.getNoticeTitle() %></td>
				 			<td><%= e.getUserid() %></td>
							<td><%= e.getCategory() %></td>
							<td><%= e.getCount() %></td>
							<td><%= e.getCreateDate() %></td>
				 		</tr>
				 	<% } %>
				 <% } %>
			</tbody>
			
		<div class="enrollevent" align="right">
    	<% if(user != null && user.getUserId().equals("admin")) { %>    	
		<input type="button" class="commonwritebutton bts"   id="inse" value="글작성" onclick="location.href='<%= request.getContextPath() %>/enrollevent.do'">	
		<% } %>
		</div>
			
		</table>
		
		
		
		</div>
		</div>
			
		<script type="text/javascript">
			<%if(!list.isEmpty()){%>
			$(function(){
				$(".listArea>tbody>tr").click(function(){
					var nno = $(this).children().eq(0).text();
					
					location.href="<%=request.getContextPath()%>/detailEvent.do?nno="+nno;
				})
			})
		<%}%>
		
		
		
		
		</script>
		

	<div>
    	<%@ include file = "../common/footer.jsp" %>
    </div>
</body>
</html>