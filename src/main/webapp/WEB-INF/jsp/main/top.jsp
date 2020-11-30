<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page import="popor.com.vos.*" %>
<%@ page import="popor.com.vos.users.UserVo" %>

<link rel="stylesheet" href="/css/index.css">

<header>

    <script type="text/javascript">

        $(document).ready(function(){
            $('.inb a') .on ('click', function(){
                $(this).addClass('on');
                $(this).siblings().removeClass('on');
            });
        });
    </script>

    <link rel="stylesheet" href="">
    <nav class="clearfix" >
        <div class="left">
            <p>위치:</p>

            <select>
                <option>KOREA 한국어</option></select>

        </div>

        <div class="logo"><a href="/polo"><img src="img/logo.webp" alt=""></a>

        </div>

        </div>
        <%  Object userVoObject = session.getAttribute("UserVo");
            UserVo userVo = null;
            if(userVoObject instanceof UserVo){
                userVo = (UserVo)userVoObject;
            }if(userVo == null) {

        %>

        <div class="right" style="margin: 10px 0; " >
            <a href="/login" > <input class="button" type="button" value="l o g i n"></a>
            <a href="/register" > <input class="button" type="button" value="Sign Up"></a>
        </div>
        <%
            } else{ // 로그인vo 가 있냐없냐 널이아니면 로그인한상태
                //로그아웃 구현할때는 세션에 있는 로그인 vo를 널ㄹ로 지정해주면 된다.
        %>
        <div class="right" style="margin: 10px 0;">
            <a href="#" > <input class="button" type="button" value="cart"></a>
            <a href="/logout" > <input class="button" type="button" value="logout"></a>
        </div>
        <% } %>
    </nav>

    <div class="inb">

        <a href="/polo" class="on"><span>promotion</span> </a>
        <a href="/list/"><span>store</span> </a>
        <a href="/kakaomap"><span>location</span> </a>
        <a href="/board?id=qna"><span> QNA </span></a>
        <span></span>
    </div>
    </div>

</header>



