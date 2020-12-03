<%@ page import="popor.com.enums.UserLoginResult" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html lang="UTF-8">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>메인페이지</title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
<link rel="stylesheet" href="/css/index.css">
<header id="header"></header>
<%@ include file = "top.jsp" %>
<%@ include file = "notice.jsp" %>
<% Object userLoginResultObject = session.getAttribute("UserLoginResult");
    UserLoginResult userLoginResult = null;
    if(userLoginResultObject instanceof UserLoginResult){
        userLoginResult = (UserLoginResult)userLoginResultObject;
    }
    session.setAttribute("UserLoginResult", null);%>

<%
    if (userLoginResult != null) {
        if (userLoginResult != UserLoginResult.SUCCESS) {
%>
<script>alert("올바르지 않은 이메일 혹은 비밀번호입니다. 다시 확인해주세요.");</script>
<%
        }
    }
%>

<section>
    <div class="part1">
        <ul>
            <li>  <video width="100%"  muted autoplay loop><source src="/img/intro.mp4"></video></li>
            <li>      <a href="/man_shop"><div class="screen">
                <div class="top"></div>
                <div class="bottom" >자세히보기</div>
                <span><img src="/img/spring20_men_hero_pc_v3.webp" alt=""></span>
            </div>
                <!-- <div><h3>이미지위에 오브젝트가 나타나는효과</h3></div> -->
            </a> </li>
            <li>         <a href="/man_shop"><div class="screen">
                <div class="top"></div>
                <div class="bottom" >자세히보기</div>
                <img src="spring20_women_hero_pc_v3.jpg"  alt="">
            </div>
                <!-- <div><h3>이미지위에 오브젝트가 나타나는효과</h3></div> -->
            </a></li>
            <li>         <a href="#a"><div class="screen">
                <div class="top"></div>
                <div class="bottom" >자세히보기</div>
                <img src="/img/spring20_kids_hero_pc_v2.webp" alt="">
            </div>
                <!-- <div><h3>이미지위에 오브젝트가 나타나는효과</h3></div> -->
            </a></li>
            <li>         <a href="#a"><div class="screen">
                <div class="top"> </div>
                <div class="bottom" >자세히보기</div>
                <img src="/img/APAC_Spring2020_PurpleLabelLP_Retouched_HP_03.webp" alt="">
            </div>
                <!-- <div><h3>이미지위에 오브젝트가 나타나는효과</h3></div> -->
            </a></li>
            <li>       <a href="#a"><div class="screen">
                <div class="top"></div>
                <div class="bottom" >자세히보기</div>
                <img src="/img/MB_WatchesLP_admiral_2.webp" alt="">
            </div>
                <!-- <div><h3>이미지위에 오브젝트가 나타나는효과</h3></div> -->
            </a></li>
            <li>        <a href="#a"><div class="screen">
                <div class="top"></div>
                <div class="bottom" >자세히보기</div>
                <img src="/img/iwd_rl_style.webp" alt="">
            </div>
                <!-- <div><h3>이미지위에 오브젝트가 나타나는효과</h3></div> -->
            </a></li>
        </ul>
    </div>
</section>
<div class="sum">
    <ul>
        <li><img src="grey-line.WEBP" alt=""></li>
        <li><img src="/img/RL-Sign-Navy_0.WEBP" alt=""></li>
        <li><img src="grey-line.WEBP" alt=""></li>
    </ul>
</div>
<section class="slade">
    <div>


    </div>
</section>
<link rel="stylesheet" href="/css/bottom.css">
<%--<style>--%>
<%--footer{background: #363947; color: #797676; margin-top: 120px; width: 100%;   }--%>
<%--footer> div { width:100%; padding: 45px 0; margin: 0 auto;}--%>
<%--footer > div > .right {float: right; position: absolute; right: 0; top: 0; width: 39px; height: 49px; margin: -20px 100px 0 0;}--%>
<%--footer > div .left{text-align: center}--%>
<%--footer img{width:51px; height: 49px;}--%>
<%--footer p{color: rgb(255, 255, 255);}</style>--%>
<%--<footer>--%>
<%--    <div>--%>



<%--        <div class="left">--%>
<%--            <div>--%>
<%--                <img src="/img/html.png">--%>
<%--                <img src="/img/css3_4.png" width= "51px;" height= "49px;">--%>
<%--                <img src="/img/js.png">--%>
<%--                <img src="/img/java.png">--%>
<%--                <img src="/img/jsp.png">--%>
<%--                <img src="/img/myslq.png">--%>
<%--                <img src="/img/in.png">--%>
<%--                <img src="/img/visu.png">--%>

<%--                <a href="#"><p style= "padding: 20px 0 0 0 ; font: italic bold 1em/1em Georgia, serif ;">메인 포토폴리오 돌아가기</p></a>--%>
<%--            </div>--%>

<%--        </div>--%>
<%--        <div class="right">  <a href="#"><img src="/img/up.jpg"></a></div>--%>

<%--    </div>--%>
<%--</footer>--%>
<%@ include file = "bottom.jsp" %>
</body>


</html>