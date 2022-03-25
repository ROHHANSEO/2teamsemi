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
				<input class="filtering" id="minprice" type="text" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" placeholder="최소 금액">  ~  <input id="maxprice" type="text" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" placeholder="최대 금액">
			</div>
			<br>
			<div class="f-1">
				<input id="searchInSearch" class="filtering" type="text" placeholder="검색 내 검색"> <div class="filtering"> <input type="checkbox" id="deal"> 거래완료 게시글 제외하기</div>
				<button id="filter-Apply2" type="button">필터 적용</button>
			</div>
		</div>
		<div id="selectCategory">
				
		</div>
		<div id="end">
			<div id="enddetail">
				<button id="details" type="button"><img src="https://img.icons8.com/fluency-systems-filled/48/000000/view-file.png"/></button>
			</div>
		</div>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
	
		// 대분류, 중분류, 소분류, 최소 금액과 최대 금액, 검색내 검색을 적용하기, 거래완료 게시글 제외하기 버튼 눌렀을 때 가져와 해당 되는 게시글 도출하기
		$('#filter-Apply2').click(function() {
			let cate; // 대중소 분류
			let minprice; // 최소금액
			let maxprice; // 최대금액
			let search; // 검색 내 검색
			let except;// 거래 완료 제외 boolean
			
			
			// 대분류만 선택했을 시
			if($("#large").children("option:selected").text() != '대분류' & $("#middle").children("option:selected").text() == '중분류' & $("#small").children("option:selected").text() == '소분류'){
				cate = $("#large").children("option:selected").text()
				
				// span 태그에 텍스트값을 담고 id 를 부여
				let selectcate = $("<span>").text(cate).attr('id', 'filtercategory')

				// x 버튼을 생성후 class 추가와 type 부여
				let button = $("<button>").text('x').addClass("deleteCategory").attr("type", "button");//type='button' onclick='delete';

				$("#selectCategory").empty() // 비워주기
				$("#selectCategory").append(selectcate).append(button); // 담기
				console.log("대분류만 선택")
			}else if($("#middle").children("option:selected").text() != '중분류' & $("#small").children("option:selected").text() == '소분류'){// 중분류까지 선택 했을 시
				cate = $("#middle").children("option:selected").text()
				
				// span 태그에 텍스트값을 담고 id 를 부여
				let selectcate = $("<span>").text(cate).attr('id', 'filtercategory')

				// x 버튼을 생성후 class 추가와 type 부여
				let button = $("<button>").text('x').addClass("deleteCategory").attr("type", "button");//type='button' onclick='delete';

				$("#selectCategory").empty() // 비워주기
				$("#selectCategory").append(selectcate).append(button); // 담기
				console.log("중분류까지만 선택")
			}else if($("#small").children("option:selected").text() != '소분류'){// 소분류까지 선택 했을 시
				cate = $("#small").children("option:selected").text()
				
				// span 태그에 텍스트값을 담고 id 를 부여
				let selectcate = $("<span>").text(cate).attr('id', 'filtercategory')
				
				// x 버튼을 생성후 class 추가와 type 부여
				let button = $("<button>").text('x').addClass("deleteCategory").attr("type", "button");//type='button' onclick='delete';

				$("#selectCategory").empty() // 비워주기
				$("#selectCategory").append(selectcate).append(button); // 담기
				console.log("소분류까지 선택")
			}else{ // 분류 선택 안했을시
				cate = "대분류";
			}

			// 최소주문금액과 최대 주문금액이 있을시 가져오기
			if($("#minprice").val() != '' & $("#maxprice").val() != ''){
				minprice = $("#minprice").val() // 최소 금액 값 담기
				maxprice = $("#maxprice").val() // 최대 금액 값 담기
			}else if($("#minprice").val() == '' & $("#maxprice").val() != '' | $("#minprice").val() != '' & $("#maxprice").val() == ''){// 둘중 하나만 했을 시 포커스
				alert("금액 범위를 검색할 때 두 금액 모두 작성하셔야 합니다.")
				if($("#minprice").val() == '' & $("#maxprice").val() != ''){
					$("#minprice").focus()
				}else{
					$("#maxprice").focus()
				}
				return false;
			}else{ // 금액범위 안했을 시
				minprice = 0;
				maxprice = 0;
			}

			// 검색내 검색이 있을 시 가져오기
			if($("#searchInSearch").val() != ''){
				search = $("#searchInSearch").val() // 검색내 검색 담기
			}else{ // 없을시
				search = 'null';
			}
			
			// 거래 완료 제외에 체크가 되있을시 가져오기
			if($('#deal').is(':checked')){
				except = $('#deal').is(':checked') // boolean 담기

			}else{
				except = false;
			}
			
			console.log("------")
			console.log("카테고리")
			console.log(cate)
			console.log(typeof(cate))
			console.log("최소금액")
			console.log(minprice)
			console.log(typeof(minprice))
			console.log("최대금액")
			console.log(maxprice)
			console.log(typeof(maxprice))
			console.log("검색내 검색")
			console.log(search)
			console.log(typeof(search))
			console.log("거래완료 안보기")
			console.log(except)
			console.log(typeof(except))
			
			$.ajax({
				url:"fileterling.do",
				data:{
					cate : cate,
					minprice : minprice,
					maxprice : maxprice,
					search : search,
					except : except
				},
				type:'get',
				success:function(obj){// 성공시 결과에 맞는 게시글을 받기
					console.log(obj)
					
					
				}
			})
			
			
		})
		
		
	
		// 대분류 카테고리
		$(function() {
			$.ajax({
				url:"selectLargeBefore.do",
				type:"get",
				success:function(list){
					console.log("Ajax 통신 성공")

					// 대분류를 비워주기
					$("#large").empty()

					if(list.length != 0){
						console.log("여기 탐")
						let examplelarge = $("<option>").text("대분류").attr("value", "대분류")
						$("#large").append(examplelarge)
						$.each(list, function(index, obj){
							let large = $("<option>").text(obj.name).attr("value", obj.code)
							console.log(obj.name)
							$("#large").append(large)
							
						})
					}
				},
				error:function(){
					console.log("Ajax 통신 실패")
					
				}
			})
		})
		
		// 상세 필터 클릭시 보였다 숨겼다
		$("#details").click(function() {
			$("#filter-Apply").toggle()
			$("#detaildiv").toggle()
			$("#filter-Apply2").toggle()
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
					$("#small").empty()

					let examplemiddle = $("<option>").text("중분류").attr("value", "중분류")
					$("#middle").append(examplemiddle)

					if(list.length != 0){
						$.each(list, function(index, obj){

							let middle = $("<option>").text(obj.name).attr("value", obj.code).addClass("middle")
							console.log(obj.name)
							$("#middle").append(middle)
						})
					}
					let examplesmall = $("<option>").text("소분류").attr("value", "소분류")
					$("#small").append(examplesmall)
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
					let examplesmall = $("<option>").text("소분류").attr("value", "소분류")
					$("#small").append(examplesmall)

					if(list.length != 0){
						$.each(list, function(index, obj){

							console.log(obj.name)
							let small = $("<option>").text(obj.name).attr("value", obj.code)
							$("#small").append(small)
						})
					}
				},
				error:function(){
					console.log("Ajax 통신 실패")
				}
			})
		})
	</script>
</body>
</html>