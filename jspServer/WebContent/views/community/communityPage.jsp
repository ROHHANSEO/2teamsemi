<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" media="screen" href="../../resources/css/community/community.css">
<link rel="stylesheet" type="text/css" media="screen" href="../../resources/css/common/common.css">
<html>
<head>
    <title>커뮤니티</title>
    
    
   
    
</head>
<body>    
<%@ include file = "../common/header.jsp" %>



<div id="wrap">
    <br>
    <div id="topForm">
        <c:if test="${sessionScope.sessionID!=null}">
            <input type="button" class="commonwritebutton bts4" value="글작성" onclick="location.href='<%= request.getContextPath() %>/insertcommunity.do'">
        </c:if>    
    </div>
    <br>
    <h2 align="center" class="comname" >커뮤니티</h2>
    <br>
		 <nav id="topMenu" >
                <ul>
                        <li><a class="menuLink" href="#">근처 맛집</a></li>
                        <li><a class="menuLink" href="#">취미생활</a></li>
                        <li><a class="menuLink" href="#">유용한 정보 공유</a></li>
                        <li><a class="menuLink" href="#">동물</a></li>
                        <li><a class="menuLink" href="#">기타</a></li>
                </ul>
        </nav>
        
        <div class="btslist" >
        <a href="<%= request.getContextPath() %>/communitypage.do">최신순</a> | <a href="<%= request.getContextPath() %>/communitypage.do">인기순</a>
	    </div>
   
	   
	 
	</form>
	
	
   
   

    <br>
    <div id="pageForm">
        페이지 번호
    </div>
    <br>
    <div id="searchForm">
        <form>
            <select name="opt">
                <option value="0">제목</option>
                <option value="1">내용</option>
                <option value="3">글쓴이</option>
            </select>
            <input type="text" size="20" name="condition"/>&nbsp;
            <input type="submit" class="commonwritebutton bts4" value="검색"/>
        </form>    
    </div>
</div>    
 
 <br><br>
 <%@ include file = "../common/footer.jsp" %>
</body>
</html>
