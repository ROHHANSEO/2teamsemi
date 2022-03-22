<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	boolean bol = false;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="../../resources/css/filter/filter.css">
</head>
<body>
	<div id="filter">
		<div id="filterdiv">
			<select id="large">
				<option value="대분류">대분류</option>
			</select>
			<select id="middle">
				<option value="중분류">중분류</option>
			</select>
			<select id="small">
				<option value="소분류">소분류</option>
			</select>
			<button id="filter-Apply" type="button">필터 적용</button>
		</div>
		<div id="detaildiv">
			<br>
			<div class="f-1">
				<input class="filtering" id="maginright" type="text" placeholder="최소 금액">  ~  <input id="maginleft" type="text" placeholder="최대 금액">
			</div>
			<br>
			<div class="f-1">
				<input class="filtering" type="text" placeholder="검색 내 검색"> <div class="filtering"> <input type="checkbox"> 거래완료 게시글 제외하기</div>
				<button id="filter-Apply2" type="button">필터 적용</button>
			</div>
		</div>
		<div id="end">
			<div id="enddetail">
				<button id="details" type="button"><img src="https://img.icons8.com/fluency-systems-filled/48/000000/view-file.png"/></button>
			</div>
		</div>
		<script>
			$("#details").click(function() {
				$("#filter-Apply").toggle()
				$("#detaildiv").toggle()
				$("#filter-Apply2").toggle()
			})
		</script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<script>
			// 대분류 선택시 선택한 카테고리 
			$("#large").change(function() {
				let selectcate = $("<div>")+$("#large").text();
				let button = $("<button>").text('x').addClass("deleteCategory").attr("type", "button");//type='button' onclick='delete';
				$("#selectCategory").append(selectcate, button);
			})	
			
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
						if(list.length != 0){
							$("#middle").show(); // 존재하면 보인다
							$.each(list, function(index, obj){
								let middle = $("<option>").text(obj.name).attr("value", obj.code)
								console.log(obj.name)
								$("#middle").append(middle)
								
							})
						}else if(list.length == 0){// 대분류로 끝날 때
							$("#middle").hide()
							$("#small").hide()
							
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
							$("#small").show(); // 존재하면 보인다
							$.each(list, function(index, obj){
								console.log(obj.name)
								let small = $("<option>").text(obj.name).attr("value", obj.name)
								$("#small").append(small)
								/*$(".deletecate").html('');
								$("#categorydiv").appped("<div>"obj.name"<button class='deletecate' type='button' onclick='delete'>"x"</button></div>")*/
							})
						}else if(list.length == 0){ // 중분류로 끝날 때
							$("#small").hide()
							/*$(".deletecate").html('');
							$("#categorydiv").appped("<div>"middle"<button class='deletecate' type='button' onclick='delete'>"x"</button></div>")*/
						}
					},
					error:function(){
						console.log("Ajax 통신 실패")
						
					}
				})
			})
		</script>
	</div>
</body>
</html>