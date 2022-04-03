<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">  
<title>Insert title here</title>
<link rel="stylesheet" href="../../resources/css/mypage/myPageList.css">
</head>
<body>
    <aside id="mypage_list_form">
        <div id="mypage_list">
            <div id="content">
                <ul role="list">
                    <span>내기록</span>
                    <li class="sales"><a href="<%=request.getContextPath()%>/salesRecordPage">판매기록</a></li>
                    <li class="purchase"><a href="<%=request.getContextPath()%>/paymentRecordPage">결제기록</a></li>
                    <li class="auction"><a href="<%=request.getContextPath()%>/auctionRecordPage">경매정산</a></li>
                </ul>
                <ul role="list">
                    <span>내 정보</span>
                    <li class="edit"><span>개인정보 수정</span></li>
                    <li class="secession"><span>회원탈퇴</span></li>
                </ul>
                <ul role="list">
                    <span>내 활동</span>
                    <li class="likeProduct"><a href="<%=request.getContextPath()%>/likeProductPage">찜 리스트</a></li>
                    <li><span>채팅 목록</span></li>
                    <li><a href="<%=request.getContextPath()%>/myQtoAList">내 1:1 문의 목록</a></li>
                </ul>
                <ul role="list">
                    <span>관리자</span>
                    <li class="admin_code"><span>관리자 코드 입력</span></li>
                </ul>
            </div>
        </div>
    </aside>
</body>
</html>