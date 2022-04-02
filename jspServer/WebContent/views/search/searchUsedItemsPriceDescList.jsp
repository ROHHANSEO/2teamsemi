<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.ArrayList, com.uni.usedItemBoard.model.vo.*"%>
<%
	ArrayList<UsedItemsBoard> list = (ArrayList<UsedItemsBoard>)request.getAttribute("ubList");
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	String search = String.valueOf(request.getAttribute("search"));
	
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
<title>Insert title here</title>
<link rel="stylesheet" href="../../resources/css/usedItemsBoard/usedItemBoardList.css">
</head>
<body>
	<div>
    	<%@include file = "../common/header.jsp" %>
    </div>
    <br>
    <div id="top">
    	<%@ include file="../common/filter.jsp" %>
    </div>
    <br>
    <div>
	    <div class="container">
	    	<div class="item" id="article1">
	    		<div class= "selectpart"><!--  최신 카테고리  -->
					<div class="selectpartb selectpartline"><!-- 최신,인기 -->
						<a href="<%=request.getContextPath()%>/usedItemsSearchList.do?search=<%= search %>"> 최신순 </a> | 
						<a href="<%= request.getContextPath() %>/usedItemsLikeDescSearchListLike.do?search=<%= search %>"> 인기순 </a> | 
						<a href="<%= request.getContextPath() %>/usedItemsPriceAscSearchList.do?search=<%= search %>"> 저가순 </a> | 
						<a href="<%= request.getContextPath() %>/usedItemsPriceDescSearchList.do?search=<%= search %>"> 고가순 </a>
					</div>
				</div>
	    	</div>
	    	<div class="item" id="article2">
			    <%for(UsedItemsBoard ub : list){ %>
					<div class="thumbnail" align="center">
						<a href="<%=request.getContextPath()%>/detailview.do?bNo=<%=ub.getUsedBoardNo()%>">
							<img src="<%=request.getContextPath() %>/resources/usedboard_upfiles/<%= ub.getTitleImg() %>" class="image"> <br>
							<div class="bottom">
								<div class="firstLine">
									<span class="title">
										<%=ub.getUsedBoardTitle() %>
									</span>
								 	<span class="like">
								 		<span>
								 			<img class="heart" src="data:image/png;base64,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">
								 		</span>
								 		<span>
								 			<%=ub.getLikeCount() %>
								 		</span>
								 	</span>
								</div>
								<div class="secondLine">
									<span class="price">
										<%= ub.getComprice() %>원
									</span>
									<span class="status">
										<%= ub.getSaleStatus() %>
									</span>
								</div>
							</div>
						</a>
					</div>
				<%} %>
		    </div>
   		    <!-- 페이징바 -->
			<div class="pagingArea" align="center">
				<!-- 맨 처음으로 (<<) -->
				<button onclick="location.href='<%= request.getContextPath() %>/usedItemsPriceDescSearchList.do?currentPage=1'"> &lt;&lt; </button>
			
				<!-- 이전페이지로(<) -->
				<%if(currentPage == 1){ %>
				<button disabled> &lt; </button>
				<%}else{ %>
				<button onclick="location.href='<%= request.getContextPath() %>/usedItemsPriceDescSearchList.do?currentPage=<%= currentPage-1 %>'"> &lt; </button>
				<%} %>
				
				<!-- 페이지 목록 -->
				<%for(int p=startPage; p<=endPage; p++){ %>
					<%if(p == currentPage){ %>
					<button disabled> <%= p %> </button>
					<%}else{ %>
					<button onclick="location.href='<%= request.getContextPath() %>/usedItemsPriceDescSearchList.do?currentPage=<%= p %>'"> <%= p %> </button>
					<%} %>
					
				<%} %>
				
				<!-- 다음페이지로(>) -->
				<%if(currentPage == maxPage){ %>
				<button disabled> &gt; </button>
				<%}else { %>
				<button onclick="location.href='<%= request.getContextPath() %>/usedItemsPriceDescSearchList.do?currentPage=<%= currentPage+1 %>'"> &gt; </button>
				<%} %>
			
				<!-- 맨 끝으로 (>>) -->
				<button onclick="location.href='<%= request.getContextPath() %>/usedItemsPriceDescSearchList.do?currentPage=<%=maxPage%>'"> &gt;&gt; </button>
			</div>
	    </div>
	    <div>
	    	<%@ include file = "../common/footer.jsp" %>
	    </div>
    </div>
</body>
</html>