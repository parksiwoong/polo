<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/index.css">
    <link rel="stylesheet" href="/css/reset.css">
    <link rel="stylesheet" href="/css/bottom.css">
<%--    <script defer src="/js/reset.js"></script>--%>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script defer src="${pageContext.request.contextPath}/js/find_password.js"></script>

    <title>회원찾기</title>
</head>
<header id="header"></header>
<%@ include file="../main/top.jsp" %>
<%@page isELIgnored="false" %>


<body>

<div style="height: auto; min-height: 100%; padding-bottom:50%;">

    <ul class="reset" >
        <h2>비밀번호찾기</h2>
        <form method="post" >
        <li><input id="email" name="email" type="email" placeholder="이메일" maxlength="100" autofocus value=""></li>
            <li><input id="name" name="name" type="text" placeholder="이름" maxlength="100" value=""></li>
            <li><input type="submit" value="비밀번호 찾기"></li>
        </form>

        <div>
            <ul>
                <li><a href="/login">로그인 페이지로 돌아가기</a></li>
<%--                <%= %>--%>
            </ul>
        </div>
    </ul>
</div>

</body>
<%@ include file="../main/bottom.jsp" %>
</html>