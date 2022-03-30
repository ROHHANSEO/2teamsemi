<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import = "java.util.ArrayList, com.uni.auction.model.vo.*, java.text.DecimalFormat"%>
<%
	Auction ad = (Auction)request.getAttribute("ac");
	ArrayList<AuctionAttachment> fileList = (ArrayList<AuctionAttachment>)request.getAttribute("fileList"); 
	
	DecimalFormat dc = new DecimalFormat("###,###,###,###,###");
	String startprice = dc.format(ad.getItemPrice());//경매 시작가
	String directPrice = dc.format(ad.getItemDirect());//경매 즉시 판매가 
	String upPrice = dc.format(ad.getItemUp());//올리는 값
	String writer = ad.getAuctionId();
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 경매 상세 페이지 </title>
<link rel="stylesheet" href="../../resources/css/common/common.css">
<link rel="stylesheet" href="../../resources/library/swiper.min.css">
<style>
	.wholeDiv{
		border:2px solid black;
		width:65%;
		margin:0 auto;
		height:130vh;
		
	}
	.swiper-button-prev, .swiper-button-next{
		color:#993333;
	}
	.swiper-button-prev:hover, .swiper-button-next:hover{
		color:white;
	}
	.swiper-pagination-bullet-active {
		background: #d30505;
	}
	.swiper-pagination-bullet {
		margin: 3px;
	}
	.swiper-pagination {
    	position: relative;
    }
	.arrow{
		color: #d30505;
	}
	#image, .imageArea{
	    width: 350px;
	    height: 350px;
	}
	.frontTi{
		border:1px solid red;
		display:flex;
		justify-content: space-between;	
	}
	.itemInfo{
		display:flex;
		margin-left:35px;
	}
	.buttonde{
		width:190px;
		height:40px;
		border:0;
		background-color:darkgray;
		color:white;
		border-radius: 5px;
	}
	.buttonde:hover{
		color:white;
		background-color:#993333;
		cursor: pointer;
	}
	.detailInfo{
		margin-left:30px;
		width:60%;
	}
	.fronta{
		margin-top:15px;
		margin-bottom:10px;
		font-size:25px;
		margin-left:10px;
	}
	.frontb img{
		margin-top:15px;
		width:35px;
		height:35px;
		margin-right:10px;
	}
	.detaila{
		font-size:35px;
		border-bottom: 1.5px solid;
		margin-bottom: 15px;
		
	}
	.deti{
		border-bottom: 1px solid;
		padding-bottom: 10px;
	}
	.detailstatusdiv{
		margin-bottom:3px;
		font-size:20px;
	}
	.startP{
		font-size:25px;
		border-bottom: 1px solid;
		display:flex;
		justify-content: space-between;	
		padding-bottom:10px;
		padding-top:10px;
	}
	.startD{
		font-size:20px;
		margin-top:10px;
		margin-bottom:20px;
		display:flex;
	}
	.font40{
		margin-left:10px;
	}
	.buttonS{
		display:flex;
		justify-content: space-between;	
	}
	.contentItem{
		margin-top:50px;
		font-size:20px;
	
	}
	.contentItem p{
		font-weight: 600;
	}
	.contentDi{
		margin-top:15px;
		border:1.5px solid;
		height:200px;
		overflow-Y: scroll;
		padding-left:10px;
		padding-right:10px;
		padding-top:5px;
	}
	table {
	    width: -webkit-fill-available;
	    margin-top: 20px;
	    border-collapse: collapse;
	    text-align: center;
	}
	thead{
		height:35px;
	}
	tbody tr{
   		 height: 30px;
   		 border-bottom:1px solid lightgray;
	}
	.main1{
		position: absolute;
		left:auto;
		transform:translateX(-50px);
		
	}
	.main2{
		position:absolute;
	}

	.main2>li{
		border:1px solid;
		background-color:white;
		width:150px;
		height:30px;
		text-align:center;
		transform:translateX(-120px);
		padding-top:6px;
	}
	.main3>li{
		border:1px solid;
		background-color:white;
		width:150px;
		height:30px;
		text-align:center;
		transform: translate(-152px, -29px);
		padding-top:6px;
	}
	.main2>li:hover, .main3>li:hover{
		background-color:#993333;
	}
	.main2>li a:hover, .main3>li a:hover{
		color:white;
	}
	


</style>

</head>
<body>
	<%@include file = "../common/header.jsp" %>
	<div class = "wholeDiv">
		<%if(ad != null){ %>
		<div class="frontTi">
			<div class="fronta"> <%=ad.getSellStatus() %> </div>
			<div class="frontb"> 
			<ul class="main1">
				<li><a href="#" class="threeB"><img src="../../resources/images/icons8-메뉴-2-96.png" /></a>
					<ul class="main2">
						<%if(user != null && user.getUserId().equals(writer)){ %> 
						<li><a href="#"> 글 수정하기 </a></li>
						<li><a href="deleteAuctionDetail.do?scno=<%=ad.getAuctionNo()%>"> 글 삭제하기 </a></li>
						<li><a href="#" onclick="changeSta()"> 거래 상태 바꾸기 </a>
							<ul class="main3">
								<li><a href="#"	onclick="changeSt()"> 경매중 </a></li>
								<li><a href="#" onclick="changeSt()"> 경매 완료 </a></li>
							</ul>
						</li>
						<%}else if(user != null && !user.getUserId().equals(writer)){ %>
						<li><a href="#"> 게시글 신고 </a></li>
						<li><a href="#"> 게시글 차단 </a></li>
						<%} %>
					</ul>
					
				</li>
			</ul>
			</div>
		</div>
		<div class="itemInfo"><!-- 사진 및 정보 -->
		<div id = "image"><!-- 사진 div -->
			<!-- swiper슬라이더 메인컨테이너 -->
			<div class="swiper-container">
				<!-- 보여지는 영역 -->
			  <div class="swiper-wrapper">
			  <% for(int i = 0; i< fileList.size();i++){ %>
			    <!-- div class="swiper-slide" 를 추가하면된다 -->
			    <div class="swiper-slide">
			      	<img width="350px" height="350px" src="<%=request.getContextPath() %>/resources/auction_upfiles/<%=fileList.get(i).getOriginName()%>">
			    </div>
			   <%} %>
			   </div>
			  
			  <!-- 방향 버튼 상황에 따라 추가 삭제가능 -->
			  <div class="swiper-button-prev"></div>
			  <div class="swiper-button-next"></div>
			</div>
			 <!-- 페이징 버튼 처리 -->
			  <div class="swiper-pagination"></div>
			
		</div>
		<%} %>
		<div class="detailInfo"><!-- 상세 내용 부분 -->
			 <div class="detaila"> <%=ad.getAuctionTitle() %> </div>
			 <div class="deti">
			 <div class="detailstatusdiv">
				<span class="detailstatus">상품 상태 : <%=ad.getItemCondition() %> </span>
			 </div>
			 <div  class="detailstatusdiv">
				<span class="detailstatus"> 작성자 : <%=ad.getAuctionId()%></span>
			 </div>
			 </div>
			 <div class="startP"> <span>경매 시작가</span><span><%=startprice %>원</span></div>
			 <div class="startP"> <span>현재가</span><span><%=startprice %>원</span> </div>
			 <div class="startD"> 
			 	<p class="font15 time-title">경매 마감시간</p> 
			 	<div class="time font40">
			 		<span class="hours"></span>
			 		<span class="col">:</span>
			 		<span class="minutes"></span>
			 		<span class="col">:</span>
			 		<span class="seconds"></span>
			 	</div>
			 </div>
			 <div class="buttonS"> 
			 	<button type="button" class="buttonde" >즉시 구매 <%=directPrice%>원</button>
			 	<button type="button" class="buttonde" >경매가 <%=upPrice%>원 올리기</button>
			 	<button type="button" class="buttonde" >낙찰자 결제하기</button>
			 </div>
		</div>
	</div>
	<div class="contentItem"><!-- 상품 설명 -->
		<p>상품 설명 </p>
		<div class="contentDi">
		<div><%=ad.getAuctionContent() %></div>
		</div>
	</div>
	<div><!-- 입찰 기록 테이블 -->
		<table>
			<thead style="background-color:lightgray;">
				<tr>
					<th> 회원 이름 </th>
					<th> 입찰 금액 </th>
					<th> 입찰 시간 </th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td> 닉네임 </td>
					<td> 505,000원 </td>
					<td> 2022-03-01 19:55:01</td>
				</tr>
				<tr>
					<td> 닉네임 </td>
					<td> 505,000원 </td>
					<td> 2022-03-01 19:55:01</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div><!-- 같은 카테고리 상품 -->
		동일 카테고리 경매 상품 
	</div>
	</div>
	<%@ include file = "../common/footer.jsp" %> 
	<script src="../../resources/library/swiper.min.js"></script>
	<script>
		
		/*경매 상태 변경해주기*/
		$(".changeSt").click(function(){
			var text = $(".changeSt").text();
			console.log(text)
		})
		/* 게시글 수정, 삭제 등 버튼들 나오는 부분*/
		$(document).ready(function(){
			$(".main2").hide();
			$(".main3").hide();
				
		})
		
		$(".threeB").click(function changeli(){
			event.stopPropagation();
			$(".main2").toggle();
		})
		function changeSta(){
			event.stopPropagation();
			$(".main3").toggle();
			
		}
		$(document).click(function(){ 
			$('.main2').hide();
			$('.main3').hide();
		});
		
		/*로그인 안한 사람이 버튼을 눌렀을 시에 알람*/
		<%if(user == null){%>
		$(".threeB").click(function(){
			alert("로그인을 먼저 해주시기 바랍니다.")
		})
		<%}%>
		/*마감 시간*/
		
		function remaindTime(){
			
			var end = '<%=ad.getDatenext()%>';
		
			/*var end1 = end.substr(0,4);
			var end2 = end.substr(4,2);
			var end3 = end.substr(6,2);
			var end4 = end.substr(8,2);
			var end5 = end.substr(10,2);
			var end6 = end.substr(12,2);*/
			
			//var postTime = new Date(now.substr(0,4), now.substr(4,2)-1, now.substr(6,2), now.substr(8,2), now.substr(10,2), now.substr(12,2));
			var nextTime = new Date(end.substr(0,4), end.substr(4,2)-1, end.substr(6,2), end.substr(8,2), end.substr(10,2), end.substr(12,2));
			var nowTime = new Date();
			console.log(nowTime)
			console.log(nextTime)
			var nowt = nowTime.getTime();//지금 시간
			//var pt = postTime.getTime();//이전시간
			var nextt = nextTime.getTime();//이후 시간
			
			//et -> 마감 시간, nt -> 지금 시간
			if(nowt<nextt){
				$(".time").fadeIn();
				sec = parseInt(nextt-nowt)/1000;
				day = parseInt(sec/60/60/24);
				sec= (sec - (day*60*60*24));
				hour = parseInt(sec/60/60);
			    sec = (sec - (hour*60*60));
			    min = parseInt(sec/60);
			    sec = parseInt(sec-(min*60));
			    if(hour<10){hour="0"+hour;}
			    if(min<10){min="0"+min;}
			    if(sec<10){sec="0"+sec;}
			    $(".hours").html(hour);
			    $(".minutes").html(min);
			    $(".seconds").html(sec);
			}
		}
		setInterval(remaindTime,1000);
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
		// 방향버튼
		navigation: {
		    nextEl: '.swiper-button-next',
		    prevEl: '.swiper-button-prev',
		},
    });

    </script>
</body>
</html>