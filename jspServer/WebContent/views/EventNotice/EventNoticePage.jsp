<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.uni.event.model.vo.*"%>
<%
	ArrayList<Event> list = (ArrayList<Event>) request.getAttribute("list");
	
	PageInfo pi = (PageInfo)request.getAttribute("pi");

	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();

	
	String pt = request.getParameter("pt");
	if (pt == null) pt = "now";
	
	
	
	String style = "background: #993333; color : white;";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" media="screen" href="../../resources/css/Event/list.css">
<link rel="stylesheet" type="text/css" media="screen" href="../../resources/css/common/common.css">
<title>이벤트&공지사항</title>

</head>
	
<div>
    	<%@ include file = "../common/header.jsp" %>
    </div>
<body>
	<div class="outer">
		<br>
		<h2 align="center" >이벤트&공지사항</h2>
		<br>
		
		<h4>이벤트&공지사항 리스트</h4>
		<select name="menulist" onchange="handleOnChange(this)" align="">
	  		<option>전체보기</option>
	  		<option>공지사항</option>
	  		<option>이벤트</option>
		</select>
		<div id='result'>
		
		<br>
		
		<script type="text/javascript">
		</script>
		<a  href="<%=request.getContextPath() %>/eventpage.do?pt=all"><button style='<%= (pt.equals("all"))? style : " " %>' class="commonwritebutton bts3" >전체</button></a>
		<a href="<%=request.getContextPath() %>/eventpage.do?pt=now"><button style='<%= (pt.equals("now"))? style : " " %>' class="commonwritebutton bts3" >현재</button></a>
		<a href="<%=request.getContextPath() %>/eventpage.do?pt=old"><button style='<%= (pt.equals("old"))? style : " " %>' class="commonwritebutton bts3" >지난</button></a>
		
		</div>
		     
		<table class="listArea" align="center">
		 
			<thead>
				<tr class="menubar">
					<th>no.</th>
					<th width="500">글제목</th>
					<th width="100">작성자</th>
					<th width="100">카테고리</th>
					<th width="100">조회수</th>
					<th width="100">작성일</th>
				</tr>
			</thead>
			<tbody>
				

				<% if(list.isEmpty()){ %>
				 	<tr>
						<td colspan="5">존재하는 공지사항이 없습니다.</td>
					</tr>
				 <% }else{  %>
				 	<% for(Event e : list){ %>
				 		<tr>
				 			<td><%= e.getNoticeno() %></td>
				 			<td><%= e.getNoticeTitle() %></td>
				 			<td><%= e.getUserid() %></td>
							<td><%= e.getCategory() %></td>
							<td><%= e.getCategory() %></td>
							<td><%= e.getCreateDate() %></td>
				 		</tr>
				 	<% } %>
				 <% } %>
			</tbody>
			
		</table>
		
	<br>
	<div class="container" align="center">
		<div class="row">
			<form method="post" name="search" action="searchEvent.jsp">
				<table class="pull-right">
					<tr>
						<td><select class="form-control" name="searchField">
								<option value="0">선택</option>
								<option value="bbsTitle">제목</option>
								<option value="userID">작성자</option>
						</select></td>
						<td><input type="text" class="form-control"
							placeholder="검색어 입력" name="searchText" maxlength="100"></td>
						<td><button type="submit" class="commonwritebutton bts2" >검색</button></td>
					</tr>
					
				</table>
			</form>
		</div>
	</div>
	
	<!-- 페이징바 만들기 -->
		<div class="pagingArea" align="center">
			<!-- 맨 처음으로 (<<) -->
			<button class="commonwritebutton god" onclick="location.href='<%=request.getContextPath()%>/eventpage.do?currentPage=1'"> &lt;&lt; </button>
		
			<!-- 이전페이지로(<) -->
			<%if(currentPage == 1){ %>
			<button disabled class="commonwritebutton god"> &lt; </button>
			<%}else{ %>
			<button class="commonwritebutton god"  onclick="location.href='<%= request.getContextPath() %>/eventpage.do?currentPage=<%= currentPage-1 %>'"> &lt; </button>
			<%} %>
			
			<!-- 페이지 목록 -->
			<%for(int p=startPage; p<=endPage; p++){ %>
				
				<%if(p == currentPage){ %>
				<button disabled class="commonwritebutton god"> <%= p %> </button>
				<%}else{ %>
				<button class="commonwritebutton god" onclick="location.href='<%=request.getContextPath() %>/eventpage.do?currentPage=<%= p %>'"> <%= p %> </button>
				<%} %>
				
			<%} %>
			
			
			<%if(currentPage == maxPage){ %>
			<button disabled class="commonwritebutton god" > &gt; </button>
			<%}else { %>
			<button class="commonwritebutton god" onclick="location.href='<%= request.getContextPath() %>/eventpage.do?currentPage=<%= currentPage+1 %>'"> &gt; </button>
			<%} %>
		
			
			<button class="commonwritebutton god" onclick="location.href='<%=request.getContextPath()%> /eventpage.do?currentPage=<%=maxPage%>'"> &gt;&gt; </button>
		</div> 
		<br><br>
	
	<div class="gotoenrollFormServiceCenter">
    	<% if(user != null && user.getUserId().equals("admin")) { %>    	
		<input type="button" class="commonwritebutton bts"   id="inse" value="글작성" onclick="location.href='<%= request.getContextPath() %>/insertEventPage.do'">	
	<% } %>
		</div>
			
		
		
		
		<br><br>
		
		
		</div>
		<script type="text/javascript">
			<%if(!list.isEmpty()){%>
			$(function(){
				$(".listArea>tbody>tr").click(function(){
					var nno = $(this).children().eq(0).text();
					
					location.href="<%=request.getContextPath()%>/detailEvent.do?nno="+nno;
				})
			})
		<%}%>
		</script>
		
		<br><br>
	
<!--  	
	<div id="page">
		
		
		<section class="main">
		
			<aside>
				<div class="content tre">
					<h3><a href="#">미지정</a></h3>
					<p>아직 어떤거를 할지 정하지 않았음</p>
				</div>
			</aside>
			
			<aside>
				<div class="content fit">
					<h3><a href="#">미지정</a></h3>
					<p>아직 어떤거를 할지 정하지 않았음</p>
				</div>
			</aside>
			
			<aside>
				<div class="content tool">
					<h3><a href="#">미지정</a></h3>
					<p>아직 어떤거를 할지 정하지 않았음</p>
				</div>
			</aside>
		</section>
	
			
		<section class="atmosphere">
			<article>
				<h2>새지마 마켓에 대하여</h2>
				<p>안녕하세요 만나서 반값습니다. 이사이트에 대해서 설명하자면 중고거래 사이트이며 실시간 경매, 실시간 물건의 시세를 확인 할 수 있는 편리한 사이트.......</p>
				<a class="btn" href="#pop1">더 보기</a>
				<div class="popup" id="pop1">
					<a href="#a">닫기</a>
					<h3>새지마 마켓에 오신것을 환영합니다.</h3>
					<h5>저희는 물건을 싸게 구매할 수 있는 사이트입니다. 당근마켓, 번개장터등 여러 다양한 사이트가 있지만 비교 되게 너~~~~무 좋습니다.
					저희는 물건을 싸게 구매할 수 있는 사이트입니다. 당근마켓, 번개장터등 여러 다양한 사이트가 있지만 비교 되게 너~~~~무 좋습니다.
					저희는 물건을 싸게 구매할 수 있는 사이트입니다. 당근마켓, 번개장터등 여러 다양한 사이트가 있지만 비교 되게 너~~~~무 좋습니다.
					저희는 물건을 싸게 구매할 수 있는 사이트입니다. 당근마켓, 번개장터등 여러 다양한 사이트가 있지만 비교 되게 너~~~~무 좋습니다.
					저희는 물건을 싸게 구매할 수 있는 사이트입니다. 당근마켓, 번개장터등 여러 다양한 사이트가 있지만 비교 되게 너~~~~무 좋습니다.</h5>
				</div>
				<div class="dim"></div>
			</article>
			</section>
		
		<section class="how-to">
			<aside>
				<div class="content">
					<img alt="choose me please" src="../../resources/images/12.png">
					<h4>choose me please</h4>
					<p>안녕하세요 만나서 반값습니다. 이사이트에 대해서 설명하자면 중고거래 사이트이며 실시간 경매, 실시간 물건의 시세를 확인 할 수 있는 편리한 사이트
					입니다.</p>
					<a class="btn" title="choose me please" href="https://m.bunjang.co.kr/">더 보기</a>
				</div>
			</aside>
			<aside>
				<div class="content">
					<img alt="choose me please" src="../../resources/images/12.png">
					<h4>choose me please</h4>
					<p>안녕하세요 만나서 반값습니다. 이사이트에 대해서 설명하자면 중고거래 사이트이며 실시간 경매, 실시간 물건의 시세를 확인 할 수 있는 편리한 사이트
					입니다.</p>
					<a class="btn" title="choose me please" href="https://www.daangn.com/hot_articles">더 보기</a>
			</aside>
			
			<blockquote>
				<p class="quote">비싸게 물건 사면 개 손해.. 새로 구매하지말고 여기서 좋은 가격으로 물건 구매합시다 :D</p>
				<p class="credit"><strong>글쓴이 이름</strong><br><em>"노한서","김태연","조현","최윤종"</em><br>발표시키지마조</p>
			</blockquote>
			</section>	
		<script>
		
		function handleOnChange(e) {
			  // 선택된 데이터 가져오기
			  const value = e.value;
			  
			}
		
		
		</script>
		
	</div>
	-->	
	<br><br><br><br><br><br><br>
	<div>
    	<%@ include file = "../common/footer.jsp" %>
    </div>
</body>
</html>