<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.uni.serviceCenter.model.vo.*"%>
   <%
	ArrayList<QtoA> list = (ArrayList<QtoA>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../../resources/css/admin/QtoAListPage.css">
<style>
	#salescontent_box {
		float: left;
    	width: 80%;
	}

	#salescontent_box .title_box{
		margin: 30px 0;
    /* margin-left: 200px; */
    	text-align: center;
	}

	.output_table_box {
		border: 1px solid black;
		/* width: 70%; */
		margin: 30px auto;
	}

	#footer{
		clear: both;
	}
	
	#title_box{
		margin: 30px 0;
		text-align: center;
	}

</style>
</head>
<body>
<%@include file="../common/header.jsp" %>
<div class="mypage_list_page">
    <%@include file = "../common/mypageList.jsp" %>
</div>
<div id="salescontent_box">
	<div id="title_box">
		<span class="title_name">내 1:1 문의</span>
	</div>
	<div class = "output_table_box">
		<table class="questionList" id="questionList" >
			<thead>
				<tr>
					<th class="SCQNo"> 번호 </th>
					<th class="SCQTitle"> 문의사항 </th>
					<th class="SCQDatere"> 작성일 </th>
				</tr>
			</thead>
			<tbody class = "QadminList" id = "QadminList"><!-- 1:1문의사항 나오는 부분 -->
				<% if(list == null){ %>
				<tr>
					<th colspan="4"> 존재하는 문의사항이 없습니다 </th>
				</tr>
				<%}else{ %>
					<%for(QtoA qa : list){ %>
						<tr class="QtoAlistpoint">
							<th><%=qa.getQuestionNo()%></th><!-- 문의넘버 -->
							<td><%=qa.getQuestionTitle() %></td><!-- 문의사항 제목 -->
							<th><%=qa.getCreateDate() %></th><!-- 문의사항 작성일 -->
						</tr>
					<% } %>
				<% } %>
			</tbody>
		</table>
	</div>
</div>
	<%@include file="../common/footer.jsp" %>
	<script src="../../resources/js/admin/QtoAListPage.js"></script>
	<script type="text/javascript">
	/* 관리자 상세페이지 */
	<%if(list != null){%>
	$(function(){
		$("#questionList>tbody>tr").click(function(){
			var scno = $(this).children().eq(0).text();
			console.log(scno)
			location.href="<%=request.getContextPath()%>/detailQtoA.do?scno="+scno;
		})
	})
	<%}%>
	</script>
</body>
</html>