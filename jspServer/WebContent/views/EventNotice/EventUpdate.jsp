<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.uni.event.model.vo.Event" %>
<%
	Event e = (Event)request.getAttribute("event");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" media="screen" href="../../resources/css/Event/update.css">
<link rel="stylesheet" type="text/css" media="screen" href="../../resources/css/common/common.css">
</head>
<body>

	<div>
    	<%@ include file = "../common/header.jsp" %>
    </div>
	
	<div class="outer">
		<br>
		<h2 align="center">이벤트&공지사항 수정</h2>
		
		<form id="updateForm" action="<%= request.getContextPath() %>/updateNotice.do" method="post" >
			<input type="hidden" name="nno" value="<%= e.getNoticeno() %>">
			<table align="center">
				<tr>
					<td>제목</td>
					<td colspan="3"><input type="text" id="Title" name="title" value="<%= e.getNoticeTitle() %>"></td>
				</tr>
				
				<tr>
					<td>내용</td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td colspan="4">
						<textarea name="content" cols="60" rows="15" style="resize:none;"><%= e.getNoticeContent() %></textarea>
					</td>
				</tr>	
			</table>
			<br>
			
			
			<div class="btns" align="center">
				<button type="button" class="commonwritebutton ses2" onclick="location.href='<%=request.getContextPath() %>/eventpage.do'">취소</button>
				<button type="submit" class="commonwritebutton ses1" >수정</button>
			</div>
		</form>
	</div>
	<script>
	
	if ($("#text").val() == "") {
		alert("제목을 정확히 입력해주세요");
		return;
		}
	
	
	</script>
	<%@ include file="../common/footer.jsp" %>
	
</body>
</html>