<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.uni.usedItemBoard.model.vo.*"%>
<%
	ArrayList<Category> cList = (ArrayList<Category>) request.getAttribute("category");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>경매 글 작성페이지</title>
<link rel="stylesheet" href="../../resources/css/common/common.css">
<style type="text/css">
	.enrollForm1{
		width:70%;
		margin:0 auto;
		margin-bottom:30px;
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
  	.firstcategory{
  		width: 150px;
  		height: 30px;
  		border-radius: 5px;
  	}
  	.imgDiv{
  		display:flex;
  		margin:10px auto 10px auto;
  	}
  	#imagin {
	    width: 59vw;
	    margin-right:15px;
	    margin-bottom:15px;
	}
  	.article{
		display: flex;
	}
	#camera{
	    position: relative;
	    display: flex;
	    justify-content: center;
	    align-items: center;
	    background-color: #efefef;
	    width: 57vw;
	    height: 200px;
	    text-align: center;
	    flex-wrap: wrap;
	    margin: 10px;
	}
	input[type = "file"]{
		width: 30%;
	    height: 200px;
	    opacity: 0;
	    position: absolute;
	}
	/* li 호버시 어두워짐 */
	ul .previewImg:hover img {
	    opacity: 0.9;
	}
	ul .previewImg:hover .del{ 
		opacity: 1;
	    color: white;
	    font-size: 70px;
	    left: 50%;
	    bottom: 50%;
	    transform: translate(-50%, 50%);
	    font-family: monospace;
	    font-weight: bold;
	}
	.previewImg {
	    margin: 10px;
	    margin-right: 0px;
	    margin-top: 5px;
	    position: relative;
	    background: #000000;
	    height: 200px;
	}
	
	.del{
		position:absolute;
		opacity: 0;
	}
	#sortimg {
	    list-style: none;
	    display: flex;
	    /* justify-content: center; */
	    /* width: 300px; */
	    /* height: 70%; */
	    flex-wrap: wrap;
	    margin-top: 5px;
	}
	.itemstatusDiv{
		border-bottom:1px solid black;
		display:flex;
	}
	.radioDiv{
		text-align:center;
		justify-content: center;
	    align-items: center;
	    font-size:20px;
	}
	#new, #used{
		margin-top:15px;
	}
	.a{
		margin-left:15px;
	}
	.divdiv{
		transform: translateX(25px);
	}
	.auctionprice{
		display:flex;
		border-bottom: 1px solid black;
	}
	.fprice{
		width:250px;
		height:30px;
		margin-top: 10px;
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
    
</style>
</head>
<body>
	<%@include file="../common/header.jsp"%>
	<div class = "enrollForm1">
	<div class="headerenrollForm"><!-- 고객센터 글 작성 위에 부분 -->
		<p> 경매 게시판 글 작성</p>
	</div>
	<div class="underheaderenrollF"></div><!-- 라인 부분 -->
	<form name="auctionForm" class="auctionForm" action="<%=request.getContextPath()%>/insertAuctionform.do" method="post" enctype="multipart/form-data">
	<div class="enrollTitlesection"><!-- 제목 div -->
		<span class="titleNamepart"> 제목<span class="dotpoint">*</span></span>
		<input type="text" name="title" class="counttitlename" placeholder="제목을 입력해주세요" required >
		<span class="counttitlelength">0 / 40</span>
	</div>
	<div class="enrollTitlesection"><!-- 카테고리 선택 div -->
		<span class="titleNamepart"> 카테고리 <span class="dotpoint">*</span></span>
		<div class="categorytogglepart">
			<select id="large" class="firstcategory" name="large" required>
				<option value="대분류"> 대분류 </option>
				<%for(Category ca : cList){ %>
						<option value="<%=ca.getCode()%>"><%=ca.getName() %></option>
				<%} %>
			</select>
			<select id="middle" class="firstcategory" name="middle">
				<option id="examplemiddle" value="중분류">중분류</option>
			</select>
			<select id = "small" class="firstcategory" name="small">
				<option id="examplesmall" value="소분류">소분류</option>
			</select> 
		</div>
	</div>
	<div class="imgDiv"><!-- 이미지 div -->
		<div>
		<span class="titleNamepart"> 이미지 선택<span class="dotpoint">*</span></span>
		<p class="a">최대 10개의 </p><p class="a">이미지 가능</p>
		</div>
		<div class="divdiv">
			<div class="article">
				<div id="imagin">
					<div id="camera">
						<input type="file" name="file1" id="file1" accept='.gif, .jpg, .png' multiple />
						<img src="https://img.icons8.com/material-rounded/24/000000/camera--v2.png" />
						<br> 이미지 선택
					</div>
					<ul id="sortimg">
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="itemstatusDiv"><!-- 상품 상태 선택 div -->
		<span class="titleNamepart"> 상품 상태 <span class="dotpoint">*</span></span>
		<div class="radioDiv">
			<input type="radio" id="new" value="새상품" name="productStatus"checked> 새상품 
			<input type="radio" value="중고상품" name="productStatus" id="used"> 중고 상품
		</div>
	</div>
	<div class="auctionprice"><!-- 경매가격 선택 div -->
		<span class="titleNamepart"> 가격 <span class="dotpoint">*</span></span>
		<div>
			<input type="text" name="fprice" id="fprice" placeholder="경매 시작가를 적어주세요" class="fprice" required> 원
		</div>
	</div>
	<div class="auctionprice"><!-- 올릴경매가 div -->
		<span class="titleNamepart"> 올릴 경매가 <span class="dotpoint">*</span></span>
		<div>
			<input type="text" name="sprice" id="sprice" placeholder="올릴 경매가를 적어주세요" class="fprice" required> 원
		</div>
	</div>
	<div class="auctionprice"><!--즉시 판매가  div -->
		<span class="titleNamepart"> 즉시 판매가 <span class="dotpoint">*</span></span>
		<div>
			<input type="text" name="tprice" id="tprice" placeholder="즉시 판매가를 적어주세요" class="fprice" required> 원
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
	<%@ include file = "../common/footer.jsp" %> 
	<script>
	
	function deletebye(index){
	    console.log(index)
	    let deleteE = document.getElementById("image"+index)
	    console.log(deleteE)
	    $("#image"+index).parent().remove()
	    $("file1").empty()
	}
	    
	// 인덱스 생성
	let index = 0;
	$(function(){
	      //드래그 앤 드롭
	      //$(".imagin").sortable();
	    
      //이미지 등록
    $("#file1").change(function(e){
        //ul 내용 비워주기
        $('#sortimg').empty();

        var files = e.target.files;
        var arr = Array.prototype.slice.call(files);
        
        var maxFileCnt = 10;   // 첨부파일 최대 개수
        var attFileCnt = document.querySelectorAll('#file1').length;    // 기존 추가된 첨부파일 개수
        var remainFileCnt = maxFileCnt - attFileCnt;    // 추가로 첨부가능한 개수
        var curFileCnt = this.files.length;  // 현재 선택된 첨부파일 개수
        console.log(remainFileCnt)
        console.log(curFileCnt)
        // 첨부파일 개수 확인
        if (curFileCnt > maxFileCnt) {
            alert("첨부파일은 최대 " + maxFileCnt + "개 까지 첨부 가능합니다.");
            return false;
        }

        //업로드 가능 파일인지 체크
        for(var i=0; i<files.length; i++){
            if(!checkExtension(files[i].name,files[i].size)){
                return false;
            }
        }
        preview(arr);
        
        function checkExtension(fileName,fileSize){
            var maxSize = 10971520;  //10MB

            if(fileSize >= maxSize){
                alert('이미지 크기가 초과되었습니다.');
                $("#file1").val("");  //파일 초기화
                return false;
            }
            
            return true;
        }

        
        function preview(arr){
            arr.forEach(function(f){

                //li에 이미지 추가
                var str = '<li class="previewImg">';
                //str += '<span>'+fileName+'</span><br>';
                
                //이미지 파일 미리보기
                if(f.type.match('image.*')){
                    
                    //파일을 읽기 위한 FileReader객체 생성
                    var reader = new FileReader(); 
                    reader.onload = function (e) { 
                        
                        //파일 읽어들이기를 성공했을때 호출되는 이벤트 핸들러
                        str += '<a id="image'+ index +'" class="image" onclick="deletebye(`'+index+'`);">'+"<span class='del'>x</span>"+'<img name="img'+index+'" src="'+e.target.result+'" title="'+f.name+'" width=200 height=200></a>';
                        str += '</li>';
                        $(str).appendTo('#sortimg');
                        ++index;
                    } 
                    reader.readAsDataURL(f);
	                }
	            })
	        }
	    })
    })
	// 게시글 체크
	function checkform() {
	    
	    // 제목 미설정시 알림
	    if(confirm("글을 등록하시겠습니까?") == true){
	        let theForm = document.auctionForm;
	        // 제목과 설명을 적지 않을시
	        if(theForm.title.value == "" && theForm.content.value == ""){
	            alert("제목과 설명을 작성해 주세요")
	            return $("#title").focus();
	        }else if(theForm.title.value == ""){
	            alert("제목을 작성해 주세요.")
	            return $("#title").focus();
	            // 설명을 적지 않을 시
	        }else if(theForm.content.value == ""){
	            alert("상품 설명을 작성해 주세요")
	            return $("#content").focus();
	            // 카테고리 설정 하지 않았을시 알림
	        }else if($("#large").val() == '대분류'){
	            alert("카테고리를 선택해 주세요");
	            return $("#large").focus();
	            // 이미지 미선택시 알림
	        }else if(!$("#file1").val()){
	            alert("이미지는 최소 1장이 필요합니다.")
	            return $("#imagin").focus();
	            // 가격 작성 하지 않을 시 알림
	        }else if(theForm.price.value == ''){
	            alert("가격을 작성해 주세요")
	            return $("#price").focus();
	        }

	        theForm.submit();
	    }
	}
	// 중분류 도출
	$("#large").change(function() {
	    let category = $("#large").val()
	    console.log(category)
	    
	    $.ajax({
	        url:"largeSelectA.do",
	        data:{
	        	category : category
	        },
	        type:"get",
	        success:function(list){
	            console.log("Ajax 통신 성공")
	            $("#middle").empty()
	            $("#small").text()
	            if(list.length != 0){
	                let examplemiddle = $("<option>").text("중분류").attr("value", "중분류")
	                $("#middle").append(examplemiddle)
	                $.each(list, function(index, obj){
	                    let middle = $("<option>").text(obj.name).attr("value", obj.code)
	                    console.log(obj.name)
	                    $("#middle").append(middle)
	                })
	            }else if(list.length == 0){// 대분류로 끝날 때
	                let examplemiddle = $("<option>").text("중분류").attr("value", "중분류")
	                $("#middle").append(examplemiddle)
	            }
	        },
	        error:function(){
	            console.log("Ajax 통신 실패")
	            
	        }
	    })
	})

	// 소분류 도출
	$("#middle").change(function() {
	    let category = $("#middle").val()
	    console.log(category)
	    
	    $.ajax({
	        url:"middleSelectA.do",
	        data:{
	        	category : category
	        },
	        type:"get",
	        success:function(list){
	            console.log("Ajax 통신 성공")
	            $("#small").empty()
	            if(list.length != 0){
	                let examplesmall = $("<option>").text("소분류").attr("value", "소분류")
	                $("#small").append(examplesmall)
	                $.each(list, function(index, obj){
	                    console.log(obj.name)
	                    let small = $("<option>").text(obj.name).attr("value", obj.code)
	                    $("#small").append(small)
	                })
	            }else if(list.length == 0){ // 중분류로 끝날 때
	                let examplesmall = $("<option>").text("소분류").attr("value", "소분류")
	                $("#small").append(examplesmall)
	            }
	        },
	        error:function(){
	            console.log("Ajax 통신 실패")
	            
	        }
	    })
	})
	
		//게시글 체크 
		<%--$(".gotoSubmitbutton").click(function(){
			console.log($("#title").text())
			console.log($("#content").text())
			theForm = document.auctionForm;
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
		
--%>

	function askcancel(){
		var cancel = confirm("글 작성을 취소하시겠습니까?");
		if(cancel){
			 location.href="<%=request.getContextPath()%>/auctionPage.do";
		}else{
			return;
		}
	}

	//제목 40자 미만으로 설정
	$(".counttitlename").on('keyup',function(){
		$(".counttitlelength").html(($(this).val().length+" / 40"));
		
		if($(this).val().length>40){
			alert("제목은 40자 이하로 적어주세요")
			$(this).val($(this).val().substring(0, 40));
            $('.counttitlelength').html("(40 / 40)");
		}
	})

	//내용 2000자 미만으로 설정

	$(".contentWritePart").on('keyup',function(){
		$(".countContentlength").html(($(this).val().length+" / 2000"));
		
		if($(this).val().length>2000){
			alert("내용은 2000자 이하로 적어주세요")
			$(this).val($(this).val().substring(0, 2000));
            $('.countContentlength').html("(2000 / 2000)");
		}
	})

		
	</script>
	
</body>
</html>