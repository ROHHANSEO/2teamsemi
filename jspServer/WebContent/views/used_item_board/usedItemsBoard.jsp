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
	    		<% if(user != null){  %>
	    			<input class="commonwritebutton write" type="button" value="글 작성하기" onclick="location.href='<%= request.getContextPath() %>/insertUsedBoard.do'">
	    		<% } %>
	    		<div></div>
	    		<div>
	    			<span href="<%= request.getContextPath() %>/usedListDesc.do">최신순</span> | <span href="<%= request.getContextPath() %>/usedListLike.do">인기순</span> | <span href="<%= request.getContextPath() %>/usedListPriceDesc.do">고가순</span> | <span href="<%= request.getContextPath() %>/usedListPriceAsc.do">저가순</span>
	    		</div>
	    	</div>
	    	<div class="item" id="article2">
			    <%for(UsedItemsBoard ub : list){ %>
					<div class="thumbnail" align="center" onclick="detail()">
						<a href="<%=request.getContextPath()%>/detailview.do?bNo=<%=ub.getUsedBoardNo()%>">
							<img src="<%=request.getContextPath() %>/resources/usedboard_upfiles/<%= ub.getTitleImg() %>" class="image"> <br>
							<div class="bottom">
								<div class="firstLine">
									<span class="title">
										<%=ub.getUsedBoardTitle() %>
									</span>
								 	<span class="like">
								 		<span>
								 			<img class="heart" src="https://img.icons8.com/ios/50/000000/like--v1.png"/>
								 		</span>
								 		<span>
								 			<%=ub.getLikeCount() %>
								 		</span>
								 	</span>
								</div>
								<div class="secondLine">
									<span class="price">
										<%= ub.getPrice() %>
									</span>
									<span class="status">
										<%= ub.getItemCondition() %>
									</span>
								</div>
							</div>
						</a>
					</div>
				<%} %>
				
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
	    <div>
	    	<%@ include file = "../common/footer.jsp" %>
	    </div>
    </div>
    <script src="../../resources/js/usedItemBoard/usedItem.js"></script>
</body>
</html>