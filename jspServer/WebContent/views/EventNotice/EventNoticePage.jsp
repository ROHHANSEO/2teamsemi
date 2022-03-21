<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.uni.event.model.vo.Event"%>
<%
	ArrayList<Event> list = (ArrayList<Event>) request.getAttribute("list");
	
	
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
<title>이벤트&공지사항</title>

</head>
	
<div>
    	<%@ include file = "../common/header.jsp" %>
    </div>
<body>
	<div class="outer">
		<br>
		<h2 align="center">이벤트&공지사항</h2>
		<br>
		<h4>공지사항 리스트</h4>
		<script type="text/javascript">
		
		</script>
		<a href="<%=request.getContextPath() %>/eventpage.do?pt=all"><button style='<%= (pt.equals("all"))? style : " " %>'>전체</button></a>
		<a href="<%=request.getContextPath() %>/eventpage.do?pt=now"><button style='<%= (pt.equals("now"))? style : " " %>'>현재</button></a>
		<a href="<%=request.getContextPath() %>/eventpage.do?pt=old"><button style='<%= (pt.equals("old"))? style : " " %>'>지난</button></a>
		
		
		     
		<table class="listArea" align="center">
		 
			<thead>
				<tr class="menubar">
					<th>글번호</th>
					<th width="500">글제목</th>
					<th width="100">작성자</th>
					<th width="100">작성일</th>
				</tr>
			</thead>
			<tbody>
				

				<% if(list.isEmpty()){ %>
				 	<tr>
						<td colspan="5">존재하는 공지사항이 없습니다.</td>
					</tr>
				 <% }else{  %>
				 	<% for(Event n : list){ %>
				 		<tr>
				 			<td><%= n.getNOTICE_NO() %></td>
							<td><%= n.getNOTICE_TITLE() %></td>
							<td><%= n.getUSER_NO() %></td>
							<td><%= n.getCREATE_DATE() %></td>
				 		</tr>
				 	<% } %>
				 <% } %>
			</tbody>
			
		</table>
	
	<form class="searchArea" align="center">
			<select id="condition" name="condition">
				<option value="writer">작성자</option>
				<option value="title">제목</option>
				<option value="content">내용</option>
			</select>
			
			
			
			<input type="search" name="search">
			<button type="submit">검색하기</button>
			
			<input type="button" id="inse" value="글 작성하기" onclick="location.href='<%= request.getContextPath() %>/insertEventPage.do'">	
		</form>
		
		
		<br><br>
		
		
		</div>
		
		<div class="banner">
			<img alt="choose me please" src="../../resources/images/cat.gif">
			고양이도 엄청 좋아하는 새지마 마켓 사이트 
			<a href="#aa">닫기</a>
		</div>
		
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
		
		
	</div>
	<div>
    	<%@ include file = "../common/footer.jsp" %>
    </div>
</body>
</html>