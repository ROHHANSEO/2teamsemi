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
</head>
<body>
<%@include file="../common/header.jsp" %>
<%@include file="../admin/AdminTitle.jsp" %>
	<div id="title_box">
		<span class="title_name">1:1 문의답변</span>
	</div>
	<div class = "testadmin">
		<table class="questionList" id="questionList" >
			<thead>
				<tr>
					<th class="SCQNo"> 번호 </th>
					<th class="SCQTitle"> 문의사항 </th>
					<th class="SCQWriter"> 회원아이디 </th>
					<th class="SCQDatere"> 작성일 </th>
				</tr>
			</thead>
			<tbody class = "QadminList" id = "QadminList"><!-- 1:1문의사항 나오는 부분 -->
				<% if(list==null){ %>
				<tr>
					<td colspan="4"> 존재하는 문의사항이 없습니다 </td>
				</tr>
				<%}else{ %>
				<%for(QtoA qa : list){ %>
				<tr class="QtoAlistpoint">
					<th><%=qa.getQuestionNo()%></th><!-- 문의넘버 -->
					<td><%=qa.getQuestionTitle() %></td><!-- 문의사항 제목 -->
					<th><%=qa.getUserNo() %></th><!-- 문의사항 작성 회원 아이디 -->
					<th><%=qa.getCreateDate() %></th><!-- 문의사항 작성일 -->
				</tr>
				<% } %>
				<% } %>
			</tbody>
		</table>
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