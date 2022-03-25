<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.uni.usedItemBoard.model.vo.UsedItemsBoard"%>
<%
    String msg = (String)request.getAttribute("msg");
	ArrayList<UsedItemsBoard> list = (ArrayList<UsedItemsBoard>)request.getAttribute("content");
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
<div class="mypage_list_page">
    <%@include file = "../common/mypageList.jsp" %>
</div>
<div id="salescontent_box">
    <div class="salescontent_title_box">
        <span id="salescontent_title"><%=msg %></span>
    </div>
    <div class="salescontent">
        <div class="button_box">
            <button id="recored_delete" class="commonsubmit" type="button">기록 삭제</button>
        </div>
        <%if(msg.equals("판매기록")){ %>
        	<%for(UsedItemsBoard board : list) {%>
		        <div class="content_box">
		            <div>
		                <div class="date_box">
		                    <span class="date">날짜</span>
		                </div>
		                <div class="del_up_box">
		                    <span class="content_update">수정</span>
		                    <span>/</span>
		                    <span class="content_delete">삭제</span>
		                </div>
		            </div>
		            <div class="main_content_box">
		                <div class="statuss">
		                    <span class="status"><%=board.getItemCondition() %></span>
		                    <span class="status_swap">상태변경</span>
		                    <div class="status_level_box">
		                        <div class="status_level"><span>거래완료</span></div>
		                        <div class="status_level"><span>거래중</span></div>
		                        <div class="status_level"><span>거래취소</span></div>
		                    </div>
		                </div>
		                <div class="content_title">
		                    <div class="img_box">
		                        <img class="content_img" src="<%=request.getContextPath() %>/resources/usedboard_upfiles/<%= board.getTitleImg() %>" alt="대표 이미지">
		                    </div>
		                    <div class="title_name_box">
		                        <span class="content_title_name"><%=board.getUsedBoardTitle() %></span>
		                        <span class="content_price">판매가격 : <span class="price"><%=board.getPrice() %>원</span> </span>
		                    </div>
		                </div>
		            </div>
		        </div>
        	<%} %>
        <%} else if(msg.equals("구매기록")) {%>
        <div class="content_box">
            <div>
                <div class="date_box">
                    <span class="date">날짜</span>
                </div>
                <div class="del_up_box">
                    <span class="content_delete">삭제</span>
                </div>
            </div>
            <div class="main_content_box">
                <div class="statuss">
                    <span class="status">거래중</span>
                </div>
                <div class="content_title">
                    <div class="img_box">
                        <img class="content_img" src="" alt="">
                    </div>
                    <div class="title_name_box">
                        <span class="content_title_name">판매 제품명</span>
                        <span class="content_price">판매가격 : <span class="price">50,000원</span> </span>
                    </div>
                </div>
            </div>
        </div>
        <%} else if(msg.equals("경매정산")) {%>
        <div class="content_box">
            <div>
                <div class="date_box">
                    <span class="date">날짜</span>
                </div>
            </div>
            <div class="main_content_box">
                <div class="statuss">
                    <span class="status">거래중</span>
                </div>
                <div class="content_title">
                    <div class="img_box">
                        <img class="content_img" src="" alt="">
                    </div>
                    <div class="title_name_box">
                        <span class="content_title_name">판매 제품명</span>
                        <span class="content_price">판매가격 : <span class="price">50,000원</span> </span>
                    </div>
                </div>
            </div>
        </div>
        <%} %>
    </div>
</div>
<div style="clear: both;">
<%@ include file = "../common/footer.jsp" %>
</div>
</body>
</html>