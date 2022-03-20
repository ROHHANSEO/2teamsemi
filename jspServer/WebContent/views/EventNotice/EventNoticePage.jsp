<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.uni.event.model.vo.Event"%>
<%
	ArrayList<Event> list = (ArrayList<Event>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
<style>
	.outer{
		width:800px;
		height:500px;
		background:white;
		color:black;
		margin:auto;
		margin-top:50px;
	}
	.listArea{
		border:1px solid white;
		text-align:center;
	}
	.searchArea{
		margin-top:50px;
	}
	
	.listArea>tbody>tr:hover{
		background:darkgrey;
		cursor:pointer
	}
</style>
</head>
<body>
	<%@ include file = "../common/header.jsp" %>
	
	<div class="outer">
		<br>
		<h2 align="center">이벤트&공지사항</h2>
		<br>
		<hr>
		
		        
		<table class="listArea" align="center">
			<thead>
				<tr>
					<th>글번호</th>
					<th width="500">글제목</th>
					<th width="100">작성자</th>
					<th width="100">작성일</th>
				</tr>
			</thead>
			<tbody>
				

				<% if(list.isEmpty()){ %>
				 	<tr>
						<td colspan="5">존재하는 공지사항이 없습니다.</td>
					</tr>
				 <% }else{  %>
				 	<% for(Event n : list){ %>
				 		<tr>
				 			<td><%= n.getNOTICE_NO() %></td>
							<td><%= n.getNOTICE_TITLE() %></td>
							<td><%= n.getUSER_NO() %></td>
							<td><%= n.getCREATE_DATE() %></td>
				 		</tr>
				 	<% } %>
				 <% } %>
			</tbody>
			
		</table>

	<form class="searchArea" align="center">
			<select id="condition" name="condition">
				<option value="writer">작성자</option>
				<option value="title">제목</option>
				<option value="content">내용</option>
			</select>
			
			
			
			<input type="search" name="search">
			<button type="submit">검색하기</button>
		</form>
		<br><br>
		
	
	
	 <%@ include file = "../common/footer.jsp" %>
</body>
</html>