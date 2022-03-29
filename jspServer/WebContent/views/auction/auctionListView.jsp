<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.uni.usedItemBoard.model.vo.Category, com.uni.auction.model.vo.*" %>
<%
	   	ArrayList<Category> list = (ArrayList<Category>)request.getAttribute("list");
		ArrayList<Auction> aulist = (ArrayList<Auction>)request.getAttribute("aulist");
		
		PageInfo pi = (PageInfo)request.getAttribute("pi");
		
		int listCount = pi.getListCount();
		int currentPage = pi.getCurrentPage();
		int maxPage = pi.getMaxPage();
		int startPage = pi.getStartPage();
		int endPage = pi.getEndPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 경매 페이지 </title>
<link rel="stylesheet" href="../../resources/css/common/common.css">
<style>
	.wtotal{
		border:2px solid black;
		width:70%;
		height:100vh;
		margin:0 auto;
	}
	.categorylist{
		display: flex;
    	width: 80%;
    	margin: 5% auto 3% auto;
    	height: 10%;
    	cursor:pointer;

	}
	.categorydiv{
		border:1px solid black;
        text-align: center;
        width: 70%;
		hight:15%;
     }
     .categorydiv:hover{
     	background-color: #993333;
 		color: white;
     }
     .categorydiv p{
     	
     	line-height:6;
     }
     .searchfilter{
     	background-color:lightgray;
     	height:5%;
     	margin:auto;
     	text-align:center;
     	display:flex;
     }
     .animation{
       	width:150px;
       	height:50%;
       	cursor: pointer;
       	transform:translateY(12px);
      }
        /*1:1 문의하기 hover 했을시*/
     .animation:hover{
       	color :  #993333;
        font-size: large;
        transition : all .35s;
      }
      .sfilterse{
      	flex:auto;
      }
      .selectpart{
      	margin-top: 20px;
      }
      .selectparta{
      	margin-left:20px;
      	font-size:25px;
      	display: inline-block;
      }
      .selectpartb{
      	right: 50%;
    	float: right;
    	margin-top:5px;
    	margin-right:10px;
      }
      .selectpartb a:hover{
       	color :  #993333;
        font-size: large;
        transition : all .35s;
      }
      .auctionList{
      	border:2px solid blue;

      }
      .auctionTot{
      	margin-top:20px;
      }
      .itemLIst{
      	border:2px solid black;
      	display:flex;
      	width:95%;
      	margin:0 auto;	
      	margin-bottom:10px;
      }
      .contentPart{
      	border:2px solid red;
      	margin-left:15px;
      	width: 60%;
      }
      .priceContent{
      	display:flex;
      }
</style>
</head>
<body>
	<%@include file="../common/header.jsp" %>
	<div class="wtotal">
		<div class="categorylist"><!-- 카테고리div 부분 -->
		<%for(Category ca : list){%>
		<div class="categorydiv"><p><%=ca.getName() %></p>
		</div>
		<%} %>
		</div>
		<div class= "searchfilter">
		 	<div  class="sfilterse">검색 필터 부분</div>
		 	<% if(user != null) { %>
		 	<div class= "animation">
    		<a href="<%= request.getContextPath() %>/insertAuction.do"> 게시물 작성 </a>
    		</div>
    		<% } %>
		</div>
		<div class= "selectpart"><!--  최신 카테고리  -->
			<div class="selectparta"><p> 전체 상품 </p></div>
			<div class="selectpartb"><!-- 최신,인기 -->
				<a href="#">최신순</a> | 
				<a href="#">인기순</a> | 
				<a href="#">저가순</a> | 
				<a href="#">고가순</a>
			</div>
		</div>
		<div class="auctionTot"><!-- 게시글  -->
		<%for(Auction al : aulist){ %>
		<div class="itemLIst">
		<div class="auctionList"><!-- 이미지 부분 -->
		<input type="hidden" value="<%=al.getAuctionNo()%>">
		<img src="<%=request.getContextPath() %>/resources/auction_upfiles/<%=al.getTitleImg() %>" width="100" height="100">
		</div>
		<div class="contentPart"><!-- 글부분 -->
			<div> <p><%=al.getSellStatus() %> </p></div>
			<div> <p><%=al.getAuctionTitle() %> </p></div>
			<div class="priceContent"> <p><%=al.getItemDirect() %>원 </p><p>즉시구매가 </p></div>
			<div> <p>경매 마감 시간 : </p></div>
		</div>
		</div>
		<%} %>
		</div>
		<!-- 페이징바 만들기 -->
		<div class="pagingArea" align="center">
		<!-- 맨 처음으로 (<<) -->
		<button onclick="location.href='<%=request.getContextPath()%>/auctionPage.do?currentPage=1'"> &lt;&lt; </button>
	
		<!-- 이전페이지로(<) -->
		<%if(currentPage == 1){ %>
		<button disabled> &lt; </button>
		<%}else{ %>
		<button onclick="location.href='<%= request.getContextPath() %>/auctionPage.do?currentPage=<%= currentPage-1 %>'"> &lt; </button>
		<%} %>
		
		<!-- 페이지 목록 -->
		<%for(int p=startPage; p<=endPage; p++){ %>
			
			<%if(p == currentPage){ %>
			<button disabled> <%= p %> </button>
			<%}else{ %>
			<button onclick="location.href='<%=request.getContextPath() %>/auctionPage.do?currentPage=<%= p %>'"> <%= p %> </button>
			<%} %>
			
		<%} %>
		
		<!-- 다음페이지로(>) -->
		<%if(currentPage == maxPage){ %>
		<button disabled> &gt; </button>
		<%}else { %>
		<button onclick="location.href='<%= request.getContextPath() %>/auctionPage.do?currentPage=<%= currentPage+1 %>'"> &gt; </button>
		<%} %>
	
		<!-- 맨 끝으로 (>>) -->
		<button onclick="location.href='<%=request.getContextPath()%>/auctionPage.do?currentPage=<%=maxPage%>'"> &gt;&gt; </button>
	</div> 
	
	</div>
	
	<%--  <%@ include file = "../common/footer.jsp" %> --%>
	<script>
		$(".categorydiv").click(function(){
			var input = $(this).text();
			console.log(input)
			$(".selectparta").text(input);
		})
		
	</script>
</body>
</html>