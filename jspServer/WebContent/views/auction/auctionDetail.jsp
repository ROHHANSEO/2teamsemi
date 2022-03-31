<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import = "java.util.ArrayList, com.uni.auction.model.vo.*, java.text.DecimalFormat"%>
<%
	Auction ad = (Auction)request.getAttribute("ac");
	ArrayList<AuctionAttachment> fileList = (ArrayList<AuctionAttachment>)request.getAttribute("fileList"); 
	ArrayList<sellRecord> sr = (ArrayList<sellRecord>)request.getAttribute("sr");
	DecimalFormat dc = new DecimalFormat("###,###,###,###,###");
	String startprice = dc.format(ad.getItemPrice());//경매 시작가
	String directPrice = dc.format(ad.getItemDirect());//경매 즉시 판매가 
	String upPrice = dc.format(ad.getItemUp());//올리는 값
	String writer = ad.getAuctionId();

	int firstI = 0;//맨첫번째 값
	String tot = null;
	String tot2 = null;
	tot = sr.get(firstI).getChangeP();
	tot2 = sr.get(firstI).getUserNo();//낙찰자 아이디
	
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

	.buttondefi, .buttondee, .a, .b{
		width:190px;
		height:40px;
		border:0;
		background-color:darkgray;
		color:white;
		border-radius: 5px;
	}
	.buttondee:hover{
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
		cursor: pointer;
	}
	.main2>li a:hover, .main3>li a:hover{
		color:white;
	}
	.kk{
		background-color:#993333;
		cursor: pointer;
		color:white;
	}
	.auctionF{
		font-width:500px;
		text-align:center;
	}
	.topdeall{
		margin-top:25px;
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
						<li><a href="#" onclick="deleteItem()"> 글 삭제하기 </a></li>
						<%}else if(user != null && !user.getUserId().equals(writer)){ %>
						<li><a href="#" onclick="blockAuction()"> 게시글 신고 </a></li>
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
			 <div class="startP"> <span>현재가</span><span class="nowP"><%=tot%>원</span> </div>
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
			 	<button type="button" class="buttonde buttondee a" >즉시 구매 <%=directPrice%>원</button>
			 	<button type="button" class="buttonde buttondee b" onclick="upPrice()" >경매가 <%=upPrice%>원 올리기</button>
			 	<button type="button" class="buttondefi c" onclick="paydeal()" disabled>낙찰자 결제하기</button>
			 </div>
		</div>
	</div>
	<div class="contentItem"><!-- 상품 설명 -->
		<p>상품 설명 </p>
		<div class="contentDi">
		<div><%=ad.getAuctionContent() %></div>
		</div>
	</div>
	<table class="topdeall">
	<thead style="background-color:lightgray;">
			<tr>
				<th width="20%"> 회원 이름 </th>
				<th width="40%"> 입찰 금액 </th>
				<th width="40%"> 입찰 시간 </th>
			</tr>
		</thead>
	</table>
	<div style="width:100%; height:300px; overflow:auto"><!-- 입찰 기록 테이블 -->
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			
			<tbody class="auctionF">
				<%--<tr>
					<td> 닉네임 </td>
					<td> 505,000원 </td>
					<td> 2022-03-01 19:55:01</td>
				</tr>
				<tr>
					<td> 닉네임 </td>
					<td> 505,000원 </td>
					<td> 2022-03-01 19:55:01</td>
				</tr> --%>
				<%for(sellRecord ss: sr){ %><!-- 낙찰받은 내용 리스트 -->
				<tr>
					<td width="20%"><%=ss.getUserNo() %></td><!-- 유저닉네임 -->
					<td width="42%"><%=ss.getChangeP() %></td>
					<td width="38%"><%=ss.getGetTime() %></td>
				</tr>
				<%} %>
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
		/*로그인 안한 사람이 버튼을 눌렀을 시에 알람*/
		<%if(user == null){%>
			$(".threeB").click(function(){
				alert("로그인을 먼저 해주시기 바랍니다.")
			})
		
		<%}%>
		<%if(user == null){%>
			$(".a").attr("disabled", true);
			$(".b").attr("disabled", true);
			$(".buttonde").removeClass("buttondee");
			
		<%}%>
		/*글삭제 알림 먼저 뜨고 삭제0*/
		function deleteItem(){
			var alertt = confirm("글을 삭제하시겠습니까?");
			if(alertt){
				location.href="deleteAuctionDetail.do?scno="+<%=ad.getAuctionNo()%>;
			}else{
				return;
			}
			
		}
		/*글신고 버튼*/
		function blockAuction(){
			var alertt = confirm("글을 신고하시겠습니까?");
			if(alertt){//확인 누를시에 
				window.name = "auctionBlock";
	    		 window.open("<%= request.getContextPath()%>/blockAuction.do?scno=<%=ad.getAuctionNo()%>&btitle=<%=ad.getAuctionTitle()%>","_blank", "width=500, height=330, top=350, left=600");
				
			}else{
				alert("취소되었습니다")
				return;
			}
		}
		//테스트완료
		let timerId = setInterval(function(){
					
			var end = '<%=ad.getDatenext()%>';
		
			/*var end1 = end.substr(0,4);
			var end2 = end.substr(4,2);
			var end3 = end.substr(6,2);
			var end4 = end.substr(8,2);
			var end5 = end.substr(10,2);
			var end6 = end.substr(12,2);*/

			//var nextTime = new Date(end.substr(0,4), end.substr(4,2)-1, end.substr(6,2), end.substr(8,2), end.substr(10,2), end.substr(12,2));
			var nowTime = new Date();
			var nextTime = new Date(nowTime.getFullYear(),nowTime.getMonth(),nowTime.getDate(), 19, 03, 00);
			//성공
			console.log(nowTime)
			
			var nowt = nowTime.getTime();//지금 시간
			var nextt = nextTime.getTime();//이후 시간
			//console.log(pt)
			
			
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
			}else if(nowt>nextt){
				clearTimeout(timerId);
				//경매 종료로 내용 변경
				$("p.time-title").html("경매 종료");
				//옆에 시간 부분 사라지게 하기
				$(".time").fadeOut();
				
				setTimeout("changeevery();",2000);
				
				//모든 사람들 다 버튼 만지지 못하도록 버튼 이벤트 적용
				/*$(".a").attr("disabled", true);
				$(".b").attr("disabled", true);
				$(".buttonde").removeClass("buttondee");
				//만약에 낙찰자면 버튼이 활성화 되게 하기
				/*$(".buttondefi").attr("disabled",false);
				//버튼 호버하였을시에 첫번째 기능, 두번째는 평상시
				$(".buttondefi").hover(function(){
					$(this).addClass("kk");
				}, function(){
					$(this).removeClass("kk");
				})
				//경매 시간 끝나고서는 다른 두 버튼들은 사용 안되도록 하기
				$(".buttondee").attr("disabled",true);
				//다른 두 버튼 호버 적용도 삭제
				$(".buttondee").removeClass("buttonde");
				*/
			}
			 
		},1000);
		
		function changeevery(){
			
			<%if(user == null){%>
				var alertv = confirm("경매가 마감되었습니다. 메인으로 나가시겠습니까?");
				if(alertv){
				 	location.href="<%=request.getContextPath()%>/auctionPage.do";
				}else{
					return;
				}
			<%} else if(user != null && user.getUserId().equals(tot2)){%><!-- 낙찰자 -->
				$(".a").attr("disabled", true);
				$(".b").attr("disabled", true);
				$(".buttonde").removeClass("buttondee");
				$(".buttondefi").attr("disabled",false);
				$(".buttondefi").hover(function(){
					$(this).addClass("kk");
				}, function(){
					$(this).removeClass("kk");
				})
				
			<%}else if(user != null && !user.getUserId().equals(tot2)){%>
				$(".a").attr("disabled", true);
				$(".b").attr("disabled", true);
				$(".buttonde").removeClass("buttondee");
				var alertt = confirm("경매가 마감되었습니다. 메인으로 나가시겠습니까?");
				if(alertt){
				 	location.href="<%=request.getContextPath()%>/auctionPage.do";
				}else{
					return;
				}
			<%}%>
		}
		
		/*경매가 올리기 버튼을 눌렀을 시에*/
		
		function upPrice(){
			<% if(user ==null){%>
				alert("로그인 해주시길 바랍니다")
			<%}else if(user != null && !ad.getAuctionId().equals(user.getUserId())){%>
			var up = <%=ad.getItemUp()%>
			console.log(up)//올릴 값 잘 출력되는거 확인
			var scno = <%=ad.getAuctionNo()%>//게시글 번호
			var now = $(".nowP").text().replace(/,/g,"").slice(0,-1);
			console.log(now)
			var changeP = Number(up)+Number(now);//둘이 합친 현재값
			console.log(changeP)
			
			var dealert = '<%=user.getUserId()%>';
			console.log(dealert)

			$.ajax({
				url:"changeprice.do",
				data:{
					scno:scno, 
					changeP:changeP
				}, 
				type:"post", 
				success:function(a){
					alert("성공")
					console.log(a)
					location.reload();
					<%--$(".nowP").empty();//안에 내용을 비워주다
					$(".nowP").text(a.itemPrice+'원');//위에 현재 가격 적어주기
					var dealer = $("<td>").text('<%=user.getUserId()%>');//회원이름
					var price = $("<td>").text(a.itemPrice);//오른가격
					var today = new Date();
					//년월일
					var year = today.getFullYear();
					var month = ('0' + (today.getMonth() + 1)).slice(-2);
					var day = ('0' + today.getDate()).slice(-2);
					//시분초
					var hours = ('0' + today.getHours()).slice(-2); 
					var minutes = ('0' + today.getMinutes()).slice(-2);
					var seconds = ('0' + today.getSeconds()).slice(-2);
					var dateString = year + '/' + month  + '/' + day +" " + hours + ':' + minutes  + ':' + seconds;

					console.log(dateString);
					var time = $("<td>").text(dateString);//날짜
					
					var tr = $("<tr>").append(dealer,price,time);
					$(".auctionF").prepend(tr);--%>
				}, 
				error:function(e){
					alert("실패")
				}
			})
			<%}else if(ad.getAuctionId().equals(user.getUserId())){%>
				alert("게시물 작성자는 가격을 올릴 수 없습니다.")
			<%}%>
		}
		
		function paydeal(){
			alert("낙찰자 결제 성공~!")
		}

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