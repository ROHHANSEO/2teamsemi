<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int bNo = (int)request.getAttribute("bNo");
	String bTitle = String.valueOf(request.getAttribute("bTitle"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<style>
	html{
		background-color:#EFEFEF;
	}
	.reportdiv{
		margin-top:14px;
		width:95vw;
		height:93vh;
	}

	.reportIndiv{
		height:10%;
	}
	
	.reportcategorydiv{
		height:10%;
		margin-top:8px;
	}
	.reportContentdiv{
		height:40%;
		margin-top:8px;
	}
	.reportTitle{
		height:30px;
		width:80%;
		line-height:30px;
		position: relative;
    	left:50%;
		transform:translateX(-50%);
		font-family: sans-serif;
		border: 1.2px solid black;
		border-radius: 5px;
	}
	.reportcategory{
		height:33px;
		width:81.5% ;
		line-height:30px;
		position: relative;
    	left:50%;
		transform:translateX(-50%);
		font-family: sans-serif;
		border: 1.2px solid black;
		border-radius: 5px;
	}
	.reportContent{
		height:150px;
		width:80%;
		position: relative;
    	left:50%;
		transform:translateX(-50%);
		font-family: sans-serif;
		border: 1.2px solid black;
		border-radius: 5px;
	}
	/*버튼 구역 나누기*/
	.reportButtonArea{
		height:10%;
		margin-top:8px;
	}
	.reportToButton1, .reportToButton2{
		height:30px;
		width:40%;
		position: relative;
		left:9.5%;
		font-family: sans-serif;
		transition: 0.25s;
		border: 1.5px solid #993333;
		box-shadow: 0 10px 35px rgba(0, 0, 0, 0.2);
		font-weight: 600;
		
	}
	.reportToButton1:hover, .reportToButton2:hover{
		background-color: #993333;
    	color: white;
    	cursor:pointer;
	}
</style>
<body>
	<div class= "reportdiv"><!-- 게시글 신고하기 전체 div -->
		<form name="reportform" id="reportform"   method="post">
		<input type="hidden" name="bNo" value="<%=bNo%>">
		<input type="hidden" name="bTitle" value="<%=bTitle%>">
		<div class="reportIndiv"><!-- 게시글 신고하기 제목 div -->
			<input type="text" name="title" class="reportTitle" placeholder="문의 제목을 입력해주세요" maxlength="40" required>
		</div>
		<div class="reportcategorydiv"><!-- 게시글 신고하기 카테고리 div -->
			<select class="reportcategory" name="category" required>
				<option class="optionlist" value=1 selected>불법</option>
				<option class="optionlist" value=2>다른카테고리</option>
				<option class="optionlist" value=3>불쾌한내용</option>
				<option class="optionlist" value=4>음란물</option>
				<option class="optionlist" value=5>허위정보</option>
				<option class="optionlist" value=6>스팸</option>
				<option class="optionlist" value=7>욕설</option>
				<option class="optionlist" value=8>폭력물</option>
				<option class="optionlist" value=9>기타</option>
			</select>
		</div>
		<div class="reportContentdiv"><!-- 게시글 신고하기 내용 div -->
			<textarea name="content" class="reportContent" placeholder="문의 내용을 입력해주세요" style="resize:none;" maxlength="2000" required></textarea>
		</div>
		<div class="reportButtonArea"><!-- 게시글 신고하기 버튼 div -->
			<button type="button" id ="cancelValue" class="reportToButton1" onclick="askcancel()">Cancel</button>
			<button type="button" id="sendValue" class="reportToButton2" >OK</button>
		</div>
		</form>
	</div>
	<script type ="text/javascript">
	
	function askcancel(){
		var cancel = confirm("글 작성을 취소하시겠습니까?");
		if(cancel){
			 window.close()
		}else{
			return;
		}
	}
	//ok버튼 누를시에 form전송을 하고나서 콘솔창 닫기 
	$(function(){
		$("#sendValue").on('click', function(){
			console.log($("#title").text())
			console.log($("#content").text())
			theForm = document.reportform;
			if(confirm("게시글을 신고 하시겠습니까?") ==true){
				if(theForm.title.value == "" && theForm.content.value == ""){
					
					alert("제목과 내용을 입력해주셔야 등록이 됩니다.")
					
				}else if(theForm.title.value == ""){
					alert("제목을 입력해주셔야 합니다.")
				}else if(theForm.content.value == ""){
					alert("내용을 입력해주셔야 합니다.")
				}
				if(theForm.title.value != "" && theForm.content.value != ""){
					var formData = $("#reportform").serialize();
					console.log(formData)
					$.ajax({
						type:"POST", 
						url : "insertUsedReportform.do", 
						data : formData, 
						success :function(result){
							console.log(result)
							if(result != null){
								alert("등록이 완료되었습니다")
								parent.opener.location.reload();
								window.open('', '_self', '').close();
							}
							
						}, 
						error: function(e){
							console.log(e)
						}
					})
					
				}
				
			}else{
				return;
			}
			
		})
	})
		
		
	</script>
</body>
</html>