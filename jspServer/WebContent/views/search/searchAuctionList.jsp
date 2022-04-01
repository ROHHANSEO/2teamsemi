<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.uni.usedItemBoard.model.vo.Category, com.uni.auction.model.vo.*" %>
<%
		ArrayList<Auction> aulist = (ArrayList<Auction>)request.getAttribute("alist");
		String search = String.valueOf(request.getAttribute("search"));		

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
		height:100%;
		margin:0 auto;
	}
     .categorydiv:hover{
     	background-color: #993333;
 		color: white;
     }
     .categorydiv p{
     	line-height:6;
     }
      .selectpart{
      	margin: 20px 30px 50px 30px;
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
      .auctionTot{
      	margin-top:20px;
      	
      }
      .itemLIst{
      	display:flex;
      	width:90%;
      	margin:0 5% 30px 5%;
      }
      .contentPart{
      	margin-left:15px;
      	width: 60%;
      	margin-top:30px;
      }
      .priceContent{
      	display:flex;
      	margin-bottom:10px;
      }
      .statusauction{
      	margin-bottom:10px;
      	font-size:16px;
      	color:#993333;
      }
      .titleauction{
      	margin-bottom:10px;
      	font-size:19px;
      }
      .endauction{
      	margin-top:15px;
      	font-size:14px;
      }
      .b{
      	margin-left:10px;
      	margin-top:10px;
      	font-size:12px;
      }
      .a{
      	font-size:22px;
      }
      .itemLIst:hover{
      	cursor: pointer;
      	opacity: 0.8;
      	background-color:rgb(236, 236, 236);;
      }
</style>
</head>
<body>
	<%@include file="../common/header.jsp" %>
	<div class="wtotal">
		<div class= "selectpart"><!--  최신 카테고리  -->
			<div class="selectparta"><span><%= search %></span><span>검색 결과</span></div>
			<div class="selectpartb"><!-- 최신,인기 -->
				<a href="#">최신순</a> | 
				<a href="#">인기순</a> | 
				<a href="#">저가순</a> | 
				<a href="#">고가순</a>
			</div>
		</div>
		<div class="auctionTot"><!-- 게시글  -->
		<% if (aulist != null){%>
			<%for(Auction al : aulist){ %>
			<div class="itemLIst">
				<div class="auctionList"><!-- 이미지 부분 -->
					<input type="hidden" value="<%=al.getAuctionNo()%>" class="auctionno">
					<img src="<%=request.getContextPath() %>/resources/auction_upfiles/<%=al.getTitleImg() %>" width="200" height="200">
				</div>
				<div class="contentPart"><!-- 글부분 -->
					<div class="statusauction"> <p><%=al.getSellStatus() %> </p></div>
					<div class="titleauction"> <p><%=al.getAuctionTitle() %> </p></div>
					<div class="priceContent"> <span class="a" ><%=al.getPriceFo() %>원 </span><p class="b">즉시구매가 </p></div>
					<div class="endauction"> <p>경매 마감 : <%=al.getDateget() %></p></div>
				</div>
			</div>
			<%} }%>
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
	
	<%@ include file = "../common/footer.jsp" %> 
	<script>
		$(".itemLIst").click(function(){
			var scno = $(this).children().children().val();
			console.log(scno)
			location.href="<%=request.getContextPath()%>/detailAuction.do?scno="+scno;
		})
			
		
	</script>
</body>
</html>