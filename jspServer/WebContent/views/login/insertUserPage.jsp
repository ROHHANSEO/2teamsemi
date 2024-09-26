<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="../../resources/css/common/common.css">
<link rel="stylesheet" href="../../resources/css/user/sign-upPage.css">
</head>
<body>
    <header id="header">
        <div id="logo">
            <a href="/"><img src="../../resources/images/logo.gif" alt="로고이미지"></a>
        </div>
        <span id="page_title">간편 회원가입</span>
    </header>
    <div id="input_container">
        <form id="input_box" action="<%=request.getContextPath() %>/insertUser" method="post">
            <div class="content_box">
                <div class="id_input">
                    <input id="idid" class="input" type="text" name="userId" placeholder="아이디" maxlength="15" required>
                    <button id="userIdCheck" class="input" type="button">중복확인</button>
                </div>
                <div>
                    <input class="userPwd" type="password" name="userPwd" placeholder="비밀번호 6자리~12자리" maxlength="12" required>
                </div>
                <div>
                    <input class="userPwdCheck" type="password" name="userPwdCheck" placeholder="비밀번호확인 6자리~12자리" maxlength="12" required>
                </div>
                <div>
                    <input type="text" name="name" placeholder="이름" maxlength="5" required>
                </div>
                <div>
                    <input type="text" name="citiNumber" placeholder="주민등록번호(-없이)" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="13" required>
                </div>
                <div>
                    <input type="text" name="phone" placeholder="전화번호(-없이)" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="11" required>
                </div>
                <div>
                    <input type="text" name="nickName" placeholder="닉네임" maxlength="10">
                </div>
                <div>
                    <input type="text" name="email" placeholder="이메일" maxlength="50">
                </div>
                <div>
                    <input id="male" type="radio" name="gender" value="M" checked><label for="male">남자</label>
                    <input id="female" type="radio" name="gender" value="F"><label for="female">여자</label>
                </div>
                <div>
                    <input id="consent" type="checkbox" name="consent"><label for="consent">개인정보제공 동의</label>
                    <textarea cols="36" rows="10" readonly>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quasi consequatur exercitationem quis, ratione ad dolor tempore corrupti omnis atque nisi necessitatibus animi, ex molestias laudantium voluptas praesentium! Officiis, blanditiis praesentium. Lorem ipsum dolor sit amet consectetur adipisicing elit. Suscipit perferendis, facilis officia quidem dolorum minus in molestiae odit quis eaque, molestias eius modi dicta earum quos voluptas tenetur illum itaque. Lorem ipsum dolor sit amet consectetur, adipisicing elit. Ea corrupti necessitatibus id veniam quas eos, corporis sit repudiandae, esse animi aspernatur modi asperiores laudantium impedit, tenetur laborum cumque quisquam at. Lorem ipsum dolor sit amet consectetur adipisicing elit. Asperiores, obcaecati? Nobis, consectetur? Dolores officiis, similique rerum nisi voluptas odit reiciendis architecto veritatis neque rem tempora harum voluptatem molestiae, facere quo!</textarea>
                </div>
                <button type="submit" class="button" disabled>다음</button>
            </div>
        </form>
    </div>

	<%@ include file = "../common/footer.jsp" %>
	
    <script src="../../resources/library/jquery-3.6.0.min.js"></script>
    <script src="../../resources/js/common/common.js"></script>
    <script src="../../resources/js/user/sign-upPage.js"></script>
</body>
</html>