<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객센터 1:1문의</title>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
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
	}
	.QtoAcategory{
		height:33px;
		width:81.5% ;
		line-height:30px;
		position: relative;
    	left:50%;
		transform:translateX(-50%);
		font-family: sans-serif;
	}
	.QtoAcontent{
		height:150px;
		width:80%;
		position: relative;
    	left:50%;
		transform:translateX(-50%);
		font-family: sans-serif;
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
		border: 1px solid #993333;
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
	<div class= "QtoAtotaldiv">
		<form name="QtoAform" method="post">
		<div class="QtoAtitlediv">
			<input type="text" name="title" class="QtoAtitle" placeholder="문의 제목을 입력해주세요" maxlength="40" required>
		</div>
		<div class="QtoAcategorydiv">
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
		<div class="QtoAcontentdiv">
			<textarea name="content" class="QtoAcontent" placeholder="문의 내용을 입력해주세요" style="resize:none;" maxlength="2000" required></textarea>
		</div>
		<div class="QtoAbuttondiv">
			<button type="button" class="QtoAtobutton1">Cancel</button>
			<button type="submit" class="QtoAtobutton2">OK</button>
		</div>
		</form>
	</div>
	<script type="text/javascript">
		$(".QtoAtobutton1").click(function(){
			swal("hello");
		})
	</script>
		<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
</body>
</html>