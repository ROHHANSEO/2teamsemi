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
                    <li class="purchase"><a href="<%=request.getContextPath()%>/purchaseRecordPage">구매기록</a></li>
                    <li class="auction"><a href="<%=request.getContextPath()%>/auctionRecordPage">경매정산</a></li>
                    <li class="community"><a href="<%=request.getContextPath()%>/communityRecordPage">내가 쓴 커뮤니티글</a></li>
                </ul>
                <ul role="list">
                    <span>내 정보</span>
                    <li class="edit"><span>개인정보 수정</span></li>
                    <li class="secession"><span>회원탈퇴</span></li>
                </ul>
                <ul role="list">
                    <span>내 활동</span>
                    <li><span>찜 리스트</span></li>
                    <li><span>채팅 목록</span></li>
                </ul>
            </div>
        </div>
    </aside>
</body>
</html>