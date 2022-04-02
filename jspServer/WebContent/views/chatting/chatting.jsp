<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.ArrayList, com.uni.chatting.model.vo.*"%>
<%
	Chatting ctt = (Chatting)request.getAttribute("ctt");//채팅 새로
	//Chatting act = (Chatting)request.getAttribute("act");//채팅 기존
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>chatting</title>
<link rel="stylesheet" href="../../resources/css/common/common.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
	.whle{
		width:100%;
		height:100%;
	}
	.topdiv{
		background-color: #993333;
		display:flex;
		justify-content: space-between;
		align-items: center;
		align-content: center;
		flex-wrap: wrap;
	}
	.sellerName{
		color:white;
	}
	.topdiv img{
		width:30px;
		height:30px;
	}
	.firstI{
		margin-top:2px;
		margin-left:5px;
		
	}
	.secondI{
		margin-right:5px;
	}
	.middleDiv{
		height:83vh;
		overflow-y: scroll;
	}
	.underDiv{
		background-color:lightgray;
		height:52.04px;
		display:flex;
		align-items: center;
		align-content: center;
		justify-content: space-between;
	}
	.reportTitle{
		height: 35px;
		width: 400px;
	}
	.sendI{
		margin-left:10px;
		margin-right:10px;
	}
	.firstI:hover, .secondI:hover, .plusI:hover, .sendI:hover{
		cursor: pointer;
	}

	.main2{
		transform: translate(-338px, 152px);
    	position: absolute;
	}
	.part2 {
	    position: absolute;
	    transform: translate(115px, -326px);
	}
	.part2>li{
		border: 1px solid black;
	    background-color: lightgray;
	    text-align: center;
	    width: 250px;
	    height: 50px;
	    line-height: 50px;
	}
	.part2>li:hover:enabled{
		background-color:#993333;
		cursor: pointer;
	}
	.main2>li{
		border: 1px solid black;
	    background-color: lightgray;
	    text-align: center;
	    width: 250px;
	    height: 50px;
	    line-height: 50px;
	}
	.main2>li:hover:enabled{
		background-color:#993333;
		cursor: pointer;
	}
	.todayT{
		text-align: center;
		font-size: 18px;
	}
	.deleteCon:hover, .goOut:hover, .fileSend:hover, .makeAppo:hover{
		background-color:#993333;
		cursor: pointer;
		color:white;
	}
</style>
</head>
<body>
	<div class= "whle"><!-- 전체 div -->
		<div class= "topdiv"><!-- top div -->
		 	<a class="firstI" ><img src = "../../resources/images/icons8-back-67 (1).png" > </a>
		 	<span class="sellerName"> <%=ctt.getAnswNick() %>(판매자)</span>
		 	<ul class= "main1">
		 		<li><a class= "secondI"><img src = "../../resources/images/icons8-ellipsis-30.png"></a>
		 			<ul class="main2">
		 				<li class="goOut">대화방 나가기</li>
		 				<li  class="deleteCon" onclick="deletechat()"> 대화내용 삭제 </li>
		 			</ul>
		 		</li>
		 	</ul>
		</div>
		<div class= "middleDiv"><!-- 채팅 부분 -->
			<div class="todayT"></div>
			<div class="contentP"></div>
		
		</div>
		<div class="underDiv"><!-- 전송 부분 -->
			<ul class= "part1">
				<li><a class="plusI"><img src= "../../resources/images/icons8-plus-48.png"></a>
					<ul class= "part2">
						<li class="fileSend">첨부파일</li>
						<li class= "makeAppo">약속</li>
					</ul>
				</li>
			</ul>
			<input type="text" placeholder="메시지를 입력하세요" name="text" class="reportTitle"  maxlength="40">
			<a class="sendI"><img src="../../resources/images/icons8-send-32.png"></a>
		</div>
	</div>
	<script>
		//내용 삭제
		function deletechat(){
			var aler = confirm("대화 내용을 삭제하시겠습니까?");
			if(aler){
				$(".contentP").empty();		
			}else{
				return;
			}
		}
		
		/*세개 점 버튼 눌르기 전에 ui에 안보이게 하기 */
		$(function(){
			$(".main2").hide();
			$(".part2").toggle();
			//오늘 날짜
			var today = new Date();
			//년월일
			var year = today.getFullYear();
			var month = ('0' + (today.getMonth() + 1)).slice(-2);
			var day = ('0' + today.getDate()).slice(-2);
			$(".todayT").text(year+'/'+month+'/'+day);
		})
		/*다른 영역 눌렀을때 창 안보이게*/
		$(document).click(function(){ 
			$('.main2').hide();
			$(".part2").hide();
		});

		/*이전 버튼은 리스트들 보이는 페이지*/
		$(".firstI").click(function(){
			alert("hahaahah")
		})
		/*설정 삼점버튼 */
		$(".secondI").click(function(){
			event.stopPropagation();
			$(".main2").toggle();
		})
		/*추가 버튼*/
		$(".plusI").click(function(){
			event.stopPropagation();
			$(".part2").toggle();
		})
		$(".sendI").click(function(){
			alert ("전송 버튼")
		})
		<%--$(".makeAppo").click(function(){
			var aler = confirm("약속을 잡으시겠습니까?")
			if(aler){
				var cNo = <%=ctt.getChatNo()%>;
				var sendP = <%=ctt.getSendP()%>;
				var ansP = <%=ctt.getAnswP()%>;
				
				$.ajax({
					url:"appointment.do", 
					data:{
						cNo:cNo, 
						sendP:sendP, 
						ansP:ansP
					}, 
					type:"get", 
					success:function(val){
						alert("약속 잡힘")
					}, 
					error:function(e){
						alert("약속 실패")
					}
				})
				
			}else{
				return;
			}
		})--%>
	</script>
</body>
</html>