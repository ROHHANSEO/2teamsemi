<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.ArrayList, com.uni.usedItemBoard.model.vo.*, com.uni.event.model.vo.*, com.uni.auction.model.vo.*"%>
<%
	String search = String.valueOf(request.getAttribute("search"));
	ArrayList<Event> eList = (ArrayList<Event>)request.getAttribute("eList");
	ArrayList<UsedItemsBoard> ubList = (ArrayList<UsedItemsBoard>)request.getAttribute("ubList");
	ArrayList<Auction> acList = (ArrayList<Auction>)request.getAttribute("acList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#container{
    margin: 0 auto;
    width: 80vw;	
    margin-bottom: 20px;
}
.article {
    margin-top: 20px;
}	
.firstLine, .secondLine{
	display: flex;
	justify-content: space-between;
}
.firstLine{
    margin-bottom: 19px;
}
.like{
	width: 35px;
    display: flex;
    justify-content: space-around;
}
.image{
    width: 10vw;
    height: 10.5vw;
}
.thumbnail{
    margin: 10px;
    margin-left: 0px;
    margin-right: 17.1px;
    border: 1px solid #d3d3d3;
   	width: 10vw;
    height: 14vw;
}
.heart{
	width: 16px;
}
.write{
	padding: 3px;
}
.status{
    color: #eec71a;
    font-weight: bolder;
}
.title, .price, .status{
	font-size: 14px;
}
.notice-event, .auction, .usedItems	{
	display: flex;
    justify-content: space-between;
    width: 60vw;
    margin: 0 auto;
    margin-bottom: 3px;
    border-bottom: 1px solid #d3d3d3
}
.searchresult{
    width: 20vw;
    margin-left: 10vw;
    margin-top: 25px;
    margin-bottom: 25px;
}
.dontResult{
	width: 60vw;
    margin: 0 auto;
	margin-top: 15px;
    margin-bottom: 15px;
}
.item1, .item2, .item3 {
    width: 60vw;
    margin: 0 auto;
    margin-bottom: 20px;
}
</style>
</head>
<body>
	<%@include file="../common/header.jsp"%>
	<div id="container">
		<div class="searchresult"><span style="color: #993333; font-weight: 600;"><%= search %> </span><span>검색 결과</span></div>
		<div class="item1">
			<div class="notice-event">
				<span>공지사항 게시판</span>
				<% if(!eList.isEmpty()){%>
					<a href="<%=request.getContextPath() %>/noticeSearchList.do?search=<%= search %>"><span>더보기</span></a>
				<% }else{ %>
				<span style="display: none;">더보기</span>
				<% } %>
			</div>
			<div class="article">
			<% if(!eList.isEmpty()){
			for(int i = 0 ; i < eList.size() ; i++){ %>
				<a href="<%=request.getContextPath() %>/detailEvent.do?nno=<%=eList.get(i).getNoticeno()%>">
					<div><%= eList.get(i).getNoticeTitle() %></div>
					<div><%= eList.get(i).getNoticeContent() %></div>
				</a>
			<% }}else{ %>
				<div class="dontResult">
					<span style="color: #993333; font-weight: 600;"><%= search %> </span>(이)가 포함된 게시글이 없습니다
				</div>
			<% } %>
			</div>
		</div>
		<div class="item2">
			<div class="auction">
				<span>경매 게시판</span>
				<% if(!acList.isEmpty()){%>
					<a href="<%=request.getContextPath() %>/auctionSearchList.do?search=<%= search %>"><span>더보기</span></a>
				<% }else{ %>
					<span style="display: none;">더보기</span>
				<% } %>
			</div>
			<div class="article">
			 <%for(Auction ab : acList){ %>
				<div class="thumbnail" align="center">
					<a href="<%=request.getContextPath()%>/detailAuction.do?scno=<%=ab.getAuctionNo()%>">
						<img src="<%=request.getContextPath() %>/resources/auction_upfiles/<%= ab.getTitleImg() %>" class="image"> <br>
						<div class="bottom">
							<div class="firstLine">
								<span class="title">
									<%=ab.getAuctionTitle() %>
								</span>
							</div>
							<div class="secondLine">
								<span class="price">
									<%= ab.getPriceFo() %>원
								</span>
								<span class="status">
									<%= ab.getSellStatus() %>
								</span>
							</div>
						</div>
					</a>
				</div>
			<%}if(acList.isEmpty()){ %>
				<div class="dontResult">
					<span style="color: #993333; font-weight: 600;"><%= search %> </span>(이)가 포함된 게시글이 없습니다
				</div>
			<% } %>
			</div>
		</div>
		<div class="item3">
			<div class="usedItems">
				<span>중고판매 게시판</span>
				<% if(!acList.isEmpty()){%>
					<a href="<%=request.getContextPath() %>/usedItemsSearchList.do?search=<%= search %>"><span>더보기</span></a>
				<% }else{ %>
					<span style="display: none;">더보기</span>
				<% } %>
			</div>
			<div class="article">
				 <%for(UsedItemsBoard ub : ubList){ %>
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
				<%}if(acList.isEmpty()){ %>
					<div class="dontResult">
						<span style="color: #993333; font-weight: 600;"><%= search %> </span>(이)가 포함된 게시글이 없습니다
					</div>
				<% } %>	
			</div>
		</div>
	</div>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>