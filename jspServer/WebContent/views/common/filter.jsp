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
	<link rel="stylesheet" href="../../resources/css/usedItemsBoard/usedItemBoardList.css">
<style type="text/css">
	
</style>
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
	
		$('#filter-Apply').click(function(){
			$("#selectCategory").empty() // 비워주기
			let cate; // 대중소 분류
			let minprice; // 최소금액
			let maxprice; // 최대금액
			let search; // 검색 내 검색
			let except;// 거래 완료 제외 boolean
			
			
			// 대분류만 선택했을 시
			if($("#large").children("option:selected").text() != '대분류' & $("#middle").children("option:selected").text() == '중분류' & $("#small").children("option:selected").text() == '소분류'){
				cate = $("#large").children("option:selected").val()
				
				let select = $("#large").children("option:selected").text()
				
				// x 버튼을 생성후 class 추가와 type 부여
				let button = $("<button>").text('x').addClass("deleteCategory").attr("type", "button");//type='button' onclick='delete';
				
				// span 태그에 텍스트값을 담고 id 를 부여
				let selectcate = $("<span>").text(select).addClass('filtercategory').append(button);

				$("#selectCategory").empty() // 비워주기
				$("#selectCategory").append(selectcate) // 담기
				console.log("대분류만 선택")
			}else if($("#middle").children("option:selected").text() != '중분류' & $("#small").children("option:selected").text() == '소분류'){// 중분류까지 선택 했을 시
				cate = $("#middle").children("option:selected").val()
				
				let select = $("#middle").children("option:selected").text()
				
				// x 버튼을 생성후 class 추가와 type 부여
				let button = $("<button>").text('x').addClass("deleteCategory").attr("type", "button");//type='button' onclick='delete';
				
				// span 태그에 텍스트값을 담고 id 를 부여
				let selectcate = $("<span>").text(select).addClass('filtercategory').append(button);

				$("#selectCategory").empty() // 비워주기
				$("#selectCategory").append(selectcate) // 담기
				console.log("중분류까지만 선택")
			}else if($("#small").children("option:selected").text() != '소분류'){// 소분류까지 선택 했을 시
				cate = $("#small").children("option:selected").val()
				
				let select = $("#middle").children("option:selected").text()
								
				// x 버튼을 생성후 class 추가와 type 부여
				let button = $("<button>").text('x').addClass("deleteCategory").attr("type", "button");//type='button' onclick='delete';

				// span 태그에 텍스트값을 담고 id 를 부여
				let selectcate = $("<span>").text(select).addClass('filtercategory').append(button);

				$("#selectCategory").empty() // 비워주기
				$("#selectCategory").append(selectcate) // 담기
				console.log("소분류까지 선택")
			}else{ // 분류 선택 안했을시
				cate = "대분류";
			}

			// 최소주문금액과 최대 주문금액이 있을시 가져오기
			if($("#minprice").val() != '' & $("#maxprice").val() != ''){
				minprice = $("#minprice").val() // 최소 금액 값 담기
				maxprice = $("#maxprice").val() // 최대 금액 값 담기
				
				// x 버튼을 생성후 class 추가와 type 부여
				let button = $("<button>").text('x').addClass("deleteCategory").attr("type", "button");//type='button' onclick='delete';
				
				// span 태그에 텍스트값을 담고 id 를 부여
				let selectcate = $("<span>").text(minprice+" ~ "+maxprice).addClass('filtercategory').append(button);
			
				$("#selectCategory").append(selectcate).append(button);
			}else if($("#minprice").val() == '' & $("#maxprice").val() != '' | $("#minprice").val() != '' & $("#maxprice").val() == ''){// 둘중 하나만 했을 시 포커스
				alert("금액 범위를 검색할 때 두 금액 모두 작성하셔야 합니다.")
				if($("#minprice").val() == '' & $("#maxprice").val() != ''){
					$("#minprice").focus()
					
					return false;
				}else{
					$("#maxprice").focus()
					
					return false;
				}
				return false;
			}else{ // 금액범위 안했을 시
				minprice = 0;
				maxprice = 0;
			}

			// 검색내 검색이 있을 시 가져오기
			if($("#searchInSearch").val() != ''){
				search = $("#searchInSearch").val() // 검색내 검색 담기
				
				// x 버튼을 생성후 class 추가와 type 부여
				let button = $("<button>").text('x').addClass("deleteCategory").attr("type", "button");//type='button' onclick='delete';
				
				// span 태그에 텍스트값을 담고 id 를 부여
				let selectcate = $("<span>").text(search).addClass('filtercategory').append(button);
				
				$("#selectCategory").append(selectcate) // 담기
			}else{ // 없을시
				search = 'null';
			}
			
			// 거래 완료 제외에 체크가 되있을시 가져오기
			if($('#deal').is(':checked')){
				except = $('#deal').is(':checked') // boolean 담기
				
				let deal = "거래완료 게시글 제외하기";
				
				// x 버튼을 생성후 class 추가와 type 부여
				let button = $("<button>").text('x').addClass("deleteCategory").attr("type", "button");//type='button' onclick='delete';
				
				// span 태그에 텍스트값을 담고 id 를 부여
				let selectcate = $("<span>").text(deal).addClass('filtercategory').append(button);
				$("#selectCategory").append(selectcate) // 담기
			}else{
				except = false;
			}
			
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
				success:function(list){// 성공시 결과에 맞는 게시글을 받기
					console.log(list)
					
					let article = $("#article2")
					article.empty();	
					if(list.length != 0){
						$.each(list, function(index, obj){
							
							
							let listprice = obj.price;
							let listcondition = obj.itemCondition;
							let listtitle = obj.usedBoardTitle;
							let listlike = obj.likeCount;
							let titleimage = obj.titleImg;
							let bNo = obj.usedBoardNo;
							
							let price = $("<span>").addClass("price").html(listprice+"원")
							let status = $("<span>").addClass("status").append(listcondition)
							let secondLine = $("<div>").addClass("secondLine").append(price).append(status);
							
							let title = $("<span>").addClass("title").html(listtitle)
							let heart = $("<img>").attr('src', "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAABmJLR0QA/wD/AP+gvaeTAAAGnElEQVRoge2Ze3BcdRXHP+d38+oDYoodNpttHpuMtcIflYICMvXBFBxnaCsSndqhM8UpWHTGsZ1p4kyzXpLSWoEGO4KSQeTlDIg0pTgM1skMzugwoK2g0ocmm6TJ7oZWkiK2Scje3/GP3U03bR67eaz8ke8/+7vnnnvO93vPfZxzF+Yxj3nMYx7/B0imjpGdVcsxst7AGoUyYBmQB/SjvAPyBxzvN36360Qm8aI7K1aIybtDVVcjXAUsAeJAD0qvIr9XRw8G3PA/Z0VI1A1eqx57RfhSBvEU5LA1Xn3A7XprPIdIQ82nRXQP6C2Z5BdoU5U6f1PHkSn8JmB096r8qG+gWeDepF8/0KpGDxHnpDN0vndw8QKvYMRZimNXishXUL4BlACeCvf7j4Ub5QU8AK3Fia2ovg+0HnCS8Z43hle8EXn7w3zvzIL/Djpe0cIAxvmkEbtWlfUkKqWC/tRnKraJ+1o8YyHd9eUl+QV5LwJfBM4juq9QnAeucNv/M9lZObNj+WUfFo1sF6gHCkGeKT3esRkgtiL4FLARGBbYkzeUv2/pj09+MFm899yay4et7gDdBiwQaBsy9o4qt+vslEL07lX5Md/A75IielTNurKm9r9OlvBi9LqVK401LwHlwK+S5o1At1VdF2jqfDubeNGG6lWIHgQCAm0+U/7liytjLjnIN9CcEhE3I9dnKwIg4Ha9haergZ6kgI3AKQyrsxUB4G/qOBI3I58FehVujmn3Qxf7jKlI1A1ei+VNYFDV3DQdEWPiJc7knwAw5ka/2350RvHcmmuw9o9AIcZclx5vTEXUYy8giO6bqQhInElVCalKaKYiAPxu+1FEmgGDZ/em7xutSGRn1XIxcgLoLzSmaqobO1NoLQ5A6uk1U/TXBYuHCukESjzLJ5btCv8L0ioiIl9NLltnSwQkBMyWCIAle8PvCxwEyDOyPmVPv7RuBlDMy7OVdK6gyMuJX12Tsl0QIlQAWKPv5JxZllBrjyWX5SlbekVKAQop6sslqekgP29hJLksS9nShTgA5zg3bgvwUUKcAZtcjvK/cLMjAwAO3pIc88oaloUpjv0pW1pF9N8AeV5BaU5ZTQOOZ1Mc30vZRoWo8Lfk6rqcspoORD+TWOjoqHBBiNU3Eha9Ice0soaKXA8gKm+mbBfuEbVtSa+1nW5lUc7ZZYie7wcWoKwFsKptKfuoEP+u7uPAUeBjRVZuyz3FzGAWF64DLgf+XLar8+SoPd1JkSeTv/WaxTyfKyiIiNYBCPwyfd8YIfaD4ceBGHBNLBS8PXcUM0OfW/U1YCUQGTJ2YiHLmnsHVXV3cvPh/rpgcY44Ton+umCxWnkYQJTdVW7XUPr+SyZEv1Pxc+AvQGC4kObc0JwaQ0W6n0RL8obvRPixi/dfIkTc1+JGuQsYVtgcDVXfkwOekyIWCm5FZRMwJMbcNd5YcIkQAF9T+O+ofjexpfsjoao14/nlAtFQ5a0KyUtKtpa67cfG8xtXCIC/qfNxhUeAAkFaI271jXPEdUJE3eBNYA4ABYjuL23qeHIi3wmFAPiPh78nwnPAIrF6OJeViTVUfh7LK8BC4MXSY53bJvOfVIi8gOeLlWwCfQlYJMihSCi4YRb5jotIKLhBxbwKXIZyoLSvZMNU43JGLz2txen7VLBFEw8BEG0plYrvTPT5crrQWpy+FcH7FXYAgvBsqZRvziRPxm9vBYmGgj8UaAAMqq+OjHjfrPjRqYGZkE+hu768JD8/7zmEWwAr0OhrDDcKaCbHZ92GREJVtwnyDFAMdFo1GwNN7a9nG2dMzIbqz4nos0AlcNYY7vS54d9mE2Na/VSPW13jWG0FrgY8gQd9fSUN0nJkJJs46n4hL2pPbRdoAvKBE1jv9mQDmxWm3Riedq9aHLdD+0C3JALp68Y6d165q70jk+N73OqaPGufVuQGEpdPizl/brvvwXfPTYfPjDvcaKjyVsF5QlE/MKhwn9+EHxAXO56/gsQaqrcg+hCwGDitRreUuZ2HZsJjVlr12A9qlmq+fQxIfK1UDlvHfCvgtvem+/W6NQFj7RPAmqTfAYmbb5fuaT8zUw6zOnPEQlW1ijwKfBx4X5F7yho7ngeIhGrWCd4vQK4AzpL4O61ltnLP+vB02q30xT3zM4T1AAqPIIgo9yZdWp24br1yd+e7s5l3zqbAWEP1JhV9FFiUNA0B9f7G8E/mIt+cjrN9bvXV1vJrAGP4us/t+Mdc5ptT9NcFiz9Kk+Y8coX/AaVlcZ96lfnPAAAAAElFTkSuQmCC").addClass("heart");
							let count = $("<span>").html(listlike)
							let span = $("<span>").html(heart)
							let like = $("<span>").addClass("like").html(span).append(count);
							let firstLine = $("<div>").append(title).append(like).addClass("firstLine");
							
							let bottom = $("<div>").addClass("bottom").append(firstLine).append(secondLine);
							let image = $("<img>").attr('src', "<%=request.getContextPath() %>/resources/usedboard_upfiles/"+titleimage).addClass("image");
							let aTag = $("<a>").attr('href', '<%=request.getContextPath()%>/detailview.do?bNo='+bNo).append(image, '<br>', bottom);
							let thumnail = $("<div>").addClass("thumbnail").html(aTag);
							
							article.append(thumnail);
						})
					}
				}
			})
			
			
		})

		// 대분류, 중분류, 소분류, 최소 금액과 최대 금액, 검색내 검색을 적용하기, 거래완료 게시글 제외하기 버튼 눌렀을 때 가져와 해당 되는 게시글 도출하기
		$('#filter-Apply2').click(function() {
			$("#selectCategory").empty() // 비워주기
			let cate; // 대중소 분류
			let minprice; // 최소금액
			let maxprice; // 최대금액
			let search; // 검색 내 검색
			let except;// 거래 완료 제외 boolean
			
			
			// 대분류만 선택했을 시
			if($("#large").children("option:selected").text() != '대분류' & $("#middle").children("option:selected").text() == '중분류' & $("#small").children("option:selected").text() == '소분류'){
				cate = $("#large").children("option:selected").val()
				
				let select = $("#large").children("option:selected").text()
				
				// x 버튼을 생성후 class 추가와 type 부여
				let button = $("<button>").text('x').addClass("deleteCategory").attr("type", "button");//type='button' onclick='delete';
				
				// span 태그에 텍스트값을 담고 id 를 부여
				let selectcate = $("<span>").text(select).addClass('filtercategory').append(button);

				$("#selectCategory").empty() // 비워주기
				$("#selectCategory").append(selectcate) // 담기
				console.log("대분류만 선택")
			}else if($("#middle").children("option:selected").text() != '중분류' & $("#small").children("option:selected").text() == '소분류'){// 중분류까지 선택 했을 시
				cate = $("#middle").children("option:selected").val()
				
				let select = $("#middle").children("option:selected").text()
				
				// x 버튼을 생성후 class 추가와 type 부여
				let button = $("<button>").text('x').addClass("deleteCategory").attr("type", "button");//type='button' onclick='delete';
				
				// span 태그에 텍스트값을 담고 id 를 부여
				let selectcate = $("<span>").text(select).addClass('filtercategory').append(button);

				$("#selectCategory").empty() // 비워주기
				$("#selectCategory").append(selectcate) // 담기
				console.log("중분류까지만 선택")
			}else if($("#small").children("option:selected").text() != '소분류'){// 소분류까지 선택 했을 시
				cate = $("#small").children("option:selected").val()
				
				let select = $("#middle").children("option:selected").text()
								
				// x 버튼을 생성후 class 추가와 type 부여
				let button = $("<button>").text('x').addClass("deleteCategory").attr("type", "button");//type='button' onclick='delete';

				// span 태그에 텍스트값을 담고 id 를 부여
				let selectcate = $("<span>").text(select).addClass('filtercategory').append(button);

				$("#selectCategory").empty() // 비워주기
				$("#selectCategory").append(selectcate) // 담기
				console.log("소분류까지 선택")
			}else{ // 분류 선택 안했을시
				cate = "대분류";
			}

			// 최소주문금액과 최대 주문금액이 있을시 가져오기
			if($("#minprice").val() != '' & $("#maxprice").val() != ''){
				minprice = $("#minprice").val() // 최소 금액 값 담기
				maxprice = $("#maxprice").val() // 최대 금액 값 담기
				
				// x 버튼을 생성후 class 추가와 type 부여
				let button = $("<button>").text('x').addClass("deleteCategory").attr("type", "button");//type='button' onclick='delete';
				
				// span 태그에 텍스트값을 담고 id 를 부여
				let selectcate = $("<span>").text(minprice+" ~ "+maxprice).addClass('filtercategory').append(button);
			
				$("#selectCategory").append(selectcate).append(button);
			}else if($("#minprice").val() == '' & $("#maxprice").val() != '' | $("#minprice").val() != '' & $("#maxprice").val() == ''){// 둘중 하나만 했을 시 포커스
				alert("금액 범위를 검색할 때 두 금액 모두 작성하셔야 합니다.")
				if($("#minprice").val() == '' & $("#maxprice").val() != ''){
					$("#minprice").focus()
					
					return false;
				}else{
					$("#maxprice").focus()
					
					return false;
				}
				return false;
			}else{ // 금액범위 안했을 시
				minprice = 0;
				maxprice = 0;
			}

			// 검색내 검색이 있을 시 가져오기
			if($("#searchInSearch").val() != ''){
				search = $("#searchInSearch").val() // 검색내 검색 담기
				
				// x 버튼을 생성후 class 추가와 type 부여
				let button = $("<button>").text('x').addClass("deleteCategory").attr("type", "button");//type='button' onclick='delete';
				
				// span 태그에 텍스트값을 담고 id 를 부여
				let selectcate = $("<span>").text(search).addClass('filtercategory').append(button);
				
				$("#selectCategory").append(selectcate) // 담기
			}else{ // 없을시
				search = 'null';
			}
			
			// 거래 완료 제외에 체크가 되있을시 가져오기
			if($('#deal').is(':checked')){
				except = $('#deal').is(':checked') // boolean 담기
				
				let deal = "거래완료 게시글 제외하기";
				
				// x 버튼을 생성후 class 추가와 type 부여
				let button = $("<button>").text('x').addClass("deleteCategory").attr("type", "button");//type='button' onclick='delete';
				
				// span 태그에 텍스트값을 담고 id 를 부여
				let selectcate = $("<span>").text(deal).addClass('filtercategory').append(button);
				$("#selectCategory").append(selectcate) // 담기
			}else{
				except = false;
			}
			
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
				success:function(list){// 성공시 결과에 맞는 게시글을 받기
					console.log(list)
					
					let article = $("#article2")
					article.empty();	
					if(list.length != 0){
						$.each(list, function(index, obj){
							
							
							let listprice = obj.price;
							let listcondition = obj.itemCondition;
							let listtitle = obj.usedBoardTitle;
							let listlike = obj.likeCount;
							let titleimage = obj.titleImg;
							let bNo = obj.usedBoardNo;
							
							let price = $("<span>").addClass("price").html(listprice+"원")
							let status = $("<span>").addClass("status").append(listcondition)
							let secondLine = $("<div>").addClass("secondLine").append(price).append(status);

							let title = $("<span>").addClass("title").html(listtitle)
							let heart = $("<img>").attr('src', "data:image/png;base64,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").addClass("heart");
							let count = $("<span>").html(listlike)
							let span = $("<span>").html(heart)
							let like = $("<span>").addClass("like").html(span).append(count);
							let firstLine = $("<div>").append(title).append(like).addClass("firstLine");
							
							let bottom = $("<div>").addClass("bottom").append(firstLine).append(secondLine);
							let image = $("<img>").attr('src', "<%=request.getContextPath() %>/resources/usedboard_upfiles/"+titleimage).addClass("image");
							let aTag = $("<a>").attr('href', '<%=request.getContextPath()%>/detailview.do?bNo='+bNo).append(image, '<br>', bottom);
							let thumnail = $("<div>").addClass("thumbnail").html(aTag);
							
							article.append(thumnail);
						})
					}else{
						article.append("찾으시는 게시물이 없습니다.");
					}
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