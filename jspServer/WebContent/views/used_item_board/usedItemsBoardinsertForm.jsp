<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.container{
		display: flex;
		justify-content: center;
	}
	.whole{
		display: flex;
		margin: 3%;
	}
	.price{
		margin-right: 1%;
	}
	.info{
		margin-right: 10%;
	}
	#imagin{
		display: flex;
		justify-content: center;
		align-items: center;
		background-color: gray;
		width: 100px;
		height: 100px;
		text-align: center;
	}
	.but{
		text-align: end;
	}
	#ret{
		border: 0px;
		border-radius: 3px;
		background-color: gainsboro;
		height: 25px;
	}
	#add{
		border: 0px;
		border-radius: 3px;
		background-color: #993333;
		color: whitesmoke;
		height: 25px;
	}
</style>
</head>
<body>
	<%@include file = "../common/header.jsp" %>
	<div class="container">
		<form class="item" action="insertUsed.do" method="post" enctype="multipart/form-data">
			<div>
				<h1>중고거래 등록하기</h1>
				<hr>
			</div>
			<div>
				<div class="whole">
					<span class="info">
						<h3>제목<c style="color: red;">*</c></h3>
					</span>
					<input type="text" id="" placeholder="제목을 입력하세요"> 0/20
				</div>
				<hr>
				<div class="whole">
					<span class="info">
						<h3>카테고리<c style="color: red;">*</c></h3>
					</span>
					<%@ include file="../common/filter.jsp" %>
				</div>
				<hr>
				<div class="whole">
					<span class="info">
						<h3>이미지 선택<c style="color: red;">*</c></h3>
						<small>
							최대 10개의<br>
							이미지 가능
						</small>
					</span>
					<div id="imagin">
						<div id="camera">
							<img src="https://img.icons8.com/material-rounded/24/000000/camera--v2.png"/>
							<br>
							이미지 선택
						</div>
					</div>
				</div>
				<hr>
				<div class="whole">
					<span class="info">
						<h3>상품 상태<c style="color: red;">*</c></h3>
					<input type="radio" id="new" name="productStatus" checked> 새상품 <input type="radio" name="productStatus" id="used"> 중고 상품 
					</span>
				</div>
				<hr>
				<div class="price">
					<span class="info">
						<h3>가격<c style="color: red;">*</c></h3>
					</span>
					<input type="text" id="" placeholder="가격을 입력해주세요">원
					<input type="checkbox" id="" checked> 바로 결제 <input type="checkbox" id=""> 만나서 결제 
				</div>
				<hr>
				<div>
					<div class="whole">
						<span class="info">
							<h3>상품 설명<c style="color: red;">*</c></h3>
						</span>
						<textarea id="" cols="30" rows="10" placeholder="내용을 입력하세요." maxlength="2000"></textarea>
					</div>
					<p style="text-align: end;"><a id="counter">0</a>/2000</p> rrrr
				</div>
				<div class="but">
					<input id="ret" type="button" value="취소하기"> <input id="add" type="button" value="작성하기" onclick="location.href='<%= request.getContextPath() %>/usedInsertBoard.do'">
				</div>
			</div>
		</form>
	</div>
	<script>
		
		$(function(){
            $("textarea").keydown(function(){
                var inputLength = $(this).val().length;
                var remain = 2000-inputLength
                $("#counter").html(inputLength)

                if(remain >= 0){
                    $("#counter").css("color","black")

                }else{
                    $("#counter").css("color","red")
                    
                }
            })
        })

	</script>
	<%-- <div>
		<%@ include file = "../common/footer.jsp" %>
	</div>--%>
</body>
</html>