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
<link rel="stylesheet" href="../../resources/css/search/search.css">
</head>
<body>
	<%@include file="../common/header.jsp" %>
	<div class="wtotal">
		<div class= "selectpart"><!--  최신 카테고리  -->
			<div class="selectparta">'<span class="searchword"><%= search %></span>' <span>검색 결과</span></div>
			<div class="selectpartb"><!-- 최신,인기 -->
				<a href="<%=request.getContextPath() %>/auctionSearchList.do?search=<%= search %>">최신순</a> | 
				<a href="<%=request.getContextPath() %>/auctionSearchListLikeDesc.do?search=<%= search %>">인기순</a> | 
				<a href="<%=request.getContextPath() %>/auctionSearchListPriceAsc.do?search=<%= search %>">저가순</a> | 
				<a href="<%=request.getContextPath() %>/auctionSearchListPriceDesc.do?search=<%= search %>">고가순</a>
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
	</div>
	<!-- 페이징바 만들기 -->
	<div class="pagingArea" align="center">
		<!-- 맨 처음으로 (<<) -->
		<button onclick="location.href='<%=request.getContextPath()%>/auctionSearchListLikeDesc.do?search=<%= search %>&currentPage=1'"> &lt;&lt; </button>
	
		<!-- 이전페이지로(<) -->
		<%if(currentPage == 1){ %>
		<button disabled> &lt; </button>
		<%}else{ %>
		<button onclick="location.href='<%= request.getContextPath() %>/auctionSearchListLikeDesc.do?search=<%= search %>&currentPage=<%= currentPage-1 %>'"> &lt; </button>
		<%} %>
		
		<!-- 페이지 목록 -->
		<%for(int p = startPage; p <= endPage; p++){ %>
			
			<%if(p == currentPage){ %>
			<button disabled> <%= p %> </button>
			<%}else{ %>
			<button onclick="location.href='<%=request.getContextPath() %>/auctionSearchListLikeDesc.do?search=<%= search %>&currentPage=<%= p %>'"> <%= p %> </button>
			<%} %>
			
		<%} %>
		
		<!-- 다음페이지로(>) -->
		<%if(currentPage == maxPage){ %>
		<button disabled> &gt; </button>
		<%}else { %>
		<button onclick="location.href='<%= request.getContextPath() %>/auctionSearchListLikeDesc.do?search=<%= search %>&currentPage=<%= currentPage+1 %>'"> &gt; </button>
		<%} %>
	
		<!-- 맨 끝으로 (>>) -->
		<button onclick="location.href='<%=request.getContextPath()%>/auctionSearchListLikeDesc.do?search=<%= search %>&currentPage=<%=maxPage%>'"> &gt;&gt; </button>
	</div>
	<%@ include file = "../common/footer.jsp" %> 
	<script>
		$(".itemLIst").click(function(){
			var scno = $(this).children().children().val();
			console.log(scno)
			increaseCount(scno)
			location.href="<%=request.getContextPath()%>/detailAuction.do?scno="+scno;
		})
		function increaseCount(scno){
			$.ajax({
				type:"post",
				url:"increaseAuction.do", 
				data:{
					scno:scno
				}, 
				success: function(){
					console.log("성공")
				}, 
				error:function(e){
					console.log("실패")
				}
				
			})
			
		}
			
		
	</script>
</body>
</html>