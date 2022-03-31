<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.uni.usedItemBoard.model.vo.Category, com.uni.auction.model.vo.*" %>
<%
	   	ArrayList<Category> list = (ArrayList<Category>)request.getAttribute("list");
		ArrayList<Auction> aulist = (ArrayList<Auction>)request.getAttribute("aulist");
		ArrayList<Auction> kklist = (ArrayList<Auction>)request.getAttribute("kklist");
		
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
	.categorylist{
		display: flex;
    	width: 80%;
    	margin: 5% auto 3% auto;
    	height: 10%;
    	cursor:pointer;

	}
	.categorydiv{
        text-align: center;
        width: 70%;
		hight:15%;
		border:1px solid black;
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
     	height:50px;
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
		<div class="categorylist"><!-- 카테고리div 부분 -->
		<%for(Category ca : list){%>
		<div class="categorydiv" ><p class= "aaaaa"><%=ca.getName() %></p>
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
			<%} %>
		<%}else{ %>
			<%for(Auction al : kklist){ %>
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
			<%} %>
		
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
	
	<%@ include file = "../common/footer.jsp" %> 
	<script>
		<%if (aulist != null){%>
		$(".categorydiv").click(function(){
			var input = $(this).text();
			console.log(input)
			$(".selectparta").text(input);
		})
		<%}%>
		$(".itemLIst").click(function(){
			var scno = $(this).children().children().val();
			console.log(scno)
			location.href="<%=request.getContextPath()%>/detailAuction.do?scno="+scno;
		})
		

		//버튼 눌렀을 시에 카테고리별로 리스트 보이게 하기 
		<%--$(".aaaaa").click(function(){
			$(".pagingArea").hide();//페이징바 안보이게 
			$(".auctionTot").empty();
			var li = $(this).text();
			$.ajax({
				url:"auctionPage.do", 
				data:{
					li:li
				}, 
				type:"get", 
				success:function(list){
					console.log(list)
					console.log("카테고리 보내기 성공")
					if(list.length == 0){
						$(".wtotal").css("height", "800");
						alert("게시글이 없습니다!")
					}else if(list.legth != 0){
						var html = '';	
							<%--
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
								</div>list[key]
							</div>
						$.each(list, function(index, obj){
							html += 	'<div class="itemLIst">';
							html +=			'<div class="auctionList">';
							html +=				'<input type="hidden" value="'+obj.auctionNo+'"class="auctionno">';
							html +=				'<img src="'+<%=request.getContextPath() %>/resources/auction_upfiles/obj.titleImg+'" width="200" height="200">';
							html +=			'</div>';
							html +=			'<div class="contentPart">';
							html +=				'<div class="statusauction"> <p>'+obj.sellStatus +'</p></div>';
							html +=				'<div class="titleauction"> <p>'+obj.getAuctionTitle +'</p></div>';
							html +=				'<div class="priceContent"> <span class="a" >'+obj.priceFo+'원 </span><p class="b">즉시구매가 </p></div>';
							html +=				'<div class="endauction"> <p>경매 마감 :'+ obj.getDateget+'</p></div>';
							html +=			'</div>';
							html +=		'</div>';
						}
						
						$(".auctionTot").append(html);
						
						})
					}
				}, 
				error:function(e){
					console.log("카테고리 보내기 실패")
				}
			})
		})--%>
		$(".aaaaa").click(function(){
			var category = $(this).text();
			console.log(category)
			location.href="<%=request.getContextPath()%>/auctionPage.do?category="+category;
		})
			
		
	</script>
</body>
</html>