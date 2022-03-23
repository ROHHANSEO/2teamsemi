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
       	height:700px;
		border:1px solid black;
      }
      /*제목 div*/
     .firstDiv{
     	height:40px;
     	line-height:40px;
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
     	height:50%;
     	width:100%;
     	margin-top:10px;
     }
     .innerConent{
     	padding-left:10px;
     }
     
     /*댓글 부분 div*/
     .answerDiv{
     	border:1px solid black;
     	height:40%;
     }
     /*등록하기 버튼*/
     .gotoSubmitbutton{
	    
     	margin-top:15px;
     }
    
  	/*카테고리 select 부분*/
     .categoryList{
     	width: 200px; /* 가로 사이즈 */
	    padding: 8px; /* 내부여백 */
	    padding-left: 12px;
	    border: 1px solid #ddd;
	    background: no-repeat right 50%; /* 화살표 위치 */
	    background-size: 30px; /* 화살표 크기 */
	    border-radius: 4px;
	    box-sizing: border-box;
	    font-size: 12px;
	    color: #000;
	    outline:none;
	    height:35px;
     }
     .showReply, .replybutton{
     	height:100%;
     }
     .replybutton{
     	border:1px solid black;
     	height:20%;
     	margin:0 10px 5px 10px;
     }
     .showReply{
     	height:80%;
     	border:1px solid black;
     }
     
</style>
</head>
<body>
	<%@include file="../common/header.jsp" %>
	<div class = "enrollForm1">
	<div class="headerenrollForm"><!-- 고객센터 글 작성 위에 부분 -->
		<p> 1:1문의 답변 </p>
	</div>
	
	<div class="underheaderenrollF"></div><!-- 라인 부분 -->
	
	<div class="firstDiv"><!-- 번호, 문의사항 div -->
		<div class="qaNo"> <p>번호 <%= qa.getQuestionNo()%></p></div>
		<div class="qaname"> <p>문의사항 <p></div>
	</div>
	<div class="firstDiv"><!-- 제목, 작성자, 작성일 선택 div -->
		<div class="qaTitle"> <p>제목 - <%=qa.getQuestionTitle() %></p></div>
		<div class="qaWriter"><p> 작성자: <%=qa.getUserNo() %> </p></div>
		<div class="qaDate"> <p>작성일: <%=qa.getCreateDate() %></p> </div>
	</div>
	<div class="contentDiv"><div class="innerConent"><%=qa.getQuestionContent() %></div></div><!-- 내용 작성 div -->
	<div class="answerDiv"> 
		<div class="showReply"> 댓글 보이는 부분</div>
		<div class="replybutton"> 댓글 다는 부분 <button type="button" class="commonsubmit gotoSubmitbutton"> 등록 </button></div>
	 </div>
	<div class="deleteorsubmit" align="right"><!-- 삭제, 등록버튼 -->
		<button type="submit" class="commonsubmit gotoSubmitbutton" > 등록하기 </button>
		<button type="button" class="commonsubmit gobackServiceCenterMain" onclick="askcancel();"> 취소하기 </button>
	</div>
	
	</div>
	
</body>
</html>