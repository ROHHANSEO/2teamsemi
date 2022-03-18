<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.ArrayList, com.uni.usedItemBoard.model.vo.*"%>
<%
	ArrayList<UsedItemsBoard> list = (ArrayList<UsedItemsBoard>)request.getAttribute("list");
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
<title>Insert title here</title>
</head>
<style>
	#top{
		display: flex;
		border: 1px solid black;
		justify-content: center;
	}
	.container{
		display: flex;
		justify-content: center;
	}
	.item{
		border: 1px solid black;
	}
</style>
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
	    <input type="button" value="글 작성하기" onclick="location.href='<%= request.getContextPath() %>/insertUsedBoard.do'">
	    <a href="<%= request.getContextPath() %>/usedListDesc.do">최신순</a> | <a href="<%= request.getContextPath() %>/usedListLike.do">인기순</a> | <a href="<%= request.getContextPath() %>/usedListPriceDesc.do">고가순</a> | <a href="<%= request.getContextPath() %>/usedListPriceAsc.do">저가순</a>
	    <hr>
	    <div class="container">
	    	<div class="item"></div>
	    	<div class="item">
		    	<%if(list.isEmpty()){ %>
					<div>조회된 리스트가 없습니다.</div>
				<%}else{ %>
					<% for(UsedItemsBoard b : list){ %>
						<div><%= b.getUsedBoardNo() %></div>
						<div><%= b.getCategory() %></div>
						<div><%= b.getUsedBoardContent() %></div>
						<div><%= b.getUsedBoardWriter() %></div>
						<div><%= b.getLikeCount() %></div>
						<div><%= b.getCreateDate() %></div>
					<%} %>
				<%} %>
		    </div>
	    	<div class="item"></div>
	    </div>
    </div>
	<!-- 페이징바 -->
	<div class="pagingArea" align="center">
		<!-- 맨 처음으로 (<<) -->
		<button onclick="location.href='<%= request.getContextPath() %>/listBoard.do?currentPage=1'"> &lt;&lt; </button>
	
		<!-- 이전페이지로(<) -->
		<%if(currentPage == 1){ %>
		<button disabled> &lt; </button>
		<%}else{ %>
		<button onclick="location.href='<%= request.getContextPath() %>/listBoard.do?currentPage=<%= currentPage-1 %>'"> &lt; </button>
		<%} %>
		
		<!-- 페이지 목록 -->
		<%for(int p=startPage; p<=endPage; p++){ %>
			<%if(p == currentPage){ %>
			<button disabled> <%= p %> </button>
			<%}else{ %>
			<button onclick="location.href='<%= request.getContextPath() %>/listBoard.do?currentPage=<%= p %>'"> <%= p %> </button>
			<%} %>
			
		<%} %>
		
		<!-- 다음페이지로(>) -->
		<%if(currentPage == maxPage){ %>
		<button disabled> &gt; </button>
		<%}else { %>
		<button onclick="location.href='<%= request.getContextPath() %>/listBoard.do?currentPage=<%= currentPage+1 %>'"> &gt; </button>
		<%} %>
	
		<!-- 맨 끝으로 (>>) -->
		<button onclick="location.href='<%= request.getContextPath() %>/listBoard.do?currentPage=<%=maxPage%>'"> &gt;&gt; </button>
	</div>
    <script src="../../resources/js/usedItemBoard/usedItem.js"></script>
    <div>
    	<%@ include file = "../common/footer.jsp" %>
    </div>
</body>
</html>