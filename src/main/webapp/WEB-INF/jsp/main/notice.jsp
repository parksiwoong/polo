<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>



<head>

    <meta http-equiv="Content-Type" content="text/xhtml+xml;charset=utf-8"/>

    <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>

    <script>

        //쿠키설정

        function setCookie(name, value, expiredays) {

            var todayDate = new Date();

            todayDate.setDate(todayDate.getDate() + expiredays);

            document.cookie = name + '=' + escape(value) + '; path=/; expires=' + todayDate.toGMTString() + ';'

        }


        //쿠키 불러오기

        function getCookie(name) {

            var obj = name + "=";

            var x = 0;

            while (x <= document.cookie.length) {

                var y = (x + obj.length);

                if (document.cookie.substring(x, y) == obj) {

                    if ((endOfCookie = document.cookie.indexOf(";", y)) == -1)

                        endOfCookie = document.cookie.length;

                    return unescape(document.cookie.substring(y, endOfCookie));

                }

                x = document.cookie.indexOf(" ", x) + 1;

                if (x == 0)

                    break;

            }

            return "";

        }


        //닫기 버튼 클릭시

        function closeWin(key) {

            if ($("#todaycloseyn").prop("checked")) {

                setCookie('divpop' + key, 'Y', 1);

            }

            $("#divpop" + key + "").hide();

        }


        $(function () {


            if (getCookie("divpop1") != "Y") {

                $("#divpop1").show();

            }


        });

    </script>

    <style>

        .divpop {

            position: absolute;
            z-index: 999;
            top: 50px;
            left: 50px;

            width: 350px;
            height: 500px;
            border: 1px solid black;
            background-color: #ffffff;
            display: none;

        }

        .title_area {
            font-weight: bold;
            text-align: center;
            width: 100%
        }

        .button_area {
            position: absolute;
            bottom: 0;
            left: 10px;
        }

    </style>

</head>



<form name="notice_form">

    <div id="divpop1" class="divpop">

        <div class="title_area">레이어 팝업 오늘 하루 이창을 열지 않음</div>

        <div class="button_area">

            <input type='checkbox' name='chkbox' id='todaycloseyn' value='Y'>오늘 하루 이 창을 열지 않음

            <a href='#' onclick="javascript:closeWin(1);"><B>[닫기]</B></a>

        </div>

    </div>

</form>
