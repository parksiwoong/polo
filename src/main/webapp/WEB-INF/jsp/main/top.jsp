<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page import="popor.com.vos.*" %>
<%@ page import="popor.com.vos.users.UserVo" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<%--        <link rel="stylesheet" href="/bootstrap.min.css">--%>

    <link rel="stylesheet" href="/css/bottom.css">
    <link rel="stylesheet" href="/css/index.css">
    <link rel="stylesheet" href="/css/mapapp.css">
    <link rel="stylesheet" href="/css/login.css">
    <link rel="stylesheet" href="/css/list.css">
    <script type="text/javascript" src="scroll_menu.js"></script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap">
    <title>POLO</title>
</head>
<body>
<header>



    <link rel="stylesheet" href="">
    <nav class="clearfix">
        <div class="left">
            <p>위치:</p>

            <select>
                <option>KOREA 한국어</option>
            </select>

        </div>

        <div class="logo"><a href="/polo"><img src="/img/logo.webp" alt=""></a>

        </div>

        </div>
        <% Object userVoObject = session.getAttribute("UserVo");
            UserVo userVo = null;
            if (userVoObject instanceof UserVo) {
                userVo = (UserVo) userVoObject;
            }
            if (userVo == null) {

        %>

        <div class="right" style="margin: 10px 0; ">
            <a href="/login"> <input class="button" type="button" value="l o g i n"></a>
            <a href="/register"> <input class="button" type="button" value="Sign Up"></a>
        </div>
        <%
        } else { // 로그인vo 가 있냐없냐 널이아니면 로그인한상태
            //로그아웃 구현할때는 세션에 있는 로그인 vo를 널ㄹ로 지정해주면 된다.
        %>
        <div class="right" style="margin: 10px 0;">
            <a href="/basket/list"> <input class="button" type="button" value="cart"></a>
            <a href="/login"> <input class="button" type="button" value="logout"></a>
        </div>
        <% } %>
    </nav>

    <div class="inb">

        <a href="/polo"><span>promotion</span> </a>
        <a href="/list/"><span>store</span></a>
        <a href="/kakaomap"><span>location</span> </a>
        <a href="/board?id=qna"><span> QNA </span> </a>
        <span></span>

    </div>
    <script type="text/javascript">
        let dir = window.location.pathname.split('/')[1].split('?')[0];
        // /polo > polo > polo
        // /board?id=qna > board ? id=qna > board

        let menus = window.document.body.querySelectorAll('.inb > a');
        switch (dir) {
            case 'list':
                menus[1].classList.add('on');
                break;
            case 'kakaomap':
                menus[2].classList.add('on');
                break;
            case 'board':
                menus[3].classList.add('on');
                break;
            default:
                menus[0].classList.add('on');
        }
    </script>
</header>



