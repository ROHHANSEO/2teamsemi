<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.uni.usedItemBoard.model.vo.UsedItemsBoard, com.uni.auction.model.vo.Auction"%>
<%
    String msg = (String)request.getAttribute("msg");
	String msg2 = (String)request.getAttribute("msg2");
	ArrayList<UsedItemsBoard> content = (ArrayList<UsedItemsBoard>)request.getAttribute("content");
	ArrayList<UsedItemsBoard> payment = (ArrayList<UsedItemsBoard>)request.getAttribute("payment");
	ArrayList<UsedItemsBoard> likeProduct = (ArrayList<UsedItemsBoard>)request.getAttribute("likeProduct");
	ArrayList<Auction> mybid = (ArrayList<Auction>)request.getAttribute("mybid");
	ArrayList<Auction> mypost = (ArrayList<Auction>)request.getAttribute("mypost");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=msg %></title>
<link rel="stylesheet" href="../../resources/css/common/common.css">
<link rel="stylesheet" href="../../resources/css/mypage/myPageList.css">
<link rel="stylesheet" href="../../resources/css/mypage/salesRecordPage.css">
</head>
<body>
<%@include file = "../common/header.jsp" %>
<%@include file = "../common/editModal1.jsp" %>
<%@include file = "../common/editModal2.jsp" %>
<%@include file = "../common/editModalSuccess.jsp" %>
<%@include file = "../common/secessionModal1.jsp" %>
<%@include file = "../common/secessionModal2.jsp" %>
<%@include file = "../common/secessionModalSuccess.jsp" %>
<%@include file = "../common/adminCode.jsp" %>
<%@include file = "../common/adminCode2.jsp" %>
<div class="mypage_list_page">
    <%@include file = "../common/mypageList.jsp" %>
</div>
<div id="salescontent_box">
    <div class="salescontent_title_box">
        <span id="salescontent_title"><%=msg %></span>
    </div>
    <div class="salescontent">
        <div class="button_box">
            <button id="record_delete" class="commonsubmit" type="button">기록 삭제</button>
            <div id="delete_btn_box">
                <button id="delete_cancel" class="commonsubmit" type="button">취소</button>
                <button id="record_select_delete" class="commonsubmit" type="button">선택 삭제</button>
                <button id="record_All_delete" class="commonsubmit" type="button">전체 삭제</button>
				<%if(msg.equals("결제기록")&&msg.equals("판매기록")){%>
				<input type="hidden" value="1">
				<%}%>
				<%if(msg.equals("경매정산")){%>
				<input type="hidden" value="2">
				<%}%>
            </div>
        </div>
         <% if(msg2.equals("void")) {%>
        	<%if(msg.equals("판매기록")) {%>
	        	<div class="void_text_box">
	        		<span class="void_view"><%=msg %>이 없습니다.</span>
	        	</div>
        	<%} %>
        	
        	<%if(msg.equals("결제기록")) {%>
	        	<div class="void_text_box">
	        		<span class="void_view"><%=msg %>이 없습니다.</span>
	        	</div>
        	<%} %>
        	
        	<%if(msg.equals("경매정산")) {%>
	        	<div class="void_text_box">
	        		<span class="void_view"><%=msg %>이 없습니다.</span>
	        	</div>
        	<%} %>
        	
        	<%if(msg.equals("찜 리스트")) {%>
	        	<div class="void_text_box">
	        		<span class="void_view"><%=msg %>가 없습니다.</span>
	        	</div>
        	<%} %>
        	
        <%} else { %>
	        <%if(msg.equals("판매기록")){ %>
	        	<%for(UsedItemsBoard board : content) {%>
			        <div class="content_box">
	                    <input class="bno" type="hidden" value="<%=board.getUsedBoardNo()%>">
			            <div class="main_box">
	                        <input class="select_delete_checkbox" type="checkbox" value="<%=board.getUsedBoardNo()%>">
			                <div class="date_box">
			                    <span class="date"><%=board.getCreateDate() %></span>
			                </div>
			                <div class="del_up_box">
			                    <span class="content_update">수정</span>
			                    <span>/</span>
			                    <span class="content_delete">삭제</span>
			                </div>
			            </div>
			            <div class="main_content_box">
			                <div class="statuss">
			                    <span class="status status<%=board.getUsedBoardNo()%>"><%=board.getSaleStatus() %></span>
			                    <span class="status_swap">상태변경</span>
			                    <div class="status_level_box">
			                        <div class="status_level"><span>거래완료</span></div>
			                        <div class="status_level"><span>거래중</span></div>
			                        <div class="status_level"><span>판매중</span></div>
			                    </div>
			                </div>
			                <div class="content_title">
			                    <div class="img_box">
	                                <a href="<%=request.getContextPath()%>/detailview.do?bNo=<%=board.getUsedBoardNo()%>">
	                                	<img class="content_img" src="<%=request.getContextPath() %>/resources/usedboard_upfiles/<%= board.getTitleImg() %>" alt="대표 이미지">
	                                </a>
			                    </div>
			                    <div class="title_name_box">
			                        <span class="content_title_name"><a href="<%=request.getContextPath()%>/detailview.do?bNo=<%=board.getUsedBoardNo()%>"><%=board.getUsedBoardTitle() %></a></span>
			                        <span class="content_price">판매가격 : <span class="price"><%=board.getComprice() %>원</span> </span>
			                    </div>
			                </div>
			            </div>
			        </div>
	        	<%} %>
	        <%} else if(msg.equals("결제기록")) {%>
	        <%for(UsedItemsBoard pay : payment) {%>
			       		        <div class="content_box">
	                    <input class="bno" type="hidden" value="<%=pay.getUsedBoardNo()%>">
			            <div class="main_box">
	                        <input class="select_delete_checkbox" type="checkbox" value="<%=pay.getUsedBoardNo()%>">
			                <div class="date_box">
			                    <span class="date"><%=pay.getCreateDate() %></span>
			                </div>
			                <div class="del_up_box">
			                    <span class="content_delete">삭제</span>
			                </div>
			            </div>
			            <div class="main_content_box">
			                <div class="statuss">
			                    <span class="status status<%=pay.getUsedBoardNo()%>"><%=pay.getSaleStatus() %></span>
			                </div>
			                <div class="content_title">
			                    <div class="img_box">
	                                <a href="<%=request.getContextPath()%>/detailview.do?bNo=<%=pay.getUsedBoardNo()%>">
	                                	<img class="content_img" src="<%=request.getContextPath() %>/resources/usedboard_upfiles/<%= pay.getTitleImg() %>" alt="대표 이미지">
	                                </a>
			                    </div>
			                    <div class="title_name_box">
			                        <span class="content_title_name"><a href="<%=request.getContextPath()%>/detailview.do?bNo=<%=pay.getUsedBoardNo()%>"><%=pay.getUsedBoardTitle() %></a></span>
			                        <span class="content_price">판매가격 : <span class="price"><%=pay.getComprice() %>원</span> </span>
			                    </div>
			                </div>
			            </div>
			        </div>
	        	<%} %>
	        <%} else if(msg.equals("경매정산")) {%>
	        	<span class="my_bid_record">입찰 참여한 게시물 목록</span>
					<%for(Auction bid : mybid) {%>
						<div class="content_box bid_content_box">
							<input class="select_delete_checkbox" type="checkbox" value="<%=bid.getAuctionNo()%>">
							<div class="main_box">
								<div class="date_box">
									<span class="date"><%=bid.getCreateDate() %></span>
								</div>
							</div>
							<div class="main_content_box">
								<div class="statuss">
									<span class="status"><%=bid.getStatus() %></span>
								</div>
								<div class="content_title">
									<div class="img_box">
										<img class="content_img" src="<%=request.getContextPath() %>/resources/auction_upfiles/<%= bid.getTitleImg() %>" alt="대표이미지">
									</div>
									<div class="title_name_box">
										<span class="content_title_name"><%=bid.getAuctionTitle() %></span>
										<span class="content_price">즉시판매가격 : <span class="price"><%=bid.getItemDirect() %></span> </span>
										<span class="content_price bid_content_price">내가 입찰한 최고가격 : <span class="price"><%=bid.getMybid() %></span></span>
										<span class="action_deadline">경매 마감 시간 : <span><%=bid.getDatenext() %></span></span>
									</div>
								</div>
							</div>
						</div>
					<%} %>
	        	<span class="my_action_post">내가 게시한 게시물 목록</span>
					<%for(Auction bid : mypost) {%>
						<div class="content_box bid_content_box">
							<input class="select_delete_checkbox" type="checkbox" value="<%=bid.getAuctionNo()%>">
							<div class="main_box">
								<div class="date_box">
									<span class="date"><%=bid.getCreateDate() %></span>
								</div>
								<div class="del_up_box">
									<span class="content_update">수정</span>
									<span>/</span>
									<span class="content_delete">삭제</span>
								</div>
							</div>
							<div class="main_content_box">
								<div class="statuss">
									<span class="status"><%=bid.getStatus() %></span>
								</div>
								<div class="content_title">
									<div class="img_box">
										<img class="content_img" src="<%=request.getContextPath() %>/resources/auction_upfiles/<%= bid.getTitleImg() %>" alt="대표이미지">
									</div>
									<div class="title_name_box">
										<span class="content_title_name"><%=bid.getAuctionTitle() %></span>
										<span class="content_price">즉시판매가격 : <span class="price"><%=bid.getItemDirect() %></span> </span>
										<span class="content_price bid_content_price">내가 입찰한 최고가격 : <span class="price"><%=bid.getMybid() %></span></span>
										<span class="action_deadline">경매 마감 시간 : <span><%=bid.getDatenext() %></span></span>
									</div>
								</div>
							</div>
						</div>
					<%} %>
	        <%} else if(msg.equals("찜 리스트")) {%>
	        	<%for(UsedItemsBoard like : likeProduct) {%>
		        <div class="like_content_box">
		            <div class="main_content_box">
		                <div class="statuss">
		                    <span class="status"><%=like.getSaleStatus() %></span>
		                </div>
		                <div class="content_title">
		                    <div class="img_box">
		                    	<a href="<%=request.getContextPath()%>/detailview.do?bNo=<%=like.getUsedBoardNo()%>">
		                        	<img class="content_img" src="<%=request.getContextPath() %>/resources/usedboard_upfiles/<%= like.getTitleImg() %>" alt="대표이미지">
		                        </a>
		                    </div>
		                    <div class="title_name_box">
		                        <span class="content_title_name"><a href="<%=request.getContextPath()%>/detailview.do?bNo=<%=like.getUsedBoardNo()%>"><%=like.getUsedBoardTitle() %></a></span>
		                        <span class="content_price">판매가격 : <span class="price"><%=like.getComprice() %>원</span> </span>
		                    </div>
		                    <div class="heart_img_box colorchange">
								<input class="bNo" type="hidden" value="<%=like.getUsedBoardNo()%>">
								<input class="uNo" type="hidden" value="<%=like.getUsedBoardWriter()%>">
		                    </div>
		                </div>
		            </div>
		        </div>
		       	<%} %>
        <%} %>
	<%} %>
    </div>
</div>
<div style="clear: both;">
<%@ include file = "../common/footer.jsp" %>
</div>

<script src="../../resources/js/mypage/mypage.js"></script>
</body>
</html>