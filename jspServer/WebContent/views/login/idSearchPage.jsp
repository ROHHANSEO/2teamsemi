<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<link rel="stylesheet" href="../../resources/css/common/common.css">
<link rel="stylesheet" href="../../resources/css/sign-upPage.css">
<link rel="stylesheet" href="../../resources/css/idSearchpage.css">
</head>
<body>
    <header id="header">
        <div id="logo">
            <a href="/"><img src="../../resources/images/logo.gif" alt="로고이미지"></a>
        </div>
        <span id="page_title">아이디 찾기</span>
    </header>
    <div id="input_container">
        <form id="input_box" action="<%=request.getContextPath() %>/idSearch" method="post">
            <div class="content_box">
                <div>
                    <input type="text" name="name" placeholder="이름" maxlength="5" required>
                </div>
                <div>
                    <input type="text" name="citiNumber" placeholder="주민등록번호(-없이)" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="13" required>
                </div>
                <div>
                    <input type="text" name="phone" placeholder="전화번호(-없이)" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="11" required>
                </div>
                <button type="submit" class="button">다음</button>
            </div>
        </form>
    </div>

	<%@ include file = "../common/footer.jsp" %>
	
    <script src="../../resources/library/jquery-3.6.0.min.js"></script>
    <script src="../../resources/js/common/common.js"></script>
</body>
</html>