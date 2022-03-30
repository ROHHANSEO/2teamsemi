<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.uni.admin.model.vo.*"%>
<%
	ArrayList<BlockBoard> list = (ArrayList<BlockBoard>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../../resources/css/common/common.css">
<link rel="stylesheet" href="../../resources/library/animate.css">
<link rel="stylesheet" href="../../resources/css/admin/reportPage.css">
</head>
<body>
	<%@include file="../common/header.jsp" %>
	<%@include file="../admin/AdminTitle.jsp" %>
	<%@include file="../admin/reportDetailModal.jsp" %>
    <div class="serviceCenterRe title_name"> 신고 게시판 </div>
    <div class="serviceCenterArea content_box" align="center">
		<table class="serviceno content_table" >
			<thead>
				<tr>
					<th>게시글 번호</th>
					<th>카테고리</th>
				    <th class="serviceCenterQuestion content_table_title">제목</th>
					<th>신고횟수</th>
				</tr>
			</thead>
			<tbody class="serviceCListFinal content_text" >
				<%for(BlockBoard a : list){%>
					<tr class="block_board_list no<%=a.getBoardNo() %>">
						<th class="board_no"><%=a.getBoardNo() %></th>
						<th class="board_class_name"><%=a.getBoardCategoryNm() %></th>
						<td class="board_title_name"><%=a.getBoardTitle()%></td>
						<th class="board_reprot_count"><%=a.getReportCount() %></th>
					</tr>
				<%}%>
			</tbody>
		</table>
		<br><br>
    </div>
    <%@ include file = "../common/footer.jsp" %>
	<script src="../../resources/js/admin/reportPage.js"></script>
</body>
</html>