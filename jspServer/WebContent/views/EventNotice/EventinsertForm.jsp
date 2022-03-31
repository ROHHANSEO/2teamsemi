<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.ArrayList, com.uni.event.model.vo.*"%>
<%
	Event e = (Event)request.getAttribute("event");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../../resources/css/common/common.css">	

<link rel="stylesheet"
	href="../../resources/css/Event/insert.css">
</head>
<body>
	<%@include file="../common/header.jsp"%>
	<div class="container">
		<form name="useditemform" class="item"
			action="<%=request.getContextPath()%>/insertEventPage.do" method="post"
			enctype="multipart/form-data">
			<h1>이벤트&공지사항 글 등록</h1>
			<div>
				<div class="whole">
					<div class="titleline">
						<h3>
							제목<a style="color: red;">*</a>
						</h3>
					</div>
					<input type="text" name="title" id="title" placeholder="제목을 입력하세요"
						maxlength="40" required>
					<div>ㅤ</div>
					<div class="count">
						<div>
							<small><c id="countone"> 0</c>/40</small>
						</div>
					</div>
				</div>

				<div class="whole">
					<div class="productline">
						<h3>
							글 분류<a style="color: red;">*</a>
						</h3>
					</div>
					<div>
						<input type="radio" id="event" value="이벤트" name="NoticeStatus"
							checked> 이벤트 <input type="radio" value="공지사항"
							name="NoticeStatus" id="notice"> 공지사항
					</div>
				</div>
				<div class="whole">
					<div class="info">
						<h3>
							이미지 선택<a style="color: red;">*</a>
						</h3>
						<small> 최대 10개의<br> 이미지 가능
						</small>
					</div>
					<div>
						<div class="article">
							<div id="imagin">
								<div id="camera">
									<input type="file" name="file1" id="file1" accept='.gif, .jpg, .png' multiple />
									<img src="https://img.icons8.com/material-rounded/24/000000/camera--v2.png" />
									<br> 이미지 선택
								</div>
								<ul id="sortimg">
								</ul>
							</div>
						</div>
					</div>
				</div>
				
				
				<div>
					<div class="whole">
						<div class="itempline">
							<h3>
								 글 내용<a style="color: red;">*</a>
							</h3>
						</div>
						<div class="itempline">
							<textarea name="content" id="content" cols="30" rows="10"
								placeholder="내용을 입력하세요." maxlength="2000" required></textarea>
						</div>
						<div class="count">
							<div>
								<small><c id="counter">0</c>/2000</small>
							</div>
						</div>
					</div>
				</div>
				<div>ㅤ</div>
				<div class="but">
					<input id="ret" class="commonsubmit" type="button" value="취소하기"
						onclick="location.href='<%=request.getContextPath()%>/eventpage.do'">
					<input id="add" class="commonsubmit" type="button" value="작성하기"
						onclick="checkform()">
				</div>
				<div>ㅤ</div>
			</div>
		</form>
	</div>
	<script src="../../resources/library/jquery-3.6.0.min.js"></script>
	<script src="../../resources/css/Event/insert.css"></script>	
	<div>
		<%@ include file="../common/footer.jsp"%>
	</div>
</body>
</html>