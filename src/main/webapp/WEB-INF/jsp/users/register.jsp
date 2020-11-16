<%@ page import="popor.com.enums.UserRegisterResult" %>
<%@ page import="popor.com.vos.users.UserRegisterVo" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    Object userRegisterResultObject = session.getAttribute("UserRegisterResult");
    UserRegisterResult userRegisterResult = null;
    if (userRegisterResultObject instanceof UserRegisterResult) {
        userRegisterResult = (UserRegisterResult) userRegisterResultObject;
    }
    session.setAttribute("UserRegisterResult", null);

    Object userRegisterVoObject = session.getAttribute("UserRegisterVo");
    UserRegisterVo userRegisterVo = null;
    if (userRegisterVoObject instanceof UserRegisterVo) {
        userRegisterVo = (UserRegisterVo) userRegisterVoObject;
    }
    session.setAttribute("UserRegisterVo", null);

%>

<!DOCTYPE html>
<html lang="UTF-8">
<head>
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/bottom.css">
       <link rel="stylesheet" href="css/register.css">
    <meta charset="UTF-8">
    <title>회원가입</title>
    <script defer src="js/register.js"></script>

</head>
<body>


<%--<div style="height: auto; min-height: 100%; padding-bottom:50%;">--%>

<%--    <ul class="register">--%>
<%--        <h2>회원가입</h2>--%>
<%--        <form id="register-form" method="post">--%>
<%--            <li class="register_1"><input name="email" type="email"  maxlength="100" autofocus--%>
<%--                                          value="  "></li>--%>
<%--            <li class="register_1"><input name="password" type="password" placeholder="비밀번호" maxlength="100"--%>
<%--                                          value="">--%>
<%--            </li>--%>
<%--            <li class="register_1"><input name="password-check" type="password" placeholder="비밀번호 재입력" maxlength="100"--%>
<%--                                          value="">--%>
<%--            </li>--%>
<%--            <li class="register_1"><input name="name" type="text" placeholder="이름" maxlength="10"--%>
<%--                                          value=""></li>--%>
<%--            <li class="register_1"><input name="nickname" type="text" placeholder="닉네임" maxlength="10"--%>
<%--                                          value="">--%>
<%--            </li>--%>
<%--            <li class="register_1"><input name="contact" type="tel" placeholder="연락처" maxlength="11"--%>
<%--                                          value=""></li>--%>


<%--            <li class="button"><input type="submit" value="회원가입"></li>--%>
<%--            <li class="button"><input type="reset" value="다시입력"></li>--%>

<%--        </form>--%>
<%--    </ul>--%>
<%--    <div>--%>
<%--        <ul>--%>
<%--            <a href="/login" target="_self">로그인 페이지로 돌아가기</a>--%>
<%--        </ul>--%>
<%--    </div>--%>
<%--</div>--%>

<%@ include file = "../main/top.jsp" %>

<style type="text/css">
    /*body{margin: 0; padding: 0;}*/
   .gaip {position: relative; min-height: 100%;  justify-content: center;height: 100vh;
       padding: 240px 381px;}
    .feedbackform { position: absolute;padding: 1%;  }
    div.fieldwrapper {  width: 550px; overflow: hidden; padding: 5px 0;  }
    div.fieldwrapper label.styled {
        float: left;
        width: 150px;
        text-transform: uppercase;
        border-bottom: 1px solid #292828;
        margin-right: 15px;
    }
    div.fieldwrapper div.thefield {  float: left;  margin-bottom: 10px;  }
    div.fieldwrapper div.thefield input[type="text"][type="password"][type="email"] {  width: 350px; }
    div.fieldwrapper div.thefield textarea {  width: 300px;
        height: 150px;
    }
    div.buttonsdiv {   margin-top: 5px;   }
    div.buttonsdiv input {   width: 80px;   background: #e1dfe0;  }
</style>


<div class="gaip">
<form class="feedbackform" id="register-form" method="post">

    <div class="fieldwrapper">
        <label for="email" class="styled">Your Email:</label>
        <div class="thefield">
           <input name = "email" type="email" id="email"  value="<%= userRegisterVo != null ? userRegisterVo.getEmail() : "" %>" size="30" placeholder="ex) tldnd5656@naver.com" autofocus/>
        </div>
    </div>

    <div class="fieldwrapper">
        <label for="password" class="styled">Password:</label>
        <div class="thefield">
            <input type="text" name = "password" id="password" value="<%= userRegisterVo != null ? userRegisterVo.getPassword() : "" %>" size="30" autofocus/>
        </div>
    </div>

    <div class="fieldwrapper">
        <label for="password_check" class="styled">password-check:</label>
        <div class="thefield">
            <input type="text" name = "password-check" id="password_check" value="<%= userRegisterVo != null ? userRegisterVo.getPassword() : "" %>" size="30" autofocus/>
        </div>
    </div>
    <div class="fieldwrapper">
        <label for="nickname" class="styled">NICKNAME:</label>
        <div class="thefield">
            <input type="text"name = "nickname" id="nickname" value="<%= userRegisterVo != null ? userRegisterVo.getNickname() : "" %>" size="30" autofocus/>
        </div>
    </div>
    <div class="fieldwrapper">
        <label for="contact" class="styled">TELL:</label>
        <div class="thefield">
            <input type="tel" name = "contact" id="contact" value="<%= userRegisterVo != null ? userRegisterVo.getContact() : "" %>" size="30"placeholder="ex) 01012341234" autofocus/>
        </div>
    </div>
    <div class="fieldwrapper">
        <label for="name" class="styled">Your Name:</label>
        <div class="thefield">
            <input type="text" id="name" name="name" value="<%= userRegisterVo != null ? userRegisterVo.getName() : "" %>" size="30"/><br/>
            <span style="font-size: 80%">*Note: Please make sure it's correctly entered!</span>

        </div>
    </div>

<%--    <div class="btn-gr">--%>
<%--        <label>--%>
<%--           <injput type="radio" name="registerGender" autoconplete=""off value="<%= userRegisterVo != null ? userRegisterVo.getName("남자") : "" %>" checked></injput>--%>
<%--        </label>--%>
<%--        <label>--%>
<%--            <injput type="radio" name="registerGender" autoconplete=""off value="<%= userRegisterVo != null ? userRegisterVo.getName("남자") : "" %>" checked></injput>--%>
<%--        </label>--%>

<%--    </div>--%>


    <div class="fieldwrapper">
        <label for="student" class="styled">education:</label>
        <div class="thefield">
            <ul style="margin-top:0;">
                <li><input type="radio" id="student" name="education" value=""/> <label for="student">student </label></li>
                <li><input type="radio" id="highschool" name="education" value=""/> <label for="highschool">Highschool graduate</label></li>
                <li><input type="radio" id="employee" name="education" value=""/> <label for="employee">employee</label></li>
                <li><input type="radio" id="vocation" name="education" value=""/> <label for="vocation">Vocation school</label></li>
                <li><input type="radio" id="old man" name="education" value=""/> <label for="old man">old man</label></li>
            </ul>
        </div>
    </div>

    <div class="fieldwrapper">
        <label for="overfit" class="styled">STAYLE:</label>
        <div class="thefield">
            <ul style="margin-top:0;">
                <li><input type="checkbox" id="overfit" name="skills" value=""/> <label for="overfit">OVER FIT</label></li>
                <li><input type="checkbox" id="slimfit" name="skills" value=""/> <label for="slimfit">SLIM FIT</label></li>
                <li><input type="checkbox" id="rouge" name="skills" value=""/> <label for="rouge"></label>ROUGE FIT</li>
            </ul>
            <span style="font-size: 80%">* Please check all that apply.</span>
        </div>
    </div>

    <div class="fieldwrapper">
        <label for="gender" class="styled">GENDER:</label>
        <div class="thefield">
            <select id="gender">
                <option value="man">man</option>
                <option value="girl">girl</option>
            </select>
        </div>
    </div>

    <div class="fieldwrapper">
        <label for="about" class="styled">About yourself:</label>
        <div class="thefield">
            <textarea id="about"></textarea>
        </div>
    </div>

    <div class="buttonsdiv">
        <input type="submit" value="Submit" style="margin-left: 150px;"/> <input type="reset" value="Reset"/>
    </div>

</form>
</div>
<%
    if (userRegisterResult != null) {
        switch (userRegisterResult) {
            case SUCCESS:
                response.getWriter().print("<script>alert(\"회원가입이 완료되었습니다.\");</script>");
                response.getWriter().print("<script>window.location.href=\"login\";</script>");
                break;
            case EMAIL_DUPLICATE:
                response.getWriter().print("<script>alert(\"이미 사용 중인 이메일입니다.\");</script>");
                break;
            case NICKNAME_DUPLICATE:
                response.getWriter().print("<script>alert(\"이미 사용 중인 닉네임입니다.\");</script>");
                break;
            case CONTACT_DUPLICATE:
                response.getWriter().print("<script>alert(\"이미 사용 중인 연락처입니다.\");</script>");
                break;
            case FAILURE:
                response.getWriter().print("<script>alert(\"알 수 없는 이유로 회원가입에 실패하였습니다. 잠시 후 다시 시도해주세요.\");</script>");
                break;
        }
    }
%>
<%@ include file = "../main/bottom.jsp" %>
</body>


</html>