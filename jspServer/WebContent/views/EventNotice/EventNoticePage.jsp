<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.uni.event.model.vo.*"%>
<%
	ArrayList<Event> list = (ArrayList<Event>)request.getAttribute("list");
	
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	
	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	
	String style = "background: #993333; color : white;";
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" media="screen" href="../../resources/css/Event/list.css">
<link rel="stylesheet" type="text/css" media="screen" href="../../resources/css/common/common.css">
<title>이벤트&공지사항</title>
	

</script>

</head>
	<%@ include file = "../common/header.jsp" %>
<div class="normal">
    	
    </div>

<body>
<div class="normal">
	<div class="outer">
		<br>
		<h2 align="center" >이벤트&공지사항</h2>
		
		
		
		
		<table class="listArea" >
		 
			<thead>
				<tr class="menubar">
					<th width="10">no.</th>
					<th width="500">글제목</th>
					<th width="100">작성자</th>
					<th width="100">카테고리</th>
					<th width="100">조회수</th>
					<th width="100">작성일</th>
				</tr>
			</thead>
			<tbody class="serviceCListFinal" >
				    		
			</tbody>
			<tbody>
				

				<% if(list.isEmpty()){ %>
				 	<tr>
						<td colspan="6">존재하는 공지사항이 없습니다.</td>
					</tr>
				 <% }else{  %>
				 	<% for(Event e : list){ %>
				 		<tr>
				 			<td><%= e.getNoticeno() %></td>
				 			<td><%= e.getNoticeTitle() %></td>
				 			<td><%= e.getUserid() %></td>
							<td><%= e.getCategory() %></td>
							<td><%= e.getCount() %></td>
							<td><%= e.getCreateDate() %></td>
				 		</tr>
				 	<% } %>
				 <% } %>
			</tbody>
			
		<div class="enrollevent" align="right">
    	<% if(user != null && user.getUserId().equals("admin")) { %>    	
		<input type="button" class="commonwritebutton bts"   id="inse" value="글작성" onclick="location.href='<%= request.getContextPath() %>/enrollevent.do'">	
		<% } %>
		</div>
			
		</table>
		
		
		
		</div>
		
		<div class="formgroupmiddle">
    			<div class="seacrchidichinput">
    		<!-- 분류 종류 : 제목, 내용 -->
    				<select class="optionserachCList" name="searchClist">
    					<option value="title" selected>제목</option>
    					<option value="content">내용</option>
    				</select>
    				<input class="seacrchinput" placeholder="검색어를 입력하세요." type="text" >
    		<!-- onkeyup:은 텍스트가 입력된 후 이벤트 실행 -->

    				<button class="commonwritebutton bbc" onclick="searchFunction();" type="button">검색</button>
    			</div>
    		</div>
		
		
		
		</div>
		<!-- 페이징바 만들기 -->
		<div class="pagingArea" align="center">
		<!-- 맨 처음으로 (<<) -->
		<button class="commonwritebutton bts" onclick="location.href='<%=request.getContextPath()%>/eventpage.do?currentPage=1'"> &lt;&lt; </button>
	
		<!-- 이전페이지로(<) -->
		<%if(currentPage == 1){ %>
		<button class="commonwritebutton bts" disabled> &lt; </button>
		<%}else{ %>
		<button class="commonwritebutton bts" onclick="location.href='<%= request.getContextPath() %>/eventpage.do?currentPage=<%= currentPage-1 %>'"> &lt; </button>
		<%} %>
		
		<!-- 페이지 목록 -->
		<%for(int p=startPage; p<=endPage; p++){ %>
			
			<%if(p == currentPage){ %>
			<button class="commonwritebutton bts" disabled> <%= p %> </button>
			<%}else{ %>
			<button class="commonwritebutton bts" onclick="location.href='<%=request.getContextPath() %>/eventpage.do?currentPage=<%= p %>'"> <%= p %> </button>
			<%} %>
			
		<%} %>
		
		<!-- 다음페이지로(>) -->
		<%if(currentPage == maxPage){ %>
		<button class="commonwritebutton bts" disabled> &gt; </button>
		<%}else { %>
		<button class="commonwritebutton bts" onclick="location.href='<%= request.getContextPath() %>/eventpage.do?currentPage=<%= currentPage+1 %>'"> &gt; </button>
		<%} %>
	
		<!-- 맨 끝으로 (>>) -->
		<button class="commonwritebutton bts" onclick="location.href='<%=request.getContextPath()%>/eventpage.do?currentPage=<%=maxPage%>'"> &gt;&gt; </button>
	
	
	
	</div> 
	
	
			
		<script type="text/javascript">
			<%if(!list.isEmpty()){%>
			$(function(){
				$(".listArea>tbody>tr").click(function(){
					var nno = $(this).children().eq(0).text();
					
					location.href="<%=request.getContextPath()%>/detailEvent.do?nno="+nno;
				})
			})
		<%}%>
		
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
       					var tt2 = $("<th colspan='6'>").text("존재하는 공지사항이 없습니다.");
       					$(".outer").css("height", "800")
       					var tt = $("<tr>").append(tt2);
       					$(".serviceCListFinal").append(tt);
       					
					}else if(list.length !=0){
       					var number = 1;
       					$(".outer").css("height", "800")
       					
       				$.each(list, function(index, obj){
       					console.log(obj.serviceWriter)
       					
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
       					<%if(user != null && user.getUserId().equals("admin")) {%>
       					$(".totalUpdateService").css("visibility", "visible");
       					<%}%>
       			}
    			}
    		})
    	}
		
		
		
		</script>
		

	<div>
    	<%@ include file = "../common/footer.jsp" %>
    </div>
</body>
</html>