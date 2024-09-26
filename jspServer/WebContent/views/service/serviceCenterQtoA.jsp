<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객센터 1:1문의</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
	html{
		background-color:#EFEFEF;
	}
	.QtoAtotaldiv{
		margin-top:14px;
		width:95vw;
		height:93vh;
	}

	.QtoAtitlediv{
		height:10%;
	}
	
	.QtoAcategorydiv{
		height:10%;
		margin-top:8px;
	}
	.QtoAcontentdiv{
		height:40%;
		margin-top:8px;
	}
	.QtoAtitle{
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
	.QtoAcategory{
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
	.QtoAcontent{
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
	.QtoAbuttondiv{
		height:10%;
		margin-top:8px;
	}
	.QtoAtobutton1, .QtoAtobutton2{
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
	.QtoAtobutton1:hover, .QtoAtobutton2:hover{
		background-color: #993333;
    	color: white;
    	cursor:pointer;
	}
</style>

</head>
<body>
	<div class= "QtoAtotaldiv"><!-- 1:1문의 전체 div -->
		<form name="QtoAform" id = "QtoAform"   method="post">
		<div class="QtoAtitlediv"><!-- 1:1문의 제목 div -->
			<input type="text" name="title" class="QtoAtitle" placeholder="문의 제목을 입력해주세요" maxlength="40" required>
		</div>
		<div class="QtoAcategorydiv"><!-- 1:1문의 카테고리 div -->
			<select class="QtoAcategory" name="category" required>
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
		<div class="QtoAcontentdiv"><!-- 1:1문의 내용 div -->
			<textarea name="content" class="QtoAcontent" placeholder="문의 내용을 입력해주세요" style="resize:none;" maxlength="2000" required></textarea>
		</div>
		<div class="QtoAbuttondiv"><!-- 1:1문의 버튼 div -->
			<button type="button" id ="cancelValue" class="QtoAtobutton1" onclick="askcancel()">Cancel</button>
			<button type="button" id="sendValue" class="QtoAtobutton2" >OK</button>
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
		$("#sendValue").click(function(){
			console.log($("#title").text())
			console.log($("#content").text())
			theForm = document.QtoAform;
			if(confirm("문의 글을 작성하시겠습니까?") ==true){
				if(theForm.title.value == "" && theForm.content.value == ""){
					
					alert("제목과 내용을 입력해주셔야 등록이 됩니다.")
					$(".QtoAtitle").focus();
				}else if(theForm.title.value == ""){
					alert("제목을 입력해주셔야 합니다.")
					$(".QtoAtitle").focus();
				}else if(theForm.content.value == ""){
					alert("내용을 입력해주셔야 합니다.")
					$(".QtoAcontent").focus();
				}
				if(theForm.title.value != "" && theForm.content.value != ""){
					var formData = $("#QtoAform").serialize();
					console.log(formData)
					$.ajax({
						type:"POST", 
						url : "insertQtoAform.do", 
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