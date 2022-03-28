<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
</head>
<body>
<%@include file="../common/header.jsp" %>
<%@include file="../admin/AdminTitle.jsp" %>
<section id="report_section">
    <div class="report_title_box">
        <div id="report_title">
            <span class="report_title_name">신고 게시판</span>
        </div>
        <div>
            <button type="button">버튼</button>
        </div>
    </div>
    <div class="report_container">
        <table class="report_content_table" style="margin: 0 auto;">
            <thead class="report_content_title">
                <tr>
                    <th>게시글 번호</th>
                    <th width="86%">게시글 제목</th>
                    <th>신고 횟수</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>1000000</td>
                    <td>dkdkdkdk</td>
                    <td>3</td>
                </tr>
                <tr>
                    <td>
                        <tr>
                            <th>신고카테고리</th>
                            <th>신고 제목</th>
                            <th>신고 시간</th>
                        </tr>
                        <tr>
                            <td>욕설</td>
                            <td>asdjlajkdas</td>
                            <td>22.03.25</td>
                        </tr>
                        <tr>
                            <td>욕설</td>
                            <td>aasddas</td>
                            <td>22.03.26</td>
                        </tr>
                        <tr>
                            <td>욕설</td>
                            <td>assssssas</td>
                            <td>22.03.27</td>
                        </tr>
                    </td>
                </tr>
                <tr>
                    <td>123123</td>
                    <td>aaaaaaaaaa</td>
                    <td>2</td>
                </tr>
            </tbody>
        </table>
    </div>
</section>


<%@include file="../common/footer.jsp" %>
</body>
</html>