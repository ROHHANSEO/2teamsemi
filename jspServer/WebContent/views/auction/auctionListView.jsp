<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.uni.usedItemBoard.model.vo.*"%>
<%
	   ArrayList<Category> list = (ArrayList<Category>)request.getAttribute("list");
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
		height:100vh;
		margin:0 auto;
	}
	.categorylist{
		display: flex;
    	width: 80%;
    	margin: 5% auto;
    	height: 10%;
    	cursor:pointer;

	}
	.categorydiv{
		border:1px solid black;
        flex-wrap: wrap;
        text-align: center;
        width: 70%;
		hight:15%;
     }
     .categorydiv p{
     	
     	line-height:6;
     }
</style>
</head>
<body>
	<%@include file="../common/header.jsp" %>
	<div class="wtotal">
		<div class="categorylist">
		<%for(Category ca : list){%>
		<div class="categorydiv"><p><%=ca.getName() %>
		</div>
		<%} %>
		</div>
	</div>
	
	<%--  <%@ include file = "../common/footer.jsp" %> --%>
	<script>

	</script>
</body>
</html>