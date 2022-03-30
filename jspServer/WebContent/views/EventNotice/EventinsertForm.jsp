<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width-device-width", initial-scale="1">
<link rel="stylesheet" type="text/css" media="screen" href="../../resources/css/Event/insert.css">
<link rel="stylesheet" type="text/css" media="screen" href="../../resources/css/common/common.css">
<title>이벤트&공지사항 작성 패이지</title>
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
	
			
	<h3 class="tt">이벤트&공지사항 글 작성페이지</h3>
	
	<br>
	<div class="container">
		
		<div class="row">
			<form method="post" action="insertEventPage.do">
				<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
					<thead>
						<tr>
							<th colspan="2" style="background-color: #993333; color: white; text-align: center;">이벤트 & 공지사항</th>
							
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="text" class="form-control" placeholder="글 제목" name="title" maxlength="40" style=" width: 1000px;"></td>
						</tr>
							
						<tr>
							<td><textarea class="form-control" placeholder="글 내용" name="content" maxlength="2000" style="height: 350px; width: 1000px; resize: none; "></textarea></td>
						</tr>
						
					</tbody>
				</table>
				
				<br><br>
				<p align="right">
				<input type="button" class="commonwritebutton bts7"   value="취소하기" onclick="history.back()">
				<input type="submit" class="commonwritebutton bts7"  value="글쓰기" onclick="location.href='<%= request.getContextPath() %>/insertEventPage.do'">
				</p>
			</form>
			
			
		</div>
	</div>
	<script>
	
		if ($("#Title").val() == "") {
		alert("이름을 정확히 입력해주세요");
		return;
		}
		
		
		</script>

	<br>
	<div>
    	<%@ include file = "../common/footer.jsp" %>
    </div>
</body>
</html>