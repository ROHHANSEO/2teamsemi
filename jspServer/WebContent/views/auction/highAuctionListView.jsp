<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.uni.usedItemBoard.model.vo.Category, com.uni.auction.model.vo.*" %>
<%
	   	ArrayList<Category> list = (ArrayList<Category>)request.getAttribute("list");
		ArrayList<Auction> aulist = (ArrayList<Auction>)request.getAttribute("aulist");
		
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
<link rel="stylesheet" href="../../resources/css/common/common.css">
<style>
	.wtotal{
			width:70%;
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
     }
     .animation{
	       	width:150px;
	       	height:50%;
	       	float: right;
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
	      	background-color:rgb(236, 236, 236);
      }
      .pagingArea{
      		margin-bottom : 70px;
      }
      .pagingArea>button{
	      	background-color : :rgb(236, 236, 236);
	      	width:30px;
	      	height:30px;
	      	border-radius: 5px;
	      	font-size:15px;
	      	border:1px solid lightgray;
      }
      .pagingArea>button:hover:enabled{
	      	border:1px solid black;
	      	background-color: #993333;
	      	color:white;
	      	cursor: pointer;
      }
      .seacrchidichinput{
	       	display: inline-block ;
	       	position:absolute;
	       	left:50%;
	       	transform:translateX(-50%);
	       	line-height:50px;
	       	height:35px;
			width:550px;
       }
     	.optionserachCList{
		  	height:33px;
		   	width:60px;
       	
       }
        .seacrchinput{
        	height:30px;
			width:400px;
        }
        .form-controlsearchbutton{
        	height:33px;
        	width:60px;
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
   			<div class="seacrchidichinput">
   		<!-- 분류 종류 : 제목, 내용 -->
   				<select class="optionserachCList" name="searchClist">
   					<option value="title" selected>제목</option>
   					<option value="content">내용</option>
   				</select>
   				<input class="seacrchinput" placeholder="검색어를 입력하세요." type="text" >
   		<!-- onkeyup:은 텍스트가 입력된 후 이벤트 실행 -->

   				<button class="form-controlsearchbutton" onclick="searchFunction();" type="button">검색</button>
   			</div>
		 	<% if(user != null) { %>
		 	<div class= "animation">
    		<a href="#" onclick="submitV()"> 게시물 작성 </a>
    		</div>
    		<% } %>
		</div>
		<div class= "selectpart"><!--  최신 카테고리  -->
			<div class="selectparta"><p> 고가순 상품 </p></div>
			<div class="selectpartb"><!-- 최신,인기 -->
				<a href="<%=request.getContextPath()%>/auctionPage.do">최신순</a> | 
				<a href="<%=request.getContextPath()%>/popAutcion.do">인기순</a> | 
				<a href="<%=request.getContextPath()%>/lowerAuction.do">저가순</a> | 
				<a href="<%=request.getContextPath()%>/highAuction.do">고가순</a>
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
		<%} %>
		</div>
		</div>
		<!-- 페이징바 만들기 -->
		<div class="pagingArea" align="center">
		<%if(currentPage == 1){ %>
		<button disabled> &lt; </button>
		<%}else{ %>
		<!-- 맨 처음으로 (<<) -->
		<button onclick="location.href='<%=request.getContextPath()%>/highAuction.do?currentPage=1'"> &lt;&lt; </button>
		<%} %>
		
		<!-- 이전페이지로(<) -->
		<%if(currentPage == 1){ %>
		<button disabled> &lt; </button>
		<%}else{ %>
		<button onclick="location.href='<%= request.getContextPath() %>/highAuction.do?currentPage=<%= currentPage-1 %>'"> &lt; </button>
		<%} %>
		
		<!-- 페이지 목록 -->
		<%for(int p=startPage; p<=endPage; p++){ %>
			
			<%if(p == currentPage){ %>
			<button disabled> <%= p %> </button>
			<%}else{ %>
			<button onclick="location.href='<%=request.getContextPath() %>/highAuction.do?currentPage=<%= p %>'"> <%= p %> </button>
			<%} %>
			
		<%} %>
		
		<!-- 다음페이지로(>) -->
		<%if(currentPage == maxPage){ %>
		<button disabled> &gt; </button>
		<%}else { %>
		<button onclick="location.href='<%= request.getContextPath() %>/highAuction.do?currentPage=<%= currentPage+1 %>'"> &gt; </button>
		<%} %>
	
		<!-- 맨 끝으로 (>>) -->
		<%if(currentPage == maxPage){ %>
		<button disabled> &gt; </button>
		<%}else { %>
		<button onclick="location.href='<%=request.getContextPath()%>/highAuction.do?currentPage=<%=maxPage%>'"> &gt;&gt; </button>
		<%} %>
	</div> 
	
	
	
	<%@ include file = "../common/footer.jsp" %> 
	<script>

		$(".itemLIst").click(function(){
			var scno = $(this).children().children().val();
			console.log(scno)
			increaseCount(scno);
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
		//글작성 버튼을 눌렀을때 
		function submitV(){
			var ll = confirm("글작성을 하시겠습니까?");
			if(ll){
				location.href="<%= request.getContextPath() %>/insertAuction.do";
			}else{
				return;
			}
		}
		
		$(".aaaaa").click(function(){
			var category = $(this).text();
			console.log(category)
			location.href="<%=request.getContextPath()%>/auctionPage.do?category="+category;
		})
			
		
	</script>
</body>
</html>