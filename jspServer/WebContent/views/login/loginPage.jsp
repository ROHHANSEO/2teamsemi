<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
//쿠키가져오기
Cookie[] cookies = request.getCookies();
if(cookies != null){
  for(Cookie tempCookie : cookies){
      if(tempCookie.getName().equals("loginCookie")){
          //실행흐름이 서버에 있을때 서버코드로써 강제이동한다.
          //특정 page로 이동하라는 정보만 준다.
          response.sendRedirect("/");
      }
  }
}
%>
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>로그인</title>
        <link rel="stylesheet" href="../../resources/css/user/loginPage.css">
        <link rel="stylesheet" href="../../resources/css/common/common.css">
        <link rel="shortcut icon" href="../../resources/images/favicon.ico">
    </head>
    <body>
        <div id="content">
            <div id="logo"><a href="/"><img src="../../resources/images/logo.gif" alt="메인로고이미지"></a></div>
            <div class="form">
                <form id="input_box" action="<%=request.getContextPath() %>/loginUser" method="post">
                    <table id="input_table">
                        <tr>
                            <td colspan="2"><input type="text" name="userId" placeholder="아이디" maxlength="15"></td>
                        </tr>
                        <tr>
                            <td colspan="2"><input type="password" name="password" placeholder="비밀번호" maxlength="12"></td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" name="loginCip" value="on"><span>자동로그인</span></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td colspan="2"><button type="submit">로그인</button></td>
                        </tr>
                    </table>
                </form>
                <div id="page_select_btn">
                    <span class="id_search"><a href="<%=request.getContextPath()%>/id_search">아이디 찾기</a></span>
                    <span class="pwd_search"><a href="<%=request.getContextPath()%>/pwd_search">비밀번호 찾기</a></span>
                    <span class="sign_up"><a href="<%=request.getContextPath()%>/sign_up">회원가입</a></span>
                </div>
            </div>
        </div>
        <%@ include file = "../common/footer.jsp" %>
        
        
        
        <script src="../../resources/library/jquery-3.6.0.min.js"></script>
        <script src="../../resources/js/user/loginPage.js"></script>
    </body>
    </html>