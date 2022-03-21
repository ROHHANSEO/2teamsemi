<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../../resources/css/common/common.css">
<style>
	/*전체 페이지 div*/
	.enrollForm1{
       	width:70%;
       	margin:0 auto;
       	height: 550px;

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
     /*제목 div*/
     .enrollTitlesection{
     	height:50px;
     	line-height:50px;
		width:100%;
		display:flex;
		align-items: center;
		border-bottom:1px solid black;
     }
     .titleNamepart, .counttitlename, .counttitlelength{
     	width:100%;
     }
     /*counttitlename :제목 입력부분 */
     .counttitlename{
     	width : 40%;
     	padding-left: 12px;
		height:60%;
     }
     /*titleNamepart : 제목 글씨부분*/
     .titleNamepart{
     	font-size:19px;
     	width:18%;
     	height:50px;
     	margin:0 0 0 15px;
     	line-height:37px;
     	
     }
     /*dotpoint:*이부분 */
     .dotpoint{
     	color: red;
     	font-weight: 800;
     	font-size:20px;
     	vertical-align: 5px;
     }
     /*counttitlelength : count하는 부분*/
     .counttitlelength, .countContentlength{
     	margin-left:15px;
     	height:70%;
     	width:30%;
     }
  	
     /*enrollcontentsection:내용 div 부분*/
     .enrollcontentsection{
     	display:flex;
     	margin-top:15px;
     }
   
     /*등록하기 버튼*/
     .gotoSubmitbutton, .gobackServiceCenterMain{
	    position: relative;
	    display: inline-block;
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
     
</style>
</head>
<body>
	<%@include file="../common/header.jsp" %>
	<div class = "enrollForm1">
	<div class="headerenrollForm"><!-- 고객센터 글 작성 위에 부분 -->
		<p> 고객센터 글 작성</p>
	</div>
	
	<div class="underheaderenrollF"></div><!-- 라인 부분 -->
	
	<form name="serviceCenterForm" class="enrollServiceCenterForm" action="<%=request.getContextPath()%>/insertServiceCenter.do" method="post">
	<div class="enrollTitlesection"><!-- 제목 div -->
		<span class="titleNamepart"> 제목<span class="dotpoint">*</span></span>
		<input type="text" name="title" class="counttitlename" placeholder="제목을 입력해주세요" maxlength="40" required >
		<span class="counttitlelength">0 / 40</span>
	</div>
	<div class="enrollTitlesection"><!-- 카테고리 선택 div -->
		<span class="titleNamepart"> 카테고리<span class="dotpoint">*</span></span>
		<div class="categorytogglepart">
			<select class="categoryList" name="category" required>
				<option class="optionlist" value=1 selected>운영 정책</option>
				<option class="optionlist" value=2>계정/인증</option>
				<option class="optionlist" value=3>구매/판매</option>
				<option class="optionlist" value=4>거래 품목</option>
				<option class="optionlist" value=5>거래 매너</option>
				<option class="optionlist" value=6>이벤트</option>
				<option class="optionlist" value=7>이용 제재</option>
				<option class="optionlist" value=8>커뮤니티</option>
				<option class="optionlist" value=9>경매</option>
				<option class="optionlist" value=10>채팅</option>
				<option class="optionlist" value=11>기타</option>
			</select>
		</div>
	</div>
	<div class="enrollcontentsection"><!-- 내용 작성 div -->
		<span class="titleNamepart"> 글 내용<span class="dotpoint">*</span></span>
		<div class="contentpart">
			<textarea name ="content" class="contentWritePart" cols="70" rows="15" style="resize:none;" required></textarea>
		</div>
		<span class="countContentlength">0 / 2000</span>
	</div>
	
	<div class="deleteorsubmit" align="right"><!-- 삭제, 등록버튼 -->
		<button type="submit" class="commonsubmit gotoSubmitbutton" > 등록하기 </button>
		<button type="button" class="commonsubmit gobackServiceCenterMain" onclick="askcancel();"> 취소하기 </button>
	</div>
	</form>
	
	</div>
	<script type ="text/javascript">
		//게시글 체크 
		$(".gotoSubmitbutton").click(function(){
			console.log($("#title").text())
			console.log($("#content").text())
			theForm = document.serviceCenterForm;
			if(confirm("글을 등록하시겠습니까?")== true){
				if(theForm.title.value == "" && theForm.content.value == ""){
					
					alert("제목과 내용을 입력해주셔야 등록이 됩니다.")
					
				}else if(theForm.title.value == ""){
					alert("제목을 입력해주셔야 합니다.")
				}else if(theForm.content.value == ""){
					alert("내용을 입력해주셔야 합니다.")
				}
				if(theForm.title.value != "" && theForm.content.value != ""){
					
					alert("등록이 완료 되었습니다.")
					
				}
				
				//https://runtoyourdream.tistory.com/203
				
			}else{
				return;
			}
		})
		

		function askcancel(){
			var cancel = confirm("글 작성을 취소하시겠습니까?");
			if(cancel){
				 location.href="<%=request.getContextPath()%>/serviceCenter.do";
			}else{
				return;
			}
		}
		//제목 40자 미만으로 설정
		$(document).ready(function(){
			$(".counttitlename").on('keyup',function(){
				$(".counttitlelength").html(($(this).val().length+" / 40"));
				
				if($(this).val().length>40){
					alert("제목은 40자 이하로 적어주세요")
					$(this).val($(this).val().substring(0, 40));
		            $('.counttitlelength').html("(40 / 40)");
				}
			})
		})
		//내용 2000자 미만으로 설정
		$(document).ready(function(){
			$(".contentWritePart").on('keyup',function(){
				$(".countContentlength").html(($(this).val().length+" / 2000"));
				
				if($(this).val().length>2000){
					alert("내용은 2000자 이하로 적어주세요")
					$(this).val($(this).val().substring(0, 2000));
		            $('.countContentlength').html("(2000 / 2000)");
				}
			})
		})
	
	</script>
	<%@ include file = "../common/footer.jsp" %>
	<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
</body>
</html>