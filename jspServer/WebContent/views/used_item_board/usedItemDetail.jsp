<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.ArrayList, com.uni.usedItemBoard.model.vo.*"%>
<%
	UsedItemsBoard ub = (UsedItemsBoard)request.getAttribute("ub");
	ArrayList<UsedAttachment> fileLise = (ArrayList<UsedAttachment>)request.getAttribute("fileList"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../../resources/library/swiper.min.css">
<style>
	.main{
		width: 70vw;
	    border: 1px solid;
	    margin: 0 auto;
	}
	#top{
		display: flex;
    	justify-content: center;
	}
	#image{
		width: 200px
	}
</style>
</head>
<body>
	<div>
		<%@include file = "../common/header.jsp" %>
	</div>
	<div class="main">
		<div id="top">
			<% if(ub != null) {%>
			<div id="image">
				<%--<img src="<%=request.getContextPath()%>/resource/usedboard_upfiles/<%= ub.getTitleImg() %>"> --%>
				<!-- swiper슬라이더 메인컨테이너 -->
				<div class="swiper-container">
				  <!-- 보여지는 영역 -->
				  <div class="swiper-wrapper">
				    <!-- div class="swiper-slide" 를 추가하면된다 -->
				    <div class="swiper-slide">
				      
				    </div>
				    <div class="swiper-slide">
				      
				    </div>
				    <div class="swiper-slide">
				      
				    </div>
				    <div class="swiper-slide">
				      
				    </div>
				   </div>
				  <!-- 페이징 버튼 처리 -->
				  <div class="swiper-pagination"></div>
				
				  <!-- 방향 버튼 상황에 따라 추가 삭제가능 -->
				  <div class="swiper-button-prev"></div>
				  <div class="swiper-button-next"></div>
				</div>
			</div>
			<div id="product">
				<div id="titleandsell">
					<div id="title">
						제목
					</div>
					<div id="sell">
						삽니다 팝니다
					</div>
				</div>
				<div class="information">
					<div class="one">
						<div class="status">
							상품상태
						</div>
						<div class="payment">
							결제
						</div>
						<div class="seller">
							판매자
						</div>
						<div class="transaction">
							거래상태
						</div>
					</div>
					<div class="two">
						가격
					</div>
					<div class="three">
						<button id="likebutton">
							<img class="heart" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAABmJLR0QA/wD/AP+gvaeTAAABVklEQVRIie3Wu0oDQRiG4e83SBRBwRQiiq0XoIWHG9FGsLTyFmy0VWMniOCpFAuvQRELQWJhnVh5aKwMmNfCBGY32c1udqcQ88EWM/zMMwd2GKmffxtgCbgAasAXUAVOgbkOtfPAWaj2HFhMAxaAMtH5BrYBa347QCOitgHsAYUkcBzqpgwcJKzd7YYuxMw+a5ZdayBkb0qyxOeSLhtuI4AANUlTnuCqmc1EwXVJg57gupkVW43wVn96QtvGDsNPHuFKHHzlEQ6MHT7jkqQXSUXlm7qkaTN7bXUEVmxm75JOckYl6dhFpQ7/bHPVz5JKOaEfkmbN7K1rJbCe4421lmqawFEO6GHq/QGGgdsM6A0wlBpu4mPAQw/oIzDeE+rgk0AlJTqRCXXwEnCfAL3LvNIO+AhwGYNeA6O5og5uwBbtj4V9IHz9epnAKr8Puxqw4h3s50/mB7ZjI/jDXzG+AAAAAElFTkSuQmCC">
							찜하기
						</button>
						<button>1:1 채팅거래</button>
						<button>구매하기</button>
					</div>
				</div>
			</div>
		</div>
		<div class="compare">
			새상품 비교사이트
			<br>
			네이버쇼핑 사이트
			<br>
			다나와 사이트
		</div>
		<div class="explain">
			상품 설명
			<div class="explainIn">
				qfNxhUeAAkFaI271jXPEdUJE3eBNYA4ABYjuL23qeHIi3wmFAPiPh78nwnPAIrF6OJeViTVUfh7LK8BC4MXSY53bJvOfVIi8gOeLlWwCfQlYJMihSCi4YRb5jotIKLhBxbwKXIZyoLSvZMNU43JGLz2txen7VLBFEw8BEG0plYrvTPT5crrQWpy
			</div>
		</div>
		<% } %>
		<div>
			<div id="relation">
				연관상품

			</div>
			<div id="popular">
				인기상품
			</div>
		</div>
	</div>
	<div>
    	<%@ include file = "../common/footer.jsp" %>
    </div>
    <script src="../../resources/library/swiper.min.js"></script>
    <script>
	    //<!-- Initialize Swiper -->
		const swiper = new Swiper('.swiper-container', {
			//기본 셋팅
			//방향 셋팅 vertical 수직, horizontal 수평 설정이 없으면 수평
			direction: 'horizontal',
			//한번에 보여지는 페이지 숫자
			slidesPerView: 1,
			//페이지와 페이지 사이의 간격
			spaceBetween: 0,
			//드레그 기능 true 사용가능 false 사용불가
			debugger: true,
			//마우스 휠기능 true 사용가능 false 사용불가
			mousewheel: true,
			//반복 기능 true 사용가능 false 사용불가
			loop: true,
			//선택된 슬라이드를 중심으로 true 사용가능 false 사용불가 djqt
			centeredSlides: true,
			// 페이지 전환효과 slidesPerView효과와 같이 사용 불가
			// effect: 'fade',
			speed: 1000,
			//자동 스크를링
			autoplay: {
				//시간 1000 이 1초
				delay: 3500,
				disableOnInteraction: false,
			},
			//페이징
			pagination: {
			    //페이지 기능
			    el: '.swiper-pagination',
			    //클릭 가능여부
			    clickable: true,
			},
        });
    </script>
</body>
</html>