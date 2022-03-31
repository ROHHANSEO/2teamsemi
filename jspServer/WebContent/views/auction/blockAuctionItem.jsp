<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String scno = (String)request.getAttribute("scno");
	String btitle = (String)request.getAttribute("btitle");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>경매 게시글 신고</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
	html{
		background-color:#EFEFEF;
	}
	.AuctionBlockDiv{
		margin-top:14px;
		width:95vw;
		height:93vh;
	}

	.auctionTitlediv{
		height:10%;
	}
	
	.BlockCategorydiv{
		height:10%;
		margin-top:8px;
	}
	.Blockcontentdiv{
		height:40%;
		margin-top:8px;
	}
	.auctionTitle{
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
	.BlockCategory{
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
	.Blockcontent{
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
	.Blockbuttondiv{
		height:10%;
		margin-top:8px;
	}
	.Blocktobutton1, .Blocktobutton2{
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
	.Blocktobutton1:hover, .Blocktobutton2:hover{
		background-color: #993333;
    	color: white;
    	cursor:pointer;
	}
</style>
</head>
<body>
	<div class= "AuctionBlockDiv"><!-- 경매 게시글 신고 전체 div -->
		<form name="ABlockform" id = "ABlockform"   method="post">
		<input type="hidden" name="scno" value="<%=scno %>" class="auctionno">
		<input type="hidden" name="btitle" value="<%=btitle %>" class="auctionno">
		<div class="auctionTitlediv"><!-- 경매 신고 제목 div -->
			<input type="text" name="title" class="auctionTitle" placeholder="문의 제목을 입력해주세요" maxlength="40" required>
		</div>
		<div class="BlockCategorydiv"><!-- 신고 카테고리 div -->
			<select class="BlockCategory" name="category" required>
				<option class="optionlist" value="불법" selected>불법</option>
				<option class="optionlist" value="다른카테고리">다른카테고리</option>
				<option class="optionlist" value="불쾌한내용">불쾌한내용</option>
				<option class="optionlist" value="음란물">음란물</option>
				<option class="optionlist" value="허위정보">허위정보</option>
				<option class="optionlist" value="스팸">스팸</option>
				<option class="optionlist" value="욕설">욕설</option>
				<option class="optionlist" value="폭력물">폭력물</option>
				<option class="optionlist" value="기타">기타</option>
			</select>
		</div>
		<div class="Blockcontentdiv"><!-- 신고 내용 div -->
			<textarea name="content" class="Blockcontent" placeholder="문의 내용을 입력해주세요" style="resize:none;" maxlength="2000" required></textarea>
		</div>
		<div class="Blockbuttondiv"><!-- 신고 버튼 div -->
			<button type="button" id ="cancelValue" class="Blocktobutton1" onclick="askcancel()">Cancel</button>
			<button type="button" id="sendValue" class="Blocktobutton2" >OK</button>
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
			theForm = document.ABlockform;
			if(confirm("문의 글을 작성하시겠습니까?") ==true){
				if(theForm.title.value == "" && theForm.content.value == ""){
					
					alert("제목과 내용을 입력해주셔야 등록이 됩니다.")
					
				}else if(theForm.title.value == ""){
					alert("제목을 입력해주셔야 합니다.")
				}else if(theForm.content.value == ""){
					alert("내용을 입력해주셔야 합니다.")
				}
				if(theForm.title.value != "" && theForm.content.value != ""){
					var formData = $("#ABlockform").serialize();
					console.log(formData)
					$.ajax({
						type:"POST", 
						url : "insertABlockform.do", 
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