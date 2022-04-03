<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.ArrayList, com.uni.usedItemBoard.model.vo.*"%>
<%
	UsedItemsBoard ub = (UsedItemsBoard)request.getAttribute("ub");
	ArrayList<UsedAttachment> fileList = (ArrayList<UsedAttachment>)request.getAttribute("fileList");
	ArrayList<LikeProduct> like = (ArrayList<LikeProduct>)request.getAttribute("like"); 
	int writerNo= Integer.parseInt(ub.getUsedBoardWriter());//글 작성자 
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../../resources/library/swiper.min.css">
<link rel="stylesheet" href="../../resources/css/usedItemsBoard/Detail.css">
</head>
<body>
	<div>
		<%@include file = "../common/header.jsp" %>
	</div>
	<div class="main">
		<div id="top">
			<% if(ub != null) {%>
				<div class="categorycode" style="display: none;"><%= ub.getCategorycode() %></div>
				<div class="bNo" style="display: none;"><%= ub.getUsedBoardNo() %></div>
			<% if(user != null){ %>
				<div class="uNo" style="display: none;"><%= user.getUserNo() %></div>
			<% } %>
			<div id="image">
				<!-- swiper슬라이더 메인컨테이너 -->
				<div class="swiper-container imageArea">
					<!-- 보여지는 영역 -->
					<div class="swiper-wrapper">
					<!-- div class="swiper-slide" 를 추가하면된다 -->
					<% for(int i = 0 ; i < fileList.size() ; i++){ %>
						<div class="swiper-slide">
							<img width="400px" height="400px" src="<%= request.getContextPath() %>/resources/usedboard_upfiles/<%= fileList.get(i).getChangeName() %>">
						</div>
					<% } %>
					</div>
					<!-- 방향 버튼 상황에 따라 추가 삭제가능 -->
					<div class="swiper-button-prev arrow"></div>
					<div class="swiper-button-next arrow"></div>
				</div>
				<!-- 페이징 버튼 처리 -->
				<div class="swiper-pagination"></div>
			</div>
			<div id="product">
				<div class="flexin">
					<div id="titleandsell">
						<div id="title" class="product_name"><%= ub.getUsedBoardTitle() %></div>
						<% if(ub.getCategorycode().equals("1160000")){ %>
							<div class="buy">
								삽니다
							</div>
						<% }else{ %>
							<div class="sell">
								팝니다
							</div>
						<% } %>
					</div>
					<% if(user != null){ // 로그인이 됐을 때만 도트가 보인다 %>
						<div class="productmenu"> <!-- 도트랑 ul태그 감싸주는 div -->
							<a class="menu-click"> <!-- 도트를 감싸주는 a태그 -->
								<img width="25px" height="25px" class="dot" src="https://img.icons8.com/external-jumpicon-glyph-ayub-irawan/32/000000/external-dot-basic-ui-jumpicon-glyph-jumpicon-glyph-ayub-irawan.png"/>
							</a>
							<div class="hidden"> <!-- ul태그를 감싸주는 div -->
								<% if(user != null && ub.getUsedBoardWriter().equals(String.valueOf(user.getUserNo()))){ %> <!-- 로그인이 되어있고 작성자이면-->
								<ul class="writer-menu"> <!-- 작성자가 보이는 ul 메뉴 -->
									<li class="writer-update borderline"><a onclick="warning()" href="<%= request.getContextPath() %>/updateUsedEnroll.do?bNo=<%= ub.getUsedBoardNo() %>">글 수정하기</a>
									</li>
									<li class="writer-delete borderline">
										<a href="<%= request.getContextPath() %>/deleteUsedBoard.do?bNo=<%= ub.getUsedBoardNo() %>">글 삭제하기</a>
									</li>
									<li class="writer-status borderline" onclick="clickStatus()"> <!-- onclick 했을 시 function 들어감 -->
										<a>거래상태 바꾸기</a>
										<ul class="status-change"> <!-- 거래상태바꾸리를 눌렀을 시 보여지는 소분류 메뉴 -->
											<li class="status-in" id="complete"><a>거래완료</a></li>
											<li class="status-in" id="deal"><a>거래중</a></li>
											<li class="status-in" id="sell"><a>판매중</a></li>
										</ul>
									</li>
								</ul>
								<% }else{ %> <!-- 작성자가 아닐경우나오는 메뉴 -->
									<ul class="writer-menu">
										<li class="writer-update" onclick="reportBoard();"><a>게시글 신고하기</a></li>
									</ul>
								<% } %>
							</div>
						</div>
					<% } %>
				</div>
				<div class="information">
					<div class="one">
						<div class="status">
							상품상태
							<div class="status-in">
								<%= ub.getSaleStatus() %>
							</div>
						</div>
						<div class="payment">
							결제
								<% if(ub.getPaymentOne().equals("Y") && ub.getPaymentTwo().equals("Y")){ %>
									<div class="payment-in">
										바로결제, 만나서 결제
									</div>
								<% }else if(ub.getPaymentOne().equals("Y") && ub.getPaymentTwo().equals("N")){%>
									<div class="payment-in">
										바로결제
									</div>
								<% }else if(ub.getPaymentOne().equals("N") && ub.getPaymentTwo().equals("Y")){ %>
									<div class="payment-in">
										만나서결제
									</div>
								<% } %>
						</div>
						<div class="seller">
							판매자
							<div class="sellerid">
								<%= ub.getNickName() %>
							</div>
						</div>
						<div id="transaction">
							<%= ub.getSaleStatus() %>
						</div>
					</div>
					<div class="two">
						<input class="product_price" type="hidden" value=<%=ub.getBuyer() %>>
						<%= ub.getComprice() %> 원	
					</div>
					<div class="three">
						<% if(!like.isEmpty()){ // like 수가 존재 할 때
							for(int i = 0 ; i < like.size() ; i++){ // for문 이용
								if(ub.getPaymentStatus() == "y"){%>
									<button class="likebutton" disabled>
										<img class="heart" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAABmJLR0QA/wD/AP+gvaeTAAABVklEQVRIie3Wu0oDQRiG4e83SBRBwRQiiq0XoIWHG9FGsLTyFmy0VWMniOCpFAuvQRELQWJhnVh5aKwMmNfCBGY32c1udqcQ88EWM/zMMwd2GKmffxtgCbgAasAXUAVOgbkOtfPAWaj2HFhMAxaAMtH5BrYBa347QCOitgHsAYUkcBzqpgwcJKzd7YYuxMw+a5ZdayBkb0qyxOeSLhtuI4AANUlTnuCqmc1EwXVJg57gupkVW43wVn96QtvGDsNPHuFKHHzlEQ6MHT7jkqQXSUXlm7qkaTN7bXUEVmxm75JOckYl6dhFpQ7/bHPVz5JKOaEfkmbN7K1rJbCe4421lmqawFEO6GHq/QGGgdsM6A0wlBpu4mPAQw/oIzDeE+rgk0AlJTqRCXXwEnCfAL3LvNIO+AhwGYNeA6O5og5uwBbtj4V9IHz9epnAKr8Puxqw4h3s50/mB7ZjI/jDXzG+AAAAAElFTkSuQmCC">
										<span style="margin: auto 0;">찜하기</span>
									</button>
								<%// 로그인을 했고 like i번째의 유저번호가 user 유저번호와 같으면 색있는 버튼을
								}else if(user != null && like.get(i).getUserNo() == user.getUserNo()){ %>
									<button class="likebutton colorchange">
										<img class="heart" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAABmJLR0QA/wD/AP+gvaeTAAABVklEQVRIie3Wu0oDQRiG4e83SBRBwRQiiq0XoIWHG9FGsLTyFmy0VWMniOCpFAuvQRELQWJhnVh5aKwMmNfCBGY32c1udqcQ88EWM/zMMwd2GKmffxtgCbgAasAXUAVOgbkOtfPAWaj2HFhMAxaAMtH5BrYBa347QCOitgHsAYUkcBzqpgwcJKzd7YYuxMw+a5ZdayBkb0qyxOeSLhtuI4AANUlTnuCqmc1EwXVJg57gupkVW43wVn96QtvGDsNPHuFKHHzlEQ6MHT7jkqQXSUXlm7qkaTN7bXUEVmxm75JOckYl6dhFpQ7/bHPVz5JKOaEfkmbN7K1rJbCe4421lmqawFEO6GHq/QGGgdsM6A0wlBpu4mPAQw/oIzDeE+rgk0AlJTqRCXXwEnCfAL3LvNIO+AhwGYNeA6O5og5uwBbtj4V9IHz9epnAKr8Puxqw4h3s50/mB7ZjI/jDXzG+AAAAAElFTkSuQmCC">
										<span style="margin: auto 0;">찜하기</span>
									</button>
								<% break; // 브레이크
								// 로그인이 되있으면서 작성자 번호와 로그인된 유저 번호가 같거나 로그인이 안되있으면 disabled
								}else if((user != null && ub.getUsedBoardWriter().equals(String.valueOf(user.getUserNo()))) || user == null){ %>
									<button class="likebutton" disabled>
										<img class="heart" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAABmJLR0QA/wD/AP+gvaeTAAABVklEQVRIie3Wu0oDQRiG4e83SBRBwRQiiq0XoIWHG9FGsLTyFmy0VWMniOCpFAuvQRELQWJhnVh5aKwMmNfCBGY32c1udqcQ88EWM/zMMwd2GKmffxtgCbgAasAXUAVOgbkOtfPAWaj2HFhMAxaAMtH5BrYBa347QCOitgHsAYUkcBzqpgwcJKzd7YYuxMw+a5ZdayBkb0qyxOeSLhtuI4AANUlTnuCqmc1EwXVJg57gupkVW43wVn96QtvGDsNPHuFKHHzlEQ6MHT7jkqQXSUXlm7qkaTN7bXUEVmxm75JOckYl6dhFpQ7/bHPVz5JKOaEfkmbN7K1rJbCe4421lmqawFEO6GHq/QGGgdsM6A0wlBpu4mPAQw/oIzDeE+rgk0AlJTqRCXXwEnCfAL3LvNIO+AhwGYNeA6O5og5uwBbtj4V9IHz9epnAKr8Puxqw4h3s50/mB7ZjI/jDXzG+AAAAAElFTkSuQmCC">
										<span style="margin: auto 0;">찜하기</span>
									</button>
								<% // 로그인을 했고 like i번째의 유저 번호가 user의 유저 번호가 다르면 색이없는 버튼을
								}else if(user != null && like.get(i).getUserNo() != user.getUserNo()){ %>
									<button class="likebutton">
										<img class="heart" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAABmJLR0QA/wD/AP+gvaeTAAABVklEQVRIie3Wu0oDQRiG4e83SBRBwRQiiq0XoIWHG9FGsLTyFmy0VWMniOCpFAuvQRELQWJhnVh5aKwMmNfCBGY32c1udqcQ88EWM/zMMwd2GKmffxtgCbgAasAXUAVOgbkOtfPAWaj2HFhMAxaAMtH5BrYBa347QCOitgHsAYUkcBzqpgwcJKzd7YYuxMw+a5ZdayBkb0qyxOeSLhtuI4AANUlTnuCqmc1EwXVJg57gupkVW43wVn96QtvGDsNPHuFKHHzlEQ6MHT7jkqQXSUXlm7qkaTN7bXUEVmxm75JOckYl6dhFpQ7/bHPVz5JKOaEfkmbN7K1rJbCe4421lmqawFEO6GHq/QGGgdsM6A0wlBpu4mPAQw/oIzDeE+rgk0AlJTqRCXXwEnCfAL3LvNIO+AhwGYNeA6O5og5uwBbtj4V9IHz9epnAKr8Puxqw4h3s50/mB7ZjI/jDXzG+AAAAAElFTkSuQmCC">
										<span style="margin: auto 0;">찜하기</span>
									</button>
								<% break; // 브래이크
								// 그중 아무것도 아닐 때
								}else{ %>
									<button class="likebutton" disabled>
										<img class="heart" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAABmJLR0QA/wD/AP+gvaeTAAABVklEQVRIie3Wu0oDQRiG4e83SBRBwRQiiq0XoIWHG9FGsLTyFmy0VWMniOCpFAuvQRELQWJhnVh5aKwMmNfCBGY32c1udqcQ88EWM/zMMwd2GKmffxtgCbgAasAXUAVOgbkOtfPAWaj2HFhMAxaAMtH5BrYBa347QCOitgHsAYUkcBzqpgwcJKzd7YYuxMw+a5ZdayBkb0qyxOeSLhtuI4AANUlTnuCqmc1EwXVJg57gupkVW43wVn96QtvGDsNPHuFKHHzlEQ6MHT7jkqQXSUXlm7qkaTN7bXUEVmxm75JOckYl6dhFpQ7/bHPVz5JKOaEfkmbN7K1rJbCe4421lmqawFEO6GHq/QGGgdsM6A0wlBpu4mPAQw/oIzDeE+rgk0AlJTqRCXXwEnCfAL3LvNIO+AhwGYNeA6O5og5uwBbtj4V9IHz9epnAKr8Puxqw4h3s50/mB7ZjI/jDXzG+AAAAAElFTkSuQmCC">
										<span style="margin: auto 0;">찜하기</span>
									</button>
							<% } // for문 close
							} // if문 close
						// 로그인 되있으면서 작성자 번호와 로그인된 유저 번호가 같거나 로그인이 안되있으면 disabled
						}else if((user != null && ub.getUsedBoardWriter().equals(String.valueOf(user.getUserNo()))) || user == null){ %>
							<button class="likebutton" disabled>
								<img class="heart" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAABmJLR0QA/wD/AP+gvaeTAAABVklEQVRIie3Wu0oDQRiG4e83SBRBwRQiiq0XoIWHG9FGsLTyFmy0VWMniOCpFAuvQRELQWJhnVh5aKwMmNfCBGY32c1udqcQ88EWM/zMMwd2GKmffxtgCbgAasAXUAVOgbkOtfPAWaj2HFhMAxaAMtH5BrYBa347QCOitgHsAYUkcBzqpgwcJKzd7YYuxMw+a5ZdayBkb0qyxOeSLhtuI4AANUlTnuCqmc1EwXVJg57gupkVW43wVn96QtvGDsNPHuFKHHzlEQ6MHT7jkqQXSUXlm7qkaTN7bXUEVmxm75JOckYl6dhFpQ7/bHPVz5JKOaEfkmbN7K1rJbCe4421lmqawFEO6GHq/QGGgdsM6A0wlBpu4mPAQw/oIzDeE+rgk0AlJTqRCXXwEnCfAL3LvNIO+AhwGYNeA6O5og5uwBbtj4V9IHz9epnAKr8Puxqw4h3s50/mB7ZjI/jDXzG+AAAAAElFTkSuQmCC">
								<span style="margin: auto 0;">찜하기</span>
							</button>
						<% } else if(user != null){ // 로그인 되어있으면 %>
							<button class="likebutton">
								<img class="heart" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAABmJLR0QA/wD/AP+gvaeTAAABVklEQVRIie3Wu0oDQRiG4e83SBRBwRQiiq0XoIWHG9FGsLTyFmy0VWMniOCpFAuvQRELQWJhnVh5aKwMmNfCBGY32c1udqcQ88EWM/zMMwd2GKmffxtgCbgAasAXUAVOgbkOtfPAWaj2HFhMAxaAMtH5BrYBa347QCOitgHsAYUkcBzqpgwcJKzd7YYuxMw+a5ZdayBkb0qyxOeSLhtuI4AANUlTnuCqmc1EwXVJg57gupkVW43wVn96QtvGDsNPHuFKHHzlEQ6MHT7jkqQXSUXlm7qkaTN7bXUEVmxm75JOckYl6dhFpQ7/bHPVz5JKOaEfkmbN7K1rJbCe4421lmqawFEO6GHq/QGGgdsM6A0wlBpu4mPAQw/oIzDeE+rgk0AlJTqRCXXwEnCfAL3LvNIO+AhwGYNeA6O5og5uwBbtj4V9IHz9epnAKr8Puxqw4h3s50/mB7ZjI/jDXzG+AAAAAElFTkSuQmCC">
								<span style="margin: auto 0;">찜하기</span>
							</button>
						<% }
						if(user == null || 	ub.getNickName().equals(user.getUserId())){%>
						<button class="chatting" onclick="startchat()" disabled>채팅 거래</button>
						<%} else{%>
						<button class="chatting" onclick="startchat()">채팅 거래</button>
						<%}
						if(user == null || ub.getNickName().equals(user.getUserId()) || ub.getPaymentStatus().equals("Y") || ub.getUsedBoardContent().contains("나눔") || ub.getUsedBoardTitle().contains("나눔")){ %>
						<button class="buyme colorchangego" disabled>구매하기</button>
						<% }else if(user != null && ub.getPaymentStatus().equals("N")){ %>
						<button class="buyme" onclick="requestPay()">구매하기</button>
						<% } %>
					</div>
				</div>
			</div>
		</div>
		<div class="explain">
			상품 설명
			<div class="explainIn">
				<%= ub.getUsedBoardContent() %>
			</div>
		</div>
		<% } %>
		<div class="itemLine">
			<div id="relation">
				연관상품
				<div class="relation-in">
				</div>
			</div>
			<div id="popular">
				인기상품
				<div class="popular-in">
				</div>
			</div>
		</div>
	</div>
	<div>
    	<%@ include file = "../common/footer.jsp" %>
    </div>
    <script src="../../resources/library/swiper.min.js"></script>
    <script src="https://cdn.iamport.kr/js/iamport.payment-1.1.8.js"></script>
	<script src="../../resources/js/common/payment.js"></script>
    <script>
  
    
    <%if(user != null && user.getUserNo()!=writerNo){%>
	function startchat(){//게시글 작성자가 아니고 로그인 한 사람일 때 
		var sendperson = <%=user.getUserNo()%>;//보낸 사람
		var answerperson = <%=ub.getUsedBoardWriter()%>;//받는 사람
		
		//아이작스로 보낸사람 받는사람 채팅이 있는지 체크 
		$.ajax({
			url : "chattingCheck.do", 
			data :{
				sendperson:sendperson, 
				answerperson:answerperson
			}, 
			type : "get", 
			success: function(status){
				if(status == "already"){//이미 채팅창이 있는 것
					alert("기존에 있는 채팅창으로 넘어가겠습니다.")
					window.name = "datachatt";
					window.open("<%=request.getContextPath()%>/alreaychat.do?nick=<%=ub.getNickName()%>&nick2=<%=user.getUserId()%>&sendp=<%=user.getUserNo()%>&ansp=<%=ub.getUsedBoardWriter()%>","_blank","width=500, height=500, top=200, left=600");
				}else if(status == "new"){//새로운 사람
					var alertt = confirm("새로운 채팅을 시작하시겠습니까?");
					if(alertt){

						//페이지에 들어가는거
						window.name = "datachatt";
						window.open("<%=request.getContextPath()%>/chattingPro.do?nick=<%=ub.getNickName()%>&nick2=<%=user.getUserId()%>&sendp=<%=user.getUserNo()%>&ansp=<%=ub.getUsedBoardWriter()%>","_blank","width=500, height=500, top=200, left=600");
						
					}else{
						alert("취소되었습니다.")
						return;
					}
				}
			}, 
			error:function(e){
				console.log("fail chatting")
			}
		})
		

	}
	<%}else if(user == null){%>
		$(".chatting").attr("disabled",true);
	<%}else{%>
		$(".chatting").attr("disabled",true);
	<%}%>
	
    function reportBoard(){// 게시글 신고하기
    	
		window.name = "reportBoardsub";
		 window.open("<%= request.getContextPath()%>/reportUsedBoard.do?bNo=<%=ub.getUsedBoardNo()%>&bTitle=<%=ub.getUsedBoardTitle()%>","_blank", "width=500, height=330, top=350, left=600");
	}

    	function warning(){
			let result =  confirm("수정하기 시 사진이 초기화되며, 카테고리를 선택할 수 없습니다. 진행하겠습니까?")
			
			if(result == true){
				return true;
			}
			return false;
    	}
    
		// 도트를 클릿 했을 때
		$(".menu-click").click(function showdiv() {
			event.stopPropagation();
			//	ul을 감싸주는 div가 보여졌다 말았다 한다
			$(".hidden").toggle();
		})

		// 거래상태 바꾸기를 클릭시 사용되는 function
    	function clickStatus() {
			event.stopPropagation();
			// 거래중,판매중,거래완료가 들어간 ul태그를 가렸다가 보여준다
			$(".status-change").toggle();
		}
		
		  $(document).click(function(){
		    	$(".hidden").hide();
		    	$(".status-change").hide();
		    })
    	
		// 찜하기 했을때 찜카운트 증가 또는 감소
		$(".likebutton").on('click', function() {
			
			let like = $('.likebutton')
			let bNo = $(".bNo").text();
			let uNo = $(".uNo").text();
			
			// colorchange 클래스가 존재하면(찜하지 않았을 시)
			if(like.hasClass('colorchange')){
				
				// sql 문에 찜을 -1
				$.ajax({
					url:'notLike.do',	
					data:{
						bNo:bNo,
						uNo:uNo
					},
					type:'get',
					success:function(){
						
						// 클래스를 지운다
						like.removeClass('colorchange')
					}
				})
			}else{ // 존재하지 않으면
				
				// sql문에 찜을 +1
				$.ajax({
					url:'like.do',
					data:{
						bNo:bNo,
						uNo:uNo
					},
					type:'get',
					success:function(){
						
						// 클래스를 추가한다
						like.addClass('colorchange')
					}
				})
			}
		})
    
    	$(function() {
    		$(".hidden").hide();
        	$(".status-change").hide();
			// 연관 상품과 인기 상품 도출하기
			// 인기상품			
			$.ajax({
				url:"popular.do",
				type:"get",
				success:function(list){
					console.log(list)

					let popular = $(".popular-in")
					popular.empty();
					if(list.length != 0){
						$.each(list, function(index, obj){
							
							let listprice = obj.comprice;
							let listcondition = obj.saleStatus;
							let listtitle = obj.usedBoardTitle;
							let listlike = obj.likeCount;
							let titleimage = obj.titleImg;
							let bNo = obj.usedBoardNo;
							
							let price = $("<span>").addClass("relation-price").html(listprice+"원")
							let status = $("<span>").addClass("relation-status").append(listcondition)
							let secondLine = $("<div>").addClass("secondLine").append(price).append(status);
							
							let title = $("<span>").addClass("relation-title").html(listtitle)
							let heart = $("<img>").attr('src', "data:image/png;base64,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").addClass("relation-heart");
							let count = $("<span>").html(listlike)
							let span = $("<span>").html(heart)
							let like = $("<span>").addClass("relation-like").html(span).append(count);
							let firstLine = $("<div>").append(title).append(like).addClass("firstLine");
							
							let bottom = $("<div>").addClass("relation-bottom").append(firstLine).append(secondLine);
							let image = $("<img>").attr('src', "<%=request.getContextPath() %>/resources/usedboard_upfiles/"+titleimage).addClass("relation-image");
							let aTag = $("<a>").attr('href', '<%=request.getContextPath()%>/detailview.do?bNo='+bNo).append(image, '<br>', bottom);
							let thumnail = $("<div>").addClass("thumbnail").html(aTag);
							
							popular.append(thumnail);
							
						})
					}
				}
			})
			
			//연관 상품
			let category = $(".categorycode").text()
			let title = $("#title").text()
			console.log(category)
			console.log(title)
			
			$.ajax({
				url:"relationItem.do",
				data:{
					category:category,
					title:title
				},
				type:'get',
				success:function(list){
					console.log(list)

					let relation = $(".relation-in")
					relation.empty();
					if(list.length != 0){
						$.each(list, function(index, obj){
							
							let listprice = obj.comprice;
							let listcondition = obj.saleStatus;
							let listtitle = obj.usedBoardTitle;
							let listlike = obj.likeCount;
							let titleimage = obj.titleImg;
							let bNo = obj.usedBoardNo;
							
							let price = $("<span>").addClass("relation-price").html(listprice+"원")
							let status = $("<span>").addClass("relation-status").append(listcondition)
							let secondLine = $("<div>").addClass("secondLine").append(price).append(status);
							
							let title = $("<span>").addClass("relation-title").html(listtitle)
							let heart = $("<img>").attr('src', "data:image/png;base64,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").addClass("relation-heart");
							let count = $("<span>").html(listlike)
							let span = $("<span>").html(heart)
							let like = $("<span>").addClass("relation-like").html(span).append(count);
							let firstLine = $("<div>").append(title).append(like).addClass("firstLine");
							
							let bottom = $("<div>").addClass("relation-bottom").append(firstLine).append(secondLine);
							let image = $("<img>").attr('src', "<%=request.getContextPath() %>/resources/usedboard_upfiles/"+titleimage).addClass("relation-image");
							let aTag = $("<a>").attr('href', '<%=request.getContextPath()%>/detailview.do?bNo='+bNo).append(image, '<br>', bottom);
							let thumnail = $("<div>").addClass("thumbnail").html(aTag);
							
							relation.append(thumnail);
							
						})
					}
				}
			})
			
		})
		
		$(".status-in").on('click', function() {
			let status = $(this).text();
			let bNo = $(".bNo").text();
			console.log("status : "+status)
			console.log("bNo : "+bNo)
			
			$.ajax({
				url:"updateStatus.do",
				data:{
					status:status,
					bNo:bNo
				},
				type:"get",
				success:function(change){
					console.log(change)
					
					$(".hidden").toggle();
					
					let changestatus = $("#transaction")
					changestatus.empty();
					changestatus.append(change)
				}
			})
    	})
    	
		/*$(document).click(function(){ 
			$('.writer-menu').hide();
			$('.status-change').hide();
		});*/
		
		
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