<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.uni.serviceCenter.model.vo.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../../resources/css/common/common.css">
<link rel="stylesheet" href="../../resources/css/serviceCenter/serviceCenter.css">
<link rel="stylesheet" href="../../resources/library/animate.css">
</head>
<body>
	
	<%@include file="../common/header.jsp" %>
	<div class="wrapper">
	<div class = "outer">
	
    <br>
    <div class="formgroupmiddle">
    	<div class="seacrchidichinput">
    		<!-- 분류 종류 : 제목, 내용 -->
    		<select class="optionserachCList" name="searchClist">
    			<option value="title" selected>제목</option>
    			<option value="content">내용</option>
    		</select>
    		<input class="seacrchinput" placeholder="검색어를 입력하세요." type="text" >
    		<!-- onkeyup:은 텍스트가 입력된 후 이벤트 실행 -->

    		<button class="form-controlsearchbutton" onclick="searchFunction();" type="button">검색</button>
    	</div>
    </div>
    <div class = "questionenroll">
    <div class = "questioneach"> 
    	<a href="#" onclick="serviceCenterQtoA();" class= "animation"> 1:1 문의하기 </a>
    </div>
    <div class="gotoenrollFormServiceCenter">
    	<button class="commonwritebutton serviceCenterwrite" onclick="writeFunction();" type="button">글 작성</button>
    </div>
    </div>
    <div  class ="serviceCentermiddle">
       	<div class = "all"> 자주 묻는 질문 </div>
       	<div class = "all">운영정책</div>
        <div class = "all">계정/인증</div>
        <div class = "all">구매/판매</div>
        <div class = "all">거래 품목</div>
        <div class = "all">거래 매너</div>
        <div class = "all">이벤트</div>
        <div class = "all">이용 제재</div>
        <div class = "all">커뮤니티</div>
        <div class = "all">경매</div>
        <div class = "all">채팅</div>
        <div class = "all">기타</div>
    </div>
    <div class="serviceCenterRe"> 자주 묻는 질문 </div><!-- 위에서 받아온 값 -->

    <div class="serviceCenterArea" align="center">
    <table class="serviceno" >
    	<thead>
    		<tr >
    			<th> 번호 </th>
    			<th class="serviceCenterQuestion" colspan="2"> 질문 </th>

    		</tr>
    	</thead>
    	<tbody class="serviceCListFinal">
    		<tr>
    			<th></th>
    			<th colspan="2"> 존재하는 공지사항이 없습니다 </th>
    		</tr>
    	</tbody>
	</table>
	
	
    </div>
    </div>
    <%@ include file = "../common/footer.jsp" %>
    </div>
    <script type="text/javascript">
 		function writeFunction(){//글작성하기 버튼
 			location.href="<%=request.getContextPath()%>/enrollFormServiceCenter.do";
 		}
    	function serviceCenterQtoA(){//1:1문의하기
    		window.open("<%= request.getContextPath()%>/serviceCenterQtoA.do","1:1문의 창", "width=500, height=300, top=350, left=600");
    	}
    	//위에 검색 부분
    	function searchFunction(){
    		//테이블 초기화 
			$(".serviceCListFinal").empty();
    		var selectinput = $(".optionserachCList").val();
    		console.log(selectinput)
    		var input = $(".seacrchinput").val();
    		console.log(input)
    		$.ajax({
    			url:"findserviceCenter.do", 
    			data: {
    				selectinput:selectinput,
    				input:input
    			}, 
    			type:"get", 
    			success: function(list){
    				
					if(list.length ==0){
       					var tt2 = $("<th colspan='2'>").text("존재하는 공지사항이 없습니다.");
       					$(".outer").css("height", "800")
       					var tt = $("<tr>").append(tt2);
       					$(".serviceCListFinal").append(tt);
       					
					}else if(list.length !=0){
       					var number = 1;
       					$(".outer").css("height", "800")
       				$.each(list, function(index, obj){
       					heightChange(list);
       					var serviceNo = $("<td>").text(number++).addClass("serviceCListt");
       					var serviceTd = $("<td>").html(obj.serviceTitle).addClass("serviceCListtitle");
       					var servicehidden = $("<td><div>").text(obj.serviceNo).addClass("serviceHiddenNo");
       					
       					var tr = $("<tr>").append(serviceNo, serviceTd, servicehidden).addClass("serviceCListClick");
       					
       					
       					var answerNo = $("<td>").text("Q");
       					var answerTC = $("<td>").html(obj.serviceContent).addClass("serviceAnswerMiddle");
       					var totalUpdate = $("<td>").text("수정하기").addClass("totalUpdateService");
       					
       					var tr2 = $("<tr>").append(answerNo, answerTC, totalUpdate).addClass("serviceAnswerList").addClass("serviceAnswerMiddle2");
       					$(".serviceCListFinal").append(tr).append(tr2);
       					
       				});
       				//number, title, count
       				
       			}
    			}
    		})
    	}
    	
       	$(".all").click(function(){
   		var input = $(this).text();
   		console.log(input)
   		$.ajax({
   			url:"serviceCenterRe.do", 
   			data:{
   				input:input	
   			}, 
   			type : "get",
   			success: function(result){
   				console.log("serviceCenter값 받아오기 성공")
   				$(".serviceCenterRe").text(result);
   				if(result!= null){
   					selectCList(input);
   				}
   			}, 
   			err:function(){
   				console.log("serivceCenter값 받기 실패했음")
   			} 
   		
   		})
    		
    	})
 		
    	function selectCList(input){
       		$(".serviceCListFinal").empty();
       		
       		$.ajax({
       			url:"serviceCenterCList.do", 
       			data:{
       				input:input
       			}, 
       			type:"get", 
       			success: function(list){
       				console.log(list)
       				console.log(JSON.stringify(list).length)
    
       				if(list.length ==0){
       					
       					$(".outer").css("height", "800")
       					var tt2 = $("<th colspan='2'>").text("존재하는 공지사항이 없습니다.");
       					
       					var tt = $("<tr>").append(tt2);
       					$(".serviceCListFinal").append(tt);
       					
       				}else if(list.length !=0){
       					console.log("여기로 들어가나 보자")
       					$(".outer").css("height", "800")
       					var number = 1;
       					
       				$.each(list, function(index, obj){
       					heightChange(list);
       					console.log(list.length +" list의 길이를 알아보자")
       					var serviceNo = $("<td>").text(number++).addClass("serviceCListt");
       					var serviceTd = $("<td>").html(obj.serviceTitle).addClass("serviceCListtitle");
       					var servicehidden = $("<td><div>").text(obj.serviceNo).addClass("serviceHiddenNo");
       				
       					var tr = $("<tr>").append(serviceNo, serviceTd, servicehidden).addClass("serviceCListClick");
       					
       					
       					var answerNo = $("<td>").text("Q");
       					var answerTC = $("<td>").html(obj.serviceContent).addClass("serviceAnswerMiddle");
       					var totalUpdate = $("<td>").text("수정하기").addClass("totalUpdateService");
       					
       					var tr2 = $("<tr>").append(answerNo, answerTC, totalUpdate).addClass("serviceAnswerList").addClass("serviceAnswerMiddle2");
       					$(".serviceCListFinal").append(tr).append(tr2);
       				
       				});
       				//number, title, count
       				}
       			}, 
       			error:function(){
       				console.log("serviceCList 통신 실패")
       			}
       			
       		})
       	}
       	var k=0;
       	function heightChange(list){
       		
       		k = $(".outer").height();
			$(".outer").css("height",k*1.018);
			console.log(k)
       	}
       	
     	$(document).on("click",".serviceCListClick",function(){
     		$(".outer").css("height", k)
     		$(".serviceCListClick").removeClass("clicked_serviceCListClick")
     		if($(this).next().hasClass("serviceAnswerList") ){
     			$(this).addClass("clicked_serviceCListClick")
     			$(".serviceAnswerMiddle2").addClass("serviceAnswerList")
         		$(this).next().removeClass("serviceAnswerList")
         		var h = $(".outer").height();
       			var t = $(this).next().height();
       			console.log(t)
				$(".outer").css("height",h+t);
				console.log(h)
     		}else{
     			$(this).next().addClass("serviceAnswerList")
     			$(".outer").css("height", k)
     		}
     		
		})
       	$(document).on("click", ".totalUpdateService", function(){
       		var scno =$(this).parent().prev().children().eq(2).text();//serviceNo(선택한 글의 primary key값)
       		console.log(scno)
       		
       		location.href="<%=request.getContextPath()%>/updateTotalServiceList.do?scno="+scno;
       	})
		
    </script>
</body>
</html>