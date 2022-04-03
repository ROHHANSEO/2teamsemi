<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.ArrayList, com.uni.auction.model.vo.Auction, com.uni.usedItemBoard.model.vo.UsedItemsBoard, com.uni.usedItemBoard.model.service.UsedItemsBoardService, com.uni.auction.model.service.AuctionService "%>
<%
	ArrayList<UsedItemsBoard> usedList = new UsedItemsBoardService().usedLikeList();
	ArrayList<Auction> auctionList = new AuctionService().actionLikeList();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>새지마 마켓</title>
    <link rel="shortcut icon" href="./resources/images/favicon.ico">
    <link rel="stylesheet" href="../../resources/css/common/common.css">
    <link rel="stylesheet" href="../../resources/library/swiper.min.css">
    <link rel="stylesheet" href="../../resources/library/jquery.fullpage.css">
</head>
<body>
    <%@include file = "views/common/header.jsp" %>
    <div class="main_img_box"></div>
    <div id="fullpage">
        <section class="section section00 swiper-container swiper1" data-anchor="main01">
            <div class="main01_content">
                <div class="event_img_box swiper-container swiper">
                    <div class="swiper-wrapper slide_index">
                        <div class="swiper-slide slide1 page1"></div>
                        <div class="swiper-slide slide2 page2"></div>
                        <div class="swiper-slide slide3 page3"></div>
                        <div class="swiper-slide slide4 page4"></div>
                        <div class="swiper-slide slide5 page4"></div>
                        <div class="swiper-slide slide6 page4"></div>
                    </div>
                </div>
                <div class="notice_box">
                    <span class="notice_text">공지사항</span>
                    <table>
                        <tr>
                            <td class="create_date">2002-03-03</td>
                            <td class="notice_title">공지사항 제목</td>
                        </tr>
                        <tr>
                            <td class="create_date">2002-03-03</td>
                            <td class="notice_title">공지사항 제목</td>
                        </tr>
                        <tr>
                            <td class="create_date">2002-03-03</td>
                            <td class="notice_title">공지사항 제목</td>
                        </tr>
                        <tr>
                            <td class="create_date">2002-03-03</td>
                            <td class="notice_title">공지사항 제목</td>
                        </tr>
                    </table>
                </div>
            </div>
        </section>
        <section class="section section01" data-anchor="main02">
            <div class="main02_content">
                <div class="title_box">
                    <span class="section_title">중고물품 인기매물</span>
                </div>
                <div class="usedboard_content_box">
                    <%if(usedList.isEmpty()) {%>
                        <div>
                            <span>게시물이 없습니다.</span>
                        </div>
                        <%} else { %>
                            <%for(UsedItemsBoard board : usedList){ %>
                        <div class="userdboard_content">
	                        <div class="used_title_img">
	                        	<input class="boardNo" type="hidden" value="<%=board.getUsedBoardNo()%>">
	                            <img src="<%=request.getContextPath() %>/resources/usedboard_upfiles/<%=board.getTitleImg() %>" alt="타이틀 이미지">
	                            <p class="title_name"><%=board.getUsedBoardTitle() %></p>
	                            <p>가격 : <span class="price"><%=board.getComprice() %></span></p>
	                        </div>
                        </div>
                        <%} %>
                    <%} %>
                </div>
            </div>
        </section>
        <section class="section section02" data-anchor="main03">
            <div class="main03_content">
                <div class="title_box">
                    <span class="section_title">경매물품 인기매물</span>
                </div>
                <div class="action_content_box">
                    <%if(auctionList.isEmpty()) {%>
                    	<div>
                            <span>게시물이 없습니다.</span>
                    	</div>
                        <%} else { %>
                            <%for(Auction board : auctionList){ %>
                        <div class="action_content">
	                        <div class="used_title_img">
	                        	<input class="boardNo" type="hidden" value="<%=board.getAuctionNo()%>">
	                            <img src="<%=request.getContextPath() %>/resources/auction_upfiles/<%= board.getTitleImg() %>" alt="타이틀 이미지">
	                            <p class="title_name"><%=board.getAuctionTitle() %></p>
	                            <p>가격 : <span class="price"><%=board.getPriceFo() %></span></p>
	                        </div>
                        </div>
                        <%} %>
                    <%} %>
                </div>
            </div>
        </section>
        <section class="section section03 fp-auto-height fp-viewing" data-anchor="main04">
            <%@include file = "views/common/footer.jsp" %>
        </section>
    </div>
    <script src="../../resources/js/common/common.js"></script>
    <script src="../../resources/library/jquery.fullpage.js"></script>
    <script src="../../resources/library/swiper.min.js"></script>
    <script>
        var swiper1 = new Swiper('.swiper', {
            autoplay: {
            delay: 3000,
            },
            loop : true,
            speed:2000,
            navigation: {
                prevEl: '.prev',
                nextEl: '.next',
            },
        });

        $(".userdboard_content").click(function(){
            var boardNo = $(this).children().children().eq(0).val();
            console.log(boardNo);

            location.href="<%=request.getContextPath()%>/detailview.do?bNo="+boardNo;
        })

        $(".action_content").click(function(){
            var boardNo = $(this).children().children().eq(0).val();
            console.log(boardNo);

            location.href="<%=request.getContextPath()%>/detailAuction.do?scno="+boardNo;
        })
    </script>
</body>
</html>