
<%@ page import="popor.com.enums.item.AddResult" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script defer src="/js/add.js"></script>
</head>
<body>
<form  id="add" method="post">
    <label for="name">상품명</label>
    <input name = "name" type="text" id="name"  autofocus/>
    <label for="price">가격</label>
    <input name = "price" type="text" id="price"  size="30" autofocus/>
    <label for="color">색상</label>
    <input name = "color" type="text" id="color"  autofocus/>
    <label for="size">사이즈</label>
    <input name = "size" type="text" id="size"  size="30" autofocus/>
    <input type="submit" class="button" value="작성">

</form>
</body>
</html>