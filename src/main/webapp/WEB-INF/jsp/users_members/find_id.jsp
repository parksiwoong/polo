<%@ page import="popor.com.vos.users.UserLoginVo" %>
<%@ page import="popor.com.enums.UserLoginResult" %>
<%@ page import="popor.com.vos.users_members.FindIdVo" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%    Object user = session.getAttribute("UserLoginVo");
    UserLoginVo userLoginVo = null;
    String predefinedName = "";
    String predefinedContect = "";

%>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/bottom.css">
    <script defer src="js/reset.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <title>회원찾기</title>
</head>
<header id="header"></header>
<%@ include file="../main/top.jsp" %>
<%@page isELIgnored="false" %>


<body>

    <%
//    Object userVoObject = session.getAttribute("UserVo");
//    UserVo userVo = null;
//    if(userVoObject instanceof UserVo){
//        userVo = (UserVo)userVoObject;
//    }
//    if (userVo != null){
//        response.sendRedirect("/board?id=ntc");
//        return;
//   }
        Object userLoginObject = session.getAttribute("FindIdVo");
        FindIdVo findIdVo = null;
        String predefinedName = "";
        String predefinedContact = "";
        if(userLoginObject instanceof UserLoginVo){
            userLoginVo = (UserLoginVo) userLoginObject;
        }
        if(userLoginVo != null){
            predefinedName = userLoginVo.getEmail();  //만약 널값이 로그인Vo 랑 안맞다면 왜 login.getE 를 preE 에 넣는건지
            predefinedPassword = userLoginVo.getPassword();
        } //값초기화하기위해 사용하는것
        session.setAttribute("UserLoginVo", null);

        Object userLoginResultObject = session.getAttribute("UserLoginResult");
        UserLoginResult userLoginResult = null;
        if(userLoginResultObject instanceof UserLoginResult){
            userLoginResult = (UserLoginResult) userLoginResultObject;
        }
    session.setAttribute("UserLoginResult", null);


    %>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/css/index.css">
<link rel="stylesheet" href="/css/login.css">
<link rel="stylesheet" href="/css/bottom.css">
<link rel="stylesheet" href="/js/login.js">

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<title>로그인</title>
<script defer src="/js/login.js"></script>
</head>
<header id="header"></header>

<body>
<%@ include file="../main/top.jsp" %>
<%@page isELIgnored="false" %>


<div style="height: auto; min-height: 100%; padding-bottom:50%;">

    <ul class="login">
        <h2>로그인</h2>
        <form method="post" id="login-form">
            <li><input id="login-email" name="email" type="email" placeholder="이메일" maxlength="100" autofocus value="<%=predefinedName%>"></li>
            <li><input id="login-password" name="password" type="password" placeholder="비밀번호" maxlength="100" value="<%=predefinedContect%>"></li>
            <li><input type="submit" value="로그인"></li>
            <li><input type="checkbox" id=""><label for="">아이디저장</label></li>
        </form>

        <div>
            <ul>
                <li><a href="/register">회원가입</a></li>
                <li><a href="id_reset">아이디 찾기</a></li>
                <li><a href="/find_password">비밀번호 찾기</a></li>
            </ul>
        </div>
    </ul>
</div>





<%
    if(userLoginResult != null){
        if(userLoginResult != UserLoginResult.SUCCESS){
%>
<script>alert("올바르지 않는 이메일 혹은 비밀번호입니다.");</script>
<%
        }
    }
%>
<%@ include file = "../main/bottom.jsp" %>
</body>
</html>