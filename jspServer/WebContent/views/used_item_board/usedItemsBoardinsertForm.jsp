<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.uni.usedItemBoard.model.vo.*"%>
<%
	ArrayList<Category> cList = (ArrayList<Category>)request.getAttribute("category");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../../resources/css/common/common.css">
<style>
	.container{
		display: flex;
		width: 100vw;
	}
	form{
		margin: 0 auto;
    	width: 70vw;
	}
	.whole{
		display: flex;
		padding: 15px;
		border-bottom: 1px solid black;
	}
	.info{
		margin-right: 30px;
	}
	#imagin{
		display: flex;
		justify-content: center;
		align-items: center;
		background-color: #efefef;
		width: 200px;
		height: 200px;
		text-align: center;
	}
	.article{
		display: flex;
	}
	.article1{
		margin: 10px;
	}
	#contentImg1, #contentImg2, #contentImg3, #contentImg4, #contentImg5, 
	#contentImg6, #contentImg7, #contentImg8, #contentImg9, #titleImg{
		width: 200px;
		height: 200px;
		margin-top: 0;
		display: none;
	}
	#contentImg1, #contentImg2, #contentImg3, #contentImg4, #contentImg5,
	#contentImg6, #contentImg7, #contentImg8, #contentImg9{
		margin-left: 0;
	}
	#title{
		width: 15em;
	}
	.titleline{
		margin-right: 94px;
	}
	.priceline{
		margin-right: 89px;
	}
	.productline{
		margin-right: 47px;
	}
	.categoryline{
		margin-right: 55px;
	}
	.itempline{
		margin-right: 45px;
	}
	.but{
		text-align: end;
	}
	input[type = "file"]{
		display: none;
	}
	.checkarea{
		width: 50vw;
		display: flex;
		justify-content: space-between;
	}
	.product{
		font-size: 11px;
	}
	.count{
		display: flex;
		flex-direction: column-reverse;
	}
	#content{
		resize: none;
		width: 40vw;
	}
	/*카테 고리부분*/
	#category{
		width: 83.9%;
		padding: 10px;
		background-color: #efefef;
	}
	#categorydiv{
		display: flex;
		flex-wrap: wrap;
	    justify-content: space-around;
	}
	#large, #middle, #small{
		width: 20%;
	}
</style>
</head>
<body>
	<%@include file = "../common/header.jsp" %>
	<div class="container">
		<form name="useditemform" class="item" action="<%=request.getContextPath()%>/insertUsed.do" method="post" enctype="multipart/form-data">
			<h1>중고거래 등록하기</h1>
			<div>
				<div class="whole">
					<div class="titleline">
						<h3>제목<a style="color: red;">*</a></h3>
					</div>
					<input type="text" name="title" id="title" placeholder="제목을 입력하세요" maxlength="40" required>
					<div>ㅤ</div>
					<div class="count">
						<div>
							<small><c id="countone"> 0</c>/40</small>
						</div>
					</div>
				</div>
				
				<div class="whole">
					<div class="categoryline">
						<h3>카테고리<a style="color: red;">*</a></h3>
					</div>
					<div id="category">
						<div id="categorydiv">
							<select id="large" name="large">
								<option value="대분류">대분류</option>
								<% for(int i = 0 ; i < cList.size() ; i++){ %>
									<option value="<%= cList.get(i).getCode() %>"><%= cList.get(i).getName() %></option>
								<% } %>
							</select>
							<select name="middle" id="middle">
								<option id="examplemiddle" value="중분류">중분류</option>
							</select>
							<select id="small" name="small">
								<option id="examplesmall" value="소분류">소분류</option>
							</select>
						</div>
					</div>
				</div>
				<div class="whole">
					<div class="info">
						<h3>이미지 선택<a style="color: red;">*</a></h3>
						<small>
							최대 10개의<br>
							이미지 가능
						</small>
					</div>
					<div>
						<div class="article">
							<div id="imagin">
								<div id="camera">
									<img src="https://img.icons8.com/material-rounded/24/000000/camera--v2.png"/>
									<br>
									이미지 선택
								</div>
							</div>
							<img id="titleImg" class="article1">
							<img id="contentImg1" class="article1">
						</div>
						<div class="article">
							<img id="contentImg2" class="article1">
							<img id="contentImg3" class="article1">
							<img id="contentImg4" class="article1">
						</div>
						<div class="article">
							<img id="contentImg5" class="article1">
							<img id="contentImg6" class="article1">
							<img id="contentImg7" class="article1">
						</div>
						<div class="article">
							<img id="contentImg8" class="article1">
							<img id="contentImg9" class="article1">
						</div>
					</div>
				</div>
				<div class="whole">
					<div class="productline">
						<h3>상품 상태<a style="color: red;">*</a></h3>
					</div>
					<div>
						<input type="radio" id="new" value="새상품" name="productStatus" checked> 새상품 <input type="radio" value="중고상품" name="productStatus" id="used"> 중고 상품 
					</div>
				</div>
				<div class="whole">
					<div class="priceline">
						<h3>가격<a style="color: red;">*</a></h3>
					</div>
					<div class="checkarea">
						<div>
							<input type="text" name="price" id="price" placeholder="가격을 입력해주세요" required>원
						</div>
						<div class="checkline">
							<div class="product">
								<input type="checkbox" id="one" value="바로 결제" name="payments"> 바로 결제 <input type="checkbox" id="two" value="만나서 결제" name="payments" checked> 만나서 결제 
							</div>
						</div>
					</div>
				</div>
				<div>
					<div class="whole">
						<div class="itempline">
							<h3>상품 설명<a style="color: red;">*</a></h3>
						</div>
						<div class="itempline">
							<textarea name="content" id="content" cols="30" rows="10" placeholder="내용을 입력하세요." maxlength="2000" required></textarea>
						</div>
						<div class="count">
							<div>
								<small><c id="counter">0</c>/2000</small>
							</div>
						</div>
					</div>
				</div>	
				<div class="filearea">
					<input type="file" name="file1" id="file1" accept='image/*' onchange="loadImg(this, i);">
					<input type="file" name="file2" id="file2" accept='image/*' onchange="loadImg(this, i);">
					<input type="file" name="file3" id="file3" accept='image/*' onchange="loadImg(this, i);">
					<input type="file" name="file4" id="file4" accept='image/*' onchange="loadImg(this, i);">
					<input type="file" name="file5" id="file5" accept='image/*' onchange="loadImg(this, i);">
					<input type="file" name="file6" id="file6" accept='image/*' onchange="loadImg(this, i);">
					<input type="file" name="file7" id="file7" accept='image/*' onchange="loadImg(this, i);">
					<input type="file" name="file8" id="file8" accept='image/*' onchange="loadImg(this, i);">
					<input type="file" name="file9" id="file9" accept='image/*' onchange="loadImg(this, i);">
					<input type="file" name="file10" id="file10" accept='image/*' onchange="loadImg(this, i);">
				</div>	
				<div>ㅤ</div>
				<div class="but">
					<input id="ret" class="commonsubmit" type="button" value="취소하기"> 
					<input id="add" class="commonsubmit" type="button" value="작성하기" onclick="checkform()">
				</div>
			</div>
		</form>
	</div>
	<script>
		// 게시글 체크
		function checkform() {
			
			// 제목 미설정시 알림
			if(confirm("글을 등록하시겠습니까?") == true){
				let theForm = document.useditemform;
				// 제목과 설명을 적지 않을시
				if(theForm.title.value == "" && theForm.content.value == ""){
					alert("제목과 설명을 작성해 주세요")
					return $("#title").focus();
				}else if(theForm.title.value == ""){
					alert("제목을 작성해 주세요.")
					return $("#title").focus();
					// 설명을 적지 않을 시
				}else if(theForm.content.value == ""){
					alert("상품 설명을 작성해 주세요")
					return $("#content").focus();
					// 카테고리 설정 하지 않았을시 알림
				}else if($("#large").val() == '대분류'){
					alert("카테고리를 선택해 주세요");
					return $("#large").focus();
					// 이미지 미선택시 알림
				}else if($("#titleImg").is('[src]') == false){
					alert("이미지는 최소 1장이 필요합니다.")
					return $("#imagin").focus();
					// 가격 작성 하지 않을 시 알림
				}else if(theForm.price.value == ''){
					alert("가격을 작성해 주세요")
					return $("#price").focus();
				}

				theForm.submit();
			}
		}
	
		// 중분류 도출
		$("#large").change(function() {
			let large = $("#large").val()
			console.log(large)
			
			$.ajax({
				url:"largeSelect.do",
				data:{
					large : large
				},
				type:"get",
				success:function(list){
					console.log("Ajax 통신 성공")
					$("#middle").empty()
					$("#small").text()
					if(list.length != 0){
						let examplemiddle = $("<option>").text("중분류").attr("value", "중분류")
						$("#middle").append(examplemiddle)
						$.each(list, function(index, obj){
							let middle = $("<option>").text(obj.name).attr("value", obj.code)
							console.log(obj.name)
							$("#middle").append(middle)
						})
					}else if(list.length == 0){// 대분류로 끝날 때
						let examplemiddle = $("<option>").text("중분류").attr("value", "중분류")
						$("#middle").append(examplemiddle)
					}
				},
				error:function(){
					console.log("Ajax 통신 실패")
					
				}
			})
		})
		
		// 소분류 도출
		$("#middle").change(function() {
			let middle = $("#middle").val()
			console.log(middle)
			
			$.ajax({
				url:"middleSelect.do",
				data:{
					middle : middle
				},
				type:"get",
				success:function(list){
					console.log("Ajax 통신 성공")
					$("#small").empty()
					if(list.length != 0){
						let examplesmall = $("<option>").text("소분류").attr("value", "소분류")
						$("#small").append(examplesmall)
						$.each(list, function(index, obj){
							console.log(obj.name)
							let small = $("<option>").text(obj.name).attr("value", obj.code)
							$("#small").append(small)
						})
					}else if(list.length == 0){ // 중분류로 끝날 때
						let examplemiddle = $("<option>").text("소분류").attr("value", "소분류")
						$("#middle").append(examplemiddle)
					}
				},
				error:function(){
					console.log("Ajax 통신 실패")
					
				}
			})
		})
	
		//결제 방식을 두가지 모드 체크 해제 했을 경우 알림
		$("#one").click(function(){
			if($("#one").is(':checked') == false & $("#two").is(':checked') == false){
				alert("결제방식은 한가지 이상이여야 합니다.")
				$("#two").focus().prop("checked",true)
			}
		})
		$("#two").click(function(){
			if($("#one").is(':checked') == false & $("#two").is(':checked') == false){
				alert("결제방식은 한가지 이상이여야 합니다.")
				$("#two").focus().prop("checked",true)
			}
		})
		
		// 제목 40자 제한
		$(function(){
            $("#title").keydown(function(){
                var inputLength = $(this).val().length;
                var remain = 20-inputLength
                $("#countone").html(inputLength)
                
				if(remain <= 0){
					alert("제목은 20자만 가능합니다.")
					$("#title").focus();
				}
            })
        })
        
		// 상품 설명 4000자 제한
		$(function(){
            $("#content").keydown(function(){
                var inputLength = $(this).val().length;
                var remain = 2000-inputLength
                $("#counter").html(inputLength)

				if(remain <= 0){
					alert("제목은 20자만 가능합니다.")
					$("#content").focus();
				}
            })
        })
        
        
		//let count = 0; // 1, 파일ID file1 fil2
		let i = 1;
		// 카운트로 파일 넘버를 고를 수 있고 카운트가 10이 될 시 알림 울림
		$("#imagin").click(function() {
			console.log($("#file"+i).val())
			console.log(i)
			console.log($("#contentImg9").is('[src]'))
			if($("#contentImg9").is('[src]') == false){
				for(i; i <= 10 ; i++){
					if($("#file"+i).val() == ''){
						$("#file"+i).click();
						break;
					}
				}
			}
			if($("#contentImg9").is('[src]') === true){
				alert("이미지는 10장 까지만 가능합니다.")
			}
		})

		// 파일 로드 후 이미지를 보여줌
		function loadImg(inputFile, num){
			console.log(inputFile.files.length )
			if(inputFile.files.length == 1){
				var reader = new FileReader(); // https://developer.mozilla.org/ko/docs/Web/API/FileReader 사이트 참고
				reader.readAsDataURL(inputFile.files[0]);
				
				reader.onload = function(e){
					switch(num){
					case 1 : $("#titleImg").show(); $("#titleImg").attr("src", e.target.result); break;
					case 2 : $("#contentImg1").show(); $("#contentImg1").attr("src", e.target.result); break;
					case 3 : $("#contentImg2").show(); $("#contentImg2").attr("src", e.target.result); break;
					case 4 : $("#contentImg3").show(); $("#contentImg3").attr("src", e.target.result); break;
					case 5 : $("#contentImg4").show(); $("#contentImg4").attr("src", e.target.result); break;
					case 6 : $("#contentImg5").show(); $("#contentImg5").attr("src", e.target.result); break;
					case 7 : $("#contentImg6").show(); $("#contentImg6").attr("src", e.target.result); break;
					case 8 : $("#contentImg7").show(); $("#contentImg7").attr("src", e.target.result); break;
					case 9 : $("#contentImg8").show(); $("#contentImg8").attr("src", e.target.result); break;
					case 10 : $("#contentImg9").show(); $("#contentImg9").attr("src", e.target.result); break;
					}
				}
			}/*else if(inputFile.files.length > 1){
				var reader = new FileReader(); // https://developer.mozilla.org/ko/docs/Web/API/FileReader 사이트 참고
				for(let i = 0 ; i < 10 ; i++){
					reader.readAsDataURL(inputFile.files[i]);
					switch(i){
					case 1 : $("#titleImg").show(); $("#titleImg").attr("src", e.target.result); break;
					case 2 : $("#contentImg1").show(); $("#contentImg1").attr("src", e.target.result); break;
					case 3 : $("#contentImg2").show(); $("#contentImg2").attr("src", e.target.result); break;
					case 4 : $("#contentImg3").show(); $("#contentImg3").attr("src", e.target.result); break;
					case 5 : $("#contentImg4").show(); $("#contentImg4").attr("src", e.target.result); break;
					case 6 : $("#contentImg5").show(); $("#contentImg5").attr("src", e.target.result); break;
					case 7 : $("#contentImg6").show(); $("#contentImg6").attr("src", e.target.result); break;
					case 8 : $("#contentImg7").show(); $("#contentImg7").attr("src", e.target.result); break;
					case 9 : $("#contentImg8").show(); $("#contentImg8").attr("src", e.target.result); break;
					case 10 : $("#contentImg9").show(); $("#contentImg9").attr("src", e.target.result); break;
					}
				}
			}*/
		}
	</script>
	<div>
		<%@ include file = "../common/footer.jsp" %>
	</div>
</body>
</html>