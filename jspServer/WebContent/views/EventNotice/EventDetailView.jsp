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
<title>공지사항 상세페이지</title>

</head>
<body>
	<div>
    	<%@ include file = "../common/header.jsp" %>
    </div>
	
	<div class="outer">
		<br>
		<div class="etitle">
		<h2 align="center" >공지사항 상세보기</h2>
		
		<div align="right">
		<% if(user != null && user.getUserId().equals("admin")) { %>
			<a class="btnupdate" href="updateEvent.do?nno=<%=e.getNoticeno()%>">수정</a> /
			<a class="btnupdate" href="eventpage.do?nno=<%=e.getNoticeno()%>">취소</a> &nbsp;&nbsp;
				
		
			<% } %>
			</div>
		</div>
		
		<br>
		
		<div class="titleline">
			<h3>제목 : <%= e.getNoticeTitle() %> </h3>
		</div>
		
		<div class="titleline">
			
			<h3 class="w" >직성자 :  </h3>
			<%= e.getUserid() %>
			<h3 class="c" >키테고리 : </h3>
			<%= e.getCategory() %> 
			<h3 class="d" >작성일 : </h3>
			<%= e.getCreateDate() %> 
		</div>
		
		<div>
			<h3 class="n" >내용 : </h3>
			<%= e.getNoticeContent() %> 
		</div>
		
	
	
		
		
		
		
	<div class="container">
	<div class="form-group">
	<h3>댓글</h3>
		<form method="post" encType = "multipart/form-data"  >
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<tr>
					<td style="border-bottom:none;" valign="middle">
					<td><input type="text" style="height:100px;" class="form-control" placeholder="댓글 내용을 입력해주세요" name = "commentText"></td>
					<td><br><br><input type="submit" class="btn-primary pull" value="댓글 작성"></td>
				</tr>
				<tr>
					<td colspan="3"><input type="file" name="fileName"></td>
				</tr>
			</table>
		</form>
	</div>
</div>
<div class="container ">
	
	<div class="row">
		<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
			<tbody>
				<tr>
					<td align="left" bgcolor="beige">댓글</td>
				</tr>
				<tr>
				
						<div class="container">		
							<div class="row">
								<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
									<tbody>
										<tr>						
											<td align="left">	
											<td colspan="2"></td>
											<td align="right">
												
													<form name = "p_search">
														<a type="button" class="btn-primary">수정</a>
													</form>	
														<a onclick="return confirm('정말로 삭제하시겠습니까?')" class="btn-primary">삭제</a>																	
												<%
												
												%>	
											</td>
										</tr>
										<tr>
											<td colspan="5" align="left">
											
										</tr>
									</tbody>
								</table>			
							</div>
						</div>
						<%
							
						%>
				</tr>
		</table>
	</div>
</div>
	<%@ include file="../common/footer.jsp" %>
</body>
</html>