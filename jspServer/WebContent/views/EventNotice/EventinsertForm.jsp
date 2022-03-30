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
	
			
	
	<br>
	<div class="container">
		<div class="row">
			<form method="post" action="insertEventPage.do">
				<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
					<thead>
						<tr>
							<th colspan="2" style="background-color: #eeeeee; text-align: center;">이벤트 & 공지사항</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="text" class="form-control" placeholder="글 제목" name="Title" maxlength="40" style=" width: 1000px;"></td>
						</tr>
						<tr>
						<td>이미지 : <input type="file" name="uploadfile" onchange="readURL(this);">
							
						<img id="preview" src="" alt="image" style="width:100px" /></td>
							
						</tr>	
						<tr>
							<td><textarea class="form-control" placeholder="글 내용" name="Content" maxlength="2000" style="height: 350px; width: 1000px; resize: none; "></textarea></td>
						</tr>
						
					</tbody>
				</table>
				
				<br><br>
				<p align="right">
				<input type="submit" class="commonwritebutton bts7"   value="취소하기" onclick="location.href='<%= request.getContextPath() %>/eventpage.do'">
				<input type="submit" class="commonwritebutton bts7"  value="글쓰기" onclick="location.href='<%= request.getContextPath() %>/insertEventPage.do'">
				</p>
			</form>
			
			
		</div>
	</div>
	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
			<script>
			function readURL(input) {
			  if (input.files && input.files[0]) {
			    var reader = new FileReader();
			    reader.onload = function (e) {
			      $('#preview')
			      .attr('src', e.target.result);
			    };
			    reader.readAsDataURL(input.files[0]);
			  }
			}
		</script>

	<br>
	<div>
    	<%@ include file = "../common/footer.jsp" %>
    </div>
</body>
</html>