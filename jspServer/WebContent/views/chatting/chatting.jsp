<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.ArrayList, com.uni.chatting.model.vo.*"%>
<%
	Chatting ct = (Chatting)request.getAttribute("ct");//채팅 새로
	Chatting act = (Chatting)request.getAttribute("act");//채팅 기존
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
	input[type = "file"]{
		width: 100%;
   		transform: translateX(-95px);
   		height: 50px;
    	border: 1px solid;
   		position: absolute;
	}
	.timeout{
		text-align: right;
    	padding-right: 5px;	
   		transform: translateY(7px);
	}
	.charContent{
		height: 30px;
	    border: 1px solid;
	    border-radius: 5px;
	    width: 50%;
	}
	#chatList{
		width:100%;
		border-spacing: 10px;
	}	
	.timein{
		text-align: left;
    	padding-left: 5px;	
   		transform: translateY(7px);
	}


</style>
</head>
<body>
	<div class= "whle"><!-- 전체 div -->
		<div class= "topdiv"><!-- top div -->
		 	<a class="firstI" ><img src = "../../resources/images/icons8-back-67 (1).png" > </a>
		 		<span class="sellerName"> 거래 채팅방 </span>
		 	<ul class= "main1">
		 		<li><a class= "secondI"><img src = "../../resources/images/icons8-ellipsis-30.png"></a>
		 			<ul class="main2">
		 				<li class="goOut">대화 삭제하기</li> 
		 				<!-- <li  class="deleteCon" onclick="deletechat()"> 대화내용 삭제 </li> -->
		 			</ul>
		 		</li>
		 	</ul>
		</div>
		<div class= "middleDiv"><!-- 채팅 부분 -->
			<div class="todayT"></div>
			<div class="contentP">
				<table id = "chatList">
				
				</table>
			</div>
		</div>
		
		<div class="underDiv"><!-- 전송 부분 -->
			<ul class= "part1">
				<li><a class="plusI"><img src= "../../resources/images/icons8-plus-48.png"></a>
					<ul class= "part2">
						<li class="fileSend"><input type="file" name="file1" id="file1" />첨부파일</li>
						<li class= "makeAppo">약속</li>
					</ul>
				</li>
			</ul>
			<input type="text" placeholder="메시지를 입력하세요" name="text" id = "report" class="reportTitle"  >
			<a class="sendI"><img src="../../resources/images/icons8-send-32.png"></a>
		</div>
	</div>
	<script>
		
		/*window.opener.location.reload();
		window.close();*/
	
		//내용 삭제
		$(".goOut").click(function(){
			var aler = confirm("다른 사용자에게도 대화 내용이 삭제됩니다. 삭제하시겠습니까?");
			if(aler){
				$(".contentP").empty();		
			}else{
				return;
			}
		})
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
		$(function(){
		
			chatList();
			$(".sendI").click(function(){
				var content = null;
				var chatNo = null;
				<%if(ct!= null){%>
				content = $("#report").val();
				console.log(content)
				chatNo = <%=ct.getChatNo()%>;
				console.log(chatNo)
				<%}else{%>
				content = $("#report").val();
				console.log(content)
				chatNo = <%=act.getChatNo()%>;
				console.log(chatNo)
				<%}%>

				$.ajax({
					url:"sendChatting.do", 
					type:"post",
					data:{
						content:content, 
						chatNo:chatNo
					}, 
					success:function(status){
						if(status=="success"){
								chatList();
							$(".reportTitle").val("")
						}
					}, 
					error:function(){
						console.log("댓글 실패")
					}
				})
			})	
		})
		
		function chatList(){
			 scroll();
			$("#chatList").empty();
			var chatNo = null;
			<%if(ct!= null){%>
			chatNo = <%=ct.getChatNo()%>;
			console.log(chatNo)
			<%}else{%>
			chatNo = <%=act.getChatNo()%>;
			console.log(chatNo)
			<%}%>
			
			$.ajax({
				url:"chattingList.do", 
				data:{chatNo:chatNo}, 
				type:"get", 
				success: function(list){
					console.log(list)
					
				$.each(list, function(index,obj){
					<%if(ct!=null){%>
						if(obj.userNo == <%=ct.getSendP()%>){
							var time = $("<td>").text(obj.timelist).addClass("timeout");
							var content = $("<td>").text(obj.chatCont).addClass("charContent");
							var tr = $("<tr>").append(time, content);
			
							$("#chatList").append(tr);
							
						}else{
							var time = $("<td>").text(obj.timelist).addClass("charContent");
							var content = $("<td>").text(obj.chatCont).addClass("timeout");
							var tr = $("<tr>").append( content,time);
			
							$("#chatList").append(tr);
							
						}
					<%	}else{%>
						if(obj.userNo == <%=act.getSendP()%>){
							var time = $("<td>").text(obj.timelist).addClass("timeout");
							var content = $("<td>").text(obj.chatCont).addClass("charContent");
							var tr = $("<tr>").append(time, content);
			
							$("#chatList").append(tr);
							
						}else if(obj.userNo ==<%=act.getAnswP()%>){
							var time = $("<td>").text(obj.timelist).addClass("timein");
							var content = $("<td>").text(obj.chatCont).addClass("charContent");
							var tr = $("<tr>").append( content,time);
			
							$("#chatList").append(tr);
							
						}
					<%	}%>
					
				})
	
				},error:function(){
					console.log("실패이다")
				}
			})
			
		}

		$(".makeAppo").click(function(){
			var aler = confirm("약속을 잡으시겠습니까?")
			if(aler){
				<%if (ct!= null){%>
				var cNo = <%=ct.getChatNo()%>;
				var sendP = <%=ct.getSendP()%>;
				var ansP = <%=ct.getAnswP()%>;
				console.log(cNo)
				console.log(sendP)
				console.log(ansP)
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
				<%}else{%>
				var cNo = <%=act.getChatNo()%>
				var sendP = <%=act.getSendP()%>;
				var ansP = <%=act.getAnswP()%>;
				console.log(cNo)
				console.log(sendP)
				console.log(ansP)
				<%}%>
				
			}else{
				return;
			}
		})
	</script>
</body>
</html>