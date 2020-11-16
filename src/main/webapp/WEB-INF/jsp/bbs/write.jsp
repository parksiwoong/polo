<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%
    String id = request.getParameter("id");
    String pageNum = request.getParameter("page") == null ? "1" : request.getParameter("page");
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>글 작성</title>
    <link rel="stylesheet" href="/css/index.css">
    <link rel="stylesheet" href="/css/bottom.css">
    <link rel="stylesheet" href="css/write.css">

    <script defer src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
    <script defer type="text/javascript" src="js/ckeditor.js"></script>
    <script defer type="text/javascript" src="js/write.js"></script>
</head>
<body>
<%--<%@ include file="../main/top.jsp" %>--%>
<form id="write-form" class="body-item form" method="post">
    <table class="form-item table">
        <caption>글작성</caption>
        <tbody>
        <tr>
            <td><!-- 제목 --><label><input class="property-full-width" type="text" name="title" maxlength="100" placeholder="제목"></label></td>
        </tr>
        <tr>
            <td><!-- 텍스트 --><label><textarea id="textarea2" class="property-full-width" name="content" maxlength="10000" placeholder=""></textarea></label></td>
        </tr>
        </tbody>
        <tfoot>
        <tr>
            <td><!-- 작성 버튼 등등 -->
                <div class="table-item button-container">
                    <input type="reset" class="button" value="다시 작성">
                    <input type="submit" class="button" value="작성">
                </div>
            </td>
        </tr>
        </tfoot>
    </table>
</form>
<a href="board?id=<%= id %>&page=<%= pageNum %>">목록으로 돌아가기</a>
<%@ include file="../main/bottom.jsp" %>
</body>
</html>