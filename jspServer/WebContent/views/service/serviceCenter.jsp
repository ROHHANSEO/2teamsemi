<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.uni.serviceCenter.model.vo.ServiceCenter"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../../resources/css/common/common.css">
<link rel="stylesheet" href="../../resources/css/serviceCenter/serviceCenter.css">
</head>
<body>
	<%@include file="../common/header.jsp" %>
	<div class = "outer">
	
    <br>
    <h2  id = "search"> 여기는 검색부분 </h2>
    
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
    			<th class="serviceCenterQuestion"> 질문 </th>

    		</tr>
    	</thead>
    	<tbody class="serviceCListFinal">
    	
    	</tbody>
	</table>
	
    		<%-- <%if(list ==null){ %>
    		<tr>
				<td colspan="3" class="serviceno">존재하는 공지사항이 없습니다.</td>
			</tr>
    		<%}else{ %>
    		<% for(ServiceCenter sc : list){ %>
			 		<tr>
			 			<td><%= sc.getServiceNo() %></td>
						<td><%= sc.getServiceTitle() %></td>
						<td><%= sc.getCount() %></td>
			 		</tr>
			 	<% } %>
			 <% } %>  --%>

			
    	
    
    </div>
    <script>

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
       				console.log(list);
       				if(list == null){
       					$(".serviceCListFinal").append($("<tr>").$("<td>").text("존재하는 공지사항이 없습니다."));
       				}else{
       					var number = 1;
       				$.each(list, function(index, obj){
       					
       					var serviceNo = $("<td>").text(number++).addClass("serviceCListt");
       					var serviceTd = $("<td>").text(obj.serviceTitle).addClass("serviceCListtitle");
       				
       					var tr = $("<tr>").append(serviceNo, serviceTd).addClass("serviceCListClick");
       					
       					
       					var answerNo = $("<td>").text("Q");
       					var answerTC = $("<td>").text(obj.serviceContent).addClass("serviceAnswerMiddle");
       					
       					var tr2 = $("<tr>").append(answerNo, answerTC).addClass("serviceAnswerList").addClass("serviceAnswerMiddle2");
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
       	
     	$(document).on("click",".serviceCListClick",function(){
     		$(".serviceCListClick").removeClass("clicked_serviceCListClick")
     		if($(this).next().hasClass("serviceAnswerList") ){
     			$(this).addClass("clicked_serviceCListClick")
     			$(".serviceAnswerMiddle2").addClass("serviceAnswerList")
         		$(this).next().removeClass("serviceAnswerList")
     		}else{
     			$(this).next().addClass("serviceAnswerList")
     		}
     		
		})
       	
    </script>
	<%@ include file = "../common/footer.jsp" %>
</body>
</html>