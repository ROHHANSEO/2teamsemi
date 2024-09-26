<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width-device-width", initial-scale="1">
<link rel="stylesheet" type="text/css" media="screen" href="../../resources/css/Event/insert.css">
<link rel="stylesheet" type="text/css" media="screen" href="../../resources/css/common/common.css">
<link rel="stylesheet" type="text/css" media="screen" href="../../resources/css/community/community.css">
<title>커뮤니티 작성 패이지</title>
</head>
<body>
	<div>
    	<%@ include file = "../common/header.jsp" %>
    </div>
	<%
		String userID = null;
		if(session.getAttribute("userID") != null){
			userID = (String)session.getAttribute("userID");
		}
	%>
	
			
	
	<br>
	<div class="container">
		<div class="row">
			<form method="post" action="insertEventPage.do">
				<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
					<thead>
						<tr>
							<th colspan="2" style="background-color: #eeeeee; text-align: center;">커뮤니티</th>
							<select name="menulist" onchange="handleOnChange(this)" align="">
						  		<option>전체보기</option>
						  		<option>공지사항</option>
						  		<option>이벤트</option>
							</select>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="text" class="form-control" placeholder="글 제목" name="Title" maxlength="40" style=" width: 1000px;"></td>
						</tr>
						<tr>
							<td><textarea class="form-control" placeholder="글 내용" name="Content" maxlength="2000" style="height: 350px; width: 1000px; resize: none; "></textarea></td>
						</tr>
						
					</tbody>
				</table>
				
				<br><br>
				<p align="right">
				<input type="submit" class="commonwritebutton gto1"   value="취소하기" onclick="location.href='<%= request.getContextPath() %>/communitypage.do'">
				<input type="submit" class="commonwritebutton gto2"  value="글쓰기" onclick="location.href='<%= request.getContextPath() %>/insertBoardPage.do'">
				</p>
			</form>
			
			
		</div>
	</div>
	<br>
	<div>
    	<%@ include file = "../common/footer.jsp" %>
    </div>