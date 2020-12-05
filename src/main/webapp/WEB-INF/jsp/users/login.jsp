<%@ page import="popor.com.vos.users.UserLoginVo" %>
<%@ page import="popor.com.enums.UserLoginResult" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ include file="../main/top.jsp" %>
<%@page isELIgnored="false" %>

<%
        Object userLoginObject = session.getAttribute("UserLoginVo");
        UserLoginVo userLoginVo = null;
        String predefinedEmail = "";
        String predefinedPassword = "";
        if(userLoginObject instanceof UserLoginVo){
            userLoginVo = (UserLoginVo) userLoginObject;
        }
        if(userLoginVo != null){
            predefinedEmail = userLoginVo.getEmail();  //만약 널값이 로그인Vo 랑 안맞다면 왜 login.getE 를 preE 에 넣는건지
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


    <title>로그인</title>
<%--    <script defer src="/js/login.js"></script>--%>
<script defer src="/js/login.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>


<body>
<div style="height: auto; min-height: 100%; padding-bottom:50%;">

    <ul class="login">
        <h2>로그인</h2>
        <form method="post" id="login-form">
            <li><input id="login-email" name="email" type="email" placeholder="이메일" maxlength="100" autofocus value="<%=predefinedEmail%>"></li>
            <li><input id="login-password" name="password" type="password" placeholder="비밀번호" maxlength="100" value="<%=predefinedPassword%>"></li>
            <li><input type="submit" value="로그인"></li>
        <li><input type="checkbox" id=""><label for="">아이디저장</label></li>
        </form>

        <div>
            <ul>
                <li><a href="/register">회원가입</a></li>
                <li><a href="/select_email">아이디 찾기</a></li>
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
