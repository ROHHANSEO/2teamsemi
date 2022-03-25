<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.ArrayList, com.uni.usedItemBoard.model.vo.*"%>
<%
ArrayList<Category> cList = (ArrayList<Category>) request.getAttribute("category");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../../resources/css/common/common.css">
<link rel="stylesheet"
	href="../../resources/css/usedItemsBoard/EnrollForm.css">
</head>
<body>
	<%@include file="../common/header.jsp"%>
	<div class="container">
		<form name="useditemform" class="item"
			action="<%=request.getContextPath()%>/insertUsed.do" method="post"
			enctype="multipart/form-data">
			<h1>중고거래 등록하기</h1>
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
					<div class="categoryline">
						<h3>
							카테고리<a style="color: red;">*</a>
						</h3>
					</div>
					<div id="category">
						<div id="categorydiv">
							<select id="large" name="large">
								<option value="대분류">대분류</option>
								<%
								for (int i = 0; i < cList.size(); i++) {
								%>
								<option value="<%=cList.get(i).getCode()%>"><%=cList.get(i).getName()%></option>
								<%
								}
								%>
							</select> <select name="middle" id="middle">
								<option id="examplemiddle" value="중분류">중분류</option>
							</select> <select id="small" name="small">
								<option id="examplesmall" value="소분류">소분류</option>
							</select>
						</div>
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
				<div class="whole">
					<div class="productline">
						<h3>
							상품 상태<a style="color: red;">*</a>
						</h3>
					</div>
					<div>
						<input type="radio" id="new" value="새상품" name="productStatus"
							checked> 새상품 <input type="radio" value="중고상품"
							name="productStatus" id="used"> 중고 상품
					</div>
				</div>
				<div class="whole">
					<div class="priceline">
						<h3>
							가격<a style="color: red;">*</a>
						</h3>
					</div>
					<div class="checkarea">
						<div>
							<input type="text" name="price" id="price"
								placeholder="가격을 입력해주세요" required>원
						</div>
						<div class="checkline">
							<div class="product">
								<input type="checkbox" id="one" value="바로 결제" name="payments">
								바로 결제 <input type="checkbox" id="two" value="만나서 결제"
									name="payments" checked> 만나서 결제
							</div>
						</div>
					</div>
				</div>
				<div>
					<div class="whole">
						<div class="itempline">
							<h3>
								상품 설명<a style="color: red;">*</a>
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
						onclick="location.href='<%=request.getContextPath()%>/usedBoardList.do'">
					<input id="add" class="commonsubmit" type="button" value="작성하기"
						onclick="checkform()">
				</div>
				<div>ㅤ</div>
			</div>
		</form>
	</div>
	<script src="../../resources/library/jquery-3.6.0.min.js"></script>
	<script src="../../resources/js/usedItemBoard/EnrollForm.js"></script>
	<div>
		<%@ include file="../common/footer.jsp"%>
	</div>
</body>
</html>