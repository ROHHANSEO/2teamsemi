<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import=" com.uni.serviceCenter.model.vo.*"%>
<%
	QtoA qa = (QtoA)request.getAttribute("qa");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 1:1문의사항 상세 페이지 </title>
<link rel="stylesheet" href="../../resources/css/common/common.css">
<style>
	/*전체 페이지 div*/
	.enrollForm1{
       	width:70%;
       	margin:0 auto;
       	height:750px;
      }
      /*제목 div*/
     .firstDiv{
     	height:40px;
		width:100%;
		display:flex;
		align-items: center;
		border-bottom:1px solid black;
     }
      /*번호 div*/
      .qaNo{
      	width:15%;
      	padding-left:10px;
      }
      /* 번호 옆 문의사항 div*/
      .qaname{
      	width:85%;
      	padding-right:15%;
      	text-align:center;
      }
     /*header 글작성 글*/
     .headerenrollForm{
     	margin-top:2%;
     	font-size:30px;
     }
     /*글 작성 아래 라인*/
     .underheaderenrollF{
     	margin-top: 10px;
     	border-top: 2px solid black;
     }
     /*제목, 작성자, 작성일 통 div*/
     .qaTitle, .qaWriter, .qaDate{
     	width:100%;
     }
     .qaTitle{
     	width:70%;
     	padding-left:10px;
     }
     .qaWriter{
     	width:15%;
     }
     .qaDate{
     	width:15%;
     }
     /*contentDiv : 내용 부분 */
     .contentDiv{
     	height:35%;
     	width:100%;
     	margin-top:10px;
     	overflow: auto;
   		overflow-wrap: anywhere;
     }
     .innerConent{
     	padding-left:10px;
     }
     
     /*댓글 부분 div*/
     .answerDiv{
     	height:35%;
     }
    
     .showReply, .replybutton{
     	height:100%;
     	
     }
     .replybutton{
     	height:23%;
     	background-color: lightgray;
     	transform:translateY(-10%);
     	display:flex;
     }
     .showReply{
     	height:80%;
     }
     .deleteorsubmit{
     	padding:auto;
     	margin-bottom:15px;
     	transform: translateY(10px);
     }
     .gotoSubmitbutton2{

     	height:38px;
     	width:70px;
		margin-top: 10px;
    	margin-left: 5px;
     	
     }
     .writecomment{
     	border:1.5px solid black;
     	height: 45px;
    	width: 91%;
    	transform: translateY(12%);
		margin-left:15px;
     }
     .gotoprevbutton{
     	text-align:left;
     	left: 15px;
    	transform: translateX(15px);
     }
     .gotonextbutton{
     	float:right;
     	right: 15px;
    	transform: translateX(-15px);
     }
   	.showReply {
	    height: 80%;
	    background-color: lightgray;
	    overflow-Y: scroll;
	}
	.showReply tr{
		height: 30px;
		
	}
	.writerpart td{
		font-size: large;
    	padding-left: 10px;
    	font-weight: bold;
		
	}
	.contenttot td{
		padding-left: 20px;
		word-break: break-all;
		padding-right:20px;
	}
	.gotoback{
		float:right;
		transform: translateY(-90%);
	}
</style>
</head>
<body>
	<%@include file="../common/header.jsp" %>
	<div class = "enrollForm1">
	<div class="headerenrollForm"><!-- 고객센터 글 작성 위에 부분 -->
		<p> 1:1문의 답변 </p>
	</div>
	<a href="<%=request.getContextPath()%>/QtoAListPage" class="gotoback commonsubmit"> 목록으로 </a>
	
	<div class="underheaderenrollF"></div><!-- 라인 부분 -->
	
	<div class="firstDiv"><!-- 번호, 문의사항 div -->
		<div class="qaNo"> <p>번호 <%= qa.getQuestionNo()%></p></div>
		<div class="qaname"> <p>문의사항 <p></div>
	</div>
	<div class="firstDiv"><!-- 제목, 작성자, 작성일 선택 div -->
		<div class="qaTitle"> <p>제목 - <%=qa.getQuestionTitle() %></p></div>
		<div class="qaWriter"><p> 작성자: <%=qa.getUserId() %> </p></div>
		<div class="qaDate"> <p>작성일: <%=qa.getCreateDate() %></p> </div>
	</div>
	<div class="contentDiv"><div class="innerConent"><%=qa.getQuestionContent() %></div></div><!-- 내용 작성 div -->
	<div class="answerDiv"> 
		<div class="showReply"> 
			<table id="replyList" >
			
			</table>
		</div>
		<div class="replybutton"> 
			<input type="text" name ="title" id="replyContent" class="writecomment" maxlength=400>
			<button type="button" class="commonsubmit gotoSubmitbutton2"> 등록 </button>
		</div>
		</div>
	<div class="deleteorsubmit" ><!-- 삭제, 등록버튼 -->
		<button type="button" class="commonsubmit gotoprevbutton" onclick="prevButton()"> < Prev </button>
		<button type="button" class="commonsubmit gotonextbutton" onclick="nextButton()"> next > </button>
	</div>
	</div>
	<script>
		//이전 게시물 버튼
		console.log(<%=qa.getQuestionNo()%>)//게시물 번호
		function prevButton(){
			var scno = "<%=qa.getPrevNo()%>";
			if(scno != 0){
			location.href="<%=request.getContextPath()%>/detailQtoA.do?scno="+scno;
			console.log(prevB)
			}else{
				alert("이전 문의사항이 없습니다")
				return;
			}
		}
		//이후 게시물 버튼
		function nextButton(){
			
			var scno = "<%=qa.getNextNo()%>";
			console.log("nextButton")
			if(scno != 0){
			location.href="<%=request.getContextPath()%>/detailQtoA.do?scno="+scno;
			console.log(nextB)
			}else{
				alert("다음 문의사항이 없습니다")
				return;
			}
		}
		$(function(){
			replyList();
			$(".gotoSubmitbutton2").click(function(){
				var content = $("#replyContent").val();
				var scno = <%=qa.getQuestionNo()%>;
				
				$.ajax({
					url:"replyInsert.do",
					type:"post", 
					data:{
						content:content, 
						scno:scno
					}, 
					success:function(status){
						console.log(status)
						if(status=="success"){
							replyList();
							$("#replyContent").val("");
						}
					}, 
					error:function(){
						console.log("댓글 달기 실패")
					}
				})
			})
		})
		
		function replyList(){
			$("#replyList").empty();
			var scno = "<%=qa.getQuestionNo()%>";
			console.log("replyList function안에 "+ scno)
			$.ajax({
				url:"replyList.do", 
				data:{scno:scno}, 
				type:"get", 
				success:function(list){
					console.log(list)//답글 리스트들을 받아와야 한다
					$.each(list, function(index,obj){

						var contentTd = $("<td>").text( obj.replyContent).addClass("replycontentpart");
						var writerTd = $("<td>").text(obj.replyWriter);
						
						
						var tr = $("<tr>").append(writerTd).addClass("writerpart");
						var tr2 = $("<tr>").append(contentTd).addClass("contenttot");
						
						$("#replyList").append(tr, tr2);
					})
				}, 
				error:function(){
					console.log("댓글 리스트 불러오기 실패")
				}
			})
		}
		
	</script>
</body>

</html>