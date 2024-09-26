<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int bNo = (int)request.getAttribute("bNo");
	String bTitle = String.valueOf(request.getAttribute("bTitle"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<link rel="stylesheet" href="../../resources/css/usedItemsBoard/report.css">
<body>
	<div class= "reportdiv"><!-- 게시글 신고하기 전체 div -->
		<form name="reportform" id="reportform"   method="post">
		<input type="hidden" name="bNo" value="<%=bNo%>">
		<input type="hidden" name="bTitle" value="<%=bTitle%>">
		<div class="reportIndiv"><!-- 게시글 신고하기 제목 div -->
			<input type="text" name="title" class="reportTitle" placeholder="문의 제목을 입력해주세요" maxlength="40" required>
		</div>
		<div class="reportcategorydiv"><!-- 게시글 신고하기 카테고리 div -->
			<select class="reportcategory" name="category" required>
				<option class="optionlist" value="불법" selected>불법</option>
				<option class="optionlist" value="다른카테고리">다른카테고리</option>
				<option class="optionlist" value="불쾌한내용">불쾌한내용</option>
				<option class="optionlist" value="음란물">음란물</option>
				<option class="optionlist" value="허위정보">허위정보</option>
				<option class="optionlist" value="스팸">스팸</option>
				<option class="optionlist" value="욕설">욕설</option>
				<option class="optionlist" value="폭력물">폭력물</option>
				<option class="optionlist" value="기타">기타</option>
			</select>
		</div>
		<div class="reportContentdiv"><!-- 게시글 신고하기 내용 div -->
			<textarea name="content" class="reportContent" placeholder="문의 내용을 입력해주세요" style="resize:none;" maxlength="2000" required></textarea>
		</div>
		<div class="reportButtonArea"><!-- 게시글 신고하기 버튼 div -->
			<button type="button" id ="cancelValue" class="reportToButton1" onclick="askcancel()">Cancel</button>
			<button type="button" id="sendValue" class="reportToButton2" >OK</button>
		</div>
		</form>
	</div>
	<script src="../../resources/js/usedItemBoard/report.js"></script>
</body>
</html>